package android.support.v4.util;

import java.io.PrintWriter;

public class TimeUtils
{
  public static final int HUNDRED_DAY_FIELD_LEN = 19;
  private static final int SECONDS_PER_DAY = 86400;
  private static final int SECONDS_PER_HOUR = 3600;
  private static final int SECONDS_PER_MINUTE = 60;
  private static char[] sFormatStr = new char[24];
  private static final Object sFormatSync = new Object();

  private static int accumField(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    if ((paramInt1 > 99) || ((paramBoolean) && (paramInt3 >= 3)))
      return paramInt2 + 3;
    if ((paramInt1 > 9) || ((paramBoolean) && (paramInt3 >= 2)))
      return paramInt2 + 2;
    if ((paramBoolean) || (paramInt1 > 0))
      return paramInt2 + 1;
    return 0;
  }

  public static void formatDuration(long paramLong1, long paramLong2, PrintWriter paramPrintWriter)
  {
    if (paramLong1 == 0L)
    {
      paramPrintWriter.print("--");
      return;
    }
    formatDuration(paramLong1 - paramLong2, paramPrintWriter, 0);
  }

  public static void formatDuration(long paramLong, PrintWriter paramPrintWriter)
  {
    formatDuration(paramLong, paramPrintWriter, 0);
  }

  public static void formatDuration(long paramLong, PrintWriter paramPrintWriter, int paramInt)
  {
    synchronized (sFormatSync)
    {
      int i = formatDurationLocked(paramLong, paramInt);
      paramPrintWriter.print(new String(sFormatStr, 0, i));
      return;
    }
  }

  public static void formatDuration(long paramLong, StringBuilder paramStringBuilder)
  {
    synchronized (sFormatSync)
    {
      int i = formatDurationLocked(paramLong, 0);
      paramStringBuilder.append(sFormatStr, 0, i);
      return;
    }
  }

  private static int formatDurationLocked(long paramLong, int paramInt)
  {
    if (sFormatStr.length < paramInt)
      sFormatStr = new char[paramInt];
    char[] arrayOfChar = sFormatStr;
    if (paramLong == 0L)
    {
      int i23 = paramInt - 1;
      while (true)
      {
        if (i23 >= 0)
        {
          arrayOfChar[0] = '0';
          return 1;
        }
        arrayOfChar[0] = ' ';
      }
    }
    int i;
    int j;
    int i5;
    boolean bool4;
    label200: boolean bool5;
    label222: boolean bool6;
    label244: int i21;
    label266: int i22;
    label282: int i7;
    label307: boolean bool1;
    label331: int i9;
    label339: boolean bool2;
    label365: int i11;
    label373: boolean bool3;
    label399: int i13;
    label407: int i14;
    if (paramLong > 0L)
    {
      i = 43;
      j = (int)(paramLong % 1000L);
      int k = (int)Math.floor(paramLong / 1000L);
      int m = k;
      int n = 0;
      if (m > 86400)
      {
        n = k / 86400;
        k -= 86400 * n;
      }
      int i1 = k;
      int i2 = 0;
      if (i1 > 3600)
      {
        i2 = k / 3600;
        k -= i2 * 3600;
      }
      int i3 = k;
      int i4 = 0;
      if (i3 > 60)
      {
        i4 = k / 60;
        k -= i4 * 60;
      }
      i5 = 0;
      if (paramInt != 0)
      {
        int i17 = accumField(n, 1, false, 0);
        if (i17 <= 0)
          break label474;
        bool4 = true;
        int i18 = i17 + accumField(i2, 1, bool4, 2);
        if (i18 <= 0)
          break label480;
        bool5 = true;
        int i19 = i18 + accumField(i4, 1, bool5, 2);
        if (i19 <= 0)
          break label486;
        bool6 = true;
        int i20 = i19 + accumField(k, 1, bool6, 2);
        if (i20 <= 0)
          break label492;
        i21 = 3;
        i22 = i20 + (1 + accumField(j, 2, true, i21));
        if (i22 < paramInt)
          break label498;
      }
      arrayOfChar[i5] = i;
      int i6 = i5 + 1;
      if (paramInt == 0)
        break label513;
      i7 = 1;
      int i8 = printField(arrayOfChar, n, 'd', i6, false, 0);
      if (i8 == i6)
        break label519;
      bool1 = true;
      if (i7 == 0)
        break label525;
      i9 = 2;
      int i10 = printField(arrayOfChar, i2, 'h', i8, bool1, i9);
      if (i10 == i6)
        break label531;
      bool2 = true;
      if (i7 == 0)
        break label537;
      i11 = 2;
      int i12 = printField(arrayOfChar, i4, 'm', i10, bool2, i11);
      if (i12 == i6)
        break label543;
      bool3 = true;
      if (i7 == 0)
        break label549;
      i13 = 2;
      i14 = printField(arrayOfChar, k, 's', i12, bool3, i13);
      if ((i7 == 0) || (i14 == i6))
        break label555;
    }
    label513: label519: label525: label531: label537: label543: label549: label555: for (int i15 = 3; ; i15 = 0)
    {
      int i16 = printField(arrayOfChar, j, 'm', i14, true, i15);
      arrayOfChar[i16] = 's';
      return i16 + 1;
      i = 45;
      paramLong = -paramLong;
      break;
      label474: bool4 = false;
      break label200;
      label480: bool5 = false;
      break label222;
      label486: bool6 = false;
      break label244;
      label492: i21 = 0;
      break label266;
      label498: arrayOfChar[i5] = ' ';
      i5++;
      i22++;
      break label282;
      i7 = 0;
      break label307;
      bool1 = false;
      break label331;
      i9 = 0;
      break label339;
      bool2 = false;
      break label365;
      i11 = 0;
      break label373;
      bool3 = false;
      break label399;
      i13 = 0;
      break label407;
    }
  }

  private static int printField(char[] paramArrayOfChar, int paramInt1, char paramChar, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    if ((paramBoolean) || (paramInt1 > 0))
    {
      int i = paramInt2;
      if (((paramBoolean) && (paramInt3 >= 3)) || (paramInt1 > 99))
      {
        int m = paramInt1 / 100;
        paramArrayOfChar[paramInt2] = ((char)(m + 48));
        paramInt2++;
        paramInt1 -= m * 100;
      }
      if (((paramBoolean) && (paramInt3 >= 2)) || (paramInt1 > 9) || (i != paramInt2))
      {
        int j = paramInt1 / 10;
        paramArrayOfChar[paramInt2] = ((char)(j + 48));
        paramInt2++;
        paramInt1 -= j * 10;
      }
      paramArrayOfChar[paramInt2] = ((char)(paramInt1 + 48));
      int k = paramInt2 + 1;
      paramArrayOfChar[k] = paramChar;
      paramInt2 = k + 1;
    }
    return paramInt2;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     android.support.v4.util.TimeUtils
 * JD-Core Version:    0.6.2
 */