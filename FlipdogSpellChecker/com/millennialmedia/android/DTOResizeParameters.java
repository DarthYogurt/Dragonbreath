package com.millennialmedia.android;

class DTOResizeParameters
{
  boolean allowOffScreen;
  String customClosePosition;
  int height;
  int offsetX;
  int offsetY;
  int width;
  int xMax;
  int yMax;

  DTOResizeParameters(float paramFloat, int paramInt1, int paramInt2, String paramString, int paramInt3, int paramInt4, boolean paramBoolean, int paramInt5, int paramInt6)
  {
    this.width = ((int)(paramFloat * paramInt1));
    this.height = ((int)(paramFloat * paramInt2));
    this.customClosePosition = paramString;
    this.offsetX = ((int)(paramFloat * paramInt3));
    this.offsetY = ((int)(paramFloat * paramInt4));
    this.allowOffScreen = paramBoolean;
    this.xMax = paramInt5;
    this.yMax = paramInt6;
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[8];
    arrayOfObject[0] = Integer.valueOf(this.width);
    arrayOfObject[1] = Integer.valueOf(this.height);
    arrayOfObject[2] = Integer.valueOf(this.offsetX);
    arrayOfObject[3] = Integer.valueOf(this.offsetY);
    arrayOfObject[4] = Boolean.valueOf(this.allowOffScreen);
    arrayOfObject[5] = this.customClosePosition;
    arrayOfObject[6] = Integer.valueOf(this.xMax);
    arrayOfObject[7] = Integer.valueOf(this.yMax);
    return String.format("width[%d] height[%d] offsetX[%d] offsetY[%d] allowOffScreen[%b] customClosePosition[%s] maxX[%d] maxY[%d]", arrayOfObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.DTOResizeParameters
 * JD-Core Version:    0.6.2
 */