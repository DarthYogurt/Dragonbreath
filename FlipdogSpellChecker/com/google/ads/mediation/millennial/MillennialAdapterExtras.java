package com.google.ads.mediation.millennial;

import com.google.ads.mediation.NetworkExtras;

public final class MillennialAdapterExtras
  implements NetworkExtras
{
  private Boolean children = null;
  private Education education = null;
  private Ethnicity ethnicity = null;
  private Integer income = null;
  private InterstitialTime interstitialTime = InterstitialTime.UNKNOWN;
  private MaritalStatus maritalStatus = null;
  private Orientation orientation = null;
  private Politics politics = null;
  private String postalCode = null;

  public MillennialAdapterExtras clearChildren()
  {
    return setChildren(null);
  }

  public MillennialAdapterExtras clearEducation()
  {
    return setEducation(null);
  }

  public MillennialAdapterExtras clearEthnicity()
  {
    return setEthnicity(null);
  }

  public MillennialAdapterExtras clearIncomeInUsDollars()
  {
    return setIncomeInUsDollars(null);
  }

  public MillennialAdapterExtras clearInterstitialTime()
  {
    return setInterstitialTime(null);
  }

  public MillennialAdapterExtras clearMaritalStatus()
  {
    return setMaritalStatus(null);
  }

  public MillennialAdapterExtras clearOrientation()
  {
    return setOrientation(null);
  }

  public MillennialAdapterExtras clearPolitics()
  {
    return setPolitics(null);
  }

  public MillennialAdapterExtras clearPostalCode()
  {
    return setPostalCode(null);
  }

  public Boolean getChildren()
  {
    return this.children;
  }

  public Education getEducation()
  {
    return this.education;
  }

  public Ethnicity getEthnicity()
  {
    return this.ethnicity;
  }

  public Integer getIncomeInUsDollars()
  {
    return this.income;
  }

  public InterstitialTime getInterstitialTime()
  {
    return this.interstitialTime;
  }

  public MaritalStatus getMaritalStatus()
  {
    return this.maritalStatus;
  }

  public Orientation getOrientation()
  {
    return this.orientation;
  }

  public Politics getPolitics()
  {
    return this.politics;
  }

  public String getPostalCode()
  {
    return this.postalCode;
  }

  public MillennialAdapterExtras setChildren(Boolean paramBoolean)
  {
    this.children = paramBoolean;
    return this;
  }

  public MillennialAdapterExtras setEducation(Education paramEducation)
  {
    this.education = paramEducation;
    return this;
  }

  public MillennialAdapterExtras setEthnicity(Ethnicity paramEthnicity)
  {
    this.ethnicity = paramEthnicity;
    return this;
  }

  public MillennialAdapterExtras setIncomeInUsDollars(Integer paramInteger)
  {
    this.income = paramInteger;
    return this;
  }

  public MillennialAdapterExtras setInterstitialTime(InterstitialTime paramInterstitialTime)
  {
    this.interstitialTime = paramInterstitialTime;
    return this;
  }

  public MillennialAdapterExtras setMaritalStatus(MaritalStatus paramMaritalStatus)
  {
    this.maritalStatus = paramMaritalStatus;
    return this;
  }

  public MillennialAdapterExtras setOrientation(Orientation paramOrientation)
  {
    this.orientation = paramOrientation;
    return this;
  }

  public MillennialAdapterExtras setPolitics(Politics paramPolitics)
  {
    this.politics = paramPolitics;
    return this;
  }

  public MillennialAdapterExtras setPostalCode(String paramString)
  {
    this.postalCode = paramString;
    return this;
  }

  public static enum Education
  {
    private final String description;

    static
    {
      ASSOCIATE = new Education("ASSOCIATE", 3, "associate");
      BACHELORS = new Education("BACHELORS", 4, "bachelors");
      MASTERS = new Education("MASTERS", 5, "masters");
      PHD = new Education("PHD", 6, "phd");
      PROFESSIONAL = new Education("PROFESSIONAL", 7, "professional");
      Education[] arrayOfEducation = new Education[8];
      arrayOfEducation[0] = HIGH_SCHOOL;
      arrayOfEducation[1] = IN_COLLEGE;
      arrayOfEducation[2] = SOME_COLLEGE;
      arrayOfEducation[3] = ASSOCIATE;
      arrayOfEducation[4] = BACHELORS;
      arrayOfEducation[5] = MASTERS;
      arrayOfEducation[6] = PHD;
      arrayOfEducation[7] = PROFESSIONAL;
    }

    private Education(String arg3)
    {
      Object localObject;
      this.description = localObject;
    }

    public String getDescription()
    {
      return this.description;
    }
  }

  public static enum Ethnicity
  {
    private final String description;

    static
    {
      BLACK = new Ethnicity("BLACK", 1, "black");
      ASIAN = new Ethnicity("ASIAN", 2, "asian");
      INDIAN = new Ethnicity("INDIAN", 3, "indian");
      MIDDLE_EASTERN = new Ethnicity("MIDDLE_EASTERN", 4, "middleeastern");
      NATIVE_AMERICAN = new Ethnicity("NATIVE_AMERICAN", 5, "nativeamerican");
      PACIFIC_ISLANDER = new Ethnicity("PACIFIC_ISLANDER", 6, "pacificislander");
      WHITE = new Ethnicity("WHITE", 7, "white");
      OTHER = new Ethnicity("OTHER", 8, "other");
      Ethnicity[] arrayOfEthnicity = new Ethnicity[9];
      arrayOfEthnicity[0] = HISPANIC;
      arrayOfEthnicity[1] = BLACK;
      arrayOfEthnicity[2] = ASIAN;
      arrayOfEthnicity[3] = INDIAN;
      arrayOfEthnicity[4] = MIDDLE_EASTERN;
      arrayOfEthnicity[5] = NATIVE_AMERICAN;
      arrayOfEthnicity[6] = PACIFIC_ISLANDER;
      arrayOfEthnicity[7] = WHITE;
      arrayOfEthnicity[8] = OTHER;
    }

    private Ethnicity(String arg3)
    {
      Object localObject;
      this.description = localObject;
    }

    public String getDescription()
    {
      return this.description;
    }
  }

  public static enum InterstitialTime
  {
    static
    {
      APP_LAUNCH = new InterstitialTime("APP_LAUNCH", 1);
      TRANSITION = new InterstitialTime("TRANSITION", 2);
      InterstitialTime[] arrayOfInterstitialTime = new InterstitialTime[3];
      arrayOfInterstitialTime[0] = UNKNOWN;
      arrayOfInterstitialTime[1] = APP_LAUNCH;
      arrayOfInterstitialTime[2] = TRANSITION;
    }
  }

  public static enum MaritalStatus
  {
    private final String description;

    static
    {
      DIVORCED = new MaritalStatus("DIVORCED", 1, "divorced");
      ENGAGED = new MaritalStatus("ENGAGED", 2, "engaged");
      RELATIONSHIP = new MaritalStatus("RELATIONSHIP", 3, "relationship");
      MARRIED = new MaritalStatus("MARRIED", 4, "married");
      MaritalStatus[] arrayOfMaritalStatus = new MaritalStatus[5];
      arrayOfMaritalStatus[0] = SINGLE;
      arrayOfMaritalStatus[1] = DIVORCED;
      arrayOfMaritalStatus[2] = ENGAGED;
      arrayOfMaritalStatus[3] = RELATIONSHIP;
      arrayOfMaritalStatus[4] = MARRIED;
    }

    private MaritalStatus(String arg3)
    {
      Object localObject;
      this.description = localObject;
    }

    public String getDescription()
    {
      return this.description;
    }
  }

  public static enum Orientation
  {
    private final String description;

    static
    {
      GAY = new Orientation("GAY", 1, "gay");
      BISEXUAL = new Orientation("BISEXUAL", 2, "bisexual");
      NOT_SURE = new Orientation("NOT_SURE", 3, "notsure");
      Orientation[] arrayOfOrientation = new Orientation[4];
      arrayOfOrientation[0] = STRAIGHT;
      arrayOfOrientation[1] = GAY;
      arrayOfOrientation[2] = BISEXUAL;
      arrayOfOrientation[3] = NOT_SURE;
    }

    private Orientation(String arg3)
    {
      Object localObject;
      this.description = localObject;
    }

    public String getDescription()
    {
      return this.description;
    }
  }

  public static enum Politics
  {
    private final String description;

    static
    {
      DEMOCRAT = new Politics("DEMOCRAT", 1, "democrat");
      CONSERVATIVE = new Politics("CONSERVATIVE", 2, "conservative");
      MODERATE = new Politics("MODERATE", 3, "moderate");
      LIBERAL = new Politics("LIBERAL", 4, "liberal");
      INDEPENDENT = new Politics("INDEPENDENT", 5, "independent");
      OTHER = new Politics("OTHER", 6, "other");
      UNKNOWN = new Politics("UNKNOWN", 7, "unknown");
      Politics[] arrayOfPolitics = new Politics[8];
      arrayOfPolitics[0] = REPUBLICAN;
      arrayOfPolitics[1] = DEMOCRAT;
      arrayOfPolitics[2] = CONSERVATIVE;
      arrayOfPolitics[3] = MODERATE;
      arrayOfPolitics[4] = LIBERAL;
      arrayOfPolitics[5] = INDEPENDENT;
      arrayOfPolitics[6] = OTHER;
      arrayOfPolitics[7] = UNKNOWN;
    }

    private Politics(String arg3)
    {
      Object localObject;
      this.description = localObject;
    }

    public String getDescription()
    {
      return this.description;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.ads.mediation.millennial.MillennialAdapterExtras
 * JD-Core Version:    0.6.2
 */