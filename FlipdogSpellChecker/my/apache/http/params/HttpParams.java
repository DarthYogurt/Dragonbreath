package my.apache.http.params;

public abstract interface HttpParams
{
  @Deprecated
  public abstract HttpParams copy();

  public abstract boolean getBooleanParameter(String paramString, boolean paramBoolean);

  public abstract double getDoubleParameter(String paramString, double paramDouble);

  public abstract int getIntParameter(String paramString, int paramInt);

  public abstract long getLongParameter(String paramString, long paramLong);

  public abstract Object getParameter(String paramString);

  public abstract boolean isParameterFalse(String paramString);

  public abstract boolean isParameterTrue(String paramString);

  public abstract boolean removeParameter(String paramString);

  public abstract HttpParams setBooleanParameter(String paramString, boolean paramBoolean);

  public abstract HttpParams setDoubleParameter(String paramString, double paramDouble);

  public abstract HttpParams setIntParameter(String paramString, int paramInt);

  public abstract HttpParams setLongParameter(String paramString, long paramLong);

  public abstract HttpParams setParameter(String paramString, Object paramObject);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.params.HttpParams
 * JD-Core Version:    0.6.2
 */