package com.millennialmedia.android;

import java.util.TreeMap;

final class MMConversionTracker
{
  private static final String KEY_REFERRER = "installReferrer";

  // ERROR //
  protected static void trackConversion(android.content.Context paramContext, String paramString, MMRequest paramMMRequest)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: lconst_0
    //   4: lstore_3
    //   5: aload_0
    //   6: ifnull +18 -> 24
    //   9: aload_1
    //   10: ifnull +14 -> 24
    //   13: aload_1
    //   14: invokevirtual 24	java/lang/String:length	()I
    //   17: istore 6
    //   19: iload 6
    //   21: ifne +7 -> 28
    //   24: ldc 2
    //   26: monitorexit
    //   27: return
    //   28: aload_0
    //   29: ldc 26
    //   31: iconst_0
    //   32: invokevirtual 32	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   35: astore 7
    //   37: aload 7
    //   39: new 34	java/lang/StringBuilder
    //   42: dup
    //   43: invokespecial 35	java/lang/StringBuilder:<init>	()V
    //   46: ldc 37
    //   48: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: aload_1
    //   52: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: invokevirtual 45	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   58: iconst_1
    //   59: invokeinterface 51 3 0
    //   64: istore 8
    //   66: aload 7
    //   68: ldc 8
    //   70: aconst_null
    //   71: invokeinterface 55 3 0
    //   76: astore 9
    //   78: new 57	java/util/TreeMap
    //   81: dup
    //   82: invokespecial 58	java/util/TreeMap:<init>	()V
    //   85: astore 10
    //   87: aload_2
    //   88: ifnull +14 -> 102
    //   91: aload_2
    //   92: aload 10
    //   94: invokevirtual 64	com/millennialmedia/android/MMRequest:getUrlParams	(Ljava/util/Map;)V
    //   97: aload 10
    //   99: invokestatic 67	com/millennialmedia/android/MMRequest:insertLocation	(Ljava/util/Map;)V
    //   102: aload 9
    //   104: ifnull +63 -> 167
    //   107: aload 9
    //   109: ldc 69
    //   111: invokevirtual 73	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   114: astore 11
    //   116: aload 11
    //   118: arraylength
    //   119: istore 12
    //   121: iconst_0
    //   122: istore 13
    //   124: iload 13
    //   126: iload 12
    //   128: if_icmpge +39 -> 167
    //   131: aload 11
    //   133: iload 13
    //   135: aaload
    //   136: ldc 75
    //   138: invokevirtual 73	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   141: astore 14
    //   143: aload 14
    //   145: arraylength
    //   146: iconst_2
    //   147: if_icmplt +243 -> 390
    //   150: aload 10
    //   152: aload 14
    //   154: iconst_0
    //   155: aaload
    //   156: aload 14
    //   158: iconst_1
    //   159: aaload
    //   160: invokevirtual 79	java/util/TreeMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   163: pop
    //   164: goto +226 -> 390
    //   167: iload 8
    //   169: ifeq +48 -> 217
    //   172: aload 7
    //   174: invokeinterface 83 1 0
    //   179: astore 16
    //   181: aload 16
    //   183: new 34	java/lang/StringBuilder
    //   186: dup
    //   187: invokespecial 35	java/lang/StringBuilder:<init>	()V
    //   190: ldc 37
    //   192: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: aload_1
    //   196: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   199: invokevirtual 45	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   202: iconst_0
    //   203: invokeinterface 89 3 0
    //   208: pop
    //   209: aload 16
    //   211: invokeinterface 93 1 0
    //   216: pop
    //   217: aload_0
    //   218: invokevirtual 97	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   221: aload_0
    //   222: invokevirtual 100	android/content/Context:getPackageName	()Ljava/lang/String;
    //   225: iconst_0
    //   226: invokevirtual 106	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   229: astore 25
    //   231: aload 25
    //   233: invokevirtual 110	java/lang/Object:getClass	()Ljava/lang/Class;
    //   236: ldc 112
    //   238: invokevirtual 118	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   241: aload 25
    //   243: invokevirtual 124	java/lang/reflect/Field:getLong	(Ljava/lang/Object;)J
    //   246: lstore 27
    //   248: lload 27
    //   250: lstore_3
    //   251: lload_3
    //   252: lconst_0
    //   253: lcmp
    //   254: ifle +34 -> 288
    //   257: new 126	java/util/GregorianCalendar
    //   260: dup
    //   261: invokespecial 127	java/util/GregorianCalendar:<init>	()V
    //   264: astore 24
    //   266: aload 24
    //   268: lload_3
    //   269: invokevirtual 131	java/util/GregorianCalendar:setTimeInMillis	(J)V
    //   272: aload 24
    //   274: ldc 133
    //   276: invokestatic 139	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   279: invokevirtual 143	java/util/GregorianCalendar:setTimeZone	(Ljava/util/TimeZone;)V
    //   282: aload 24
    //   284: invokevirtual 147	java/util/GregorianCalendar:getTimeInMillis	()J
    //   287: lstore_3
    //   288: lload_3
    //   289: lstore 20
    //   291: aload_0
    //   292: invokestatic 153	com/millennialmedia/android/MMSDK:isConnected	(Landroid/content/Context;)Z
    //   295: ifeq +77 -> 372
    //   298: aload 10
    //   300: ldc 155
    //   302: new 34	java/lang/StringBuilder
    //   305: dup
    //   306: invokespecial 35	java/lang/StringBuilder:<init>	()V
    //   309: ldc 157
    //   311: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   314: getstatic 162	android/os/Build:MODEL	Ljava/lang/String;
    //   317: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   320: invokevirtual 45	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   323: invokevirtual 79	java/util/TreeMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   326: pop
    //   327: aload 10
    //   329: ldc 164
    //   331: getstatic 168	com/millennialmedia/android/HandShake:apid	Ljava/lang/String;
    //   334: invokevirtual 79	java/util/TreeMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   337: pop
    //   338: aload_0
    //   339: aload 10
    //   341: invokestatic 172	com/millennialmedia/android/MMSDK:insertUrlCommonValues	(Landroid/content/Context;Ljava/util/Map;)V
    //   344: new 174	com/millennialmedia/android/MMConversionTracker$1
    //   347: dup
    //   348: aload_1
    //   349: iload 8
    //   351: lload 20
    //   353: aload 10
    //   355: invokespecial 177	com/millennialmedia/android/MMConversionTracker$1:<init>	(Ljava/lang/String;ZJLjava/util/TreeMap;)V
    //   358: invokestatic 183	com/millennialmedia/android/Utils$ThreadUtils:execute	(Ljava/lang/Runnable;)V
    //   361: goto -337 -> 24
    //   364: astore 5
    //   366: ldc 2
    //   368: monitorexit
    //   369: aload 5
    //   371: athrow
    //   372: ldc 185
    //   374: invokestatic 191	com/millennialmedia/android/MMSDK$Log:w	(Ljava/lang/String;)V
    //   377: goto -353 -> 24
    //   380: astore 19
    //   382: goto -131 -> 251
    //   385: astore 26
    //   387: goto -136 -> 251
    //   390: iinc 13 1
    //   393: goto -269 -> 124
    //
    // Exception table:
    //   from	to	target	type
    //   13	19	364	finally
    //   28	87	364	finally
    //   91	102	364	finally
    //   107	121	364	finally
    //   131	164	364	finally
    //   172	217	364	finally
    //   217	231	364	finally
    //   231	248	364	finally
    //   257	288	364	finally
    //   291	361	364	finally
    //   372	377	364	finally
    //   217	231	380	android/content/pm/PackageManager$NameNotFoundException
    //   231	248	380	android/content/pm/PackageManager$NameNotFoundException
    //   231	248	385	java/lang/Exception
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.MMConversionTracker
 * JD-Core Version:    0.6.2
 */