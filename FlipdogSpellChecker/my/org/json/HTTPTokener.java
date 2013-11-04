package my.org.json;

public class HTTPTokener extends JSONTokener
{
  public HTTPTokener(String paramString)
  {
    super(paramString);
  }

  public String nextToken()
    throws JSONException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    char c1;
    do
      c1 = next();
    while (Character.isWhitespace(c1));
    if ((c1 == '"') || (c1 == '\''))
    {
      c2 = c1;
      while (true)
      {
        c3 = next();
        if (c3 < ' ')
          throw syntaxError("Unterminated string.");
        if (c3 == c2)
          return localStringBuffer.toString();
        localStringBuffer.append(c3);
      }
    }
    while ((c1 != 0) && (!Character.isWhitespace(c1)))
    {
      char c2;
      char c3;
      localStringBuffer.append(c1);
      c1 = next();
    }
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.org.json.HTTPTokener
 * JD-Core Version:    0.6.2
 */