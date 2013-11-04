package com.a.a;

import com.a.a.a.b;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import my.apache.http.HttpEntity;
import my.apache.http.client.methods.HttpGet;
import my.apache.http.client.methods.HttpPost;
import my.apache.http.client.methods.HttpPut;
import my.apache.http.entity.InputStreamEntity;
import org.json.simple.JSONArray;

public class a<SESS_T extends com.a.a.b.j>
{
  public static final int a = 1;
  public static final String b = v.a();
  public static final long c = 188743680L;
  protected static final int d = 25000;
  private static final int f = 1000;
  private static final int g = 10000;
  private static final int h = 180000;
  protected final SESS_T e;

  public a(SESS_T paramSESS_T)
  {
    if (paramSESS_T == null)
      throw new IllegalArgumentException("Session must not be null.");
    this.e = paramSESS_T;
  }

  private c a(String paramString1, String paramString2, boolean paramBoolean, String paramString3)
    throws com.a.a.c.i
  {
    if ((paramString1 == null) || (paramString1.equals("")))
      throw new IllegalArgumentException("path is null or empty.");
    c();
    if (!paramString1.startsWith("/"))
      paramString1 = "/" + paramString1;
    String str = "/commit_chunked_upload/" + this.e.d() + paramString1;
    String[] arrayOfString = new String[8];
    arrayOfString[0] = "overwrite";
    arrayOfString[1] = String.valueOf(paramBoolean);
    arrayOfString[2] = "parent_rev";
    arrayOfString[3] = paramString3;
    arrayOfString[4] = "locale";
    arrayOfString[5] = this.e.e().toString();
    arrayOfString[6] = "upload_id";
    arrayOfString[7] = paramString2;
    HttpPost localHttpPost = new HttpPost(m.a(this.e.k(), 1, str, arrayOfString));
    this.e.a(localHttpPost);
    return new c((Map)m.a(m.a(this.e, localHttpPost)));
  }

  private r a(String paramString1, InputStream paramInputStream, long paramLong, boolean paramBoolean, String paramString2, h paramh)
    throws com.a.a.c.i
  {
    if ((paramString1 == null) || (paramString1.equals("")))
      throw new IllegalArgumentException("path is null or empty.");
    c();
    if (!paramString1.startsWith("/"))
      paramString1 = "/" + paramString1;
    String str = "/files_put/" + this.e.d() + paramString1;
    if (paramString2 == null)
      paramString2 = "";
    String[] arrayOfString = new String[6];
    arrayOfString[0] = "overwrite";
    arrayOfString[1] = String.valueOf(paramBoolean);
    arrayOfString[2] = "parent_rev";
    arrayOfString[3] = paramString2;
    arrayOfString[4] = "locale";
    arrayOfString[5] = this.e.e().toString();
    HttpPut localHttpPut = new HttpPut(m.a(this.e.k(), 1, str, arrayOfString));
    this.e.a(localHttpPut);
    InputStreamEntity localInputStreamEntity = new InputStreamEntity(paramInputStream, paramLong);
    localInputStreamEntity.setContentEncoding("application/octet-stream");
    localInputStreamEntity.setChunked(false);
    if (paramh != null);
    for (Object localObject = new s(localInputStreamEntity, paramh); ; localObject = localInputStreamEntity)
    {
      localHttpPut.setEntity((HttpEntity)localObject);
      return new y(localHttpPut, this.e);
    }
  }

  protected static boolean a(Map<String, Object> paramMap, String paramString)
  {
    Object localObject = paramMap.get(paramString);
    if ((localObject != null) && ((localObject instanceof Boolean)))
      return ((Boolean)localObject).booleanValue();
    return false;
  }

  protected static long b(Map<String, Object> paramMap, String paramString)
  {
    Object localObject = paramMap.get(paramString);
    if (localObject != null)
    {
      if ((localObject instanceof Number))
        return ((Number)localObject).longValue();
      if ((localObject instanceof String))
        return Long.parseLong((String)localObject, 16);
    }
    return 0L;
  }

  public SESS_T a()
  {
    return this.e;
  }

