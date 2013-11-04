package com.millennialmedia.android;

import com.millennialmedia.google.gson.annotations.SerializedName;

class DTOAdViewLayout
{
  int height;
  int width;

  @SerializedName("x")
  int xWindowPosition;

  @SerializedName("y")
  int yWindowPosition;

  DTOAdViewLayout(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.xWindowPosition = paramInt1;
    this.yWindowPosition = paramInt2;
    this.width = paramInt3;
    this.height = paramInt4;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.DTOAdViewLayout
 * JD-Core Version:    0.6.2
 */