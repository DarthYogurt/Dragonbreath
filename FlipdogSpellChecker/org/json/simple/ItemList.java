package org.json.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ItemList
{
  List items = new ArrayList();
  private String sp = ",";

  public ItemList()
  {
  }

  public ItemList(String paramString)
  {
    split(paramString, this.sp, this.items);
  }

  public ItemList(String paramString1, String paramString2)
  {
    this.sp = paramString1;
    split(paramString1, paramString2, this.items);
  }

  public ItemList(String paramString1, String paramString2, boolean paramBoolean)
  {
    split(paramString1, paramString2, this.items, paramBoolean);
  }

  public void add(int paramInt, String paramString)
  {
    if (paramString == null)
      return;
    this.items.add(paramInt, paramString.trim());
  }

  public void add(String paramString)
  {
    if (paramString == null)
      return;
    this.items.add(paramString.trim());
  }

  public void addAll(String paramString)
  {
    split(paramString, this.sp, this.items);
  }

  public void addAll(String paramString1, String paramString2)
  {
    split(paramString1, paramString2, this.items);
  }

  public void addAll(String paramString1, String paramString2, boolean paramBoolean)
  {
    split(paramString1, paramString2, this.items, paramBoolean);
  }

  public void addAll(ItemList paramItemList)
  {
    this.items.addAll(paramItemList.items);
  }

  public void clear()
  {
    this.items.clear();
  }

  public String get(int paramInt)
  {
    return (String)this.items.get(paramInt);
  }

  public String[] getArray()
  {
    return (String[])this.items.toArray();
  }

  public List getItems()
  {
    return this.items;
  }

  public void reset()
  {
    this.sp = ",";
    this.items.clear();
  }

  public void setSP(String paramString)
  {
    this.sp = paramString;
  }

  public int size()
  {
    return this.items.size();
  }

  public void split(String paramString1, String paramString2, List paramList)
  {
    if ((paramString1 == null) || (paramString2 == null))
      return;
    int k;
    for (int i = 0; ; i = k)
    {
      int j = paramString1.indexOf(paramString2, i);
      if (j == -1);
      do
      {
        paramList.add(paramString1.substring(i).trim());
        return;
        paramList.add(paramString1.substring(i, j).trim());
        k = j + paramString2.length();
      }
      while (k == -1);
    }
  }

  public void split(String paramString1, String paramString2, List paramList, boolean paramBoolean)
  {
    if ((paramString1 == null) || (paramString2 == null));
    while (true)
    {
      return;
      if (!paramBoolean)
        break;
      StringTokenizer localStringTokenizer = new StringTokenizer(paramString1, paramString2);
      while (localStringTokenizer.hasMoreTokens())
        paramList.add(localStringTokenizer.nextToken().trim());
    }
    split(paramString1, paramString2, paramList);
  }

  public String toString()
  {
    return toString(this.sp);
  }

  public String toString(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    if (i < this.items.size())
    {
      if (i == 0)
        localStringBuffer.append(this.items.get(i));
      while (true)
      {
        i++;
        break;
        localStringBuffer.append(paramString);
        localStringBuffer.append(this.items.get(i));
      }
    }
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     org.json.simple.ItemList
 * JD-Core Version:    0.6.2
 */