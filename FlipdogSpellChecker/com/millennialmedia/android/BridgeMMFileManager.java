package com.millennialmedia.android;

import android.content.Context;
import android.net.Uri;
import android.os.StatFs;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import org.json.JSONArray;

class BridgeMMFileManager extends MMJSObject
{
  private File root;

  private boolean hasCreativeDirectory()
  {
    Context localContext = (Context)this.contextRef.get();
    if (localContext != null)
    {
      File localFile = AdCache.getCacheDirectory(localContext);
      this.root = localFile;
      if (localFile != null)
        return true;
    }
    return false;
  }

  public MMJSResponse cleanupCache(HashMap<String, String> paramHashMap)
  {
    long l = 259200000L;
    if (hasCreativeDirectory())
    {
      boolean bool1 = paramHashMap.containsKey("clear");
      boolean bool2 = false;
      if (bool1)
        bool2 = Boolean.parseBoolean((String)paramHashMap.get("clear"));
      if (!bool2)
        break label58;
      l = 0L;
    }
    try
    {
      while (true)
      {
        AdCache.cleanupDirectory(this.root, l);
        label56: return null;
        label58: Context localContext = (Context)this.contextRef.get();
        if (localContext != null)
          l = HandShake.sharedHandShake(localContext).creativeCacheTimeout;
      }
    }
    catch (SecurityException localSecurityException)
    {
      break label56;
    }
  }

  public MMJSResponse downloadFile(HashMap<String, String> paramHashMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str1 = (String)paramHashMap.get("url");
    if ((!TextUtils.isEmpty(str1)) && (localContext != null))
    {
      if (paramHashMap.containsKey("path"));
      for (String str2 = (String)paramHashMap.get("path"); AdCache.downloadComponentToCache(str1, str2, localContext); str2 = Uri.parse((String)paramHashMap.get("url")).getLastPathSegment())
        return MMJSResponse.responseWithSuccess(str2);
    }
    return null;
  }

  public MMJSResponse getDirectoryContents(HashMap<String, String> paramHashMap)
  {
    if (hasCreativeDirectory())
    {
      if (paramHashMap.containsKey("path"));
      JSONArray localJSONArray;
      for (File localFile = new File(this.root, (String)paramHashMap.get("path")); ; localFile = this.root)
      {
        localJSONArray = new JSONArray();
        String[] arrayOfString = localFile.list();
        int i = arrayOfString.length;
        for (int j = 0; j < i; j++)
          localJSONArray.put(arrayOfString[j]);
      }
      MMJSResponse localMMJSResponse = new MMJSResponse();
      localMMJSResponse.result = 1;
      localMMJSResponse.response = localJSONArray;
      return localMMJSResponse;
    }
    return null;
  }

