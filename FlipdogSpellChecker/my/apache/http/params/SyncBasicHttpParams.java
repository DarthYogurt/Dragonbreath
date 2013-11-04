package my.apache.http.params;

import my.apache.http.annotation.ThreadSafe;

@ThreadSafe
public class SyncBasicHttpParams extends BasicHttpParams
{
  private static final long serialVersionUID = 5387834869062660642L;

  public void clear()
  {
    try
    {
      super.clear();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    try
    {
      Object localObject2 = super.clone();
      return localObject2;
    }
    finally
    {
      localObject1 = finally;
      throw localObject1;
    }
  }

  public Object getParameter(String paramString)
  {
    try
    {
      Object localObject2 = super.getParameter(paramString);
      return localObject2;
    }
    finally
    {
      localObject1 = finally;
      throw localObject1;
    }
  }

  public boolean isParameterSet(String paramString)
  {
    try
    {
      boolean bool = super.isParameterSet(paramString);
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean isParameterSetLocally(String paramString)
  {
    try
    {
      boolean bool = super.isParameterSetLocally(paramString);
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean removeParameter(String paramString)
  {
    try
    {
      boolean bool = super.removeParameter(paramString);
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public HttpParams setParameter(String paramString, Object paramObject)
  {
    try
    {
      HttpParams localHttpParams = super.setParameter(paramString, paramObject);
      return localHttpParams;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setParameters(String[] paramArrayOfString, Object paramObject)
  {
    try
    {
      super.setParameters(paramArrayOfString, paramObject);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.params.SyncBasicHttpParams
 * JD-Core Version:    0.6.2
 */