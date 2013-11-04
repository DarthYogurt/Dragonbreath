package com.flipdog.commons.r;

import android.content.Context;
import android.os.Environment;
import android.os.Process;
import com.flipdog.commons.a.ay;
import com.flipdog.commons.a.c;
import com.flipdog.commons.diagnostic.Track;
import java.io.File;
import java.lang.reflect.Method;

public class a
  implements b
{
  private static final String a = "user";
  private File b = h();

  private Context f()
  {
    return (Context)com.flipdog.commons.i.b.a(Context.class);
  }

  private String g()
  {
    return f().getPackageName();
  }

  private File h()
  {
    File localFile = Environment.getExternalStorageDirectory();
    Object localObject1 = f().getSystemService("user");
    if (localObject1 == null)
      return new File(localFile, g());
    Object localObject2 = c.a(Process.class, "myUserHandle", new Object[0]);
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = localObject2.getClass();
    arrayOfObject1[1] = localObject2;
    long l = ((Long)c.a(localObject1, "getSerialNumberForUser", arrayOfObject1)).longValue();
    if (l == -1L)
      throw new RuntimeException();
    Object[] arrayOfObject2 = new Object[1];
    arrayOfObject2[0] = Long.valueOf(l);
    String str = String.format("/%s", arrayOfObject2);
    if (localFile.getPath().contains(str))
      return new File(localFile, g());
    Object[] arrayOfObject3 = new Object[2];
    arrayOfObject3[0] = g();
    arrayOfObject3[1] = Long.valueOf(l);
    return new File(localFile, String.format("%s/users/%s", arrayOfObject3));
  }

  public File a(String paramString)
  {
    if (ay.b() >= 8)
      try
      {
        Context localContext = f();
        File localFile = (File)localContext.getClass().getMethod("getExternalFilesDir", new Class[] { String.class }).invoke(localContext, new Object[] { paramString });
        return localFile;
      }
      catch (Exception localException)
      {
        Track.it(localException);
      }
    String str = g();
    return new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + str + "/files");
  }

  public String a()
  {
    return f().getDatabasePath("dummy.db").getParent();
  }

  public String b()
  {
    return f().getFilesDir().toString();
  }

  public String c()
  {
    return this.b.getAbsolutePath();
  }

  public String d()
  {
    return Environment.getExternalStorageDirectory().getPath();
  }

  public String e()
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = f().getFilesDir().getParent();
    return String.format("%s/", arrayOfObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.r.a
 * JD-Core Version:    0.6.2
 */