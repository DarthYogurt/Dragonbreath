package com.millennialmedia.android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Externalizable;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class VideoAd extends CachedAd
  implements Parcelable, Externalizable
{
  public static final Parcelable.Creator<VideoAd> CREATOR = new Parcelable.Creator()
  {
    public VideoAd createFromParcel(Parcel paramAnonymousParcel)
    {
      return new VideoAd(paramAnonymousParcel);
    }

    public VideoAd[] newArray(int paramAnonymousInt)
    {
      return new VideoAd[paramAnonymousInt];
    }
  };
  static final String VIDEO_FILE_ID = "video.dat";
  static final long serialVersionUID = 2679125946930815832L;
  ArrayList<VideoLogEvent> activities = new ArrayList();
  ArrayList<VideoImage> buttons = new ArrayList();
  String[] cacheComplete;
  String[] cacheFailed;
  String cacheMissURL;
  DTOCachedVideo cachedVideoDto;
  long closeDelayMillis;
  long contentLength;
  long duration;
  String[] endActivity;
  String endOverlayURL;
  String onCompletionUrl;
  boolean reloadNonEndOverlayOnRestart;
  boolean showControls;
  boolean showCountdown;
  String[] startActivity;
  boolean stayInPlayer;
  boolean usingInternalStorage;
  String[] videoError;
  private String videoFileId;
  String webOverlayURL;

  public VideoAd()
  {
  }

  VideoAd(Parcel paramParcel)
  {
    super(paramParcel);
    try
    {
      this.startActivity = new String[paramParcel.readInt()];
      paramParcel.readStringArray(this.startActivity);
      this.endActivity = new String[paramParcel.readInt()];
      paramParcel.readStringArray(this.endActivity);
      boolean[] arrayOfBoolean = new boolean[5];
      paramParcel.readBooleanArray(arrayOfBoolean);
      this.showControls = arrayOfBoolean[0];
      this.stayInPlayer = arrayOfBoolean[1];
      this.showCountdown = arrayOfBoolean[2];
      this.reloadNonEndOverlayOnRestart = arrayOfBoolean[3];
      this.usingInternalStorage = arrayOfBoolean[4];
      this.onCompletionUrl = paramParcel.readString();
      this.webOverlayURL = paramParcel.readString();
      this.endOverlayURL = paramParcel.readString();
      this.cacheMissURL = paramParcel.readString();
      this.videoFileId = paramParcel.readString();
      this.duration = paramParcel.readLong();
      this.contentLength = paramParcel.readLong();
      this.closeDelayMillis = paramParcel.readLong();
      this.buttons = paramParcel.readArrayList(VideoImage.class.getClassLoader());
      this.activities = paramParcel.readArrayList(VideoLogEvent.class.getClassLoader());
      this.cacheComplete = new String[paramParcel.readInt()];
      paramParcel.readStringArray(this.cacheComplete);
      this.cacheFailed = new String[paramParcel.readInt()];
      paramParcel.readStringArray(this.cacheFailed);
      this.videoError = new String[paramParcel.readInt()];
      paramParcel.readStringArray(this.videoError);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  VideoAd(String paramString)
  {
    if (paramString != null);
    try
    {
      JSONObject localJSONObject1 = new JSONObject(paramString);
      localJSONObject2 = localJSONObject1;
      if (localJSONObject2 != null)
      {
        JSONObject localJSONObject3 = localJSONObject2.optJSONObject("video");
        if (localJSONObject3 != null)
          deserializeFromObj(localJSONObject3);
      }
      return;
    }
    catch (JSONException localJSONException)
    {
      while (true)
      {
        localJSONException.printStackTrace();
        JSONObject localJSONObject2 = null;
      }
    }
  }

  private void deleteVideoFile(Context paramContext)
  {
    if (AdCache.deleteFile(paramContext, this.videoFileId + "video.dat"))
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.videoFileId;
      MMSDK.Log.v("VideoAd video file %s was deleted.", arrayOfObject);
    }
  }

  static boolean downloadVideoFile(Context paramContext, String paramString1, String paramString2)
  {
    File localFile = AdCache.getCacheDirectory(paramContext);
    if ((localFile == null) || (!localFile.isDirectory()))
      return false;
    MMSDK.Log.v("Downloading (" + paramString2 + "video.dat" + ") content to %s", new Object[] { localFile });
    boolean bool = AdCache.downloadComponent(paramString1, paramString2 + "video.dat", localFile, paramContext);
    String str = "Caching completed successfully (" + paramString2 + "video.dat" + ")? %b";
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Boolean.valueOf(bool);
    MMSDK.Log.v(str, arrayOfObject);
    return bool;
  }

  static Uri getVideoUri(Context paramContext, String paramString)
  {
    return Uri.fromFile(AdCache.getDownloadFile(paramContext, paramString + "video.dat"));
  }

  private void handleSharedVideoFile(final Context paramContext)
  {
    AdCache.iterateCachedAds(paramContext, 2, new AdCache.Iterator()
    {
      boolean hasSharedVideoFile = false;

      boolean callback(CachedAd paramAnonymousCachedAd)
      {
        if ((paramAnonymousCachedAd != null) && ((paramAnonymousCachedAd instanceof VideoAd)) && (((VideoAd)paramAnonymousCachedAd).videoFileId.equals(VideoAd.this.videoFileId)))
          this.hasSharedVideoFile = true;
        return super.callback(paramAnonymousCachedAd);
      }

      void done()
      {
        if (!this.hasSharedVideoFile)
          VideoAd.this.deleteVideoFile(paramContext);
        super.done();
      }
    });
  }

  static boolean hasVideoFile(Context paramContext, String paramString)
  {
    return AdCache.hasDownloadFile(paramContext, paramString + "video.dat");
  }

  static void playAd(Context paramContext, String paramString, HttpRedirection.RedirectionListenerImpl paramRedirectionListenerImpl)
  {
    if (paramString != null)
    {
      VideoAd localVideoAd = (VideoAd)AdCache.load(paramContext, paramString);
      if ((localVideoAd != null) && (localVideoAd.canShow(paramContext, null, false)))
      {
        paramRedirectionListenerImpl.updateLastVideoViewedTime();
        MMSDK.Log.v("mmvideo: attempting to play video %s", new Object[] { paramString });
        localVideoAd.show(paramContext, paramRedirectionListenerImpl.creatorAdImplInternalId);
        paramRedirectionListenerImpl.startingVideo();
      }
    }
    else
    {
      return;
    }
    MMSDK.Log.v("mmvideo: Ad %s cannot be shown at this time.", new Object[] { paramString });
  }

  boolean canShow(Context paramContext, MMAdImpl paramMMAdImpl, boolean paramBoolean)
  {
    if (paramBoolean)
      if ((isExpired()) || (!isOnDisk(paramContext)) || (!HandShake.sharedHandShake(paramContext).canDisplayCachedAd(paramMMAdImpl.adType, this.deferredViewStart)));
    while ((!isExpired()) && (isOnDisk(paramContext)))
    {
      return true;
      return false;
    }
    return false;
  }

  void delete(Context paramContext)
  {
    super.delete(paramContext);
    handleSharedVideoFile(paramContext);
    AdCache.cachedVideoWasRemoved(paramContext, this.acid);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = getId();
    MMSDK.Log.v("Ad %s was deleted.", arrayOfObject);
  }

  public int describeContents()
  {
    return 0;
  }

  protected void deserializeFromObj(JSONObject paramJSONObject)
  {
    super.deserializeFromObj(paramJSONObject);
    JSONArray localJSONArray1 = paramJSONObject.optJSONArray("startActivity");
    this.webOverlayURL = paramJSONObject.optString("overlayURL", null);
    this.endOverlayURL = paramJSONObject.optString("endURL", null);
    this.cacheMissURL = paramJSONObject.optString("cacheMissURL", null);
    this.videoFileId = paramJSONObject.optString("videoFileId", null);
    if (localJSONArray1 != null)
    {
      this.startActivity = new String[localJSONArray1.length()];
      for (int i2 = 0; i2 < localJSONArray1.length(); i2++)
        this.startActivity[i2] = localJSONArray1.optString(i2);
    }
    this.startActivity = new String[0];
    JSONArray localJSONArray2 = paramJSONObject.optJSONArray("endActivity");
    if (localJSONArray2 != null)
    {
      this.endActivity = new String[localJSONArray2.length()];
      for (int i1 = 0; i1 < localJSONArray2.length(); i1++)
        this.endActivity[i1] = localJSONArray2.optString(i1);
    }
    this.endActivity = new String[0];
    JSONArray localJSONArray3 = paramJSONObject.optJSONArray("cacheComplete");
    if (localJSONArray3 != null)
    {
      this.cacheComplete = new String[localJSONArray3.length()];
      for (int n = 0; n < localJSONArray3.length(); n++)
        this.cacheComplete[n] = localJSONArray3.optString(n);
    }
    this.cacheComplete = new String[0];
    JSONArray localJSONArray4 = paramJSONObject.optJSONArray("cacheFailed");
    if (localJSONArray4 != null)
    {
      this.cacheFailed = new String[localJSONArray4.length()];
      for (int m = 0; m < localJSONArray4.length(); m++)
        this.cacheFailed[m] = localJSONArray4.optString(m);
    }
    this.cacheFailed = new String[0];
    JSONArray localJSONArray5 = paramJSONObject.optJSONArray("videoError");
    if (localJSONArray5 != null)
    {
      this.videoError = new String[localJSONArray5.length()];
      for (int k = 0; k < localJSONArray5.length(); k++)
        this.videoError[k] = localJSONArray5.optString(k);
    }
    this.videoError = new String[0];
    this.showControls = paramJSONObject.optBoolean("showVideoPlayerControls");
    this.showCountdown = paramJSONObject.optBoolean("showCountdownHUD");
    this.reloadNonEndOverlayOnRestart = paramJSONObject.optBoolean("reloadOverlayOnRestart");
    JSONObject localJSONObject1 = paramJSONObject.optJSONObject("onCompletion");
    if (localJSONObject1 != null)
    {
      this.onCompletionUrl = localJSONObject1.optString("url", null);
      this.stayInPlayer = localJSONObject1.optBoolean("stayInPlayer");
    }
    this.duration = (()(1000.0D * paramJSONObject.optDouble("duration", 0.0D)));
    this.contentLength = paramJSONObject.optLong("contentLength");
    this.closeDelayMillis = paramJSONObject.optLong("closeAfterDelay");
    JSONArray localJSONArray6 = paramJSONObject.optJSONArray("buttons");
    if (localJSONArray6 != null)
      for (int j = 0; j < localJSONArray6.length(); j++)
      {
        JSONObject localJSONObject3 = localJSONArray6.optJSONObject(j);
        if (localJSONObject3 != null)
        {
          VideoImage localVideoImage = new VideoImage(localJSONObject3);
          this.buttons.add(localVideoImage);
        }
      }
    JSONArray localJSONArray7 = paramJSONObject.optJSONArray("log");
    if (localJSONArray7 != null)
      for (int i = 0; i < localJSONArray7.length(); i++)
      {
        JSONObject localJSONObject2 = localJSONArray7.optJSONObject(i);
        if (localJSONObject2 != null)
        {
          VideoLogEvent localVideoLogEvent = new VideoLogEvent(localJSONObject2);
          this.activities.add(localVideoLogEvent);
        }
      }
  }

  boolean download(Context paramContext)
  {
    File localFile = AdCache.getCacheDirectory(paramContext);
    if ((localFile == null) || (!localFile.isDirectory()))
      return false;
    this.usingInternalStorage = AdCache.isInternalDir(paramContext, localFile);
    MMSDK.Log.v("Downloading content to %s", new Object[] { localFile });
    boolean bool = AdCache.downloadComponent(this.contentUrl, this.videoFileId + "video.dat", localFile, paramContext);
    int i;
    if (bool)
    {
      i = 0;
      if (i < this.buttons.size())
      {
        VideoImage localVideoImage = (VideoImage)this.buttons.get(i);
        bool = AdCache.downloadComponent(localVideoImage.imageUrl, getId() + localVideoImage.getImageName(), localFile, paramContext);
        if (bool)
          break label193;
      }
    }
    if (!bool)
    {
      if (this.downloadAllOrNothing)
        delete(paramContext);
      HttpGetRequest.log(this.cacheFailed);
    }
    while (true)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Boolean.valueOf(bool);
      MMSDK.Log.v("Caching completed successfully? %b", arrayOfObject);
      return bool;
      label193: i++;
      break;
      if (bool)
      {
        if ((this.acid != null) && (this.acid.length() > 0))
          AdCache.cachedVideoWasAdded(paramContext, this.acid);
        HttpGetRequest.log(this.cacheComplete);
      }
    }
  }

  int getType()
  {
    return 1;
  }

  String getTypeString()
  {
    return "Video";
  }

  Intent getVideoExtrasIntent(Context paramContext, long paramLong)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("videoId", getId());
    if (paramLong != -4L)
      localIntent.putExtra("internalId", paramLong);
    localIntent.setData(Uri.parse(AdCache.getCacheDirectory(paramContext).getAbsolutePath() + File.separator + this.videoFileId + "video.dat"));
    return localIntent;
  }

  boolean hasEndCard()
  {
    Iterator localIterator = this.buttons.iterator();
    while (localIterator.hasNext())
      if (((VideoImage)localIterator.next()).isLeaveBehind)
        return true;
    return false;
  }

  boolean isOnDisk(Context paramContext)
  {
    File localFile = AdCache.getCacheDirectory(paramContext);
    boolean bool1 = false;
    if (localFile != null)
    {
      boolean bool2 = localFile.exists();
      bool1 = false;
      if (bool2)
      {
        String[] arrayOfString = localFile.list(new FilenameFilter()
        {
          public boolean accept(File paramAnonymousFile, String paramAnonymousString)
          {
            if (VideoAd.this.getId() == null)
              return false;
            return paramAnonymousString.startsWith(VideoAd.this.getId());
          }
        });
        bool1 = false;
        if (arrayOfString != null)
        {
          int i = arrayOfString.length;
          int j = 1 + this.buttons.size();
          bool1 = false;
          if (i >= j)
            bool1 = true;
        }
        if (bool1)
          if (new File(localFile, this.videoFileId + "video.dat").exists())
            break label116;
      }
    }
    label116: VideoImage localVideoImage;
    do
    {
      bool1 = false;
      Iterator localIterator;
      while (!localIterator.hasNext())
      {
        return bool1;
        localIterator = this.buttons.iterator();
      }
      localVideoImage = (VideoImage)localIterator.next();
    }
    while (new File(localFile, getId() + localVideoImage.getImageName()).exists());
    return false;
  }

  void logBeginEvent()
  {
    if (this.startActivity != null)
    {
      MMSDK.Log.d("Cached video begin event logged");
      for (int i = 0; i < this.startActivity.length; i++)
        MMSDK.Event.logEvent(this.startActivity[i]);
    }
  }

  void logEndEvent()
  {
    if (this.endActivity != null)
    {
      MMSDK.Log.d("Cached video end event logged");
      for (int i = 0; i < this.endActivity.length; i++)
        MMSDK.Event.logEvent(this.endActivity[i]);
    }
  }

  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    super.readExternal(paramObjectInput);
    this.showControls = paramObjectInput.readBoolean();
    this.onCompletionUrl = ((String)paramObjectInput.readObject());
    this.webOverlayURL = ((String)paramObjectInput.readObject());
    this.endOverlayURL = ((String)paramObjectInput.readObject());
    this.cacheMissURL = ((String)paramObjectInput.readObject());
    this.videoFileId = ((String)paramObjectInput.readObject());
    this.stayInPlayer = paramObjectInput.readBoolean();
    this.showCountdown = paramObjectInput.readBoolean();
    this.reloadNonEndOverlayOnRestart = paramObjectInput.readBoolean();
    int i = paramObjectInput.readInt();
    this.startActivity = new String[i];
    for (int j = 0; j < i; j++)
      this.startActivity[j] = ((String)paramObjectInput.readObject());
    int k = paramObjectInput.readInt();
    this.endActivity = new String[k];
    for (int m = 0; m < k; m++)
      this.endActivity[m] = ((String)paramObjectInput.readObject());
    this.duration = paramObjectInput.readLong();
    this.usingInternalStorage = paramObjectInput.readBoolean();
    this.contentLength = paramObjectInput.readLong();
    this.closeDelayMillis = paramObjectInput.readLong();
    int n = paramObjectInput.readInt();
    this.cacheComplete = new String[n];
    for (int i1 = 0; i1 < n; i1++)
      this.cacheComplete[i1] = ((String)paramObjectInput.readObject());
    int i2 = paramObjectInput.readInt();
    this.cacheFailed = new String[i2];
    for (int i3 = 0; i3 < i2; i3++)
      this.cacheFailed[i3] = ((String)paramObjectInput.readObject());
    int i4 = paramObjectInput.readInt();
    this.videoError = new String[i4];
    for (int i5 = 0; i5 < i4; i5++)
      this.videoError[i5] = ((String)paramObjectInput.readObject());
    this.buttons.clear();
    int i6 = paramObjectInput.readInt();
    for (int i7 = 0; i7 < i6; i7++)
    {
      VideoImage localVideoImage = (VideoImage)paramObjectInput.readObject();
      this.buttons.add(localVideoImage);
    }
    this.activities.clear();
    int i8 = paramObjectInput.readInt();
    for (int i9 = 0; i9 < i8; i9++)
    {
      VideoLogEvent localVideoLogEvent = (VideoLogEvent)paramObjectInput.readObject();
      this.activities.add(localVideoLogEvent);
    }
  }

  boolean saveAssets(Context paramContext)
  {
    return true;
  }

  void setDtoCachedVideo(DTOCachedVideo paramDTOCachedVideo)
  {
    this.cachedVideoDto = paramDTOCachedVideo;
  }

  void show(Context paramContext, long paramLong)
  {
    Utils.IntentUtils.startCachedVideoPlayerActivity(paramContext, getVideoExtrasIntent(paramContext, paramLong));
  }

  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    super.writeExternal(paramObjectOutput);
    paramObjectOutput.writeBoolean(this.showControls);
    paramObjectOutput.writeObject(this.onCompletionUrl);
    paramObjectOutput.writeObject(this.webOverlayURL);
    paramObjectOutput.writeObject(this.endOverlayURL);
    paramObjectOutput.writeObject(this.cacheMissURL);
    paramObjectOutput.writeObject(this.videoFileId);
    paramObjectOutput.writeBoolean(this.stayInPlayer);
    paramObjectOutput.writeBoolean(this.showCountdown);
    paramObjectOutput.writeBoolean(this.reloadNonEndOverlayOnRestart);
    paramObjectOutput.writeInt(this.startActivity.length);
    String[] arrayOfString1 = this.startActivity;
    int i = arrayOfString1.length;
    for (int j = 0; j < i; j++)
      paramObjectOutput.writeObject(arrayOfString1[j]);
    paramObjectOutput.writeInt(this.endActivity.length);
    String[] arrayOfString2 = this.endActivity;
    int k = arrayOfString2.length;
    for (int m = 0; m < k; m++)
      paramObjectOutput.writeObject(arrayOfString2[m]);
    paramObjectOutput.writeLong(this.duration);
    paramObjectOutput.writeBoolean(this.usingInternalStorage);
    paramObjectOutput.writeLong(this.contentLength);
    paramObjectOutput.writeLong(this.closeDelayMillis);
    paramObjectOutput.writeInt(this.cacheComplete.length);
    String[] arrayOfString3 = this.cacheComplete;
    int n = arrayOfString3.length;
    for (int i1 = 0; i1 < n; i1++)
      paramObjectOutput.writeObject(arrayOfString3[i1]);
    paramObjectOutput.writeInt(this.cacheFailed.length);
    String[] arrayOfString4 = this.cacheFailed;
    int i2 = arrayOfString4.length;
    for (int i3 = 0; i3 < i2; i3++)
      paramObjectOutput.writeObject(arrayOfString4[i3]);
    paramObjectOutput.writeInt(this.videoError.length);
    String[] arrayOfString5 = this.videoError;
    int i4 = arrayOfString5.length;
    for (int i5 = 0; i5 < i4; i5++)
      paramObjectOutput.writeObject(arrayOfString5[i5]);
    paramObjectOutput.writeInt(this.buttons.size());
    Iterator localIterator1 = this.buttons.iterator();
    while (localIterator1.hasNext())
      paramObjectOutput.writeObject((VideoImage)localIterator1.next());
    paramObjectOutput.writeInt(this.activities.size());
    Iterator localIterator2 = this.activities.iterator();
    while (localIterator2.hasNext())
      paramObjectOutput.writeObject((VideoLogEvent)localIterator2.next());
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(this.startActivity.length);
    paramParcel.writeStringArray(this.startActivity);
    paramParcel.writeInt(this.endActivity.length);
    paramParcel.writeStringArray(this.endActivity);
    boolean[] arrayOfBoolean = new boolean[5];
    arrayOfBoolean[0] = this.showControls;
    arrayOfBoolean[1] = this.stayInPlayer;
    arrayOfBoolean[2] = this.showCountdown;
    arrayOfBoolean[3] = this.reloadNonEndOverlayOnRestart;
    arrayOfBoolean[4] = this.usingInternalStorage;
    paramParcel.writeBooleanArray(arrayOfBoolean);
    paramParcel.writeString(this.onCompletionUrl);
    paramParcel.writeString(this.endOverlayURL);
    paramParcel.writeString(this.webOverlayURL);
    paramParcel.writeString(this.cacheMissURL);
    paramParcel.writeString(this.videoFileId);
    paramParcel.writeLong(this.duration);
    paramParcel.writeLong(this.contentLength);
    paramParcel.writeLong(this.closeDelayMillis);
    paramParcel.writeList(this.buttons);
    paramParcel.writeList(this.activities);
    paramParcel.writeInt(this.cacheComplete.length);
    paramParcel.writeStringArray(this.cacheComplete);
    paramParcel.writeInt(this.cacheFailed.length);
    paramParcel.writeStringArray(this.cacheFailed);
    paramParcel.writeInt(this.videoError.length);
    paramParcel.writeStringArray(this.videoError);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.VideoAd
 * JD-Core Version:    0.6.2
 */