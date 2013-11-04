package my.org.json;

import java.util.HashMap;

public class XMLTokener extends JSONTokener
{
  public static final HashMap entity = new HashMap(8);

  static
  {
    entity.put("amp", XML.AMP);
    entity.put("apos", XML.APOS);
    entity.put("gt", XML.GT);
    entity.put("lt", XML.LT);
    entity.put("quot", XML.QUOT);
  }

  public XMLTokener(String paramString)
  {
    super(paramString);
  }

  public String nextCDATA()
    throws JSONException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i;
    do
    {
      char c = next();
      if (end())
        throw syntaxError("Unclosed CDATA");
      localStringBuffer.append(c);
      i = -3 + localStringBuffer.length();
    }
    while ((i < 0) || (localStringBuffer.charAt(i) != ']') || (localStringBuffer.charAt(i + 1) != ']') || (localStringBuffer.charAt(i + 2) != '>'));
    localStringBuffer.setLength(i);
    return localStringBuffer.toString();
  }

  public Object nextContent()
    throws JSONException
  {
    char c;
    do
      c = next();
    while (Character.isWhitespace(c));
    if (c == 0)
      return null;
    if (c == '<')
      return XML.LT;
    StringBuffer localStringBuffer = new StringBuffer();
    if ((c == '<') || (c == 0))
    {
      back();
      return localStringBuffer.toString().trim();
    }
    if (c == '&')
      localStringBuffer.append(nextEntity(c));
    while (true)
    {
      c = next();
      break;
      localStringBuffer.append(c);
    }
  }

  public Object nextEntity(char paramChar)
    throws JSONException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    char c;
    while (true)
    {
      c = next();
      if ((!Character.isLetterOrDigit(c)) && (c != '#'))
        break;
      localStringBuffer.append(Character.toLowerCase(c));
    }
    String str;
    if (c == ';')
    {
      str = localStringBuffer.toString();
      Object localObject = entity.get(str);
      if (localObject != null)
        return localObject;
    }
    else
    {
      throw syntaxError("Missing ';' in XML entity: &" + localStringBuffer);
    }
    return paramChar + str + ";";
  }

  public Object nextMeta()
    throws JSONException
  {
    char c1;
    do
      c1 = next();
    while (Character.isWhitespace(c1));
    switch (c1)
    {
    default:
    case '\000':
    case '<':
    case '>':
    case '/':
    case '=':
    case '!':
    case '?':
    case '"':
    case '\'':
    }
    while (true)
    {
      char c3 = next();
      if (Character.isWhitespace(c3))
      {
        return Boolean.TRUE;
        throw syntaxError("Misshaped meta tag");
        return XML.LT;
        return XML.GT;
        return XML.SLASH;
        return XML.EQ;
        return XML.BANG;
        return XML.QUEST;
        char c2;
        do
        {
          c2 = next();
          if (c2 == 0)
            throw syntaxError("Unterminated string");
        }
        while (c2 != c1);
        return Boolean.TRUE;
      }
      switch (c3)
      {
      default:
      case '\000':
      case '!':
      case '"':
      case '\'':
      case '/':
      case '<':
      case '=':
      case '>':
      case '?':
      }
    }
    back();
    return Boolean.TRUE;
  }

  public Object nextToken()
    throws JSONException
  {
    char c1;
    do
      c1 = next();
    while (Character.isWhitespace(c1));
    StringBuffer localStringBuffer2;
    switch (c1)
    {
    default:
      localStringBuffer2 = new StringBuffer();
    case '\000':
    case '<':
    case '>':
    case '/':
    case '=':
    case '!':
    case '?':
    case '"':
    case '\'':
    }
    while (true)
    {
      localStringBuffer2.append(c1);
      c1 = next();
      if (Character.isWhitespace(c1))
      {
        return localStringBuffer2.toString();
        throw syntaxError("Misshaped element");
        throw syntaxError("Misplaced '<'");
        return XML.GT;
        return XML.SLASH;
        return XML.EQ;
        return XML.BANG;
        return XML.QUEST;
        char c2 = c1;
        StringBuffer localStringBuffer1 = new StringBuffer();
        while (true)
        {
          char c3 = next();
          if (c3 == 0)
            throw syntaxError("Unterminated string");
          if (c3 == c2)
            return localStringBuffer1.toString();
          if (c3 == '&')
            localStringBuffer1.append(nextEntity(c3));
          else
            localStringBuffer1.append(c3);
        }
      }
      switch (c1)
      {
      default:
      case '\000':
      case '!':
      case '/':
      case '=':
      case '>':
      case '?':
      case '[':
      case ']':
      case '"':
      case '\'':
      case '<':
      }
    }
    return localStringBuffer2.toString();
    back();
    return localStringBuffer2.toString();
    throw syntaxError("Bad character in a name");
  }

  public boolean skipPast(String paramString)
    throws JSONException
  {
    int i = paramString.length();
    char[] arrayOfChar = new char[i];
    int j = 0;
    int k = 0;
    if (j >= i);
    while (true)
    {
      int n = k;
      int i1 = 1;
      int i2 = 0;
      label31: label37: boolean bool;
      if (i2 >= i)
      {
        if (i1 == 0)
          break label114;
        bool = true;
      }
      label114: int i3;
      do
      {
        int m;
        do
        {
          return bool;
          m = next();
          bool = false;
        }
        while (m == 0);
        arrayOfChar[j] = m;
        j++;
        break;
        if (arrayOfChar[n] != paramString.charAt(i2))
        {
          i1 = 0;
          break label37;
        }
        n++;
        if (n >= i)
          n -= i;
        i2++;
        break label31;
        i3 = next();
        bool = false;
      }
      while (i3 == 0);
      arrayOfChar[k] = i3;
      k++;
      if (k >= i)
        k -= i;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.org.json.XMLTokener
 * JD-Core Version:    0.6.2
 */