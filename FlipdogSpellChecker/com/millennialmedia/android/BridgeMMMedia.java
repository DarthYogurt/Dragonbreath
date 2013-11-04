package com.millennialmedia.android;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;

class BridgeMMMedia extends MMJSObject
{
  private static final String PATH = "path";
  private static final String PICTURES = "Pictures";
  private static Object pickerActivityObject;
  MediaScannerConnection mediaScanner;

  private static Bitmap centerOfImage(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    float f1 = (paramBitmap.getWidth() - paramInt1) / 2;
    float f2 = (paramBitmap.getHeight() - paramInt2) / 2;
    return cropImage(paramBitmap, (int)f1, (int)f2, paramInt1, paramInt2);
  }

  private static Bitmap cropImage(Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return Bitmap.createBitmap(paramBitmap, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  private boolean isCameraAvailable()
  {
    Context localContext = (Context)this.contextRef.get();
    boolean bool = false;
    if (localContext != null)
    {
      int i = localContext.getPackageManager().checkPermission("android.permission.CAMERA", localContext.getPackageName());
      bool = false;
      if (i == 0)
      {
        Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        int j = localContext.getPackageManager().queryIntentActivities(localIntent, 65536).size();
        bool = false;
        if (j > 0)
          bool = true;
      }
    }
    return bool;
  }

  private boolean isPictureChooserAvailable()
  {
    Context localContext = (Context)this.contextRef.get();
    boolean bool = false;
    if (localContext != null)
    {
      Intent localIntent = new Intent();
      localIntent.setType("image/*");
      localIntent.setAction("android.intent.action.GET_CONTENT");
      int i = localContext.getPackageManager().queryIntentActivities(localIntent, 65536).size();
      bool = false;
      if (i > 0)
        bool = true;
    }
    return bool;
  }

  private boolean moveFile(File paramFile1, File paramFile2)
  {
    try
    {
      FileChannel localFileChannel1 = new FileInputStream(paramFile1).getChannel();
      paramFile2.createNewFile();
      FileChannel localFileChannel2 = new FileOutputStream(paramFile2).getChannel();
      localFileChannel1.transferTo(0L, localFileChannel1.size(), localFileChannel2);
      return true;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  private static Bitmap resizeImage(Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3)
  {
    return Bitmap.createScaledBitmap(paramBitmap, paramInt1, paramInt2, true);
  }

  static Bitmap resizeImage(Bitmap paramBitmap, String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    float f1 = paramInt1 / paramBitmap.getWidth();
    float f2 = paramInt2 / paramBitmap.getHeight();
    if (paramString.equals("Center"))
      return centerOfImage(paramBitmap, paramInt1, paramInt2);
    if (paramString.equals("ScaleToAspectFit"))
    {
      float f4 = Math.min(f1, f2);
      return resizeImage(paramBitmap, (int)(f4 * paramBitmap.getWidth()), (int)(f4 * paramBitmap.getHeight()), paramInt3);
    }
    if (paramString.equals("ScaleToAspectFill"))
    {
      float f3 = Math.max(f1, f2);
      return cropImage(resizeImage(paramBitmap, (int)(f3 * paramBitmap.getWidth()), (int)(f3 * paramBitmap.getHeight()), paramInt3), 0, 0, paramInt1, paramInt2);
    }
    return resizeImage(paramBitmap, paramInt1, paramInt2, paramInt3);
  }

  // ERROR //
  private static final byte[] scaleBitmapToBytes(File paramFile, int paramInt1, int paramInt2, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: new 106	java/io/FileInputStream
    //   9: dup
    //   10: aload_0
    //   11: invokespecial 109	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   14: astore 6
    //   16: new 174	android/graphics/BitmapFactory$Options
    //   19: dup
    //   20: invokespecial 175	android/graphics/BitmapFactory$Options:<init>	()V
    //   23: astore 7
    //   25: aload 7
    //   27: iconst_1
    //   28: putfield 179	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   31: aload 6
    //   33: aconst_null
    //   34: aload 7
    //   36: invokestatic 185	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   39: pop
    //   40: aload 7
    //   42: getfield 189	android/graphics/BitmapFactory$Options:outHeight	I
    //   45: istore 27
    //   47: aload 7
    //   49: getfield 192	android/graphics/BitmapFactory$Options:outWidth	I
    //   52: istore 28
    //   54: iconst_1
    //   55: istore 29
    //   57: iload 27
    //   59: iload_2
    //   60: if_icmpgt +9 -> 69
    //   63: iload 28
    //   65: iload_1
    //   66: if_icmple +21 -> 87
    //   69: iload 28
    //   71: iload 27
    //   73: if_icmple +172 -> 245
    //   76: iload 27
    //   78: i2f
    //   79: iload_2
    //   80: i2f
    //   81: fdiv
    //   82: invokestatic 196	java/lang/Math:round	(F)I
    //   85: istore 29
    //   87: new 106	java/io/FileInputStream
    //   90: dup
    //   91: aload_0
    //   92: invokespecial 109	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   95: astore 30
    //   97: aload 7
    //   99: iconst_0
    //   100: putfield 179	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   103: aload 7
    //   105: iload 29
    //   107: putfield 199	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   110: aload 30
    //   112: aconst_null
    //   113: aload 7
    //   115: invokestatic 185	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   118: astore 32
    //   120: aload 32
    //   122: astore 9
    //   124: aload 6
    //   126: ifnull +8 -> 134
    //   129: aload 6
    //   131: invokevirtual 202	java/io/FileInputStream:close	()V
    //   134: aload 30
    //   136: ifnull +8 -> 144
    //   139: aload 30
    //   141: invokevirtual 202	java/io/FileInputStream:close	()V
    //   144: aconst_null
    //   145: astore 11
    //   147: aload 9
    //   149: ifnull +93 -> 242
    //   152: aload 9
    //   154: aload_3
    //   155: iload_1
    //   156: iload_2
    //   157: iconst_1
    //   158: invokestatic 204	com/millennialmedia/android/BridgeMMMedia:resizeImage	(Landroid/graphics/Bitmap;Ljava/lang/String;III)Landroid/graphics/Bitmap;
    //   161: astore 12
    //   163: aconst_null
    //   164: astore 13
    //   166: new 206	java/io/ByteArrayOutputStream
    //   169: dup
    //   170: invokespecial 207	java/io/ByteArrayOutputStream:<init>	()V
    //   173: astore 14
    //   175: aload_3
    //   176: ldc 209
    //   178: invokevirtual 149	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   181: ifeq +155 -> 336
    //   184: getstatic 215	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   187: astore 22
    //   189: aload 9
    //   191: aload 22
    //   193: bipush 100
    //   195: aload 14
    //   197: invokevirtual 219	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   200: pop
    //   201: aload 14
    //   203: invokevirtual 223	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   206: astore 20
    //   208: aload 20
    //   210: astore 11
    //   212: aload 9
    //   214: ifnull +8 -> 222
    //   217: aload 9
    //   219: invokevirtual 226	android/graphics/Bitmap:recycle	()V
    //   222: aload 12
    //   224: ifnull +8 -> 232
    //   227: aload 12
    //   229: invokevirtual 226	android/graphics/Bitmap:recycle	()V
    //   232: aload 14
    //   234: ifnull +8 -> 242
    //   237: aload 14
    //   239: invokevirtual 227	java/io/ByteArrayOutputStream:close	()V
    //   242: aload 11
    //   244: areturn
    //   245: iload 28
    //   247: i2f
    //   248: iload_1
    //   249: i2f
    //   250: fdiv
    //   251: fstore 34
    //   253: fload 34
    //   255: invokestatic 196	java/lang/Math:round	(F)I
    //   258: istore 35
    //   260: iload 35
    //   262: istore 29
    //   264: goto -177 -> 87
    //   267: astore 33
    //   269: goto -125 -> 144
    //   272: astore 36
    //   274: aload 4
    //   276: ifnull +8 -> 284
    //   279: aload 4
    //   281: invokevirtual 202	java/io/FileInputStream:close	()V
    //   284: aconst_null
    //   285: astore 9
    //   287: aload 5
    //   289: ifnull -145 -> 144
    //   292: aload 5
    //   294: invokevirtual 202	java/io/FileInputStream:close	()V
    //   297: aconst_null
    //   298: astore 9
    //   300: goto -156 -> 144
    //   303: astore 10
    //   305: aconst_null
    //   306: astore 9
    //   308: goto -164 -> 144
    //   311: astore 24
    //   313: aload 4
    //   315: ifnull +8 -> 323
    //   318: aload 4
    //   320: invokevirtual 202	java/io/FileInputStream:close	()V
    //   323: aload 5
    //   325: ifnull +8 -> 333
    //   328: aload 5
    //   330: invokevirtual 202	java/io/FileInputStream:close	()V
    //   333: aload 24
    //   335: athrow
    //   336: aload 12
    //   338: getstatic 215	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   341: bipush 100
    //   343: aload 14
    //   345: invokevirtual 219	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   348: pop
    //   349: goto -148 -> 201
    //   352: astore 17
    //   354: aload 14
    //   356: astore 13
    //   358: aload 17
    //   360: invokevirtual 134	java/lang/Exception:printStackTrace	()V
    //   363: aload 9
    //   365: ifnull +8 -> 373
    //   368: aload 9
    //   370: invokevirtual 226	android/graphics/Bitmap:recycle	()V
    //   373: aload 12
    //   375: ifnull +8 -> 383
    //   378: aload 12
    //   380: invokevirtual 226	android/graphics/Bitmap:recycle	()V
    //   383: aconst_null
    //   384: astore 11
    //   386: aload 13
    //   388: ifnull -146 -> 242
    //   391: aload 13
    //   393: invokevirtual 227	java/io/ByteArrayOutputStream:close	()V
    //   396: aconst_null
    //   397: areturn
    //   398: astore 18
    //   400: aload 18
    //   402: invokevirtual 134	java/lang/Exception:printStackTrace	()V
    //   405: aconst_null
    //   406: areturn
    //   407: astore 21
    //   409: aload 21
    //   411: invokevirtual 134	java/lang/Exception:printStackTrace	()V
    //   414: aload 11
    //   416: areturn
    //   417: astore 15
    //   419: aload 9
    //   421: ifnull +8 -> 429
    //   424: aload 9
    //   426: invokevirtual 226	android/graphics/Bitmap:recycle	()V
    //   429: aload 12
    //   431: ifnull +8 -> 439
    //   434: aload 12
    //   436: invokevirtual 226	android/graphics/Bitmap:recycle	()V
    //   439: aload 13
    //   441: ifnull +8 -> 449
    //   444: aload 13
    //   446: invokevirtual 227	java/io/ByteArrayOutputStream:close	()V
    //   449: aload 15
    //   451: athrow
    //   452: astore 16
    //   454: aload 16
    //   456: invokevirtual 134	java/lang/Exception:printStackTrace	()V
    //   459: goto -10 -> 449
    //   462: astore 15
    //   464: aload 14
    //   466: astore 13
    //   468: goto -49 -> 419
    //   471: astore 17
    //   473: aconst_null
    //   474: astore 13
    //   476: goto -118 -> 358
    //   479: astore 25
    //   481: goto -148 -> 333
    //   484: astore 24
    //   486: aload 6
    //   488: astore 4
    //   490: aconst_null
    //   491: astore 5
    //   493: goto -180 -> 313
    //   496: astore 24
    //   498: aload 30
    //   500: astore 5
    //   502: aload 6
    //   504: astore 4
    //   506: goto -193 -> 313
    //   509: astore 8
    //   511: aload 6
    //   513: astore 4
    //   515: aconst_null
    //   516: astore 5
    //   518: goto -244 -> 274
    //   521: astore 31
    //   523: aload 30
    //   525: astore 5
    //   527: aload 6
    //   529: astore 4
    //   531: goto -257 -> 274
    //
    // Exception table:
    //   from	to	target	type
    //   129	134	267	java/io/IOException
    //   139	144	267	java/io/IOException
    //   6	16	272	java/io/FileNotFoundException
    //   279	284	303	java/io/IOException
    //   292	297	303	java/io/IOException
    //   6	16	311	finally
    //   175	201	352	java/lang/Exception
    //   201	208	352	java/lang/Exception
    //   336	349	352	java/lang/Exception
    //   368	373	398	java/lang/Exception
    //   378	383	398	java/lang/Exception
    //   391	396	398	java/lang/Exception
    //   217	222	407	java/lang/Exception
    //   227	232	407	java/lang/Exception
    //   237	242	407	java/lang/Exception
    //   166	175	417	finally
    //   358	363	417	finally
    //   424	429	452	java/lang/Exception
    //   434	439	452	java/lang/Exception
    //   444	449	452	java/lang/Exception
    //   175	201	462	finally
    //   201	208	462	finally
    //   336	349	462	finally
    //   166	175	471	java/lang/Exception
    //   318	323	479	java/io/IOException
    //   328	333	479	java/io/IOException
    //   16	54	484	finally
    //   76	87	484	finally
    //   87	97	484	finally
    //   253	260	484	finally
    //   97	120	496	finally
    //   16	54	509	java/io/FileNotFoundException
    //   76	87	509	java/io/FileNotFoundException
    //   87	97	509	java/io/FileNotFoundException
    //   253	260	509	java/io/FileNotFoundException
    //   97	120	521	java/io/FileNotFoundException
  }

  private void scanMedia(final String paramString)
  {
    Context localContext = (Context)this.contextRef.get();
    if (localContext != null)
    {
      this.mediaScanner = new MediaScannerConnection(localContext.getApplicationContext(), new MediaScannerConnection.MediaScannerConnectionClient()
      {
        public void onMediaScannerConnected()
        {
          if (BridgeMMMedia.this.mediaScanner != null)
            BridgeMMMedia.this.mediaScanner.scanFile(paramString, null);
        }

        public void onScanCompleted(String paramAnonymousString, Uri paramAnonymousUri)
        {
          if (paramAnonymousUri == null)
            MMSDK.Log.d("Failed to scan " + paramAnonymousString);
        }
      });
      if (this.mediaScanner != null)
        this.mediaScanner.connect();
    }
  }

  public MMJSResponse availableSourceTypes(HashMap<String, String> paramHashMap)
  {
    JSONArray localJSONArray = new JSONArray();
    if (isCameraAvailable())
      localJSONArray.put("Camera");
    if (isPictureChooserAvailable())
      localJSONArray.put("Photo Library");
    MMJSResponse localMMJSResponse = new MMJSResponse();
    localMMJSResponse.result = 1;
    localMMJSResponse.response = localJSONArray;
    return localMMJSResponse;
  }

  // ERROR //
  public MMJSResponse getPicture(HashMap<String, String> paramHashMap)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 47	com/millennialmedia/android/BridgeMMMedia:contextRef	Ljava/lang/ref/WeakReference;
    //   6: invokevirtual 52	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   9: checkcast 54	android/content/Context
    //   12: astore_3
    //   13: aload_1
    //   14: ldc_w 276
    //   17: invokevirtual 281	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   20: checkcast 145	java/lang/String
    //   23: astore 4
    //   25: aload_1
    //   26: ldc_w 283
    //   29: invokevirtual 281	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   32: checkcast 145	java/lang/String
    //   35: astore 5
    //   37: aload_1
    //   38: ldc_w 285
    //   41: invokevirtual 281	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   44: checkcast 145	java/lang/String
    //   47: astore 6
    //   49: aload_1
    //   50: ldc_w 287
    //   53: invokevirtual 281	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   56: checkcast 145	java/lang/String
    //   59: astore 7
    //   61: aload 7
    //   63: ifnonnull +322 -> 385
    //   66: ldc 209
    //   68: astore 7
    //   70: goto +315 -> 385
    //   73: ldc_w 289
    //   76: invokestatic 293	com/millennialmedia/android/MMJSResponse:responseWithError	(Ljava/lang/String;)Lcom/millennialmedia/android/MMJSResponse;
    //   79: astore 8
    //   81: aload 8
    //   83: astore 9
    //   85: aload_0
    //   86: monitorexit
    //   87: aload 9
    //   89: areturn
    //   90: aload 5
    //   92: invokestatic 299	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   95: f2i
    //   96: istore 10
    //   98: aload 6
    //   100: invokestatic 299	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   103: f2i
    //   104: istore 11
    //   106: iload 10
    //   108: iload 11
    //   110: imul
    //   111: ldc_w 300
    //   114: if_icmple +14 -> 128
    //   117: ldc_w 302
    //   120: invokestatic 293	com/millennialmedia/android/MMJSResponse:responseWithError	(Ljava/lang/String;)Lcom/millennialmedia/android/MMJSResponse;
    //   123: astore 9
    //   125: goto -40 -> 85
    //   128: aload_3
    //   129: ifnull +250 -> 379
    //   132: aload 4
    //   134: ifnull +245 -> 379
    //   137: new 115	java/io/File
    //   140: dup
    //   141: aload_3
    //   142: invokestatic 308	com/millennialmedia/android/AdCache:getCacheDirectory	(Landroid/content/Context;)Ljava/io/File;
    //   145: new 310	java/lang/StringBuilder
    //   148: dup
    //   149: invokespecial 311	java/lang/StringBuilder:<init>	()V
    //   152: ldc_w 313
    //   155: invokevirtual 317	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: invokestatic 322	java/lang/System:currentTimeMillis	()J
    //   161: invokestatic 326	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   164: invokevirtual 317	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: ldc_w 328
    //   170: invokevirtual 317	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: invokevirtual 331	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   176: invokespecial 334	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   179: astore 12
    //   181: aload 4
    //   183: ldc_w 256
    //   186: invokevirtual 338	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   189: ifeq +10 -> 199
    //   192: aload_0
    //   193: invokespecial 254	com/millennialmedia/android/BridgeMMMedia:isCameraAvailable	()Z
    //   196: ifne +36 -> 232
    //   199: aload 4
    //   201: ldc_w 264
    //   204: invokevirtual 338	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   207: ifne +14 -> 221
    //   210: aload 4
    //   212: ldc_w 340
    //   215: invokevirtual 338	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   218: ifeq +161 -> 379
    //   221: aload_0
    //   222: invokespecial 262	com/millennialmedia/android/BridgeMMMedia:isPictureChooserAvailable	()Z
    //   225: istore 13
    //   227: iload 13
    //   229: ifeq +150 -> 379
    //   232: new 342	java/lang/Object
    //   235: dup
    //   236: invokespecial 343	java/lang/Object:<init>	()V
    //   239: putstatic 23	com/millennialmedia/android/BridgeMMMedia:pickerActivityObject	Ljava/lang/Object;
    //   242: getstatic 23	com/millennialmedia/android/BridgeMMMedia:pickerActivityObject	Ljava/lang/Object;
    //   245: astore 18
    //   247: aload 18
    //   249: monitorenter
    //   250: aload_3
    //   251: aload 12
    //   253: aload 4
    //   255: invokestatic 349	com/millennialmedia/android/Utils$IntentUtils:startPickerActivity	(Landroid/content/Context;Ljava/io/File;Ljava/lang/String;)V
    //   258: getstatic 23	com/millennialmedia/android/BridgeMMMedia:pickerActivityObject	Ljava/lang/Object;
    //   261: invokevirtual 352	java/lang/Object:wait	()V
    //   264: aload 18
    //   266: monitorexit
    //   267: aconst_null
    //   268: putstatic 23	com/millennialmedia/android/BridgeMMMedia:pickerActivityObject	Ljava/lang/Object;
    //   271: aload 12
    //   273: ifnull +106 -> 379
    //   276: aload 12
    //   278: invokevirtual 355	java/io/File:exists	()Z
    //   281: ifeq +98 -> 379
    //   284: aload 12
    //   286: invokevirtual 358	java/io/File:length	()J
    //   289: lconst_0
    //   290: lcmp
    //   291: ifle +88 -> 379
    //   294: aload 12
    //   296: iload 11
    //   298: iload 10
    //   300: aload 7
    //   302: invokestatic 360	com/millennialmedia/android/BridgeMMMedia:scaleBitmapToBytes	(Ljava/io/File;IILjava/lang/String;)[B
    //   305: astore 16
    //   307: aload 12
    //   309: invokevirtual 363	java/io/File:delete	()Z
    //   312: pop
    //   313: aload 16
    //   315: ifnull +64 -> 379
    //   318: new 266	com/millennialmedia/android/MMJSResponse
    //   321: dup
    //   322: invokespecial 267	com/millennialmedia/android/MMJSResponse:<init>	()V
    //   325: astore 9
    //   327: aload 9
    //   329: iconst_1
    //   330: putfield 270	com/millennialmedia/android/MMJSResponse:result	I
    //   333: aload 9
    //   335: aload 16
    //   337: putfield 367	com/millennialmedia/android/MMJSResponse:dataResponse	[B
    //   340: goto -255 -> 85
    //   343: astore_2
    //   344: aload_0
    //   345: monitorexit
    //   346: aload_2
    //   347: athrow
    //   348: astore 19
    //   350: aload 18
    //   352: monitorexit
    //   353: aload 19
    //   355: athrow
    //   356: astore 15
    //   358: aload 15
    //   360: invokevirtual 134	java/lang/Exception:printStackTrace	()V
    //   363: aconst_null
    //   364: putstatic 23	com/millennialmedia/android/BridgeMMMedia:pickerActivityObject	Ljava/lang/Object;
    //   367: goto -96 -> 271
    //   370: astore 14
    //   372: aconst_null
    //   373: putstatic 23	com/millennialmedia/android/BridgeMMMedia:pickerActivityObject	Ljava/lang/Object;
    //   376: aload 14
    //   378: athrow
    //   379: aconst_null
    //   380: astore 9
    //   382: goto -297 -> 85
    //   385: aload 5
    //   387: ifnull -314 -> 73
    //   390: aload 6
    //   392: ifnonnull -302 -> 90
    //   395: goto -322 -> 73
    //
    // Exception table:
    //   from	to	target	type
    //   2	61	343	finally
    //   73	81	343	finally
    //   90	106	343	finally
    //   117	125	343	finally
    //   137	199	343	finally
    //   199	221	343	finally
    //   221	227	343	finally
    //   267	271	343	finally
    //   276	313	343	finally
    //   318	340	343	finally
    //   363	367	343	finally
    //   372	379	343	finally
    //   250	267	348	finally
    //   350	353	348	finally
    //   232	250	356	java/lang/Exception
    //   353	356	356	java/lang/Exception
    //   232	250	370	finally
    //   353	356	370	finally
    //   358	363	370	finally
  }

  public MMJSResponse isSourceTypeAvailable(HashMap<String, String> paramHashMap)
  {
    String str = (String)paramHashMap.get("sourceType");
    if (str != null)
    {
      if ((str.equalsIgnoreCase("Camera")) && (isCameraAvailable()))
        return MMJSResponse.responseWithSuccess("true");
      if ((str.equalsIgnoreCase("Photo Library")) && (isPictureChooserAvailable()))
        return MMJSResponse.responseWithSuccess("true");
    }
    return MMJSResponse.responseWithError("false");
  }

  public MMJSResponse playAudio(HashMap<String, String> paramHashMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str = (String)paramHashMap.get("path");
    if ((localContext != null) && (str != null))
    {
      if (Audio.sharedAudio(localContext).isPlaying())
        return MMJSResponse.responseWithError("Audio already playing.");
      if (str.startsWith("http"))
        return Audio.sharedAudio(localContext).playAudio(Uri.parse(str), Boolean.parseBoolean((String)paramHashMap.get("repeat")));
      File localFile = AdCache.getDownloadFile(localContext, str);
      if (localFile.exists())
        return Audio.sharedAudio(localContext).playAudio(Uri.fromFile(localFile), Boolean.parseBoolean((String)paramHashMap.get("repeat")));
    }
    return null;
  }

  public MMJSResponse playSound(HashMap<String, String> paramHashMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str = (String)paramHashMap.get("path");
    if ((localContext != null) && (str != null))
    {
      File localFile = AdCache.getDownloadFile(localContext, str);
      if (localFile.exists())
        return Audio.sharedAudio((Context)this.contextRef.get()).playSound(localFile);
    }
    return null;
  }

  public MMJSResponse playVideo(HashMap<String, String> paramHashMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str = (String)paramHashMap.get("path");
    if ((localContext != null) && (str != null))
    {
      if (str.startsWith("http"))
      {
        Utils.IntentUtils.startVideoPlayerActivityWithData(localContext, str);
        return MMJSResponse.responseWithSuccess(str);
      }
      File localFile = AdCache.getDownloadFile(localContext, str);
      if (localFile.exists())
      {
        Utils.IntentUtils.startVideoPlayerActivityWithData(localContext, localFile);
        return MMJSResponse.responseWithSuccess(localFile.getName());
      }
    }
    return null;
  }

  public MMJSResponse stopAudio(HashMap<String, String> paramHashMap)
  {
    return Audio.sharedAudio((Context)this.contextRef.get()).stop();
  }

  public MMJSResponse writeToPhotoLibrary(HashMap<String, String> paramHashMap)
  {
    try
    {
      Context localContext = (Context)this.contextRef.get();
      Uri localUri = Uri.parse((String)paramHashMap.get("path"));
      File localFile1 = new File(AdCache.getCacheDirectory(localContext).getAbsolutePath() + File.separator + "Pictures" + File.separator + localUri.getLastPathSegment());
      String str1 = localUri.getScheme();
      MMJSResponse localMMJSResponse2;
      if ((str1 != null) && (str1.equals("http")))
      {
        paramHashMap.put("url", localUri.toString());
        paramHashMap.put("path", localUri.getLastPathSegment());
        BridgeMMFileManager localBridgeMMFileManager = new BridgeMMFileManager();
        localBridgeMMFileManager.setContext(localContext);
        if (localBridgeMMFileManager.downloadFile(paramHashMap) == null)
          localMMJSResponse2 = MMJSResponse.responseWithError("Failed to download");
      }
      File localFile2;
      for (MMJSResponse localMMJSResponse1 = localMMJSResponse2; ; localMMJSResponse1 = MMJSResponse.responseWithError("No file at " + localFile2.getAbsolutePath()))
      {
        return localMMJSResponse1;
        localFile2 = AdCache.getDownloadFile(localContext, localUri.getLastPathSegment());
        if (localFile2.exists())
          break;
      }
      if (moveFile(localFile2, localFile1));
      String str2;
      for (Object localObject2 = localFile1.getAbsolutePath(); ; localObject2 = str2)
      {
        scanMedia((String)localObject2);
        localMMJSResponse1 = MMJSResponse.responseWithSuccess("Image saved to photo library");
        break;
        str2 = localFile2.getAbsolutePath();
      }
    }
    finally
    {
    }
  }

  static class Audio
  {
    private static final int MAX_SOUNDS = 4;
    private static Audio sharedInstance;
    private OnLoadCompleteListener completionListener;
    private WeakReference<Context> contextRef;
    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;

    private Audio()
    {
    }

    private Audio(Context paramContext)
    {
      this.contextRef = new WeakReference(paramContext.getApplicationContext());
    }

    static Audio sharedAudio(Context paramContext)
    {
      try
      {
        if (sharedInstance == null)
          sharedInstance = new Audio(paramContext);
        Audio localAudio = sharedInstance;
        return localAudio;
      }
      finally
      {
      }
    }

    boolean isPlaying()
    {
      try
      {
        if (this.mediaPlayer != null)
        {
          boolean bool2 = this.mediaPlayer.isPlaying();
          if (bool2)
          {
            bool1 = true;
            return bool1;
          }
        }
        boolean bool1 = false;
      }
      finally
      {
      }
    }

    MMJSResponse playAudio(Uri paramUri, boolean paramBoolean)
    {
      try
      {
        if (this.mediaPlayer != null)
        {
          this.mediaPlayer.release();
          this.mediaPlayer = null;
        }
        this.mediaPlayer = MediaPlayer.create((Context)this.contextRef.get(), paramUri);
        this.mediaPlayer.setLooping(paramBoolean);
        this.mediaPlayer.start();
        this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
          public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
          {
            try
            {
              if (BridgeMMMedia.Audio.this.mediaPlayer != null)
              {
                BridgeMMMedia.Audio.this.mediaPlayer.release();
                BridgeMMMedia.Audio.access$102(BridgeMMMedia.Audio.this, null);
              }
              return;
            }
            finally
            {
              localObject = finally;
              throw localObject;
            }
          }
        });
        MMJSResponse localMMJSResponse = MMJSResponse.responseWithSuccess("Audio playback started");
        return localMMJSResponse;
      }
      catch (Exception localException)
      {
        while (true)
          MMSDK.Log.e(localException.getCause());
      }
      finally
      {
      }
    }

    // ERROR //
    MMJSResponse playSound(File paramFile)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 109	com/millennialmedia/android/BridgeMMMedia$Audio:soundPool	Landroid/media/SoundPool;
      //   6: ifnonnull +33 -> 39
      //   9: aload_0
      //   10: new 111	android/media/SoundPool
      //   13: dup
      //   14: iconst_4
      //   15: iconst_3
      //   16: iconst_0
      //   17: invokespecial 114	android/media/SoundPool:<init>	(III)V
      //   20: putfield 109	com/millennialmedia/android/BridgeMMMedia$Audio:soundPool	Landroid/media/SoundPool;
      //   23: aload_0
      //   24: new 116	com/millennialmedia/android/BridgeMMMedia$Audio$2
      //   27: dup
      //   28: aload_0
      //   29: aload_0
      //   30: getfield 109	com/millennialmedia/android/BridgeMMMedia$Audio:soundPool	Landroid/media/SoundPool;
      //   33: invokespecial 119	com/millennialmedia/android/BridgeMMMedia$Audio$2:<init>	(Lcom/millennialmedia/android/BridgeMMMedia$Audio;Landroid/media/SoundPool;)V
      //   36: putfield 121	com/millennialmedia/android/BridgeMMMedia$Audio:completionListener	Lcom/millennialmedia/android/BridgeMMMedia$Audio$OnLoadCompleteListener;
      //   39: aload_0
      //   40: getfield 121	com/millennialmedia/android/BridgeMMMedia$Audio:completionListener	Lcom/millennialmedia/android/BridgeMMMedia$Audio$OnLoadCompleteListener;
      //   43: aload_0
      //   44: getfield 109	com/millennialmedia/android/BridgeMMMedia$Audio:soundPool	Landroid/media/SoundPool;
      //   47: aload_1
      //   48: invokevirtual 127	java/io/File:getAbsolutePath	()Ljava/lang/String;
      //   51: iconst_1
      //   52: invokevirtual 131	android/media/SoundPool:load	(Ljava/lang/String;I)I
      //   55: invokevirtual 137	com/millennialmedia/android/BridgeMMMedia$Audio$OnLoadCompleteListener:testSample	(I)V
      //   58: ldc 139
      //   60: invokestatic 95	com/millennialmedia/android/MMJSResponse:responseWithSuccess	(Ljava/lang/String;)Lcom/millennialmedia/android/MMJSResponse;
      //   63: astore 4
      //   65: aload_0
      //   66: monitorexit
      //   67: aload 4
      //   69: areturn
      //   70: astore_3
      //   71: aload_0
      //   72: monitorexit
      //   73: aload_3
      //   74: athrow
      //   75: astore_2
      //   76: goto -18 -> 58
      //
      // Exception table:
      //   from	to	target	type
      //   2	39	70	finally
      //   39	58	70	finally
      //   58	65	70	finally
      //   2	39	75	java/lang/Exception
      //   39	58	75	java/lang/Exception
    }

    MMJSResponse stop()
    {
      try
      {
        if (this.mediaPlayer != null)
        {
          this.mediaPlayer.release();
          this.mediaPlayer = null;
        }
        if (this.soundPool != null)
        {
          this.soundPool.release();
          this.soundPool = null;
        }
        if (this.completionListener != null)
        {
          this.completionListener.release();
          this.completionListener = null;
        }
        MMJSResponse localMMJSResponse = MMJSResponse.responseWithSuccess("Audio stopped");
        return localMMJSResponse;
      }
      finally
      {
      }
    }

    private abstract class OnLoadCompleteListener
    {
      private static final int TEST_PERIOD_MS = 100;
      private ArrayList<Integer> sampleIds = new ArrayList();
      private SoundPool soundPool;
      private Timer timer;

      public OnLoadCompleteListener(SoundPool arg2)
      {
        Object localObject;
        this.soundPool = localObject;
      }

      abstract void onLoadComplete(SoundPool paramSoundPool, int paramInt1, int paramInt2);

      void release()
      {
        try
        {
          if (this.timer != null)
          {
            this.timer.cancel();
            this.timer.purge();
          }
          return;
        }
        finally
        {
          localObject = finally;
          throw localObject;
        }
      }

      void testSample(int paramInt)
      {
        try
        {
          this.sampleIds.add(Integer.valueOf(paramInt));
          if (this.sampleIds.size() == 1)
          {
            this.timer = new Timer();
            this.timer.scheduleAtFixedRate(new TimerTask()
            {
              public void run()
              {
                ArrayList localArrayList = new ArrayList();
                Iterator localIterator = BridgeMMMedia.Audio.OnLoadCompleteListener.this.sampleIds.iterator();
                while (localIterator.hasNext())
                {
                  Integer localInteger = (Integer)localIterator.next();
                  int i = BridgeMMMedia.Audio.OnLoadCompleteListener.this.soundPool.play(localInteger.intValue(), 0.0F, 0.0F, 0, 0, 1.0F);
                  if (i != 0)
                  {
                    BridgeMMMedia.Audio.OnLoadCompleteListener.this.soundPool.stop(i);
                    BridgeMMMedia.Audio.OnLoadCompleteListener.this.onLoadComplete(BridgeMMMedia.Audio.OnLoadCompleteListener.this.soundPool, localInteger.intValue(), 0);
                    localArrayList.add(localInteger);
                  }
                }
                BridgeMMMedia.Audio.OnLoadCompleteListener.this.sampleIds.removeAll(localArrayList);
                if (BridgeMMMedia.Audio.OnLoadCompleteListener.this.sampleIds.size() == 0)
                {
                  BridgeMMMedia.Audio.OnLoadCompleteListener.this.timer.cancel();
                  BridgeMMMedia.Audio.OnLoadCompleteListener.this.timer.purge();
                }
              }
            }
            , 0L, 100L);
          }
          return;
        }
        finally
        {
          localObject = finally;
          throw localObject;
        }
      }
    }
  }

