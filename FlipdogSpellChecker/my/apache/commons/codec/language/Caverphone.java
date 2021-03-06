package my.apache.commons.codec.language;

import my.apache.commons.codec.EncoderException;
import my.apache.commons.codec.StringEncoder;

public class Caverphone
  implements StringEncoder
{
  private final Caverphone2 encoder = new Caverphone2();

  public String caverphone(String paramString)
  {
    return this.encoder.encode(paramString);
  }

  public Object encode(Object paramObject)
    throws EncoderException
  {
    if (!(paramObject instanceof String))
      throw new EncoderException("Parameter supplied to Caverphone encode is not of type java.lang.String");
    return caverphone((String)paramObject);
  }

  public String encode(String paramString)
  {
    return caverphone(paramString);
  }

  public boolean isCaverphoneEqual(String paramString1, String paramString2)
  {
    return caverphone(paramString1).equals(caverphone(paramString2));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.commons.codec.language.Caverphone
 * JD-Core Version:    0.6.2
 */