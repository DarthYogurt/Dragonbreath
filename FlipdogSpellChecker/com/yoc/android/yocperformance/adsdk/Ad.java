package com.yoc.android.yocperformance.adsdk;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Xml;
import com.flipdog.commons.diagnostic.Track;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xml.sax.SAXException;

public final class Ad
  implements Parcelable
{
  public static final Parcelable.Creator<Ad> CREATOR = new a();
  private Uri a;
  private Uri b;
  private boolean c = false;
  private List<Uri> d = new ArrayList();

  private Ad()
  {
  }

  public Ad(Uri paramUri1, Uri paramUri2)
  {
    this.a = paramUri1;
    this.b = paramUri2;
  }

  public Ad(Uri paramUri1, Uri paramUri2, boolean paramBoolean, Uri paramUri3)
  {
    this.a = paramUri1;
    this.b = paramUri2;
    this.c = paramBoolean;
    this.d.add(paramUri3);
  }

  public Ad(Parcel paramParcel)
  {
    this.a = ((Uri)paramParcel.readParcelable(null));
    this.b = ((Uri)paramParcel.readParcelable(null));
    int j = paramParcel.readInt();
    while (true)
    {
      if (i >= j)
        return;
      this.d.add((Uri)paramParcel.readParcelable(null));
      i++;
    }
  }

  public static Ad a(String paramString)
    throws SAXException
  {
    if ((paramString == null) || (paramString.length() == 0))
      throw new IllegalArgumentException("Snipplet empty");
    o localo = new o(null);
    Xml.parse(paramString, localo);
    return localo.a();
  }

  private static void c(String paramString)
  {
    Track.it(paramString, new String[] { "Ads" });
  }

  public static Parcelable.Creator<Ad> e()
  {
    return CREATOR;
  }

  public Uri a()
  {
    return this.a;
  }

  public Uri b()
  {
    return this.b;
  }

  public boolean c()
  {
    return this.c;
  }

  public List<Uri> d()
  {
    return this.d;
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable(this.a, 0);
    paramParcel.writeParcelable(this.b, 0);
    paramParcel.writeInt(this.d.size());
    Iterator localIterator = this.d.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      paramParcel.writeParcelable((Uri)localIterator.next(), 0);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.yoc.android.yocperformance.adsdk.Ad
 * JD-Core Version:    0.6.2
 */