package com.a.a.c;

import java.io.BufferedReader;
import java.io.IOException;

public class h extends i
{
  private static final long a = 1L;

  public h(BufferedReader paramBufferedReader)
  {
    super("failed to parse: " + a(paramBufferedReader));
  }

  public h(String paramString)
  {
    super(paramString);
  }

  public static String a(BufferedReader paramBufferedReader)
  {
    if (paramBufferedReader != null);
    try
    {
      paramBufferedReader.reset();
      label8: StringBuffer localStringBuffer = new StringBuffer();
      try
      {
        while (true)
        {
          String str = paramBufferedReader.readLine();
          if (str == null)
            label25: return localStringBuffer.toString();
          localStringBuffer.append(str);
        }
      }
      catch (IOException localIOException1)
      {
        break label25;
      }
    }
    catch (IOException localIOException2)
    {
      break label8;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.c.h
 * JD-Core Version:    0.6.2
 */