package com.a.a.a;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class g extends c<List<Object>>
  implements Iterable<j>
{
  public g(List<Object> paramList)
  {
    super(paramList);
  }

  public g(List<Object> paramList, String paramString)
  {
    super(paramList, paramString);
  }

  private static String b(String paramString, int paramInt)
  {
    return j.a(paramString, Integer.toString(paramInt));
  }

  public int a()
  {
    return ((List)this.a).size();
  }

  public j a(int paramInt)
    throws b
  {
    if (paramInt >= ((List)this.a).size())
      throw a("expecting array to have an element at index " + paramInt + ", but it only has length " + ((List)this.a).size());
    return new j(((List)this.a).get(paramInt), b(this.b, paramInt));
  }

  public <T> ArrayList<T> a(f<T> paramf)
    throws b
  {
    ArrayList localArrayList = new ArrayList(a());
    Iterator localIterator = ((List)this.a).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localArrayList;
      localArrayList.add(paramf.a(new j(localIterator.next())));
    }
  }

  public void b(int paramInt)
    throws b
  {
    if (((List)this.a).size() != paramInt)
      throw a("expecting array to have length " + paramInt + ", but it has length " + ((List)this.a).size());
  }

  public Iterator<j> iterator()
  {
    return new h(this.b, ((List)this.a).iterator(), null);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.a.g
 * JD-Core Version:    0.6.2
 */