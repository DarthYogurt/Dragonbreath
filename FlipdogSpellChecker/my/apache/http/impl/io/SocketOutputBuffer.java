package my.apache.http.impl.io;

import java.io.IOException;
import java.net.Socket;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.params.HttpParams;

@NotThreadSafe
public class SocketOutputBuffer extends AbstractSessionOutputBuffer
{
  public SocketOutputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramSocket == null)
      throw new IllegalArgumentException("Socket may not be null");
    if (paramInt < 0)
      paramInt = paramSocket.getSendBufferSize();
    if (paramInt < 1024)
      paramInt = 1024;
    init(paramSocket.getOutputStream(), paramInt, paramHttpParams);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.io.SocketOutputBuffer
 * JD-Core Version:    0.6.2
 */