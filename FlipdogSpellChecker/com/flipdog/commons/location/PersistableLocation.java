package com.flipdog.commons.location;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Printer;

public class PersistableLocation extends Location
{
  private double a;
  private double b;
  private long c;
  private String d;
  private float e;
  private double f;
  private Location g;

  public PersistableLocation()
  {
    super(null);
  }

  public PersistableLocation(Location paramLocation)
  {
    super(null);
    this.a = paramLocation.getLongitude();
    this.b = paramLocation.getLatitude();
    this.c = paramLocation.getTime();
    this.d = paramLocation.getProvider();
    this.e = paramLocation.getAccuracy();
    this.f = paramLocation.getAltitude();
    this.g = paramLocation;
  }

  public float bearingTo(Location paramLocation)
  {
    return this.g.bearingTo(paramLocation);
  }

  protected Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public int describeContents()
  {
    return this.g.describeContents();
  }

  public float distanceTo(Location paramLocation)
  {
    return this.g.distanceTo(paramLocation);
  }

  public void dump(Printer paramPrinter, String paramString)
  {
    super.dump(paramPrinter, paramString);
  }

  public boolean equals(Object paramObject)
  {
    return super.equals(paramObject);
  }

  protected void finalize()
    throws Throwable
  {
    super.finalize();
  }

  public float getAccuracy()
  {
    return this.e;
  }

  public double getAltitude()
  {
    return this.f;
  }

  public float getBearing()
  {
    return this.g.getBearing();
  }

  public Bundle getExtras()
  {
    return this.g.getExtras();
  }

  public double getLatitude()
  {
    return this.b;
  }

  public double getLongitude()
  {
    return this.a;
  }

  public String getProvider()
  {
    return this.d;
  }

  public float getSpeed()
  {
    return this.g.getSpeed();
  }

  public long getTime()
  {
    return this.c;
  }

  public boolean hasAccuracy()
  {
    return this.g.hasAccuracy();
  }

  public boolean hasAltitude()
  {
    return this.g.hasAltitude();
  }

  public boolean hasBearing()
  {
    return this.g.hasBearing();
  }

  public boolean hasSpeed()
  {
    return this.g.hasSpeed();
  }

  public int hashCode()
  {
    return super.hashCode();
  }

  public void removeAccuracy()
  {
    super.removeAccuracy();
  }

  public void removeAltitude()
  {
    super.removeAltitude();
  }

  public void removeBearing()
  {
    super.removeBearing();
  }

  public void removeSpeed()
  {
    super.removeSpeed();
  }

  public void reset()
  {
    super.reset();
  }

  public void set(Location paramLocation)
  {
    super.set(paramLocation);
  }

  public void setAccuracy(float paramFloat)
  {
    super.setAccuracy(paramFloat);
  }

  public void setAltitude(double paramDouble)
  {
    super.setAltitude(paramDouble);
  }

  public void setBearing(float paramFloat)
  {
    super.setBearing(paramFloat);
  }

  public void setExtras(Bundle paramBundle)
  {
    super.setExtras(paramBundle);
  }

  public void setLatitude(double paramDouble)
  {
    this.b = paramDouble;
  }

  public void setLongitude(double paramDouble)
  {
    this.a = paramDouble;
  }

  public void setProvider(String paramString)
  {
    this.d = paramString;
  }

  public void setSpeed(float paramFloat)
  {
    super.setSpeed(paramFloat);
  }

  public void setTime(long paramLong)
  {
    this.c = paramLong;
  }

  public String toString()
  {
    return super.toString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.location.PersistableLocation
 * JD-Core Version:    0.6.2
 */