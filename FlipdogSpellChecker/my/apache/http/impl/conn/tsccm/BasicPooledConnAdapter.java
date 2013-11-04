package my.apache.http.impl.conn.tsccm;

import my.apache.http.conn.ClientConnectionManager;
import my.apache.http.impl.conn.AbstractPoolEntry;
import my.apache.http.impl.conn.AbstractPooledConnAdapter;

@Deprecated
public class BasicPooledConnAdapter extends AbstractPooledConnAdapter
{
  protected BasicPooledConnAdapter(ThreadSafeClientConnManager paramThreadSafeClientConnManager, AbstractPoolEntry paramAbstractPoolEntry)
  {
    super(paramThreadSafeClientConnManager, paramAbstractPoolEntry);
    markReusable();
  }

  protected void detach()
  {
    super.detach();
  }

  protected ClientConnectionManager getManager()
  {
    return super.getManager();
  }

  protected AbstractPoolEntry getPoolEntry()
  {
    return super.getPoolEntry();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.tsccm.BasicPooledConnAdapter
 * JD-Core Version:    0.6.2
 */