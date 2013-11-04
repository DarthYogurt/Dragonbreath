package my.apache.commons.codec.net;

import java.io.UnsupportedEncodingException;
import my.apache.commons.codec.DecoderException;
import my.apache.commons.codec.EncoderException;
import my.apache.commons.codec.binary.StringUtils;

abstract class RFC1522Codec
{
  protected static final String POSTFIX = "?=";
  protected static final String PREFIX = "=?";
  protected static final char SEP = '?';

  protected String decodeText(String paramString)
    throws DecoderException, UnsupportedEncodingException
  {
    if (paramString == null)
      return null;
    if ((!paramString.startsWith("=?")) || (!paramString.endsWith("?=")))
      throw new DecoderException("RFC 1522 violation: malformed encoded content");
    int i = -2 + paramString.length();
    int j = paramString.indexOf('?', 2);
    if (j == i)
      throw new DecoderException("RFC 1522 violation: charset token not found");
    String str1 = paramString.substring(2, j);
    if (str1.equals(""))
      throw new DecoderException("RFC 1522 violation: charset not specified");
    int k = j + 1;
    int m = paramString.indexOf('?', k);
    if (m == i)
      throw new DecoderException("RFC 1522 violation: encoding token not found");
    String str2 = paramString.substring(k, m);
    if (!getEncoding().equalsIgnoreCase(str2))
      throw new DecoderException("This codec cannot decode " + str2 + " encoded content");
    int n = m + 1;
    return new String(doDecoding(StringUtils.getBytesUsAscii(paramString.substring(n, paramString.indexOf('?', n)))), str1);
  }

  protected abstract byte[] doDecoding(byte[] paramArrayOfByte)
    throws DecoderException;

  protected abstract byte[] doEncoding(byte[] paramArrayOfByte)
    throws EncoderException;

  protected String encodeText(String paramString1, String paramString2)
    throws EncoderException, UnsupportedEncodingException
  {
    if (paramString1 == null)
      return null;
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("=?");
    localStringBuffer.append(paramString2);
    localStringBuffer.append('?');
    localStringBuffer.append(getEncoding());
    localStringBuffer.append('?');
    localStringBuffer.append(StringUtils.newStringUsAscii(doEncoding(paramString1.getBytes(paramString2))));
    localStringBuffer.append("?=");
    return localStringBuffer.toString();
  }

  protected abstract String getEncoding();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.commons.codec.net.RFC1522Codec
 * JD-Core Version:    0.6.2
 */