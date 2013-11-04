package com.a.a.b;

import my.apache.http.ConnectionReuseStrategy;
import my.apache.http.conn.ClientConnectionManager;
import my.apache.http.conn.ConnectionKeepAliveStrategy;
import my.apache.http.impl.client.DefaultHttpClient;
import my.apache.http.params.HttpParams;

class o extends DefaultHttpClient
{
  o(k paramk, ClientConnectionManager paramClientConnectionManager, HttpParams paramHttpParams)
  {
    super(paramClientConnectionManager, paramHttpParams);
  }

  protected ConnectionKeepAliveStrategy createConnectionKeepAliveStrategy()
  {
    return new e(null);
  }

  protected ConnectionReuseStrategy createConnectionReuseStrategy()
  {
    return new d(null);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.b.o
 * JD-Core Version:    0.6.2
 */