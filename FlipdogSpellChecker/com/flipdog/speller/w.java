package com.flipdog.speller;

import com.flipdog.commons.a.as;
import com.flipdog.commons.diagnostic.Track;
import java.util.List;
import my.apache.http.client.HttpClient;
import my.apache.http.client.methods.HttpPost;
import my.apache.http.entity.StringEntity;
import my.apache.http.impl.client.DefaultHttpClient;
import my.org.json.JSONArray;
import my.org.json.JSONException;
import my.org.json.JSONObject;

public class w
{
  private static List<s> a(String paramString)
    throws JSONException
  {
    List localList1 = as.b();
    JSONObject localJSONObject1 = new JSONObject(paramString);
    if (localJSONObject1.has("error"))
    {
      JSONObject localJSONObject4 = localJSONObject1.getJSONObject("error");
      int m = localJSONObject4.getInt("code");
      String str = localJSONObject4.getString("message");
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = str;
      arrayOfObject[1] = Integer.valueOf(m);
      throw new RuntimeException(String.format("%s (%s).", arrayOfObject));
    }
    JSONObject localJSONObject2 = localJSONObject1.getJSONObject("result");
    if (!localJSONObject2.has("spellingCheckResponse"))
      return localList1;
    JSONArray localJSONArray = localJSONObject2.getJSONObject("spellingCheckResponse").getJSONArray("misspellings");
    for (int i = 0; ; i++)
    {
      if (i >= localJSONArray.length())
        return localList1;
      JSONObject localJSONObject3 = localJSONArray.getJSONObject(i);
      int j = localJSONObject3.getInt("charStart");
      int k = localJSONObject3.getInt("charLength");
      List localList2 = a(localJSONObject3);
      s locals = new s();
      locals.a = j;
      locals.b = k;
      locals.d = ((String[])as.a(localList2, String.class));
      localList1.add(locals);
    }
  }

  public static List<s> a(String paramString1, String paramString2)
    throws Exception
  {
    JSONObject localJSONObject = b(paramString1, paramString2);
    DefaultHttpClient localDefaultHttpClient = t.a();
    HttpPost localHttpPost = new HttpPost("https://www.googleapis.com/rpc");
    localHttpPost.setHeader("Content-Type", "application/json; charset=utf-8");
    localHttpPost.setEntity(new StringEntity(localJSONObject.toString(), "utf-8"));
    String str = t.a(localDefaultHttpClient.execute(localHttpPost));
    try
    {
      List localList = a(str);
      return localList;
    }
    catch (Exception localException)
    {
      Track.it(localException);
      Track.me("Warning", "request = %s, %s", new Object[] { paramString2, paramString1 });
      Track.me("Warning", "response = %s", new Object[] { str });
      throw localException;
    }
  }

  private static List<String> a(JSONObject paramJSONObject)
    throws JSONException
  {
    List localList = as.b();
    if (!paramJSONObject.has("suggestions"))
      return localList;
    JSONArray localJSONArray = paramJSONObject.getJSONArray("suggestions");
    for (int i = 0; ; i++)
    {
      if (i >= localJSONArray.length())
        return localList;
      localList.add(localJSONArray.getJSONObject(i).getString("suggestion"));
    }
  }

  private static JSONObject b(String paramString1, String paramString2)
    throws JSONException
  {
    JSONObject localJSONObject1 = new JSONObject();
    JSONObject localJSONObject2 = new JSONObject();
    localJSONObject2.put("text", paramString1);
    localJSONObject2.put("language", paramString2);
    localJSONObject2.put("originCountry", "");
    localJSONObject2.put("key", "AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
    localJSONObject1.put("method", "spelling.check");
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(2);
    localJSONObject1.put("apiVersion", String.format("v%d", arrayOfObject));
    localJSONObject1.put("params", localJSONObject2);
    return localJSONObject1;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.speller.w
 * JD-Core Version:    0.6.2
 */