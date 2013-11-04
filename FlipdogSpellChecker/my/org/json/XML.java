package my.org.json;

import java.util.Iterator;

public class XML
{
  public static final Character AMP = new Character('&');
  public static final Character APOS = new Character('\'');
  public static final Character BANG = new Character('!');
  public static final Character EQ = new Character('=');
  public static final Character GT = new Character('>');
  public static final Character LT = new Character('<');
  public static final Character QUEST = new Character('?');
  public static final Character QUOT = new Character('"');
  public static final Character SLASH = new Character('/');

  public static String escape(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    int j = paramString.length();
    if (i >= j)
      return localStringBuffer.toString();
    char c = paramString.charAt(i);
    switch (c)
    {
    default:
      localStringBuffer.append(c);
    case '&':
    case '<':
    case '>':
    case '"':
    case '\'':
    }
    while (true)
    {
      i++;
      break;
      localStringBuffer.append("&amp;");
      continue;
      localStringBuffer.append("&lt;");
      continue;
      localStringBuffer.append("&gt;");
      continue;
      localStringBuffer.append("&quot;");
      continue;
      localStringBuffer.append("&apos;");
    }
  }

  public static void noSpace(String paramString)
    throws JSONException
  {
    int i = paramString.length();
    if (i == 0)
      throw new JSONException("Empty string.");
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      if (Character.isWhitespace(paramString.charAt(j)))
        throw new JSONException("'" + paramString + "' contains a space character.");
    }
  }

  private static boolean parse(XMLTokener paramXMLTokener, JSONObject paramJSONObject, String paramString)
    throws JSONException
  {
    Object localObject1 = paramXMLTokener.nextToken();
    if (localObject1 == BANG)
    {
      int i = paramXMLTokener.next();
      label47: int j;
      if (i == 45)
      {
        if (paramXMLTokener.next() == '-')
        {
          paramXMLTokener.skipPast("-->");
          return false;
        }
        paramXMLTokener.back();
        j = 1;
      }
      while (true)
      {
        Object localObject7 = paramXMLTokener.nextMeta();
        if (localObject7 == null)
        {
          throw paramXMLTokener.syntaxError("Missing '>' after '<!'.");
          if (i != 91)
            break label47;
          if (("CDATA".equals(paramXMLTokener.nextToken())) && (paramXMLTokener.next() == '['))
          {
            String str4 = paramXMLTokener.nextCDATA();
            if (str4.length() <= 0)
              break;
            paramJSONObject.accumulate("content", str4);
            return false;
          }
          throw paramXMLTokener.syntaxError("Expected 'CDATA['");
        }
        if (localObject7 == LT)
          j++;
        while (j <= 0)
        {
          return false;
          if (localObject7 == GT)
            j--;
        }
      }
    }
    if (localObject1 == QUEST)
    {
      paramXMLTokener.skipPast("?>");
      return false;
    }
    if (localObject1 == SLASH)
    {
      Object localObject6 = paramXMLTokener.nextToken();
      if (paramString == null)
        throw paramXMLTokener.syntaxError("Mismatched close tag " + localObject6);
      if (!localObject6.equals(paramString))
        throw paramXMLTokener.syntaxError("Mismatched " + paramString + " and " + localObject6);
      if (paramXMLTokener.nextToken() != GT)
        throw paramXMLTokener.syntaxError("Misshaped close tag");
      return true;
    }
    if ((localObject1 instanceof Character))
      throw paramXMLTokener.syntaxError("Misshaped tag");
    String str1 = (String)localObject1;
    Object localObject2 = null;
    JSONObject localJSONObject = new JSONObject();
    label306: if (localObject2 == null);
    for (Object localObject3 = paramXMLTokener.nextToken(); ; localObject3 = localObject2)
    {
      if ((localObject3 instanceof String))
      {
        String str3 = (String)localObject3;
        localObject2 = paramXMLTokener.nextToken();
        if (localObject2 == EQ)
        {
          Object localObject5 = paramXMLTokener.nextToken();
          if (!(localObject5 instanceof String))
            throw paramXMLTokener.syntaxError("Missing value");
          localJSONObject.accumulate(str3, stringToValue((String)localObject5));
          localObject2 = null;
          break label306;
        }
        localJSONObject.accumulate(str3, "");
        break label306;
      }
      if (localObject3 == SLASH)
      {
        if (paramXMLTokener.nextToken() != GT)
          throw paramXMLTokener.syntaxError("Misshaped tag");
        if (localJSONObject.length() > 0)
        {
          paramJSONObject.accumulate(str1, localJSONObject);
          return false;
        }
        paramJSONObject.accumulate(str1, "");
        return false;
      }
      if (localObject3 == GT)
      {
        Object localObject4;
        label542: 
        do
          while (true)
          {
            localObject4 = paramXMLTokener.nextContent();
            if (localObject4 == null)
            {
              if (str1 == null)
                break;
              throw paramXMLTokener.syntaxError("Unclosed tag " + str1);
            }
            if (!(localObject4 instanceof String))
              break label542;
            String str2 = (String)localObject4;
            if (str2.length() > 0)
              localJSONObject.accumulate("content", stringToValue(str2));
          }
        while ((localObject4 != LT) || (!parse(paramXMLTokener, localJSONObject, str1)));
        if (localJSONObject.length() == 0)
        {
          paramJSONObject.accumulate(str1, "");
          return false;
        }
        if ((localJSONObject.length() == 1) && (localJSONObject.opt("content") != null))
        {
          paramJSONObject.accumulate(str1, localJSONObject.opt("content"));
          return false;
        }
        paramJSONObject.accumulate(str1, localJSONObject);
        return false;
      }
      throw paramXMLTokener.syntaxError("Misshaped tag");
    }
  }

  public static Object stringToValue(String paramString)
  {
    int i = 1;
    if ("".equals(paramString))
      return paramString;
    if ("true".equalsIgnoreCase(paramString))
      return Boolean.TRUE;
    if ("false".equalsIgnoreCase(paramString))
      return Boolean.FALSE;
    if ("null".equalsIgnoreCase(paramString))
      return JSONObject.NULL;
    if ("0".equals(paramString))
      return new Integer(0);
    while (true)
    {
      int j;
      int k;
      try
      {
        j = paramString.charAt(0);
        k = 0;
        if (j == 45)
        {
          j = paramString.charAt(1);
          k = 1;
          break label199;
          if ((paramString.charAt(i) == '0') || (j < 48) || (j > 57))
            break;
          if (paramString.indexOf('.') >= 0)
            return Double.valueOf(paramString);
          if ((paramString.indexOf('e') >= 0) || (paramString.indexOf('E') >= 0))
            break;
          Long localLong = new Long(paramString);
          if (localLong.longValue() == localLong.intValue())
          {
            Integer localInteger = new Integer(localLong.intValue());
            return localInteger;
          }
          return localLong;
        }
      }
      catch (Exception localException)
      {
        return paramString;
      }
      label199: if (j == 48)
        if (k != 0)
          i = 2;
    }
  }

  public static JSONObject toJSONObject(String paramString)
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    XMLTokener localXMLTokener = new XMLTokener(paramString);
    while (true)
    {
      if ((!localXMLTokener.more()) || (!localXMLTokener.skipPast("<")))
        return localJSONObject;
      parse(localXMLTokener, localJSONObject, null);
    }
  }

  public static String toString(Object paramObject)
    throws JSONException
  {
    return toString(paramObject, null);
  }

  public static String toString(Object paramObject, String paramString)
    throws JSONException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if ((paramObject instanceof JSONObject))
    {
      if (paramString != null)
      {
        localStringBuffer.append('<');
        localStringBuffer.append(paramString);
        localStringBuffer.append('>');
      }
      JSONObject localJSONObject = (JSONObject)paramObject;
      Iterator localIterator = localJSONObject.keys();
      while (true)
      {
        if (!localIterator.hasNext())
        {
          if (paramString != null)
          {
            localStringBuffer.append("</");
            localStringBuffer.append(paramString);
            localStringBuffer.append('>');
          }
          return localStringBuffer.toString();
        }
        String str3 = localIterator.next().toString();
        Object localObject2 = localJSONObject.opt(str3);
        if (localObject2 == null)
          localObject2 = "";
        if ((localObject2 instanceof String))
          ((String)localObject2);
        while (true)
          if ("content".equals(str3))
          {
            if ((localObject2 instanceof JSONArray))
            {
              JSONArray localJSONArray3 = (JSONArray)localObject2;
              int n = localJSONArray3.length();
              for (int i1 = 0; i1 < n; i1++)
              {
                if (i1 > 0)
                  localStringBuffer.append('\n');
                localStringBuffer.append(escape(localJSONArray3.get(i1).toString()));
              }
              break;
              continue;
            }
            localStringBuffer.append(escape(localObject2.toString()));
            break;
          }
        if ((localObject2 instanceof JSONArray))
        {
          JSONArray localJSONArray2 = (JSONArray)localObject2;
          int k = localJSONArray2.length();
          int m = 0;
          label258: Object localObject3;
          if (m < k)
          {
            localObject3 = localJSONArray2.get(m);
            if (!(localObject3 instanceof JSONArray))
              break label341;
            localStringBuffer.append('<');
            localStringBuffer.append(str3);
            localStringBuffer.append('>');
            localStringBuffer.append(toString(localObject3));
            localStringBuffer.append("</");
            localStringBuffer.append(str3);
            localStringBuffer.append('>');
          }
          while (true)
          {
            m++;
            break label258;
            break;
            label341: localStringBuffer.append(toString(localObject3, str3));
          }
        }
        if ("".equals(localObject2))
        {
          localStringBuffer.append('<');
          localStringBuffer.append(str3);
          localStringBuffer.append("/>");
        }
        else
        {
          localStringBuffer.append(toString(localObject2, str3));
        }
      }
    }
    if (paramObject.getClass().isArray())
      paramObject = new JSONArray(paramObject);
    if ((paramObject instanceof JSONArray))
    {
      JSONArray localJSONArray1 = (JSONArray)paramObject;
      int i = localJSONArray1.length();
      int j = 0;
      if (j >= i)
        return localStringBuffer.toString();
      Object localObject1 = localJSONArray1.opt(j);
      if (paramString == null);
      for (String str2 = "array"; ; str2 = paramString)
      {
        localStringBuffer.append(toString(localObject1, str2));
        j++;
        break;
      }
    }
    if (paramObject == null);
    for (String str1 = "null"; paramString == null; str1 = escape(paramObject.toString()))
      return "\"" + str1 + "\"";
    if (str1.length() == 0)
      return "<" + paramString + "/>";
    return "<" + paramString + ">" + str1 + "</" + paramString + ">";
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.org.json.XML
 * JD-Core Version:    0.6.2
 */