package com.flipdog.speller;

import android.content.Context;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;

public class SpellerNative
{
  public static native Document createRequestNative(Context paramContext, String paramString)
    throws FactoryConfigurationError, ParserConfigurationException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.speller.SpellerNative
 * JD-Core Version:    0.6.2
 */