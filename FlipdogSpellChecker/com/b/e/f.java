package com.b.e;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class f<K, V>
{
  private HashMap<K, V> a = new HashMap();

  public V a(K paramK)
  {
    return this.a.get(paramK);
  }

  public Collection<V> a()
  {
    return this.a.values();
  }

  public void a(K paramK, V paramV)
  {
    this.a.put(paramK, paramV);
  }

  public int b()
  {
    return this.a.size();
  }

  public boolean b(K paramK)
  {
    return this.a.containsKey(paramK);
  }

  public V c(K paramK)
  {
    return this.a.remove(paramK);
  }

  public Set<K> c()
  {
    return this.a.keySet();
  }

  public void d()
  {
    this.a.clear();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.e.f
 * JD-Core Version:    0.6.2
 */