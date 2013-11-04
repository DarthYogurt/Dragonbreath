package my.org.json;

public class CDL
{
  private static String getValue(JSONTokener paramJSONTokener)
    throws JSONException
  {
    char c1;
    do
      c1 = paramJSONTokener.next();
    while ((c1 == ' ') || (c1 == '\t'));
    switch (c1)
    {
    default:
      paramJSONTokener.back();
      return paramJSONTokener.nextTo(',');
    case '\000':
      return null;
    case '"':
    case '\'':
      StringBuffer localStringBuffer = new StringBuffer();
      while (true)
      {
        char c2 = paramJSONTokener.next();
        if (c2 == c1)
          return localStringBuffer.toString();
        if ((c2 == 0) || (c2 == '\n') || (c2 == '\r'))
          throw paramJSONTokener.syntaxError("Missing close quote '" + c1 + "'.");
        localStringBuffer.append(c2);
      }
    case ',':
    }
    paramJSONTokener.back();
    return "";
  }

  public static JSONArray rowToJSONArray(JSONTokener paramJSONTokener)
    throws JSONException
  {
    JSONArray localJSONArray = new JSONArray();
    label121: 
    while (true)
    {
      String str = getValue(paramJSONTokener);
      char c = paramJSONTokener.next();
      if ((str == null) || ((localJSONArray.length() == 0) && (str.length() == 0) && (c != ',')))
      {
        localJSONArray = null;
        return localJSONArray;
      }
      localJSONArray.put(str);
      while (true)
      {
        if (c == ',')
          break label121;
        if (c != ' ')
        {
          if ((c == '\n') || (c == '\r') || (c == 0))
            break;
          throw paramJSONTokener.syntaxError("Bad character '" + c + "' (" + c + ").");
        }
        c = paramJSONTokener.next();
      }
    }
  }

  public static JSONObject rowToJSONObject(JSONArray paramJSONArray, JSONTokener paramJSONTokener)
    throws JSONException
  {
    JSONArray localJSONArray = rowToJSONArray(paramJSONTokener);
    if (localJSONArray != null)
      return localJSONArray.toJSONObject(paramJSONArray);
    return null;
  }

  public static String rowToString(JSONArray paramJSONArray)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    if (i >= paramJSONArray.length())
    {
      localStringBuffer.append('\n');
      return localStringBuffer.toString();
    }
    if (i > 0)
      localStringBuffer.append(',');
    Object localObject = paramJSONArray.opt(i);
    String str;
    int k;
    if (localObject != null)
    {
      str = localObject.toString();
      if ((str.length() <= 0) || ((str.indexOf(',') < 0) && (str.indexOf('\n') < 0) && (str.indexOf('\r') < 0) && (str.indexOf(0) < 0) && (str.charAt(0) != '"')))
        break label188;
      localStringBuffer.append('"');
      int j = str.length();
      k = 0;
      label132: if (k < j)
        break label152;
      localStringBuffer.append('"');
    }
    while (true)
    {
      i++;
      break;
      label152: char c = str.charAt(k);
      if ((c >= ' ') && (c != '"'))
        localStringBuffer.append(c);
      k++;
      break label132;
      label188: localStringBuffer.append(str);
    }
  }

  public static JSONArray toJSONArray(String paramString)
    throws JSONException
  {
    return toJSONArray(new JSONTokener(paramString));
  }

  public static JSONArray toJSONArray(JSONArray paramJSONArray, String paramString)
    throws JSONException
  {
    return toJSONArray(paramJSONArray, new JSONTokener(paramString));
  }

  public static JSONArray toJSONArray(JSONArray paramJSONArray, JSONTokener paramJSONTokener)
    throws JSONException
  {
    if ((paramJSONArray == null) || (paramJSONArray.length() == 0))
    {
      localJSONArray = null;
      return localJSONArray;
    }
    JSONArray localJSONArray = new JSONArray();
    while (true)
    {
      JSONObject localJSONObject = rowToJSONObject(paramJSONArray, paramJSONTokener);
      if (localJSONObject == null)
      {
        if (localJSONArray.length() != 0)
          break;
        return null;
      }
      localJSONArray.put(localJSONObject);
    }
  }

  public static JSONArray toJSONArray(JSONTokener paramJSONTokener)
    throws JSONException
  {
    return toJSONArray(rowToJSONArray(paramJSONTokener), paramJSONTokener);
  }

  public static String toString(JSONArray paramJSONArray)
    throws JSONException
  {
    JSONObject localJSONObject = paramJSONArray.optJSONObject(0);
    if (localJSONObject != null)
    {
      JSONArray localJSONArray = localJSONObject.names();
      if (localJSONArray != null)
        return rowToString(localJSONArray) + toString(localJSONArray, paramJSONArray);
    }
    return null;
  }

  public static String toString(JSONArray paramJSONArray1, JSONArray paramJSONArray2)
    throws JSONException
  {
    if ((paramJSONArray1 == null) || (paramJSONArray1.length() == 0))
      return null;
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; ; i++)
    {
      if (i >= paramJSONArray2.length())
        return localStringBuffer.toString();
      JSONObject localJSONObject = paramJSONArray2.optJSONObject(i);
      if (localJSONObject != null)
        localStringBuffer.append(rowToString(localJSONObject.toJSONArray(paramJSONArray1)));
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.org.json.CDL
 * JD-Core Version:    0.6.2
 */