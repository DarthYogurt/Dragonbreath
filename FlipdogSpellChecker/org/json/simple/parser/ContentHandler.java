package org.json.simple.parser;

import java.io.IOException;

public abstract interface ContentHandler
{
  public abstract boolean endArray()
    throws ParseException, IOException;

  public abstract void endJSON()
    throws ParseException, IOException;

  public abstract boolean endObject()
    throws ParseException, IOException;

  public abstract boolean endObjectEntry()
    throws ParseException, IOException;

  public abstract boolean primitive(Object paramObject)
    throws ParseException, IOException;

  public abstract boolean startArray()
    throws ParseException, IOException;

  public abstract void startJSON()
    throws ParseException, IOException;

  public abstract boolean startObject()
    throws ParseException, IOException;

  public abstract boolean startObjectEntry(String paramString)
    throws ParseException, IOException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     org.json.simple.parser.ContentHandler
 * JD-Core Version:    0.6.2
 */