package com.flipdog.commons.q;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public final class a
{
  private static void a(b paramb, ByteBuffer paramByteBuffer)
  {
    if (paramb != null)
      paramb.a(paramByteBuffer.remaining());
  }

  public static void a(ReadableByteChannel paramReadableByteChannel, WritableByteChannel paramWritableByteChannel)
    throws IOException
  {
    ByteBuffer localByteBuffer = ByteBuffer.allocateDirect(16384);
    if (paramReadableByteChannel.read(localByteBuffer) == -1)
      localByteBuffer.flip();
    while (true)
    {
      if (!localByteBuffer.hasRemaining())
      {
        return;
        localByteBuffer.flip();
        paramWritableByteChannel.write(localByteBuffer);
        localByteBuffer.compact();
        break;
      }
      paramWritableByteChannel.write(localByteBuffer);
    }
  }

  public static void a(ReadableByteChannel paramReadableByteChannel, WritableByteChannel paramWritableByteChannel, b paramb, com.flipdog.commons.l.b paramb1)
    throws IOException, com.flipdog.commons.u.a
  {
    ByteBuffer localByteBuffer = ByteBuffer.allocateDirect(16384);
    if (paramReadableByteChannel.read(localByteBuffer) == -1)
      localByteBuffer.flip();
    while (true)
    {
      if (!localByteBuffer.hasRemaining())
      {
        return;
        localByteBuffer.flip();
        a(paramb, localByteBuffer);
        paramWritableByteChannel.write(localByteBuffer);
        localByteBuffer.compact();
        if ((paramb1 == null) || (!paramb1.a()))
          break;
        throw new com.flipdog.commons.u.a();
      }
      a(paramb, localByteBuffer);
      paramWritableByteChannel.write(localByteBuffer);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.q.a
 * JD-Core Version:    0.6.2
 */