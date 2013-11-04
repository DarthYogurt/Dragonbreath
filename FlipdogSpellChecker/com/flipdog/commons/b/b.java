package com.flipdog.commons.b;

import android.content.Context;
import android.content.res.XmlResourceParser;

public class b
{
  private static final String a = "http://schemas.android.com/apk/res/android";
  private static final String b = "id";
  private static final String c = "showAsAction";
  private static final String d = "icon";
  private static final String e = "title";

  private static String a(Context paramContext, XmlResourceParser paramXmlResourceParser, String paramString)
  {
    int i = paramXmlResourceParser.getAttributeResourceValue("http://schemas.android.com/apk/res/android", paramString, 0);
    if (i != 0)
      return paramContext.getString(i);
    return paramXmlResourceParser.getAttributeValue("http://schemas.android.com/apk/res/android", paramString);
  }

  // ERROR //
  public static java.util.List<c> a(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: new 48	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 49	java/util/ArrayList:<init>	()V
    //   7: astore_2
    //   8: new 48	java/util/ArrayList
    //   11: dup
    //   12: invokespecial 49	java/util/ArrayList:<init>	()V
    //   15: astore_3
    //   16: iconst_0
    //   17: istore 4
    //   19: aconst_null
    //   20: astore 5
    //   22: aload_0
    //   23: invokevirtual 53	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   26: iload_1
    //   27: invokevirtual 59	android/content/res/Resources:getXml	(I)Landroid/content/res/XmlResourceParser;
    //   30: astore 5
    //   32: aload 5
    //   34: invokeinterface 63 1 0
    //   39: istore 9
    //   41: iload 9
    //   43: istore 10
    //   45: iconst_0
    //   46: istore 11
    //   48: iconst_0
    //   49: istore 12
    //   51: iload 12
    //   53: ifeq +17 -> 70
    //   56: aload 5
    //   58: ifnull +10 -> 68
    //   61: aload 5
    //   63: invokeinterface 66 1 0
    //   68: aload_2
    //   69: areturn
    //   70: iload 10
    //   72: tableswitch	default:+28 -> 100, 1:+364->436, 2:+72->144, 3:+305->377
    //   101: fconst_1
    //   102: istore 13
    //   104: iload 11
    //   106: istore 14
    //   108: iload 4
    //   110: istore 15
    //   112: aload 5
    //   114: invokeinterface 69 1 0
    //   119: istore 16
    //   121: iload 13
    //   123: istore 17
    //   125: iload 16
    //   127: istore 10
    //   129: iload 15
    //   131: istore 4
    //   133: iload 14
    //   135: istore 11
    //   137: iload 17
    //   139: istore 12
    //   141: goto -90 -> 51
    //   144: aload 5
    //   146: invokeinterface 73 1 0
    //   151: ldc 75
    //   153: invokevirtual 81	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   156: ifeq +129 -> 285
    //   159: new 83	com/flipdog/commons/b/c
    //   162: dup
    //   163: invokespecial 84	com/flipdog/commons/b/c:<init>	()V
    //   166: astore 18
    //   168: aload 18
    //   170: aload 5
    //   172: ldc 8
    //   174: ldc 11
    //   176: iconst_0
    //   177: invokeinterface 31 4 0
    //   182: putfield 87	com/flipdog/commons/b/c:a	I
    //   185: aload 18
    //   187: aload 5
    //   189: ldc 8
    //   191: ldc 14
    //   193: iconst_m1
    //   194: invokeinterface 90 4 0
    //   199: putfield 92	com/flipdog/commons/b/c:b	I
    //   202: aload 18
    //   204: aload 5
    //   206: ldc 8
    //   208: ldc 17
    //   210: iconst_0
    //   211: invokeinterface 31 4 0
    //   216: putfield 94	com/flipdog/commons/b/c:c	I
    //   219: aload 18
    //   221: aload_0
    //   222: aload 5
    //   224: ldc 20
    //   226: invokestatic 96	com/flipdog/commons/b/b:a	(Landroid/content/Context;Landroid/content/res/XmlResourceParser;Ljava/lang/String;)Ljava/lang/String;
    //   229: putfield 99	com/flipdog/commons/b/c:d	Ljava/lang/CharSequence;
    //   232: iload 4
    //   234: ifeq +27 -> 261
    //   237: aload_3
    //   238: aload 18
    //   240: invokeinterface 104 2 0
    //   245: pop
    //   246: iload 12
    //   248: istore 13
    //   250: iload 11
    //   252: istore 14
    //   254: iload 4
    //   256: istore 15
    //   258: goto -146 -> 112
    //   261: aload_2
    //   262: aload 18
    //   264: invokeinterface 104 2 0
    //   269: pop
    //   270: iload 12
    //   272: istore 13
    //   274: iload 11
    //   276: istore 14
    //   278: iload 4
    //   280: istore 15
    //   282: goto -170 -> 112
    //   285: aload 5
    //   287: invokeinterface 73 1 0
    //   292: ldc 106
    //   294: invokevirtual 81	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   297: ifeq -197 -> 100
    //   300: iload 11
    //   302: iconst_1
    //   303: iadd
    //   304: istore 21
    //   306: iload 21
    //   308: iconst_2
    //   309: if_icmple +44 -> 353
    //   312: new 108	java/lang/RuntimeException
    //   315: dup
    //   316: ldc 110
    //   318: invokespecial 113	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   321: athrow
    //   322: astore 8
    //   324: new 115	android/view/InflateException
    //   327: dup
    //   328: ldc 117
    //   330: aload 8
    //   332: invokespecial 120	android/view/InflateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   335: athrow
    //   336: astore 7
    //   338: aload 5
    //   340: ifnull +10 -> 350
    //   343: aload 5
    //   345: invokeinterface 66 1 0
    //   350: aload 7
    //   352: athrow
    //   353: iload 21
    //   355: iconst_2
    //   356: if_icmpne +108 -> 464
    //   359: iconst_1
    //   360: istore 15
    //   362: iload 12
    //   364: istore 22
    //   366: iload 21
    //   368: istore 14
    //   370: iload 22
    //   372: istore 13
    //   374: goto -262 -> 112
    //   377: aload 5
    //   379: invokeinterface 73 1 0
    //   384: ldc 106
    //   386: invokevirtual 81	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   389: ifeq -289 -> 100
    //   392: iinc 11 255
    //   395: iload 11
    //   397: iconst_1
    //   398: if_icmpne -298 -> 100
    //   401: aload_2
    //   402: iconst_m1
    //   403: aload_2
    //   404: invokeinterface 123 1 0
    //   409: iadd
    //   410: invokeinterface 127 2 0
    //   415: checkcast 83	com/flipdog/commons/b/c
    //   418: aload_3
    //   419: putfield 130	com/flipdog/commons/b/c:e	Ljava/util/List;
    //   422: iload 12
    //   424: istore 13
    //   426: iload 11
    //   428: istore 14
    //   430: iconst_0
    //   431: istore 15
    //   433: goto -321 -> 112
    //   436: iconst_1
    //   437: istore 13
    //   439: iload 11
    //   441: istore 14
    //   443: iload 4
    //   445: istore 15
    //   447: goto -335 -> 112
    //   450: astore 6
    //   452: new 115	android/view/InflateException
    //   455: dup
    //   456: ldc 117
    //   458: aload 6
    //   460: invokespecial 120	android/view/InflateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   463: athrow
    //   464: iload 4
    //   466: istore 15
    //   468: iload 12
    //   470: istore 13
    //   472: iload 21
    //   474: istore 14
    //   476: goto -364 -> 112
    //
    // Exception table:
    //   from	to	target	type
    //   22	41	322	org/xmlpull/v1/XmlPullParserException
    //   112	121	322	org/xmlpull/v1/XmlPullParserException
    //   144	232	322	org/xmlpull/v1/XmlPullParserException
    //   237	246	322	org/xmlpull/v1/XmlPullParserException
    //   261	270	322	org/xmlpull/v1/XmlPullParserException
    //   285	300	322	org/xmlpull/v1/XmlPullParserException
    //   312	322	322	org/xmlpull/v1/XmlPullParserException
    //   377	392	322	org/xmlpull/v1/XmlPullParserException
    //   401	422	322	org/xmlpull/v1/XmlPullParserException
    //   22	41	336	finally
    //   112	121	336	finally
    //   144	232	336	finally
    //   237	246	336	finally
    //   261	270	336	finally
    //   285	300	336	finally
    //   312	322	336	finally
    //   324	336	336	finally
    //   377	392	336	finally
    //   401	422	336	finally
    //   452	464	336	finally
    //   22	41	450	java/io/IOException
    //   112	121	450	java/io/IOException
    //   144	232	450	java/io/IOException
    //   237	246	450	java/io/IOException
    //   261	270	450	java/io/IOException
    //   285	300	450	java/io/IOException
    //   312	322	450	java/io/IOException
    //   377	392	450	java/io/IOException
    //   401	422	450	java/io/IOException
  }

  public boolean a(int paramInt1, int paramInt2)
  {
    return (paramInt1 & paramInt2) != 0;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.b.b
 * JD-Core Version:    0.6.2
 */