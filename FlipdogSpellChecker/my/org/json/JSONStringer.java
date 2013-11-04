package my.org.json;

import java.io.StringWriter;

public class JSONStringer extends JSONWriter
{
  public JSONStringer()
  {
    super(new StringWriter());
  }

  public String toString()
  {
    if (this.mode == 'd')
      return this.writer.toString();
    return null;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.org.json.JSONStringer
 * JD-Core Version:    0.6.2
 */