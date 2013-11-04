package my.apache.http.params;

public final class HttpConnectionParams
  implements CoreConnectionPNames
{
  public static int getConnectionTimeout(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getIntParameter("http.connection.timeout", 0);
  }

  public static int getLinger(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getIntParameter("http.socket.linger", -1);
  }

  public static boolean getSoKeepalive(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getBooleanParameter("http.socket.keepalive", false);
  }

  public static boolean getSoReuseaddr(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getBooleanParameter("http.socket.reuseaddr", false);
  }

  public static int getSoTimeout(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getIntParameter("http.socket.timeout", 0);
  }

  public static int getSocketBufferSize(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getIntParameter("http.socket.buffer-size", -1);
  }

  public static boolean getTcpNoDelay(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getBooleanParameter("http.tcp.nodelay", true);
  }

  public static boolean isStaleCheckingEnabled(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getBooleanParameter("http.connection.stalecheck", true);
  }

  public static void setConnectionTimeout(HttpParams paramHttpParams, int paramInt)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setIntParameter("http.connection.timeout", paramInt);
  }

  public static void setLinger(HttpParams paramHttpParams, int paramInt)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setIntParameter("http.socket.linger", paramInt);
  }

  public static void setSoKeepalive(HttpParams paramHttpParams, boolean paramBoolean)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setBooleanParameter("http.socket.keepalive", paramBoolean);
  }

  public static void setSoReuseaddr(HttpParams paramHttpParams, boolean paramBoolean)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setBooleanParameter("http.socket.reuseaddr", paramBoolean);
  }

  public static void setSoTimeout(HttpParams paramHttpParams, int paramInt)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setIntParameter("http.socket.timeout", paramInt);
  }

  public static void setSocketBufferSize(HttpParams paramHttpParams, int paramInt)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setIntParameter("http.socket.buffer-size", paramInt);
  }

  public static void setStaleCheckingEnabled(HttpParams paramHttpParams, boolean paramBoolean)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setBooleanParameter("http.connection.stalecheck", paramBoolean);
  }

  public static void setTcpNoDelay(HttpParams paramHttpParams, boolean paramBoolean)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setBooleanParameter("http.tcp.nodelay", paramBoolean);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.params.HttpConnectionParams
 * JD-Core Version:    0.6.2
 */