package com.google.inject;

public enum Stage
{
  static
  {
    DEVELOPMENT = new Stage("DEVELOPMENT", 1);
    PRODUCTION = new Stage("PRODUCTION", 2);
    Stage[] arrayOfStage = new Stage[3];
    arrayOfStage[0] = TOOL;
    arrayOfStage[1] = DEVELOPMENT;
    arrayOfStage[2] = PRODUCTION;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.Stage
 * JD-Core Version:    0.6.2
 */