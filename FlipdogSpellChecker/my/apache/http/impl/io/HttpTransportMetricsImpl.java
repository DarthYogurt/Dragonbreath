package my.apache.http.impl.io;

import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.io.HttpTransportMetrics;

@NotThreadSafe
public class HttpTransportMetricsImpl
  implements HttpTransportMetrics
{
  private long bytesTransferred = 0L;

  public long getBytesTransferred()
  {
    return this.bytesTransferred;
  }

  public void incrementBytesTransferred(long paramLong)
  {
    this.bytesTransferred = (paramLong + this.bytesTransferred);
  }

  public void reset()
  {
    this.bytesTransferred = 0L;
  }

  public void setBytesTransferred(long paramLong)
  {
    this.bytesTransferred = paramLong;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.io.HttpTransportMetricsImpl
 * JD-Core Version:    0.6.2
 */