package my.apache.commons.codec.language;

import java.util.Locale;
import my.apache.commons.codec.EncoderException;
import my.apache.commons.codec.StringEncoder;

public class ColognePhonetic
  implements StringEncoder
{
  private static final char[][] PREPROCESS_MAP = { { 196, 65 }, { 220, 85 }, { 214, 79 }, { 223, 83 } };

  private static boolean arrayContains(char[] paramArrayOfChar, char paramChar)
  {
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayOfChar.length)
        return false;
      if (paramArrayOfChar[i] == paramChar)
        return true;
    }
  }

  private String preprocess(String paramString)
  {
    char[] arrayOfChar = paramString.toUpperCase(Locale.GERMAN).toCharArray();
    int i = 0;
    if (i >= arrayOfChar.length)
      return new String(arrayOfChar);
    if (arrayOfChar[i] > 'Z');
    label82: for (int j = 0; ; j++)
    {
      if (j >= PREPROCESS_MAP.length);
      while (true)
      {
        i++;
        break;
        if (arrayOfChar[i] != PREPROCESS_MAP[j][0])
          break label82;
        arrayOfChar[i] = PREPROCESS_MAP[j][1];
      }
    }
  }

  public String colognePhonetic(String paramString)
  {
    if (paramString == null)
      return null;
    String str = preprocess(paramString);
    CologneOutputBuffer localCologneOutputBuffer = new CologneOutputBuffer(2 * str.length());
    CologneInputBuffer localCologneInputBuffer = new CologneInputBuffer(str.toCharArray());
    char c1 = '-';
    int i = 47;
    int j = localCologneInputBuffer.length();
    if (j <= 0)
      return localCologneOutputBuffer.toString();
    char c2 = localCologneInputBuffer.removeNext();
    j = localCologneInputBuffer.length();
    char c3;
    label92: char c4;
    if (j > 0)
    {
      c3 = localCologneInputBuffer.getNextChar();
      if (!arrayContains(new char[] { 65, 69, 73, 74, 79, 85, 89 }, c2))
        break label210;
      c4 = '0';
    }
    while (true)
    {
      if ((c4 != '-') && (((i != c4) && ((c4 != '0') || (i == 47))) || (c4 < '0') || (c4 > '8')))
        localCologneOutputBuffer.addRight(c4);
      c1 = c2;
      i = c4;
      break;
      c3 = '-';
      break label92;
      label210: if ((c2 == 'H') || (c2 < 'A') || (c2 > 'Z'))
      {
        if (i == 47)
          break;
        c4 = '-';
        continue;
      }
      if ((c2 == 'B') || ((c2 == 'P') && (c3 != 'H')))
        c4 = '1';
      else if ((c2 == 'D') || (c2 == 'T'))
      {
        if (!arrayContains(new char[] { 83, 67, 90 }, c3))
          c4 = '2';
      }
      else if (arrayContains(new char[] { 87, 70, 80, 86 }, c2))
        c4 = '3';
      else if (arrayContains(new char[] { 71, 75, 81 }, c2))
        c4 = '4';
      else if (c2 == 'X')
      {
        if (!arrayContains(new char[] { 67, 75, 81 }, c1))
        {
          c4 = '4';
          localCologneInputBuffer.addLeft('S');
          j++;
        }
      }
      else if ((c2 == 'S') || (c2 == 'Z'))
        c4 = '8';
      else if (c2 == 'C')
      {
        if (i == 47)
        {
          if (arrayContains(new char[] { 65, 72, 75, 76, 79, 81, 82, 85, 88 }, c3))
            c4 = '4';
          else
            c4 = '8';
        }
        else
        {
          if (!arrayContains(new char[] { 83, 90 }, c1))
          {
            if (arrayContains(new char[] { 65, 72, 79, 85, 75, 81, 88 }, c3));
          }
          else
          {
            c4 = '8';
            continue;
          }
          c4 = '4';
        }
      }
      else if (arrayContains(new char[] { 84, 68, 88 }, c2))
        c4 = '8';
      else if (c2 == 'R')
        c4 = '7';
      else if (c2 == 'L')
        c4 = '5';
      else if ((c2 == 'M') || (c2 == 'N'))
        c4 = '6';
      else
        c4 = c2;
    }
  }

  public Object encode(Object paramObject)
    throws EncoderException
  {
    if (!(paramObject instanceof String))
      throw new EncoderException("This methodâ€™s parameter was expected to be of the type " + String.class.getName() + ". But actually it was of the type " + paramObject.getClass().getName() + ".");
    return encode((String)paramObject);
  }

  public String encode(String paramString)
  {
    return colognePhonetic(paramString);
  }

  public boolean isEncodeEqual(String paramString1, String paramString2)
  {
    return colognePhonetic(paramString1).equals(colognePhonetic(paramString2));
  }

  private abstract class CologneBuffer
  {
    protected final char[] data;
    protected int length = 0;

    public CologneBuffer(int arg2)
    {
      int i;
      this.data = new char[i];
      this.length = 0;
    }

    public CologneBuffer(char[] arg2)
    {
      Object localObject;
      this.data = localObject;
      this.length = localObject.length;
    }

    protected abstract char[] copyData(int paramInt1, int paramInt2);

    public int length()
    {
      return this.length;
    }

    public String toString()
    {
      return new String(copyData(0, this.length));
    }
  }

  private class CologneInputBuffer extends ColognePhonetic.CologneBuffer
  {
    public CologneInputBuffer(char[] arg2)
    {
      super(arrayOfChar);
    }

    public void addLeft(char paramChar)
    {
      this.length = (1 + this.length);
      this.data[getNextPos()] = paramChar;
    }

    protected char[] copyData(int paramInt1, int paramInt2)
    {
      char[] arrayOfChar = new char[paramInt2];
      System.arraycopy(this.data, paramInt1 + (this.data.length - this.length), arrayOfChar, 0, paramInt2);
      return arrayOfChar;
    }

    public char getNextChar()
    {
      return this.data[getNextPos()];
    }

    protected int getNextPos()
    {
      return this.data.length - this.length;
    }

    public char removeNext()
    {
      char c = getNextChar();
      this.length = (-1 + this.length);
      return c;
    }
  }

  private class CologneOutputBuffer extends ColognePhonetic.CologneBuffer
  {
    public CologneOutputBuffer(int arg2)
    {
      super(i);
    }

    public void addRight(char paramChar)
    {
      this.data[this.length] = paramChar;
      this.length = (1 + this.length);
    }

    protected char[] copyData(int paramInt1, int paramInt2)
    {
      char[] arrayOfChar = new char[paramInt2];
      System.arraycopy(this.data, paramInt1, arrayOfChar, 0, paramInt2);
      return arrayOfChar;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.commons.codec.language.ColognePhonetic
 * JD-Core Version:    0.6.2
 */