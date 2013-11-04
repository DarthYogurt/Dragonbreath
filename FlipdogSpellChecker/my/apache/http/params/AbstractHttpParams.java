package my.apache.http.params;

import java.util.Set;

public abstract class AbstractHttpParams
  implements HttpParams, HttpParamsNames
{
  public boolean getBooleanParameter(String paramString, boolean paramBoolean)
  {
    Object localObject = getParameter(paramString);
    if (localObject == null)
      return paramBoolean;
    return ((Boolean)localObject).booleanValue();
  }

  public double getDoubleParameter(String paramString, double paramDouble)
  {
    Object localObject = getParameter(paramString);
    if (localObject == null)
      return paramDouble;
    return ((Double)localObject).doubleValue();
  }

  public int getIntParameter(String paramString, int paramInt)
  {
    Object localObject = getParameter(paramString);
    if (localObject == null)
      return paramInt;
    return ((Integer)localObject).intValue();
  }

  public long getLongParameter(String paramString, long paramLong)
  {
    Object localObject = getParameter(paramString);
    if (localObject == null)
      return paramLong;
    return ((Long)localObject).longValue();
  }

  public Set<String> getNames()
  {
    throw new UnsupportedOperationException();
  }

  public boolean isParameterFalse(String paramString)
  {
    return !getBooleanParameter(paramString, false);
  }

  public boolean isParameterTrue(String paramString)
  {
    return getBooleanParameter(paramString, false);
  }

  public HttpParams setBooleanParameter(String paramString, boolean paramBoolean)
  {
    if (paramBoolean);
    for (Boolean localBoolean = Boolean.TRUE; ; localBoolean = Boolean.FALSE)
    {
      setParameter(paramString, localBoolean);
      return this;
    }
  }

  public HttpParams setDoubleParameter(String paramString, double paramDouble)
  {
    setParameter(paramString, new Double(paramDouble));
    return this;
  }

  public HttpParams setIntParameter(String paramString, int paramInt)
  {
    setParameter(paramString, new Integer(paramInt));
    return this;
  }

  public HttpParams setLongParameter(String paramString, long paramLong)
  {
    setParameter(paramString, new Long(paramLong));
    return this;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.params.AbstractHttpParams
 * JD-Core Version:    0.6.2
 */