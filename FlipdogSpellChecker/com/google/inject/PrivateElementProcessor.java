package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.Lists;
import com.google.inject.spi.PrivateElements;
import java.util.List;

class PrivateElementProcessor extends AbstractProcessor
{
  private final List<InjectorShell.Builder> injectorShellBuilders = Lists.newArrayList();
  private final Stage stage;

  PrivateElementProcessor(Errors paramErrors, Stage paramStage)
  {
    super(paramErrors);
    this.stage = paramStage;
  }

  public List<InjectorShell.Builder> getInjectorShellBuilders()
  {
    return this.injectorShellBuilders;
  }

  public Boolean visit(PrivateElements paramPrivateElements)
  {
    InjectorShell.Builder localBuilder = new InjectorShell.Builder().parent(this.injector).stage(this.stage).privateElements(paramPrivateElements);
    this.injectorShellBuilders.add(localBuilder);
    return Boolean.valueOf(true);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.PrivateElementProcessor
 * JD-Core Version:    0.6.2
 */