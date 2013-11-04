package my.apache.http.impl.client;

import my.apache.http.annotation.Immutable;

@Immutable
public class LaxRedirectStrategy extends DefaultRedirectStrategy
{
  private static final String[] REDIRECT_METHODS = { "GET", "POST", "HEAD" };

  protected boolean isRedirectable(String paramString)
  {
    String[] arrayOfString = REDIRECT_METHODS;
    int i = arrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return false;
      if (arrayOfString[j].equalsIgnoreCase(paramString))
        return true;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.LaxRedirectStrategy
 * JD-Core Version:    0.6.2
 */