package com.millennialmedia.android;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.millennialmedia.google.gson.Gson;
import com.millennialmedia.google.gson.annotations.SerializedName;

class OverlaySettings
  implements Parcelable
{
  public static final Parcelable.Creator<OverlaySettings> CREATOR = new Parcelable.Creator()
  {
    public OverlaySettings createFromParcel(Parcel paramAnonymousParcel)
    {
      return new OverlaySettings(paramAnonymousParcel);
    }

    public OverlaySettings[] newArray(int paramAnonymousInt)
    {
      return new OverlaySettings[paramAnonymousInt];
    }
  };
  static final String PROPERTIES_ACCELEROMETER = "enableNativeAccelerometer";
  static final String PROPERTIES_ALLOW_ORIENTATION_CHANGE = "allowOrientationChange";
  static final String PROPERTIES_CUSTOM_CLOSE = "useCustomClose";
  static final String PROPERTIES_ENABLE_HARDWARE_ACCEL = "enableHardwareAccel";
  static final String PROPERTIES_FORCE_ORIENTATION = "forceOrientation";
  static final String PROPERTIES_HEIGHT = "height";
  static final String PROPERTIES_MODAL = "modal";
  static final String PROPERTIES_ORIENTATION = "orientation";
  static final String PROPERTIES_TRANSITION = "transition";
  static final String PROPERTIES_TRANSITION_DURATION = "transitionDuration";
  static final String PROPERTIES_TRANSPARENT = "transparent";
  static final String PROPERTIES_WIDTH = "width";
  String adUrl = "";
  boolean allowOrientationChange = true;
  boolean canAccelerate;
  String content = "";
  long creatorAdImplId;
  boolean hasLoadedExpandUrl = false;
  int height;
  boolean isFromInterstitial;

  @SerializedName("transparent")
  private boolean isTransparent;
  HttpMMHeaders mmHeaders;
  boolean modal;
  String orientation = "";
  boolean shouldLaunchToOverlay;
  int shouldResizeOverlay;
  private String transition = "";

  @SerializedName("transitionDuration")
  private long transitionTimeInMillis;
  String urlToLoad = "";
  private boolean useCustomClose;
  int width;

  OverlaySettings()
  {
  }

  OverlaySettings(Parcel paramParcel)
  {
    try
    {
      boolean[] arrayOfBoolean = new boolean[7];
      paramParcel.readBooleanArray(arrayOfBoolean);
      this.shouldLaunchToOverlay = arrayOfBoolean[0];
      this.isTransparent = arrayOfBoolean[1];
      this.canAccelerate = arrayOfBoolean[2];
      this.useCustomClose = arrayOfBoolean[3];
      this.modal = arrayOfBoolean[4];
      this.isFromInterstitial = arrayOfBoolean[5];
      this.allowOrientationChange = arrayOfBoolean[6];
      this.shouldResizeOverlay = paramParcel.readInt();
      this.transition = paramParcel.readString();
      this.transitionTimeInMillis = paramParcel.readLong();
      if (this.transitionTimeInMillis < l);
      while (true)
      {
        this.transitionTimeInMillis = l;
        this.orientation = paramParcel.readString();
        this.creatorAdImplId = paramParcel.readLong();
        this.urlToLoad = paramParcel.readString();
        this.height = paramParcel.readInt();
        this.width = paramParcel.readInt();
        this.content = paramParcel.readString();
        this.adUrl = paramParcel.readString();
        this.mmHeaders = ((HttpMMHeaders)paramParcel.readParcelable(HttpMMHeaders.class.getClassLoader()));
        return;
        l = this.transitionTimeInMillis;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  static final OverlaySettings createFromJson(String paramString)
  {
    return (OverlaySettings)new Gson().fromJson(paramString, OverlaySettings.class);
  }

  public int describeContents()
  {
    return 0;
  }

  boolean enableHardwareAccel()
  {
    return (this.mmHeaders != null) && (this.mmHeaders.enableHardwareAccel);
  }

  boolean getIsTransparent()
  {
    return (this.isTransparent) || ((this.mmHeaders != null) && (this.mmHeaders.isTransparent));
  }

  String getTransition()
  {
    if (!TextUtils.isEmpty(this.transition))
      return this.transition;
    if ((this.mmHeaders != null) && (this.mmHeaders.transition != null) && (!TextUtils.isEmpty(this.mmHeaders.transition)))
      return this.mmHeaders.transition;
    return "none";
  }

  long getTransitionDurationInMillis()
  {
    long l = 0L;
    if (this.transitionTimeInMillis > l)
      l = this.transitionTimeInMillis;
    while (this.mmHeaders == null)
      return l;
    return this.mmHeaders.transitionTimeInMillis;
  }

  boolean getUseCustomClose()
  {
    return (this.useCustomClose) || ((this.mmHeaders != null) && (this.mmHeaders.useCustomClose));
  }

  boolean hasExpandUrl()
  {
    return (this.urlToLoad != null) && (!this.urlToLoad.equals(""));
  }

  boolean hasLoadedExpandUrl()
  {
    boolean bool = true;
    if (!this.hasLoadedExpandUrl)
    {
      this.hasLoadedExpandUrl = bool;
      bool = false;
    }
    return bool;
  }

  boolean isExpanded()
  {
    return (!this.isFromInterstitial) && (this.creatorAdImplId != 0L);
  }

  boolean isFromInterstitial()
  {
    return (this.isFromInterstitial) && (this.creatorAdImplId != 0L);
  }

  void log()
  {
    MMSDK.Log.v(toString());
  }

  void setIsTransparent(boolean paramBoolean)
  {
    this.isTransparent = paramBoolean;
  }

  void setTransition(String paramString)
  {
    this.transition = paramString;
  }

  void setTransitionDurationInMillis(long paramLong)
  {
    this.transitionTimeInMillis = paramLong;
  }

  void setUseCustomClose(boolean paramBoolean)
  {
    this.useCustomClose = paramBoolean;
  }

  void setWebMMHeaders(HttpMMHeaders paramHttpMMHeaders)
  {
    this.mmHeaders = paramHttpMMHeaders;
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[12];
    arrayOfObject[0] = Integer.valueOf(this.height);
    arrayOfObject[1] = Integer.valueOf(this.width);
    arrayOfObject[2] = Boolean.valueOf(this.modal);
    arrayOfObject[3] = this.urlToLoad;
    arrayOfObject[4] = Long.valueOf(this.creatorAdImplId);
    arrayOfObject[5] = Integer.valueOf(this.shouldResizeOverlay);
    arrayOfObject[6] = Long.valueOf(this.transitionTimeInMillis);
    arrayOfObject[7] = this.transition;
    arrayOfObject[8] = Boolean.valueOf(this.canAccelerate);
    arrayOfObject[9] = Boolean.valueOf(this.isTransparent);
    arrayOfObject[10] = Boolean.valueOf(this.useCustomClose);
    arrayOfObject[11] = this.orientation;
    return String.format("height %d width %d modal %b urlToLoad %s creatorAdImplId %s shouldResizeOverlay: %d transitionTime: %d overlayTransition: %s shouldAccelerate: %b shouldMakeOverlayTransparent: %b shouldShowCustomClose: %b Orientation: %s", arrayOfObject);
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    boolean[] arrayOfBoolean = new boolean[7];
    arrayOfBoolean[0] = this.shouldLaunchToOverlay;
    arrayOfBoolean[1] = this.isTransparent;
    arrayOfBoolean[2] = this.canAccelerate;
    arrayOfBoolean[3] = this.useCustomClose;
    arrayOfBoolean[4] = this.modal;
    arrayOfBoolean[5] = this.isFromInterstitial;
    arrayOfBoolean[6] = this.allowOrientationChange;
    paramParcel.writeBooleanArray(arrayOfBoolean);
    paramParcel.writeInt(this.shouldResizeOverlay);
    paramParcel.writeString(this.transition);
    paramParcel.writeLong(this.transitionTimeInMillis);
    paramParcel.writeString(this.orientation);
    paramParcel.writeLong(this.creatorAdImplId);
    paramParcel.writeString(this.urlToLoad);
    paramParcel.writeInt(this.height);
    paramParcel.writeInt(this.width);
    paramParcel.writeString(this.content);
    paramParcel.writeString(this.adUrl);
    paramParcel.writeParcelable(this.mmHeaders, paramInt);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.OverlaySettings
 * JD-Core Version:    0.6.2
 */