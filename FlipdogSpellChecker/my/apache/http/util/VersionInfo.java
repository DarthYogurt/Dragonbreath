package my.apache.http.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class VersionInfo
{
  public static final String PROPERTY_MODULE = "info.module";
  public static final String PROPERTY_RELEASE = "info.release";
  public static final String PROPERTY_TIMESTAMP = "info.timestamp";
  public static final String UNAVAILABLE = "UNAVAILABLE";
  public static final String VERSION_PROPERTY_FILE = "version.properties";
  private final String infoClassloader;
  private final String infoModule;
  private final String infoPackage;
  private final String infoRelease;
  private final String infoTimestamp;

  protected VersionInfo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Package identifier must not be null.");
    this.infoPackage = paramString1;
    if (paramString2 != null)
    {
      this.infoModule = paramString2;
      if (paramString3 == null)
        break label70;
      label36: this.infoRelease = paramString3;
      if (paramString4 == null)
        break label76;
      label46: this.infoTimestamp = paramString4;
      if (paramString5 == null)
        break label83;
    }
    while (true)
    {
      this.infoClassloader = paramString5;
      return;
      paramString2 = "UNAVAILABLE";
      break;
      label70: paramString3 = "UNAVAILABLE";
      break label36;
      label76: paramString4 = "UNAVAILABLE";
      break label46;
      label83: paramString5 = "UNAVAILABLE";
    }
  }

  protected static final VersionInfo fromMap(String paramString, Map<?, ?> paramMap, ClassLoader paramClassLoader)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Package identifier must not be null.");
    String str1 = null;
    String str2 = null;
    String str3 = null;
    if (paramMap != null)
    {
      str1 = (String)paramMap.get("info.module");
      if ((str1 != null) && (str1.length() < 1))
        str1 = null;
      str2 = (String)paramMap.get("info.release");
      if ((str2 != null) && ((str2.length() < 1) || (str2.equals("${pom.version}"))))
        str2 = null;
      str3 = (String)paramMap.get("info.timestamp");
      if ((str3 != null) && ((str3.length() < 1) || (str3.equals("${mvn.timestamp}"))))
        str3 = null;
    }
    String str4 = null;
    if (paramClassLoader != null)
      str4 = paramClassLoader.toString();
    return new VersionInfo(paramString, str1, str2, str3, str4);
  }

  public static final VersionInfo loadVersionInfo(String paramString, ClassLoader paramClassLoader)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Package identifier must not be null.");
    if (paramClassLoader == null)
      paramClassLoader = Thread.currentThread().getContextClassLoader();
    Object localObject1 = null;
    try
    {
      InputStream localInputStream = paramClassLoader.getResourceAsStream(paramString.replace('.', '/') + "/" + "version.properties");
      localObject1 = null;
      if (localInputStream != null);
      try
      {
        Properties localProperties = new Properties();
        localProperties.load(localInputStream);
        localObject1 = localProperties;
        localInputStream.close();
        label95: VersionInfo localVersionInfo = null;
        if (localObject1 != null)
          localVersionInfo = fromMap(paramString, localObject1, paramClassLoader);
        return localVersionInfo;
      }
      finally
      {
        localInputStream.close();
      }
    }
    catch (IOException localIOException)
    {
      break label95;
    }
  }

  public static final VersionInfo[] loadVersionInfo(String[] paramArrayOfString, ClassLoader paramClassLoader)
  {
    if (paramArrayOfString == null)
      throw new IllegalArgumentException("Package identifier list must not be null.");
    ArrayList localArrayList = new ArrayList(paramArrayOfString.length);
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayOfString.length)
        return (VersionInfo[])localArrayList.toArray(new VersionInfo[localArrayList.size()]);
      VersionInfo localVersionInfo = loadVersionInfo(paramArrayOfString[i], paramClassLoader);
      if (localVersionInfo != null)
        localArrayList.add(localVersionInfo);
    }
  }

  public final String getClassloader()
  {
    return this.infoClassloader;
  }

  public final String getModule()
  {
    return this.infoModule;
  }

  public final String getPackage()
  {
    return this.infoPackage;
  }

  public final String getRelease()
  {
    return this.infoRelease;
  }

  public final String getTimestamp()
  {
    return this.infoTimestamp;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(20 + this.infoPackage.length() + this.infoModule.length() + this.infoRelease.length() + this.infoTimestamp.length() + this.infoClassloader.length());
    localStringBuilder.append("VersionInfo(").append(this.infoPackage).append(':').append(this.infoModule);
    if (!"UNAVAILABLE".equals(this.infoRelease))
      localStringBuilder.append(':').append(this.infoRelease);
    if (!"UNAVAILABLE".equals(this.infoTimestamp))
      localStringBuilder.append(':').append(this.infoTimestamp);
    localStringBuilder.append(')');
    if (!"UNAVAILABLE".equals(this.infoClassloader))
      localStringBuilder.append('@').append(this.infoClassloader);
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.util.VersionInfo
 * JD-Core Version:    0.6.2
 */