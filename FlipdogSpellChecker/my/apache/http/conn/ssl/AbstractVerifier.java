package my.apache.http.conn.ssl;

import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.security.auth.x500.X500Principal;
import my.apache.http.annotation.Immutable;
import my.apache.http.conn.util.InetAddressUtils;

@Immutable
public abstract class AbstractVerifier
  implements X509HostnameVerifier
{
  private static final String[] BAD_COUNTRY_2LDS = { "ac", "co", "com", "ed", "edu", "go", "gouv", "gov", "info", "lg", "ne", "net", "or", "org" };

  static
  {
    Arrays.sort(BAD_COUNTRY_2LDS);
  }

  public static boolean acceptableCountryWildcard(String paramString)
  {
    String[] arrayOfString = paramString.split("\\.");
    if ((arrayOfString.length != 3) || (arrayOfString[2].length() != 2));
    while (Arrays.binarySearch(BAD_COUNTRY_2LDS, arrayOfString[1]) < 0)
      return true;
    return false;
  }

  public static int countDots(String paramString)
  {
    int i = 0;
    for (int j = 0; ; j++)
    {
      if (j >= paramString.length())
        return i;
      if (paramString.charAt(j) == '.')
        i++;
    }
  }

  public static String[] getCNs(X509Certificate paramX509Certificate)
  {
    LinkedList localLinkedList = new LinkedList();
    StringTokenizer localStringTokenizer = new StringTokenizer(paramX509Certificate.getSubjectX500Principal().toString(), ",");
    while (true)
    {
      if (!localStringTokenizer.hasMoreTokens())
      {
        if (localLinkedList.isEmpty())
          break;
        String[] arrayOfString = new String[localLinkedList.size()];
        localLinkedList.toArray(arrayOfString);
        return arrayOfString;
      }
      String str = localStringTokenizer.nextToken().trim();
      if ((str.length() > 3) && (str.substring(0, 3).equalsIgnoreCase("CN=")))
        localLinkedList.add(str.substring(3));
    }
    return null;
  }

  public static String[] getDNSSubjectAlts(X509Certificate paramX509Certificate)
  {
    return getSubjectAlts(paramX509Certificate, null);
  }

  private static String[] getSubjectAlts(X509Certificate paramX509Certificate, String paramString)
  {
    int i;
    if (isIPAddress(paramString))
      i = 7;
    while (true)
    {
      LinkedList localLinkedList = new LinkedList();
      try
      {
        Collection localCollection2 = paramX509Certificate.getSubjectAlternativeNames();
        localCollection1 = localCollection2;
        Iterator localIterator;
        if (localCollection1 != null)
          localIterator = localCollection1.iterator();
        while (true)
        {
          if (!localIterator.hasNext())
          {
            if (localLinkedList.isEmpty())
              break label132;
            String[] arrayOfString = new String[localLinkedList.size()];
            localLinkedList.toArray(arrayOfString);
            return arrayOfString;
            i = 2;
            break;
          }
          List localList = (List)localIterator.next();
          if (((Integer)localList.get(0)).intValue() == i)
            localLinkedList.add((String)localList.get(1));
        }
        label132: return null;
      }
      catch (CertificateParsingException localCertificateParsingException)
      {
        while (true)
          Collection localCollection1 = null;
      }
    }
  }

  private static boolean isIPAddress(String paramString)
  {
    return (paramString != null) && ((InetAddressUtils.isIPv4Address(paramString)) || (InetAddressUtils.isIPv6Address(paramString)));
  }

  public final void verify(String paramString, X509Certificate paramX509Certificate)
    throws SSLException
  {
    verify(paramString, getCNs(paramX509Certificate), getSubjectAlts(paramX509Certificate, paramString));
  }

  public final void verify(String paramString, SSLSocket paramSSLSocket)
    throws IOException
  {
    if (paramString == null)
      throw new NullPointerException("host to verify is null");
    SSLSession localSSLSession = paramSSLSocket.getSession();
    if (localSSLSession == null)
    {
      paramSSLSocket.getInputStream().available();
      localSSLSession = paramSSLSocket.getSession();
      if (localSSLSession == null)
      {
        paramSSLSocket.startHandshake();
        localSSLSession = paramSSLSocket.getSession();
      }
    }
    verify(paramString, (X509Certificate)localSSLSession.getPeerCertificates()[0]);
  }

  public final void verify(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, boolean paramBoolean)
    throws SSLException
  {
    LinkedList localLinkedList = new LinkedList();
    if ((paramArrayOfString1 != null) && (paramArrayOfString1.length > 0) && (paramArrayOfString1[0] != null))
      localLinkedList.add(paramArrayOfString1[0]);
    int j;
    if (paramArrayOfString2 != null)
      j = paramArrayOfString2.length;
    for (int k = 0; ; k++)
    {
      if (k >= j)
      {
        if (!localLinkedList.isEmpty())
          break;
        String str7 = "Certificate for <" + paramString + "> doesn't contain CN or DNS subjectAlt";
        SSLException localSSLException = new SSLException(str7);
        throw localSSLException;
      }
      String str8 = paramArrayOfString2[k];
      if (str8 != null)
        localLinkedList.add(str8);
    }
    StringBuilder localStringBuilder = new StringBuilder();
    String str1 = paramString.trim().toLowerCase(Locale.US);
    boolean bool = false;
    Iterator localIterator = localLinkedList.iterator();
    label311: label455: label461: label471: 
    while (true)
      if (!localIterator.hasNext())
      {
        if (!bool)
          throw new SSLException("hostname in certificate didn't match: <" + paramString + "> !=" + localStringBuilder);
      }
      else
      {
        String str2 = ((String)localIterator.next()).toLowerCase(Locale.US);
        localStringBuilder.append(" <");
        localStringBuilder.append(str2);
        localStringBuilder.append('>');
        if (localIterator.hasNext())
          localStringBuilder.append(" OR");
        String[] arrayOfString = str2.split("\\.");
        int i;
        if ((arrayOfString.length >= 3) && (arrayOfString[0].endsWith("*")) && (acceptableCountryWildcard(str2)) && (!isIPAddress(paramString)))
        {
          i = 1;
          if (i == 0)
            break label461;
          String str3 = arrayOfString[0];
          if (str3.length() <= 1)
            break label439;
          String str4 = str3.substring(0, -1 + str3.length());
          String str5 = str2.substring(str3.length());
          String str6 = str1.substring(str4.length());
          if ((!str1.startsWith(str4)) || (!str6.endsWith(str5)))
            break label433;
          bool = true;
          if ((bool) && (paramBoolean))
          {
            if (countDots(str1) != countDots(str2))
              break label455;
            bool = true;
          }
        }
        while (true)
        {
          if (!bool)
            break label471;
          break;
          i = 0;
          break label311;
          bool = false;
          break label393;
          bool = str1.endsWith(str2.substring(1));
          break label393;
          bool = false;
          continue;
          bool = str1.equals(str2);
        }
      }
    label393: label433: label439: return;
  }

  public final boolean verify(String paramString, SSLSession paramSSLSession)
  {
    try
    {
      verify(paramString, (X509Certificate)paramSSLSession.getPeerCertificates()[0]);
      return true;
    }
    catch (SSLException localSSLException)
    {
    }
    return false;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.ssl.AbstractVerifier
 * JD-Core Version:    0.6.2
 */