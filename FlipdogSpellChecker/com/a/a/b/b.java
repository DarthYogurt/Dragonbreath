package com.a.a.b;

import my.apache.http.conn.ClientConnectionRequest;
import my.apache.http.conn.routing.HttpRoute;
import my.apache.http.conn.scheme.SchemeRegistry;
import my.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import my.apache.http.params.HttpParams;

class b extends ThreadSafeClientConnManager
{
  public b(HttpParams paramHttpParams, SchemeRegistry paramSchemeRegistry)
  {
    super(paramHttpParams, paramSchemeRegistry);
  }

  public ClientConnectionRequest requestConnection(HttpRoute paramHttpRoute, Object paramObject)
  {
    h.a(this, 20, 5);
    return super.requestConnection(paramHttpRoute, paramObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.b.b
 * JD-Core Version:    0.6.2
 */