  public c a(String paramString)
    throws com.a.a.c.i
  {
    c();
    String[] arrayOfString = new String[6];
    arrayOfString[0] = "root";
    arrayOfString[1] = this.e.d().toString();
    arrayOfString[2] = "path";
    arrayOfString[3] = paramString;
    arrayOfString[4] = "locale";
    arrayOfString[5] = this.e.e().toString();
    return new c((Map)m.a(k.b, this.e.j(), "/fileops/create_folder", 1, arrayOfString, this.e));
  }

  public c a(String paramString1, int paramInt, String paramString2, boolean paramBoolean, String paramString3)
    throws com.a.a.c.i
  {
    c();
    if (paramInt <= 0)
      paramInt = 25000;
    String[] arrayOfString = new String[10];
    arrayOfString[0] = "file_limit";
    arrayOfString[1] = String.valueOf(paramInt);
    arrayOfString[2] = "hash";
    arrayOfString[3] = paramString2;
    arrayOfString[4] = "list";
    arrayOfString[5] = String.valueOf(paramBoolean);
    arrayOfString[6] = "rev";
    arrayOfString[7] = paramString3;
    arrayOfString[8] = "locale";
    arrayOfString[9] = this.e.e().toString();
    String str = "/metadata/" + this.e.d() + paramString1;
    return new c((Map)m.a(k.a, this.e.j(), str, 1, arrayOfString, this.e));
  }

  public c a(String paramString, InputStream paramInputStream, long paramLong, h paramh)
    throws com.a.a.c.i
  {
    return b(paramString, paramInputStream, paramLong, paramh).b();
  }

  public c a(String paramString1, InputStream paramInputStream, long paramLong, String paramString2, h paramh)
    throws com.a.a.c.i
  {
    return b(paramString1, paramInputStream, paramLong, paramString2, paramh).b();
  }

  public e a(String paramString, boolean paramBoolean)
    throws com.a.a.c.i
  {
    c();
    String str1 = "/media/" + this.e.d() + paramString;
    k localk = k.a;
    String str2 = this.e.j();
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "locale";
    arrayOfString[1] = this.e.e().toString();
    return new e((Map)m.a(localk, str2, str1, 1, arrayOfString, this.e), paramBoolean, null);
  }

  public f a(String paramString, OutputStream paramOutputStream, i parami, o paramo, h paramh)
    throws com.a.a.c.i
  {
    g localg = a(paramString, parami, paramo);
    localg.a(paramOutputStream, paramh);
    return localg.a();
  }

  public f a(String paramString1, String paramString2, OutputStream paramOutputStream, h paramh)
    throws com.a.a.c.i
  {
    g localg = a(paramString1, paramString2);
    localg.a(paramOutputStream, paramh);
    return localg.a();
  }

  public g a(String paramString, i parami, o paramo)
    throws com.a.a.c.i
  {
    c();
    String str = "/thumbnails/" + this.e.d() + paramString;
    String[] arrayOfString = new String[6];
    arrayOfString[0] = "size";
    arrayOfString[1] = parami.a();
    arrayOfString[2] = "format";
    arrayOfString[3] = paramo.toString();
    arrayOfString[4] = "locale";
    arrayOfString[5] = this.e.e().toString();
    d locald = m.b(k.a, this.e.k(), str, 1, arrayOfString, this.e);
    return new g(locald.a, locald.b);
  }

  public g a(String paramString1, String paramString2)
    throws com.a.a.c.i
  {
    c();
    if (!paramString1.startsWith("/"))
      paramString1 = "/" + paramString1;
    String str = "/files/" + this.e.d() + paramString1;
    String[] arrayOfString = new String[4];
    arrayOfString[0] = "rev";
    arrayOfString[1] = paramString2;
    arrayOfString[2] = "locale";
    arrayOfString[3] = this.e.e().toString();
    HttpGet localHttpGet = new HttpGet(m.a(this.e.k(), 1, str, arrayOfString));
    this.e.a(localHttpGet);
    return new g(localHttpGet, m.a(this.e, localHttpGet));
  }

