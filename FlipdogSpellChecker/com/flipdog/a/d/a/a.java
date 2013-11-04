package com.flipdog.a.d.a;

import com.flipdog.a.b.b.b;
import com.flipdog.a.b.b.c;
import com.flipdog.a.c.c.d;
import com.flipdog.commons.a.ba;
import com.flipdog.commons.diagnostic.Track;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONObject;

public class a
{
  private static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);

  public static com.flipdog.a.b.b.a a(JSONObject paramJSONObject, b paramb)
  {
    String str1 = d.c(paramJSONObject, "type");
    String str2 = d.c(paramJSONObject, "name");
    String str3 = d.c(paramJSONObject, "id");
    Object localObject;
    if ("folder".equals(str1))
      localObject = new b(str2, str3);
    while (true)
    {
      ((com.flipdog.a.b.b.a)localObject).b = a(paramJSONObject);
      ((com.flipdog.a.b.b.a)localObject).c = paramb;
      Track.me("Box.com", "Item: %s", new Object[] { localObject });
      return localObject;
      if (!"file".equals(str1))
        break;
      localObject = new c(str2, str3);
      ((c)localObject).e = d.e(paramJSONObject, "size");
    }
    Track.me("Box.com", "Unknown item: %s", new Object[] { paramJSONObject });
    return null;
  }

  public static Date a(JSONObject paramJSONObject)
  {
    String str = d.c(paramJSONObject, "modified_at");
    int i = -3 + str.length();
    if (str.charAt(i) == ':')
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str.substring(0, i));
      localStringBuilder.append(str.substring(i + 1));
      str = localStringBuilder.toString();
    }
    try
    {
      Date localDate = a.parse(str);
      return localDate;
    }
    catch (ParseException localParseException)
    {
      Track.me("Box.com", "Unknown date: %s", new Object[] { str });
    }
    return ba.a();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.d.a.a
 * JD-Core Version:    0.6.2
 */