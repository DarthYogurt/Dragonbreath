package com.flipdog.commons.n;

import android.database.DataSetObserver;

class a extends DataSetObserver
{
  a(b paramb)
  {
  }

  public void onChanged()
  {
    this.a.notifyDataSetChanged();
  }

  public void onInvalidated()
  {
    this.a.notifyDataSetInvalidated();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.n.a
 * JD-Core Version:    0.6.2
 */