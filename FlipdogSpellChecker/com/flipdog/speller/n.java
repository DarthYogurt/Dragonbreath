package com.flipdog.speller;

import android.content.Context;
import android.text.Editable;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.b.i;
import com.flipdog.activity.MyActivity;
import com.flipdog.commons.a.e;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.commons.f.c;
import com.flipdog.commons.i.b;
import java.util.Iterator;
import java.util.List;

public class n
{
  private EditText a;
  private ProgressBar b;
  private MyActivity c;

  public n(MyActivity paramMyActivity, EditText paramEditText, ProgressBar paramProgressBar)
  {
    this.c = paramMyActivity;
    this.a = paramEditText;
    this.b = paramProgressBar;
  }

  private String a(int paramInt)
  {
    return ((i)b.a(i.class)).a(paramInt);
  }

  private void a(MyActivity paramMyActivity, String paramString)
  {
    com.flipdog.commons.a.a.a(paramMyActivity, paramString);
  }

  private void a(Exception paramException)
  {
    Track.it(paramException);
    d();
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = b();
    arrayOfObject[1] = com.flipdog.editor.c.a.a(paramException);
    String str = String.format("%s %s", arrayOfObject);
    a(this.c, str);
  }

  private void a(List<s> paramList)
  {
    d();
    if (paramList.size() == 0)
      e.a(a(com.flipdog.s.success));
    Editable localEditable = this.a.getText();
    int i = localEditable.length();
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      if (!localIterator.hasNext());
      o localo;
      int j;
      int k;
      do
      {
        this.a.requestFocus();
        return;
        s locals = (s)localIterator.next();
        localo = new o(locals);
        j = locals.a;
        k = j + locals.b;
      }
      while (k > i);
      localEditable.setSpan(localo, j, k, 33);
    }
  }

  private String b()
  {
    return a(com.flipdog.s.cant_spell_check);
  }

  private void c()
  {
    this.b.setVisibility(0);
  }

  private void d()
  {
    this.b.setVisibility(8);
  }

  private String e()
  {
    return d.a().a;
  }

  protected List<s> a(Context paramContext, String paramString1, String paramString2)
    throws Exception
  {
    return w.a(paramString1, paramString2);
  }

  public void a()
  {
    int i = 0;
    Editable localEditable = this.a.getText();
    o[] arrayOfo = (o[])localEditable.getSpans(0, localEditable.length(), o.class);
    int j = arrayOfo.length;
    while (true)
    {
      if (i >= j)
      {
        String str1 = localEditable.toString();
        String str2 = e();
        c();
        c.a(new v(this, str1, str2));
        return;
      }
      localEditable.removeSpan(arrayOfo[i]);
      i++;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.speller.n
 * JD-Core Version:    0.6.2
 */