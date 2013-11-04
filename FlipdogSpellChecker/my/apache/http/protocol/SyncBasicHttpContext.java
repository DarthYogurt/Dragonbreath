package my.apache.http.protocol;

@Deprecated
public class SyncBasicHttpContext extends BasicHttpContext
{
  public SyncBasicHttpContext()
  {
  }

  public SyncBasicHttpContext(HttpContext paramHttpContext)
  {
    super(paramHttpContext);
  }

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

  public Object getAttribute(String paramString)
  {
    try
    {
      Object localObject2 = super.getAttribute(paramString);
      return localObject2;
    }
    finally
    {
      localObject1 = finally;
      throw localObject1;
    }
  }

  public Object removeAttribute(String paramString)
  {
    try
    {
      Object localObject2 = super.removeAttribute(paramString);
      return localObject2;
    }
    finally
    {
      localObject1 = finally;
      throw localObject1;
    }
  }

  public void setAttribute(String paramString, Object paramObject)
  {
    try
    {
      super.setAttribute(paramString, paramObject);
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
 * Qualified Name:     my.apache.http.protocol.SyncBasicHttpContext
 * JD-Core Version:    0.6.2
 */