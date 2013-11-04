package my.apache.http.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import my.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class FileEntity extends AbstractHttpEntity
  implements Cloneable
{
  protected final File file;

  public FileEntity(File paramFile)
  {
    if (paramFile == null)
      throw new IllegalArgumentException("File may not be null");
    this.file = paramFile;
  }

  @Deprecated
  public FileEntity(File paramFile, String paramString)
  {
    if (paramFile == null)
      throw new IllegalArgumentException("File may not be null");
    this.file = paramFile;
    setContentType(paramString);
  }

  public FileEntity(File paramFile, ContentType paramContentType)
  {
    if (paramFile == null)
      throw new IllegalArgumentException("File may not be null");
    this.file = paramFile;
    if (paramContentType != null)
      setContentType(paramContentType.toString());
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public InputStream getContent()
    throws IOException
  {
    return new FileInputStream(this.file);
  }

  public long getContentLength()
  {
    return this.file.length();
  }

  public boolean isRepeatable()
  {
    return true;
  }

  public boolean isStreaming()
  {
    return false;
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException("Output stream may not be null");
    FileInputStream localFileInputStream = new FileInputStream(this.file);
    try
    {
      byte[] arrayOfByte = new byte[4096];
      while (true)
      {
        int i = localFileInputStream.read(arrayOfByte);
        if (i == -1)
        {
          paramOutputStream.flush();
          return;
        }
        paramOutputStream.write(arrayOfByte, 0, i);
      }
    }
    finally
    {
      localFileInputStream.close();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.entity.FileEntity
 * JD-Core Version:    0.6.2
 */