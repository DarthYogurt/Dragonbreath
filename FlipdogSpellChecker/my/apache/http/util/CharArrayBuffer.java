package my.apache.http.util;

import java.io.Serializable;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.protocol.HTTP;

@NotThreadSafe
public final class CharArrayBuffer
  implements Serializable
{
  private static final long serialVersionUID = -6208952725094867135L;
  private char[] buffer;
  private int len;

  public CharArrayBuffer(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("Buffer capacity may not be negative");
    this.buffer = new char[paramInt];
  }

  private void expand(int paramInt)
  {
    char[] arrayOfChar = new char[Math.max(this.buffer.length << 1, paramInt)];
    System.arraycopy(this.buffer, 0, arrayOfChar, 0, this.len);
    this.buffer = arrayOfChar;
  }

  public void append(char paramChar)
  {
    int i = 1 + this.len;
    if (i > this.buffer.length)
      expand(i);
    this.buffer[this.len] = paramChar;
    this.len = i;
  }

  public void append(Object paramObject)
  {
    append(String.valueOf(paramObject));
  }

  public void append(String paramString)
  {
    if (paramString == null)
      paramString = "null";
    int i = paramString.length();
    int j = i + this.len;
    if (j > this.buffer.length)
      expand(j);
    paramString.getChars(0, i, this.buffer, this.len);
    this.len = j;
  }

  public void append(ByteArrayBuffer paramByteArrayBuffer, int paramInt1, int paramInt2)
  {
    if (paramByteArrayBuffer == null)
      return;
    append(paramByteArrayBuffer.buffer(), paramInt1, paramInt2);
  }

  public void append(CharArrayBuffer paramCharArrayBuffer)
  {
    if (paramCharArrayBuffer == null)
      return;
    append(paramCharArrayBuffer.buffer, 0, paramCharArrayBuffer.len);
  }

  public void append(CharArrayBuffer paramCharArrayBuffer, int paramInt1, int paramInt2)
  {
    if (paramCharArrayBuffer == null)
      return;
    append(paramCharArrayBuffer.buffer, paramInt1, paramInt2);
  }

  public void append(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null);
    do
    {
      return;
      if ((paramInt1 < 0) || (paramInt1 > paramArrayOfByte.length) || (paramInt2 < 0) || (paramInt1 + paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfByte.length))
        throw new IndexOutOfBoundsException("off: " + paramInt1 + " len: " + paramInt2 + " b.length: " + paramArrayOfByte.length);
    }
    while (paramInt2 == 0);
    int i = this.len;
    int j = i + paramInt2;
    if (j > this.buffer.length)
      expand(j);
    int k = paramInt1;
    for (int m = i; ; m++)
    {
      if (m >= j)
      {
        this.len = j;
        return;
      }
      this.buffer[m] = ((char)(0xFF & paramArrayOfByte[k]));
      k++;
    }
  }

  public void append(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (paramArrayOfChar == null);
    do
    {
      return;
      if ((paramInt1 < 0) || (paramInt1 > paramArrayOfChar.length) || (paramInt2 < 0) || (paramInt1 + paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfChar.length))
        throw new IndexOutOfBoundsException("off: " + paramInt1 + " len: " + paramInt2 + " b.length: " + paramArrayOfChar.length);
    }
    while (paramInt2 == 0);
    int i = paramInt2 + this.len;
    if (i > this.buffer.length)
      expand(i);
    System.arraycopy(paramArrayOfChar, paramInt1, this.buffer, this.len, paramInt2);
    this.len = i;
  }

  public char[] buffer()
  {
    return this.buffer;
  }

  public int capacity()
  {
    return this.buffer.length;
  }

  public char charAt(int paramInt)
  {
    return this.buffer[paramInt];
  }

  public void clear()
  {
    this.len = 0;
  }

  public void ensureCapacity(int paramInt)
  {
    if (paramInt <= 0);
    while (paramInt <= this.buffer.length - this.len)
      return;
    expand(paramInt + this.len);
  }

  public int indexOf(int paramInt)
  {
    return indexOf(paramInt, 0, this.len);
  }

  public int indexOf(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt2 < 0)
      paramInt2 = 0;
    if (paramInt3 > this.len)
      paramInt3 = this.len;
    if (paramInt2 > paramInt3)
    {
      i = -1;
      return i;
    }
    for (int i = paramInt2; ; i++)
    {
      if (i >= paramInt3)
        return -1;
      if (this.buffer[i] == paramInt1)
        break;
    }
  }

  public boolean isEmpty()
  {
    return this.len == 0;
  }

  public boolean isFull()
  {
    return this.len == this.buffer.length;
  }

  public int length()
  {
    return this.len;
  }

  public void setLength(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > this.buffer.length))
      throw new IndexOutOfBoundsException("len: " + paramInt + " < 0 or > buffer len: " + this.buffer.length);
    this.len = paramInt;
  }

  public String substring(int paramInt1, int paramInt2)
  {
    return new String(this.buffer, paramInt1, paramInt2 - paramInt1);
  }

  public String substringTrimmed(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0)
      throw new IndexOutOfBoundsException("Negative beginIndex: " + paramInt1);
    if (paramInt2 > this.len)
      throw new IndexOutOfBoundsException("endIndex: " + paramInt2 + " > length: " + this.len);
    if (paramInt1 > paramInt2)
      throw new IndexOutOfBoundsException("beginIndex: " + paramInt1 + " > endIndex: " + paramInt2);
    while ((paramInt1 < paramInt2) && (HTTP.isWhitespace(this.buffer[paramInt1])))
      paramInt1++;
    while (true)
    {
      if ((paramInt2 <= paramInt1) || (!HTTP.isWhitespace(this.buffer[(paramInt2 - 1)])))
        return new String(this.buffer, paramInt1, paramInt2 - paramInt1);
      paramInt2--;
    }
  }

  public char[] toCharArray()
  {
    char[] arrayOfChar = new char[this.len];
    if (this.len > 0)
      System.arraycopy(this.buffer, 0, arrayOfChar, 0, this.len);
    return arrayOfChar;
  }

  public String toString()
  {
    return new String(this.buffer, 0, this.len);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.util.CharArrayBuffer
 * JD-Core Version:    0.6.2
 */