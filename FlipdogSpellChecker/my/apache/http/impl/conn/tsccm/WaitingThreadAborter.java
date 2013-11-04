package my.apache.http.impl.conn.tsccm;

@Deprecated
public class WaitingThreadAborter
{
  private boolean aborted;
  private WaitingThread waitingThread;

  public void abort()
  {
    this.aborted = true;
    if (this.waitingThread != null)
      this.waitingThread.interrupt();
  }

  public void setWaitingThread(WaitingThread paramWaitingThread)
  {
    this.waitingThread = paramWaitingThread;
    if (this.aborted)
      paramWaitingThread.interrupt();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.tsccm.WaitingThreadAborter
 * JD-Core Version:    0.6.2
 */