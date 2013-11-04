package my.apache.commons.codec.net;

import java.io.UnsupportedEncodingException;
import java.util.BitSet;
import my.apache.commons.codec.DecoderException;
import my.apache.commons.codec.EncoderException;
import my.apache.commons.codec.StringDecoder;
import my.apache.commons.codec.StringEncoder;

public class QCodec extends RFC1522Codec
  implements StringEncoder, StringDecoder
{
  private static final byte BLANK = 32;
  private static final BitSet PRINTABLE_CHARS = new BitSet(256);
  private static final byte UNDERSCORE = 95;
  private final String charset;
  private boolean encodeBlanks = false;

  static
  {
    PRINTABLE_CHARS.set(32);
    PRINTABLE_CHARS.set(33);
    PRINTABLE_CHARS.set(34);
    PRINTABLE_CHARS.set(35);
    PRINTABLE_CHARS.set(36);
    PRINTABLE_CHARS.set(37);
    PRINTABLE_CHARS.set(38);
    PRINTABLE_CHARS.set(39);
    PRINTABLE_CHARS.set(40);
    PRINTABLE_CHARS.set(41);
    PRINTABLE_CHARS.set(42);
    PRINTABLE_CHARS.set(43);
    PRINTABLE_CHARS.set(44);
    PRINTABLE_CHARS.set(45);
    PRINTABLE_CHARS.set(46);
    PRINTABLE_CHARS.set(47);
    int i = 48;
    int j;
    if (i > 57)
    {
      PRINTABLE_CHARS.set(58);
      PRINTABLE_CHARS.set(59);
      PRINTABLE_CHARS.set(60);
      PRINTABLE_CHARS.set(62);
      PRINTABLE_CHARS.set(64);
      j = 65;
      label193: if (j <= 90)
        break label294;
      PRINTABLE_CHARS.set(91);
      PRINTABLE_CHARS.set(92);
      PRINTABLE_CHARS.set(93);
      PRINTABLE_CHARS.set(94);
      PRINTABLE_CHARS.set(96);
    }
    for (int k = 97; ; k++)
    {
      if (k > 122)
      {
        PRINTABLE_CHARS.set(123);
        PRINTABLE_CHARS.set(124);
        PRINTABLE_CHARS.set(125);
        PRINTABLE_CHARS.set(126);
        return;
        PRINTABLE_CHARS.set(i);
        i++;
        break;
        label294: PRINTABLE_CHARS.set(j);
        j++;
        break label193;
      }
      PRINTABLE_CHARS.set(k);
    }
  }

  public QCodec()
  {
    this("UTF-8");
  }

  public QCodec(String paramString)
  {
    this.charset = paramString;
  }

  public Object decode(Object paramObject)
    throws DecoderException
  {
    if (paramObject == null)
      return null;
    if ((paramObject instanceof String))
      return decode((String)paramObject);
    throw new DecoderException("Objects of type " + paramObject.getClass().getName() + " cannot be decoded using Q codec");
  }

  public String decode(String paramString)
    throws DecoderException
  {
    if (paramString == null)
      return null;
    try
    {
      String str = decodeText(paramString);
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new DecoderException(localUnsupportedEncodingException.getMessage(), localUnsupportedEncodingException);
    }
  }

  protected byte[] doDecoding(byte[] paramArrayOfByte)
    throws DecoderException
  {
    if (paramArrayOfByte == null)
      return null;
    byte[] arrayOfByte;
    int m;
    for (int i = 0; ; i++)
    {
      int j = paramArrayOfByte.length;
      int k = 0;
      if (i >= j);
      while (true)
      {
        if (k == 0)
          break label102;
        arrayOfByte = new byte[paramArrayOfByte.length];
        m = 0;
        if (m < paramArrayOfByte.length)
          break label66;
        return QuotedPrintableCodec.decodeQuotedPrintable(arrayOfByte);
        if (paramArrayOfByte[i] != 95)
          break;
        k = 1;
      }
    }
    label66: int n = paramArrayOfByte[m];
    if (n != 95)
      arrayOfByte[m] = n;
    while (true)
    {
      m++;
      break;
      arrayOfByte[m] = 32;
    }
    label102: return QuotedPrintableCodec.decodeQuotedPrintable(paramArrayOfByte);
  }

  protected byte[] doEncoding(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte;
    if (paramArrayOfByte == null)
      arrayOfByte = null;
    while (true)
    {
      return arrayOfByte;
      arrayOfByte = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, paramArrayOfByte);
      if (this.encodeBlanks)
        for (int i = 0; i < arrayOfByte.length; i++)
          if (arrayOfByte[i] == 32)
            arrayOfByte[i] = 95;
    }
  }

  public Object encode(Object paramObject)
    throws EncoderException
  {
    if (paramObject == null)
      return null;
    if ((paramObject instanceof String))
      return encode((String)paramObject);
    throw new EncoderException("Objects of type " + paramObject.getClass().getName() + " cannot be encoded using Q codec");
  }

  public String encode(String paramString)
    throws EncoderException
  {
    if (paramString == null)
      return null;
    return encode(paramString, getDefaultCharset());
  }

  public String encode(String paramString1, String paramString2)
    throws EncoderException
  {
    if (paramString1 == null)
      return null;
    try
    {
      String str = encodeText(paramString1, paramString2);
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new EncoderException(localUnsupportedEncodingException.getMessage(), localUnsupportedEncodingException);
    }
  }

  public String getDefaultCharset()
  {
    return this.charset;
  }

  protected String getEncoding()
  {
    return "Q";
  }

  public boolean isEncodeBlanks()
  {
    return this.encodeBlanks;
  }

  public void setEncodeBlanks(boolean paramBoolean)
  {
    this.encodeBlanks = paramBoolean;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.commons.codec.net.QCodec
 * JD-Core Version:    0.6.2
 */