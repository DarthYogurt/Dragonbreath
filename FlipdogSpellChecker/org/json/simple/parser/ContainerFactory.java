package org.json.simple.parser;

import java.util.List;
import java.util.Map;

public abstract interface ContainerFactory
{
  public abstract List creatArrayContainer();

  public abstract Map createObjectContainer();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     org.json.simple.parser.ContainerFactory
 * JD-Core Version:    0.6.2
 */