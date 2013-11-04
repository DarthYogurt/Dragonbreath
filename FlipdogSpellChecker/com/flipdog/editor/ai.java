package com.flipdog.editor;

import android.widget.EditText;

public class ai
{
  private static final int a = 16908333;
  private EditText b;
  private int c;
  private int d;

  private ai(EditText paramEditText)
  {
    this.b = paramEditText;
  }

  public static ai a(EditText paramEditText)
  {
    ai localai = new ai(paramEditText);
    localai.b();
    return localai;
  }

  private void b()
  {
    this.c = this.b.getSelectionStart();
    this.d = this.b.getSelectionEnd();
  }

  private boolean b(EditText paramEditText)
  {
    return paramEditText.getSelectionStart() == paramEditText.getSelectionEnd();
  }

  private boolean c()
  {
    return this.c == this.d;
  }

  public void a()
  {
    if ((b(this.b)) && (!c()))
    {
      this.b.setSelection(this.c, this.d);
      this.b.onTextContextMenuItem(16908333);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.ai
 * JD-Core Version:    0.6.2
 */