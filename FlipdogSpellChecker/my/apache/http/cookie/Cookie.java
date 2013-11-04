package my.apache.http.cookie;

import java.util.Date;

public abstract interface Cookie
{
  public abstract String getComment();

  public abstract String getCommentURL();

  public abstract String getDomain();

  public abstract Date getExpiryDate();

  public abstract String getName();

  public abstract String getPath();

  public abstract int[] getPorts();

  public abstract String getValue();

  public abstract int getVersion();

  public abstract boolean isExpired(Date paramDate);

  public abstract boolean isPersistent();

  public abstract boolean isSecure();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.cookie.Cookie
 * JD-Core Version:    0.6.2
 */