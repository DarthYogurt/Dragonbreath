package com.flipdog.commons.g;

import com.android.vending.licensing.a.a;
import com.android.vending.licensing.u;
import com.flipdog.commons.diagnostic.Track;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyFactory;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public class n
{
  private static final String a = "RSA";
  private static final String b = "SHA1withRSA";
  private static final String c = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6g7C7ku7wDnnWJFfoEYffIzmqPBEXWmvsgfZ+IPAm+NKWWyJLw0ynJ6ndPtCCw3rJgcwiDakCwo328rE1k0IqS3mbHfmkkKyuDoBLA+kSHQcTksJXLiaKxKuItwFPYzIHK1ZjNv7oMEbMaoQlgfLmuK1Jkv8idsnj2p4uucSz+xXEdy5yZNEBHYYwW3H4IX2hY29zTQrwalfoollLxUxD7f0t4719R5viN6faTRdtubVtM1Bq5B+mLeYSZghIwVQzakQeTFFqcQlBkxJ0mGhKBZlMSiWjSMIFVrhlCcnUmdzGhEDEsPvdlw24e3c8+ITf39fiazmRlifqey1jYoyJQIDAQAB";

  public static long a(Date paramDate)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(paramDate);
    return localCalendar.getTimeInMillis();
  }

  public static Date a(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    return localCalendar.getTime();
  }

  public static Date a(u paramu)
  {
    Date localDate1;
    if (paramu == null)
      localDate1 = new Date(1970, 1, 1);
    Date localDate2;
    do
    {
      return localDate1;
      localDate2 = c(paramu);
      localDate1 = new Date(259200000L + paramu.f);
    }
    while ((localDate2 == null) || (localDate2.getTime() >= localDate1.getTime()));
    return localDate2;
  }

  public static Map<String, String> a(String paramString)
  {
    HashMap localHashMap = new HashMap();
    try
    {
      Iterator localIterator = URLEncodedUtils.parse(new URI("?" + paramString), "UTF-8").iterator();
      while (true)
      {
        if (!localIterator.hasNext())
          return localHashMap;
        NameValuePair localNameValuePair = (NameValuePair)localIterator.next();
        localHashMap.put(localNameValuePair.getName(), localNameValuePair.getValue());
      }
    }
    catch (URISyntaxException localURISyntaxException)
    {
    }
    return localHashMap;
  }

  public static boolean a(b paramb)
  {
    try
    {
      boolean bool2 = a(paramb, "BC");
      return bool2;
    }
    catch (NoSuchProviderException localNoSuchProviderException)
    {
      boolean bool1 = a(paramb, null);
      return bool1;
    }
    catch (Exception localException)
    {
      Track.it(localException);
    }
    return false;
  }

  public static boolean a(b paramb, String paramString)
    throws Exception
  {
    byte[] arrayOfByte = a.a("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6g7C7ku7wDnnWJFfoEYffIzmqPBEXWmvsgfZ+IPAm+NKWWyJLw0ynJ6ndPtCCw3rJgcwiDakCwo328rE1k0IqS3mbHfmkkKyuDoBLA+kSHQcTksJXLiaKxKuItwFPYzIHK1ZjNv7oMEbMaoQlgfLmuK1Jkv8idsnj2p4uucSz+xXEdy5yZNEBHYYwW3H4IX2hY29zTQrwalfoollLxUxD7f0t4719R5viN6faTRdtubVtM1Bq5B+mLeYSZghIwVQzakQeTFFqcQlBkxJ0mGhKBZlMSiWjSMIFVrhlCcnUmdzGhEDEsPvdlw24e3c8+ITf39fiazmRlifqey1jYoyJQIDAQAB");
    KeyFactory localKeyFactory;
    PublicKey localPublicKey;
    if (paramString != null)
    {
      localKeyFactory = KeyFactory.getInstance("RSA", paramString);
      localPublicKey = localKeyFactory.generatePublic(new X509EncodedKeySpec(arrayOfByte));
      if (paramString == null)
        break label88;
    }
    label88: for (Signature localSignature = Signature.getInstance("SHA1withRSA", paramString); ; localSignature = Signature.getInstance("SHA1withRSA"))
    {
      localSignature.initVerify(localPublicKey);
      localSignature.update(paramb.a.getBytes());
      if (localSignature.verify(a.a(paramb.b)))
        break label98;
      return false;
      localKeyFactory = KeyFactory.getInstance("RSA");
      break;
    }
    label98: return true;
  }

  public static u b(String paramString)
  {
    if (paramString == null)
      return null;
    u localu = u.a(paramString);
    localu.h = a(localu.g);
    return localu;
  }

  public static boolean b(u paramu)
  {
    if (paramu == null);
    do
    {
      return false;
      if (paramu.a == 0)
        return true;
    }
    while (paramu.a != 2);
    return true;
  }

  private static Date c(u paramu)
  {
    String str = (String)a(paramu.g).get("VT");
    if (str == null)
      return null;
    try
    {
      long l = Long.parseLong(str);
      return a(l);
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    return null;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.g.n
 * JD-Core Version:    0.6.2
 */