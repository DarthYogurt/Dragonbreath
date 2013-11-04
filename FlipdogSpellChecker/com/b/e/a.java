package com.b.e;

import java.util.HashMap;

public class a<K1, K2, K3, V>
{
  private b<K1, K2, HashMap<K3, V>> a = new b();

  public V a(K1 paramK1, K2 paramK2, K3 paramK3)
  {
    HashMap localHashMap = (HashMap)this.a.a(paramK1, paramK2);
    if (localHashMap == null)
      return null;
    return localHashMap.get(paramK3);
  }

  public void a(K1 paramK1)
  {
    this.a.a(paramK1);
  }

  public void a(K1 paramK1, K2 paramK2, K3 paramK3, V paramV)
  {
    if (!this.a.b(paramK1, paramK2))
      this.a.a(paramK1, paramK2, new HashMap());
    ((HashMap)this.a.a(paramK1, paramK2)).put(paramK3, paramV);
  }

  public boolean b(K1 paramK1, K2 paramK2, K3 paramK3)
  {
    if (!this.a.b(paramK1, paramK2))
      return false;
    return ((HashMap)this.a.a(paramK1, paramK2)).containsKey(paramK3);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.e.a
 * JD-Core Version:    0.6.2
 */