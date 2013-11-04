package my.apache.commons.codec.binary;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BaseNCodecInputStream extends FilterInputStream
{
  private final BaseNCodec baseNCodec;
  private byte[] buf;
  private final boolean doEncode;
  private final byte[] singleByte = new byte[1];

  protected BaseNCodecInputStream(InputStream paramInputStream, BaseNCodec paramBaseNCodec, boolean paramBoolean)
  {
    super(paramInputStream);
    this.doEncode = paramBoolean;
    this.baseNCodec = paramBaseNCodec;
    if (paramBoolean);
    for (int i = 4096; ; i = 8192)
    {
      this.buf = new byte[i];
      return;
    }
  }

  public boolean markSupported()
  {
    return false;
  }

  public int read()
    throws IOException
  {
    for (int i = read(this.singleByte, 0, 1); ; i = read(this.singleByte, 0, 1))
      if (i != 0)
      {
        if (i <= 0)
          break label60;
        if (this.singleByte[0] >= 0)
          break;
        return 256 + this.singleByte[0];
      }
    return this.singleByte[0];
    label60: return -1;
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramArrayOfByte == null)
      throw new NullPointerException();
    if ((paramInt1 < 0) || (paramInt2 < 0))
      throw new IndexOutOfBoundsException();
    if ((paramInt1 > paramArrayOfByte.length) || (paramInt1 + paramInt2 > paramArrayOfByte.length))
      throw new IndexOutOfBoundsException();
    if (paramInt2 == 0)
    {
      i = 0;
      return i;
    }
    int i = 0;
    label63: int j;
    if (i == 0)
      if (!this.baseNCodec.hasData())
      {
        j = this.in.read(this.buf);
        if (!this.doEncode)
          break label127;
        this.baseNCodec.encode(this.buf, 0, j);
      }
    while (true)
    {
      i = this.baseNCodec.readResults(paramArrayOfByte, paramInt1, paramInt2);
      break label63;
      break;
      label127: this.baseNCodec.decode(this.buf, 0, j);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.commons.codec.binary.BaseNCodecInputStream
 * JD-Core Version:    0.6.2
 */