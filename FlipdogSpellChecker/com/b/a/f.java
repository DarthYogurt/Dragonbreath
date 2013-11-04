package com.b.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.flipdog.commons.a.as;
import com.flipdog.commons.i.b;
import java.io.File;
import java.util.Set;

public class f
{
  private static Set<String> a = as.c();

  public static void a(String paramString)
  {
    a.add(paramString);
  }

  public static SQLiteDatabase b(String paramString)
  {
    return c(((Context)b.a(Context.class)).getDatabasePath(paramString).getPath());
  }

  public static SQLiteDatabase c(String paramString)
  {
    File localFile = new File(paramString);
    localFile.getParentFile().mkdirs();
    if (a.contains(localFile.getName()))
      throw new SQLiteException("TEST");
    try
    {
      SQLiteDatabase localSQLiteDatabase = SQLiteDatabase.openDatabase(localFile.getAbsolutePath(), null, 0);
      return localSQLiteDatabase;
    }
    catch (SQLiteException localSQLiteException)
    {
    }
    return SQLiteDatabase.openOrCreateDatabase(localFile, null);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.a.f
 * JD-Core Version:    0.6.2
 */