package my.apache.http.conn.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import my.apache.http.annotation.Immutable;

@Immutable
public class InetAddressUtils
{
  private static final char COLON_CHAR = ':';
  private static final Pattern IPV4_PATTERN = Pattern.compile("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$");
  private static final Pattern IPV6_HEX_COMPRESSED_PATTERN = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");
  private static final Pattern IPV6_STD_PATTERN = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
  private static final int MAX_COLON_COUNT = 7;

  public static boolean isIPv4Address(String paramString)
  {
    return IPV4_PATTERN.matcher(paramString).matches();
  }

  public static boolean isIPv6Address(String paramString)
  {
    return (isIPv6StdAddress(paramString)) || (isIPv6HexCompressedAddress(paramString));
  }

  public static boolean isIPv6HexCompressedAddress(String paramString)
  {
    int i = 0;
    for (int j = 0; ; j++)
    {
      if (j >= paramString.length())
      {
        if ((i > 7) || (!IPV6_HEX_COMPRESSED_PATTERN.matcher(paramString).matches()))
          break;
        return true;
      }
      if (paramString.charAt(j) == ':')
        i++;
    }
    return false;
  }

  public static boolean isIPv6StdAddress(String paramString)
  {
    return IPV6_STD_PATTERN.matcher(paramString).matches();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.util.InetAddressUtils
 * JD-Core Version:    0.6.2
 */