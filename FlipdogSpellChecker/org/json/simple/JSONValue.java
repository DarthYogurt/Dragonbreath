package org.json.simple;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONValue
{
  public static String escape(String paramString)
  {
    if (paramString == null)
      return null;
    StringBuffer localStringBuffer = new StringBuffer();
    escape(paramString, localStringBuffer);
    return localStringBuffer.toString();
  }

  static void escape(String paramString, StringBuffer paramStringBuffer)
  {
    int i = 0;
    if (i < paramString.length())
    {
      int j = paramString.charAt(i);
      String str;
      int k;
      switch (j)
      {
      default:
        if (((j >= 0) && (j <= 31)) || ((j >= 127) && (j <= 159)) || ((j >= 8192) && (j <= 8447)))
        {
          str = Integer.toHexString(j);
          paramStringBuffer.append("\\u");
          k = 0;
        }
        break;
      case 34:
        while (k < 4 - str.length())
        {
          paramStringBuffer.append('0');
          k++;
          continue;
          paramStringBuffer.append("\\\"");
        }
      case 92:
      case 8:
      case 12:
      case 10:
      case 13:
      case 9:
      case 47:
      }
      while (true)
      {
        i++;
        break;
        paramStringBuffer.append("\\\\");
        continue;
        paramStringBuffer.append("\\b");
        continue;
        paramStringBuffer.append("\\f");
        continue;
        paramStringBuffer.append("\\n");
        continue;
        paramStringBuffer.append("\\r");
        continue;
        paramStringBuffer.append("\\t");
        continue;
        paramStringBuffer.append("\\/");
        continue;
        paramStringBuffer.append(str.toUpperCase());
        continue;
        paramStringBuffer.append(j);
      }
    }
  }

  public static Object parse(Reader paramReader)
  {
    try
    {
      Object localObject = new JSONParser().parse(paramReader);
      return localObject;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public static Object parse(String paramString)
  {
    return parse(new StringReader(paramString));
  }

  public static Object parseWithException(Reader paramReader)
    throws IOException, ParseException
  {
    return new JSONParser().parse(paramReader);
  }

  public static Object parseWithException(String paramString)
    throws ParseException
  {
    return new JSONParser().parse(paramString);
  }

  public static String toJSONString(Object paramObject)
  {
    if (paramObject == null)
      return "null";
    if ((paramObject instanceof String))
      return "\"" + escape((String)paramObject) + "\"";
    if ((paramObject instanceof Double))
    {
      if ((((Double)paramObject).isInfinite()) || (((Double)paramObject).isNaN()))
        return "null";
      return paramObject.toString();
    }
    if ((paramObject instanceof Float))
    {
      if ((((Float)paramObject).isInfinite()) || (((Float)paramObject).isNaN()))
        return "null";
      return paramObject.toString();
    }
    if ((paramObject instanceof Number))
      return paramObject.toString();
    if ((paramObject instanceof Boolean))
      return paramObject.toString();
    if ((paramObject instanceof JSONAware))
      return ((JSONAware)paramObject).toJSONString();
    if ((paramObject instanceof Map))
      return JSONObject.toJSONString((Map)paramObject);
    if ((paramObject instanceof List))
      return JSONArray.toJSONString((List)paramObject);
    return paramObject.toString();
  }

  public static void writeJSONString(Object paramObject, Writer paramWriter)
    throws IOException
  {
    if (paramObject == null)
    {
      paramWriter.write("null");
      return;
    }
    if ((paramObject instanceof String))
    {
      paramWriter.write(34);
      paramWriter.write(escape((String)paramObject));
      paramWriter.write(34);
      return;
    }
    if ((paramObject instanceof Double))
    {
      if ((((Double)paramObject).isInfinite()) || (((Double)paramObject).isNaN()))
      {
        paramWriter.write("null");
        return;
      }
      paramWriter.write(paramObject.toString());
      return;
    }
    if ((paramObject instanceof Float))
    {
      if ((((Float)paramObject).isInfinite()) || (((Float)paramObject).isNaN()))
      {
        paramWriter.write("null");
        return;
      }
      paramWriter.write(paramObject.toString());
      return;
    }
    if ((paramObject instanceof Number))
    {
      paramWriter.write(paramObject.toString());
      return;
    }
    if ((paramObject instanceof Boolean))
    {
      paramWriter.write(paramObject.toString());
      return;
    }
    if ((paramObject instanceof JSONStreamAware))
    {
      ((JSONStreamAware)paramObject).writeJSONString(paramWriter);
      return;
    }
    if ((paramObject instanceof JSONAware))
    {
      paramWriter.write(((JSONAware)paramObject).toJSONString());
      return;
    }
    if ((paramObject instanceof Map))
    {
      JSONObject.writeJSONString((Map)paramObject, paramWriter);
      return;
    }
    if ((paramObject instanceof List))
    {
      JSONArray.writeJSONString((List)paramObject, paramWriter);
      return;
    }
    paramWriter.write(paramObject.toString());
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     org.json.simple.JSONValue
 * JD-Core Version:    0.6.2
 */