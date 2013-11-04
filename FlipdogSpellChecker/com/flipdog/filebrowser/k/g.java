package com.flipdog.filebrowser.k;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.flipdog.commons.a.as;
import com.flipdog.commons.diagnostic.Track;

public class g
{
  private String a;

  private void a(ViewGroup paramViewGroup, String paramString)
  {
    for (int i = 0; ; i++)
    {
      if (i >= paramViewGroup.getChildCount())
        return;
      View localView = paramViewGroup.getChildAt(i);
      b(paramString, localView);
      if ((localView instanceof ViewGroup))
        a((ViewGroup)localView, paramString + " ");
    }
  }

  private void b(String paramString, View paramView)
  {
    if ((paramView instanceof TextView))
    {
      TextView localTextView = (TextView)paramView;
      String str2 = this.a;
      Object[] arrayOfObject2 = new Object[3];
      arrayOfObject2[0] = paramString;
      arrayOfObject2[1] = paramView;
      arrayOfObject2[2] = localTextView.getText();
      Track.me(str2, "%s%s (%s)", arrayOfObject2);
      return;
    }
    if ((paramView instanceof EditText))
    {
      EditText localEditText = (EditText)paramView;
      String str1 = this.a;
      Object[] arrayOfObject1 = new Object[3];
      arrayOfObject1[0] = paramString;
      arrayOfObject1[1] = paramView;
      arrayOfObject1[2] = localEditText.getText();
      Track.me(str1, "%s%s (%s)", arrayOfObject1);
      return;
    }
    Track.me(this.a, "%s%s", new Object[] { paramString, paramView });
  }

  public void a(String paramString, Activity paramActivity)
  {
    a(paramString, as.a(paramActivity, 16908290));
  }

  public void a(String paramString, View paramView)
  {
    this.a = paramString;
    b("", paramView);
    if ((paramView instanceof ViewGroup))
      a((ViewGroup)paramView, "");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.k.g
 * JD-Core Version:    0.6.2
 */