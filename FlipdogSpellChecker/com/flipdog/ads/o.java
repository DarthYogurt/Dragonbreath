package com.flipdog.ads;

import android.location.Location;
import com.adwhirl.AdWhirlTargeting;
import com.adwhirl.AdWhirlTargeting.TargetingGender;
import com.flipdog.ads.b.b;
import com.flipdog.commons.a.as;
import com.flipdog.commons.a.ba;
import java.util.Date;

public class o
{
  private static Integer a;

  public static void a()
  {
    b localb = b.b();
    a(localb.e());
    a(localb);
    a(localb.j());
    a(localb.i());
  }

  private static void a(b paramb)
  {
    if (paramb.h() == 0.0F)
      return;
    String str = paramb.l();
    if (as.d(str))
      str = "gps";
    Location localLocation = new Location(str);
    localLocation.setLongitude(paramb.h());
    localLocation.setLatitude(paramb.d());
    long l = paramb.k();
    if (l == 0L)
      l = new Date().getTime();
    localLocation.setTime(l);
    AdWhirlTargeting.setLocation(localLocation);
  }

  private static void a(Boolean paramBoolean)
  {
    if (paramBoolean == null)
      return;
    if (paramBoolean.booleanValue())
    {
      AdWhirlTargeting.setGender(AdWhirlTargeting.TargetingGender.FEMALE);
      return;
    }
    AdWhirlTargeting.setGender(AdWhirlTargeting.TargetingGender.MALE);
  }

  private static void a(Integer paramInteger)
  {
    if (paramInteger == null);
    do
    {
      return;
      if (a == null)
        a = Integer.valueOf(ba.b(paramInteger.intValue()));
    }
    while ((a.intValue() < 10) && (a.intValue() > 100));
    AdWhirlTargeting.setAge(a.intValue());
  }

  private static void a(String paramString)
  {
    if (paramString != null)
      AdWhirlTargeting.setPostalCode(paramString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.ads.o
 * JD-Core Version:    0.6.2
 */