  // ERROR //
  public MMJSResponse getFileContents(HashMap<String, String> paramHashMap)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 40	com/millennialmedia/android/BridgeMMFileManager:hasCreativeDirectory	()Z
    //   4: ifeq +147 -> 151
    //   7: aconst_null
    //   8: astore_2
    //   9: aload_1
    //   10: ldc 84
    //   12: invokevirtual 48	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   15: ifeq +136 -> 151
    //   18: new 107	java/io/File
    //   21: dup
    //   22: aload_0
    //   23: getfield 32	com/millennialmedia/android/BridgeMMFileManager:root	Ljava/io/File;
    //   26: aload_1
    //   27: ldc 84
    //   29: invokevirtual 51	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   32: checkcast 53	java/lang/String
    //   35: invokespecial 110	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   38: astore_3
    //   39: new 135	java/io/FileInputStream
    //   42: dup
    //   43: aload_3
    //   44: invokespecial 138	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   47: astore 4
    //   49: aload_3
    //   50: invokevirtual 142	java/io/File:length	()J
    //   53: l2i
    //   54: newarray byte
    //   56: astore 6
    //   58: aload 4
    //   60: aload 6
    //   62: invokevirtual 146	java/io/FileInputStream:read	([B)I
    //   65: pop
    //   66: aload 4
    //   68: ifnull +8 -> 76
    //   71: aload 4
    //   73: invokevirtual 149	java/io/FileInputStream:close	()V
    //   76: aload 6
    //   78: ifnull +73 -> 151
    //   81: new 90	com/millennialmedia/android/MMJSResponse
    //   84: dup
    //   85: invokespecial 122	com/millennialmedia/android/MMJSResponse:<init>	()V
    //   88: astore 8
    //   90: aload 8
    //   92: iconst_1
    //   93: putfield 126	com/millennialmedia/android/MMJSResponse:result	I
    //   96: aload 8
    //   98: aload 6
    //   100: putfield 153	com/millennialmedia/android/MMJSResponse:dataResponse	[B
    //   103: aload 8
    //   105: areturn
    //   106: astore 12
    //   108: goto -32 -> 76
    //   111: astore 13
    //   113: aconst_null
    //   114: astore 6
    //   116: aload_2
    //   117: ifnull -41 -> 76
    //   120: aload_2
    //   121: invokevirtual 149	java/io/FileInputStream:close	()V
    //   124: aconst_null
    //   125: astore 6
    //   127: goto -51 -> 76
    //   130: astore 7
    //   132: aconst_null
    //   133: astore 6
    //   135: goto -59 -> 76
    //   138: astore 9
    //   140: aload_2
    //   141: ifnull +7 -> 148
    //   144: aload_2
    //   145: invokevirtual 149	java/io/FileInputStream:close	()V
    //   148: aload 9
    //   150: athrow
    //   151: aconst_null
    //   152: areturn
    //   153: astore 10
    //   155: goto -7 -> 148
    //   158: astore 9
    //   160: aload 4
    //   162: astore_2
    //   163: goto -23 -> 140
    //   166: astore 5
    //   168: aload 4
    //   170: astore_2
    //   171: goto -58 -> 113
    //
    // Exception table:
    //   from	to	target	type
    //   71	76	106	java/lang/Exception
    //   18	49	111	java/lang/Exception
    //   120	124	130	java/lang/Exception
    //   18	49	138	finally
    //   144	148	153	java/lang/Exception
    //   49	66	158	finally
    //   49	66	166	java/lang/Exception
  }

  public MMJSResponse getFreeDiskSpace(HashMap<String, String> paramHashMap)
  {
    if (hasCreativeDirectory())
    {
      MMJSResponse localMMJSResponse = new MMJSResponse();
      localMMJSResponse.result = 1;
      StatFs localStatFs = new StatFs(this.root.getAbsolutePath());
      localMMJSResponse.response = new Long(localStatFs.getAvailableBlocks() * localStatFs.getBlockSize());
      return localMMJSResponse;
    }
    return null;
  }

  public MMJSResponse getMimeType(HashMap<String, String> paramHashMap)
  {
    if (hasCreativeDirectory())
    {
      String[] arrayOfString = ((String)paramHashMap.get("path")).split("\\.");
      String str = MimeTypeMap.getSingleton().getMimeTypeFromExtension(arrayOfString[(-1 + arrayOfString.length)]);
      if (str != null)
      {
        MMJSResponse localMMJSResponse = new MMJSResponse();
        localMMJSResponse.result = 1;
        localMMJSResponse.response = str;
        return localMMJSResponse;
      }
    }
    return null;
  }

  public MMJSResponse moveFile(HashMap<String, String> paramHashMap)
  {
    if (hasCreativeDirectory())
      try
      {
        String str1 = (String)paramHashMap.get("fromPath");
        String str2 = (String)paramHashMap.get("toPath");
        if ((str1 != null) && (str2 != null) && (new File(this.root, str1).renameTo(new File(this.root, str2))))
        {
          MMJSResponse localMMJSResponse = MMJSResponse.responseWithSuccess("File moved successfully");
          return localMMJSResponse;
        }
      }
      catch (Exception localException)
      {
      }
    return null;
  }

  public MMJSResponse removeAtPath(HashMap<String, String> paramHashMap)
  {
    if (hasCreativeDirectory())
      try
      {
        String str = (String)paramHashMap.get("path");
        if ((str != null) && (new File(this.root, str).delete()))
        {
          MMJSResponse localMMJSResponse = MMJSResponse.responseWithSuccess("File removed successfully");
          return localMMJSResponse;
        }
      }
      catch (Exception localException)
      {
      }
    return null;
  }

  // ERROR //
  public MMJSResponse writeData(HashMap<String, String> paramHashMap)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 40	com/millennialmedia/android/BridgeMMFileManager:hasCreativeDirectory	()Z
    //   4: ifeq +144 -> 148
    //   7: aconst_null
    //   8: astore_2
    //   9: aload_1
    //   10: ldc 84
    //   12: invokevirtual 48	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   15: ifeq +133 -> 148
    //   18: aload_1
    //   19: ldc 211
    //   21: invokevirtual 48	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   24: ifeq +124 -> 148
    //   27: new 107	java/io/File
    //   30: dup
    //   31: aload_0
    //   32: getfield 32	com/millennialmedia/android/BridgeMMFileManager:root	Ljava/io/File;
    //   35: aload_1
    //   36: ldc 84
    //   38: invokevirtual 51	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   41: checkcast 53	java/lang/String
    //   44: invokespecial 110	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   47: astore_3
    //   48: aload_1
    //   49: ldc 211
    //   51: invokevirtual 51	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   54: checkcast 53	java/lang/String
    //   57: invokestatic 217	com/millennialmedia/android/Base64:decode	(Ljava/lang/String;)[B
    //   60: astore 9
    //   62: new 219	java/io/FileOutputStream
    //   65: dup
    //   66: aload_3
    //   67: invokespecial 220	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   70: astore 10
    //   72: aload 10
    //   74: aload 9
    //   76: invokevirtual 224	java/io/FileOutputStream:write	([B)V
    //   79: iconst_1
    //   80: istore 7
    //   82: aload 10
    //   84: ifnull +8 -> 92
    //   87: aload 10
    //   89: invokevirtual 225	java/io/FileOutputStream:close	()V
    //   92: iload 7
    //   94: ifeq +54 -> 148
    //   97: ldc 227
    //   99: invokestatic 94	com/millennialmedia/android/MMJSResponse:responseWithSuccess	(Ljava/lang/String;)Lcom/millennialmedia/android/MMJSResponse;
    //   102: areturn
    //   103: astore 12
    //   105: goto -13 -> 92
    //   108: astore 6
    //   110: iconst_0
    //   111: istore 7
    //   113: aload_2
    //   114: ifnull -22 -> 92
    //   117: aload_2
    //   118: invokevirtual 225	java/io/FileOutputStream:close	()V
    //   121: iconst_0
    //   122: istore 7
    //   124: goto -32 -> 92
    //   127: astore 8
    //   129: iconst_0
    //   130: istore 7
    //   132: goto -40 -> 92
    //   135: astore 4
    //   137: aload_2
    //   138: ifnull +7 -> 145
    //   141: aload_2
    //   142: invokevirtual 225	java/io/FileOutputStream:close	()V
    //   145: aload 4
    //   147: athrow
    //   148: aconst_null
    //   149: areturn
    //   150: astore 5
    //   152: goto -7 -> 145
    //   155: astore 4
    //   157: aload 10
    //   159: astore_2
    //   160: goto -23 -> 137
    //   163: astore 11
    //   165: aload 10
    //   167: astore_2
    //   168: goto -58 -> 110
    //
    // Exception table:
    //   from	to	target	type
    //   87	92	103	java/lang/Exception
    //   27	72	108	java/lang/Exception
    //   117	121	127	java/lang/Exception
    //   27	72	135	finally
    //   141	145	150	java/lang/Exception
    //   72	79	155	finally
    //   72	79	163	java/lang/Exception
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.BridgeMMFileManager
 * JD-Core Version:    0.6.2
 */