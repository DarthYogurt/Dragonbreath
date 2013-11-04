package com.flipdog.commons.g;

class ac
  implements Runnable
{
  ac(t paramt, int paramInt)
  {
  }

  public void run()
  {
    switch (this.b)
    {
    default:
      ae.a(t.a(this.a), "Failed to check the license: " + this.b);
      return;
    case 0:
    case 2:
      ae.a(t.a(this.a), "License was successfully updated.");
      return;
    case 1:
      ae.a(t.a(this.a), "You are not allowed to use this application.");
      return;
    case 5:
      ae.a(t.a(this.a), "Licensing server is refusing to talk to this device, over quota. Try again few minutes latter.");
      return;
    case 4:
      ae.a(t.a(this.a), "An error has occurred on the licensing server. Try again few minutes latter.");
      return;
    case 257:
    }
    ae.a(t.a(this.a), "Error contacting licensing server. Check your network connection.");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.g.ac
 * JD-Core Version:    0.6.2
 */