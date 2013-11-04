package my.apache.http.message;

import my.apache.http.Header;
import my.apache.http.HttpVersion;
import my.apache.http.ParseException;
import my.apache.http.ProtocolVersion;
import my.apache.http.RequestLine;
import my.apache.http.StatusLine;
import my.apache.http.annotation.Immutable;
import my.apache.http.protocol.HTTP;
import my.apache.http.util.CharArrayBuffer;

@Immutable
public class BasicLineParser
  implements LineParser
{
  public static final BasicLineParser DEFAULT = new BasicLineParser();
  protected final ProtocolVersion protocol;

  public BasicLineParser()
  {
    this(null);
  }

  public BasicLineParser(ProtocolVersion paramProtocolVersion)
  {
    if (paramProtocolVersion == null)
      paramProtocolVersion = HttpVersion.HTTP_1_1;
    this.protocol = paramProtocolVersion;
  }

  public static final Header parseHeader(String paramString, LineParser paramLineParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null");
    if (paramLineParser == null)
      paramLineParser = DEFAULT;
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(paramString.length());
    localCharArrayBuffer.append(paramString);
    return paramLineParser.parseHeader(localCharArrayBuffer);
  }

  public static final ProtocolVersion parseProtocolVersion(String paramString, LineParser paramLineParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null.");
    if (paramLineParser == null)
      paramLineParser = DEFAULT;
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(paramString.length());
    localCharArrayBuffer.append(paramString);
    return paramLineParser.parseProtocolVersion(localCharArrayBuffer, new ParserCursor(0, paramString.length()));
  }

  public static final RequestLine parseRequestLine(String paramString, LineParser paramLineParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null.");
    if (paramLineParser == null)
      paramLineParser = DEFAULT;
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(paramString.length());
    localCharArrayBuffer.append(paramString);
    return paramLineParser.parseRequestLine(localCharArrayBuffer, new ParserCursor(0, paramString.length()));
  }

  public static final StatusLine parseStatusLine(String paramString, LineParser paramLineParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null.");
    if (paramLineParser == null)
      paramLineParser = DEFAULT;
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(paramString.length());
    localCharArrayBuffer.append(paramString);
    return paramLineParser.parseStatusLine(localCharArrayBuffer, new ParserCursor(0, paramString.length()));
  }

  protected ProtocolVersion createProtocolVersion(int paramInt1, int paramInt2)
  {
    return this.protocol.forVersion(paramInt1, paramInt2);
  }

  protected RequestLine createRequestLine(String paramString1, String paramString2, ProtocolVersion paramProtocolVersion)
  {
    return new BasicRequestLine(paramString1, paramString2, paramProtocolVersion);
  }

  protected StatusLine createStatusLine(ProtocolVersion paramProtocolVersion, int paramInt, String paramString)
  {
    return new BasicStatusLine(paramProtocolVersion, paramInt, paramString);
  }

  public boolean hasProtocolVersion(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    int i = paramParserCursor.getPos();
    String str = this.protocol.getProtocol();
    int j = str.length();
    if (paramCharArrayBuffer.length() < j + 4);
    label77: 
    while (4 + (i + j) > paramCharArrayBuffer.length())
    {
      return false;
      if (i >= 0)
        break;
      i = -4 + paramCharArrayBuffer.length() - j;
    }
    boolean bool = true;
    int k = 0;
    if ((!bool) || (k >= j))
      if (bool)
        if (paramCharArrayBuffer.charAt(i + j) != '/')
          break label194;
    label194: for (bool = true; ; bool = false)
    {
      return bool;
      if (i != 0)
        break;
      while ((i < paramCharArrayBuffer.length()) && (HTTP.isWhitespace(paramCharArrayBuffer.charAt(i))))
        i++;
      break label77;
      if (paramCharArrayBuffer.charAt(i + k) == str.charAt(k));
      for (bool = true; ; bool = false)
      {
        k++;
        break;
      }
    }
  }

  public Header parseHeader(CharArrayBuffer paramCharArrayBuffer)
    throws ParseException
  {
    return new BufferedHeader(paramCharArrayBuffer);
  }

  // ERROR //
  public ProtocolVersion parseProtocolVersion(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
    throws ParseException
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +13 -> 14
    //   4: new 36	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 105
    //   10: invokespecial 41	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_2
    //   15: ifnonnull +13 -> 28
    //   18: new 36	java/lang/IllegalArgumentException
    //   21: dup
    //   22: ldc 107
    //   24: invokespecial 41	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   27: athrow
    //   28: aload_0
    //   29: getfield 30	my/apache/http/message/BasicLineParser:protocol	Lmy/apache/http/ProtocolVersion;
    //   32: invokevirtual 114	my/apache/http/ProtocolVersion:getProtocol	()Ljava/lang/String;
    //   35: astore_3
    //   36: aload_3
    //   37: invokevirtual 49	java/lang/String:length	()I
    //   40: istore 4
    //   42: aload_2
    //   43: invokevirtual 110	my/apache/http/message/ParserCursor:getPos	()I
    //   46: istore 5
    //   48: aload_2
    //   49: invokevirtual 136	my/apache/http/message/ParserCursor:getUpperBound	()I
    //   52: istore 6
    //   54: aload_0
    //   55: aload_1
    //   56: aload_2
    //   57: invokevirtual 140	my/apache/http/message/BasicLineParser:skipWhitespace	(Lmy/apache/http/util/CharArrayBuffer;Lmy/apache/http/message/ParserCursor;)V
    //   60: aload_2
    //   61: invokevirtual 110	my/apache/http/message/ParserCursor:getPos	()I
    //   64: istore 7
    //   66: iconst_4
    //   67: iload 7
    //   69: iload 4
    //   71: iadd
    //   72: iadd
    //   73: iload 6
    //   75: if_icmple +34 -> 109
    //   78: new 34	my/apache/http/ParseException
    //   81: dup
    //   82: new 142	java/lang/StringBuilder
    //   85: dup
    //   86: ldc 144
    //   88: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   91: aload_1
    //   92: iload 5
    //   94: iload 6
    //   96: invokevirtual 149	my/apache/http/util/CharArrayBuffer:substring	(II)Ljava/lang/String;
    //   99: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   102: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   105: invokespecial 156	my/apache/http/ParseException:<init>	(Ljava/lang/String;)V
    //   108: athrow
    //   109: iconst_1
    //   110: istore 8
    //   112: iconst_0
    //   113: istore 9
    //   115: iload 8
    //   117: ifeq +10 -> 127
    //   120: iload 9
    //   122: iload 4
    //   124: if_icmplt +61 -> 185
    //   127: iload 8
    //   129: ifeq +20 -> 149
    //   132: aload_1
    //   133: iload 7
    //   135: iload 4
    //   137: iadd
    //   138: invokevirtual 119	my/apache/http/util/CharArrayBuffer:charAt	(I)C
    //   141: bipush 47
    //   143: if_icmpne +75 -> 218
    //   146: iconst_1
    //   147: istore 8
    //   149: iload 8
    //   151: ifne +73 -> 224
    //   154: new 34	my/apache/http/ParseException
    //   157: dup
    //   158: new 142	java/lang/StringBuilder
    //   161: dup
    //   162: ldc 144
    //   164: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   167: aload_1
    //   168: iload 5
    //   170: iload 6
    //   172: invokevirtual 149	my/apache/http/util/CharArrayBuffer:substring	(II)Ljava/lang/String;
    //   175: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   178: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   181: invokespecial 156	my/apache/http/ParseException:<init>	(Ljava/lang/String;)V
    //   184: athrow
    //   185: aload_1
    //   186: iload 7
    //   188: iload 9
    //   190: iadd
    //   191: invokevirtual 119	my/apache/http/util/CharArrayBuffer:charAt	(I)C
    //   194: aload_3
    //   195: iload 9
    //   197: invokevirtual 126	java/lang/String:charAt	(I)C
    //   200: if_icmpne +12 -> 212
    //   203: iconst_1
    //   204: istore 8
    //   206: iinc 9 1
    //   209: goto -94 -> 115
    //   212: iconst_0
    //   213: istore 8
    //   215: goto -9 -> 206
    //   218: iconst_0
    //   219: istore 8
    //   221: goto -72 -> 149
    //   224: iload 7
    //   226: iload 4
    //   228: iconst_1
    //   229: iadd
    //   230: iadd
    //   231: istore 10
    //   233: aload_1
    //   234: bipush 46
    //   236: iload 10
    //   238: iload 6
    //   240: invokevirtual 160	my/apache/http/util/CharArrayBuffer:indexOf	(III)I
    //   243: istore 11
    //   245: iload 11
    //   247: iconst_m1
    //   248: if_icmpne +34 -> 282
    //   251: new 34	my/apache/http/ParseException
    //   254: dup
    //   255: new 142	java/lang/StringBuilder
    //   258: dup
    //   259: ldc 162
    //   261: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   264: aload_1
    //   265: iload 5
    //   267: iload 6
    //   269: invokevirtual 149	my/apache/http/util/CharArrayBuffer:substring	(II)Ljava/lang/String;
    //   272: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   278: invokespecial 156	my/apache/http/ParseException:<init>	(Ljava/lang/String;)V
    //   281: athrow
    //   282: aload_1
    //   283: iload 10
    //   285: iload 11
    //   287: invokevirtual 165	my/apache/http/util/CharArrayBuffer:substringTrimmed	(II)Ljava/lang/String;
    //   290: invokestatic 171	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   293: istore 13
    //   295: iload 11
    //   297: iconst_1
    //   298: iadd
    //   299: istore 14
    //   301: aload_1
    //   302: bipush 32
    //   304: iload 14
    //   306: iload 6
    //   308: invokevirtual 160	my/apache/http/util/CharArrayBuffer:indexOf	(III)I
    //   311: istore 15
    //   313: iload 15
    //   315: iconst_m1
    //   316: if_icmpne +7 -> 323
    //   319: iload 6
    //   321: istore 15
    //   323: aload_1
    //   324: iload 14
    //   326: iload 15
    //   328: invokevirtual 165	my/apache/http/util/CharArrayBuffer:substringTrimmed	(II)Ljava/lang/String;
    //   331: invokestatic 171	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   334: istore 17
    //   336: aload_2
    //   337: iload 15
    //   339: invokevirtual 174	my/apache/http/message/ParserCursor:updatePos	(I)V
    //   342: aload_0
    //   343: iload 13
    //   345: iload 17
    //   347: invokevirtual 176	my/apache/http/message/BasicLineParser:createProtocolVersion	(II)Lmy/apache/http/ProtocolVersion;
    //   350: areturn
    //   351: astore 12
    //   353: new 34	my/apache/http/ParseException
    //   356: dup
    //   357: new 142	java/lang/StringBuilder
    //   360: dup
    //   361: ldc 178
    //   363: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   366: aload_1
    //   367: iload 5
    //   369: iload 6
    //   371: invokevirtual 149	my/apache/http/util/CharArrayBuffer:substring	(II)Ljava/lang/String;
    //   374: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   377: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   380: invokespecial 156	my/apache/http/ParseException:<init>	(Ljava/lang/String;)V
    //   383: athrow
    //   384: astore 16
    //   386: new 34	my/apache/http/ParseException
    //   389: dup
    //   390: new 142	java/lang/StringBuilder
    //   393: dup
    //   394: ldc 180
    //   396: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   399: aload_1
    //   400: iload 5
    //   402: iload 6
    //   404: invokevirtual 149	my/apache/http/util/CharArrayBuffer:substring	(II)Ljava/lang/String;
    //   407: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   410: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   413: invokespecial 156	my/apache/http/ParseException:<init>	(Ljava/lang/String;)V
    //   416: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   282	295	351	java/lang/NumberFormatException
    //   323	336	384	java/lang/NumberFormatException
  }

  public RequestLine parseRequestLine(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
    throws ParseException
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    int i = paramParserCursor.getPos();
    int j = paramParserCursor.getUpperBound();
    int k;
    int m;
    try
    {
      skipWhitespace(paramCharArrayBuffer, paramParserCursor);
      k = paramParserCursor.getPos();
      m = paramCharArrayBuffer.indexOf(32, k, j);
      if (m < 0)
        throw new ParseException("Invalid request line: " + paramCharArrayBuffer.substring(i, j));
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      throw new ParseException("Invalid request line: " + paramCharArrayBuffer.substring(i, j));
    }
    String str1 = paramCharArrayBuffer.substringTrimmed(k, m);
    paramParserCursor.updatePos(m);
    skipWhitespace(paramCharArrayBuffer, paramParserCursor);
    int n = paramParserCursor.getPos();
    int i1 = paramCharArrayBuffer.indexOf(32, n, j);
    if (i1 < 0)
      throw new ParseException("Invalid request line: " + paramCharArrayBuffer.substring(i, j));
    String str2 = paramCharArrayBuffer.substringTrimmed(n, i1);
    paramParserCursor.updatePos(i1);
    ProtocolVersion localProtocolVersion = parseProtocolVersion(paramCharArrayBuffer, paramParserCursor);
    skipWhitespace(paramCharArrayBuffer, paramParserCursor);
    if (!paramParserCursor.atEnd())
      throw new ParseException("Invalid request line: " + paramCharArrayBuffer.substring(i, j));
    RequestLine localRequestLine = createRequestLine(str1, str2, localProtocolVersion);
    return localRequestLine;
  }

  // ERROR //
  public StatusLine parseStatusLine(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
    throws ParseException
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +13 -> 14
    //   4: new 36	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 105
    //   10: invokespecial 41	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_2
    //   15: ifnonnull +13 -> 28
    //   18: new 36	java/lang/IllegalArgumentException
    //   21: dup
    //   22: ldc 107
    //   24: invokespecial 41	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   27: athrow
    //   28: aload_2
    //   29: invokevirtual 110	my/apache/http/message/ParserCursor:getPos	()I
    //   32: istore_3
    //   33: aload_2
    //   34: invokevirtual 136	my/apache/http/message/ParserCursor:getUpperBound	()I
    //   37: istore 4
    //   39: aload_0
    //   40: aload_1
    //   41: aload_2
    //   42: invokevirtual 185	my/apache/http/message/BasicLineParser:parseProtocolVersion	(Lmy/apache/http/util/CharArrayBuffer;Lmy/apache/http/message/ParserCursor;)Lmy/apache/http/ProtocolVersion;
    //   45: astore 6
    //   47: aload_0
    //   48: aload_1
    //   49: aload_2
    //   50: invokevirtual 140	my/apache/http/message/BasicLineParser:skipWhitespace	(Lmy/apache/http/util/CharArrayBuffer;Lmy/apache/http/message/ParserCursor;)V
    //   53: aload_2
    //   54: invokevirtual 110	my/apache/http/message/ParserCursor:getPos	()I
    //   57: istore 7
    //   59: aload_1
    //   60: bipush 32
    //   62: iload 7
    //   64: iload 4
    //   66: invokevirtual 160	my/apache/http/util/CharArrayBuffer:indexOf	(III)I
    //   69: istore 8
    //   71: iload 8
    //   73: ifge +7 -> 80
    //   76: iload 4
    //   78: istore 8
    //   80: aload_1
    //   81: iload 7
    //   83: iload 8
    //   85: invokevirtual 165	my/apache/http/util/CharArrayBuffer:substringTrimmed	(II)Ljava/lang/String;
    //   88: astore 9
    //   90: iconst_0
    //   91: istore 10
    //   93: aload 9
    //   95: invokevirtual 49	java/lang/String:length	()I
    //   98: istore 11
    //   100: iload 10
    //   102: iload 11
    //   104: if_icmplt +42 -> 146
    //   107: aload 9
    //   109: invokestatic 171	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   112: istore 13
    //   114: iload 8
    //   116: istore 14
    //   118: iload 14
    //   120: iload 4
    //   122: if_icmpge +137 -> 259
    //   125: aload_1
    //   126: iload 14
    //   128: iload 4
    //   130: invokevirtual 165	my/apache/http/util/CharArrayBuffer:substringTrimmed	(II)Ljava/lang/String;
    //   133: astore 15
    //   135: aload_0
    //   136: aload 6
    //   138: iload 13
    //   140: aload 15
    //   142: invokevirtual 193	my/apache/http/message/BasicLineParser:createStatusLine	(Lmy/apache/http/ProtocolVersion;ILjava/lang/String;)Lmy/apache/http/StatusLine;
    //   145: areturn
    //   146: aload 9
    //   148: iload 10
    //   150: invokevirtual 126	java/lang/String:charAt	(I)C
    //   153: invokestatic 198	java/lang/Character:isDigit	(C)Z
    //   156: ifne +65 -> 221
    //   159: new 34	my/apache/http/ParseException
    //   162: dup
    //   163: new 142	java/lang/StringBuilder
    //   166: dup
    //   167: ldc 200
    //   169: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   172: aload_1
    //   173: iload_3
    //   174: iload 4
    //   176: invokevirtual 149	my/apache/http/util/CharArrayBuffer:substring	(II)Ljava/lang/String;
    //   179: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   185: invokespecial 156	my/apache/http/ParseException:<init>	(Ljava/lang/String;)V
    //   188: athrow
    //   189: astore 5
    //   191: new 34	my/apache/http/ParseException
    //   194: dup
    //   195: new 142	java/lang/StringBuilder
    //   198: dup
    //   199: ldc 202
    //   201: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   204: aload_1
    //   205: iload_3
    //   206: iload 4
    //   208: invokevirtual 149	my/apache/http/util/CharArrayBuffer:substring	(II)Ljava/lang/String;
    //   211: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   214: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   217: invokespecial 156	my/apache/http/ParseException:<init>	(Ljava/lang/String;)V
    //   220: athrow
    //   221: iinc 10 1
    //   224: goto -131 -> 93
    //   227: astore 12
    //   229: new 34	my/apache/http/ParseException
    //   232: dup
    //   233: new 142	java/lang/StringBuilder
    //   236: dup
    //   237: ldc 200
    //   239: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   242: aload_1
    //   243: iload_3
    //   244: iload 4
    //   246: invokevirtual 149	my/apache/http/util/CharArrayBuffer:substring	(II)Ljava/lang/String;
    //   249: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   252: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   255: invokespecial 156	my/apache/http/ParseException:<init>	(Ljava/lang/String;)V
    //   258: athrow
    //   259: ldc 204
    //   261: astore 15
    //   263: goto -128 -> 135
    //
    // Exception table:
    //   from	to	target	type
    //   39	71	189	java/lang/IndexOutOfBoundsException
    //   80	90	189	java/lang/IndexOutOfBoundsException
    //   93	100	189	java/lang/IndexOutOfBoundsException
    //   107	114	189	java/lang/IndexOutOfBoundsException
    //   125	135	189	java/lang/IndexOutOfBoundsException
    //   135	146	189	java/lang/IndexOutOfBoundsException
    //   146	189	189	java/lang/IndexOutOfBoundsException
    //   229	259	189	java/lang/IndexOutOfBoundsException
    //   107	114	227	java/lang/NumberFormatException
  }

  protected void skipWhitespace(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    int i = paramParserCursor.getPos();
    int j = paramParserCursor.getUpperBound();
    while (true)
    {
      if ((i >= j) || (!HTTP.isWhitespace(paramCharArrayBuffer.charAt(i))))
      {
        paramParserCursor.updatePos(i);
        return;
      }
      i++;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.message.BasicLineParser
 * JD-Core Version:    0.6.2
 */