  static class PickerActivity extends MMBaseActivity
  {
    private Uri fileUri;
    boolean hasRequestedPic = false;

    // ERROR //
    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
      // Byte code:
      //   0: aload_0
      //   1: iload_1
      //   2: iload_2
      //   3: aload_3
      //   4: invokespecial 20	com/millennialmedia/android/MMBaseActivity:onActivityResult	(IILandroid/content/Intent;)V
      //   7: aload_3
      //   8: ifnull +164 -> 172
      //   11: aconst_null
      //   12: astore 6
      //   14: aconst_null
      //   15: astore 7
      //   17: aload_3
      //   18: invokevirtual 26	android/content/Intent:getData	()Landroid/net/Uri;
      //   21: astore 9
      //   23: aload 9
      //   25: ifnonnull +249 -> 274
      //   28: aload_3
      //   29: invokevirtual 30	android/content/Intent:getExtras	()Landroid/os/Bundle;
      //   32: ifnull +140 -> 172
      //   35: aload_3
      //   36: invokevirtual 30	android/content/Intent:getExtras	()Landroid/os/Bundle;
      //   39: ldc 32
      //   41: invokevirtual 38	android/os/Bundle:get	(Ljava/lang/String;)Ljava/lang/Object;
      //   44: checkcast 40	android/graphics/Bitmap
      //   47: astore 10
      //   49: new 42	java/io/File
      //   52: dup
      //   53: aload_0
      //   54: invokevirtual 46	com/millennialmedia/android/BridgeMMMedia$PickerActivity:getIntent	()Landroid/content/Intent;
      //   57: invokevirtual 26	android/content/Intent:getData	()Landroid/net/Uri;
      //   60: invokevirtual 52	android/net/Uri:getPath	()Ljava/lang/String;
      //   63: invokespecial 55	java/io/File:<init>	(Ljava/lang/String;)V
      //   66: astore 11
      //   68: new 57	java/io/ByteArrayOutputStream
      //   71: dup
      //   72: invokespecial 58	java/io/ByteArrayOutputStream:<init>	()V
      //   75: astore 12
      //   77: aload 10
      //   79: getstatic 64	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
      //   82: iconst_0
      //   83: aload 12
      //   85: invokevirtual 68	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
      //   88: pop
      //   89: new 70	java/io/ByteArrayInputStream
      //   92: dup
      //   93: aload 12
      //   95: invokevirtual 74	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   98: invokespecial 77	java/io/ByteArrayInputStream:<init>	([B)V
      //   101: astore 19
      //   103: new 79	java/io/FileOutputStream
      //   106: dup
      //   107: aload 11
      //   109: invokespecial 82	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   112: astore 20
      //   114: sipush 1024
      //   117: newarray byte
      //   119: astore 22
      //   121: aload 19
      //   123: aload 22
      //   125: invokevirtual 86	java/io/ByteArrayInputStream:read	([B)I
      //   128: istore 23
      //   130: iload 23
      //   132: ifle +62 -> 194
      //   135: aload 20
      //   137: aload 22
      //   139: iconst_0
      //   140: iload 23
      //   142: invokevirtual 92	java/io/OutputStream:write	([BII)V
      //   145: goto -24 -> 121
      //   148: astore 21
      //   150: aload 20
      //   152: astore 14
      //   154: iconst_0
      //   155: ifeq +7 -> 162
      //   158: aconst_null
      //   159: invokevirtual 97	java/io/InputStream:close	()V
      //   162: aload 14
      //   164: ifnull +8 -> 172
      //   167: aload 14
      //   169: invokevirtual 98	java/io/OutputStream:close	()V
      //   172: invokestatic 104	com/millennialmedia/android/BridgeMMMedia:access$000	()Ljava/lang/Object;
      //   175: astore 4
      //   177: aload 4
      //   179: monitorenter
      //   180: invokestatic 104	com/millennialmedia/android/BridgeMMMedia:access$000	()Ljava/lang/Object;
      //   183: invokevirtual 109	java/lang/Object:notify	()V
      //   186: aload 4
      //   188: monitorexit
      //   189: aload_0
      //   190: invokevirtual 112	com/millennialmedia/android/BridgeMMMedia$PickerActivity:finish	()V
      //   193: return
      //   194: iconst_0
      //   195: ifeq +7 -> 202
      //   198: aconst_null
      //   199: invokevirtual 97	java/io/InputStream:close	()V
      //   202: aload 20
      //   204: ifnull +8 -> 212
      //   207: aload 20
      //   209: invokevirtual 98	java/io/OutputStream:close	()V
      //   212: goto -40 -> 172
      //   215: astore 24
      //   217: goto -45 -> 172
      //   220: astore 16
      //   222: iconst_0
      //   223: ifeq +7 -> 230
      //   226: aconst_null
      //   227: invokevirtual 97	java/io/InputStream:close	()V
      //   230: aload 7
      //   232: ifnull +8 -> 240
      //   235: aload 7
      //   237: invokevirtual 98	java/io/OutputStream:close	()V
      //   240: aload 16
      //   242: athrow
      //   243: astore 8
      //   245: new 114	java/lang/StringBuilder
      //   248: dup
      //   249: invokespecial 115	java/lang/StringBuilder:<init>	()V
      //   252: ldc 117
      //   254: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   257: aload 8
      //   259: invokevirtual 124	java/lang/Exception:getMessage	()Ljava/lang/String;
      //   262: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   265: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   268: invokestatic 132	com/millennialmedia/android/MMSDK$Log:e	(Ljava/lang/String;)V
      //   271: goto -99 -> 172
      //   274: aload 9
      //   276: ifnull -104 -> 172
      //   279: iconst_1
      //   280: anewarray 134	java/lang/String
      //   283: dup
      //   284: iconst_0
      //   285: ldc 136
      //   287: aastore
      //   288: astore 25
      //   290: aload_0
      //   291: invokevirtual 140	com/millennialmedia/android/BridgeMMMedia$PickerActivity:getContentResolver	()Landroid/content/ContentResolver;
      //   294: astore 26
      //   296: aload 26
      //   298: ifnull -126 -> 172
      //   301: aload 26
      //   303: aload 9
      //   305: aload 25
      //   307: aconst_null
      //   308: aconst_null
      //   309: aconst_null
      //   310: invokevirtual 146	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
      //   313: astore 27
      //   315: aload 27
      //   317: ifnull -145 -> 172
      //   320: aload 27
      //   322: ldc 136
      //   324: invokeinterface 152 2 0
      //   329: istore 28
      //   331: iload 28
      //   333: iconst_m1
      //   334: if_icmpeq -162 -> 172
      //   337: aload 27
      //   339: invokeinterface 156 1 0
      //   344: pop
      //   345: new 42	java/io/File
      //   348: dup
      //   349: aload 27
      //   351: iload 28
      //   353: invokeinterface 160 2 0
      //   358: invokespecial 55	java/io/File:<init>	(Ljava/lang/String;)V
      //   361: astore 30
      //   363: aload 27
      //   365: invokeinterface 161 1 0
      //   370: new 42	java/io/File
      //   373: dup
      //   374: aload_0
      //   375: invokevirtual 46	com/millennialmedia/android/BridgeMMMedia$PickerActivity:getIntent	()Landroid/content/Intent;
      //   378: invokevirtual 26	android/content/Intent:getData	()Landroid/net/Uri;
      //   381: invokevirtual 52	android/net/Uri:getPath	()Ljava/lang/String;
      //   384: invokespecial 55	java/io/File:<init>	(Ljava/lang/String;)V
      //   387: astore 31
      //   389: new 163	java/io/FileInputStream
      //   392: dup
      //   393: aload 30
      //   395: invokespecial 164	java/io/FileInputStream:<init>	(Ljava/io/File;)V
      //   398: astore 32
      //   400: new 79	java/io/FileOutputStream
      //   403: dup
      //   404: aload 31
      //   406: invokespecial 82	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   409: astore 33
      //   411: sipush 1024
      //   414: newarray byte
      //   416: astore 40
      //   418: aload 32
      //   420: aload 40
      //   422: invokevirtual 165	java/io/InputStream:read	([B)I
      //   425: istore 41
      //   427: iload 41
      //   429: ifle +54 -> 483
      //   432: aload 33
      //   434: aload 40
      //   436: iconst_0
      //   437: iload 41
      //   439: invokevirtual 92	java/io/OutputStream:write	([BII)V
      //   442: goto -24 -> 418
      //   445: astore 36
      //   447: aload 33
      //   449: astore 37
      //   451: aload 32
      //   453: astore 38
      //   455: aload 38
      //   457: ifnull +8 -> 465
      //   460: aload 38
      //   462: invokevirtual 97	java/io/InputStream:close	()V
      //   465: aload 37
      //   467: ifnull -295 -> 172
      //   470: aload 37
      //   472: invokevirtual 98	java/io/OutputStream:close	()V
      //   475: goto -303 -> 172
      //   478: astore 39
      //   480: goto -308 -> 172
      //   483: aload 32
      //   485: ifnull +8 -> 493
      //   488: aload 32
      //   490: invokevirtual 97	java/io/InputStream:close	()V
      //   493: aload 33
      //   495: ifnull +8 -> 503
      //   498: aload 33
      //   500: invokevirtual 98	java/io/OutputStream:close	()V
      //   503: goto -331 -> 172
      //   506: astore 42
      //   508: goto -336 -> 172
      //   511: astore 34
      //   513: aload 6
      //   515: ifnull +8 -> 523
      //   518: aload 6
      //   520: invokevirtual 97	java/io/InputStream:close	()V
      //   523: aload 7
      //   525: ifnull +8 -> 533
      //   528: aload 7
      //   530: invokevirtual 98	java/io/OutputStream:close	()V
      //   533: aload 34
      //   535: athrow
      //   536: astore 5
      //   538: aload 4
      //   540: monitorexit
      //   541: aload 5
      //   543: athrow
      //   544: astore 35
      //   546: goto -13 -> 533
      //   549: astore 34
      //   551: aload 32
      //   553: astore 6
      //   555: aconst_null
      //   556: astore 7
      //   558: goto -45 -> 513
      //   561: astore 34
      //   563: aload 33
      //   565: astore 7
      //   567: aload 32
      //   569: astore 6
      //   571: goto -58 -> 513
      //   574: astore 44
      //   576: aconst_null
      //   577: astore 38
      //   579: aconst_null
      //   580: astore 37
      //   582: goto -127 -> 455
      //   585: astore 43
      //   587: aload 32
      //   589: astore 38
      //   591: aconst_null
      //   592: astore 37
      //   594: goto -139 -> 455
      //   597: astore 17
      //   599: goto -359 -> 240
      //   602: astore 16
      //   604: aload 20
      //   606: astore 7
      //   608: goto -386 -> 222
      //   611: astore 15
      //   613: goto -441 -> 172
      //   616: astore 13
      //   618: aconst_null
      //   619: astore 14
      //   621: goto -467 -> 154
      //
      // Exception table:
      //   from	to	target	type
      //   114	121	148	java/lang/Exception
      //   121	130	148	java/lang/Exception
      //   135	145	148	java/lang/Exception
      //   198	202	215	java/lang/Exception
      //   207	212	215	java/lang/Exception
      //   49	114	220	finally
      //   17	23	243	java/lang/Exception
      //   28	49	243	java/lang/Exception
      //   240	243	243	java/lang/Exception
      //   279	296	243	java/lang/Exception
      //   301	315	243	java/lang/Exception
      //   320	331	243	java/lang/Exception
      //   337	370	243	java/lang/Exception
      //   533	536	243	java/lang/Exception
      //   411	418	445	java/lang/Exception
      //   418	427	445	java/lang/Exception
      //   432	442	445	java/lang/Exception
      //   460	465	478	java/lang/Exception
      //   470	475	478	java/lang/Exception
      //   488	493	506	java/lang/Exception
      //   498	503	506	java/lang/Exception
      //   370	400	511	finally
      //   180	189	536	finally
      //   538	541	536	finally
      //   518	523	544	java/lang/Exception
      //   528	533	544	java/lang/Exception
      //   400	411	549	finally
      //   411	418	561	finally
      //   418	427	561	finally
      //   432	442	561	finally
      //   370	400	574	java/lang/Exception
      //   400	411	585	java/lang/Exception
      //   226	230	597	java/lang/Exception
      //   235	240	597	java/lang/Exception
      //   114	121	602	finally
      //   121	130	602	finally
      //   135	145	602	finally
      //   158	162	611	java/lang/Exception
      //   167	172	611	java/lang/Exception
      //   49	114	616	java/lang/Exception
    }

    public void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      if (getLastNonConfigurationInstance() != null)
        this.hasRequestedPic = ((Bundle)getLastNonConfigurationInstance()).getBoolean("hasRequestedPic");
      if (!this.hasRequestedPic)
      {
        if (getIntent().getStringExtra("type").equalsIgnoreCase("Camera"))
        {
          Intent localIntent1 = new Intent("android.media.action.IMAGE_CAPTURE");
          this.fileUri = getIntent().getData();
          localIntent1.putExtra("return-data", true);
          this.hasRequestedPic = true;
          startActivityForResult(localIntent1, 0);
        }
      }
      else
        return;
      Intent localIntent2 = new Intent();
      localIntent2.setType("image/*");
      localIntent2.setAction("android.intent.action.GET_CONTENT");
      this.hasRequestedPic = true;
      startActivityForResult(localIntent2, 0);
    }

    public Object onRetainNonConfigurationInstance()
    {
      super.onRetainNonConfigurationInstance();
      Bundle localBundle = new Bundle();
      localBundle.putBoolean("hasRequestedPic", this.hasRequestedPic);
      return localBundle;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.BridgeMMMedia
 * JD-Core Version:    0.6.2
 */