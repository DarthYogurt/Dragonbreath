package com.a.a;

import com.a.a.a.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;

public class c
{
  public static final f<c> o = new j();
  public long a;
  public String b;
  public String c;
  public boolean d;
  public String e;
  public String f;
  public String g;
  public String h;
  public String i;
  public String j;
  public String k;
  public boolean l;
  public boolean m;
  public List<c> n;

  public c()
  {
  }

  public c(Map<String, Object> paramMap)
  {
    this.a = a.b(paramMap, "bytes");
    this.b = ((String)paramMap.get("hash"));
    this.c = ((String)paramMap.get("icon"));
    this.d = a.a(paramMap, "is_dir");
    this.e = ((String)paramMap.get("modified"));
    this.f = ((String)paramMap.get("client_mtime"));
    this.g = ((String)paramMap.get("path"));
    this.h = ((String)paramMap.get("root"));
    this.i = ((String)paramMap.get("size"));
    this.j = ((String)paramMap.get("mime_type"));
    this.k = ((String)paramMap.get("rev"));
    this.l = a.a(paramMap, "thumb_exists");
    this.m = a.a(paramMap, "is_deleted");
    Object localObject1 = paramMap.get("contents");
    if ((localObject1 != null) && ((localObject1 instanceof JSONArray)))
    {
      this.n = new ArrayList();
      Iterator localIterator = ((JSONArray)localObject1).iterator();
      while (true)
      {
        if (!localIterator.hasNext())
          return;
        Object localObject2 = localIterator.next();
        if ((localObject2 instanceof Map))
          this.n.add(new c((Map)localObject2));
      }
    }
    this.n = null;
  }

  public String a()
  {
    int i1 = this.g.lastIndexOf('/');
    return this.g.substring(i1 + 1, this.g.length());
  }

  public String b()
  {
    if (this.g.equals("/"))
      return "";
    int i1 = this.g.lastIndexOf('/');
    return this.g.substring(0, i1 + 1);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.c
 * JD-Core Version:    0.6.2
 */