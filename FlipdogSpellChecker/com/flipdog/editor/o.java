package com.flipdog.editor;

import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import com.flipdog.editor.spans.MyStyleSpan;
import com.flipdog.editor.spans.i;
import com.flipdog.editor.spans.j;
import java.util.Iterator;
import java.util.List;

class o
  implements TextWatcher
{
  o(ak paramak)
  {
  }

  private void a(int paramInt1, Spannable paramSpannable, int paramInt2)
  {
    this.a.a(ak.c(this.a), paramSpannable, paramInt1, paramInt2, i.a);
    this.a.a(ak.d(this.a), paramSpannable, paramInt1, paramInt2, i.b);
    this.a.a(ak.e(this.a), paramSpannable, paramInt1, paramInt2, i.c);
    this.a.a(ak.f(this.a), paramSpannable, paramInt1, paramInt2, i.g);
    this.a.a(ak.g(this.a), paramSpannable, paramInt1, paramInt2, i.h);
    if (ak.h(this.a) != null)
      this.a.a(paramSpannable, paramInt1, paramInt2, ak.h(this.a).intValue());
    if (ak.i(this.a) != null)
      this.a.b(paramSpannable, paramInt1, paramInt2, ak.i(this.a).intValue());
    while (true)
    {
      if (ak.k(this.a) != null)
        ak.a(this.a, paramSpannable, paramInt1, paramInt2, ak.k(this.a));
      if (ak.l(this.a) != null)
        this.a.a(paramSpannable, paramInt1, paramInt2, ak.l(this.a));
      return;
      if (ak.j(this.a) != -1)
        this.a.b(paramSpannable, paramInt1, paramInt2, ak.j(this.a));
    }
  }

  public void afterTextChanged(Editable paramEditable)
  {
    this.a.a(new String[] { "Editor" });
    this.a.j();
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    ak localak = this.a;
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = Integer.valueOf(paramInt1);
    arrayOfObject[1] = Integer.valueOf(paramInt2);
    arrayOfObject[2] = Integer.valueOf(paramInt3);
    arrayOfObject[3] = paramCharSequence;
    ak.a(localak, "beforeTextChanged, start = %s, count = %s, after = %s, s = %s", arrayOfObject);
    this.a.a(new String[] { "Editor" });
    ak.a(this.a, ak.b(this.a).getText());
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    ak localak = this.a;
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = Integer.valueOf(paramInt1);
    arrayOfObject[1] = Integer.valueOf(paramInt3);
    arrayOfObject[2] = Integer.valueOf(paramInt2);
    arrayOfObject[3] = paramCharSequence;
    ak.a(localak, "onTextChanged, start = %s, count = %s, before = %s, s = %s", arrayOfObject);
    this.a.a(new String[] { "Editor" });
    ak.a(this.a, ak.b(this.a).getText());
    Spannable localSpannable = this.a.k();
    int i = paramInt1 + paramInt3;
    if (ak.a(this.a, paramInt3))
    {
      MyStyleSpan[] arrayOfMyStyleSpan = (MyStyleSpan[])localSpannable.getSpans(paramInt1, i, MyStyleSpan.class);
      j.b(localSpannable, this.a.a(arrayOfMyStyleSpan), paramInt1, i);
      this.a.a(localSpannable, paramInt1, i);
      if (!this.a.a(paramInt2, paramInt3));
    }
    else
    {
      ak.a(this.a, "Expand spans on the left side:", new Object[0]);
      Iterator localIterator = this.a.a(localSpannable, paramInt1, new Class[] { StyleSpan.class, UnderlineSpan.class, ForegroundColorSpan.class, BackgroundColorSpan.class, RelativeSizeSpan.class, AbsoluteSizeSpan.class, TypefaceSpan.class, SubscriptSpan.class, SuperscriptSpan.class }).iterator();
      while (true)
      {
        if (!localIterator.hasNext())
        {
          ak.a(this.a, ak.b(this.a).getText());
          break;
        }
        Object localObject = localIterator.next();
        if (!j.a(localSpannable, localObject))
          localSpannable.setSpan(localObject, localSpannable.getSpanStart(localObject), i, 33);
      }
    }
    a(paramInt1, localSpannable, i);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.o
 * JD-Core Version:    0.6.2
 */