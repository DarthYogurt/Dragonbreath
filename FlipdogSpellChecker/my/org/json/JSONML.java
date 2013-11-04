package my.org.json;

import java.util.Iterator;

public class JSONML
{
  private static Object parse(XMLTokener paramXMLTokener, boolean paramBoolean, JSONArray paramJSONArray)
    throws JSONException
  {
    Object localObject1;
    Object localObject2;
    Object localObject6;
    while (true)
    {
      if (!paramXMLTokener.more())
        throw paramXMLTokener.syntaxError("Bad XML");
      localObject1 = paramXMLTokener.nextContent();
      if (localObject1 != XML.LT)
        break label713;
      localObject2 = paramXMLTokener.nextToken();
      if (!(localObject2 instanceof Character))
        break label286;
      if (localObject2 == XML.SLASH)
      {
        localObject6 = paramXMLTokener.nextToken();
        if (!(localObject6 instanceof String))
          throw new JSONException("Expected a closing name instead of '" + localObject6 + "'.");
        if (paramXMLTokener.nextToken() == XML.GT)
          break label592;
        throw paramXMLTokener.syntaxError("Misshaped close tag");
      }
      if (localObject2 == XML.BANG)
      {
        int i = paramXMLTokener.next();
        if (i == 45)
        {
          if (paramXMLTokener.next() == '-')
            paramXMLTokener.skipPast("-->");
          else
            paramXMLTokener.back();
        }
        else if (i == 91)
        {
          if ((paramXMLTokener.nextToken().equals("CDATA")) && (paramXMLTokener.next() == '['))
          {
            if (paramJSONArray != null)
              paramJSONArray.put(paramXMLTokener.nextCDATA());
          }
          else
            throw paramXMLTokener.syntaxError("Expected 'CDATA['");
        }
        else
        {
          int j = 1;
          label259: 
          while (true)
          {
            Object localObject7 = paramXMLTokener.nextMeta();
            if (localObject7 == null)
              throw paramXMLTokener.syntaxError("Missing '>' after '<!'.");
            if (localObject7 == XML.LT)
              j++;
            while (true)
            {
              if (j > 0)
                break label259;
              break;
              if (localObject7 == XML.GT)
                j--;
            }
          }
        }
      }
      else
      {
        if (localObject2 != XML.QUEST)
          break;
        paramXMLTokener.skipPast("?>");
      }
    }
    throw paramXMLTokener.syntaxError("Misshaped tag");
    label286: if (!(localObject2 instanceof String))
      throw paramXMLTokener.syntaxError("Bad tagName '" + localObject2 + "'.");
    String str1 = (String)localObject2;
    JSONArray localJSONArray = new JSONArray();
    JSONObject localJSONObject = new JSONObject();
    label369: Object localObject3;
    if (paramBoolean)
    {
      localJSONArray.put(str1);
      if (paramJSONArray != null)
        paramJSONArray.put(localJSONArray);
      localObject3 = null;
      label372: if (localObject3 != null)
        break label741;
    }
    label592: label598: label741: for (Object localObject4 = paramXMLTokener.nextToken(); ; localObject4 = localObject3)
    {
      if (localObject4 == null)
      {
        throw paramXMLTokener.syntaxError("Misshaped tag");
        localJSONObject.put("tagName", str1);
        if (paramJSONArray == null)
          break label369;
        paramJSONArray.put(localJSONObject);
        break label369;
      }
      if (!(localObject4 instanceof String))
      {
        if ((paramBoolean) && (localJSONObject.length() > 0))
          localJSONArray.put(localJSONObject);
        if (localObject4 != XML.SLASH)
          break label598;
        if (paramXMLTokener.nextToken() != XML.GT)
          throw paramXMLTokener.syntaxError("Misshaped tag");
      }
      else
      {
        String str2 = (String)localObject4;
        if ((!paramBoolean) && (("tagName".equals(str2)) || ("childNode".equals(str2))))
          throw paramXMLTokener.syntaxError("Reserved attribute.");
        localObject3 = paramXMLTokener.nextToken();
        if (localObject3 == XML.EQ)
        {
          Object localObject5 = paramXMLTokener.nextToken();
          if (!(localObject5 instanceof String))
            throw paramXMLTokener.syntaxError("Missing value");
          localJSONObject.accumulate(str2, XML.stringToValue((String)localObject5));
          localObject3 = null;
          break label372;
        }
        localJSONObject.accumulate(str2, "");
        break label372;
      }
      if (paramJSONArray != null)
        break;
      if (paramBoolean)
      {
        localObject6 = localJSONArray;
        return localObject6;
      }
      return localJSONObject;
      if (localObject4 != XML.GT)
        throw paramXMLTokener.syntaxError("Misshaped tag");
      String str3 = (String)parse(paramXMLTokener, paramBoolean, localJSONArray);
      if (str3 == null)
        break;
      if (!str3.equals(str1))
        throw paramXMLTokener.syntaxError("Mismatched '" + str1 + "' and '" + str3 + "'");
      if ((!paramBoolean) && (localJSONArray.length() > 0))
        localJSONObject.put("childNodes", localJSONArray);
      if (paramJSONArray != null)
        break;
      if (paramBoolean)
        return localJSONArray;
      return localJSONObject;
      if (paramJSONArray == null)
        break;
      if ((localObject1 instanceof String))
        localObject1 = XML.stringToValue((String)localObject1);
      paramJSONArray.put(localObject1);
      break;
    }
  }

