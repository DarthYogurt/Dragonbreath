package com.b.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.b.a;

public class o
  implements h
{
  private SQLiteDatabase a;

  public o(SQLiteDatabase paramSQLiteDatabase)
  {
    this.a = paramSQLiteDatabase;
  }

  private void b(String paramString)
  {
    a.a(paramString, o.class);
  }

  public Cursor a(String paramString, String[] paramArrayOfString)
  {
    b(paramString);
    return this.a.rawQuery(paramString, paramArrayOfString);
  }

  public void a()
  {
    b("begin transaction");
    this.a.beginTransaction();
  }

  public void a(String paramString)
  {
    b(paramString);
    this.a.execSQL(paramString);
  }

  public void a(String paramString1, ContentValues paramContentValues, String paramString2, String[] paramArrayOfString)
  {
    b("update");
    this.a.update(paramString1, paramContentValues, paramString2, paramArrayOfString);
  }

  public void a(String paramString1, String paramString2, ContentValues paramContentValues)
  {
    b("insert");
    this.a.insert(paramString1, paramString2, paramContentValues);
  }

  public void a(String paramString, Object[] paramArrayOfObject)
  {
    b(paramString);
    this.a.execSQL(paramString, paramArrayOfObject);
  }

  public void b()
  {
    this.a.setTransactionSuccessful();
  }

  public void c()
  {
    this.a.endTransaction();
    b("transaction completed");
  }

  public void d()
  {
    this.a.close();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.a.o
 * JD-Core Version:    0.6.2
 */