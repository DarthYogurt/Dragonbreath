package my.apache.commons.codec.language;

import java.util.Locale;
import my.apache.commons.codec.EncoderException;
import my.apache.commons.codec.StringEncoder;

final class SoundexUtils
{
  static String clean(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return paramString;
    int i = paramString.length();
    char[] arrayOfChar = new char[i];
    int j = 0;
    int k = 0;
    int m;
    if (j >= i)
    {
      if (k == i)
        return paramString.toUpperCase(Locale.ENGLISH);
    }
    else
    {
      if (!Character.isLetter(paramString.charAt(j)))
        break label100;
      m = k + 1;
      arrayOfChar[k] = paramString.charAt(j);
    }
    while (true)
    {
      j++;
      k = m;
      break;
      return new String(arrayOfChar, 0, k).toUpperCase(Locale.ENGLISH);
      label100: m = k;
    }
  }

  static int difference(StringEncoder paramStringEncoder, String paramString1, String paramString2)
    throws EncoderException
  {
    return differenceEncoded(paramStringEncoder.encode(paramString1), paramStringEncoder.encode(paramString2));
  }

  static int differenceEncoded(String paramString1, String paramString2)
  {
    int i;
    if ((paramString1 == null) || (paramString2 == null))
      i = 0;
    while (true)
    {
      return i;
      int j = Math.min(paramString1.length(), paramString2.length());
      i = 0;
      for (int k = 0; k < j; k++)
        if (paramString1.charAt(k) == paramString2.charAt(k))
          i++;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.commons.codec.language.SoundexUtils
 * JD-Core Version:    0.6.2
 */