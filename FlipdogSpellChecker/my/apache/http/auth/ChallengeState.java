package my.apache.http.auth;

public enum ChallengeState
{
  static
  {
    PROXY = new ChallengeState("PROXY", 1);
    ChallengeState[] arrayOfChallengeState = new ChallengeState[2];
    arrayOfChallengeState[0] = TARGET;
    arrayOfChallengeState[1] = PROXY;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.auth.ChallengeState
 * JD-Core Version:    0.6.2
 */