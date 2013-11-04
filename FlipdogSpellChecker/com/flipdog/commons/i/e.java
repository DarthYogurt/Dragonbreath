package com.flipdog.commons.i;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class e
  implements a
{
  private Injector a;

  public e(Module paramModule)
  {
    this.a = Guice.createInjector(new Module[] { paramModule });
  }

  public Injector a()
  {
    return this.a;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.i.e
 * JD-Core Version:    0.6.2
 */