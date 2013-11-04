package com.flipdog.spellchecker;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.flipdog.commons.a.as;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MyDataContentProvider extends ContentProvider
{
  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    as.f(ac.a(paramUri));
    return 1;
  }

  public String getType(Uri paramUri)
  {
    return null;
  }

  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    return null;
  }

  public boolean onCreate()
  {
    return false;
  }

  public ParcelFileDescriptor openFile(Uri paramUri, String paramString)
    throws FileNotFoundException
  {
    return ParcelFileDescriptor.open(ac.a(paramUri), 268435456);
  }

  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    File localFile = ac.a(paramUri);
    ArrayList localArrayList = new ArrayList();
    int i = paramArrayOfString1.length;
    int j = 0;
    if (j >= i)
    {
      MatrixCursor localMatrixCursor = new MatrixCursor(paramArrayOfString1);
      localMatrixCursor.addRow(localArrayList);
      return localMatrixCursor;
    }
    String str = paramArrayOfString1[j];
    if (str.equals("_display_name"))
      localArrayList.add(localFile.getName());
    while (true)
    {
      j++;
      break;
      if (str.equals("_size"))
        localArrayList.add(Long.valueOf(localFile.length()));
    }
  }

  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    return 0;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.spellchecker.MyDataContentProvider
 * JD-Core Version:    0.6.2
 */