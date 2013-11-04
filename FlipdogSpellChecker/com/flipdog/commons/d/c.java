package com.flipdog.commons.d;

import android.text.Editable;
import android.text.TextWatcher;
import java.io.PrintStream;

public class c
  implements TextWatcher
{
  public void afterTextChanged(Editable paramEditable)
  {
    System.out.println(String.format("afterTextChanged(s = %s)", new Object[] { paramEditable }));
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    PrintStream localPrintStream = System.out;
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = paramCharSequence;
    arrayOfObject[1] = Integer.valueOf(paramInt1);
    arrayOfObject[2] = Integer.valueOf(paramInt2);
    arrayOfObject[3] = Integer.valueOf(paramInt3);
    localPrintStream.println(String.format("beforeTextChanged(s = %s, start = %s, count = %s, after = %s)", arrayOfObject));
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    PrintStream localPrintStream = System.out;
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = paramCharSequence;
    arrayOfObject[1] = Integer.valueOf(paramInt1);
    arrayOfObject[2] = Integer.valueOf(paramInt2);
    arrayOfObject[3] = Integer.valueOf(paramInt3);
    localPrintStream.println(String.format("onTextChanged(s = %s, start = %s, before = %s, count = %s)", arrayOfObject));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.d.c
 * JD-Core Version:    0.6.2
 */