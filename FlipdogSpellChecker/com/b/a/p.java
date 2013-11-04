package com.b.a;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import java.util.HashMap;
import java.util.Map;

public class p
  implements Cursor
{
  private Cursor a;
  private Map<String, Integer> b;

  public p(Cursor paramCursor)
  {
    this.a = paramCursor;
  }

  public void close()
  {
    this.a.close();
  }

  public void copyStringToBuffer(int paramInt, CharArrayBuffer paramCharArrayBuffer)
  {
    this.a.copyStringToBuffer(paramInt, paramCharArrayBuffer);
  }

  public void deactivate()
  {
    this.a.deactivate();
  }

  public byte[] getBlob(int paramInt)
  {
    return this.a.getBlob(paramInt);
  }

  public int getColumnCount()
  {
    return this.a.getColumnCount();
  }

  public int getColumnIndex(String paramString)
  {
    String[] arrayOfString;
    int i;
    HashMap localHashMap;
    if (this.b == null)
    {
      arrayOfString = this.a.getColumnNames();
      i = arrayOfString.length;
      localHashMap = new HashMap(i, 1.0F);
    }
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        this.b = localHashMap;
        Integer localInteger = (Integer)this.b.get(paramString);
        if (localInteger == null)
          break;
        return localInteger.intValue();
      }
      localHashMap.put(arrayOfString[j], Integer.valueOf(j));
    }
    return -1;
  }

  public int getColumnIndexOrThrow(String paramString)
    throws IllegalArgumentException
  {
    return this.a.getColumnIndexOrThrow(paramString);
  }

  public String getColumnName(int paramInt)
  {
    return this.a.getColumnName(paramInt);
  }

  public String[] getColumnNames()
  {
    return this.a.getColumnNames();
  }

  public int getCount()
  {
    return this.a.getCount();
  }

  public double getDouble(int paramInt)
  {
    return this.a.getDouble(paramInt);
  }

  public Bundle getExtras()
  {
    return this.a.getExtras();
  }

  public float getFloat(int paramInt)
  {
    return this.a.getFloat(paramInt);
  }

  public int getInt(int paramInt)
  {
    return this.a.getInt(paramInt);
  }

  public long getLong(int paramInt)
  {
    return this.a.getLong(paramInt);
  }

  public int getPosition()
  {
    return this.a.getPosition();
  }

  public short getShort(int paramInt)
  {
    return this.a.getShort(paramInt);
  }

  public String getString(int paramInt)
  {
    return this.a.getString(paramInt);
  }

  public int getType(int paramInt)
  {
    throw new RuntimeException("Unsupported.");
  }

  public boolean getWantsAllOnMoveCalls()
  {
    return this.a.getWantsAllOnMoveCalls();
  }

  public boolean isAfterLast()
  {
    return this.a.isAfterLast();
  }

  public boolean isBeforeFirst()
  {
    return this.a.isBeforeFirst();
  }

  public boolean isClosed()
  {
    return this.a.isClosed();
  }

  public boolean isFirst()
  {
    return this.a.isFirst();
  }

  public boolean isLast()
  {
    return this.a.isLast();
  }

  public boolean isNull(int paramInt)
  {
    return this.a.isNull(paramInt);
  }

  public boolean move(int paramInt)
  {
    return this.a.move(paramInt);
  }

  public boolean moveToFirst()
  {
    return this.a.moveToFirst();
  }

  public boolean moveToLast()
  {
    return this.a.moveToLast();
  }

  public boolean moveToNext()
  {
    return this.a.moveToNext();
  }

  public boolean moveToPosition(int paramInt)
  {
    return this.a.moveToPosition(paramInt);
  }

  public boolean moveToPrevious()
  {
    return this.a.moveToPrevious();
  }

  public void registerContentObserver(ContentObserver paramContentObserver)
  {
    this.a.registerContentObserver(paramContentObserver);
  }

  public void registerDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    this.a.registerDataSetObserver(paramDataSetObserver);
  }

  public boolean requery()
  {
    return this.a.requery();
  }

  public Bundle respond(Bundle paramBundle)
  {
    return this.a.respond(paramBundle);
  }

  public void setNotificationUri(ContentResolver paramContentResolver, Uri paramUri)
  {
    this.a.setNotificationUri(paramContentResolver, paramUri);
  }

  public void unregisterContentObserver(ContentObserver paramContentObserver)
  {
    this.a.unregisterContentObserver(paramContentObserver);
  }

  public void unregisterDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    this.a.unregisterDataSetObserver(paramDataSetObserver);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.a.p
 * JD-Core Version:    0.6.2
 */