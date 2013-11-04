package my.apache.http.impl.auth;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import my.apache.commons.codec.binary.Base64;
import my.apache.http.util.EncodingUtils;

final class NTLMEngineImpl
  implements NTLMEngine
{
  static final String DEFAULT_CHARSET = "ASCII";
  protected static final int FLAG_DOMAIN_PRESENT = 4096;
  protected static final int FLAG_REQUEST_128BIT_KEY_EXCH = 536870912;
  protected static final int FLAG_REQUEST_56BIT_ENCRYPTION = -2147483648;
  protected static final int FLAG_REQUEST_ALWAYS_SIGN = 32768;
  protected static final int FLAG_REQUEST_EXPLICIT_KEY_EXCH = 1073741824;
  protected static final int FLAG_REQUEST_LAN_MANAGER_KEY = 128;
  protected static final int FLAG_REQUEST_NTLM2_SESSION = 524288;
  protected static final int FLAG_REQUEST_NTLMv1 = 512;
  protected static final int FLAG_REQUEST_SEAL = 32;
  protected static final int FLAG_REQUEST_SIGN = 16;
  protected static final int FLAG_REQUEST_TARGET = 4;
  protected static final int FLAG_REQUEST_UNICODE_ENCODING = 1;
  protected static final int FLAG_REQUEST_VERSION = 33554432;
  protected static final int FLAG_TARGETINFO_PRESENT = 8388608;
  protected static final int FLAG_WORKSTATION_PRESENT = 8192;
  private static final SecureRandom RND_GEN;
  private static byte[] SIGNATURE;
  private String credentialCharset = "ASCII";

  static
  {
    try
    {
      SecureRandom localSecureRandom2 = SecureRandom.getInstance("SHA1PRNG");
      localSecureRandom1 = localSecureRandom2;
      RND_GEN = localSecureRandom1;
      byte[] arrayOfByte = EncodingUtils.getBytes("NTLMSSP", "ASCII");
      SIGNATURE = new byte[1 + arrayOfByte.length];
      System.arraycopy(arrayOfByte, 0, SIGNATURE, 0, arrayOfByte.length);
      SIGNATURE[arrayOfByte.length] = 0;
      return;
    }
    catch (Exception localException)
    {
      while (true)
        SecureRandom localSecureRandom1 = null;
    }
  }

  static int F(int paramInt1, int paramInt2, int paramInt3)
  {
    return paramInt1 & paramInt2 | paramInt3 & (paramInt1 ^ 0xFFFFFFFF);
  }

  static int G(int paramInt1, int paramInt2, int paramInt3)
  {
    return paramInt1 & paramInt2 | paramInt1 & paramInt3 | paramInt2 & paramInt3;
  }

  static int H(int paramInt1, int paramInt2, int paramInt3)
  {
    return paramInt3 ^ (paramInt1 ^ paramInt2);
  }

  static byte[] RC4(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws NTLMEngineException
  {
    try
    {
      Cipher localCipher = Cipher.getInstance("RC4");
      localCipher.init(1, new SecretKeySpec(paramArrayOfByte2, "RC4"));
      byte[] arrayOfByte = localCipher.doFinal(paramArrayOfByte1);
      return arrayOfByte;
    }
    catch (Exception localException)
    {
      throw new NTLMEngineException(localException.getMessage(), localException);
    }
  }

  private static String convertDomain(String paramString)
  {
    return stripDotSuffix(paramString);
  }

  private static String convertHost(String paramString)
  {
    return stripDotSuffix(paramString);
  }

  private static byte[] createBlob(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
  {
    byte[] arrayOfByte1 = new byte[4];
    arrayOfByte1[0] = 1;
    arrayOfByte1[1] = 1;
    byte[] arrayOfByte2 = new byte[4];
    byte[] arrayOfByte3 = new byte[4];
    byte[] arrayOfByte4 = new byte[4];
    byte[] arrayOfByte5 = new byte[8 + (arrayOfByte1.length + arrayOfByte2.length + paramArrayOfByte3.length) + arrayOfByte3.length + paramArrayOfByte2.length + arrayOfByte4.length];
    System.arraycopy(arrayOfByte1, 0, arrayOfByte5, 0, arrayOfByte1.length);
    int i = 0 + arrayOfByte1.length;
    System.arraycopy(arrayOfByte2, 0, arrayOfByte5, i, arrayOfByte2.length);
    int j = i + arrayOfByte2.length;
    System.arraycopy(paramArrayOfByte3, 0, arrayOfByte5, j, paramArrayOfByte3.length);
    int k = j + paramArrayOfByte3.length;
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte5, k, 8);
    int m = k + 8;
    System.arraycopy(arrayOfByte3, 0, arrayOfByte5, m, arrayOfByte3.length);
    int n = m + arrayOfByte3.length;
    System.arraycopy(paramArrayOfByte2, 0, arrayOfByte5, n, paramArrayOfByte2.length);
    int i1 = n + paramArrayOfByte2.length;
    System.arraycopy(arrayOfByte4, 0, arrayOfByte5, i1, arrayOfByte4.length);
    (i1 + arrayOfByte4.length);
    return arrayOfByte5;
  }

  private static Key createDESKey(byte[] paramArrayOfByte, int paramInt)
  {
    byte[] arrayOfByte1 = new byte[7];
    System.arraycopy(paramArrayOfByte, paramInt, arrayOfByte1, 0, 7);
    byte[] arrayOfByte2 = new byte[8];
    arrayOfByte2[0] = arrayOfByte1[0];
    arrayOfByte2[1] = ((byte)(arrayOfByte1[0] << 7 | (0xFF & arrayOfByte1[1]) >>> 1));
    arrayOfByte2[2] = ((byte)(arrayOfByte1[1] << 6 | (0xFF & arrayOfByte1[2]) >>> 2));
    arrayOfByte2[3] = ((byte)(arrayOfByte1[2] << 5 | (0xFF & arrayOfByte1[3]) >>> 3));
    arrayOfByte2[4] = ((byte)(arrayOfByte1[3] << 4 | (0xFF & arrayOfByte1[4]) >>> 4));
    arrayOfByte2[5] = ((byte)(arrayOfByte1[4] << 3 | (0xFF & arrayOfByte1[5]) >>> 5));
    arrayOfByte2[6] = ((byte)(arrayOfByte1[5] << 2 | (0xFF & arrayOfByte1[6]) >>> 6));
    arrayOfByte2[7] = ((byte)(arrayOfByte1[6] << 1));
    oddParity(arrayOfByte2);
    return new SecretKeySpec(arrayOfByte2, "DES");
  }

  static byte[] hmacMD5(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws NTLMEngineException
  {
    HMACMD5 localHMACMD5 = new HMACMD5(paramArrayOfByte2);
    localHMACMD5.update(paramArrayOfByte1);
    return localHMACMD5.getOutput();
  }

  private static byte[] lmHash(String paramString)
    throws NTLMEngineException
  {
    try
    {
      byte[] arrayOfByte1 = paramString.toUpperCase(Locale.US).getBytes("US-ASCII");
      int i = Math.min(arrayOfByte1.length, 14);
      byte[] arrayOfByte2 = new byte[14];
      System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, i);
      Key localKey1 = createDESKey(arrayOfByte2, 0);
      Key localKey2 = createDESKey(arrayOfByte2, 7);
      byte[] arrayOfByte3 = "KGS!@#$%".getBytes("US-ASCII");
      Cipher localCipher = Cipher.getInstance("DES/ECB/NoPadding");
      localCipher.init(1, localKey1);
      byte[] arrayOfByte4 = localCipher.doFinal(arrayOfByte3);
      localCipher.init(1, localKey2);
      byte[] arrayOfByte5 = localCipher.doFinal(arrayOfByte3);
      byte[] arrayOfByte6 = new byte[16];
      System.arraycopy(arrayOfByte4, 0, arrayOfByte6, 0, 8);
      System.arraycopy(arrayOfByte5, 0, arrayOfByte6, 8, 8);
      return arrayOfByte6;
    }
    catch (Exception localException)
    {
      throw new NTLMEngineException(localException.getMessage(), localException);
    }
  }

  private static byte[] lmResponse(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws NTLMEngineException
  {
    try
    {
      byte[] arrayOfByte1 = new byte[21];
      System.arraycopy(paramArrayOfByte1, 0, arrayOfByte1, 0, 16);
      Key localKey1 = createDESKey(arrayOfByte1, 0);
      Key localKey2 = createDESKey(arrayOfByte1, 7);
      Key localKey3 = createDESKey(arrayOfByte1, 14);
      Cipher localCipher = Cipher.getInstance("DES/ECB/NoPadding");
      localCipher.init(1, localKey1);
      byte[] arrayOfByte2 = localCipher.doFinal(paramArrayOfByte2);
      localCipher.init(1, localKey2);
      byte[] arrayOfByte3 = localCipher.doFinal(paramArrayOfByte2);
      localCipher.init(1, localKey3);
      byte[] arrayOfByte4 = localCipher.doFinal(paramArrayOfByte2);
      byte[] arrayOfByte5 = new byte[24];
      System.arraycopy(arrayOfByte2, 0, arrayOfByte5, 0, 8);
      System.arraycopy(arrayOfByte3, 0, arrayOfByte5, 8, 8);
      System.arraycopy(arrayOfByte4, 0, arrayOfByte5, 16, 8);
      return arrayOfByte5;
    }
    catch (Exception localException)
    {
      throw new NTLMEngineException(localException.getMessage(), localException);
    }
  }

  private static byte[] lmv2Hash(String paramString1, String paramString2, byte[] paramArrayOfByte)
    throws NTLMEngineException
  {
    try
    {
      HMACMD5 localHMACMD5 = new HMACMD5(paramArrayOfByte);
      localHMACMD5.update(paramString2.toUpperCase(Locale.US).getBytes("UnicodeLittleUnmarked"));
      localHMACMD5.update(paramString1.toUpperCase(Locale.US).getBytes("UnicodeLittleUnmarked"));
      byte[] arrayOfByte = localHMACMD5.getOutput();
      return arrayOfByte;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new NTLMEngineException("Unicode not supported! " + localUnsupportedEncodingException.getMessage(), localUnsupportedEncodingException);
    }
  }

  private static byte[] lmv2Response(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
    throws NTLMEngineException
  {
    HMACMD5 localHMACMD5 = new HMACMD5(paramArrayOfByte1);
    localHMACMD5.update(paramArrayOfByte2);
    localHMACMD5.update(paramArrayOfByte3);
    byte[] arrayOfByte1 = localHMACMD5.getOutput();
    byte[] arrayOfByte2 = new byte[arrayOfByte1.length + paramArrayOfByte3.length];
    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, arrayOfByte1.length);
    System.arraycopy(paramArrayOfByte3, 0, arrayOfByte2, arrayOfByte1.length, paramArrayOfByte3.length);
    return arrayOfByte2;
  }

  private static byte[] makeRandomChallenge()
    throws NTLMEngineException
  {
    if (RND_GEN == null)
      throw new NTLMEngineException("Random generator not available");
    byte[] arrayOfByte = new byte[8];
    synchronized (RND_GEN)
    {
      RND_GEN.nextBytes(arrayOfByte);
      return arrayOfByte;
    }
  }

  private static byte[] makeSecondaryKey()
    throws NTLMEngineException
  {
    if (RND_GEN == null)
      throw new NTLMEngineException("Random generator not available");
    byte[] arrayOfByte = new byte[16];
    synchronized (RND_GEN)
    {
      RND_GEN.nextBytes(arrayOfByte);
      return arrayOfByte;
    }
  }

  static byte[] ntlm2SessionResponse(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
    throws NTLMEngineException
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramArrayOfByte2);
      localMessageDigest.update(paramArrayOfByte3);
      byte[] arrayOfByte1 = localMessageDigest.digest();
      byte[] arrayOfByte2 = new byte[8];
      System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, 8);
      byte[] arrayOfByte3 = lmResponse(paramArrayOfByte1, arrayOfByte2);
      return arrayOfByte3;
    }
    catch (Exception localException)
    {
      if ((localException instanceof NTLMEngineException))
        throw ((NTLMEngineException)localException);
      throw new NTLMEngineException(localException.getMessage(), localException);
    }
  }

  private static byte[] ntlmHash(String paramString)
    throws NTLMEngineException
  {
    try
    {
      byte[] arrayOfByte1 = paramString.getBytes("UnicodeLittleUnmarked");
      MD4 localMD4 = new MD4();
      localMD4.update(arrayOfByte1);
      byte[] arrayOfByte2 = localMD4.getOutput();
      return arrayOfByte2;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new NTLMEngineException("Unicode not supported: " + localUnsupportedEncodingException.getMessage(), localUnsupportedEncodingException);
    }
  }

  private static byte[] ntlmv2Hash(String paramString1, String paramString2, byte[] paramArrayOfByte)
    throws NTLMEngineException
  {
    try
    {
      HMACMD5 localHMACMD5 = new HMACMD5(paramArrayOfByte);
      localHMACMD5.update(paramString2.toUpperCase(Locale.US).getBytes("UnicodeLittleUnmarked"));
      localHMACMD5.update(paramString1.getBytes("UnicodeLittleUnmarked"));
      byte[] arrayOfByte = localHMACMD5.getOutput();
      return arrayOfByte;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new NTLMEngineException("Unicode not supported! " + localUnsupportedEncodingException.getMessage(), localUnsupportedEncodingException);
    }
  }

  private static void oddParity(byte[] paramArrayOfByte)
  {
    int i = 0;
    if (i >= paramArrayOfByte.length)
      return;
    int j = paramArrayOfByte[i];
    int k;
    if ((0x1 & (j >>> 7 ^ j >>> 6 ^ j >>> 5 ^ j >>> 4 ^ j >>> 3 ^ j >>> 2 ^ j >>> 1)) == 0)
    {
      k = 1;
      label49: if (k == 0)
        break label73;
      paramArrayOfByte[i] = ((byte)(0x1 | paramArrayOfByte[i]));
    }
    while (true)
    {
      i++;
      break;
      k = 0;
      break label49;
      label73: paramArrayOfByte[i] = ((byte)(0xFFFFFFFE & paramArrayOfByte[i]));
    }
  }

  private static byte[] readSecurityBuffer(byte[] paramArrayOfByte, int paramInt)
    throws NTLMEngineException
  {
    int i = readUShort(paramArrayOfByte, paramInt);
    int j = readULong(paramArrayOfByte, paramInt + 4);
    if (paramArrayOfByte.length < j + i)
      throw new NTLMEngineException("NTLM authentication - buffer too small for data item");
    byte[] arrayOfByte = new byte[i];
    System.arraycopy(paramArrayOfByte, j, arrayOfByte, 0, i);
    return arrayOfByte;
  }

  private static int readULong(byte[] paramArrayOfByte, int paramInt)
    throws NTLMEngineException
  {
    if (paramArrayOfByte.length < paramInt + 4)
      throw new NTLMEngineException("NTLM authentication - buffer too small for DWORD");
    return 0xFF & paramArrayOfByte[paramInt] | (0xFF & paramArrayOfByte[(paramInt + 1)]) << 8 | (0xFF & paramArrayOfByte[(paramInt + 2)]) << 16 | (0xFF & paramArrayOfByte[(paramInt + 3)]) << 24;
  }

  private static int readUShort(byte[] paramArrayOfByte, int paramInt)
    throws NTLMEngineException
  {
    if (paramArrayOfByte.length < paramInt + 2)
      throw new NTLMEngineException("NTLM authentication - buffer too small for WORD");
    return 0xFF & paramArrayOfByte[paramInt] | (0xFF & paramArrayOfByte[(paramInt + 1)]) << 8;
  }

  static int rotintlft(int paramInt1, int paramInt2)
  {
    return paramInt1 << paramInt2 | paramInt1 >>> 32 - paramInt2;
  }

  private static String stripDotSuffix(String paramString)
  {
    int i = paramString.indexOf(".");
    if (i != -1)
      paramString = paramString.substring(0, i);
    return paramString;
  }

  static void writeULong(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    paramArrayOfByte[paramInt2] = ((byte)(paramInt1 & 0xFF));
    paramArrayOfByte[(paramInt2 + 1)] = ((byte)(0xFF & paramInt1 >> 8));
    paramArrayOfByte[(paramInt2 + 2)] = ((byte)(0xFF & paramInt1 >> 16));
    paramArrayOfByte[(paramInt2 + 3)] = ((byte)(0xFF & paramInt1 >> 24));
  }

  public String generateType1Msg(String paramString1, String paramString2)
    throws NTLMEngineException
  {
    return getType1Message(paramString2, paramString1);
  }

  public String generateType3Msg(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
    throws NTLMEngineException
  {
    Type2Message localType2Message = new Type2Message(paramString5);
    return getType3Message(paramString1, paramString2, paramString4, paramString3, localType2Message.getChallenge(), localType2Message.getFlags(), localType2Message.getTarget(), localType2Message.getTargetInfo());
  }

  String getCredentialCharset()
  {
    return this.credentialCharset;
  }

  final String getResponseFor(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
    throws NTLMEngineException
  {
    if ((paramString1 == null) || (paramString1.trim().equals("")))
      return getType1Message(paramString4, paramString5);
    Type2Message localType2Message = new Type2Message(paramString1);
    return getType3Message(paramString2, paramString3, paramString4, paramString5, localType2Message.getChallenge(), localType2Message.getFlags(), localType2Message.getTarget(), localType2Message.getTargetInfo());
  }

  String getType1Message(String paramString1, String paramString2)
    throws NTLMEngineException
  {
    return new Type1Message(paramString2, paramString1).getResponse();
  }

  String getType3Message(String paramString1, String paramString2, String paramString3, String paramString4, byte[] paramArrayOfByte1, int paramInt, String paramString5, byte[] paramArrayOfByte2)
    throws NTLMEngineException
  {
    return new Type3Message(paramString4, paramString3, paramString1, paramString2, paramArrayOfByte1, paramInt, paramString5, paramArrayOfByte2).getResponse();
  }

  void setCredentialCharset(String paramString)
  {
    this.credentialCharset = paramString;
  }

  protected static class CipherGen
  {
    protected final byte[] challenge;
    protected byte[] clientChallenge;
    protected byte[] clientChallenge2;
    protected final String domain;
    protected byte[] lanManagerSessionKey = null;
    protected byte[] lm2SessionResponse = null;
    protected byte[] lmHash = null;
    protected byte[] lmResponse = null;
    protected byte[] lmUserSessionKey = null;
    protected byte[] lmv2Hash = null;
    protected byte[] lmv2Response = null;
    protected byte[] ntlm2SessionResponse = null;
    protected byte[] ntlm2SessionResponseUserSessionKey = null;
    protected byte[] ntlmHash = null;
    protected byte[] ntlmResponse = null;
    protected byte[] ntlmUserSessionKey = null;
    protected byte[] ntlmv2Blob = null;
    protected byte[] ntlmv2Hash = null;
    protected byte[] ntlmv2Response = null;
    protected byte[] ntlmv2UserSessionKey = null;
    protected final String password;
    protected byte[] secondaryKey;
    protected final String target;
    protected final byte[] targetInformation;
    protected byte[] timestamp;
    protected final String user;

    public CipherGen(String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte1, String paramString4, byte[] paramArrayOfByte2)
    {
      this(paramString1, paramString2, paramString3, paramArrayOfByte1, paramString4, paramArrayOfByte2, null, null, null, null);
    }

    public CipherGen(String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte1, String paramString4, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, byte[] paramArrayOfByte5, byte[] paramArrayOfByte6)
    {
      this.domain = paramString1;
      this.target = paramString4;
      this.user = paramString2;
      this.password = paramString3;
      this.challenge = paramArrayOfByte1;
      this.targetInformation = paramArrayOfByte2;
      this.clientChallenge = paramArrayOfByte3;
      this.clientChallenge2 = paramArrayOfByte4;
      this.secondaryKey = paramArrayOfByte5;
      this.timestamp = paramArrayOfByte6;
    }

    public byte[] getClientChallenge()
      throws NTLMEngineException
    {
      if (this.clientChallenge == null)
        this.clientChallenge = NTLMEngineImpl.access$0();
      return this.clientChallenge;
    }

    public byte[] getClientChallenge2()
      throws NTLMEngineException
    {
      if (this.clientChallenge2 == null)
        this.clientChallenge2 = NTLMEngineImpl.access$0();
      return this.clientChallenge2;
    }

    public byte[] getLM2SessionResponse()
      throws NTLMEngineException
    {
      if (this.lm2SessionResponse == null)
      {
        byte[] arrayOfByte = getClientChallenge();
        this.lm2SessionResponse = new byte[24];
        System.arraycopy(arrayOfByte, 0, this.lm2SessionResponse, 0, arrayOfByte.length);
        Arrays.fill(this.lm2SessionResponse, arrayOfByte.length, this.lm2SessionResponse.length, (byte)0);
      }
      return this.lm2SessionResponse;
    }

    public byte[] getLMHash()
      throws NTLMEngineException
    {
      if (this.lmHash == null)
        this.lmHash = NTLMEngineImpl.lmHash(this.password);
      return this.lmHash;
    }

    public byte[] getLMResponse()
      throws NTLMEngineException
    {
      if (this.lmResponse == null)
        this.lmResponse = NTLMEngineImpl.lmResponse(getLMHash(), this.challenge);
      return this.lmResponse;
    }

    public byte[] getLMUserSessionKey()
      throws NTLMEngineException
    {
      if (this.lmUserSessionKey == null)
      {
        byte[] arrayOfByte = getLMHash();
        this.lmUserSessionKey = new byte[16];
        System.arraycopy(arrayOfByte, 0, this.lmUserSessionKey, 0, 8);
        Arrays.fill(this.lmUserSessionKey, 8, 16, (byte)0);
      }
      return this.lmUserSessionKey;
    }

    public byte[] getLMv2Hash()
      throws NTLMEngineException
    {
      if (this.lmv2Hash == null)
        this.lmv2Hash = NTLMEngineImpl.lmv2Hash(this.domain, this.user, getNTLMHash());
      return this.lmv2Hash;
    }

    public byte[] getLMv2Response()
      throws NTLMEngineException
    {
      if (this.lmv2Response == null)
        this.lmv2Response = NTLMEngineImpl.lmv2Response(getLMv2Hash(), this.challenge, getClientChallenge());
      return this.lmv2Response;
    }

    public byte[] getLanManagerSessionKey()
      throws NTLMEngineException
    {
      byte[] arrayOfByte1;
      byte[] arrayOfByte2;
      if (this.lanManagerSessionKey == null)
      {
        arrayOfByte1 = getLMHash();
        arrayOfByte2 = getLMResponse();
      }
      try
      {
        byte[] arrayOfByte3 = new byte[14];
        System.arraycopy(arrayOfByte1, 0, arrayOfByte3, 0, 8);
        Arrays.fill(arrayOfByte3, 8, arrayOfByte3.length, (byte)-67);
        Key localKey1 = NTLMEngineImpl.createDESKey(arrayOfByte3, 0);
        Key localKey2 = NTLMEngineImpl.createDESKey(arrayOfByte3, 7);
        byte[] arrayOfByte4 = new byte[8];
        System.arraycopy(arrayOfByte2, 0, arrayOfByte4, 0, arrayOfByte4.length);
        Cipher localCipher1 = Cipher.getInstance("DES/ECB/NoPadding");
        localCipher1.init(1, localKey1);
        byte[] arrayOfByte5 = localCipher1.doFinal(arrayOfByte4);
        Cipher localCipher2 = Cipher.getInstance("DES/ECB/NoPadding");
        localCipher2.init(1, localKey2);
        byte[] arrayOfByte6 = localCipher2.doFinal(arrayOfByte4);
        this.lanManagerSessionKey = new byte[16];
        System.arraycopy(arrayOfByte5, 0, this.lanManagerSessionKey, 0, arrayOfByte5.length);
        System.arraycopy(arrayOfByte6, 0, this.lanManagerSessionKey, arrayOfByte5.length, arrayOfByte6.length);
        return this.lanManagerSessionKey;
      }
      catch (Exception localException)
      {
        throw new NTLMEngineException(localException.getMessage(), localException);
      }
    }

    public byte[] getNTLM2SessionResponse()
      throws NTLMEngineException
    {
      if (this.ntlm2SessionResponse == null)
        this.ntlm2SessionResponse = NTLMEngineImpl.ntlm2SessionResponse(getNTLMHash(), this.challenge, getClientChallenge());
      return this.ntlm2SessionResponse;
    }

    public byte[] getNTLM2SessionResponseUserSessionKey()
      throws NTLMEngineException
    {
      if (this.ntlm2SessionResponseUserSessionKey == null)
      {
        byte[] arrayOfByte1 = getNTLMUserSessionKey();
        byte[] arrayOfByte2 = getLM2SessionResponse();
        byte[] arrayOfByte3 = new byte[this.challenge.length + arrayOfByte2.length];
        System.arraycopy(this.challenge, 0, arrayOfByte3, 0, this.challenge.length);
        System.arraycopy(arrayOfByte2, 0, arrayOfByte3, this.challenge.length, arrayOfByte2.length);
        this.ntlm2SessionResponseUserSessionKey = NTLMEngineImpl.hmacMD5(arrayOfByte3, arrayOfByte1);
      }
      return this.ntlm2SessionResponseUserSessionKey;
    }

    public byte[] getNTLMHash()
      throws NTLMEngineException
    {
      if (this.ntlmHash == null)
        this.ntlmHash = NTLMEngineImpl.ntlmHash(this.password);
      return this.ntlmHash;
    }

    public byte[] getNTLMResponse()
      throws NTLMEngineException
    {
      if (this.ntlmResponse == null)
        this.ntlmResponse = NTLMEngineImpl.lmResponse(getNTLMHash(), this.challenge);
      return this.ntlmResponse;
    }

    public byte[] getNTLMUserSessionKey()
      throws NTLMEngineException
    {
      if (this.ntlmUserSessionKey == null)
      {
        byte[] arrayOfByte = getNTLMHash();
        NTLMEngineImpl.MD4 localMD4 = new NTLMEngineImpl.MD4();
        localMD4.update(arrayOfByte);
        this.ntlmUserSessionKey = localMD4.getOutput();
      }
      return this.ntlmUserSessionKey;
    }

    public byte[] getNTLMv2Blob()
      throws NTLMEngineException
    {
      if (this.ntlmv2Blob == null)
        this.ntlmv2Blob = NTLMEngineImpl.createBlob(getClientChallenge2(), this.targetInformation, getTimestamp());
      return this.ntlmv2Blob;
    }

    public byte[] getNTLMv2Hash()
      throws NTLMEngineException
    {
      if (this.ntlmv2Hash == null)
        this.ntlmv2Hash = NTLMEngineImpl.ntlmv2Hash(this.domain, this.user, getNTLMHash());
      return this.ntlmv2Hash;
    }

    public byte[] getNTLMv2Response()
      throws NTLMEngineException
    {
      if (this.ntlmv2Response == null)
        this.ntlmv2Response = NTLMEngineImpl.lmv2Response(getNTLMv2Hash(), this.challenge, getNTLMv2Blob());
      return this.ntlmv2Response;
    }

    public byte[] getNTLMv2UserSessionKey()
      throws NTLMEngineException
    {
      if (this.ntlmv2UserSessionKey == null)
      {
        byte[] arrayOfByte1 = getNTLMv2Hash();
        byte[] arrayOfByte2 = new byte[16];
        System.arraycopy(getNTLMv2Response(), 0, arrayOfByte2, 0, 16);
        this.ntlmv2UserSessionKey = NTLMEngineImpl.hmacMD5(arrayOfByte2, arrayOfByte1);
      }
      return this.ntlmv2UserSessionKey;
    }

    public byte[] getSecondaryKey()
      throws NTLMEngineException
    {
      if (this.secondaryKey == null)
        this.secondaryKey = NTLMEngineImpl.access$1();
      return this.secondaryKey;
    }

    public byte[] getTimestamp()
    {
      long l;
      if (this.timestamp == null)
      {
        l = 10000L * (11644473600000L + System.currentTimeMillis());
        this.timestamp = new byte[8];
      }
      for (int i = 0; ; i++)
      {
        if (i >= 8)
          return this.timestamp;
        this.timestamp[i] = ((byte)(int)l);
        l >>>= 8;
      }
    }
  }

  static class HMACMD5
  {
    protected byte[] ipad;
    protected MessageDigest md5;
    protected byte[] opad;

    HMACMD5(byte[] paramArrayOfByte)
      throws NTLMEngineException
    {
      while (true)
      {
        int j;
        try
        {
          this.md5 = MessageDigest.getInstance("MD5");
          this.ipad = new byte[64];
          this.opad = new byte[64];
          int i = paramArrayOfByte.length;
          if (i > 64)
          {
            this.md5.update(paramArrayOfByte);
            paramArrayOfByte = this.md5.digest();
            i = paramArrayOfByte.length;
          }
          j = 0;
          if (j >= i)
          {
            if (j < 64)
              break label157;
            this.md5.reset();
            this.md5.update(this.ipad);
            return;
          }
        }
        catch (Exception localException)
        {
          throw new NTLMEngineException("Error getting md5 message digest implementation: " + localException.getMessage(), localException);
        }
        this.ipad[j] = ((byte)(0x36 ^ paramArrayOfByte[j]));
        this.opad[j] = ((byte)(0x5C ^ paramArrayOfByte[j]));
        j++;
        continue;
        label157: this.ipad[j] = 54;
        this.opad[j] = 92;
        j++;
      }
    }

    byte[] getOutput()
    {
      byte[] arrayOfByte = this.md5.digest();
      this.md5.update(this.opad);
      return this.md5.digest(arrayOfByte);
    }

    void update(byte[] paramArrayOfByte)
    {
      this.md5.update(paramArrayOfByte);
    }

    void update(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      this.md5.update(paramArrayOfByte, paramInt1, paramInt2);
    }
  }

  static class MD4
  {
    protected int A = 1732584193;
    protected int B = -271733879;
    protected int C = -1732584194;
    protected int D = 271733878;
    protected long count = 0L;
    protected byte[] dataBuffer = new byte[64];

    byte[] getOutput()
    {
      int i = (int)(0x3F & this.count);
      int j;
      byte[] arrayOfByte1;
      if (i < 56)
      {
        j = 56 - i;
        arrayOfByte1 = new byte[j + 8];
        arrayOfByte1[0] = -128;
      }
      for (int k = 0; ; k++)
      {
        if (k >= 8)
        {
          update(arrayOfByte1);
          byte[] arrayOfByte2 = new byte[16];
          NTLMEngineImpl.writeULong(arrayOfByte2, this.A, 0);
          NTLMEngineImpl.writeULong(arrayOfByte2, this.B, 4);
          NTLMEngineImpl.writeULong(arrayOfByte2, this.C, 8);
          NTLMEngineImpl.writeULong(arrayOfByte2, this.D, 12);
          return arrayOfByte2;
          j = 120 - i;
          break;
        }
        arrayOfByte1[(j + k)] = ((byte)(int)(8L * this.count >>> k * 8));
      }
    }

    protected void processBuffer()
    {
      int[] arrayOfInt = new int[16];
      for (int i = 0; ; i++)
      {
        if (i >= 16)
        {
          int j = this.A;
          int k = this.B;
          int m = this.C;
          int n = this.D;
          round1(arrayOfInt);
          round2(arrayOfInt);
          round3(arrayOfInt);
          this.A = (j + this.A);
          this.B = (k + this.B);
          this.C = (m + this.C);
          this.D = (n + this.D);
          return;
        }
        arrayOfInt[i] = ((0xFF & this.dataBuffer[(i * 4)]) + ((0xFF & this.dataBuffer[(1 + i * 4)]) << 8) + ((0xFF & this.dataBuffer[(2 + i * 4)]) << 16) + ((0xFF & this.dataBuffer[(3 + i * 4)]) << 24));
      }
    }

    protected void round1(int[] paramArrayOfInt)
    {
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.F(this.B, this.C, this.D) + paramArrayOfInt[0], 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.F(this.A, this.B, this.C) + paramArrayOfInt[1], 7);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.F(this.D, this.A, this.B) + paramArrayOfInt[2], 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.F(this.C, this.D, this.A) + paramArrayOfInt[3], 19);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.F(this.B, this.C, this.D) + paramArrayOfInt[4], 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.F(this.A, this.B, this.C) + paramArrayOfInt[5], 7);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.F(this.D, this.A, this.B) + paramArrayOfInt[6], 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.F(this.C, this.D, this.A) + paramArrayOfInt[7], 19);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.F(this.B, this.C, this.D) + paramArrayOfInt[8], 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.F(this.A, this.B, this.C) + paramArrayOfInt[9], 7);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.F(this.D, this.A, this.B) + paramArrayOfInt[10], 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.F(this.C, this.D, this.A) + paramArrayOfInt[11], 19);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.F(this.B, this.C, this.D) + paramArrayOfInt[12], 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.F(this.A, this.B, this.C) + paramArrayOfInt[13], 7);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.F(this.D, this.A, this.B) + paramArrayOfInt[14], 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.F(this.C, this.D, this.A) + paramArrayOfInt[15], 19);
    }

    protected void round2(int[] paramArrayOfInt)
    {
      this.A = NTLMEngineImpl.rotintlft(1518500249 + (this.A + NTLMEngineImpl.G(this.B, this.C, this.D) + paramArrayOfInt[0]), 3);
      this.D = NTLMEngineImpl.rotintlft(1518500249 + (this.D + NTLMEngineImpl.G(this.A, this.B, this.C) + paramArrayOfInt[4]), 5);
      this.C = NTLMEngineImpl.rotintlft(1518500249 + (this.C + NTLMEngineImpl.G(this.D, this.A, this.B) + paramArrayOfInt[8]), 9);
      this.B = NTLMEngineImpl.rotintlft(1518500249 + (this.B + NTLMEngineImpl.G(this.C, this.D, this.A) + paramArrayOfInt[12]), 13);
      this.A = NTLMEngineImpl.rotintlft(1518500249 + (this.A + NTLMEngineImpl.G(this.B, this.C, this.D) + paramArrayOfInt[1]), 3);
      this.D = NTLMEngineImpl.rotintlft(1518500249 + (this.D + NTLMEngineImpl.G(this.A, this.B, this.C) + paramArrayOfInt[5]), 5);
      this.C = NTLMEngineImpl.rotintlft(1518500249 + (this.C + NTLMEngineImpl.G(this.D, this.A, this.B) + paramArrayOfInt[9]), 9);
      this.B = NTLMEngineImpl.rotintlft(1518500249 + (this.B + NTLMEngineImpl.G(this.C, this.D, this.A) + paramArrayOfInt[13]), 13);
      this.A = NTLMEngineImpl.rotintlft(1518500249 + (this.A + NTLMEngineImpl.G(this.B, this.C, this.D) + paramArrayOfInt[2]), 3);
      this.D = NTLMEngineImpl.rotintlft(1518500249 + (this.D + NTLMEngineImpl.G(this.A, this.B, this.C) + paramArrayOfInt[6]), 5);
      this.C = NTLMEngineImpl.rotintlft(1518500249 + (this.C + NTLMEngineImpl.G(this.D, this.A, this.B) + paramArrayOfInt[10]), 9);
      this.B = NTLMEngineImpl.rotintlft(1518500249 + (this.B + NTLMEngineImpl.G(this.C, this.D, this.A) + paramArrayOfInt[14]), 13);
      this.A = NTLMEngineImpl.rotintlft(1518500249 + (this.A + NTLMEngineImpl.G(this.B, this.C, this.D) + paramArrayOfInt[3]), 3);
      this.D = NTLMEngineImpl.rotintlft(1518500249 + (this.D + NTLMEngineImpl.G(this.A, this.B, this.C) + paramArrayOfInt[7]), 5);
      this.C = NTLMEngineImpl.rotintlft(1518500249 + (this.C + NTLMEngineImpl.G(this.D, this.A, this.B) + paramArrayOfInt[11]), 9);
      this.B = NTLMEngineImpl.rotintlft(1518500249 + (this.B + NTLMEngineImpl.G(this.C, this.D, this.A) + paramArrayOfInt[15]), 13);
    }

    protected void round3(int[] paramArrayOfInt)
    {
      this.A = NTLMEngineImpl.rotintlft(1859775393 + (this.A + NTLMEngineImpl.H(this.B, this.C, this.D) + paramArrayOfInt[0]), 3);
      this.D = NTLMEngineImpl.rotintlft(1859775393 + (this.D + NTLMEngineImpl.H(this.A, this.B, this.C) + paramArrayOfInt[8]), 9);
      this.C = NTLMEngineImpl.rotintlft(1859775393 + (this.C + NTLMEngineImpl.H(this.D, this.A, this.B) + paramArrayOfInt[4]), 11);
      this.B = NTLMEngineImpl.rotintlft(1859775393 + (this.B + NTLMEngineImpl.H(this.C, this.D, this.A) + paramArrayOfInt[12]), 15);
      this.A = NTLMEngineImpl.rotintlft(1859775393 + (this.A + NTLMEngineImpl.H(this.B, this.C, this.D) + paramArrayOfInt[2]), 3);
      this.D = NTLMEngineImpl.rotintlft(1859775393 + (this.D + NTLMEngineImpl.H(this.A, this.B, this.C) + paramArrayOfInt[10]), 9);
      this.C = NTLMEngineImpl.rotintlft(1859775393 + (this.C + NTLMEngineImpl.H(this.D, this.A, this.B) + paramArrayOfInt[6]), 11);
      this.B = NTLMEngineImpl.rotintlft(1859775393 + (this.B + NTLMEngineImpl.H(this.C, this.D, this.A) + paramArrayOfInt[14]), 15);
      this.A = NTLMEngineImpl.rotintlft(1859775393 + (this.A + NTLMEngineImpl.H(this.B, this.C, this.D) + paramArrayOfInt[1]), 3);
      this.D = NTLMEngineImpl.rotintlft(1859775393 + (this.D + NTLMEngineImpl.H(this.A, this.B, this.C) + paramArrayOfInt[9]), 9);
      this.C = NTLMEngineImpl.rotintlft(1859775393 + (this.C + NTLMEngineImpl.H(this.D, this.A, this.B) + paramArrayOfInt[5]), 11);
      this.B = NTLMEngineImpl.rotintlft(1859775393 + (this.B + NTLMEngineImpl.H(this.C, this.D, this.A) + paramArrayOfInt[13]), 15);
      this.A = NTLMEngineImpl.rotintlft(1859775393 + (this.A + NTLMEngineImpl.H(this.B, this.C, this.D) + paramArrayOfInt[3]), 3);
      this.D = NTLMEngineImpl.rotintlft(1859775393 + (this.D + NTLMEngineImpl.H(this.A, this.B, this.C) + paramArrayOfInt[11]), 9);
      this.C = NTLMEngineImpl.rotintlft(1859775393 + (this.C + NTLMEngineImpl.H(this.D, this.A, this.B) + paramArrayOfInt[7]), 11);
      this.B = NTLMEngineImpl.rotintlft(1859775393 + (this.B + NTLMEngineImpl.H(this.C, this.D, this.A) + paramArrayOfInt[15]), 15);
    }

    void update(byte[] paramArrayOfByte)
    {
      int i = (int)(0x3F & this.count);
      int j = 0;
      while (true)
      {
        if (i + (paramArrayOfByte.length - j) < this.dataBuffer.length)
        {
          if (j < paramArrayOfByte.length)
          {
            int m = paramArrayOfByte.length - j;
            System.arraycopy(paramArrayOfByte, j, this.dataBuffer, i, m);
            this.count += m;
            (i + m);
          }
          return;
        }
        int k = this.dataBuffer.length - i;
        System.arraycopy(paramArrayOfByte, j, this.dataBuffer, i, k);
        this.count += k;
        j += k;
        processBuffer();
        i = 0;
      }
    }
  }

  static class NTLMMessage
  {
    private int currentOutputPosition = 0;
    private byte[] messageContents = null;

    NTLMMessage()
    {
    }

    NTLMMessage(String paramString, int paramInt)
      throws NTLMEngineException
    {
      this.messageContents = Base64.decodeBase64(EncodingUtils.getBytes(paramString, "ASCII"));
      if (this.messageContents.length < NTLMEngineImpl.SIGNATURE.length)
        throw new NTLMEngineException("NTLM message decoding error - packet too short");
      for (int i = 0; ; i++)
      {
        if (i >= NTLMEngineImpl.SIGNATURE.length)
        {
          int j = readULong(NTLMEngineImpl.SIGNATURE.length);
          if (j == paramInt)
            break;
          throw new NTLMEngineException("NTLM type " + Integer.toString(paramInt) + " message expected - instead got type " + Integer.toString(j));
        }
        if (this.messageContents[i] != NTLMEngineImpl.SIGNATURE[i])
          throw new NTLMEngineException("NTLM message expected - instead got unrecognized bytes");
      }
      this.currentOutputPosition = this.messageContents.length;
    }

    protected void addByte(byte paramByte)
    {
      this.messageContents[this.currentOutputPosition] = paramByte;
      this.currentOutputPosition = (1 + this.currentOutputPosition);
    }

    protected void addBytes(byte[] paramArrayOfByte)
    {
      int i = paramArrayOfByte.length;
      for (int j = 0; ; j++)
      {
        if (j >= i)
          return;
        int k = paramArrayOfByte[j];
        this.messageContents[this.currentOutputPosition] = k;
        this.currentOutputPosition = (1 + this.currentOutputPosition);
      }
    }

    protected void addULong(int paramInt)
    {
      addByte((byte)(paramInt & 0xFF));
      addByte((byte)(0xFF & paramInt >> 8));
      addByte((byte)(0xFF & paramInt >> 16));
      addByte((byte)(0xFF & paramInt >> 24));
    }

    protected void addUShort(int paramInt)
    {
      addByte((byte)(paramInt & 0xFF));
      addByte((byte)(0xFF & paramInt >> 8));
    }

    protected int getMessageLength()
    {
      return this.currentOutputPosition;
    }

    protected int getPreambleLength()
    {
      return 4 + NTLMEngineImpl.SIGNATURE.length;
    }

    String getResponse()
    {
      byte[] arrayOfByte2;
      int i;
      if (this.messageContents.length > this.currentOutputPosition)
      {
        arrayOfByte2 = new byte[this.currentOutputPosition];
        i = 0;
        if (i < this.currentOutputPosition);
      }
      for (byte[] arrayOfByte1 = arrayOfByte2; ; arrayOfByte1 = this.messageContents)
      {
        return EncodingUtils.getAsciiString(Base64.encodeBase64(arrayOfByte1));
        arrayOfByte2[i] = this.messageContents[i];
        i++;
        break;
      }
    }

    protected void prepareResponse(int paramInt1, int paramInt2)
    {
      this.messageContents = new byte[paramInt1];
      this.currentOutputPosition = 0;
      addBytes(NTLMEngineImpl.SIGNATURE);
      addULong(paramInt2);
    }

    protected byte readByte(int paramInt)
      throws NTLMEngineException
    {
      if (this.messageContents.length < paramInt + 1)
        throw new NTLMEngineException("NTLM: Message too short");
      return this.messageContents[paramInt];
    }

    protected void readBytes(byte[] paramArrayOfByte, int paramInt)
      throws NTLMEngineException
    {
      if (this.messageContents.length < paramInt + paramArrayOfByte.length)
        throw new NTLMEngineException("NTLM: Message too short");
      System.arraycopy(this.messageContents, paramInt, paramArrayOfByte, 0, paramArrayOfByte.length);
    }

    protected byte[] readSecurityBuffer(int paramInt)
      throws NTLMEngineException
    {
      return NTLMEngineImpl.readSecurityBuffer(this.messageContents, paramInt);
    }

    protected int readULong(int paramInt)
      throws NTLMEngineException
    {
      return NTLMEngineImpl.readULong(this.messageContents, paramInt);
    }

    protected int readUShort(int paramInt)
      throws NTLMEngineException
    {
      return NTLMEngineImpl.readUShort(this.messageContents, paramInt);
    }
  }

  static class Type1Message extends NTLMEngineImpl.NTLMMessage
  {
    protected byte[] domainBytes;
    protected byte[] hostBytes;

    Type1Message(String paramString1, String paramString2)
      throws NTLMEngineException
    {
      try
      {
        String str1 = NTLMEngineImpl.convertHost(paramString2);
        String str2 = NTLMEngineImpl.convertDomain(paramString1);
        this.hostBytes = str1.getBytes("ASCII");
        this.domainBytes = str2.toUpperCase(Locale.US).getBytes("ASCII");
        return;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        throw new NTLMEngineException("Unicode unsupported: " + localUnsupportedEncodingException.getMessage(), localUnsupportedEncodingException);
      }
    }

    String getResponse()
    {
      prepareResponse(40, 1);
      addULong(-1576500735);
      addUShort(0);
      addUShort(0);
      addULong(40);
      addUShort(0);
      addUShort(0);
      addULong(40);
      addUShort(261);
      addULong(2600);
      addUShort(3840);
      return super.getResponse();
    }
  }

  static class Type2Message extends NTLMEngineImpl.NTLMMessage
  {
    protected byte[] challenge = new byte[8];
    protected int flags;
    protected String target;
    protected byte[] targetInfo;

    Type2Message(String paramString)
      throws NTLMEngineException
    {
      super(2);
      readBytes(this.challenge, 24);
      this.flags = readULong(20);
      if ((0x1 & this.flags) == 0)
        throw new NTLMEngineException("NTLM type 2 message has flags that make no sense: " + Integer.toString(this.flags));
      this.target = null;
      byte[] arrayOfByte2;
      if (getMessageLength() >= 20)
      {
        arrayOfByte2 = readSecurityBuffer(12);
        if (arrayOfByte2.length == 0);
      }
      try
      {
        this.target = new String(arrayOfByte2, "UnicodeLittleUnmarked");
        this.targetInfo = null;
        if (getMessageLength() >= 48)
        {
          byte[] arrayOfByte1 = readSecurityBuffer(40);
          if (arrayOfByte1.length != 0)
            this.targetInfo = arrayOfByte1;
        }
        return;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        throw new NTLMEngineException(localUnsupportedEncodingException.getMessage(), localUnsupportedEncodingException);
      }
    }

    byte[] getChallenge()
    {
      return this.challenge;
    }

    int getFlags()
    {
      return this.flags;
    }

    String getTarget()
    {
      return this.target;
    }

    byte[] getTargetInfo()
    {
      return this.targetInfo;
    }
  }

  static class Type3Message extends NTLMEngineImpl.NTLMMessage
  {
    protected byte[] domainBytes;
    protected byte[] hostBytes;
    protected byte[] lmResp;
    protected byte[] ntResp;
    protected byte[] sessionKey;
    protected int type2Flags;
    protected byte[] userBytes;

    Type3Message(String paramString1, String paramString2, String paramString3, String paramString4, byte[] paramArrayOfByte1, int paramInt, String paramString5, byte[] paramArrayOfByte2)
      throws NTLMEngineException
    {
      this.type2Flags = paramInt;
      String str1 = NTLMEngineImpl.convertHost(paramString2);
      String str2 = NTLMEngineImpl.convertDomain(paramString1);
      NTLMEngineImpl.CipherGen localCipherGen = new NTLMEngineImpl.CipherGen(str2, paramString3, paramString4, paramArrayOfByte1, paramString5, paramArrayOfByte2);
      if (((0x800000 & paramInt) != 0) && (paramArrayOfByte2 != null) && (paramString5 != null));
      try
      {
        this.ntResp = localCipherGen.getNTLMv2Response();
        this.lmResp = localCipherGen.getLMv2Response();
        if ((paramInt & 0x80) != 0)
        {
          byte[] arrayOfByte2 = localCipherGen.getLanManagerSessionKey();
          localObject = arrayOfByte2;
          if ((paramInt & 0x10) == 0)
            break label339;
          if ((0x40000000 & paramInt) == 0)
            break label330;
          this.sessionKey = NTLMEngineImpl.RC4(localCipherGen.getSecondaryKey(), (byte[])localObject);
        }
      }
      catch (NTLMEngineException localNTLMEngineException)
      {
        try
        {
          while (true)
          {
            this.domainBytes = str2.toUpperCase(Locale.US).getBytes("UnicodeLittleUnmarked");
            this.hostBytes = str1.getBytes("UnicodeLittleUnmarked");
            this.userBytes = paramString3.getBytes("UnicodeLittleUnmarked");
            return;
            Object localObject = localCipherGen.getNTLMv2UserSessionKey();
            continue;
            if ((0x80000 & paramInt) != 0)
            {
              this.ntResp = localCipherGen.getNTLM2SessionResponse();
              this.lmResp = localCipherGen.getLM2SessionResponse();
              if ((paramInt & 0x80) != 0)
                localObject = localCipherGen.getLanManagerSessionKey();
              else
                localObject = localCipherGen.getNTLM2SessionResponseUserSessionKey();
            }
            else
            {
              this.ntResp = localCipherGen.getNTLMResponse();
              this.lmResp = localCipherGen.getLMResponse();
              if ((paramInt & 0x80) != 0)
              {
                localObject = localCipherGen.getLanManagerSessionKey();
              }
              else
              {
                byte[] arrayOfByte1 = localCipherGen.getNTLMUserSessionKey();
                localObject = arrayOfByte1;
                continue;
                localNTLMEngineException = localNTLMEngineException;
                this.ntResp = new byte[0];
                this.lmResp = localCipherGen.getLMResponse();
                if ((paramInt & 0x80) != 0)
                {
                  localObject = localCipherGen.getLanManagerSessionKey();
                }
                else
                {
                  localObject = localCipherGen.getLMUserSessionKey();
                  continue;
                  label330: this.sessionKey = ((byte[])localObject);
                  continue;
                  label339: this.sessionKey = null;
                }
              }
            }
          }
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException)
        {
          throw new NTLMEngineException("Unicode not supported: " + localUnsupportedEncodingException.getMessage(), localUnsupportedEncodingException);
        }
      }
    }

    String getResponse()
    {
      int i = this.ntResp.length;
      int j = this.lmResp.length;
      int k = this.domainBytes.length;
      int m = this.hostBytes.length;
      int n = this.userBytes.length;
      if (this.sessionKey != null);
      for (int i1 = this.sessionKey.length; ; i1 = 0)
      {
        int i2 = 72 + j;
        int i3 = i2 + i;
        int i4 = i3 + k;
        int i5 = i4 + n;
        int i6 = i5 + m;
        prepareResponse(i6 + i1, 3);
        addUShort(j);
        addUShort(j);
        addULong(72);
        addUShort(i);
        addUShort(i);
        addULong(i2);
        addUShort(k);
        addUShort(k);
        addULong(i3);
        addUShort(n);
        addUShort(n);
        addULong(i4);
        addUShort(m);
        addUShort(m);
        addULong(i5);
        addUShort(i1);
        addUShort(i1);
        addULong(i6);
        addULong(0x2000000 | (0x80 & this.type2Flags | 0x200 & this.type2Flags | 0x80000 & this.type2Flags) | 0x8000 & this.type2Flags | 0x20 & this.type2Flags | 0x10 & this.type2Flags | 0x20000000 & this.type2Flags | 0x80000000 & this.type2Flags | 0x40000000 & this.type2Flags | 0x800000 & this.type2Flags | 0x1 & this.type2Flags | 0x4 & this.type2Flags);
        addUShort(261);
        addULong(2600);
        addUShort(3840);
        addBytes(this.lmResp);
        addBytes(this.ntResp);
        addBytes(this.domainBytes);
        addBytes(this.userBytes);
        addBytes(this.hostBytes);
        if (this.sessionKey != null)
          addBytes(this.sessionKey);
        return super.getResponse();
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.auth.NTLMEngineImpl
 * JD-Core Version:    0.6.2
 */