  public x a(InputStream paramInputStream, long paramLong1, h paramh, long paramLong2, String paramString)
  {
    String[] arrayOfString;
    if (paramLong2 == 0L)
      arrayOfString = new String[0];
    while (true)
    {
      HttpPut localHttpPut = new HttpPut(m.a(this.e.k(), 1, "/chunked_upload/", arrayOfString));
      this.e.a(localHttpPut);
      InputStreamEntity localInputStreamEntity = new InputStreamEntity(paramInputStream, paramLong1);
      s locals = new s(localInputStreamEntity, paramh);
      localInputStreamEntity.setContentEncoding("application/octet-stream");
      localInputStreamEntity.setChunked(false);
      localHttpPut.setEntity(locals);
      return new x(localHttpPut, this.e);
      arrayOfString = new String[4];
      arrayOfString[0] = "upload_id";
      arrayOfString[1] = paramString;
      arrayOfString[2] = "offset";
      arrayOfString[3] = paramLong2;
    }
  }

  public a<SESS_T>.z a(InputStream paramInputStream, long paramLong)
  {
    return new z(this, paramInputStream, paramLong);
  }

  public a<SESS_T>.z a(InputStream paramInputStream, long paramLong, int paramInt)
  {
    return new z(this, paramInputStream, paramLong, paramInt);
  }

