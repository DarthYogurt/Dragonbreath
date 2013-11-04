package my.org.json;

import java.util.Iterator;

public class CookieList
{
  public static JSONObject toJSONObject(String paramString)
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    JSONTokener localJSONTokener = new JSONTokener(paramString);
    while (true)
    {
      if (!localJSONTokener.more())
        return localJSONObject;
      String str = Cookie.unescape(localJSONTokener.nextTo('='));
      localJSONTokener.next('=');
      localJSONObject.put(str, Cookie.unescape(localJSONTokener.nextTo(';')));
      localJSONTokener.next();
    }
  }

  public static String toString(JSONObject paramJSONObject)
    throws JSONException
  {
    int i = 0;
    Iterator localIterator = paramJSONObject.keys();
    StringBuffer localStringBuffer = new StringBuffer();
    while (true)
    {
      if (!localIterator.hasNext())
        return localStringBuffer.toString();
      String str = localIterator.next().toString();
      if (!paramJSONObject.isNull(str))
      {
        if (i != 0)
          localStringBuffer.append(';');
        localStringBuffer.append(Cookie.escape(str));
        localStringBuffer.append("=");
        localStringBuffer.append(Cookie.escape(paramJSONObject.getString(str)));
        i = 1;
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.org.json.CookieList
 * JD-Core Version:    0.6.2
 */