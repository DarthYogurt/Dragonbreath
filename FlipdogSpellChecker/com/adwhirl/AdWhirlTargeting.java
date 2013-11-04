package com.adwhirl;

import android.location.Location;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class AdWhirlTargeting
{
  private static GregorianCalendar birthDate;
  private static TargetingGender gender;
  private static Set<String> keywordSet;
  private static Location location;
  private static String postalCode;
  private static boolean testMode;

  static
  {
    resetData();
  }

  public static void addKeyword(String paramString)
  {
    if (keywordSet == null)
      keywordSet = new HashSet();
    keywordSet.add(paramString);
  }

  public static int getAge()
  {
    if (birthDate != null)
      return Calendar.getInstance().get(1) - birthDate.get(1);
    return -1;
  }

  public static GregorianCalendar getBirthDate()
  {
    return birthDate;
  }

  public static TargetingGender getGender()
  {
    return gender;
  }

  public static Set<String> getKeywordSet()
  {
    return keywordSet;
  }

  public static Location getLocation()
  {
    return location;
  }

  public static String getPostalCode()
  {
    return postalCode;
  }

  public static boolean getTestMode()
  {
    return testMode;
  }

  public static void resetData()
  {
    testMode = false;
    gender = TargetingGender.UNKNOWN;
    birthDate = null;
    postalCode = null;
    keywordSet = null;
    location = null;
  }

  public static void setAge(int paramInt)
  {
    birthDate = new GregorianCalendar(Calendar.getInstance().get(1) - paramInt, 0, 1);
  }

  public static void setBirthDate(GregorianCalendar paramGregorianCalendar)
  {
    birthDate = paramGregorianCalendar;
  }

  public static void setGender(TargetingGender paramTargetingGender)
  {
    if (paramTargetingGender == null)
      paramTargetingGender = TargetingGender.UNKNOWN;
    gender = paramTargetingGender;
  }

  public static void setKeywordSet(Set<String> paramSet)
  {
    keywordSet = paramSet;
  }

  public static void setLocation(Location paramLocation)
  {
    location = paramLocation;
  }

  public static void setPostalCode(String paramString)
  {
    postalCode = paramString;
  }

  public static void setTestMode(boolean paramBoolean)
  {
    testMode = paramBoolean;
  }

  public static enum TargetingGender
  {
    static
    {
      MALE = new TargetingGender("MALE", 1);
      FEMALE = new TargetingGender("FEMALE", 2);
      TargetingGender[] arrayOfTargetingGender = new TargetingGender[3];
      arrayOfTargetingGender[0] = UNKNOWN;
      arrayOfTargetingGender[1] = MALE;
      arrayOfTargetingGender[2] = FEMALE;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.adwhirl.AdWhirlTargeting
 * JD-Core Version:    0.6.2
 */