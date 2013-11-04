package com.android.vending.licensing;

import android.text.TextUtils;
import android.text.TextUtils.SimpleStringSplitter;
import android.text.TextUtils.StringSplitter;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

public class u
{
  public int a;
  public int b;
  public String c;
  public String d;
  public String e;
  public long f;
  public String g;
  public Map<String, String> h;

  public static u a(String paramString)
  {
    TextUtils.SimpleStringSplitter localSimpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
    localSimpleStringSplitter.setString(paramString);
    Iterator localIterator = localSimpleStringSplitter.iterator();
    if (!localIterator.hasNext())
      throw new IllegalArgumentException("Blank response.");
    String str1 = (String)localIterator.next();
    String str2 = "";
    if (localIterator.hasNext())
      str2 = (String)localIterator.next();
    String[] arrayOfString = TextUtils.split(str1, Pattern.quote("|"));
    if (arrayOfString.length < 6)
      throw new IllegalArgumentException("Wrong number of fields.");
    u localu = new u();
    localu.g = str2;
    localu.a = Integer.parseInt(arrayOfString[0]);
    localu.b = Integer.parseInt(arrayOfString[1]);
    localu.c = arrayOfString[2];
    localu.d = arrayOfString[3];
    localu.e = arrayOfString[4];
    localu.f = Long.parseLong(arrayOfString[5]);
    return localu;
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[6];
    arrayOfObject[0] = Integer.valueOf(this.a);
    arrayOfObject[1] = Integer.valueOf(this.b);
    arrayOfObject[2] = this.c;
    arrayOfObject[3] = this.d;
    arrayOfObject[4] = this.e;
    arrayOfObject[5] = Long.valueOf(this.f);
    return TextUtils.join("|", arrayOfObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.android.vending.licensing.u
 * JD-Core Version:    0.6.2
 */