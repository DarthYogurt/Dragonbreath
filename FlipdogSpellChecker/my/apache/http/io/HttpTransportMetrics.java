package my.apache.http.io;

public abstract interface HttpTransportMetrics
{
  public abstract long getBytesTransferred();

  public abstract void reset();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.io.HttpTransportMetrics
 * JD-Core Version:    0.6.2
 */