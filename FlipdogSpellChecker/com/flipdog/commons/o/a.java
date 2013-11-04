package com.flipdog.commons.o;

import android.text.Editable;
import android.text.GetChars;
import android.text.InputFilter;
import android.text.NoCopySpan;
import android.text.Selection;
import android.text.SpanWatcher;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import java.lang.reflect.Array;

public class a
  implements Editable, GetChars, Spannable, Appendable, CharSequence
{
  private static final InputFilter[] a = new InputFilter[0];
  private static final int k = 1;
  private static final int l = 2;
  private static final int m = 3;
  private static final int n = 240;
  private static final int o = 15;
  private static final int p = 4;
  private InputFilter[] b = a;
  private char[] c;
  private int d;
  private int e;
  private Object[] f;
  private int[] g;
  private int[] h;
  private int[] i;
  private int j;

  public a()
  {
    this("");
  }

  public a(CharSequence paramCharSequence)
  {
    this(paramCharSequence, 0, paramCharSequence.length());
  }

  public a(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    int i1 = paramInt2 - paramInt1;
    int i2 = b.a(i1 + 1);
    this.c = new char[i2];
    this.d = i1;
    this.e = (i2 - i1);
    TextUtils.getChars(paramCharSequence, paramInt1, paramInt2, this.c, 0);
    this.j = 0;
    int i3 = b.b(0);
    this.f = new Object[i3];
    this.g = new int[i3];
    this.h = new int[i3];
    this.i = new int[i3];
    Spanned localSpanned;
    Object[] arrayOfObject;
    int i4;
    if ((paramCharSequence instanceof Spanned))
    {
      localSpanned = (Spanned)paramCharSequence;
      arrayOfObject = localSpanned.getSpans(paramInt1, paramInt2, Object.class);
      i4 = 0;
      if (i4 < arrayOfObject.length);
    }
    else
    {
      return;
    }
    if ((arrayOfObject[i4] instanceof NoCopySpan));
    while (true)
    {
      i4++;
      break;
      int i5 = localSpanned.getSpanStart(arrayOfObject[i4]) - paramInt1;
      int i6 = localSpanned.getSpanEnd(arrayOfObject[i4]) - paramInt1;
      int i7 = localSpanned.getSpanFlags(arrayOfObject[i4]);
      if (i5 < 0)
        i5 = 0;
      if (i5 > paramInt2 - paramInt1)
        i5 = paramInt2 - paramInt1;
      if (i6 < 0)
        i6 = 0;
      if (i6 > paramInt2 - paramInt1)
        i6 = paramInt2 - paramInt1;
      setSpan(arrayOfObject[i4], i5, i6, i7);
    }
  }

  private int a(boolean paramBoolean, int paramInt1, int paramInt2, CharSequence paramCharSequence, int paramInt3, int paramInt4)
  {
    a("replace", paramInt1, paramInt2);
    int i1 = paramInt4 - paramInt3;
    TextWatcher[] arrayOfTextWatcher1 = (TextWatcher[])null;
    if (paramBoolean);
    for (TextWatcher[] arrayOfTextWatcher2 = a(paramInt1, paramInt2 - paramInt1, paramInt4 - paramInt3); ; arrayOfTextWatcher2 = arrayOfTextWatcher1)
    {
      int i2 = -1 + this.j;
      Spanned localSpanned;
      Object[] arrayOfObject;
      int i13;
      int i3;
      int i4;
      int i5;
      int i8;
      label340: int i6;
      if (i2 < 0)
      {
        b(paramInt2);
        if (paramInt4 - paramInt3 >= this.e + (paramInt2 - paramInt1))
          a(paramInt4 + (this.c.length - this.e) - paramInt3 - (paramInt2 - paramInt1));
        this.d += paramInt4 - paramInt3 - (paramInt2 - paramInt1);
        this.e -= paramInt4 - paramInt3 - (paramInt2 - paramInt1);
        if (this.e < 1)
          new Exception("mGapLength < 1").printStackTrace();
        TextUtils.getChars(paramCharSequence, paramInt3, paramInt4, this.c, paramInt1);
        if ((paramCharSequence instanceof Spanned))
        {
          localSpanned = (Spanned)paramCharSequence;
          arrayOfObject = localSpanned.getSpans(paramInt3, paramInt4, Object.class);
          i13 = 0;
          if (i13 < arrayOfObject.length);
        }
        else
        {
          if ((paramInt4 <= paramInt3) || (paramInt2 - paramInt1 != 0))
            break label576;
          if (paramBoolean)
          {
            a(arrayOfTextWatcher2, paramInt1, paramInt2 - paramInt1, paramInt4 - paramInt3);
            a(arrayOfTextWatcher2);
          }
          return i1;
        }
      }
      else if ((0x33 & this.i[i2]) == 51)
      {
        i3 = this.g[i2];
        if (i3 > this.d)
          i3 -= this.e;
        i4 = this.h[i2];
        if (i4 > this.d)
          i4 -= this.e;
        i5 = length();
        if ((i3 <= paramInt1) || (i3 > paramInt2))
          break label969;
        i8 = paramInt2;
        if (i8 < i5)
          break label415;
        i6 = i8;
      }
      while (true)
      {
        label351: int i7;
        if ((i4 > paramInt1) && (i4 <= paramInt2))
        {
          i7 = paramInt2;
          label366: if (i7 < i5);
        }
        while (true)
        {
          if ((i6 != i3) || (i7 != i4))
            setSpan(this.f[i2], i6, i7, this.i[i2]);
          i2--;
          break;
          label415: if ((i8 > paramInt2) && (charAt(i8 - 1) == '\n'))
          {
            i6 = i8;
            break label351;
          }
          i8++;
          break label340;
          if ((i7 <= paramInt2) || (charAt(i7 - 1) != '\n'))
          {
            i7++;
            break label366;
            int i14 = localSpanned.getSpanStart(arrayOfObject[i13]);
            int i15 = localSpanned.getSpanEnd(arrayOfObject[i13]);
            if (i14 < paramInt3);
            for (int i16 = paramInt3; ; i16 = i14)
            {
              if (i15 > paramInt4)
                i15 = paramInt4;
              if (getSpanStart(arrayOfObject[i13]) < 0)
                a(false, arrayOfObject[i13], paramInt1 + (i16 - paramInt3), paramInt1 + (i15 - paramInt3), localSpanned.getSpanFlags(arrayOfObject[i13]));
              i13++;
              break;
              label576: if (this.d + this.e == this.c.length);
              int i10;
              for (int i9 = 1; ; i9 = 0)
              {
                i10 = -1 + this.j;
                if (i10 >= 0)
                  break;
                if (paramBoolean)
                {
                  a(arrayOfTextWatcher2, paramInt1, paramInt2 - paramInt1, paramInt4 - paramInt3);
                  a(arrayOfTextWatcher2);
                }
                return i1;
              }
              if ((this.g[i10] >= paramInt1) && (this.g[i10] < this.d + this.e))
              {
                int i12 = (0xF0 & this.i[i10]) >> 4;
                if ((i12 == 2) || ((i12 == 3) && (i9 != 0)))
                  this.g[i10] = (this.d + this.e);
              }
              else
              {
                label721: if ((this.h[i10] >= paramInt1) && (this.h[i10] < this.d + this.e))
                {
                  int i11 = 0xF & this.i[i10];
                  if ((i11 != 2) && ((i11 != 3) || (i9 == 0)))
                    break label944;
                  this.h[i10] = (this.d + this.e);
                }
              }
              while (true)
              {
                if (this.h[i10] < this.g[i10])
                {
                  System.arraycopy(this.f, i10 + 1, this.f, i10, this.j - (i10 + 1));
                  System.arraycopy(this.g, i10 + 1, this.g, i10, this.j - (i10 + 1));
                  System.arraycopy(this.h, i10 + 1, this.h, i10, this.j - (i10 + 1));
                  System.arraycopy(this.i, i10 + 1, this.i, i10, this.j - (i10 + 1));
                  this.j = (-1 + this.j);
                }
                i10--;
                break;
                this.g[i10] = paramInt1;
                break label721;
                label944: this.h[i10] = paramInt1;
              }
            }
            i7 = i4;
          }
        }
        label969: i6 = i3;
      }
    }
  }

  public static SpannableStringBuilder a(CharSequence paramCharSequence)
  {
    if ((paramCharSequence instanceof SpannableStringBuilder))
      return (SpannableStringBuilder)paramCharSequence;
    return new SpannableStringBuilder(paramCharSequence);
  }

  private void a(int paramInt)
  {
    int i1 = 0;
    int i2 = b.a(paramInt + 1);
    char[] arrayOfChar = new char[i2];
    int i3 = this.c.length - (this.d + this.e);
    System.arraycopy(this.c, 0, arrayOfChar, 0, this.d);
    System.arraycopy(this.c, this.c.length - i3, arrayOfChar, i2 - i3, i3);
    while (true)
    {
      if (i1 >= this.j)
      {
        int i4 = this.c.length;
        this.c = arrayOfChar;
        this.e += this.c.length - i4;
        if (this.e < 1)
          new Exception("mGapLength < 1").printStackTrace();
        return;
      }
      if (this.g[i1] > this.d)
      {
        int[] arrayOfInt2 = this.g;
        arrayOfInt2[i1] += i2 - this.c.length;
      }
      if (this.h[i1] > this.d)
      {
        int[] arrayOfInt1 = this.h;
        arrayOfInt1[i1] += i2 - this.c.length;
      }
      i1++;
    }
  }

  private void a(Object paramObject, int paramInt1, int paramInt2)
  {
    SpanWatcher[] arrayOfSpanWatcher = (SpanWatcher[])getSpans(paramInt1, paramInt2, SpanWatcher.class);
    int i1 = arrayOfSpanWatcher.length;
    for (int i2 = 0; ; i2++)
    {
      if (i2 >= i1)
        return;
      arrayOfSpanWatcher[i2].onSpanAdded(this, paramObject, paramInt1, paramInt2);
    }
  }

  private void a(Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    SpanWatcher[] arrayOfSpanWatcher = (SpanWatcher[])getSpans(Math.min(paramInt1, paramInt3), Math.max(paramInt2, paramInt4), SpanWatcher.class);
    int i1 = arrayOfSpanWatcher.length;
    for (int i2 = 0; ; i2++)
    {
      if (i2 >= i1)
        return;
      arrayOfSpanWatcher[i2].onSpanChanged(this, paramObject, paramInt1, paramInt2, paramInt3, paramInt4);
    }
  }

  private void a(String paramString, int paramInt1, int paramInt2)
  {
    if (paramInt2 < paramInt1)
      throw new IndexOutOfBoundsException(paramString + " " + b(paramInt1, paramInt2) + " has end before start");
    int i1 = length();
    if ((paramInt1 > i1) || (paramInt2 > i1))
      throw new IndexOutOfBoundsException(paramString + " " + b(paramInt1, paramInt2) + " ends beyond length " + i1);
    if ((paramInt1 < 0) || (paramInt2 < 0))
      throw new IndexOutOfBoundsException(paramString + " " + b(paramInt1, paramInt2) + " starts before 0");
  }

  private void a(boolean paramBoolean, Object paramObject, int paramInt1, int paramInt2, int paramInt3)
  {
    a("setSpan", paramInt1, paramInt2);
    if (((paramInt3 & 0xF0) == 48) && (paramInt1 != 0) && (paramInt1 != length()) && (charAt(paramInt1 - 1) != '\n'))
      throw new RuntimeException("PARAGRAPH span must start at paragraph boundary");
    if (((paramInt3 & 0xF) == 3) && (paramInt2 != 0) && (paramInt2 != length()) && (charAt(paramInt2 - 1) != '\n'))
      throw new RuntimeException("PARAGRAPH span must end at paragraph boundary");
    int i1;
    if (paramInt1 > this.d)
      i1 = paramInt1 + this.e;
    while (true)
    {
      int i2;
      if (paramInt2 > this.d)
        i2 = paramInt2 + this.e;
      while (true)
      {
        label134: if (i1 > i2)
          i1 = i2;
        int i3 = this.j;
        Object[] arrayOfObject1 = this.f;
        label572: for (int i4 = 0; ; i4++)
        {
          if (i4 >= i3)
          {
            if (1 + this.j >= this.f.length)
            {
              int i7 = b.b(1 + this.j);
              Object[] arrayOfObject2 = new Object[i7];
              int[] arrayOfInt1 = new int[i7];
              int[] arrayOfInt2 = new int[i7];
              int[] arrayOfInt3 = new int[i7];
              System.arraycopy(this.f, 0, arrayOfObject2, 0, this.j);
              System.arraycopy(this.g, 0, arrayOfInt1, 0, this.j);
              System.arraycopy(this.h, 0, arrayOfInt2, 0, this.j);
              System.arraycopy(this.i, 0, arrayOfInt3, 0, this.j);
              this.f = arrayOfObject2;
              this.g = arrayOfInt1;
              this.h = arrayOfInt2;
              this.i = arrayOfInt3;
            }
            this.f[this.j] = paramObject;
            this.g[this.j] = i1;
            this.h[this.j] = i2;
            this.i[this.j] = paramInt3;
            this.j = (1 + this.j);
            if (paramBoolean)
              a(paramObject, paramInt1, paramInt2);
          }
          int i5;
          int i6;
          do
          {
            return;
            if (paramInt1 != this.d)
              break label585;
            int i9 = (paramInt3 & 0xF0) >> 4;
            if ((i9 != 2) && ((i9 != 3) || (paramInt1 != length())))
              break label585;
            i1 = paramInt1 + this.e;
            break;
            if (paramInt2 != this.d)
              break label578;
            int i8 = paramInt3 & 0xF;
            if ((i8 != 2) && ((i8 != 3) || (paramInt2 != length())))
              break label578;
            i2 = paramInt2 + this.e;
            break label134;
            if (arrayOfObject1[i4] != paramObject)
              break label572;
            i5 = this.g[i4];
            i6 = this.h[i4];
            if (i5 > this.d)
              i5 -= this.e;
            if (i6 > this.d)
              i6 -= this.e;
            this.g[i4] = i1;
            this.h[i4] = i2;
            this.i[i4] = paramInt3;
          }
          while (!paramBoolean);
          a(paramObject, i5, i6, paramInt1, paramInt2);
          return;
        }
        label578: i2 = paramInt2;
      }
      label585: i1 = paramInt1;
    }
  }

  private void a(TextWatcher[] paramArrayOfTextWatcher)
  {
    int i1 = paramArrayOfTextWatcher.length;
    for (int i2 = 0; ; i2++)
    {
      if (i2 >= i1)
        return;
      paramArrayOfTextWatcher[i2].afterTextChanged(this);
    }
  }

  private void a(TextWatcher[] paramArrayOfTextWatcher, int paramInt1, int paramInt2, int paramInt3)
  {
    int i1 = paramArrayOfTextWatcher.length;
    for (int i2 = 0; ; i2++)
    {
      if (i2 >= i1)
        return;
      paramArrayOfTextWatcher[i2].onTextChanged(this, paramInt1, paramInt2, paramInt3);
    }
  }

  private TextWatcher[] a(int paramInt1, int paramInt2, int paramInt3)
  {
    TextWatcher[] arrayOfTextWatcher = (TextWatcher[])getSpans(paramInt1, paramInt1 + paramInt2, TextWatcher.class);
    int i1 = arrayOfTextWatcher.length;
    for (int i2 = 0; ; i2++)
    {
      if (i2 >= i1)
        return arrayOfTextWatcher;
      arrayOfTextWatcher[i2].beforeTextChanged(this, paramInt1, paramInt2, paramInt3);
    }
  }

  private int b(int paramInt1, int paramInt2, CharSequence paramCharSequence, int paramInt3, int paramInt4)
  {
    return a(true, paramInt1, paramInt2, paramCharSequence, paramInt3, paramInt4);
  }

  private static String b(int paramInt1, int paramInt2)
  {
    return "(" + paramInt1 + " ... " + paramInt2 + ")";
  }

  private void b(int paramInt)
  {
    int i1 = 0;
    if (paramInt == this.d)
      return;
    int i2;
    if (paramInt == length())
    {
      i2 = 1;
      if (paramInt >= this.d)
        break label82;
      int i8 = this.d - paramInt;
      System.arraycopy(this.c, paramInt, this.c, this.d + this.e - i8, i8);
    }
    while (true)
    {
      if (i1 < this.j)
        break label121;
      this.d = paramInt;
      return;
      i2 = 0;
      break;
      label82: int i3 = paramInt - this.d;
      System.arraycopy(this.c, paramInt + this.e - i3, this.c, this.d, i3);
      i1 = 0;
    }
    label121: int i4 = this.g[i1];
    int i5 = this.h[i1];
    if (i4 > this.d)
      i4 -= this.e;
    if (i4 > paramInt)
    {
      i4 += this.e;
      label170: if (i5 > this.d)
        i5 -= this.e;
      if (i5 <= paramInt)
        break label273;
      i5 += this.e;
    }
    while (true)
    {
      this.g[i1] = i4;
      this.h[i1] = i5;
      i1++;
      break;
      if (i4 != paramInt)
        break label170;
      int i6 = (0xF0 & this.i[i1]) >> 4;
      if ((i6 != 2) && ((i2 == 0) || (i6 != 3)))
        break label170;
      i4 += this.e;
      break label170;
      label273: if (i5 == paramInt)
      {
        int i7 = 0xF & this.i[i1];
        if ((i7 == 2) || ((i2 != 0) && (i7 == 3)))
          i5 += this.e;
      }
    }
  }

  private void b(Object paramObject, int paramInt1, int paramInt2)
  {
    SpanWatcher[] arrayOfSpanWatcher = (SpanWatcher[])getSpans(paramInt1, paramInt2, SpanWatcher.class);
    int i1 = arrayOfSpanWatcher.length;
    for (int i2 = 0; ; i2++)
    {
      if (i2 >= i1)
        return;
      arrayOfSpanWatcher[i2].onSpanRemoved(this, paramObject, paramInt1, paramInt2);
    }
  }

  private boolean b(char paramChar)
  {
    return (paramChar >= ' ') && (paramChar <= '~');
  }

  public a a(char paramChar)
  {
    return b(String.valueOf(paramChar));
  }

  public a a(int paramInt1, int paramInt2)
  {
    a locala = a(paramInt1, paramInt2, "", 0, 0);
    if (this.e > 2 * length())
      a(length());
    return locala;
  }

  public a a(int paramInt1, int paramInt2, CharSequence paramCharSequence)
  {
    return a(paramInt1, paramInt2, paramCharSequence, 0, paramCharSequence.length());
  }

  public a a(int paramInt1, int paramInt2, CharSequence paramCharSequence, int paramInt3, int paramInt4)
  {
    int i1 = this.b.length;
    int i2 = 0;
    int i3 = paramInt4;
    int i4 = paramInt3;
    Object localObject = paramCharSequence;
    while (true)
    {
      if (i2 >= i1)
      {
        if ((paramInt2 != paramInt1) || (i4 != i3))
          break;
        return this;
      }
      CharSequence localCharSequence = this.b[i2].filter((CharSequence)localObject, i4, i3, this, paramInt1, paramInt2);
      if (localCharSequence != null)
      {
        i4 = 0;
        i3 = localCharSequence.length();
        localObject = localCharSequence;
      }
      i2++;
    }
    if ((paramInt2 == paramInt1) || (i4 == i3))
    {
      b(paramInt1, paramInt2, (CharSequence)localObject, i4, i3);
      return this;
    }
    int i5 = Selection.getSelectionStart(this);
    int i6 = Selection.getSelectionEnd(this);
    a("replace", paramInt1, paramInt2);
    b(paramInt2);
    TextWatcher[] arrayOfTextWatcher = a(paramInt1, paramInt2 - paramInt1, i3 - i4);
    int i7 = paramInt2 - paramInt1;
    if (this.e < 2)
      a(1 + length());
    for (int i8 = -1 + this.j; ; i8--)
    {
      if (i8 < 0)
      {
        this.c[this.d] = ' ';
        this.d = (1 + this.d);
        this.e = (-1 + this.e);
        if (this.e < 1)
          new Exception("mGapLength < 1").printStackTrace();
        int i9 = paramInt2 + 1 - paramInt1;
        int i10 = a(false, paramInt1 + 1, paramInt1 + 1, (CharSequence)localObject, i4, i3);
        a(false, paramInt1, paramInt1 + 1, "", 0, 0);
        a(false, paramInt1 + i10, -1 + (i9 + (paramInt1 + i10)), "", 0, 0);
        if ((i5 > paramInt1) && (i5 < paramInt2))
        {
          int i12 = paramInt1 + (int)((i5 - paramInt1) * i10 / (paramInt2 - paramInt1));
          a(false, Selection.SELECTION_START, i12, i12, 34);
        }
        if ((i6 > paramInt1) && (i6 < paramInt2))
        {
          int i11 = paramInt1 + (int)((i6 - paramInt1) * i10 / (paramInt2 - paramInt1));
          a(false, Selection.SELECTION_END, i11, i11, 34);
        }
        a(arrayOfTextWatcher, paramInt1, i7, i10);
        a(arrayOfTextWatcher);
        return this;
      }
      if (this.g[i8] == this.d)
      {
        int[] arrayOfInt2 = this.g;
        arrayOfInt2[i8] = (1 + arrayOfInt2[i8]);
      }
      if (this.h[i8] == this.d)
      {
        int[] arrayOfInt1 = this.h;
        arrayOfInt1[i8] = (1 + arrayOfInt1[i8]);
      }
    }
  }

  public a a(int paramInt, CharSequence paramCharSequence)
  {
    return a(paramInt, paramInt, paramCharSequence, 0, paramCharSequence.length());
  }

  public a a(int paramInt1, CharSequence paramCharSequence, int paramInt2, int paramInt3)
  {
    return a(paramInt1, paramInt1, paramCharSequence, paramInt2, paramInt3);
  }

  public a a(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    int i1 = length();
    return a(i1, i1, paramCharSequence, paramInt1, paramInt2);
  }

  public a b(CharSequence paramCharSequence)
  {
    int i1 = length();
    return a(i1, i1, paramCharSequence, 0, paramCharSequence.length());
  }

  public char charAt(int paramInt)
  {
    int i1 = length();
    if (paramInt < 0)
      throw new IndexOutOfBoundsException("charAt: " + paramInt + " < 0");
    if (paramInt >= i1)
      throw new IndexOutOfBoundsException("charAt: " + paramInt + " >= length " + i1);
    if (paramInt >= this.d)
      return this.c[(paramInt + this.e)];
    return this.c[paramInt];
  }

  public void clear()
  {
    a(0, length(), "", 0, 0);
  }

  public void clearSpans()
  {
    for (int i1 = -1 + this.j; ; i1--)
    {
      if (i1 < 0)
        return;
      Object localObject = this.f[i1];
      int i2 = this.g[i1];
      int i3 = this.h[i1];
      if (i2 > this.d)
        i2 -= this.e;
      if (i3 > this.d)
        i3 -= this.e;
      this.j = i1;
      this.f[i1] = null;
      b(localObject, i2, i3);
    }
  }

  public void getChars(int paramInt1, int paramInt2, char[] paramArrayOfChar, int paramInt3)
  {
    a("getChars", paramInt1, paramInt2);
    if (paramInt2 <= this.d)
    {
      System.arraycopy(this.c, paramInt1, paramArrayOfChar, paramInt3, paramInt2 - paramInt1);
      return;
    }
    if (paramInt1 >= this.d)
    {
      System.arraycopy(this.c, paramInt1 + this.e, paramArrayOfChar, paramInt3, paramInt2 - paramInt1);
      return;
    }
    System.arraycopy(this.c, paramInt1, paramArrayOfChar, paramInt3, this.d - paramInt1);
    System.arraycopy(this.c, this.d + this.e, paramArrayOfChar, paramInt3 + (this.d - paramInt1), paramInt2 - this.d);
  }

  public InputFilter[] getFilters()
  {
    return this.b;
  }

  public int getSpanEnd(Object paramObject)
  {
    int i1 = this.j;
    Object[] arrayOfObject = this.f;
    for (int i2 = i1 - 1; ; i2--)
    {
      int i3;
      if (i2 < 0)
        i3 = -1;
      do
      {
        return i3;
        if (arrayOfObject[i2] != paramObject)
          break;
        i3 = this.h[i2];
      }
      while (i3 <= this.d);
      return i3 - this.e;
    }
  }

  public int getSpanFlags(Object paramObject)
  {
    int i1 = this.j;
    Object[] arrayOfObject = this.f;
    for (int i2 = i1 - 1; ; i2--)
    {
      if (i2 < 0)
        return 0;
      if (arrayOfObject[i2] == paramObject)
        return this.i[i2];
    }
  }

  public int getSpanStart(Object paramObject)
  {
    int i1 = this.j;
    Object[] arrayOfObject = this.f;
    for (int i2 = i1 - 1; ; i2--)
    {
      int i3;
      if (i2 < 0)
        i3 = -1;
      do
      {
        return i3;
        if (arrayOfObject[i2] != paramObject)
          break;
        i3 = this.g[i2];
      }
      while (i3 <= this.d);
      return i3 - this.e;
    }
  }

  public <T> T[] getSpans(int paramInt1, int paramInt2, Class<T> paramClass)
  {
    int i1 = this.j;
    Object[] arrayOfObject1 = this.f;
    int[] arrayOfInt1 = this.g;
    int[] arrayOfInt2 = this.h;
    int[] arrayOfInt3 = this.i;
    int i2 = this.d;
    int i3 = this.e;
    int i4 = 0;
    Object[] arrayOfObject2 = (Object[])null;
    int i5 = 0;
    Object localObject1 = null;
    Object localObject2 = arrayOfObject2;
    Object localObject6;
    Object localObject5;
    int i9;
    label295: Object localObject3;
    if (i5 >= i1)
    {
      if (i4 == 0)
        return b.a(paramClass);
    }
    else
    {
      int i6 = arrayOfInt1[i5];
      int i7 = arrayOfInt2[i5];
      if (i6 > i2)
        i6 -= i3;
      if (i7 > i2)
        i7 -= i3;
      if (i6 > paramInt2)
      {
        localObject6 = localObject1;
        localObject5 = localObject2;
        i9 = i4;
      }
      while (true)
      {
        i5++;
        i4 = i9;
        localObject2 = localObject5;
        localObject1 = localObject6;
        break;
        if (i7 < paramInt1)
        {
          localObject6 = localObject1;
          localObject5 = localObject2;
          i9 = i4;
        }
        else if ((i6 != i7) && (paramInt1 != paramInt2))
        {
          if (i6 == paramInt2)
          {
            localObject6 = localObject1;
            localObject5 = localObject2;
            i9 = i4;
          }
          else if (i7 == paramInt1)
          {
            localObject6 = localObject1;
            localObject5 = localObject2;
            i9 = i4;
          }
        }
        else if ((paramClass != null) && (!paramClass.isInstance(arrayOfObject1[i5])))
        {
          localObject6 = localObject1;
          localObject5 = localObject2;
          i9 = i4;
        }
        else
        {
          if (i4 != 0)
            break label295;
          localObject6 = arrayOfObject1[i5];
          int i11 = i4 + 1;
          Object localObject8 = localObject2;
          i9 = i11;
          localObject5 = localObject8;
        }
      }
      if (i4 != 1)
        break label514;
      localObject3 = (Object[])Array.newInstance(paramClass, 1 + (i1 - i5));
      localObject3[0] = localObject1;
    }
    while (true)
    {
      int i8 = 0xFF0000 & arrayOfInt3[i5];
      if (i8 != 0)
        for (int i10 = 0; ; i10++)
        {
          if (i10 >= i4);
          while (i8 > (0xFF0000 & getSpanFlags(localObject3[i10])))
          {
            System.arraycopy(localObject3, i10, localObject3, i10 + 1, i4 - i10);
            localObject3[i10] = arrayOfObject1[i5];
            i9 = i4 + 1;
            Object localObject7 = localObject1;
            localObject5 = localObject3;
            localObject6 = localObject7;
            break;
          }
        }
      i9 = i4 + 1;
      localObject3[i4] = arrayOfObject1[i5];
      Object localObject4 = localObject1;
      localObject5 = localObject3;
      localObject6 = localObject4;
      break;
      if (i4 == 1)
      {
        Object[] arrayOfObject4 = (Object[])Array.newInstance(paramClass, 1);
        arrayOfObject4[0] = localObject1;
        return arrayOfObject4;
      }
      if (i4 == localObject2.length)
        return localObject2;
      Object[] arrayOfObject3 = (Object[])Array.newInstance(paramClass, i4);
      System.arraycopy(localObject2, 0, arrayOfObject3, 0, i4);
      return arrayOfObject3;
      label514: localObject3 = localObject2;
    }
  }

  public int length()
  {
    return this.c.length - this.e;
  }

  public int nextSpanTransition(int paramInt1, int paramInt2, Class paramClass)
  {
    int i1 = this.j;
    Object[] arrayOfObject = this.f;
    int[] arrayOfInt1 = this.g;
    int[] arrayOfInt2 = this.h;
    int i2 = this.d;
    int i3 = this.e;
    if (paramClass == null)
      paramClass = Object.class;
    int i4 = 0;
    int i5 = paramInt2;
    if (i4 >= i1)
      return i5;
    int i6 = arrayOfInt1[i4];
    int i7 = arrayOfInt2[i4];
    if (i6 > i2)
      i6 -= i3;
    if (i7 > i2)
      i7 -= i3;
    if ((i6 > paramInt1) && (i6 < i5) && (paramClass.isInstance(arrayOfObject[i4])));
    while (true)
    {
      if ((i7 > paramInt1) && (i7 < i6) && (paramClass.isInstance(arrayOfObject[i4])));
      while (true)
      {
        i4++;
        i5 = i7;
        break;
        i7 = i6;
      }
      i6 = i5;
    }
  }

  public void removeSpan(Object paramObject)
  {
    for (int i1 = -1 + this.j; ; i1--)
    {
      if (i1 < 0)
        return;
      if (this.f[i1] == paramObject)
      {
        int i2 = this.g[i1];
        int i3 = this.h[i1];
        if (i2 > this.d)
          i2 -= this.e;
        if (i3 > this.d)
          i3 -= this.e;
        int i4 = this.j - (i1 + 1);
        System.arraycopy(this.f, i1 + 1, this.f, i1, i4);
        System.arraycopy(this.g, i1 + 1, this.g, i1, i4);
        System.arraycopy(this.h, i1 + 1, this.h, i1, i4);
        System.arraycopy(this.i, i1 + 1, this.i, i1, i4);
        this.j = (-1 + this.j);
        this.f[this.j] = null;
        b(paramObject, i2, i3);
        return;
      }
    }
  }

  public void setFilters(InputFilter[] paramArrayOfInputFilter)
  {
    if (paramArrayOfInputFilter == null)
      throw new IllegalArgumentException();
    this.b = paramArrayOfInputFilter;
  }

  public void setSpan(Object paramObject, int paramInt1, int paramInt2, int paramInt3)
  {
    a(true, paramObject, paramInt1, paramInt2, paramInt3);
  }

  public CharSequence subSequence(int paramInt1, int paramInt2)
  {
    return new SpannableStringBuilder(this, paramInt1, paramInt2);
  }

  public String toString()
  {
    int i1 = length();
    char[] arrayOfChar = new char[i1];
    getChars(0, i1, arrayOfChar, 0);
    return new String(arrayOfChar);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.o.a
 * JD-Core Version:    0.6.2
 */