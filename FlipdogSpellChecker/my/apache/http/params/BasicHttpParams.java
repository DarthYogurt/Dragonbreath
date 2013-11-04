package my.apache.http.params;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import my.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class BasicHttpParams extends AbstractHttpParams
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = -7086398485908701455L;
  private final HashMap<String, Object> parameters = new HashMap();

  public void clear()
  {
    this.parameters.clear();
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    BasicHttpParams localBasicHttpParams = (BasicHttpParams)super.clone();
    copyParams(localBasicHttpParams);
    return localBasicHttpParams;
  }

  @Deprecated
  public HttpParams copy()
  {
    try
    {
      HttpParams localHttpParams = (HttpParams)clone();
      return localHttpParams;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
    }
    throw new UnsupportedOperationException("Cloning not supported");
  }

  public void copyParams(HttpParams paramHttpParams)
  {
    Iterator localIterator = this.parameters.entrySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if ((localEntry.getKey() instanceof String))
        paramHttpParams.setParameter((String)localEntry.getKey(), localEntry.getValue());
    }
  }

  public Set<String> getNames()
  {
    return new HashSet(this.parameters.keySet());
  }

  public Object getParameter(String paramString)
  {
    return this.parameters.get(paramString);
  }

  public boolean isParameterSet(String paramString)
  {
    return getParameter(paramString) != null;
  }

  public boolean isParameterSetLocally(String paramString)
  {
    return this.parameters.get(paramString) != null;
  }

  public boolean removeParameter(String paramString)
  {
    if (this.parameters.containsKey(paramString))
    {
      this.parameters.remove(paramString);
      return true;
    }
    return false;
  }

  public HttpParams setParameter(String paramString, Object paramObject)
  {
    this.parameters.put(paramString, paramObject);
    return this;
  }

  public void setParameters(String[] paramArrayOfString, Object paramObject)
  {
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayOfString.length)
        return;
      setParameter(paramArrayOfString[i], paramObject);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.params.BasicHttpParams
 * JD-Core Version:    0.6.2
 */