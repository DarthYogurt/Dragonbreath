package my.apache.http.params;

import java.util.HashSet;
import java.util.Set;

public final class DefaultedHttpParams extends AbstractHttpParams
{
  private final HttpParams defaults;
  private final HttpParams local;

  public DefaultedHttpParams(HttpParams paramHttpParams1, HttpParams paramHttpParams2)
  {
    if (paramHttpParams1 == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.local = paramHttpParams1;
    this.defaults = paramHttpParams2;
  }

  private Set<String> getNames(HttpParams paramHttpParams)
  {
    if ((paramHttpParams instanceof HttpParamsNames))
      return ((HttpParamsNames)paramHttpParams).getNames();
    throw new UnsupportedOperationException("HttpParams instance does not implement HttpParamsNames");
  }

  @Deprecated
  public HttpParams copy()
  {
    return new DefaultedHttpParams(this.local.copy(), this.defaults);
  }

  public Set<String> getDefaultNames()
  {
    return new HashSet(getNames(this.defaults));
  }

  @Deprecated
  public HttpParams getDefaults()
  {
    return this.defaults;
  }

  public Set<String> getLocalNames()
  {
    return new HashSet(getNames(this.local));
  }

  public Set<String> getNames()
  {
    HashSet localHashSet = new HashSet(getNames(this.defaults));
    localHashSet.addAll(getNames(this.local));
    return localHashSet;
  }

  public Object getParameter(String paramString)
  {
    Object localObject = this.local.getParameter(paramString);
    if ((localObject == null) && (this.defaults != null))
      localObject = this.defaults.getParameter(paramString);
    return localObject;
  }

  public boolean removeParameter(String paramString)
  {
    return this.local.removeParameter(paramString);
  }

  public HttpParams setParameter(String paramString, Object paramObject)
  {
    return this.local.setParameter(paramString, paramObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.params.DefaultedHttpParams
 * JD-Core Version:    0.6.2
 */