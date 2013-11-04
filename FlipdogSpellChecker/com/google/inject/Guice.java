package com.google.inject;

import java.util.Arrays;

public final class Guice
{
  public static Injector createInjector(Stage paramStage, Iterable<? extends Module> paramIterable)
  {
    return new InjectorBuilder().stage(paramStage).addModules(paramIterable).build();
  }

  public static Injector createInjector(Stage paramStage, Module[] paramArrayOfModule)
  {
    return createInjector(paramStage, Arrays.asList(paramArrayOfModule));
  }

  public static Injector createInjector(Iterable<? extends Module> paramIterable)
  {
    return createInjector(Stage.DEVELOPMENT, paramIterable);
  }

  public static Injector createInjector(Module[] paramArrayOfModule)
  {
    return createInjector(Arrays.asList(paramArrayOfModule));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.Guice
 * JD-Core Version:    0.6.2
 */