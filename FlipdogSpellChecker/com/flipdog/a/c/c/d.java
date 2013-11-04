package com.flipdog.a.c.c;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d
{
  public static JSONObject a(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
    }
    throw new RuntimeException(paramString);
  }

  public static JSONObject a(JSONArray paramJSONArray, int paramInt)
  {
    try
    {
      JSONObject localJSONObject = paramJSONArray.getJSONObject(paramInt);
      return localJSONObject;
    }
    catch (JSONException localJSONException1)
    {
      try
      {
        throw new RuntimeException(paramJSONArray.get(paramInt).toString());
      }
      catch (JSONException localJSONException2)
      {
        throw new RuntimeException(localJSONException2);
      }
    }
  }

  public static JSONObject a(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      JSONObject localJSONObject = paramJSONObject.getJSONObject(paramString);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
    }
    throw new RuntimeException(paramJSONObject.toString());
  }

  public static JSONArray b(String paramString)
  {
    try
    {
      JSONArray localJSONArray = new JSONArray(paramString);
      return localJSONArray;
    }
    catch (JSONException localJSONException)
    {
    }
    throw new RuntimeException(paramString);
  }

  public static JSONArray b(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      JSONArray localJSONArray = paramJSONObject.getJSONArray(paramString);
      return localJSONArray;
    }
    catch (JSONException localJSONException)
    {
    }
    throw new RuntimeException(paramJSONObject.toString());
  }

  public static String c(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      String str = paramJSONObject.getString(paramString);
      return str;
    }
    catch (JSONException localJSONException)
    {
    }
    throw new RuntimeException(paramJSONObject.toString());
  }

  public static String d(JSONObject paramJSONObject, String paramString)
  {
    if (paramJSONObject.has(paramString))
      return c(paramJSONObject, paramString);
    return null;
  }

  public static int e(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      int i = paramJSONObject.getInt(paramString);
      return i;
    }
    catch (JSONException localJSONException)
    {
    }
    throw new RuntimeException(paramJSONObject.toString());
  }

  public static int f(JSONObject paramJSONObject, String paramString)
  {
    if (paramJSONObject.has(paramString))
      return e(paramJSONObject, paramString);
    return 0;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.c.c.d
 * JD-Core Version:    0.6.2
 */