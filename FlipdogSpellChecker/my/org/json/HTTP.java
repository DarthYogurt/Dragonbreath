package my.org.json;

import java.util.Iterator;

public class HTTP
{
  public static final String CRLF = "\r\n";

  public static JSONObject toJSONObject(String paramString)
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    HTTPTokener localHTTPTokener = new HTTPTokener(paramString);
    String str1 = localHTTPTokener.nextToken();
    if (str1.toUpperCase().startsWith("HTTP"))
    {
      localJSONObject.put("HTTP-Version", str1);
      localJSONObject.put("Status-Code", localHTTPTokener.nextToken());
      localJSONObject.put("Reason-Phrase", localHTTPTokener.nextTo('\000'));
      localHTTPTokener.next();
    }
    while (true)
      if (!localHTTPTokener.more())
      {
        return localJSONObject;
        localJSONObject.put("Method", str1);
        localJSONObject.put("Request-URI", localHTTPTokener.nextToken());
        localJSONObject.put("HTTP-Version", localHTTPTokener.nextToken());
      }
      else
      {
        String str2 = localHTTPTokener.nextTo(':');
        localHTTPTokener.next(':');
        localJSONObject.put(str2, localHTTPTokener.nextTo('\000'));
        localHTTPTokener.next();
      }
  }

  public static String toString(JSONObject paramJSONObject)
    throws JSONException
  {
    Iterator localIterator = paramJSONObject.keys();
    StringBuffer localStringBuffer = new StringBuffer();
    if ((paramJSONObject.has("Status-Code")) && (paramJSONObject.has("Reason-Phrase")))
    {
      localStringBuffer.append(paramJSONObject.getString("HTTP-Version"));
      localStringBuffer.append(' ');
      localStringBuffer.append(paramJSONObject.getString("Status-Code"));
      localStringBuffer.append(' ');
      localStringBuffer.append(paramJSONObject.getString("Reason-Phrase"));
      localStringBuffer.append("\r\n");
    }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        localStringBuffer.append("\r\n");
        return localStringBuffer.toString();
        if ((paramJSONObject.has("Method")) && (paramJSONObject.has("Request-URI")))
        {
          localStringBuffer.append(paramJSONObject.getString("Method"));
          localStringBuffer.append(' ');
          localStringBuffer.append('"');
          localStringBuffer.append(paramJSONObject.getString("Request-URI"));
          localStringBuffer.append('"');
          localStringBuffer.append(' ');
          localStringBuffer.append(paramJSONObject.getString("HTTP-Version"));
          break;
        }
        throw new JSONException("Not enough material for an HTTP header.");
      }
      String str = localIterator.next().toString();
      if ((!"HTTP-Version".equals(str)) && (!"Status-Code".equals(str)) && (!"Reason-Phrase".equals(str)) && (!"Method".equals(str)) && (!"Request-URI".equals(str)) && (!paramJSONObject.isNull(str)))
      {
        localStringBuffer.append(str);
        localStringBuffer.append(": ");
        localStringBuffer.append(paramJSONObject.getString(str));
        localStringBuffer.append("\r\n");
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.org.json.HTTP
 * JD-Core Version:    0.6.2
 */