  public static JSONArray toJSONArray(String paramString)
    throws JSONException
  {
    return toJSONArray(new XMLTokener(paramString));
  }

  public static JSONArray toJSONArray(XMLTokener paramXMLTokener)
    throws JSONException
  {
    return (JSONArray)parse(paramXMLTokener, true, null);
  }

  public static JSONObject toJSONObject(String paramString)
    throws JSONException
  {
    return toJSONObject(new XMLTokener(paramString));
  }

  public static JSONObject toJSONObject(XMLTokener paramXMLTokener)
    throws JSONException
  {
    return (JSONObject)parse(paramXMLTokener, false, null);
  }

  public static String toString(JSONArray paramJSONArray)
    throws JSONException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    String str1 = paramJSONArray.getString(0);
    XML.noSpace(str1);
    String str2 = XML.escape(str1);
    localStringBuffer.append('<');
    localStringBuffer.append(str2);
    Object localObject1 = paramJSONArray.opt(1);
    int i;
    JSONObject localJSONObject;
    Iterator localIterator;
    if ((localObject1 instanceof JSONObject))
    {
      i = 2;
      localJSONObject = (JSONObject)localObject1;
      localIterator = localJSONObject.keys();
      if (localIterator.hasNext());
    }
    int j;
    while (true)
    {
      j = paramJSONArray.length();
      if (i < j)
        break label198;
      localStringBuffer.append('/');
      localStringBuffer.append('>');
      return localStringBuffer.toString();
      String str3 = localIterator.next().toString();
      XML.noSpace(str3);
      String str4 = localJSONObject.optString(str3);
      if (str4 == null)
        break;
      localStringBuffer.append(' ');
      localStringBuffer.append(XML.escape(str3));
      localStringBuffer.append('=');
      localStringBuffer.append('"');
      localStringBuffer.append(XML.escape(str4));
      localStringBuffer.append('"');
      break;
      i = 1;
    }
    label198: localStringBuffer.append('>');
    label279: label325: 
    while (true)
    {
      Object localObject2 = paramJSONArray.get(i);
      i++;
      if (localObject2 != null)
      {
        if (!(localObject2 instanceof String))
          break label279;
        localStringBuffer.append(XML.escape(localObject2.toString()));
      }
      while (true)
      {
        if (i < j)
          break label325;
        localStringBuffer.append('<');
        localStringBuffer.append('/');
        localStringBuffer.append(str2);
        localStringBuffer.append('>');
        break;
        if ((localObject2 instanceof JSONObject))
          localStringBuffer.append(toString((JSONObject)localObject2));
        else if ((localObject2 instanceof JSONArray))
          localStringBuffer.append(toString((JSONArray)localObject2));
      }
    }
  }

  public static String toString(JSONObject paramJSONObject)
    throws JSONException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    String str1 = paramJSONObject.optString("tagName");
    if (str1 == null)
      return XML.escape(paramJSONObject.toString());
    XML.noSpace(str1);
    String str2 = XML.escape(str1);
    localStringBuffer.append('<');
    localStringBuffer.append(str2);
    Iterator localIterator = paramJSONObject.keys();
    JSONArray localJSONArray;
    if (!localIterator.hasNext())
    {
      localJSONArray = paramJSONObject.optJSONArray("childNodes");
      if (localJSONArray != null)
        break label198;
      localStringBuffer.append('/');
      localStringBuffer.append('>');
    }
    label198: int j;
    while (true)
    {
      return localStringBuffer.toString();
      String str3 = localIterator.next().toString();
      if (("tagName".equals(str3)) || ("childNodes".equals(str3)))
        break;
      XML.noSpace(str3);
      String str4 = paramJSONObject.optString(str3);
      if (str4 == null)
        break;
      localStringBuffer.append(' ');
      localStringBuffer.append(XML.escape(str3));
      localStringBuffer.append('=');
      localStringBuffer.append('"');
      localStringBuffer.append(XML.escape(str4));
      localStringBuffer.append('"');
      break;
      localStringBuffer.append('>');
      int i = localJSONArray.length();
      j = 0;
      if (j < i)
        break label252;
      localStringBuffer.append('<');
      localStringBuffer.append('/');
      localStringBuffer.append(str2);
      localStringBuffer.append('>');
    }
    label252: Object localObject = localJSONArray.get(j);
    if (localObject != null)
    {
      if (!(localObject instanceof String))
        break label293;
      localStringBuffer.append(XML.escape(localObject.toString()));
    }
    while (true)
    {
      j++;
      break;
      label293: if ((localObject instanceof JSONObject))
        localStringBuffer.append(toString((JSONObject)localObject));
      else if ((localObject instanceof JSONArray))
        localStringBuffer.append(toString((JSONArray)localObject));
      else
        localStringBuffer.append(localObject.toString());
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.org.json.JSONML
 * JD-Core Version:    0.6.2
 */