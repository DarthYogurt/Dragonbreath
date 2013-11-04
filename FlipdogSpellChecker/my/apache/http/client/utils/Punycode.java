package my.apache.http.client.utils;

import my.apache.http.annotation.Immutable;

@Immutable
public class Punycode
{
  private static final Idn impl;

  static
  {
    try
    {
      localObject = new JdkIdn();
      impl = (Idn)localObject;
      return;
    }
    catch (Exception localException)
    {
      while (true)
        Object localObject = new Rfc3492Idn();
    }
  }

  public static String toUnicode(String paramString)
  {
    return impl.toUnicode(paramString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.utils.Punycode
 * JD-Core Version:    0.6.2
 */