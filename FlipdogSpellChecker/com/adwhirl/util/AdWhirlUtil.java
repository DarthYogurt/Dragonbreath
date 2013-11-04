package com.adwhirl.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class AdWhirlUtil
{
  public static final String ADWHIRL = "AdWhirl SDK";
  public static final int CUSTOM_TYPE_BANNER = 1;
  public static final int CUSTOM_TYPE_ICON = 2;
  public static final int NETWORK_TYPE_4THSCREEN = 13;
  public static final int NETWORK_TYPE_ADMOB = 1;
  public static final int NETWORK_TYPE_ADSENSE = 14;
  public static final int NETWORK_TYPE_ADWHIRL = 10;
  public static final int NETWORK_TYPE_CUSTOM = 9;
  public static final int NETWORK_TYPE_DOUBLECLICK = 15;
  public static final int NETWORK_TYPE_EVENT = 17;
  public static final int NETWORK_TYPE_GENERIC = 16;
  public static final int NETWORK_TYPE_GREYSTRIP = 7;
  public static final int NETWORK_TYPE_INMOBI = 18;
  public static final int NETWORK_TYPE_JUMPTAP = 2;
  public static final int NETWORK_TYPE_LIVERAIL = 5;
  public static final int NETWORK_TYPE_MDOTM = 12;
  public static final int NETWORK_TYPE_MEDIALETS = 4;
  public static final int NETWORK_TYPE_MILLENNIAL = 6;
  public static final int NETWORK_TYPE_MOBCLIX = 11;
  public static final int NETWORK_TYPE_NEXAGE = 24;
  public static final int NETWORK_TYPE_ONERIOT = 23;
  public static final int NETWORK_TYPE_QUATTRO = 8;
  public static final int NETWORK_TYPE_VIDEOEGG = 3;
  public static final int NETWORK_TYPE_ZESTADZ = 20;
  public static final int VERSION = 311;
  private static double density = 0.0D;
  public static final String locationString = "&location=%f,%f&location_timestamp=%d";
  public static final String urlClick = "http://met.adwhirl.com/exclick.php?appid=%s&nid=%s&type=%d&uuid=%s&country_code=%s&appver=%d&client=2";
  public static final String urlConfig = "http://mob.adwhirl.com/getInfo.php?appid=%s&appver=%d&client=2";
  public static final String urlCustom = "http://cus.adwhirl.com/custom.php?appid=%s&nid=%s&uuid=%s&country_code=%s%s&appver=%d&client=2";
  public static final String urlImpression = "http://met.adwhirl.com/exmet.php?appid=%s&nid=%s&type=%d&uuid=%s&country_code=%s&appver=%d&client=2";

  public static String convertToHex(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = paramArrayOfByte.length;
    int j = 0;
    if (j >= i)
      return localStringBuffer.toString();
    int k = paramArrayOfByte[j];
    int m = 0xF & k >>> 4;
    int i1;
    label105: for (int n = 0; ; n = i1)
    {
      if ((m >= 0) && (m <= 9))
        localStringBuffer.append((char)(m + 48));
      while (true)
      {
        m = k & 0xF;
        i1 = n + 1;
        if (n < 1)
          break label105;
        j++;
        break;
        localStringBuffer.append((char)(97 + (m - 10)));
      }
    }
  }

  public static double convertToScreenPixels(double paramDouble1, double paramDouble2)
  {
    if (paramDouble2 > 0.0D)
      paramDouble1 *= paramDouble2;
    return paramDouble1;
  }

  public static int convertToScreenPixels(int paramInt, double paramDouble)
  {
    return (int)convertToScreenPixels(paramInt, paramDouble);
  }

  public static double getDensity(Activity paramActivity)
  {
    if (density == -1.0D)
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      density = localDisplayMetrics.density;
    }
    return density;
  }

  public static String getEncodedDeviceId(Context paramContext)
  {
    String str1 = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    if ((str1 == null) || (isEmulator()));
    for (String str2 = md5("emulator"); str2 == null; str2 = md5(str1))
      return null;
    return str2.toUpperCase(Locale.US);
  }

  public static boolean isEmulator()
  {
    return (Build.BOARD.equals("unknown")) && (Build.DEVICE.equals("generic")) && (Build.BRAND.equals("generic"));
  }

  private static String md5(String paramString)
  {
    Object localObject = null;
    if (paramString != null)
    {
      int i = paramString.length();
      localObject = null;
      if (i <= 0);
    }
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramString.getBytes(), 0, paramString.length());
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = new BigInteger(1, localMessageDigest.digest());
      String str = String.format("%032X", arrayOfObject);
      localObject = str;
      return localObject;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
    }
    return paramString.substring(0, 32);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.adwhirl.util.AdWhirlUtil
 * JD-Core Version:    0.6.2
 */