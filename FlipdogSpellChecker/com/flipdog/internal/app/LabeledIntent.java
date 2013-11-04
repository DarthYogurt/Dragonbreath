package com.flipdog.internal.app;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;

public class LabeledIntent extends Intent
{
  public static final Parcelable.Creator<LabeledIntent> CREATOR = new j();
  private String a;
  private int b;
  private CharSequence c;
  private int d;

  public LabeledIntent(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    super(paramIntent);
    this.a = paramString;
    this.b = paramInt1;
    this.c = null;
    this.d = paramInt2;
  }

  public LabeledIntent(Intent paramIntent, String paramString, CharSequence paramCharSequence, int paramInt)
  {
    super(paramIntent);
    this.a = paramString;
    this.b = 0;
    this.c = paramCharSequence;
    this.d = paramInt;
  }

  protected LabeledIntent(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }

  public LabeledIntent(String paramString, int paramInt1, int paramInt2)
  {
    this.a = paramString;
    this.b = paramInt1;
    this.c = null;
    this.d = paramInt2;
  }

  public LabeledIntent(String paramString, CharSequence paramCharSequence, int paramInt)
  {
    this.a = paramString;
    this.b = 0;
    this.c = paramCharSequence;
    this.d = paramInt;
  }

  public CharSequence a(PackageManager paramPackageManager)
  {
    CharSequence localCharSequence;
    if (this.c != null)
      localCharSequence = this.c;
    do
    {
      return localCharSequence;
      if ((this.b == 0) || (this.a == null))
        break;
      localCharSequence = paramPackageManager.getText(this.a, this.b, null);
    }
    while (localCharSequence != null);
    return null;
  }

  public String a()
  {
    return this.a;
  }

  public int b()
  {
    return this.b;
  }

  public Drawable b(PackageManager paramPackageManager)
  {
    if ((this.d != 0) && (this.a != null))
    {
      Drawable localDrawable = paramPackageManager.getDrawable(this.a, this.d, null);
      if (localDrawable != null)
        return localDrawable;
    }
    return null;
  }

  public CharSequence c()
  {
    return this.c;
  }

  public int d()
  {
    return this.d;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    super.readFromParcel(paramParcel);
    this.a = paramParcel.readString();
    this.b = paramParcel.readInt();
    this.c = ((CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel));
    this.d = paramParcel.readInt();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString(this.a);
    paramParcel.writeInt(this.b);
    TextUtils.writeToParcel(this.c, paramParcel, paramInt);
    paramParcel.writeInt(this.d);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.internal.app.LabeledIntent
 * JD-Core Version:    0.6.2
 */