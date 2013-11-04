package com.flipdog.editor.b;

import android.text.Editable;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import com.flipdog.commons.a.as;
import com.flipdog.commons.s.a;
import com.flipdog.editor.MyEditText;
import java.util.Iterator;
import java.util.List;

public class e
{
  private Editable a = new SpannableStringBuilder();
  private boolean b;
  private MyEditText c;
  private List<TextWatcher> d = as.b();
  private List<a> e = as.b();

  public e()
  {
    a(0);
  }

  public e(Editable paramEditable)
  {
    this.a = paramEditable;
    this.b = false;
  }

  public e(MyEditText paramMyEditText)
  {
    this.c = paramMyEditText;
    this.b = true;
  }

  private void a(Editable paramEditable)
  {
    Iterator localIterator = this.d.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((TextWatcher)localIterator.next()).afterTextChanged(paramEditable);
    }
  }

  private void a(Editable paramEditable, int paramInt1, int paramInt2, int paramInt3)
  {
    Iterator localIterator = this.d.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((TextWatcher)localIterator.next()).beforeTextChanged(paramEditable, paramInt1, paramInt2, paramInt3);
    }
  }

  private void b(int paramInt1, int paramInt2, CharSequence paramCharSequence)
  {
    Editable localEditable = b();
    int i = paramCharSequence.length();
    a(localEditable, paramInt1, paramInt2, i);
    localEditable.replace(paramInt1, paramInt1 + paramInt2, paramCharSequence);
    b(localEditable, paramInt1, paramInt2, i);
    a(localEditable);
  }

  private void b(Editable paramEditable, int paramInt1, int paramInt2, int paramInt3)
  {
    Iterator localIterator = this.d.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((TextWatcher)localIterator.next()).onTextChanged(paramEditable, paramInt1, paramInt2, paramInt3);
    }
  }

  private void d(int paramInt1, int paramInt2)
  {
    Iterator localIterator = this.e.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((a)localIterator.next()).a(paramInt1, paramInt2);
    }
  }

  private int f()
  {
    return Selection.getSelectionStart(b());
  }

  public void a()
  {
    int i = f();
    if (i == 0)
      return;
    a(i - 1, 1);
  }

  public void a(int paramInt)
  {
    c(paramInt, paramInt);
  }

  public void a(int paramInt1, int paramInt2)
  {
    b(paramInt1, paramInt2, "");
  }

  public void a(int paramInt1, int paramInt2, CharSequence paramCharSequence)
  {
    b(paramInt1, paramInt2 - paramInt1, paramCharSequence);
  }

  public void a(int paramInt, String paramString)
  {
    a(paramInt, paramInt, paramString);
  }

  public void a(TextWatcher paramTextWatcher)
  {
    if (this.b)
    {
      this.c.addTextChangedListener(paramTextWatcher);
      return;
    }
    this.d.add(paramTextWatcher);
  }

  public void a(a parama)
  {
    if (this.b)
    {
      this.c.a(parama);
      return;
    }
    this.e.add(parama);
  }

  public void a(CharSequence paramCharSequence)
  {
    Editable localEditable = b();
    localEditable.replace(0, localEditable.length(), paramCharSequence);
    a(0);
  }

  public void a(String paramString)
  {
    b(f(), 0, paramString);
  }

  public Editable b()
  {
    if (this.b)
      return this.c.getText();
    return this.a;
  }

  public void b(int paramInt1, int paramInt2)
  {
    a(paramInt1, paramInt2, "");
  }

  public void b(CharSequence paramCharSequence)
  {
    b(f(), 0, paramCharSequence);
  }

  public int c()
  {
    return Selection.getSelectionStart(b());
  }

  public void c(int paramInt1, int paramInt2)
  {
    Selection.setSelection(b(), paramInt1, paramInt2);
    d(paramInt1, paramInt2);
  }

  public int d()
  {
    return Selection.getSelectionEnd(b());
  }

  public int e()
  {
    if (this.b)
      return this.c.length();
    return this.a.length();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.b.e
 * JD-Core Version:    0.6.2
 */