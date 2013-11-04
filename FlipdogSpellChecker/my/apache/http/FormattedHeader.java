package my.apache.http;

import my.apache.http.util.CharArrayBuffer;

public abstract interface FormattedHeader extends Header
{
  public abstract CharArrayBuffer getBuffer();

  public abstract int getValuePos();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.FormattedHeader
 * JD-Core Version:    0.6.2
 */