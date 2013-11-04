package com.millennialmedia.android;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.json.JSONArray;
import org.json.JSONObject;

class VideoLogEvent
  implements Parcelable, Externalizable
{
  public static final Parcelable.Creator<VideoLogEvent> CREATOR = new Parcelable.Creator()
  {
    public VideoLogEvent createFromParcel(Parcel paramAnonymousParcel)
    {
      return new VideoLogEvent(paramAnonymousParcel);
    }

    public VideoLogEvent[] newArray(int paramAnonymousInt)
    {
      return new VideoLogEvent[paramAnonymousInt];
    }
  };
  static final long serialVersionUID = 795553873017368584L;
  String[] activities;
  long position;

  public VideoLogEvent()
  {
  }

  VideoLogEvent(Parcel paramParcel)
  {
    try
    {
      this.position = paramParcel.readLong();
      this.activities = new String[paramParcel.readInt()];
      paramParcel.readStringArray(this.activities);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  VideoLogEvent(JSONObject paramJSONObject)
  {
    deserializeFromObj(paramJSONObject);
  }

  private void deserializeFromObj(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null);
    while (true)
    {
      return;
      this.position = (1000 * paramJSONObject.optInt("time"));
      JSONArray localJSONArray = paramJSONObject.optJSONArray("urls");
      if (localJSONArray == null)
        break;
      this.activities = new String[localJSONArray.length()];
      for (int i = 0; i < localJSONArray.length(); i++)
        this.activities[i] = localJSONArray.optString(i);
    }
    this.activities = new String[0];
  }

  public int describeContents()
  {
    return 0;
  }

  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.position = paramObjectInput.readLong();
    int i = paramObjectInput.readInt();
    this.activities = new String[i];
    for (int j = 0; j < i; j++)
      this.activities[j] = ((String)paramObjectInput.readObject());
  }

  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeLong(this.position);
    paramObjectOutput.writeInt(this.activities.length);
    String[] arrayOfString = this.activities;
    int i = arrayOfString.length;
    for (int j = 0; j < i; j++)
      paramObjectOutput.writeObject(arrayOfString[j]);
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeLong(this.position);
    paramParcel.writeInt(this.activities.length);
    paramParcel.writeStringArray(this.activities);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.VideoLogEvent
 * JD-Core Version:    0.6.2
 */