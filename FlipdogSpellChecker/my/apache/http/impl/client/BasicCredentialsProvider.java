package my.apache.http.impl.client;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import my.apache.http.annotation.ThreadSafe;
import my.apache.http.auth.AuthScope;
import my.apache.http.auth.Credentials;
import my.apache.http.client.CredentialsProvider;

@ThreadSafe
public class BasicCredentialsProvider
  implements CredentialsProvider
{
  private final ConcurrentHashMap<AuthScope, Credentials> credMap = new ConcurrentHashMap();

  private static Credentials matchCredentials(Map<AuthScope, Credentials> paramMap, AuthScope paramAuthScope)
  {
    Credentials localCredentials = (Credentials)paramMap.get(paramAuthScope);
    int i;
    Object localObject;
    Iterator localIterator;
    if (localCredentials == null)
    {
      i = -1;
      localObject = null;
      localIterator = paramMap.keySet().iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if (localObject != null)
          localCredentials = (Credentials)paramMap.get(localObject);
        return localCredentials;
      }
      AuthScope localAuthScope = (AuthScope)localIterator.next();
      int j = paramAuthScope.match(localAuthScope);
      if (j > i)
      {
        i = j;
        localObject = localAuthScope;
      }
    }
  }

  public void clear()
  {
    this.credMap.clear();
  }

  public Credentials getCredentials(AuthScope paramAuthScope)
  {
    if (paramAuthScope == null)
      throw new IllegalArgumentException("Authentication scope may not be null");
    return matchCredentials(this.credMap, paramAuthScope);
  }

  public void setCredentials(AuthScope paramAuthScope, Credentials paramCredentials)
  {
    if (paramAuthScope == null)
      throw new IllegalArgumentException("Authentication scope may not be null");
    this.credMap.put(paramAuthScope, paramCredentials);
  }

  public String toString()
  {
    return this.credMap.toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.BasicCredentialsProvider
 * JD-Core Version:    0.6.2
 */