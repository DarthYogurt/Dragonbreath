package my.apache.http.conn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import my.apache.http.HttpEntity;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.entity.HttpEntityWrapper;
import my.apache.http.util.EntityUtils;

@NotThreadSafe
public class BasicManagedEntity extends HttpEntityWrapper
  implements ConnectionReleaseTrigger, EofSensorWatcher
{
  protected final boolean attemptReuse;
  protected ManagedClientConnection managedConn;

  public BasicManagedEntity(HttpEntity paramHttpEntity, ManagedClientConnection paramManagedClientConnection, boolean paramBoolean)
  {
    super(paramHttpEntity);
    if (paramManagedClientConnection == null)
      throw new IllegalArgumentException("Connection may not be null.");
    this.managedConn = paramManagedClientConnection;
    this.attemptReuse = paramBoolean;
  }

  private void ensureConsumed()
    throws IOException
  {
    if (this.managedConn == null)
      return;
    try
    {
      if (this.attemptReuse)
      {
        EntityUtils.consume(this.wrappedEntity);
        this.managedConn.markReusable();
      }
      while (true)
      {
        return;
        this.managedConn.unmarkReusable();
      }
    }
    finally
    {
      releaseManagedConnection();
    }
  }

  public void abortConnection()
    throws IOException
  {
    if (this.managedConn != null);
    try
    {
      this.managedConn.abortConnection();
      return;
    }
    finally
    {
      this.managedConn = null;
    }
  }

  public void consumeContent()
    throws IOException
  {
    ensureConsumed();
  }

  public boolean eofDetected(InputStream paramInputStream)
    throws IOException
  {
    try
    {
      if (this.managedConn != null)
      {
        if (!this.attemptReuse)
          break label33;
        paramInputStream.close();
        this.managedConn.markReusable();
      }
      while (true)
      {
        return false;
        label33: this.managedConn.unmarkReusable();
      }
    }
    finally
    {
      releaseManagedConnection();
    }
  }

  public InputStream getContent()
    throws IOException
  {
    return new EofSensorInputStream(this.wrappedEntity.getContent(), this);
  }

  public boolean isRepeatable()
  {
    return false;
  }

  public void releaseConnection()
    throws IOException
  {
    ensureConsumed();
  }

  protected void releaseManagedConnection()
    throws IOException
  {
    if (this.managedConn != null);
    try
    {
      this.managedConn.releaseConnection();
      return;
    }
    finally
    {
      this.managedConn = null;
    }
  }

  public boolean streamAbort(InputStream paramInputStream)
    throws IOException
  {
    if (this.managedConn != null)
      this.managedConn.abortConnection();
    return false;
  }

  public boolean streamClosed(InputStream paramInputStream)
    throws IOException
  {
    while (true)
    {
      try
      {
        boolean bool;
        if (this.managedConn != null)
        {
          if (this.attemptReuse)
            bool = this.managedConn.isOpen();
        }
        else
          try
          {
            paramInputStream.close();
            this.managedConn.markReusable();
            return false;
          }
          catch (SocketException localSocketException)
          {
            if (!bool)
              continue;
            throw localSocketException;
          }
      }
      finally
      {
        releaseManagedConnection();
      }
      this.managedConn.unmarkReusable();
    }
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    super.writeTo(paramOutputStream);
    ensureConsumed();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.BasicManagedEntity
 * JD-Core Version:    0.6.2
 */