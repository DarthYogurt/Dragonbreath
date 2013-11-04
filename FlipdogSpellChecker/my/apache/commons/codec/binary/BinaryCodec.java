package my.apache.commons.codec.binary;

import my.apache.commons.codec.BinaryDecoder;
import my.apache.commons.codec.BinaryEncoder;
import my.apache.commons.codec.DecoderException;
import my.apache.commons.codec.EncoderException;

public class BinaryCodec
  implements BinaryDecoder, BinaryEncoder
{
  private static final int[] BITS = { 1, 2, 4, 8, 16, 32, 64, 128 };
  private static final int BIT_0 = 1;
  private static final int BIT_1 = 2;
  private static final int BIT_2 = 4;
  private static final int BIT_3 = 8;
  private static final int BIT_4 = 16;
  private static final int BIT_5 = 32;
  private static final int BIT_6 = 64;
  private static final int BIT_7 = 128;
  private static final byte[] EMPTY_BYTE_ARRAY;
  private static final char[] EMPTY_CHAR_ARRAY = new char[0];

  static
  {
    EMPTY_BYTE_ARRAY = new byte[0];
  }

  public static byte[] fromAscii(byte[] paramArrayOfByte)
  {
    if (isEmpty(paramArrayOfByte))
    {
      arrayOfByte = EMPTY_BYTE_ARRAY;
      return arrayOfByte;
    }
    byte[] arrayOfByte = new byte[paramArrayOfByte.length >> 3];
    int i = 0;
    int j = -1 + paramArrayOfByte.length;
    label27: if (i < arrayOfByte.length);
    for (int k = 0; ; k++)
    {
      if (k >= BITS.length)
      {
        i++;
        j -= 8;
        break label27;
        break;
      }
      if (paramArrayOfByte[(j - k)] == 49)
        arrayOfByte[i] = ((byte)(arrayOfByte[i] | BITS[k]));
    }
  }

  public static byte[] fromAscii(char[] paramArrayOfChar)
  {
    if ((paramArrayOfChar == null) || (paramArrayOfChar.length == 0))
    {
      arrayOfByte = EMPTY_BYTE_ARRAY;
      return arrayOfByte;
    }
    byte[] arrayOfByte = new byte[paramArrayOfChar.length >> 3];
    int i = 0;
    int j = -1 + paramArrayOfChar.length;
    label29: if (i < arrayOfByte.length);
    for (int k = 0; ; k++)
    {
      if (k >= BITS.length)
      {
        i++;
        j -= 8;
        break label29;
        break;
      }
      if (paramArrayOfChar[(j - k)] == '1')
        arrayOfByte[i] = ((byte)(arrayOfByte[i] | BITS[k]));
    }
  }

  private static boolean isEmpty(byte[] paramArrayOfByte)
  {
    return (paramArrayOfByte == null) || (paramArrayOfByte.length == 0);
  }

  public static byte[] toAsciiBytes(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte;
    if (isEmpty(paramArrayOfByte))
      arrayOfByte = EMPTY_BYTE_ARRAY;
    int i;
    int j;
    int k;
    while (true)
    {
      return arrayOfByte;
      arrayOfByte = new byte[paramArrayOfByte.length << 3];
      i = 0;
      for (j = -1 + arrayOfByte.length; i < paramArrayOfByte.length; j -= 8)
      {
        k = 0;
        if (k < BITS.length)
          break label54;
        i++;
      }
    }
    label54: if ((paramArrayOfByte[i] & BITS[k]) == 0)
      arrayOfByte[(j - k)] = 48;
    while (true)
    {
      k++;
      break;
      arrayOfByte[(j - k)] = 49;
    }
  }

  public static char[] toAsciiChars(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar;
    if (isEmpty(paramArrayOfByte))
      arrayOfChar = EMPTY_CHAR_ARRAY;
    int i;
    int j;
    int k;
    while (true)
    {
      return arrayOfChar;
      arrayOfChar = new char[paramArrayOfByte.length << 3];
      i = 0;
      for (j = -1 + arrayOfChar.length; i < paramArrayOfByte.length; j -= 8)
      {
        k = 0;
        if (k < BITS.length)
          break label54;
        i++;
      }
    }
    label54: if ((paramArrayOfByte[i] & BITS[k]) == 0)
      arrayOfChar[(j - k)] = '0';
    while (true)
    {
      k++;
      break;
      arrayOfChar[(j - k)] = '1';
    }
  }

  public static String toAsciiString(byte[] paramArrayOfByte)
  {
    return new String(toAsciiChars(paramArrayOfByte));
  }

  public Object decode(Object paramObject)
    throws DecoderException
  {
    if (paramObject == null)
      return EMPTY_BYTE_ARRAY;
    if ((paramObject instanceof byte[]))
      return fromAscii((byte[])paramObject);
    if ((paramObject instanceof char[]))
      return fromAscii((char[])paramObject);
    if ((paramObject instanceof String))
      return fromAscii(((String)paramObject).toCharArray());
    throw new DecoderException("argument not a byte array");
  }

  public byte[] decode(byte[] paramArrayOfByte)
  {
    return fromAscii(paramArrayOfByte);
  }

  public Object encode(Object paramObject)
    throws EncoderException
  {
    if (!(paramObject instanceof byte[]))
      throw new EncoderException("argument not a byte array");
    return toAsciiChars((byte[])paramObject);
  }

  public byte[] encode(byte[] paramArrayOfByte)
  {
    return toAsciiBytes(paramArrayOfByte);
  }

  public byte[] toByteArray(String paramString)
  {
    if (paramString == null)
      return EMPTY_BYTE_ARRAY;
    return fromAscii(paramString.toCharArray());
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.commons.codec.binary.BinaryCodec
 * JD-Core Version:    0.6.2
 */