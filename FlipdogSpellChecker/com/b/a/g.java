package com.b.a;

import com.flipdog.commons.a.ax;

public class g
{
  private String a;
  private String[] b;
  private String[] c;
  private String d;
  private String e;
  private String f;
  private String g;

  public g(String paramString1, String paramString2)
  {
    this.a = paramString1;
    a(paramString2.split(","));
    k();
  }

  public g(String paramString, String[] paramArrayOfString)
  {
    this.a = paramString;
    a(paramArrayOfString);
    k();
  }

  public static g a(String paramString, String[] paramArrayOfString)
  {
    return new g(paramString, paramArrayOfString);
  }

  private void a(String[] paramArrayOfString)
  {
    int i = 0;
    this.c = new String[paramArrayOfString.length];
    this.b = new String[1 + paramArrayOfString.length];
    this.b[0] = "id";
    while (true)
    {
      if (i >= paramArrayOfString.length)
        return;
      this.c[i] = paramArrayOfString[i].trim();
      this.b[(i + 1)] = paramArrayOfString[i].trim();
      i++;
    }
  }

  private void k()
  {
    this.d = ax.a(this.b, ",");
    this.e = ax.a(this.c, ",");
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; ; i++)
    {
      if (i >= this.c.length)
      {
        this.f = localStringBuilder.toString();
        this.g = (ax.a(this.c, "=?, ") + "=? ");
        return;
      }
      if (localStringBuilder.length() != 0)
        localStringBuilder.append(", ");
      localStringBuilder.append("?");
    }
  }

  public String a()
  {
    return this.d;
  }

  public String a(String paramString)
  {
    return paramString + "." + ax.a(this.b, new StringBuilder(", ").append(paramString).append(".").toString());
  }

  public String b()
  {
    return this.e;
  }

  public String b(String paramString)
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = this.a;
    arrayOfObject[1] = this.g;
    arrayOfObject[2] = paramString;
    return String.format("UPDATE %s SET %s %s", arrayOfObject);
  }

  public String c()
  {
    return this.f;
  }

  public String c(String paramString)
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = this.d;
    arrayOfObject[1] = this.a;
    arrayOfObject[2] = paramString;
    return String.format("SELECT %s FROM %s %s", arrayOfObject);
  }

  public String d()
  {
    return this.g;
  }

  public String d(String paramString)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.a;
    arrayOfObject[1] = paramString;
    return String.format("SELECT COUNT(*) FROM %s %s", arrayOfObject);
  }

  public String e()
  {
    return b("WHERE id=?");
  }

  public String e(String paramString)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.a;
    arrayOfObject[1] = paramString;
    return String.format("DELETE FROM %s %s", arrayOfObject);
  }

  public String f()
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = this.a;
    arrayOfObject[1] = this.e;
    arrayOfObject[2] = this.f;
    return String.format("INSERT INTO %s (%s) VALUES(%s)", arrayOfObject);
  }

  public String g()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.d;
    arrayOfObject[1] = this.a;
    return String.format("SELECT %s FROM %s", arrayOfObject);
  }

  public String h()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.d;
    arrayOfObject[1] = this.a;
    return String.format("SELECT %s FROM %s WHERE id = ?", arrayOfObject);
  }

  public String i()
  {
    return d("");
  }

  public String j()
  {
    return this.a;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.a.g
 * JD-Core Version:    0.6.2
 */