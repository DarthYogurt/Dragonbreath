package com.flipdog.filebrowser.a;

import android.graphics.Paint;
import com.flipdog.filebrowser.k.k;
import com.flipdog.filebrowser.preference.a;

public class d
{
  private int[] a = new int[3];
  private Paint b = new Paint();

  private int a(String paramString, int paramInt)
  {
    this.b.setTextSize(a.b().e());
    return (int)this.b.measureText(paramString);
  }

  public void a()
  {
    this.a[0] = k.a(50);
    this.a[1] = a("45:29 AM ", 1);
    this.a[2] = a("12/34/5678 ", 1);
  }

  public int b()
  {
    return this.a[0];
  }

  public int c()
  {
    return this.a[1];
  }

  public int d()
  {
    return this.a[2];
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.a.d
 * JD-Core Version:    0.6.2
 */