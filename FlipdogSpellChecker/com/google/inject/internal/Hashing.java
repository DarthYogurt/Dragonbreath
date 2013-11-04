package com.google.inject.internal;

final class Hashing
{
  private static final int CUTOFF = 536870912;
  private static final int MAX_TABLE_SIZE = 1073741824;

  static int chooseTableSize(int paramInt)
  {
    if (paramInt < 536870912)
      return Integer.highestOneBit(paramInt) << 2;
    if (paramInt < 1073741824);
    for (boolean bool = true; ; bool = false)
    {
      Preconditions.checkArgument(bool, "collection too large");
      return 1073741824;
    }
  }

  static int smear(int paramInt)
  {
    int i = paramInt ^ (paramInt >>> 20 ^ paramInt >>> 12);
    return i ^ i >>> 7 ^ i >>> 4;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.Hashing
 * JD-Core Version:    0.6.2
 */