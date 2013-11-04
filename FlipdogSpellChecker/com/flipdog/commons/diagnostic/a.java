package com.flipdog.commons.diagnostic;

import java.io.PrintStream;

public class a
  implements f
{
  public void a(String paramString1, String paramString2)
  {
    PrintStream localPrintStream = System.out;
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = Long.valueOf(Thread.currentThread().getId());
    arrayOfObject[1] = paramString1;
    arrayOfObject[2] = paramString2;
    localPrintStream.println(String.format("[% 3d][%s] %s", arrayOfObject));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.diagnostic.a
 * JD-Core Version:    0.6.2
 */