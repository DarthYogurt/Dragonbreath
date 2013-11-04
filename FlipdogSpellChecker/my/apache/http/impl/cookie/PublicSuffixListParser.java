package my.apache.http.impl.cookie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import my.apache.http.annotation.Immutable;

@Immutable
public class PublicSuffixListParser
{
  private static final int MAX_LINE_LEN = 256;
  private final PublicSuffixFilter filter;

  PublicSuffixListParser(PublicSuffixFilter paramPublicSuffixFilter)
  {
    this.filter = paramPublicSuffixFilter;
  }

  private boolean readLine(Reader paramReader, StringBuilder paramStringBuilder)
    throws IOException
  {
    paramStringBuilder.setLength(0);
    int i = 0;
    do
    {
      int j = paramReader.read();
      if (j == -1);
      char c;
      do
      {
        boolean bool = false;
        if (j != -1)
          bool = true;
        return bool;
        c = (char)j;
      }
      while (c == '\n');
      if (Character.isWhitespace(c))
        i = 1;
      if (i == 0)
        paramStringBuilder.append(c);
    }
    while (paramStringBuilder.length() <= 256);
    throw new IOException("Line too long");
  }

  public void parse(Reader paramReader)
    throws IOException
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    BufferedReader localBufferedReader = new BufferedReader(paramReader);
    StringBuilder localStringBuilder = new StringBuilder(256);
    boolean bool1 = true;
    while (true)
    {
      if (!bool1)
      {
        this.filter.setPublicSuffixes(localArrayList1);
        this.filter.setExceptions(localArrayList2);
        return;
      }
      bool1 = readLine(localBufferedReader, localStringBuilder);
      String str = localStringBuilder.toString();
      if ((str.length() != 0) && (!str.startsWith("//")))
      {
        if (str.startsWith("."))
          str = str.substring(1);
        boolean bool2 = str.startsWith("!");
        if (bool2)
          str = str.substring(1);
        if (bool2)
          localArrayList2.add(str);
        else
          localArrayList1.add(str);
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.cookie.PublicSuffixListParser
 * JD-Core Version:    0.6.2
 */