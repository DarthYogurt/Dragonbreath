package com.android.a.a;

import android.util.Xml;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public class b
{
  public static final int a(CharSequence paramCharSequence)
  {
    int i = 16;
    String str = paramCharSequence.toString();
    int j = str.length();
    int k;
    if ('0' == str.charAt(0))
    {
      if (j - 1 == 0)
        return 0;
      int m = str.charAt(1);
      if ((120 == m) || (88 == m))
        k = 2;
    }
    while (true)
    {
      return (int)Long.parseLong(str.substring(k), i);
      i = 8;
      k = 1;
      continue;
      if ('#' == str.charAt(0))
      {
        k = 1;
      }
      else
      {
        i = 10;
        k = 0;
      }
    }
  }

  public static final int a(CharSequence paramCharSequence, int paramInt)
  {
    int i = 1;
    if (paramCharSequence == null)
      return paramInt;
    String str = paramCharSequence.toString();
    int j = str.length();
    int k;
    if ('-' == str.charAt(0))
      k = -1;
    while (true)
    {
      int m;
      int n;
      if ('0' == str.charAt(i))
      {
        if (i == j - 1)
          return 0;
        int i1 = str.charAt(i + 1);
        if ((120 == i1) || (88 == i1))
        {
          m = i + 2;
          n = 16;
        }
      }
      while (true)
      {
        return k * Integer.parseInt(str.substring(m), n);
        m = i + 1;
        n = 8;
        continue;
        if ('#' == str.charAt(i))
        {
          m = i + 1;
          n = 16;
        }
        else
        {
          m = i;
          n = 10;
        }
      }
      k = i;
      i = 0;
    }
  }

  public static final int a(CharSequence paramCharSequence, String[] paramArrayOfString, int paramInt)
  {
    if (paramCharSequence != null);
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayOfString.length)
        i = paramInt;
      while (paramCharSequence.equals(paramArrayOfString[i]))
        return i;
    }
  }

  public static final int a(String paramString, int paramInt)
  {
    if (paramString == null)
      return paramInt;
    return a(paramString);
  }

  public static final Object a(XmlPullParser paramXmlPullParser, String[] paramArrayOfString)
    throws XmlPullParserException, IOException
  {
    int i = paramXmlPullParser.getEventType();
    do
    {
      if (i == 2)
        return b(paramXmlPullParser, paramArrayOfString);
      if (i == 3)
        throw new XmlPullParserException("Unexpected end tag at: " + paramXmlPullParser.getName());
      if (i == 4)
        throw new XmlPullParserException("Unexpected text: " + paramXmlPullParser.getText());
      i = paramXmlPullParser.next();
    }
    while (i != 1);
    throw new XmlPullParserException("Unexpected end of document");
  }

  public static final HashMap a(InputStream paramInputStream)
    throws XmlPullParserException, IOException
  {
    XmlPullParser localXmlPullParser = Xml.newPullParser();
    localXmlPullParser.setInput(paramInputStream, null);
    return (HashMap)a(localXmlPullParser, new String[1]);
  }

  public static final HashMap a(XmlPullParser paramXmlPullParser, String paramString, String[] paramArrayOfString)
    throws XmlPullParserException, IOException
  {
    HashMap localHashMap = new HashMap();
    int i = paramXmlPullParser.getEventType();
    if (i == 2)
    {
      localObject = b(paramXmlPullParser, paramArrayOfString);
      if (paramArrayOfString[0] != null)
        localHashMap.put(paramArrayOfString[0], localObject);
    }
    while (i != 3)
    {
      Object localObject;
      i = paramXmlPullParser.next();
      if (i != 1)
        break;
      throw new XmlPullParserException("Document ended before " + paramString + " end tag");
      throw new XmlPullParserException("Map value without name attribute: " + paramXmlPullParser.getName());
    }
    if (paramXmlPullParser.getName().equals(paramString))
      return localHashMap;
    throw new XmlPullParserException("Expected " + paramString + " end tag at: " + paramXmlPullParser.getName());
  }

  public static final void a(Object paramObject, String paramString, XmlSerializer paramXmlSerializer)
    throws XmlPullParserException, IOException
  {
    if (paramObject == null)
    {
      paramXmlSerializer.startTag(null, "null");
      if (paramString != null)
        paramXmlSerializer.attribute(null, "name", paramString);
      paramXmlSerializer.endTag(null, "null");
      return;
    }
    if ((paramObject instanceof String))
    {
      paramXmlSerializer.startTag(null, "string");
      if (paramString != null)
        paramXmlSerializer.attribute(null, "name", paramString);
      paramXmlSerializer.text(paramObject.toString());
      paramXmlSerializer.endTag(null, "string");
      return;
    }
    String str;
    if ((paramObject instanceof Integer))
      str = "int";
    while (true)
    {
      paramXmlSerializer.startTag(null, str);
      if (paramString != null)
        paramXmlSerializer.attribute(null, "name", paramString);
      paramXmlSerializer.attribute(null, "value", paramObject.toString());
      paramXmlSerializer.endTag(null, str);
      return;
      if ((paramObject instanceof Long))
      {
        str = "long";
      }
      else if ((paramObject instanceof Float))
      {
        str = "float";
      }
      else if ((paramObject instanceof Double))
      {
        str = "double";
      }
      else
      {
        if (!(paramObject instanceof Boolean))
          break;
        str = "boolean";
      }
    }
    if ((paramObject instanceof byte[]))
    {
      a((byte[])paramObject, paramString, paramXmlSerializer);
      return;
    }
    if ((paramObject instanceof int[]))
    {
      a((int[])paramObject, paramString, paramXmlSerializer);
      return;
    }
    if ((paramObject instanceof Map))
    {
      a((Map)paramObject, paramString, paramXmlSerializer);
      return;
    }
    if ((paramObject instanceof List))
    {
      a((List)paramObject, paramString, paramXmlSerializer);
      return;
    }
    if ((paramObject instanceof Set))
    {
      a((Set)paramObject, paramString, paramXmlSerializer);
      return;
    }
    if ((paramObject instanceof CharSequence))
    {
      paramXmlSerializer.startTag(null, "string");
      if (paramString != null)
        paramXmlSerializer.attribute(null, "name", paramString);
      paramXmlSerializer.text(paramObject.toString());
      paramXmlSerializer.endTag(null, "string");
      return;
    }
    throw new RuntimeException("writeValueXml: unable to write value " + paramObject);
  }

  public static final void a(List paramList, OutputStream paramOutputStream)
    throws XmlPullParserException, IOException
  {
    XmlSerializer localXmlSerializer = Xml.newSerializer();
    localXmlSerializer.setOutput(paramOutputStream, "utf-8");
    localXmlSerializer.startDocument(null, Boolean.valueOf(true));
    localXmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
    a(paramList, null, localXmlSerializer);
    localXmlSerializer.endDocument();
  }

  public static final void a(List paramList, String paramString, XmlSerializer paramXmlSerializer)
    throws XmlPullParserException, IOException
  {
    if (paramList == null)
    {
      paramXmlSerializer.startTag(null, "null");
      paramXmlSerializer.endTag(null, "null");
      return;
    }
    paramXmlSerializer.startTag(null, "list");
    if (paramString != null)
      paramXmlSerializer.attribute(null, "name", paramString);
    int i = paramList.size();
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        paramXmlSerializer.endTag(null, "list");
        return;
      }
      a(paramList.get(j), null, paramXmlSerializer);
    }
  }

  public static final void a(Map paramMap, OutputStream paramOutputStream)
    throws XmlPullParserException, IOException
  {
    c localc = new c();
    localc.setOutput(paramOutputStream, "utf-8");
    localc.startDocument(null, Boolean.valueOf(true));
    localc.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
    a(paramMap, null, localc);
    localc.endDocument();
  }

  public static final void a(Map paramMap, String paramString, XmlSerializer paramXmlSerializer)
    throws XmlPullParserException, IOException
  {
    if (paramMap == null)
    {
      paramXmlSerializer.startTag(null, "null");
      paramXmlSerializer.endTag(null, "null");
      return;
    }
    Iterator localIterator = paramMap.entrySet().iterator();
    paramXmlSerializer.startTag(null, "map");
    if (paramString != null)
      paramXmlSerializer.attribute(null, "name", paramString);
    while (true)
    {
      if (!localIterator.hasNext())
      {
        paramXmlSerializer.endTag(null, "map");
        return;
      }
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      a(localEntry.getValue(), (String)localEntry.getKey(), paramXmlSerializer);
    }
  }

  public static final void a(Set paramSet, String paramString, XmlSerializer paramXmlSerializer)
    throws XmlPullParserException, IOException
  {
    if (paramSet == null)
    {
      paramXmlSerializer.startTag(null, "null");
      paramXmlSerializer.endTag(null, "null");
      return;
    }
    paramXmlSerializer.startTag(null, "set");
    if (paramString != null)
      paramXmlSerializer.attribute(null, "name", paramString);
    Iterator localIterator = paramSet.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        paramXmlSerializer.endTag(null, "set");
        return;
      }
      a(localIterator.next(), null, paramXmlSerializer);
    }
  }

  public static void a(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    int i = paramXmlPullParser.getDepth();
    int j;
    do
      j = paramXmlPullParser.next();
    while ((j != 1) && ((j != 3) || (paramXmlPullParser.getDepth() > i)));
  }

  public static final void a(XmlPullParser paramXmlPullParser, String paramString)
    throws XmlPullParserException, IOException
  {
    int i;
    do
      i = paramXmlPullParser.next();
    while ((i != 2) && (i != 1));
    if (i != 2)
      throw new XmlPullParserException("No start tag found");
    if (!paramXmlPullParser.getName().equals(paramString))
      throw new XmlPullParserException("Unexpected start tag: found " + paramXmlPullParser.getName() + ", expected " + paramString);
  }

  public static final void a(byte[] paramArrayOfByte, String paramString, XmlSerializer paramXmlSerializer)
    throws XmlPullParserException, IOException
  {
    if (paramArrayOfByte == null)
    {
      paramXmlSerializer.startTag(null, "null");
      paramXmlSerializer.endTag(null, "null");
      return;
    }
    paramXmlSerializer.startTag(null, "byte-array");
    if (paramString != null)
      paramXmlSerializer.attribute(null, "name", paramString);
    int i = paramArrayOfByte.length;
    paramXmlSerializer.attribute(null, "num", Integer.toString(i));
    StringBuilder localStringBuilder = new StringBuilder(2 * paramArrayOfByte.length);
    int j = 0;
    if (j >= i)
    {
      paramXmlSerializer.text(localStringBuilder.toString());
      paramXmlSerializer.endTag(null, "byte-array");
      return;
    }
    int k = paramArrayOfByte[j];
    int m = k >> 4;
    int n;
    label147: int i1;
    if (m >= 10)
    {
      n = -10 + (m + 97);
      localStringBuilder.append(n);
      i1 = k & 0xFF;
      if (i1 < 10)
        break label204;
    }
    label204: for (int i2 = -10 + (i1 + 97); ; i2 = i1 + 48)
    {
      localStringBuilder.append(i2);
      j++;
      break;
      n = m + 48;
      break label147;
    }
  }

  public static final void a(int[] paramArrayOfInt, String paramString, XmlSerializer paramXmlSerializer)
    throws XmlPullParserException, IOException
  {
    if (paramArrayOfInt == null)
    {
      paramXmlSerializer.startTag(null, "null");
      paramXmlSerializer.endTag(null, "null");
      return;
    }
    paramXmlSerializer.startTag(null, "int-array");
    if (paramString != null)
      paramXmlSerializer.attribute(null, "name", paramString);
    int i = paramArrayOfInt.length;
    paramXmlSerializer.attribute(null, "num", Integer.toString(i));
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        paramXmlSerializer.endTag(null, "int-array");
        return;
      }
      paramXmlSerializer.startTag(null, "item");
      paramXmlSerializer.attribute(null, "value", Integer.toString(paramArrayOfInt[j]));
      paramXmlSerializer.endTag(null, "item");
    }
  }

  public static final boolean a(CharSequence paramCharSequence, boolean paramBoolean)
  {
    if (paramCharSequence == null)
      return paramBoolean;
    boolean bool1;
    if ((!paramCharSequence.equals("1")) && (!paramCharSequence.equals("true")))
    {
      boolean bool2 = paramCharSequence.equals("TRUE");
      bool1 = false;
      if (!bool2);
    }
    else
    {
      bool1 = true;
    }
    return bool1;
  }

  public static boolean a(XmlPullParser paramXmlPullParser, int paramInt)
    throws IOException, XmlPullParserException
  {
    int i;
    do
    {
      i = paramXmlPullParser.next();
      if ((i == 1) || ((i == 3) && (paramXmlPullParser.getDepth() == paramInt)))
        return false;
    }
    while ((i != 2) || (paramXmlPullParser.getDepth() != paramInt + 1));
    return true;
  }

  private static final Object b(XmlPullParser paramXmlPullParser, String[] paramArrayOfString)
    throws XmlPullParserException, IOException
  {
    String str1 = paramXmlPullParser.getAttributeValue(null, "name");
    String str2 = paramXmlPullParser.getName();
    boolean bool = str2.equals("null");
    Object localObject = null;
    if (bool);
    int i;
    label543: 
    do
    {
      while (true)
      {
        i = paramXmlPullParser.next();
        if (i != 1)
          break label543;
        throw new XmlPullParserException("Unexpected end of document in <" + str2 + ">");
        if (str2.equals("string"))
        {
          String str3 = "";
          int j;
          do
            while (true)
            {
              j = paramXmlPullParser.next();
              if (j == 1)
                throw new XmlPullParserException("Unexpected end of document in <string>");
              if (j == 3)
              {
                if (paramXmlPullParser.getName().equals("string"))
                {
                  paramArrayOfString[0] = str1;
                  return str3;
                }
                throw new XmlPullParserException("Unexpected end tag in <string>: " + paramXmlPullParser.getName());
              }
              if (j != 4)
                break;
              str3 = str3 + paramXmlPullParser.getText();
            }
          while (j != 2);
          throw new XmlPullParserException("Unexpected start tag in <string>: " + paramXmlPullParser.getName());
        }
        if (str2.equals("int"))
        {
          localObject = Integer.valueOf(Integer.parseInt(paramXmlPullParser.getAttributeValue(null, "value")));
        }
        else if (str2.equals("long"))
        {
          localObject = Long.valueOf(paramXmlPullParser.getAttributeValue(null, "value"));
        }
        else if (str2.equals("float"))
        {
          localObject = new Float(paramXmlPullParser.getAttributeValue(null, "value"));
        }
        else if (str2.equals("double"))
        {
          localObject = new Double(paramXmlPullParser.getAttributeValue(null, "value"));
        }
        else
        {
          if (!str2.equals("boolean"))
            break;
          localObject = Boolean.valueOf(paramXmlPullParser.getAttributeValue(null, "value"));
        }
      }
      if (str2.equals("int-array"))
      {
        paramXmlPullParser.next();
        int[] arrayOfInt = d(paramXmlPullParser, "int-array", paramArrayOfString);
        paramArrayOfString[0] = str1;
        return arrayOfInt;
      }
      if (str2.equals("map"))
      {
        paramXmlPullParser.next();
        HashMap localHashMap = a(paramXmlPullParser, "map", paramArrayOfString);
        paramArrayOfString[0] = str1;
        return localHashMap;
      }
      if (str2.equals("list"))
      {
        paramXmlPullParser.next();
        ArrayList localArrayList = b(paramXmlPullParser, "list", paramArrayOfString);
        paramArrayOfString[0] = str1;
        return localArrayList;
      }
      if (str2.equals("set"))
      {
        paramXmlPullParser.next();
        HashSet localHashSet = c(paramXmlPullParser, "set", paramArrayOfString);
        paramArrayOfString[0] = str1;
        return localHashSet;
      }
      throw new XmlPullParserException("Unknown tag: " + str2);
      if (i == 3)
      {
        if (paramXmlPullParser.getName().equals(str2))
        {
          paramArrayOfString[0] = str1;
          return localObject;
        }
        throw new XmlPullParserException("Unexpected end tag in <" + str2 + ">: " + paramXmlPullParser.getName());
      }
      if (i == 4)
        throw new XmlPullParserException("Unexpected text in <" + str2 + ">: " + paramXmlPullParser.getName());
    }
    while (i != 2);
    throw new XmlPullParserException("Unexpected start tag in <" + str2 + ">: " + paramXmlPullParser.getName());
  }

  public static final ArrayList b(InputStream paramInputStream)
    throws XmlPullParserException, IOException
  {
    XmlPullParser localXmlPullParser = Xml.newPullParser();
    localXmlPullParser.setInput(paramInputStream, null);
    return (ArrayList)a(localXmlPullParser, new String[1]);
  }

  public static final ArrayList b(XmlPullParser paramXmlPullParser, String paramString, String[] paramArrayOfString)
    throws XmlPullParserException, IOException
  {
    ArrayList localArrayList = new ArrayList();
    int i = paramXmlPullParser.getEventType();
    if (i == 2)
      localArrayList.add(b(paramXmlPullParser, paramArrayOfString));
    while (i != 3)
    {
      i = paramXmlPullParser.next();
      if (i != 1)
        break;
      throw new XmlPullParserException("Document ended before " + paramString + " end tag");
    }
    if (paramXmlPullParser.getName().equals(paramString))
      return localArrayList;
    throw new XmlPullParserException("Expected " + paramString + " end tag at: " + paramXmlPullParser.getName());
  }

  public static final void b(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    int i;
    do
      i = paramXmlPullParser.next();
    while ((i != 2) && (i != 1));
  }

  public static final HashSet c(InputStream paramInputStream)
    throws XmlPullParserException, IOException
  {
    XmlPullParser localXmlPullParser = Xml.newPullParser();
    localXmlPullParser.setInput(paramInputStream, null);
    return (HashSet)a(localXmlPullParser, new String[1]);
  }

  public static final HashSet c(XmlPullParser paramXmlPullParser, String paramString, String[] paramArrayOfString)
    throws XmlPullParserException, IOException
  {
    HashSet localHashSet = new HashSet();
    int i = paramXmlPullParser.getEventType();
    if (i == 2)
      localHashSet.add(b(paramXmlPullParser, paramArrayOfString));
    while (i != 3)
    {
      i = paramXmlPullParser.next();
      if (i != 1)
        break;
      throw new XmlPullParserException("Document ended before " + paramString + " end tag");
    }
    if (paramXmlPullParser.getName().equals(paramString))
      return localHashSet;
    throw new XmlPullParserException("Expected " + paramString + " end tag at: " + paramXmlPullParser.getName());
  }

  // ERROR //
  public static final int[] d(XmlPullParser paramXmlPullParser, String paramString, String[] paramArrayOfString)
    throws XmlPullParserException, IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: aconst_null
    //   2: ldc_w 284
    //   5: invokeinterface 308 3 0
    //   10: invokestatic 326	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   13: istore 5
    //   15: iload 5
    //   17: newarray int
    //   19: astore 6
    //   21: aload_0
    //   22: invokeinterface 60 1 0
    //   27: istore 7
    //   29: iconst_0
    //   30: istore 8
    //   32: iload 7
    //   34: istore 9
    //   36: iload 9
    //   38: iconst_2
    //   39: if_icmpne +159 -> 198
    //   42: aload_0
    //   43: invokeinterface 73 1 0
    //   48: ldc_w 296
    //   51: invokevirtual 117	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   54: ifeq +114 -> 168
    //   57: aload 6
    //   59: iload 8
    //   61: aload_0
    //   62: aconst_null
    //   63: ldc 150
    //   65: invokeinterface 308 3 0
    //   70: invokestatic 326	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   73: iastore
    //   74: aload_0
    //   75: invokeinterface 87 1 0
    //   80: istore 9
    //   82: iload 9
    //   84: iconst_1
    //   85: if_icmpne -49 -> 36
    //   88: new 53	org/xmlpull/v1/XmlPullParserException
    //   91: dup
    //   92: new 65	java/lang/StringBuilder
    //   95: dup
    //   96: ldc 112
    //   98: invokespecial 70	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   101: aload_1
    //   102: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: ldc 114
    //   107: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   110: invokevirtual 78	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   113: invokespecial 79	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   116: athrow
    //   117: astore 4
    //   119: new 53	org/xmlpull/v1/XmlPullParserException
    //   122: dup
    //   123: ldc_w 378
    //   126: invokespecial 79	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   129: athrow
    //   130: astore_3
    //   131: new 53	org/xmlpull/v1/XmlPullParserException
    //   134: dup
    //   135: ldc_w 380
    //   138: invokespecial 79	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   141: athrow
    //   142: astore 11
    //   144: new 53	org/xmlpull/v1/XmlPullParserException
    //   147: dup
    //   148: ldc_w 382
    //   151: invokespecial 79	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   154: athrow
    //   155: astore 10
    //   157: new 53	org/xmlpull/v1/XmlPullParserException
    //   160: dup
    //   161: ldc_w 384
    //   164: invokespecial 79	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   167: athrow
    //   168: new 53	org/xmlpull/v1/XmlPullParserException
    //   171: dup
    //   172: new 65	java/lang/StringBuilder
    //   175: dup
    //   176: ldc_w 386
    //   179: invokespecial 70	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   182: aload_0
    //   183: invokeinterface 73 1 0
    //   188: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   191: invokevirtual 78	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   194: invokespecial 79	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   197: athrow
    //   198: iload 9
    //   200: iconst_3
    //   201: if_icmpne -127 -> 74
    //   204: aload_0
    //   205: invokeinterface 73 1 0
    //   210: aload_1
    //   211: invokevirtual 117	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   214: ifeq +6 -> 220
    //   217: aload 6
    //   219: areturn
    //   220: aload_0
    //   221: invokeinterface 73 1 0
    //   226: ldc_w 296
    //   229: invokevirtual 117	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   232: ifeq +9 -> 241
    //   235: iinc 8 1
    //   238: goto -164 -> 74
    //   241: new 53	org/xmlpull/v1/XmlPullParserException
    //   244: dup
    //   245: new 65	java/lang/StringBuilder
    //   248: dup
    //   249: ldc 119
    //   251: invokespecial 70	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   254: aload_1
    //   255: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   258: ldc 121
    //   260: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   263: aload_0
    //   264: invokeinterface 73 1 0
    //   269: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   272: invokevirtual 78	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   275: invokespecial 79	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   278: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   0	15	117	java/lang/NullPointerException
    //   0	15	130	java/lang/NumberFormatException
    //   57	74	142	java/lang/NullPointerException
    //   57	74	155	java/lang/NumberFormatException
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.android.a.a.b
 * JD-Core Version:    0.6.2
 */