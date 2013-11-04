package com.flipdog.editor;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import com.flipdog.commons.s.a;
import java.util.ArrayList;
import java.util.Iterator;

public class MyEditText extends EditText
{
  private ArrayList<a> a = new ArrayList();
  private int b;

  public MyEditText(Context paramContext)
  {
    super(paramContext);
  }

  public MyEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public MyEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public int a()
  {
    return this.b;
  }

  public void a(a parama)
  {
    this.a.add(parama);
  }

  protected void onSelectionChanged(int paramInt1, int paramInt2)
  {
    super.onSelectionChanged(paramInt1, paramInt2);
    Iterator localIterator;
    if (this.a != null)
      localIterator = this.a.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((a)localIterator.next()).a(paramInt1, paramInt2);
    }
  }

  public boolean onTextContextMenuItem(int paramInt)
  {
    this.b = paramInt;
    try
    {
      boolean bool = super.onTextContextMenuItem(paramInt);
      return bool;
    }
    finally
    {
      this.b = -1;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.MyEditText
 * JD-Core Version:    0.6.2
 */