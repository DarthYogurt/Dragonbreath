package com.adwhirl.obj;

public class Ration
  implements Comparable<Ration>
{
  public String key = "";
  public String key2 = "";
  public String name = "";
  public String nid = "";
  public int priority = 0;
  public int type = 0;
  public double weight = 0.0D;

  public int compareTo(Ration paramRation)
  {
    int i = paramRation.priority;
    if (this.priority < i)
      return -1;
    if (this.priority > i)
      return 1;
    return 0;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.adwhirl.obj.Ration
 * JD-Core Version:    0.6.2
 */