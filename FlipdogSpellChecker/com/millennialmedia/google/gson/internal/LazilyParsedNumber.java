package com.millennialmedia.google.gson.internal;

import java.io.ObjectStreamException;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class LazilyParsedNumber extends Number
{
  private final String value;

  public LazilyParsedNumber(String paramString)
  {
    this.value = paramString;
  }

  private Object writeReplace()
    throws ObjectStreamException
  {
    return new BigDecimal(this.value);
  }

  public double doubleValue()
  {
    return Double.parseDouble(this.value);
  }

  public float floatValue()
  {
    return Float.parseFloat(this.value);
  }

  public int intValue()
  {
    try
    {
      int i = Integer.parseInt(this.value);
      return i;
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      try
      {
        long l = Long.parseLong(this.value);
        return (int)l;
      }
      catch (NumberFormatException localNumberFormatException2)
      {
      }
    }
    return new BigInteger(this.value).intValue();
  }

  public long longValue()
  {
    try
    {
      long l = Long.parseLong(this.value);
      return l;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    return new BigInteger(this.value).longValue();
  }

  public String toString()
  {
    return this.value;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.google.gson.internal.LazilyParsedNumber
 * JD-Core Version:    0.6.2
 */