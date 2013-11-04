package com.flipdog.ads.c;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import com.flipdog.commons.a.ba;
import com.google.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class d
{
  private LocationManager a;
  private boolean b;

  @Inject
  public d(Context paramContext)
  {
    this.a = ((LocationManager)paramContext.getSystemService("location"));
  }

  private long a(com.flipdog.ads.b.b paramb)
  {
    if (paramb.g() == null)
      return 9223372036854775807L;
    return ba.d(ba.a(), paramb.g());
  }

  private String a(double paramDouble1, double paramDouble2)
    throws MalformedURLException, IOException, XmlPullParserException
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Double.valueOf(paramDouble1);
    arrayOfObject[1] = Double.valueOf(paramDouble2);
    URLConnection localURLConnection = new URL(String.format("http://ws.geonames.org/findNearbyPostalCodes?lat=%s&lng=%s", arrayOfObject)).openConnection();
    localURLConnection.setDoOutput(true);
    InputStream localInputStream = localURLConnection.getInputStream();
    try
    {
      String str = a(localInputStream);
      return str;
    }
    finally
    {
      localInputStream.close();
    }
  }

  private static String a(InputStream paramInputStream)
    throws XmlPullParserException, IOException
  {
    XmlPullParser localXmlPullParser = XmlPullParserFactory.newInstance().newPullParser();
    localXmlPullParser.setInput(paramInputStream, null);
    a locala = new a();
    locala.c(localXmlPullParser);
    ArrayList localArrayList = locala.a();
    if (localArrayList.size() == 0)
      return null;
    return (String)localArrayList.get(0);
  }

  private void a(com.flipdog.ads.b.b paramb, Location paramLocation)
  {
    paramb.a((float)paramLocation.getLatitude());
    paramb.b((float)paramLocation.getLongitude());
    paramb.a(paramLocation.getTime());
    paramb.b(paramLocation.getProvider());
  }

  private boolean b(com.flipdog.ads.b.b paramb)
  {
    if (paramb.f() == null);
    while (ba.d(ba.a(), paramb.f()) > 604800000L)
      return true;
    return false;
  }

  private Location c()
  {
    Location localLocation = this.a.getLastKnownLocation("gps");
    Iterator localIterator;
    if (localLocation == null)
      localIterator = this.a.getAllProviders().iterator();
    do
    {
      if (!localIterator.hasNext())
        return localLocation;
      String str = (String)localIterator.next();
      localLocation = this.a.getLastKnownLocation(str);
    }
    while (localLocation == null);
    return localLocation;
  }

  private boolean d()
  {
    com.flipdog.ads.b.b localb = com.flipdog.ads.b.b.b();
    return (b(localb)) && (a(localb) > 3600000L);
  }

  public void a()
  {
    if (!this.b)
      com.flipdog.commons.f.c.a(new b(this));
    if (!d())
      return;
    com.flipdog.commons.f.c.a(new c(this));
  }

  protected void a(Location paramLocation)
  {
    if (paramLocation == null)
      return;
    com.flipdog.ads.b.b localb = com.flipdog.ads.b.b.b();
    a(localb, paramLocation);
    localb.c();
  }

  // ERROR //
  protected void b()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 48	com/flipdog/ads/c/d:c	()Landroid/location/Location;
    //   4: astore 11
    //   6: aload 11
    //   8: astore 4
    //   10: aload 4
    //   12: ifnull +156 -> 168
    //   15: iconst_2
    //   16: anewarray 4	java/lang/Object
    //   19: astore 17
    //   21: aload 17
    //   23: iconst_0
    //   24: aload 4
    //   26: invokevirtual 138	android/location/Location:getLatitude	()D
    //   29: invokestatic 61	java/lang/Double:valueOf	(D)Ljava/lang/Double;
    //   32: aastore
    //   33: aload 17
    //   35: iconst_1
    //   36: aload 4
    //   38: invokevirtual 144	android/location/Location:getLongitude	()D
    //   41: invokestatic 61	java/lang/Double:valueOf	(D)Ljava/lang/Double;
    //   44: aastore
    //   45: ldc 227
    //   47: aload 17
    //   49: invokestatic 71	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   52: iconst_1
    //   53: anewarray 67	java/lang/String
    //   56: dup
    //   57: iconst_0
    //   58: ldc 229
    //   60: aastore
    //   61: invokestatic 235	com/flipdog/commons/diagnostic/Track:it	(Ljava/lang/String;[Ljava/lang/String;)V
    //   64: aload_0
    //   65: aload 4
    //   67: invokevirtual 138	android/location/Location:getLatitude	()D
    //   70: aload 4
    //   72: invokevirtual 144	android/location/Location:getLongitude	()D
    //   75: invokespecial 237	com/flipdog/ads/c/d:a	(DD)Ljava/lang/String;
    //   78: astore 18
    //   80: aload 18
    //   82: astore 14
    //   84: new 239	java/lang/StringBuilder
    //   87: dup
    //   88: ldc 241
    //   90: invokespecial 242	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   93: aload 14
    //   95: invokevirtual 246	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: invokevirtual 249	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   101: iconst_1
    //   102: anewarray 67	java/lang/String
    //   105: dup
    //   106: iconst_0
    //   107: ldc 229
    //   109: aastore
    //   110: invokestatic 235	com/flipdog/commons/diagnostic/Track:it	(Ljava/lang/String;[Ljava/lang/String;)V
    //   113: invokestatic 197	com/flipdog/ads/b/b:b	()Lcom/flipdog/ads/b/b;
    //   116: astore 15
    //   118: invokestatic 39	com/flipdog/commons/a/ba:a	()Ljava/util/Date;
    //   121: astore 16
    //   123: aload 4
    //   125: ifnull +11 -> 136
    //   128: aload_0
    //   129: aload 15
    //   131: aload 4
    //   133: invokespecial 221	com/flipdog/ads/c/d:a	(Lcom/flipdog/ads/b/b;Landroid/location/Location;)V
    //   136: aload 14
    //   138: ifnull +17 -> 155
    //   141: aload 15
    //   143: aload 14
    //   145: invokevirtual 251	com/flipdog/ads/b/b:a	(Ljava/lang/String;)V
    //   148: aload 15
    //   150: aload 16
    //   152: invokevirtual 254	com/flipdog/ads/b/b:a	(Ljava/util/Date;)V
    //   155: aload 15
    //   157: aload 16
    //   159: invokevirtual 256	com/flipdog/ads/b/b:b	(Ljava/util/Date;)V
    //   162: aload 15
    //   164: invokevirtual 223	com/flipdog/ads/b/b:c	()V
    //   167: return
    //   168: ldc_w 258
    //   171: iconst_1
    //   172: anewarray 67	java/lang/String
    //   175: dup
    //   176: iconst_0
    //   177: ldc 229
    //   179: aastore
    //   180: invokestatic 235	com/flipdog/commons/diagnostic/Track:it	(Ljava/lang/String;[Ljava/lang/String;)V
    //   183: aconst_null
    //   184: astore 14
    //   186: goto -73 -> 113
    //   189: astore 13
    //   191: aconst_null
    //   192: astore_3
    //   193: aload 13
    //   195: astore_2
    //   196: aload_2
    //   197: invokestatic 261	com/flipdog/commons/diagnostic/Track:it	(Ljava/lang/Throwable;)V
    //   200: invokestatic 197	com/flipdog/ads/b/b:b	()Lcom/flipdog/ads/b/b;
    //   203: astore 8
    //   205: invokestatic 39	com/flipdog/commons/a/ba:a	()Ljava/util/Date;
    //   208: astore 9
    //   210: aload 4
    //   212: ifnull +11 -> 223
    //   215: aload_0
    //   216: aload 8
    //   218: aload 4
    //   220: invokespecial 221	com/flipdog/ads/c/d:a	(Lcom/flipdog/ads/b/b;Landroid/location/Location;)V
    //   223: aload_3
    //   224: ifnull +16 -> 240
    //   227: aload 8
    //   229: aload_3
    //   230: invokevirtual 251	com/flipdog/ads/b/b:a	(Ljava/lang/String;)V
    //   233: aload 8
    //   235: aload 9
    //   237: invokevirtual 254	com/flipdog/ads/b/b:a	(Ljava/util/Date;)V
    //   240: aload 8
    //   242: aload 9
    //   244: invokevirtual 256	com/flipdog/ads/b/b:b	(Ljava/util/Date;)V
    //   247: aload 8
    //   249: invokevirtual 223	com/flipdog/ads/b/b:c	()V
    //   252: return
    //   253: astore 10
    //   255: aconst_null
    //   256: astore 4
    //   258: aload 10
    //   260: astore 5
    //   262: aconst_null
    //   263: astore_3
    //   264: invokestatic 197	com/flipdog/ads/b/b:b	()Lcom/flipdog/ads/b/b;
    //   267: astore 6
    //   269: invokestatic 39	com/flipdog/commons/a/ba:a	()Ljava/util/Date;
    //   272: astore 7
    //   274: aload 4
    //   276: ifnull +11 -> 287
    //   279: aload_0
    //   280: aload 6
    //   282: aload 4
    //   284: invokespecial 221	com/flipdog/ads/c/d:a	(Lcom/flipdog/ads/b/b;Landroid/location/Location;)V
    //   287: aload_3
    //   288: ifnull +16 -> 304
    //   291: aload 6
    //   293: aload_3
    //   294: invokevirtual 251	com/flipdog/ads/b/b:a	(Ljava/lang/String;)V
    //   297: aload 6
    //   299: aload 7
    //   301: invokevirtual 254	com/flipdog/ads/b/b:a	(Ljava/util/Date;)V
    //   304: aload 6
    //   306: aload 7
    //   308: invokevirtual 256	com/flipdog/ads/b/b:b	(Ljava/util/Date;)V
    //   311: aload 6
    //   313: invokevirtual 223	com/flipdog/ads/b/b:c	()V
    //   316: aload 5
    //   318: athrow
    //   319: astore 12
    //   321: aload 12
    //   323: astore 5
    //   325: aconst_null
    //   326: astore_3
    //   327: goto -63 -> 264
    //   330: astore 20
    //   332: aload 14
    //   334: astore_3
    //   335: aload 20
    //   337: astore 5
    //   339: goto -75 -> 264
    //   342: astore 5
    //   344: goto -80 -> 264
    //   347: astore_1
    //   348: aload_1
    //   349: astore_2
    //   350: aconst_null
    //   351: astore_3
    //   352: aconst_null
    //   353: astore 4
    //   355: goto -159 -> 196
    //   358: astore 19
    //   360: aload 14
    //   362: astore_3
    //   363: aload 19
    //   365: astore_2
    //   366: goto -170 -> 196
    //
    // Exception table:
    //   from	to	target	type
    //   15	80	189	java/lang/Exception
    //   168	183	189	java/lang/Exception
    //   0	6	253	finally
    //   15	80	319	finally
    //   168	183	319	finally
    //   84	113	330	finally
    //   196	200	342	finally
    //   0	6	347	java/lang/Exception
    //   84	113	358	java/lang/Exception
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.ads.c.d
 * JD-Core Version:    0.6.2
 */