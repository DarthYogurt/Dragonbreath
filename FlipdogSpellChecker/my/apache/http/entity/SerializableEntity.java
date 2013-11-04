package my.apache.http.entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import my.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class SerializableEntity extends AbstractHttpEntity
{
  private Serializable objRef;
  private byte[] objSer;

  public SerializableEntity(Serializable paramSerializable, boolean paramBoolean)
    throws IOException
  {
    if (paramSerializable == null)
      throw new IllegalArgumentException("Source object may not be null");
    if (paramBoolean)
    {
      createBytes(paramSerializable);
      return;
    }
    this.objRef = paramSerializable;
  }

  private void createBytes(Serializable paramSerializable)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
    localObjectOutputStream.writeObject(paramSerializable);
    localObjectOutputStream.flush();
    this.objSer = localByteArrayOutputStream.toByteArray();
  }

  public InputStream getContent()
    throws IOException, IllegalStateException
  {
    if (this.objSer == null)
      createBytes(this.objRef);
    return new ByteArrayInputStream(this.objSer);
  }

  public long getContentLength()
  {
    if (this.objSer == null)
      return -1L;
    return this.objSer.length;
  }

  public boolean isRepeatable()
  {
    return true;
  }

  public boolean isStreaming()
  {
    return this.objSer == null;
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException("Output stream may not be null");
    if (this.objSer == null)
    {
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(paramOutputStream);
      localObjectOutputStream.writeObject(this.objRef);
      localObjectOutputStream.flush();
      return;
    }
    paramOutputStream.write(this.objSer);
    paramOutputStream.flush();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.entity.SerializableEntity
 * JD-Core Version:    0.6.2
 */