package my.apache.http.client.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import my.apache.http.annotation.Immutable;

@Immutable
public class JdkIdn
  implements Idn
{
  private final Method toUnicode;

  public JdkIdn()
    throws ClassNotFoundException
  {
    Class localClass = Class.forName("java.net.IDN");
    try
    {
      this.toUnicode = localClass.getMethod("toUnicode", new Class[] { String.class });
      return;
    }
    catch (SecurityException localSecurityException)
    {
      throw new IllegalStateException(localSecurityException.getMessage(), localSecurityException);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new IllegalStateException(localNoSuchMethodException.getMessage(), localNoSuchMethodException);
    }
  }

  public String toUnicode(String paramString)
  {
    try
    {
      String str = (String)this.toUnicode.invoke(null, new Object[] { paramString });
      return str;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new IllegalStateException(localIllegalAccessException.getMessage(), localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      Throwable localThrowable = localInvocationTargetException.getCause();
      throw new RuntimeException(localThrowable.getMessage(), localThrowable);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.utils.JdkIdn
 * JD-Core Version:    0.6.2
 */