package my.apache.http.client.params;

import my.apache.http.auth.params.AuthPNames;
import my.apache.http.conn.params.ConnConnectionPNames;
import my.apache.http.conn.params.ConnManagerPNames;
import my.apache.http.conn.params.ConnRoutePNames;
import my.apache.http.cookie.params.CookieSpecPNames;
import my.apache.http.params.CoreConnectionPNames;
import my.apache.http.params.CoreProtocolPNames;

public abstract interface AllClientPNames extends CoreConnectionPNames, CoreProtocolPNames, ClientPNames, AuthPNames, CookieSpecPNames, ConnConnectionPNames, ConnManagerPNames, ConnRoutePNames
{
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.params.AllClientPNames
 * JD-Core Version:    0.6.2
 */