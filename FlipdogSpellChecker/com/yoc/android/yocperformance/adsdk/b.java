package com.yoc.android.yocperformance.adsdk;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import java.io.InputStream;
import java.util.Vector;

class b
{
  public static final int a = 0;
  public static final int b = 1;
  public static final int c = 2;
  protected static final int d = 4096;
  protected int A;
  protected int B;
  protected int C;
  protected Bitmap D;
  protected Bitmap E;
  protected byte[] F = new byte[256];
  protected int G = 0;
  protected int H = 0;
  protected int I = 0;
  protected boolean J = false;
  protected int K = 0;
  protected int L;
  protected short[] M;
  protected byte[] N;
  protected byte[] O;
  protected byte[] P;
  protected Vector<s> Q;
  protected int R;
  protected Bitmap.Config S = Bitmap.Config.ARGB_8888;
  protected InputStream e;
  protected int f;
  protected int g;
  protected int h;
  protected boolean i;
  protected int j;
  protected int k = 1;
  protected int[] l;
  protected int[] m;
  protected int[] n;
  protected int o;
  protected int p;
  protected int q;
  protected int r;
  protected boolean s;
  protected boolean t;
  protected int u;
  protected int v;
  protected int w;
  protected int x;
  protected int y;
  protected int z;

  public int a()
  {
    return this.R;
  }

  public int a(int paramInt)
  {
    this.K = -1;
    if ((paramInt >= 0) && (paramInt < this.R))
      this.K = ((s)this.Q.elementAt(paramInt)).b;
    return this.K;
  }

  public int a(InputStream paramInputStream)
  {
    g();
    if (paramInputStream != null)
    {
      this.e = paramInputStream;
      l();
      if (!f())
      {
        j();
        if (this.R < 0)
          this.f = 1;
      }
    }
    try
    {
      while (true)
      {
        paramInputStream.close();
        label44: this.e = null;
        return this.f;
        this.f = 2;
      }
    }
    catch (Exception localException)
    {
      break label44;
    }
  }

  public void a(Bitmap.Config paramConfig)
  {
    this.S = paramConfig;
  }

  public Bitmap b()
  {
    return b(0);
  }

  public Bitmap b(int paramInt)
  {
    if (this.R <= 0)
      return null;
    int i1 = paramInt % this.R;
    return ((s)this.Q.elementAt(i1)).a;
  }

  public int c()
  {
    return this.k;
  }

