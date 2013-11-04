package my.apache.commons.codec.net;

import java.io.UnsupportedEncodingException;
import my.apache.commons.codec.DecoderException;
import my.apache.commons.codec.EncoderException;
import my.apache.commons.codec.StringDecoder;
import my.apache.commons.codec.StringEncoder;
import my.apache.commons.codec.binary.Base64;

public class BCodec extends RFC1522Codec
  implements StringEncoder, StringDecoder
{
  private final String charset;

  public BCodec()
  {
    this("UTF-8");
  }

  public BCodec(String paramString)
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
    throw new DecoderException("Objects of type " + paramObject.getClass().getName() + " cannot be decoded using BCodec");
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
  {
    if (paramArrayOfByte == null)
      return null;
    return Base64.decodeBase64(paramArrayOfByte);
  }

  protected byte[] doEncoding(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return null;
    return Base64.encodeBase64(paramArrayOfByte);
  }

  public Object encode(Object paramObject)
    throws EncoderException
  {
    if (paramObject == null)
      return null;
    if ((paramObject instanceof String))
      return encode((String)paramObject);
    throw new EncoderException("Objects of type " + paramObject.getClass().getName() + " cannot be encoded using BCodec");
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
    return "B";
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.commons.codec.net.BCodec
 * JD-Core Version:    0.6.2
 */