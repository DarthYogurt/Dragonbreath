package com.flipdog.speller;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Layout;
import android.text.Spannable;
import android.text.TextPaint;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.flipdog.activity.MyActivity;
import com.flipdog.m;

public class r
{
  private EditText a;
  private LayoutInflater b;
  private PopupWindow c;
  private MyActivity d;
  private WindowManager e;
  private View f;
  private com.flipdog.commons.s.b g;
  private com.b.b.h h = new com.b.b.h();
  private n i;

  public r(MyActivity paramMyActivity, EditText paramEditText, n paramn)
  {
    this.d = paramMyActivity;
    this.a = paramEditText;
    this.i = paramn;
    this.b = ((LayoutInflater)paramMyActivity.getSystemService("layout_inflater"));
    this.e = ((WindowManager)a().getSystemService("window"));
    this.f = this.b.inflate(com.flipdog.p.speller_suggestions_list_item, null);
    this.f.measure(-2, -2);
    this.g = ((com.flipdog.commons.s.b)com.flipdog.commons.i.b.a(com.flipdog.commons.s.b.class));
    c();
    d();
  }

  private int a(int paramInt1, Drawable paramDrawable, int paramInt2)
  {
    return paramInt1 * (paramInt2 + this.f.getMeasuredHeight()) + paramDrawable.getMinimumHeight();
  }

  private int a(String[] paramArrayOfString, Drawable paramDrawable, TextPaint paramTextPaint)
  {
    return a(paramArrayOfString, paramTextPaint) + paramDrawable.getMinimumWidth();
  }

  private int a(String[] paramArrayOfString, TextPaint paramTextPaint)
  {
    int j = -1;
    int k = paramArrayOfString.length;
    int m = 0;
    if (m >= k)
      return j;
    int n = (int)paramTextPaint.measureText(paramArrayOfString[m]);
    if (n > j);
    while (true)
    {
      m++;
      j = n;
      break;
      n = j;
    }
  }

  private void a(View paramView, int paramInt)
  {
    FrameLayout localFrameLayout = (FrameLayout)paramView.getParent();
    WindowManager.LayoutParams localLayoutParams = (WindowManager.LayoutParams)localFrameLayout.getLayoutParams();
    localLayoutParams.width = paramInt;
    localLayoutParams.flags = (0x20 | localLayoutParams.flags);
    this.e.updateViewLayout(localFrameLayout, localLayoutParams);
  }

  private void a(o paramo, MotionEvent paramMotionEvent)
  {
    a(paramo, paramo.a(), paramMotionEvent);
  }

  private void a(o paramo, s params, MotionEvent paramMotionEvent)
  {
    if (this.c != null)
      this.c.dismiss();
    View localView = this.b.inflate(com.flipdog.p.speller_suggestions, null);
    this.c = new PopupWindow(localView, 50, 50);
    this.c.setWidth(-2);
    this.c.setHeight(-2);
    this.c.setFocusable(true);
    this.c.setTouchable(true);
    this.c.setOutsideTouchable(true);
    this.c.setInputMethodMode(1);
    Drawable localDrawable = a().getResources().getDrawable(17301505);
    this.c.setBackgroundDrawable(localDrawable);
    ListView localListView = (ListView)localView.findViewById(m.list);
    localListView.setAdapter(new p(a(), params.d));
    localListView.setOnItemClickListener(new f(this, paramo, params));
    this.a.setOnKeyListener(new e(this));
    this.c.setOnDismissListener(new g(this));
    float f1 = paramMotionEvent.getY();
    float f2 = paramMotionEvent.getX();
    float f3 = paramMotionEvent.getRawY();
    int j = (int)f2;
    int k = -1 * (this.a.getHeight() - (int)f1);
    if (f3 > e() / 2);
    for (int m = k - a(params.d.length, localDrawable, 2); ; m = k + 10)
    {
      this.c.showAsDropDown(this.a, j, m);
      TextPaint localTextPaint = ((TextView)this.f.findViewById(m.text)).getPaint();
      a(localView, a(params.d, localDrawable, localTextPaint));
      return;
    }
  }

  private void c()
  {
    this.a.setOnTouchListener(new i(this));
  }

  private void d()
  {
    this.g.a(this.h, new h(this));
  }

  private int e()
  {
    return this.e.getDefaultDisplay().getHeight();
  }

  protected Activity a()
  {
    return this.d;
  }

  protected void a(TextView paramTextView, MotionEvent paramMotionEvent)
  {
    Editable localEditable = paramTextView.getEditableText();
    int j = paramMotionEvent.getAction();
    int i5;
    if ((j == 1) || (j == 0))
    {
      int k = (int)paramMotionEvent.getX();
      int m = (int)paramMotionEvent.getY();
      int n = k - paramTextView.getTotalPaddingLeft();
      int i1 = m - paramTextView.getTotalPaddingTop();
      int i2 = n + paramTextView.getScrollX();
      int i3 = i1 + paramTextView.getScrollY();
      Layout localLayout = paramTextView.getLayout();
      int i4 = localLayout.getLineForVertical(i3);
      i5 = localLayout.getOffsetForHorizontal(i4, i2);
      if (localLayout.getLineBottom(i4) >= i3)
        break label112;
    }
    label112: o[] arrayOfo;
    do
    {
      return;
      arrayOfo = (o[])localEditable.getSpans(i5, i5, o.class);
    }
    while ((arrayOfo.length == 0) || (j != 1));
    arrayOfo[0].a(paramTextView, paramMotionEvent);
  }

  protected void a(o paramo, s params, int paramInt)
  {
    Editable localEditable = this.a.getText();
    localEditable.replace(localEditable.getSpanStart(paramo), localEditable.getSpanEnd(paramo), params.d[paramInt]);
    localEditable.removeSpan(paramo);
    this.c.dismiss();
  }

  public void b()
  {
    this.i.a();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.speller.r
 * JD-Core Version:    0.6.2
 */