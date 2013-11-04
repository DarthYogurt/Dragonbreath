package my.apache.http.impl.client;

class SystemClock
  implements Clock
{
  public long getCurrentTime()
  {
    return System.currentTimeMillis();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.SystemClock
 * JD-Core Version:    0.6.2
 */