  protected int[] c(int paramInt)
  {
    int i1 = 0;
    int i2 = paramInt * 3;
    int[] arrayOfInt = (int[])null;
    byte[] arrayOfByte = new byte[i2];
    while (true)
    {
      int i4;
      try
      {
        int i11 = this.e.read(arrayOfByte);
        i3 = i11;
        if (i3 < i2)
        {
          this.f = 1;
          return arrayOfInt;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        int i3 = 0;
        continue;
        arrayOfInt = new int[256];
        i4 = 0;
      }
      while (i1 < paramInt)
      {
        int i5 = i4 + 1;
        int i6 = 0xFF & arrayOfByte[i4];
        int i7 = i5 + 1;
        int i8 = 0xFF & arrayOfByte[i5];
        i4 = i7 + 1;
        int i9 = 0xFF & arrayOfByte[i7];
        int i10 = i1 + 1;
        arrayOfInt[i1] = (i9 | (0xFF000000 | i6 << 16 | i8 << 8));
        i1 = i10;
      }
    }
  }

  protected void d()
  {
    int i1 = 0;
    int[] arrayOfInt = new int[this.g * this.h];
    if (this.I > 0)
    {
      if (this.I == 3)
      {
        int i21 = -2 + this.R;
        if (i21 <= 0)
          break label156;
        this.E = b(i21 - 1);
      }
      if (this.E != null)
      {
        this.E.getPixels(arrayOfInt, 0, this.g, 0, 0, this.g, this.h);
        if (this.I == 2)
          if (this.J)
            break label454;
      }
    }
    label156: label454: for (int i17 = this.q; ; i17 = 0)
    {
      int i18 = 0;
      int i2;
      int i3;
      int i4;
      if (i18 >= this.C)
      {
        i2 = 8;
        i3 = 1;
        i4 = 0;
        if (i1 < this.y)
          break label217;
        this.D = Bitmap.createBitmap(arrayOfInt, this.g, this.h, this.S);
        return;
        this.E = null;
        break;
      }
      int i19 = (i18 + this.A) * this.g + this.z;
      int i20 = i19 + this.B;
      while (true)
      {
        if (i19 >= i20)
        {
          i18++;
          break;
        }
        arrayOfInt[i19] = i17;
        i19++;
      }
      label217: int i16;
      if (this.t)
      {
        if (i4 >= this.y)
          i3++;
        switch (i3)
        {
        default:
          int i15 = i4 + i2;
          i16 = i4;
          i4 = i15;
        case 2:
        case 3:
        case 4:
        }
      }
      for (int i5 = i16; ; i5 = i1)
      {
        int i6 = i5 + this.w;
        int i9;
        int i10;
        int i11;
        if (i6 < this.h)
        {
          int i7 = i6 * this.g;
          int i8 = i7 + this.v;
          i9 = i8 + this.x;
          if (i7 + this.g < i9)
            i9 = i7 + this.g;
          i10 = i1 * this.x;
          i11 = i8;
        }
        while (true)
        {
          if (i11 >= i9)
          {
            i1++;
            break;
            i4 = 4;
            break label264;
            i4 = 2;
            i2 = 4;
            break label264;
            i4 = 1;
            i2 = 2;
            break label264;
          }
          byte[] arrayOfByte = this.P;
          int i12 = i10 + 1;
          int i13 = 0xFF & arrayOfByte[i10];
          int i14 = this.n[i13];
          if (i14 != 0)
            arrayOfInt[i11] = i14;
          i11++;
          i10 = i12;
        }
      }
    }
  }

  protected void e()
  {
    int i1 = this.x * this.y;
    if ((this.P == null) || (this.P.length < i1))
      this.P = new byte[i1];
    if (this.M == null)
      this.M = new short[4096];
    if (this.N == null)
      this.N = new byte[4096];
    if (this.O == null)
      this.O = new byte[4097];
    int i2 = h();
    int i3 = 1 << i2;
    int i4 = i3 + 1;
    int i5 = i3 + 2;
    int i6 = i2 + 1;
    int i7 = -1 + (1 << i6);
    int i8 = 0;
    int i9;
    int i10;
    int i11;
    int i12;
    int i13;
    int i14;
    int i15;
    int i16;
    int i17;
    if (i8 >= i3)
    {
      i9 = 0;
      i10 = 0;
      i11 = 0;
      i12 = 0;
      i13 = 0;
      i14 = 0;
      i15 = 0;
      i16 = -1;
      i17 = 0;
      label149: if (i13 < i1)
        break label190;
    }
    int i35;
    label159: label190: label221: label252: int i29;
    int i30;
    int i31;
    label393: int i33;
    do
    {
      do
      {
        do
        {
          i35 = i9;
          if (i35 < i1)
            break label636;
          return;
          this.M[i8] = 0;
          this.N[i8] = ((byte)i8);
          i8++;
          break;
          if (i10 != 0)
            break label661;
          if (i15 >= i6)
            break label252;
          if (i14 != 0)
            break label221;
          i14 = i();
        }
        while (i14 <= 0);
        i17 = 0;
        i12 += ((0xFF & this.F[i17]) << i15);
        i15 += 8;
        i17++;
        i14--;
        break label149;
        i29 = i12 & i7;
        i12 >>= i6;
        i15 -= i6;
      }
      while ((i29 > i5) || (i29 == i4));
      if (i29 == i3)
      {
        i6 = i2 + 1;
        i7 = -1 + (1 << i6);
        i5 = i3 + 2;
        i16 = -1;
        break label149;
      }
      if (i16 == -1)
      {
        byte[] arrayOfByte5 = this.O;
        int i36 = i10 + 1;
        arrayOfByte5[i10] = this.N[i29];
        i10 = i36;
        i16 = i29;
        i11 = i29;
        break label149;
      }
      if (i29 != i5)
        break label650;
      byte[] arrayOfByte4 = this.O;
      i30 = i10 + 1;
      arrayOfByte4[i10] = ((byte)i11);
      i31 = i16;
      if (i31 > i3)
        break label596;
      i33 = 0xFF & this.N[i31];
    }
    while (i5 >= 4096);
    byte[] arrayOfByte3 = this.O;
    int i34 = i30 + 1;
    arrayOfByte3[i30] = ((byte)i33);
    this.M[i5] = ((short)i16);
    this.N[i5] = ((byte)i33);
    int i25 = i5 + 1;
    if (((i25 & i7) == 0) && (i25 < 4096))
    {
      i6++;
      i7 += i25;
    }
    int i23 = i12;
    int i24 = i29;
    int i18 = i7;
    int i19 = i33;
    int i20 = i15;
    int i21 = i6;
    int i22 = i34;
    while (true)
    {
      int i26 = i22 - 1;
      byte[] arrayOfByte1 = this.P;
      int i27 = i9 + 1;
      arrayOfByte1[i9] = this.O[i26];
      i13++;
      i9 = i27;
      i6 = i21;
      i15 = i20;
      i11 = i19;
      i7 = i18;
      int i28 = i24;
      i12 = i23;
      i10 = i26;
      i5 = i25;
      i16 = i28;
      break;
      label596: byte[] arrayOfByte2 = this.O;
      int i32 = i30 + 1;
      arrayOfByte2[i30] = this.N[i31];
      i31 = this.M[i31];
      i30 = i32;
      break label393;
      label636: this.P[i35] = 0;
      i35++;
      break label159;
      label650: i30 = i10;
      i31 = i29;
      break label393;
      label661: i18 = i7;
      i19 = i11;
      i20 = i15;
      i21 = i6;
      i22 = i10;
      i23 = i12;
      i24 = i16;
      i25 = i5;
    }
  }

  protected boolean f()
  {
    return this.f != 0;
  }

  protected void g()
  {
    this.f = 0;
    this.R = 0;
    this.Q = new Vector();
    this.l = null;
    this.m = null;
  }

  protected int h()
  {
    try
    {
      int i1 = this.e.read();
      return i1;
    }
    catch (Exception localException)
    {
      this.f = 1;
    }
    return 0;
  }

  protected int i()
  {
    this.G = h();
    int i1 = this.G;
    int i2 = 0;
    if (i1 > 0);
    try
    {
      while (true)
      {
        int i3 = this.G;
        if (i2 >= i3);
        int i4;
        do
        {
          if (i2 < this.G)
            this.f = 1;
          return i2;
          i4 = this.e.read(this.F, i2, this.G - i2);
        }
        while (i4 == -1);
        i2 += i4;
      }
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  protected void j()
  {
    int i1 = 0;
    while (true)
    {
      if ((i1 != 0) || (f()))
        return;
      switch (h())
      {
      default:
        this.f = 1;
        break;
      case 44:
        m();
        break;
      case 33:
        switch (h())
        {
        default:
          r();
          break;
        case 249:
          k();
          break;
        case 255:
          i();
          String str = "";
          for (int i2 = 0; ; i2++)
          {
            if (i2 >= 11)
            {
              if (!str.equals("NETSCAPE2.0"))
                break label192;
              o();
              break;
            }
            str = str + (char)this.F[i2];
          }
          r();
          break;
        case 254:
          r();
          break;
        case 1:
          r();
        }
        break;
      case 59:
        label192: i1 = 1;
      }
    }
  }

  protected void k()
  {
    int i1 = 1;
    h();
    int i2 = h();
    this.H = ((i2 & 0x1C) >> 2);
    if (this.H == 0)
      this.H = i1;
    if ((i2 & 0x1) != 0);
    while (true)
    {
      this.J = i1;
      this.K = (10 * p());
      this.L = h();
      h();
      return;
      i1 = 0;
    }
  }

  protected void l()
  {
    String str = "";
    int i1 = 0;
    if (i1 >= 6)
    {
      if (str.startsWith("GIF"))
        break label55;
      this.f = 1;
    }
    label55: 
    do
    {
      return;
      str = str + (char)h();
      i1++;
      break;
      n();
    }
    while ((!this.i) || (f()));
    this.l = c(this.j);
    this.p = this.l[this.o];
  }

  protected void m()
  {
    this.v = p();
    this.w = p();
    this.x = p();
    this.y = p();
    int i1 = h();
    boolean bool1;
    boolean bool2;
    label79: label111: int i2;
    if ((i1 & 0x80) != 0)
    {
      bool1 = true;
      this.s = bool1;
      this.u = ((int)Math.pow(2.0D, 1 + (i1 & 0x7)));
      if ((i1 & 0x40) == 0)
        break label175;
      bool2 = true;
      this.t = bool2;
      if (!this.s)
        break label180;
      this.m = c(this.u);
      this.n = this.m;
      boolean bool3 = this.J;
      i2 = 0;
      if (bool3)
      {
        int i3 = this.n[this.L];
        this.n[this.L] = 0;
        i2 = i3;
      }
      if (this.n == null)
        this.f = 1;
      if (!f())
        break label207;
    }
    label175: label180: label207: 
    do
    {
      return;
      bool1 = false;
      break;
      bool2 = false;
      break label79;
      this.n = this.l;
      if (this.o != this.L)
        break label111;
      this.p = 0;
      break label111;
      e();
      r();
    }
    while (f());
    this.R = (1 + this.R);
    this.D = Bitmap.createBitmap(this.g, this.h, this.S);
    d();
    this.Q.addElement(new s(this.D, this.K));
    if (this.J)
      this.n[this.L] = i2;
    q();
  }

  protected void n()
  {
    this.g = p();
    this.h = p();
    int i1 = h();
    if ((i1 & 0x80) != 0);
    for (boolean bool = true; ; bool = false)
    {
      this.i = bool;
      this.j = (2 << (i1 & 0x7));
      this.o = h();
      this.r = h();
      return;
    }
  }

  protected void o()
  {
    do
    {
      i();
      if (this.F[0] == 1)
        this.k = (0xFF & this.F[1] | (0xFF & this.F[2]) << 8);
    }
    while ((this.G > 0) && (!f()));
  }

  protected int p()
  {
    return h() | h() << 8;
  }

  protected void q()
  {
    this.I = this.H;
    this.z = this.v;
    this.A = this.w;
    this.B = this.x;
    this.C = this.y;
    this.E = this.D;
    this.q = this.p;
    this.H = 0;
    this.J = false;
    this.K = 0;
    this.m = null;
  }

  protected void r()
  {
    do
      i();
    while ((this.G > 0) && (!f()));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.yoc.android.yocperformance.adsdk.b
 * JD-Core Version:    0.6.2
 */