  public List<c> a(String paramString, int paramInt)
    throws com.a.a.c.i
  {
    c();
    if (paramInt <= 0)
      paramInt = 1000;
    String[] arrayOfString = new String[4];
    arrayOfString[0] = "rev_limit";
    arrayOfString[1] = String.valueOf(paramInt);
    arrayOfString[2] = "locale";
    arrayOfString[3] = this.e.e().toString();
    String str = "/revisions/" + this.e.d() + paramString;
    JSONArray localJSONArray = (JSONArray)m.a(k.a, this.e.j(), str, 1, arrayOfString, this.e);
    LinkedList localLinkedList = new LinkedList();
    Iterator localIterator = localJSONArray.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localLinkedList;
      localLinkedList.add(new c((Map)localIterator.next()));
    }
  }

  public List<c> a(String paramString1, String paramString2, int paramInt, boolean paramBoolean)
    throws com.a.a.c.i
  {
    c();
    if (paramInt <= 0)
      paramInt = 10000;
    String str = "/search/" + this.e.d() + paramString1;
    String[] arrayOfString = new String[8];
    arrayOfString[0] = "query";
    arrayOfString[1] = paramString2;
    arrayOfString[2] = "file_limit";
    arrayOfString[3] = String.valueOf(paramInt);
    arrayOfString[4] = "include_deleted";
    arrayOfString[5] = String.valueOf(paramBoolean);
    arrayOfString[6] = "locale";
    arrayOfString[7] = this.e.e().toString();
    Object localObject1 = m.a(k.a, this.e.j(), str, 1, arrayOfString, this.e);
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator;
    if ((localObject1 instanceof JSONArray))
      localIterator = ((JSONArray)localObject1).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localArrayList;
      Object localObject2 = localIterator.next();
      if ((localObject2 instanceof Map))
        localArrayList.add(new c((Map)localObject2));
    }
  }

  public c b(String paramString1, String paramString2)
    throws com.a.a.c.i
  {
    c();
    String[] arrayOfString = new String[8];
    arrayOfString[0] = "root";
    arrayOfString[1] = this.e.d().toString();
    arrayOfString[2] = "from_path";
    arrayOfString[3] = paramString1;
    arrayOfString[4] = "to_path";
    arrayOfString[5] = paramString2;
    arrayOfString[6] = "locale";
    arrayOfString[7] = this.e.e().toString();
    return new c((Map)m.a(k.b, this.e.j(), "/fileops/move", 1, arrayOfString, this.e));
  }

  public p b()
    throws com.a.a.c.i
  {
    c();
    k localk = k.a;
    String str = this.e.j();
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "locale";
    arrayOfString[1] = this.e.e().toString();
    return new p((Map)m.a(localk, str, "/account/info", 1, arrayOfString, this.e));
  }

  public r b(String paramString, InputStream paramInputStream, long paramLong, h paramh)
    throws com.a.a.c.i
  {
    return a(paramString, paramInputStream, paramLong, true, null, paramh);
  }

  public r b(String paramString1, InputStream paramInputStream, long paramLong, String paramString2, h paramh)
    throws com.a.a.c.i
  {
    return a(paramString1, paramInputStream, paramLong, false, paramString2, paramh);
  }

  public void b(String paramString)
    throws com.a.a.c.i
  {
    c();
    String[] arrayOfString = new String[6];
    arrayOfString[0] = "root";
    arrayOfString[1] = this.e.d().toString();
    arrayOfString[2] = "path";
    arrayOfString[3] = paramString;
    arrayOfString[4] = "locale";
    arrayOfString[5] = this.e.e().toString();
    m.a(k.b, this.e.j(), "/fileops/delete", 1, arrayOfString, this.e);
  }

  public c c(String paramString1, String paramString2)
    throws com.a.a.c.i
  {
    c();
    String[] arrayOfString = new String[8];
    arrayOfString[0] = "root";
    arrayOfString[1] = this.e.d().toString();
    arrayOfString[2] = "from_path";
    arrayOfString[3] = paramString1;
    arrayOfString[4] = "to_path";
    arrayOfString[5] = paramString2;
    arrayOfString[6] = "locale";
    arrayOfString[7] = this.e.e().toString();
    return new c((Map)m.a(k.b, this.e.j(), "/fileops/copy", 1, arrayOfString, this.e));
  }

  public e c(String paramString)
    throws com.a.a.c.i
  {
    c();
    String str1 = "/shares/" + this.e.d() + paramString;
    k localk = k.a;
    String str2 = this.e.j();
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "locale";
    arrayOfString[1] = this.e.e().toString();
    Map localMap = (Map)m.a(localk, str2, str1, 1, arrayOfString, this.e);
    String str3 = (String)localMap.get("url");
    Date localDate = m.a((String)localMap.get("expires"));
    if ((str3 == null) || (localDate == null))
      throw new com.a.a.c.h("Could not parse share response.");
    return new e(localMap, null);
  }

  protected void c()
    throws com.a.a.c.g
  {
    if (!this.e.f())
      throw new com.a.a.c.g();
  }

  public c d(String paramString1, String paramString2)
    throws com.a.a.c.i
  {
    c();
    String[] arrayOfString = new String[4];
    arrayOfString[0] = "rev";
    arrayOfString[1] = paramString2;
    arrayOfString[2] = "locale";
    arrayOfString[3] = this.e.e().toString();
    String str = "/restore/" + this.e.d() + paramString1;
    return new c((Map)m.a(k.a, this.e.j(), str, 1, arrayOfString, this.e));
  }

  public t<c> d(String paramString)
    throws com.a.a.c.i
  {
    String[] arrayOfString = new String[4];
    arrayOfString[0] = "cursor";
    arrayOfString[1] = paramString;
    arrayOfString[2] = "locale";
    arrayOfString[3] = this.e.e().toString();
    Object localObject = m.a(k.b, this.e.j(), "/delta", 1, arrayOfString, this.e);
    try
    {
      t localt = t.a(new com.a.a.a.j(localObject), c.o);
      return localt;
    }
    catch (b localb)
    {
      throw new com.a.a.c.h("Error parsing /delta results: " + localb.getMessage());
    }
  }

  public c e(String paramString1, String paramString2)
    throws com.a.a.c.i
  {
    c();
    if (!paramString2.startsWith("/"))
      throw new IllegalArgumentException("'targetPath' doesn't start with \"/\": " + paramString2);
    String[] arrayOfString = new String[8];
    arrayOfString[0] = "locale";
    arrayOfString[1] = this.e.e().toString();
    arrayOfString[2] = "root";
    arrayOfString[3] = this.e.d().toString();
    arrayOfString[4] = "from_copy_ref";
    arrayOfString[5] = paramString1;
    arrayOfString[6] = "to_path";
    arrayOfString[7] = paramString2;
    return new c((Map)m.a(k.a, this.e.j(), "/fileops/copy", 1, arrayOfString, this.e));
  }

  public u e(String paramString)
    throws com.a.a.c.i
  {
    c();
    if (!paramString.startsWith("/"))
      throw new IllegalArgumentException("'sourcePath' must start with \"/\": " + paramString);
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "locale";
    arrayOfString[1] = this.e.e().toString();
    String str = "/copy_ref/" + this.e.d() + paramString;
    Object localObject = m.a(k.a, this.e.j(), str, 1, arrayOfString, this.e);
    try
    {
      u localu = u.a(new com.a.a.a.j(localObject));
      return localu;
    }
    catch (b localb)
    {
      throw new com.a.a.c.h("Error parsing /copy_ref results: " + localb.getMessage());
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.a
 * JD-Core Version:    0.6.2
 */