package com.b.e;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class b<K1, K2, V>
{
  private f<K1, HashMap<K2, V>> a = new f();

  public int a()
  {
    Iterator localIterator = this.a.c().iterator();
    int i = 0;
    while (true)
    {
      if (!localIterator.hasNext())
        return i;
      Object localObject = (Object)localIterator.next();
      i += ((HashMap)this.a.a(localObject)).size();
    }
  }

  public V a(K1 paramK1, K2 paramK2)
  {
    HashMap localHashMap = (HashMap)this.a.a(paramK1);
    if (localHashMap == null)
      return null;
    return localHashMap.get(paramK2);
  }

  public void a(K1 paramK1)
  {
    this.a.c(paramK1);
  }

  public void a(K1 paramK1, K2 paramK2, V paramV)
  {
    if (!this.a.b(paramK1))
      this.a.a(paramK1, new HashMap());
    ((HashMap)this.a.a(paramK1)).put(paramK2, paramV);
  }

  public void b()
  {
    this.a.d();
  }

  public boolean b(K1 paramK1, K2 paramK2)
  {
    if (!this.a.b(paramK1))
      return false;
    return ((HashMap)this.a.a(paramK1)).containsKey(paramK2);
  }

  public void c(K1 paramK1, K2 paramK2)
  {
    HashMap localHashMap = (HashMap)this.a.a(paramK1);
    if (localHashMap == null)
      return;
    localHashMap.remove(paramK2);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.e.b
 * JD-Core Version:    0.6.2
 */