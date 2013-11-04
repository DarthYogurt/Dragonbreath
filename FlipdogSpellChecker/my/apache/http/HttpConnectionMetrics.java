package my.apache.http;

public abstract interface HttpConnectionMetrics
{
  public abstract Object getMetric(String paramString);

  public abstract long getReceivedBytesCount();

  public abstract long getRequestCount();

  public abstract long getResponseCount();

  public abstract long getSentBytesCount();

  public abstract void reset();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.HttpConnectionMetrics
 * JD-Core Version:    0.6.2
 */