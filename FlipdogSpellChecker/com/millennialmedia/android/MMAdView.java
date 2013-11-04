package com.millennialmedia.android;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Random;

public final class MMAdView extends MMLayout
  implements View.OnClickListener, Animation.AnimationListener
{
  static final int DEFAULT_RESIZE_PARAM_VALUES = -50;
  public static final int TRANSITION_DOWN = 3;
  public static final int TRANSITION_FADE = 1;
  public static final int TRANSITION_NONE = 0;
  public static final int TRANSITION_RANDOM = 4;
  public static final int TRANSITION_UP = 2;
  int height = 0;
  int oldHeight = -50;
  int oldWidth = -50;
  ImageView refreshAnimationimageView;
  int transitionType = 4;
  ResizeView view;
  int width = 0;

  public MMAdView(Context paramContext)
  {
    super(paramContext);
    this.adImpl = new MMAdViewMMAdImpl(paramContext);
    init(paramContext);
  }

  @Deprecated
  public MMAdView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  @Deprecated
  public MMAdView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    String str1;
    String str2;
    if (!isInEditMode())
    {
      MMSDK.Log.d("Creating MMAdView from XML layout.");
      this.adImpl = new MMAdViewMMAdImpl(paramContext);
      if (paramAttributeSet != null)
      {
        setApid(paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "apid"));
        this.adImpl.ignoreDensityScaling = paramAttributeSet.getAttributeBooleanValue("http://millennialmedia.com/android/schema", "ignoreDensityScaling", false);
        str1 = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "height");
        str2 = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "width");
      }
    }
    try
    {
      if (!TextUtils.isEmpty(str1))
        this.height = Integer.parseInt(str1);
      if (!TextUtils.isEmpty(str2))
        this.width = Integer.parseInt(str2);
      label153: if (this.adImpl.mmRequest != null)
      {
        this.adImpl.mmRequest.age = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "age");
        this.adImpl.mmRequest.children = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "children");
        this.adImpl.mmRequest.education = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "education");
        this.adImpl.mmRequest.ethnicity = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "ethnicity");
        this.adImpl.mmRequest.gender = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "gender");
        this.adImpl.mmRequest.income = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "income");
        this.adImpl.mmRequest.keywords = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "keywords");
        this.adImpl.mmRequest.orientation = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "orientation");
        this.adImpl.mmRequest.marital = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "marital");
        this.adImpl.mmRequest.politics = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "politics");
        this.adImpl.mmRequest.vendor = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "vendor");
        this.adImpl.mmRequest.zip = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "zip");
      }
      this.goalId = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "goalId");
      this.adImpl.xmlLayout = true;
      init(paramContext);
      return;
      initEclipseAd(paramContext);
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      break label153;
    }
  }

  private void attachToWindow(View paramView)
  {
    try
    {
      detachFromParent(paramView);
      Context localContext = getContext();
      if ((localContext != null) && ((localContext instanceof Activity)))
      {
        Window localWindow = ((Activity)localContext).getWindow();
        if (localWindow != null)
        {
          View localView = localWindow.getDecorView();
          if ((localView != null) && ((localView instanceof ViewGroup)))
            ((ViewGroup)localView).addView(paramView);
        }
      }
      return;
    }
    finally
    {
    }
  }

  private void callSetTranslationX(int paramInt)
  {
    try
    {
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Float.TYPE;
      Method localMethod = View.class.getMethod("setTranslationX", arrayOfClass);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      localMethod.invoke(this, arrayOfObject);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void callSetTranslationY(int paramInt)
  {
    try
    {
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Float.TYPE;
      Method localMethod = View.class.getMethod("setTranslationY", arrayOfClass);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      localMethod.invoke(this, arrayOfObject);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void detachFromParent(View paramView)
  {
    if (paramView != null);
    try
    {
      ViewParent localViewParent = getParent();
      if ((localViewParent != null) && ((localViewParent instanceof ViewGroup)))
      {
        ViewGroup localViewGroup = (ViewGroup)localViewParent;
        if (paramView.getParent() != null)
          localViewGroup.removeView(paramView);
      }
      return;
    }
    finally
    {
    }
  }

  private void getAdInternal()
  {
    if (this.adImpl != null)
      this.adImpl.requestAd();
  }

  private boolean hasDefaultResizeParams()
  {
    return (this.oldWidth == -50) && (this.oldHeight == -50);
  }

  private void init(Context paramContext)
  {
    setBackgroundColor(0);
    this.adImpl.adType = "b";
    setOnClickListener(this);
    setFocusable(true);
    this.refreshAnimationimageView = new ImageView(paramContext);
    this.refreshAnimationimageView.setScaleType(ImageView.ScaleType.FIT_XY);
    this.refreshAnimationimageView.setVisibility(8);
    addView(this.refreshAnimationimageView, new RelativeLayout.LayoutParams(-2, -2));
    setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
  }

  // ERROR //
  private void initEclipseAd(Context paramContext)
  {
    // Byte code:
    //   0: new 277	android/widget/ImageView
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 278	android/widget/ImageView:<init>	(Landroid/content/Context;)V
    //   8: astore_2
    //   9: aconst_null
    //   10: astore_3
    //   11: aconst_null
    //   12: astore 4
    //   14: ldc_w 307
    //   17: invokestatic 313	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   20: astore 9
    //   22: aconst_null
    //   23: astore_3
    //   24: aconst_null
    //   25: astore 4
    //   27: aload 9
    //   29: ifnull +46 -> 75
    //   32: aload 9
    //   34: getstatic 318	java/io/File:separator	Ljava/lang/String;
    //   37: invokevirtual 324	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   40: istore 10
    //   42: aconst_null
    //   43: astore_3
    //   44: aconst_null
    //   45: astore 4
    //   47: iload 10
    //   49: ifne +26 -> 75
    //   52: new 326	java/lang/StringBuilder
    //   55: dup
    //   56: invokespecial 328	java/lang/StringBuilder:<init>	()V
    //   59: aload 9
    //   61: invokevirtual 332	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: getstatic 318	java/io/File:separator	Ljava/lang/String;
    //   67: invokevirtual 332	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: invokevirtual 336	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   73: astore 9
    //   75: new 315	java/io/File
    //   78: dup
    //   79: new 326	java/lang/StringBuilder
    //   82: dup
    //   83: invokespecial 328	java/lang/StringBuilder:<init>	()V
    //   86: aload 9
    //   88: invokevirtual 332	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: ldc_w 338
    //   94: invokevirtual 332	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: invokevirtual 336	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   100: invokespecial 340	java/io/File:<init>	(Ljava/lang/String;)V
    //   103: astore 11
    //   105: aload 11
    //   107: invokevirtual 343	java/io/File:exists	()Z
    //   110: istore 12
    //   112: aconst_null
    //   113: astore_3
    //   114: aconst_null
    //   115: astore 4
    //   117: iload 12
    //   119: ifne +124 -> 243
    //   122: new 345	java/net/URL
    //   125: dup
    //   126: ldc_w 347
    //   129: invokespecial 348	java/net/URL:<init>	(Ljava/lang/String;)V
    //   132: invokevirtual 352	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   135: checkcast 354	java/net/HttpURLConnection
    //   138: astore 13
    //   140: aload 13
    //   142: iconst_1
    //   143: invokevirtual 357	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   146: aload 13
    //   148: sipush 3000
    //   151: invokevirtual 360	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   154: aload 13
    //   156: invokevirtual 363	java/net/HttpURLConnection:connect	()V
    //   159: aload 13
    //   161: invokevirtual 367	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   164: astore_3
    //   165: new 369	java/io/FileOutputStream
    //   168: dup
    //   169: aload 11
    //   171: invokespecial 372	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   174: astore 14
    //   176: sipush 1024
    //   179: newarray byte
    //   181: astore 16
    //   183: aload_3
    //   184: aload 16
    //   186: invokevirtual 378	java/io/InputStream:read	([B)I
    //   189: istore 17
    //   191: iload 17
    //   193: ifle +46 -> 239
    //   196: aload 14
    //   198: aload 16
    //   200: iconst_0
    //   201: iload 17
    //   203: invokevirtual 384	java/io/OutputStream:write	([BII)V
    //   206: goto -23 -> 183
    //   209: astore 15
    //   211: aload 14
    //   213: astore 4
    //   215: aload_3
    //   216: ifnull +7 -> 223
    //   219: aload_3
    //   220: invokevirtual 387	java/io/InputStream:close	()V
    //   223: aload 4
    //   225: ifnull +8 -> 233
    //   228: aload 4
    //   230: invokevirtual 388	java/io/OutputStream:close	()V
    //   233: aload_0
    //   234: aload_2
    //   235: invokevirtual 389	com/millennialmedia/android/MMAdView:addView	(Landroid/view/View;)V
    //   238: return
    //   239: aload 14
    //   241: astore 4
    //   243: aload 11
    //   245: invokevirtual 392	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   248: invokestatic 398	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   251: astore 18
    //   253: aload_2
    //   254: ifnull +14 -> 268
    //   257: aload 18
    //   259: ifnull +9 -> 268
    //   262: aload_2
    //   263: aload 18
    //   265: invokevirtual 402	android/widget/ImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   268: aload_3
    //   269: ifnull +7 -> 276
    //   272: aload_3
    //   273: invokevirtual 387	java/io/InputStream:close	()V
    //   276: aload 4
    //   278: ifnull -45 -> 233
    //   281: aload 4
    //   283: invokevirtual 388	java/io/OutputStream:close	()V
    //   286: goto -53 -> 233
    //   289: astore 19
    //   291: goto -58 -> 233
    //   294: astore 7
    //   296: aload_3
    //   297: ifnull +7 -> 304
    //   300: aload_3
    //   301: invokevirtual 387	java/io/InputStream:close	()V
    //   304: aload 4
    //   306: ifnull +8 -> 314
    //   309: aload 4
    //   311: invokevirtual 388	java/io/OutputStream:close	()V
    //   314: aload 7
    //   316: athrow
    //   317: astore 8
    //   319: goto -5 -> 314
    //   322: astore 7
    //   324: aload 14
    //   326: astore 4
    //   328: goto -32 -> 296
    //   331: astore 6
    //   333: goto -100 -> 233
    //   336: astore 5
    //   338: goto -123 -> 215
    //
    // Exception table:
    //   from	to	target	type
    //   176	183	209	java/lang/Exception
    //   183	191	209	java/lang/Exception
    //   196	206	209	java/lang/Exception
    //   272	276	289	java/lang/Exception
    //   281	286	289	java/lang/Exception
    //   14	22	294	finally
    //   32	42	294	finally
    //   52	75	294	finally
    //   75	112	294	finally
    //   122	176	294	finally
    //   243	253	294	finally
    //   262	268	294	finally
    //   300	304	317	java/lang/Exception
    //   309	314	317	java/lang/Exception
    //   176	183	322	finally
    //   183	191	322	finally
    //   196	206	322	finally
    //   219	223	331	java/lang/Exception
    //   228	233	331	java/lang/Exception
    //   14	22	336	java/lang/Exception
    //   32	42	336	java/lang/Exception
    //   52	75	336	java/lang/Exception
    //   75	112	336	java/lang/Exception
    //   122	176	336	java/lang/Exception
    //   243	253	336	java/lang/Exception
    //   262	268	336	java/lang/Exception
  }

  private void setUnresizeParameters()
  {
    if (hasDefaultResizeParams())
    {
      ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
      this.oldWidth = localLayoutParams.width;
      this.oldHeight = localLayoutParams.height;
      if (this.oldWidth <= 0)
        this.oldWidth = getWidth();
      if (this.oldHeight <= 0)
        this.oldHeight = getHeight();
    }
  }

  void closeAreaTouched()
  {
    this.adImpl.unresizeToDefault();
  }

  public void getAd()
  {
    if ((this.adImpl != null) && (this.adImpl.requestListener != null))
    {
      getAd(this.adImpl.requestListener);
      return;
    }
    getAdInternal();
  }

  public void getAd(RequestListener paramRequestListener)
  {
    if (this.adImpl != null)
      this.adImpl.requestListener = paramRequestListener;
    getAdInternal();
  }

  void handleMraidResize(DTOResizeParameters paramDTOResizeParameters)
  {
    try
    {
      this.refreshAnimationimageView.setImageBitmap(null);
      if (MMSDK.hasSetTranslationMethod())
      {
        if (this.view == null)
        {
          this.view = new ResizeView(getContext());
          this.view.setId(304025022);
          this.view.setLayoutParams(new RelativeLayout.LayoutParams(1, 1));
          this.view.setBackgroundColor(0);
        }
        if (this.view.getParent() == null)
        {
          ViewParent localViewParent = getParent();
          if ((localViewParent != null) && ((localViewParent instanceof ViewGroup)))
            ((ViewGroup)localViewParent).addView(this.view);
        }
        BannerBounds localBannerBounds = new BannerBounds(paramDTOResizeParameters);
        if (!paramDTOResizeParameters.allowOffScreen)
          localBannerBounds.calculateOnScreenBounds();
        int[] arrayOfInt1 = new int[2];
        getLocationInWindow(arrayOfInt1);
        attachToWindow(this);
        int[] arrayOfInt2 = new int[2];
        getLocationInWindow(arrayOfInt2);
        setUnresizeParameters();
        int i = arrayOfInt1[0] - arrayOfInt2[0];
        int j = arrayOfInt1[1] - arrayOfInt2[1];
        localBannerBounds.modifyLayoutParams(getLayoutParams());
        callSetTranslationX(i + localBannerBounds.translationX);
        callSetTranslationY(j + localBannerBounds.translationY);
        setCloseArea(paramDTOResizeParameters.customClosePosition);
      }
      return;
    }
    finally
    {
    }
  }

  void handleUnresize()
  {
    try
    {
      if (MMSDK.hasSetTranslationMethod())
      {
        removeCloseTouchDelegate();
        if (!hasDefaultResizeParams())
        {
          ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
          localLayoutParams.width = this.oldWidth;
          localLayoutParams.height = this.oldHeight;
          callSetTranslationX(0);
          callSetTranslationY(0);
          this.oldWidth = -50;
          this.oldHeight = -50;
        }
        if (this.view != null)
        {
          this.isResizing = true;
          this.view.attachToContext(this);
          ViewParent localViewParent = getParent();
          if ((localViewParent != null) && ((localViewParent instanceof ViewGroup)))
          {
            ViewGroup localViewGroup = (ViewGroup)localViewParent;
            if (this.view.getParent() != null)
              localViewGroup.removeView(this.view);
          }
          this.isResizing = false;
        }
        setBackgroundColor(-16776961);
      }
      return;
    }
    finally
    {
    }
  }

  @Deprecated
  public void onAnimationEnd(Animation paramAnimation)
  {
    this.refreshAnimationimageView.setVisibility(8);
  }

  @Deprecated
  public void onAnimationRepeat(Animation paramAnimation)
  {
  }

  @Deprecated
  public void onAnimationStart(Animation paramAnimation)
  {
  }

  @Deprecated
  public void onClick(View paramView)
  {
    MMSDK.Log.d("On click for " + paramView.getId() + " view, " + paramView + " adimpl" + this.adImpl);
    onTouchEvent(MotionEvent.obtain(0L, System.currentTimeMillis(), 1, 0.0F, 0.0F, 0));
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    Object[] arrayOfObject = new Object[5];
    arrayOfObject[0] = Boolean.valueOf(paramBoolean);
    arrayOfObject[1] = Integer.valueOf(paramInt1);
    arrayOfObject[2] = Integer.valueOf(paramInt2);
    arrayOfObject[3] = Integer.valueOf(paramInt3);
    arrayOfObject[4] = Integer.valueOf(paramInt4);
    MMSDK.Log.w(String.format("AdView onLayout changed%b int left %d int top %d int right %d int bottom %d", arrayOfObject));
    float f = getContext().getResources().getDisplayMetrics().density;
    if (this.width <= 0)
      this.width = ((int)(getWidth() / f));
    if (this.height <= 0)
      this.height = ((int)(getHeight() / f));
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    if ((paramBoolean) && (this.adImpl.controller != null))
    {
      if (this.adImpl.controller.webView == null)
        this.adImpl.controller.webView = MMAdImplController.getWebViewFromExistingAdImpl(this.adImpl);
      if (!this.adImpl.controller.webView.isCurrentParent(this.adImpl.internalId))
      {
        this.adImpl.controller.webView.removeFromParent();
        addView(this.adImpl.controller.webView);
      }
    }
  }

  public void setHeight(int paramInt)
  {
    this.height = paramInt;
  }

  public void setTransitionType(int paramInt)
  {
    this.transitionType = paramInt;
  }

  public void setWidth(int paramInt)
  {
    this.width = paramInt;
  }

  class BannerBounds
  {
    DTOResizeParameters resizeParams;
    int translationX;
    int translationY;

    BannerBounds(DTOResizeParameters arg2)
    {
      Object localObject;
      this.resizeParams = localObject;
      this.translationX = localObject.offsetX;
      this.translationY = localObject.offsetY;
    }

    private BoundsResult calculateBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      int i = paramInt1;
      int j = paramInt3;
      if (paramInt2 + (paramInt1 + paramInt3) > paramInt4)
      {
        i = paramInt2 + (paramInt4 - paramInt3);
        if (i < 0)
        {
          i = 0;
          j = paramInt4;
        }
      }
      while (true)
      {
        BoundsResult localBoundsResult = new BoundsResult(null);
        localBoundsResult.translation = (i - paramInt1);
        localBoundsResult.size = j;
        return localBoundsResult;
        if (i + paramInt3 > paramInt4)
        {
          i = paramInt4 - paramInt3;
          continue;
          if (paramInt2 > 0)
            i = paramInt2;
        }
      }
    }

    private void calculateXBounds()
    {
      int[] arrayOfInt = new int[2];
      MMAdView.this.getLocationInWindow(arrayOfInt);
      BoundsResult localBoundsResult = calculateBounds(arrayOfInt[0], this.resizeParams.offsetX, this.resizeParams.width, this.resizeParams.xMax);
      this.resizeParams.width = localBoundsResult.size;
      this.translationX = localBoundsResult.translation;
    }

    private void calculateYBounds()
    {
      int[] arrayOfInt = new int[2];
      MMAdView.this.getLocationInWindow(arrayOfInt);
      BoundsResult localBoundsResult = calculateBounds(arrayOfInt[1], this.resizeParams.offsetY, this.resizeParams.height, this.resizeParams.yMax);
      this.resizeParams.height = localBoundsResult.size;
      this.translationY = localBoundsResult.translation;
    }

    void calculateOnScreenBounds()
    {
      calculateXBounds();
      calculateYBounds();
    }

    ViewGroup.LayoutParams modifyLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      paramLayoutParams.width = this.resizeParams.width;
      paramLayoutParams.height = this.resizeParams.height;
      return paramLayoutParams;
    }

    private class BoundsResult
    {
      int size;
      int translation;

      private BoundsResult()
      {
      }
    }
  }

  class MMAdViewMMAdImpl extends MMLayout.MMLayoutMMAdImpl
  {
    public MMAdViewMMAdImpl(Context arg2)
    {
      super(localContext);
      this.mmWebViewClientListener = new MMAdImpl.BasicMMWebViewClientListener(MMAdView.this)
      {
        public void onPageFinished(String paramAnonymousString)
        {
          super.onPageFinished(paramAnonymousString);
          if (MMAdView.MMAdViewMMAdImpl.this.isTransitionAnimated())
            MMAdView.MMAdViewMMAdImpl.this.animateTransition();
        }
      };
    }

    void animateTransition()
    {
      if (MMAdView.this.refreshAnimationimageView.getDrawable() != null)
        MMSDK.runOnUiThread(new Runnable()
        {
          public void run()
          {
            int i = MMAdView.this.transitionType;
            if (i == 4)
              i = new Random().nextInt(4);
            Object localObject;
            switch (i)
            {
            default:
              localObject = new AlphaAnimation(1.0F, 0.0F);
            case 2:
            case 3:
            }
            while (true)
            {
              ((Animation)localObject).setDuration(1000L);
              ((Animation)localObject).setAnimationListener(MMAdView.this);
              ((Animation)localObject).setFillEnabled(true);
              ((Animation)localObject).setFillBefore(true);
              ((Animation)localObject).setFillAfter(true);
              MMAdView.this.refreshAnimationimageView.startAnimation((Animation)localObject);
              return;
              localObject = new TranslateAnimation(0.0F, 0.0F, 0.0F, -MMAdView.this.getHeight());
              continue;
              localObject = new TranslateAnimation(0.0F, 0.0F, 0.0F, MMAdView.this.getHeight());
            }
          }
        });
    }

    String getReqType()
    {
      return "getad";
    }

    String getRequestCompletedAction()
    {
      return "millennialmedia.action.ACTION_GETAD_SUCCEEDED";
    }

    String getRequestFailedAction()
    {
      return "millennialmedia.action.ACTION_GETAD_FAILED";
    }

    public boolean hasCachedVideoSupport()
    {
      return false;
    }

    void insertUrlAdMetaValues(Map<String, String> paramMap)
    {
      if (MMAdView.this.height > 0)
        paramMap.put("hsht", String.valueOf(MMAdView.this.height));
      if (MMAdView.this.width > 0)
        paramMap.put("hswd", String.valueOf(MMAdView.this.width));
      super.insertUrlAdMetaValues(paramMap);
    }

    public boolean isBanner()
    {
      return true;
    }

    boolean isLifecycleObservable()
    {
      return MMAdView.this.getWindowToken() != null;
    }

    boolean isTransitionAnimated()
    {
      return MMAdView.this.transitionType != 0;
    }

    void prepareTransition(Bitmap paramBitmap)
    {
      MMAdView.this.refreshAnimationimageView.setImageBitmap(paramBitmap);
      MMAdView.this.refreshAnimationimageView.setVisibility(0);
      MMAdView.this.refreshAnimationimageView.bringToFront();
    }
  }

  class ResizeView extends View
  {
    public ResizeView(Context arg2)
    {
      super();
    }

    void attachToContext(View paramView)
    {
      try
      {
        MMAdView.this.detachFromParent(paramView);
        if ((getParent() != null) && ((getParent() instanceof ViewGroup)))
          ((ViewGroup)getParent()).addView(paramView);
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    protected void onRestoreInstanceState(Parcelable paramParcelable)
    {
      MMSDK.Log.d("onRestoreInstanceState");
      MMAdView.this.attachToWindow(MMAdView.this);
      super.onRestoreInstanceState(paramParcelable);
    }

    protected Parcelable onSaveInstanceState()
    {
      MMSDK.Log.d("onSaveInstanceState");
      attachToContext(MMAdView.this);
      return super.onSaveInstanceState();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.MMAdView
 * JD-Core Version:    0.6.2
 */