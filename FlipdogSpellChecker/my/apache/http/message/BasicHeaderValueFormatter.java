package my.apache.http.message;

import my.apache.http.HeaderElement;
import my.apache.http.NameValuePair;
import my.apache.http.annotation.Immutable;
import my.apache.http.util.CharArrayBuffer;

@Immutable
public class BasicHeaderValueFormatter
  implements HeaderValueFormatter
{
  public static final BasicHeaderValueFormatter DEFAULT = new BasicHeaderValueFormatter();
  public static final String SEPARATORS = " ;,:@()<>\\\"/[]?={}\t";
  public static final String UNSAFE_CHARS = "\"\\";

  public static final String formatElements(HeaderElement[] paramArrayOfHeaderElement, boolean paramBoolean, HeaderValueFormatter paramHeaderValueFormatter)
  {
    if (paramHeaderValueFormatter == null)
      paramHeaderValueFormatter = DEFAULT;
    return paramHeaderValueFormatter.formatElements(null, paramArrayOfHeaderElement, paramBoolean).toString();
  }

  public static final String formatHeaderElement(HeaderElement paramHeaderElement, boolean paramBoolean, HeaderValueFormatter paramHeaderValueFormatter)
  {
    if (paramHeaderValueFormatter == null)
      paramHeaderValueFormatter = DEFAULT;
    return paramHeaderValueFormatter.formatHeaderElement(null, paramHeaderElement, paramBoolean).toString();
  }

  public static final String formatNameValuePair(NameValuePair paramNameValuePair, boolean paramBoolean, HeaderValueFormatter paramHeaderValueFormatter)
  {
    if (paramHeaderValueFormatter == null)
      paramHeaderValueFormatter = DEFAULT;
    return paramHeaderValueFormatter.formatNameValuePair(null, paramNameValuePair, paramBoolean).toString();
  }

  public static final String formatParameters(NameValuePair[] paramArrayOfNameValuePair, boolean paramBoolean, HeaderValueFormatter paramHeaderValueFormatter)
  {
    if (paramHeaderValueFormatter == null)
      paramHeaderValueFormatter = DEFAULT;
    return paramHeaderValueFormatter.formatParameters(null, paramArrayOfNameValuePair, paramBoolean).toString();
  }

  protected void doFormatValue(CharArrayBuffer paramCharArrayBuffer, String paramString, boolean paramBoolean)
  {
    int j;
    if (!paramBoolean)
    {
      j = 0;
      if ((j < paramString.length()) && (!paramBoolean));
    }
    else if (paramBoolean)
    {
      paramCharArrayBuffer.append('"');
    }
    for (int i = 0; ; i++)
    {
      if (i >= paramString.length())
      {
        if (paramBoolean)
          paramCharArrayBuffer.append('"');
        return;
        paramBoolean = isSeparator(paramString.charAt(j));
        j++;
        break;
      }
      char c = paramString.charAt(i);
      if (isUnsafe(c))
        paramCharArrayBuffer.append('\\');
      paramCharArrayBuffer.append(c);
    }
  }

  protected int estimateElementsLen(HeaderElement[] paramArrayOfHeaderElement)
  {
    int i;
    if ((paramArrayOfHeaderElement == null) || (paramArrayOfHeaderElement.length < 1))
      i = 0;
    while (true)
    {
      return i;
      i = 2 * (-1 + paramArrayOfHeaderElement.length);
      for (int j = 0; j < paramArrayOfHeaderElement.length; j++)
        i += estimateHeaderElementLen(paramArrayOfHeaderElement[j]);
    }
  }

  protected int estimateHeaderElementLen(HeaderElement paramHeaderElement)
  {
    int i;
    if (paramHeaderElement == null)
      i = 0;
    while (true)
    {
      return i;
      i = paramHeaderElement.getName().length();
      String str = paramHeaderElement.getValue();
      if (str != null)
        i += 3 + str.length();
      int j = paramHeaderElement.getParameterCount();
      if (j > 0)
        for (int k = 0; k < j; k++)
          i += 2 + estimateNameValuePairLen(paramHeaderElement.getParameter(k));
    }
  }

  protected int estimateNameValuePairLen(NameValuePair paramNameValuePair)
  {
    int i;
    if (paramNameValuePair == null)
      i = 0;
    String str;
    do
    {
      return i;
      i = paramNameValuePair.getName().length();
      str = paramNameValuePair.getValue();
    }
    while (str == null);
    return i + (3 + str.length());
  }

  protected int estimateParametersLen(NameValuePair[] paramArrayOfNameValuePair)
  {
    int i;
    if ((paramArrayOfNameValuePair == null) || (paramArrayOfNameValuePair.length < 1))
      i = 0;
    while (true)
    {
      return i;
      i = 2 * (-1 + paramArrayOfNameValuePair.length);
      for (int j = 0; j < paramArrayOfNameValuePair.length; j++)
        i += estimateNameValuePairLen(paramArrayOfNameValuePair[j]);
    }
  }

  public CharArrayBuffer formatElements(CharArrayBuffer paramCharArrayBuffer, HeaderElement[] paramArrayOfHeaderElement, boolean paramBoolean)
  {
    if (paramArrayOfHeaderElement == null)
      throw new IllegalArgumentException("Header element array must not be null.");
    int i = estimateElementsLen(paramArrayOfHeaderElement);
    if (paramCharArrayBuffer == null)
      paramCharArrayBuffer = new CharArrayBuffer(i);
    for (int j = 0; ; j++)
    {
      if (j >= paramArrayOfHeaderElement.length)
      {
        return paramCharArrayBuffer;
        paramCharArrayBuffer.ensureCapacity(i);
        break;
      }
      if (j > 0)
        paramCharArrayBuffer.append(", ");
      formatHeaderElement(paramCharArrayBuffer, paramArrayOfHeaderElement[j], paramBoolean);
    }
  }

  public CharArrayBuffer formatHeaderElement(CharArrayBuffer paramCharArrayBuffer, HeaderElement paramHeaderElement, boolean paramBoolean)
  {
    if (paramHeaderElement == null)
      throw new IllegalArgumentException("Header element must not be null.");
    int i = estimateHeaderElementLen(paramHeaderElement);
    int j;
    if (paramCharArrayBuffer == null)
    {
      paramCharArrayBuffer = new CharArrayBuffer(i);
      paramCharArrayBuffer.append(paramHeaderElement.getName());
      String str = paramHeaderElement.getValue();
      if (str != null)
      {
        paramCharArrayBuffer.append('=');
        doFormatValue(paramCharArrayBuffer, str, paramBoolean);
      }
      j = paramHeaderElement.getParameterCount();
      if (j <= 0);
    }
    for (int k = 0; ; k++)
    {
      if (k >= j)
      {
        return paramCharArrayBuffer;
        paramCharArrayBuffer.ensureCapacity(i);
        break;
      }
      paramCharArrayBuffer.append("; ");
      formatNameValuePair(paramCharArrayBuffer, paramHeaderElement.getParameter(k), paramBoolean);
    }
  }

  public CharArrayBuffer formatNameValuePair(CharArrayBuffer paramCharArrayBuffer, NameValuePair paramNameValuePair, boolean paramBoolean)
  {
    if (paramNameValuePair == null)
      throw new IllegalArgumentException("NameValuePair must not be null.");
    int i = estimateNameValuePairLen(paramNameValuePair);
    if (paramCharArrayBuffer == null)
      paramCharArrayBuffer = new CharArrayBuffer(i);
    while (true)
    {
      paramCharArrayBuffer.append(paramNameValuePair.getName());
      String str = paramNameValuePair.getValue();
      if (str != null)
      {
        paramCharArrayBuffer.append('=');
        doFormatValue(paramCharArrayBuffer, str, paramBoolean);
      }
      return paramCharArrayBuffer;
      paramCharArrayBuffer.ensureCapacity(i);
    }
  }

  public CharArrayBuffer formatParameters(CharArrayBuffer paramCharArrayBuffer, NameValuePair[] paramArrayOfNameValuePair, boolean paramBoolean)
  {
    if (paramArrayOfNameValuePair == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    int i = estimateParametersLen(paramArrayOfNameValuePair);
    if (paramCharArrayBuffer == null)
      paramCharArrayBuffer = new CharArrayBuffer(i);
    for (int j = 0; ; j++)
    {
      if (j >= paramArrayOfNameValuePair.length)
      {
        return paramCharArrayBuffer;
        paramCharArrayBuffer.ensureCapacity(i);
        break;
      }
      if (j > 0)
        paramCharArrayBuffer.append("; ");
      formatNameValuePair(paramCharArrayBuffer, paramArrayOfNameValuePair[j], paramBoolean);
    }
  }

  protected boolean isSeparator(char paramChar)
  {
    return " ;,:@()<>\\\"/[]?={}\t".indexOf(paramChar) >= 0;
  }

  protected boolean isUnsafe(char paramChar)
  {
    return "\"\\".indexOf(paramChar) >= 0;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.message.BasicHeaderValueFormatter
 * JD-Core Version:    0.6.2
 */