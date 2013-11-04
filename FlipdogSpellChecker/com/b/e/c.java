package com.b.e;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class c<Key, T>
{
  private f<Key, ArrayList<T>> a = new f();

  public List<T> a(Key paramKey)
  {
    if (!this.a.b(paramKey))
      return new ArrayList();
    return (List)this.a.a(paramKey);
  }

  public Set<Key> a()
  {
    return this.a.c();
  }

  public void a(Key paramKey, T paramT)
  {
    if (!this.a.b(paramKey))
      this.a.a(paramKey, new ArrayList());
    ((ArrayList)this.a.a(paramKey)).add(paramT);
  }

  public List<T> b(Key paramKey)
  {
    List localList = (List)this.a.c(paramKey);
    if (localList == null)
      localList = Collections.emptyList();
    return localList;
  }

  public void b(Key paramKey, T paramT)
  {
    if (!this.a.b(paramKey))
      return;
    ArrayList localArrayList = (ArrayList)this.a.a(paramKey);
    while (true)
    {
      if (!localArrayList.contains(paramT))
      {
        if (localArrayList.size() != 0)
          break;
        this.a.c(paramKey);
        return;
      }
      localArrayList.remove(paramT);
    }
  }

  public boolean c(Key paramKey)
  {
    return this.a.b(paramKey);
  }

  public boolean c(Key paramKey, T paramT)
  {
    ArrayList localArrayList = (ArrayList)this.a.a(paramKey);
    if (localArrayList == null)
      return false;
    return localArrayList.contains(paramT);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.e.c
 * JD-Core Version:    0.6.2
 */