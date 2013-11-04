package my.apache.http.conn.routing;

public abstract interface HttpRouteDirector
{
  public static final int COMPLETE = 0;
  public static final int CONNECT_PROXY = 2;
  public static final int CONNECT_TARGET = 1;
  public static final int LAYER_PROTOCOL = 5;
  public static final int TUNNEL_PROXY = 4;
  public static final int TUNNEL_TARGET = 3;
  public static final int UNREACHABLE = -1;

  public abstract int nextStep(RouteInfo paramRouteInfo1, RouteInfo paramRouteInfo2);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.routing.HttpRouteDirector
 * JD-Core Version:    0.6.2
 */