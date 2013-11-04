package com.millennialmedia.android;

import android.content.Context;
import android.os.Parcel;
import java.io.Externalizable;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

abstract class CachedAd
  implements Externalizable
{
  static final int INTERSTITIAL = 2;
  static final int NATIVE = 3;
  static final int VIDEO = 1;
  static final long serialVersionUID = 316862728709355974L;
  String acid;
  String contentUrl;
  long deferredViewStart;
  boolean downloadAllOrNothing = false;
  int downloadPriority;
  Date expiration;
  private String id;

  CachedAd()
  {
    this.deferredViewStart = System.currentTimeMillis();
  }

  protected CachedAd(Parcel paramParcel)
  {
    try
    {
      this.id = paramParcel.readString();
      this.acid = paramParcel.readString();
      this.expiration = ((Date)paramParcel.readSerializable());
      this.deferredViewStart = paramParcel.readLong();
      boolean[] arrayOfBoolean = new boolean[1];
      paramParcel.readBooleanArray(arrayOfBoolean);
      this.downloadAllOrNothing = arrayOfBoolean[0];
      this.contentUrl = paramParcel.readString();
      this.downloadPriority = paramParcel.readInt();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  static CachedAd parseJSON(String paramString)
  {
    if (MMSDK.logLevel >= 5)
    {
      MMSDK.Log.v("Received cached ad.");
      int j = paramString.length();
      if (j <= 1000)
        break label103;
      int k = 999;
      int m = 0;
      while (k < j)
      {
        MMSDK.Log.v(paramString.substring(m, k));
        m = k;
        k += 1000;
        if (k > j)
          k = j - 1;
      }
      MMSDK.Log.v(paramString.substring(m, k));
    }
    while (true)
    {
      int i = paramString.length();
      VideoAd localVideoAd = null;
      if (i > 0)
        localVideoAd = new VideoAd(paramString);
      return localVideoAd;
      label103: MMSDK.Log.v(paramString);
    }
  }

  abstract boolean canShow(Context paramContext, MMAdImpl paramMMAdImpl, boolean paramBoolean);

  void delete(Context paramContext)
  {
    File localFile = AdCache.getCacheDirectory(paramContext);
    if ((localFile != null) && (localFile.isDirectory()))
      try
      {
        File[] arrayOfFile = localFile.listFiles(new FileFilter()
        {
          public boolean accept(File paramAnonymousFile)
          {
            return (paramAnonymousFile.isFile()) && (paramAnonymousFile.getName().startsWith(CachedAd.this.id));
          }
        });
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(arrayOfFile.length);
        arrayOfObject[1] = this.id;
        MMSDK.Log.v("Deleting %d files for %s.", arrayOfObject);
        for (int i = 0; i < arrayOfFile.length; i++)
          arrayOfFile[i].delete();
      }
      catch (Exception localException)
      {
        MMSDK.Log.v(localException);
      }
  }

  protected void deserializeFromObj(JSONObject paramJSONObject)
  {
    this.id = paramJSONObject.optString("id", null);
    this.acid = paramJSONObject.optString("vid", null);
    this.contentUrl = paramJSONObject.optString("content-url", null);
    String str = paramJSONObject.optString("expiration", null);
    SimpleDateFormat localSimpleDateFormat;
    if (str != null)
      localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ZZZZ");
    try
    {
      this.expiration = localSimpleDateFormat.parse(str);
      return;
    }
    catch (ParseException localParseException)
    {
      MMSDK.Log.e(localParseException);
    }
  }

  abstract boolean download(Context paramContext);

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if (!(paramObject instanceof CachedAd))
      return false;
    CachedAd localCachedAd = (CachedAd)paramObject;
    return this.id.equals(localCachedAd.id);
  }

  String getId()
  {
    return this.id;
  }

  abstract int getType();

  abstract String getTypeString();

  boolean isExpired()
  {
    return (this.expiration != null) && (this.expiration.getTime() <= System.currentTimeMillis());
  }

  abstract boolean isOnDisk(Context paramContext);

  boolean isValid()
  {
    return (this.id != null) && (this.id.length() > 0) && (this.contentUrl != null) && (this.contentUrl.length() > 0);
  }

  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.id = ((String)paramObjectInput.readObject());
    this.acid = ((String)paramObjectInput.readObject());
    this.expiration = ((Date)paramObjectInput.readObject());
    this.deferredViewStart = paramObjectInput.readLong();
    this.contentUrl = ((String)paramObjectInput.readObject());
  }

  abstract boolean saveAssets(Context paramContext);

  void setId(String paramString)
  {
    this.id = paramString;
  }

  abstract void show(Context paramContext, long paramLong);

  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(this.id);
    paramObjectOutput.writeObject(this.acid);
    paramObjectOutput.writeObject(this.expiration);
    paramObjectOutput.writeLong(this.deferredViewStart);
    paramObjectOutput.writeObject(this.contentUrl);
  }

  protected void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.id);
    paramParcel.writeString(this.acid);
    paramParcel.writeSerializable(this.expiration);
    paramParcel.writeLong(this.deferredViewStart);
    boolean[] arrayOfBoolean = new boolean[1];
    arrayOfBoolean[0] = this.downloadAllOrNothing;
    paramParcel.writeBooleanArray(arrayOfBoolean);
    paramParcel.writeString(this.contentUrl);
    paramParcel.writeInt(this.downloadPriority);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.CachedAd
 * JD-Core Version:    0.6.2
 */