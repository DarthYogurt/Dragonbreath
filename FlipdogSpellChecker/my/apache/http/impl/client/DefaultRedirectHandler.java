package my.apache.http.impl.client;

import my.apache.http.HttpRequest;
import my.apache.http.HttpResponse;
import my.apache.http.RequestLine;
import my.apache.http.StatusLine;
import my.apache.http.annotation.Immutable;
import my.apache.http.client.RedirectHandler;
import my.apache.http.protocol.HttpContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Deprecated
@Immutable
public class DefaultRedirectHandler
  implements RedirectHandler
{
  private static final String REDIRECT_LOCATIONS = "http.protocol.redirect-locations";
  private final Log log = LogFactory.getLog(getClass());

  // ERROR //
  public java.net.URI getLocationURI(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws my.apache.http.ProtocolException
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +13 -> 14
    //   4: new 38	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 40
    //   10: invokespecial 43	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_1
    //   15: ldc 45
    //   17: invokeinterface 51 2 0
    //   22: astore_3
    //   23: aload_3
    //   24: ifnonnull +37 -> 61
    //   27: new 34	my/apache/http/ProtocolException
    //   30: dup
    //   31: new 53	java/lang/StringBuilder
    //   34: dup
    //   35: ldc 55
    //   37: invokespecial 56	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   40: aload_1
    //   41: invokeinterface 60 1 0
    //   46: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   49: ldc 66
    //   51: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   57: invokespecial 74	my/apache/http/ProtocolException:<init>	(Ljava/lang/String;)V
    //   60: athrow
    //   61: aload_3
    //   62: invokeinterface 79 1 0
    //   67: astore 4
    //   69: aload_0
    //   70: getfield 30	my/apache/http/impl/client/DefaultRedirectHandler:log	Lorg/apache/commons/logging/Log;
    //   73: invokeinterface 85 1 0
    //   78: ifeq +34 -> 112
    //   81: aload_0
    //   82: getfield 30	my/apache/http/impl/client/DefaultRedirectHandler:log	Lorg/apache/commons/logging/Log;
    //   85: new 53	java/lang/StringBuilder
    //   88: dup
    //   89: ldc 87
    //   91: invokespecial 56	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   94: aload 4
    //   96: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: ldc 89
    //   101: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   107: invokeinterface 93 2 0
    //   112: new 95	java/net/URI
    //   115: dup
    //   116: aload 4
    //   118: invokespecial 96	java/net/URI:<init>	(Ljava/lang/String;)V
    //   121: astore 5
    //   123: aload_1
    //   124: invokeinterface 100 1 0
    //   129: astore 6
    //   131: aload 5
    //   133: invokevirtual 103	java/net/URI:isAbsolute	()Z
    //   136: ifne +151 -> 287
    //   139: aload 6
    //   141: ldc 105
    //   143: invokeinterface 111 2 0
    //   148: ifeq +62 -> 210
    //   151: new 34	my/apache/http/ProtocolException
    //   154: dup
    //   155: new 53	java/lang/StringBuilder
    //   158: dup
    //   159: ldc 113
    //   161: invokespecial 56	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   164: aload 5
    //   166: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   169: ldc 115
    //   171: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   177: invokespecial 74	my/apache/http/ProtocolException:<init>	(Ljava/lang/String;)V
    //   180: athrow
    //   181: astore 15
    //   183: new 34	my/apache/http/ProtocolException
    //   186: dup
    //   187: new 53	java/lang/StringBuilder
    //   190: dup
    //   191: ldc 117
    //   193: invokespecial 56	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   196: aload 4
    //   198: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   201: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   204: aload 15
    //   206: invokespecial 120	my/apache/http/ProtocolException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   209: athrow
    //   210: aload_2
    //   211: ldc 122
    //   213: invokeinterface 128 2 0
    //   218: checkcast 130	my/apache/http/HttpHost
    //   221: astore 11
    //   223: aload 11
    //   225: ifnonnull +13 -> 238
    //   228: new 132	java/lang/IllegalStateException
    //   231: dup
    //   232: ldc 134
    //   234: invokespecial 135	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   237: athrow
    //   238: aload_2
    //   239: ldc 137
    //   241: invokeinterface 128 2 0
    //   246: checkcast 139	my/apache/http/HttpRequest
    //   249: astore 12
    //   251: new 95	java/net/URI
    //   254: dup
    //   255: aload 12
    //   257: invokeinterface 143 1 0
    //   262: invokeinterface 148 1 0
    //   267: invokespecial 96	java/net/URI:<init>	(Ljava/lang/String;)V
    //   270: aload 11
    //   272: iconst_1
    //   273: invokestatic 154	my/apache/http/client/utils/URIUtils:rewriteURI	(Ljava/net/URI;Lmy/apache/http/HttpHost;Z)Ljava/net/URI;
    //   276: aload 5
    //   278: invokestatic 158	my/apache/http/client/utils/URIUtils:resolve	(Ljava/net/URI;Ljava/net/URI;)Ljava/net/URI;
    //   281: astore 14
    //   283: aload 14
    //   285: astore 5
    //   287: aload 6
    //   289: ldc 160
    //   291: invokeinterface 163 2 0
    //   296: ifeq +170 -> 466
    //   299: aload_2
    //   300: ldc 12
    //   302: invokeinterface 128 2 0
    //   307: checkcast 165	my/apache/http/impl/client/RedirectLocations
    //   310: astore 7
    //   312: aload 7
    //   314: ifnonnull +22 -> 336
    //   317: new 165	my/apache/http/impl/client/RedirectLocations
    //   320: dup
    //   321: invokespecial 166	my/apache/http/impl/client/RedirectLocations:<init>	()V
    //   324: astore 7
    //   326: aload_2
    //   327: ldc 12
    //   329: aload 7
    //   331: invokeinterface 170 3 0
    //   336: aload 5
    //   338: invokevirtual 173	java/net/URI:getFragment	()Ljava/lang/String;
    //   341: ifnull +111 -> 452
    //   344: aload 5
    //   346: new 130	my/apache/http/HttpHost
    //   349: dup
    //   350: aload 5
    //   352: invokevirtual 176	java/net/URI:getHost	()Ljava/lang/String;
    //   355: aload 5
    //   357: invokevirtual 180	java/net/URI:getPort	()I
    //   360: aload 5
    //   362: invokevirtual 183	java/net/URI:getScheme	()Ljava/lang/String;
    //   365: invokespecial 186	my/apache/http/HttpHost:<init>	(Ljava/lang/String;ILjava/lang/String;)V
    //   368: iconst_1
    //   369: invokestatic 154	my/apache/http/client/utils/URIUtils:rewriteURI	(Ljava/net/URI;Lmy/apache/http/HttpHost;Z)Ljava/net/URI;
    //   372: astore 10
    //   374: aload 10
    //   376: astore 8
    //   378: aload 7
    //   380: aload 8
    //   382: invokevirtual 190	my/apache/http/impl/client/RedirectLocations:contains	(Ljava/net/URI;)Z
    //   385: ifeq +74 -> 459
    //   388: new 192	my/apache/http/client/CircularRedirectException
    //   391: dup
    //   392: new 53	java/lang/StringBuilder
    //   395: dup
    //   396: ldc 194
    //   398: invokespecial 56	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   401: aload 8
    //   403: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   406: ldc 89
    //   408: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   411: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   414: invokespecial 195	my/apache/http/client/CircularRedirectException:<init>	(Ljava/lang/String;)V
    //   417: athrow
    //   418: astore 13
    //   420: new 34	my/apache/http/ProtocolException
    //   423: dup
    //   424: aload 13
    //   426: invokevirtual 198	java/net/URISyntaxException:getMessage	()Ljava/lang/String;
    //   429: aload 13
    //   431: invokespecial 120	my/apache/http/ProtocolException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   434: athrow
    //   435: astore 9
    //   437: new 34	my/apache/http/ProtocolException
    //   440: dup
    //   441: aload 9
    //   443: invokevirtual 198	java/net/URISyntaxException:getMessage	()Ljava/lang/String;
    //   446: aload 9
    //   448: invokespecial 120	my/apache/http/ProtocolException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   451: athrow
    //   452: aload 5
    //   454: astore 8
    //   456: goto -78 -> 378
    //   459: aload 7
    //   461: aload 8
    //   463: invokevirtual 202	my/apache/http/impl/client/RedirectLocations:add	(Ljava/net/URI;)V
    //   466: aload 5
    //   468: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   112	123	181	java/net/URISyntaxException
    //   251	283	418	java/net/URISyntaxException
    //   344	374	435	java/net/URISyntaxException
  }

  public boolean isRedirectRequested(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    switch (paramHttpResponse.getStatusLine().getStatusCode())
    {
    case 304:
    case 305:
    case 306:
    default:
    case 301:
    case 302:
    case 307:
      String str;
      do
      {
        return false;
        str = ((HttpRequest)paramHttpContext.getAttribute("http.request")).getRequestLine().getMethod();
      }
      while ((!str.equalsIgnoreCase("GET")) && (!str.equalsIgnoreCase("HEAD")));
      return true;
    case 303:
    }
    return true;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.DefaultRedirectHandler
 * JD-Core Version:    0.6.2
 */