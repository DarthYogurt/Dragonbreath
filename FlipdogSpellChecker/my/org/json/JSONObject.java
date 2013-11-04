package my.org.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;

public class JSONObject
{
  public static final Object NULL = new Null(null);
  private final Map map = new HashMap();

  public JSONObject()
  {
  }

  public JSONObject(Object paramObject)
  {
    this();
    populateMap(paramObject);
  }

  public JSONObject(Object paramObject, String[] paramArrayOfString)
  {
    this();
    Class localClass = paramObject.getClass();
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfString.length)
        return;
      String str = paramArrayOfString[i];
      try
      {
        putOpt(str, localClass.getField(str).get(paramObject));
        label43: i++;
      }
      catch (Exception localException)
      {
        break label43;
      }
    }
  }

  public JSONObject(String paramString)
    throws JSONException
  {
    this(new JSONTokener(paramString));
  }

  public JSONObject(String paramString, Locale paramLocale)
    throws JSONException
  {
    this();
    ResourceBundle localResourceBundle = ResourceBundle.getBundle(paramString, paramLocale, Thread.currentThread().getContextClassLoader());
    Enumeration localEnumeration = localResourceBundle.getKeys();
    Object localObject1;
    do
    {
      if (!localEnumeration.hasMoreElements())
        return;
      localObject1 = localEnumeration.nextElement();
    }
    while (!(localObject1 instanceof String));
    String[] arrayOfString = ((String)localObject1).split("\\.");
    int i = -1 + arrayOfString.length;
    Object localObject2 = this;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        ((JSONObject)localObject2).put(arrayOfString[i], localResourceBundle.getString((String)localObject1));
        break;
      }
      String str = arrayOfString[j];
      JSONObject localJSONObject = ((JSONObject)localObject2).optJSONObject(str);
      if (localJSONObject == null)
      {
        localJSONObject = new JSONObject();
        ((JSONObject)localObject2).put(str, localJSONObject);
      }
      localObject2 = localJSONObject;
    }
  }

  public JSONObject(Map paramMap)
  {
    Iterator localIterator;
    if (paramMap != null)
      localIterator = paramMap.entrySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject = localEntry.getValue();
      if (localObject != null)
        this.map.put(localEntry.getKey(), wrap(localObject));
    }
  }

  public JSONObject(JSONObject paramJSONObject, String[] paramArrayOfString)
  {
    this();
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfString.length)
        return;
      try
      {
        putOnce(paramArrayOfString[i], paramJSONObject.opt(paramArrayOfString[i]));
        label28: i++;
      }
      catch (Exception localException)
      {
        break label28;
      }
    }
  }

  public JSONObject(JSONTokener paramJSONTokener)
    throws JSONException
  {
    this();
    if (paramJSONTokener.nextClean() != '{')
      throw paramJSONTokener.syntaxError("A JSONObject text must begin with '{'");
    do
    {
      paramJSONTokener.back();
      String str;
      int i;
      switch (paramJSONTokener.nextClean())
      {
      default:
        paramJSONTokener.back();
        str = paramJSONTokener.nextValue().toString();
        i = paramJSONTokener.nextClean();
        if (i == 61)
          if (paramJSONTokener.next() != '>')
            paramJSONTokener.back();
      case '\000':
        while (i == 58)
        {
          putOnce(str, paramJSONTokener.nextValue());
          switch (paramJSONTokener.nextClean())
          {
          default:
            throw paramJSONTokener.syntaxError("Expected a ',' or '}'");
            throw paramJSONTokener.syntaxError("A JSONObject text must end with '}'");
          case ',':
          case ';':
          case '}':
          }
        }
        throw paramJSONTokener.syntaxError("Expected a ':' after a key");
      case '}':
      }
    }
    while (paramJSONTokener.nextClean() != '}');
  }

  public static String doubleToString(double paramDouble)
  {
    String str;
    if ((Double.isInfinite(paramDouble)) || (Double.isNaN(paramDouble)))
      str = "null";
    do
    {
      return str;
      str = Double.toString(paramDouble);
    }
    while ((str.indexOf('.') <= 0) || (str.indexOf('e') >= 0) || (str.indexOf('E') >= 0));
    while (true)
    {
      if (!str.endsWith("0"))
      {
        if (!str.endsWith("."))
          break;
        return str.substring(0, -1 + str.length());
      }
      str = str.substring(0, -1 + str.length());
    }
  }

  public static String[] getNames(Object paramObject)
  {
    String[] arrayOfString = null;
    if (paramObject == null);
    while (true)
    {
      return arrayOfString;
      Field[] arrayOfField = paramObject.getClass().getFields();
      int i = arrayOfField.length;
      arrayOfString = null;
      if (i != 0)
      {
        arrayOfString = new String[i];
        for (int j = 0; j < i; j++)
          arrayOfString[j] = arrayOfField[j].getName();
      }
    }
  }

  public static String[] getNames(JSONObject paramJSONObject)
  {
    int i = paramJSONObject.length();
    String[] arrayOfString;
    if (i == 0)
      arrayOfString = null;
    while (true)
    {
      return arrayOfString;
      Iterator localIterator = paramJSONObject.keys();
      arrayOfString = new String[i];
      for (int j = 0; localIterator.hasNext(); j++)
        arrayOfString[j] = ((String)localIterator.next());
    }
  }

  static final void indent(Writer paramWriter, int paramInt)
    throws IOException
  {
    for (int i = 0; ; i++)
    {
      if (i >= paramInt)
        return;
      paramWriter.write(32);
    }
  }

  public static String numberToString(Number paramNumber)
    throws JSONException
  {
    if (paramNumber == null)
      throw new JSONException("Null pointer");
    testValidity(paramNumber);
    String str = paramNumber.toString();
    if ((str.indexOf('.') > 0) && (str.indexOf('e') < 0) && (str.indexOf('E') < 0));
    while (true)
    {
      if (!str.endsWith("0"))
      {
        if (str.endsWith("."))
          str = str.substring(0, -1 + str.length());
        return str;
      }
      str = str.substring(0, -1 + str.length());
    }
  }

  private void populateMap(Object paramObject)
  {
    Class localClass = paramObject.getClass();
    ClassLoader localClassLoader = localClass.getClassLoader();
    int i = 0;
    if (localClassLoader != null)
      i = 1;
    Method[] arrayOfMethod;
    int j;
    if (i != 0)
    {
      arrayOfMethod = localClass.getMethods();
      j = 0;
    }
    while (true)
    {
      if (j >= arrayOfMethod.length)
      {
        return;
        arrayOfMethod = localClass.getDeclaredMethods();
        break;
      }
      try
      {
        Method localMethod = arrayOfMethod[j];
        if (Modifier.isPublic(localMethod.getModifiers()))
        {
          String str1 = localMethod.getName();
          localObject1 = "";
          if (str1.startsWith("get"))
          {
            if ("getClass".equals(str1))
              break label288;
            if ("getDeclaringClass".equals(str1))
            {
              break label288;
              if ((((String)localObject1).length() <= 0) || (!Character.isUpperCase(((String)localObject1).charAt(0))) || (localMethod.getParameterTypes().length != 0))
                break label296;
              if (((String)localObject1).length() != 1)
                break label230;
              localObject1 = ((String)localObject1).toLowerCase();
            }
          }
          while (true)
          {
            Object localObject2 = localMethod.invoke(paramObject, null);
            if (localObject2 == null)
              break label296;
            this.map.put(localObject1, wrap(localObject2));
            break label296;
            localObject1 = str1.substring(3);
            break;
            if (!str1.startsWith("is"))
              break;
            localObject1 = str1.substring(2);
            break;
            label230: if (!Character.isUpperCase(((String)localObject1).charAt(1)))
            {
              String str2 = ((String)localObject1).substring(0, 1).toLowerCase() + ((String)localObject1).substring(1);
              localObject1 = str2;
            }
          }
        }
      }
      catch (Exception localException)
      {
        while (true)
        {
          break;
          label288: Object localObject1 = "";
        }
        label296: j++;
      }
    }
  }

  public static Writer quote(String paramString, Writer paramWriter)
    throws IOException
  {
    if ((paramString == null) || (paramString.length() == 0))
    {
      paramWriter.write("\"\"");
      return paramWriter;
    }
    int i = 0;
    int j = paramString.length();
    paramWriter.write(34);
    int k = 0;
    if (k >= j)
    {
      paramWriter.write(34);
      return paramWriter;
    }
    int m = i;
    i = paramString.charAt(k);
    switch (i)
    {
    default:
      if ((i < 32) || ((i >= 128) && (i < 160)) || ((i >= 8192) && (i < 8448)))
      {
        String str = "000" + Integer.toHexString(i);
        paramWriter.write("\\u" + str.substring(-4 + str.length()));
      }
      break;
    case 34:
    case 92:
    case 47:
    case 8:
    case 9:
    case 10:
    case 12:
    case 13:
    }
    while (true)
    {
      k++;
      break;
      paramWriter.write(92);
      paramWriter.write(i);
      continue;
      if (m == 60)
        paramWriter.write(92);
      paramWriter.write(i);
      continue;
      paramWriter.write("\\b");
      continue;
      paramWriter.write("\\t");
      continue;
      paramWriter.write("\\n");
      continue;
      paramWriter.write("\\f");
      continue;
      paramWriter.write("\\r");
      continue;
      paramWriter.write(i);
    }
  }

  public static String quote(String paramString)
  {
    StringWriter localStringWriter = new StringWriter();
    synchronized (localStringWriter.getBuffer())
    {
      try
      {
        String str = quote(paramString, localStringWriter).toString();
        return str;
      }
      catch (IOException localIOException)
      {
        return "";
      }
    }
  }

  public static Object stringToValue(String paramString)
  {
    if (paramString.equals(""));
    while (true)
    {
      return paramString;
      if (paramString.equalsIgnoreCase("true"))
        return Boolean.TRUE;
      if (paramString.equalsIgnoreCase("false"))
        return Boolean.FALSE;
      if (paramString.equalsIgnoreCase("null"))
        return NULL;
      int i = paramString.charAt(0);
      if (((i >= 48) && (i <= 57)) || (i == 46) || (i == 45) || (i == 43))
        try
        {
          if ((paramString.indexOf('.') > -1) || (paramString.indexOf('e') > -1) || (paramString.indexOf('E') > -1))
          {
            Double localDouble = Double.valueOf(paramString);
            if ((!localDouble.isInfinite()) && (!localDouble.isNaN()))
              return localDouble;
          }
          else
          {
            Long localLong = new Long(paramString);
            if (localLong.longValue() == localLong.intValue())
            {
              Integer localInteger = new Integer(localLong.intValue());
              return localInteger;
            }
            return localLong;
          }
        }
        catch (Exception localException)
        {
        }
    }
    return paramString;
  }

  public static void testValidity(Object paramObject)
    throws JSONException
  {
    if (paramObject != null)
      if ((paramObject instanceof Double))
      {
        if ((((Double)paramObject).isInfinite()) || (((Double)paramObject).isNaN()))
          throw new JSONException("JSON does not allow non-finite numbers.");
      }
      else if (((paramObject instanceof Float)) && ((((Float)paramObject).isInfinite()) || (((Float)paramObject).isNaN())))
        throw new JSONException("JSON does not allow non-finite numbers.");
  }

  public static String valueToString(Object paramObject)
    throws JSONException
  {
    if ((paramObject == null) || (paramObject.equals(null)))
      return "null";
    if ((paramObject instanceof JSONString))
    {
      String str;
      try
      {
        str = ((JSONString)paramObject).toJSONString();
        if ((str instanceof String))
          return (String)str;
      }
      catch (Exception localException)
      {
        throw new JSONException(localException);
      }
      throw new JSONException("Bad value from toJSONString: " + str);
    }
    if ((paramObject instanceof Number))
      return numberToString((Number)paramObject);
    if (((paramObject instanceof Boolean)) || ((paramObject instanceof JSONObject)) || ((paramObject instanceof JSONArray)))
      return paramObject.toString();
    if ((paramObject instanceof Map))
      return new JSONObject((Map)paramObject).toString();
    if ((paramObject instanceof Collection))
      return new JSONArray((Collection)paramObject).toString();
    if (paramObject.getClass().isArray())
      return new JSONArray(paramObject).toString();
    return quote(paramObject.toString());
  }

  public static Object wrap(Object paramObject)
  {
    if (paramObject == null);
    while (true)
    {
      try
      {
        return NULL;
        if ((!(paramObject instanceof JSONObject)) && (!(paramObject instanceof JSONArray)) && (!NULL.equals(paramObject)) && (!(paramObject instanceof JSONString)) && (!(paramObject instanceof Byte)) && (!(paramObject instanceof Character)) && (!(paramObject instanceof Short)) && (!(paramObject instanceof Integer)) && (!(paramObject instanceof Long)) && (!(paramObject instanceof Boolean)) && (!(paramObject instanceof Float)) && (!(paramObject instanceof Double)) && (!(paramObject instanceof String)) && (!(paramObject instanceof Enum)))
        {
          if ((paramObject instanceof Collection))
            return new JSONArray((Collection)paramObject);
          if (paramObject.getClass().isArray())
            return new JSONArray(paramObject);
          if ((paramObject instanceof Map))
            return new JSONObject((Map)paramObject);
          Package localPackage = paramObject.getClass().getPackage();
          if (localPackage == null)
            break label236;
          str = localPackage.getName();
          if ((str.startsWith("java.")) || (str.startsWith("javax.")) || (paramObject.getClass().getClassLoader() == null))
            return paramObject.toString();
          JSONObject localJSONObject = new JSONObject(paramObject);
          return localJSONObject;
        }
      }
      catch (Exception localException)
      {
        paramObject = null;
      }
      return paramObject;
      label236: String str = "";
    }
  }

  static final Writer writeValue(Writer paramWriter, Object paramObject, int paramInt1, int paramInt2)
    throws JSONException, IOException
  {
    if ((paramObject == null) || (paramObject.equals(null)))
    {
      paramWriter.write("null");
      return paramWriter;
    }
    if ((paramObject instanceof JSONObject))
    {
      ((JSONObject)paramObject).write(paramWriter, paramInt1, paramInt2);
      return paramWriter;
    }
    if ((paramObject instanceof JSONArray))
    {
      ((JSONArray)paramObject).write(paramWriter, paramInt1, paramInt2);
      return paramWriter;
    }
    if ((paramObject instanceof Map))
    {
      new JSONObject((Map)paramObject).write(paramWriter, paramInt1, paramInt2);
      return paramWriter;
    }
    if ((paramObject instanceof Collection))
    {
      new JSONArray((Collection)paramObject).write(paramWriter, paramInt1, paramInt2);
      return paramWriter;
    }
    if (paramObject.getClass().isArray())
    {
      new JSONArray(paramObject).write(paramWriter, paramInt1, paramInt2);
      return paramWriter;
    }
    if ((paramObject instanceof Number))
    {
      paramWriter.write(numberToString((Number)paramObject));
      return paramWriter;
    }
    if ((paramObject instanceof Boolean))
    {
      paramWriter.write(paramObject.toString());
      return paramWriter;
    }
    if ((paramObject instanceof JSONString))
      while (true)
      {
        try
        {
          String str1 = ((JSONString)paramObject).toJSONString();
          if (str1 != null)
          {
            str2 = str1.toString();
            paramWriter.write(str2);
            return paramWriter;
          }
        }
        catch (Exception localException)
        {
          throw new JSONException(localException);
        }
        String str2 = quote(paramObject.toString());
      }
    quote(paramObject.toString(), paramWriter);
    return paramWriter;
  }

  public JSONObject accumulate(String paramString, Object paramObject)
    throws JSONException
  {
    testValidity(paramObject);
    Object localObject = opt(paramString);
    if (localObject == null)
    {
      if ((paramObject instanceof JSONArray))
        paramObject = new JSONArray().put(paramObject);
      put(paramString, paramObject);
      return this;
    }
    if ((localObject instanceof JSONArray))
    {
      ((JSONArray)localObject).put(paramObject);
      return this;
    }
    put(paramString, new JSONArray().put(localObject).put(paramObject));
    return this;
  }

  public JSONObject append(String paramString, Object paramObject)
    throws JSONException
  {
    testValidity(paramObject);
    Object localObject = opt(paramString);
    if (localObject == null)
    {
      put(paramString, new JSONArray().put(paramObject));
      return this;
    }
    if ((localObject instanceof JSONArray))
    {
      put(paramString, ((JSONArray)localObject).put(paramObject));
      return this;
    }
    throw new JSONException("JSONObject[" + paramString + "] is not a JSONArray.");
  }

  public Object get(String paramString)
    throws JSONException
  {
    if (paramString == null)
      throw new JSONException("Null key.");
    Object localObject = opt(paramString);
    if (localObject == null)
      throw new JSONException("JSONObject[" + quote(paramString) + "] not found.");
    return localObject;
  }

  public boolean getBoolean(String paramString)
    throws JSONException
  {
    Object localObject = get(paramString);
    if ((localObject.equals(Boolean.FALSE)) || (((localObject instanceof String)) && (((String)localObject).equalsIgnoreCase("false"))))
      return false;
    if ((localObject.equals(Boolean.TRUE)) || (((localObject instanceof String)) && (((String)localObject).equalsIgnoreCase("true"))))
      return true;
    throw new JSONException("JSONObject[" + quote(paramString) + "] is not a Boolean.");
  }

  public double getDouble(String paramString)
    throws JSONException
  {
    Object localObject = get(paramString);
    try
    {
      if ((localObject instanceof Number))
        return ((Number)localObject).doubleValue();
      double d = Double.parseDouble((String)localObject);
      return d;
    }
    catch (Exception localException)
    {
    }
    throw new JSONException("JSONObject[" + quote(paramString) + "] is not a number.");
  }

  public int getInt(String paramString)
    throws JSONException
  {
    Object localObject = get(paramString);
    try
    {
      if ((localObject instanceof Number))
        return ((Number)localObject).intValue();
      int i = Integer.parseInt((String)localObject);
      return i;
    }
    catch (Exception localException)
    {
    }
    throw new JSONException("JSONObject[" + quote(paramString) + "] is not an int.");
  }

  public JSONArray getJSONArray(String paramString)
    throws JSONException
  {
    Object localObject = get(paramString);
    if ((localObject instanceof JSONArray))
      return (JSONArray)localObject;
    throw new JSONException("JSONObject[" + quote(paramString) + "] is not a JSONArray.");
  }

  public JSONObject getJSONObject(String paramString)
    throws JSONException
  {
    Object localObject = get(paramString);
    if ((localObject instanceof JSONObject))
      return (JSONObject)localObject;
    throw new JSONException("JSONObject[" + quote(paramString) + "] is not a JSONObject.");
  }

  public long getLong(String paramString)
    throws JSONException
  {
    Object localObject = get(paramString);
    try
    {
      if ((localObject instanceof Number))
        return ((Number)localObject).longValue();
      long l = Long.parseLong((String)localObject);
      return l;
    }
    catch (Exception localException)
    {
    }
    throw new JSONException("JSONObject[" + quote(paramString) + "] is not a long.");
  }

  public String getString(String paramString)
    throws JSONException
  {
    Object localObject = get(paramString);
    if ((localObject instanceof String))
      return (String)localObject;
    throw new JSONException("JSONObject[" + quote(paramString) + "] not a string.");
  }

  public boolean has(String paramString)
  {
    return this.map.containsKey(paramString);
  }

  public JSONObject increment(String paramString)
    throws JSONException
  {
    Object localObject = opt(paramString);
    if (localObject == null)
    {
      put(paramString, 1);
      return this;
    }
    if ((localObject instanceof Integer))
    {
      put(paramString, 1 + ((Integer)localObject).intValue());
      return this;
    }
    if ((localObject instanceof Long))
    {
      put(paramString, 1L + ((Long)localObject).longValue());
      return this;
    }
    if ((localObject instanceof Double))
    {
      put(paramString, 1.0D + ((Double)localObject).doubleValue());
      return this;
    }
    if ((localObject instanceof Float))
    {
      put(paramString, 1.0F + ((Float)localObject).floatValue());
      return this;
    }
    throw new JSONException("Unable to increment [" + quote(paramString) + "].");
  }

  public boolean isNull(String paramString)
  {
    return NULL.equals(opt(paramString));
  }

  public Iterator keys()
  {
    return this.map.keySet().iterator();
  }

  public int length()
  {
    return this.map.size();
  }

  public JSONArray names()
  {
    JSONArray localJSONArray = new JSONArray();
    Iterator localIterator = keys();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if (localJSONArray.length() == 0)
          localJSONArray = null;
        return localJSONArray;
      }
      localJSONArray.put(localIterator.next());
    }
  }

  public Object opt(String paramString)
  {
    if (paramString == null)
      return null;
    return this.map.get(paramString);
  }

  public boolean optBoolean(String paramString)
  {
    return optBoolean(paramString, false);
  }

  public boolean optBoolean(String paramString, boolean paramBoolean)
  {
    try
    {
      boolean bool = getBoolean(paramString);
      return bool;
    }
    catch (Exception localException)
    {
    }
    return paramBoolean;
  }

  public double optDouble(String paramString)
  {
    return optDouble(paramString, (0.0D / 0.0D));
  }

  public double optDouble(String paramString, double paramDouble)
  {
    try
    {
      double d = getDouble(paramString);
      return d;
    }
    catch (Exception localException)
    {
    }
    return paramDouble;
  }

  public int optInt(String paramString)
  {
    return optInt(paramString, 0);
  }

  public int optInt(String paramString, int paramInt)
  {
    try
    {
      int i = getInt(paramString);
      return i;
    }
    catch (Exception localException)
    {
    }
    return paramInt;
  }

  public JSONArray optJSONArray(String paramString)
  {
    Object localObject = opt(paramString);
    if ((localObject instanceof JSONArray))
      return (JSONArray)localObject;
    return null;
  }

  public JSONObject optJSONObject(String paramString)
  {
    Object localObject = opt(paramString);
    if ((localObject instanceof JSONObject))
      return (JSONObject)localObject;
    return null;
  }

  public long optLong(String paramString)
  {
    return optLong(paramString, 0L);
  }

  public long optLong(String paramString, long paramLong)
  {
    try
    {
      long l = getLong(paramString);
      return l;
    }
    catch (Exception localException)
    {
    }
    return paramLong;
  }

  public String optString(String paramString)
  {
    return optString(paramString, "");
  }

  public String optString(String paramString1, String paramString2)
  {
    Object localObject = opt(paramString1);
    if (NULL.equals(localObject))
      return paramString2;
    return localObject.toString();
  }

  public JSONObject put(String paramString, double paramDouble)
    throws JSONException
  {
    put(paramString, new Double(paramDouble));
    return this;
  }

  public JSONObject put(String paramString, int paramInt)
    throws JSONException
  {
    put(paramString, new Integer(paramInt));
    return this;
  }

  public JSONObject put(String paramString, long paramLong)
    throws JSONException
  {
    put(paramString, new Long(paramLong));
    return this;
  }

  public JSONObject put(String paramString, Object paramObject)
    throws JSONException
  {
    if (paramString == null)
      throw new JSONException("Null key.");
    if (paramObject != null)
    {
      testValidity(paramObject);
      this.map.put(paramString, paramObject);
      return this;
    }
    remove(paramString);
    return this;
  }

  public JSONObject put(String paramString, Collection paramCollection)
    throws JSONException
  {
    put(paramString, new JSONArray(paramCollection));
    return this;
  }

  public JSONObject put(String paramString, Map paramMap)
    throws JSONException
  {
    put(paramString, new JSONObject(paramMap));
    return this;
  }

  public JSONObject put(String paramString, boolean paramBoolean)
    throws JSONException
  {
    if (paramBoolean);
    for (Boolean localBoolean = Boolean.TRUE; ; localBoolean = Boolean.FALSE)
    {
      put(paramString, localBoolean);
      return this;
    }
  }

  public JSONObject putOnce(String paramString, Object paramObject)
    throws JSONException
  {
    if ((paramString != null) && (paramObject != null))
    {
      if (opt(paramString) != null)
        throw new JSONException("Duplicate key \"" + paramString + "\"");
      put(paramString, paramObject);
    }
    return this;
  }

  public JSONObject putOpt(String paramString, Object paramObject)
    throws JSONException
  {
    if ((paramString != null) && (paramObject != null))
      put(paramString, paramObject);
    return this;
  }

  public Object remove(String paramString)
  {
    return this.map.remove(paramString);
  }

  public JSONArray toJSONArray(JSONArray paramJSONArray)
    throws JSONException
  {
    JSONArray localJSONArray;
    if ((paramJSONArray == null) || (paramJSONArray.length() == 0))
      localJSONArray = null;
    while (true)
    {
      return localJSONArray;
      localJSONArray = new JSONArray();
      for (int i = 0; i < paramJSONArray.length(); i++)
        localJSONArray.put(opt(paramJSONArray.getString(i)));
    }
  }

  public String toString()
  {
    try
    {
      String str = toString(0);
      return str;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public String toString(int paramInt)
    throws JSONException
  {
    StringWriter localStringWriter = new StringWriter();
    synchronized (localStringWriter.getBuffer())
    {
      String str = write(localStringWriter, paramInt, 0).toString();
      return str;
    }
  }

  public Writer write(Writer paramWriter)
    throws JSONException
  {
    return write(paramWriter, 0, 0);
  }

  Writer write(Writer paramWriter, int paramInt1, int paramInt2)
    throws JSONException
  {
    while (true)
    {
      int i;
      Iterator localIterator;
      try
      {
        i = length();
        localIterator = keys();
        paramWriter.write(123);
        if (i != 1)
          break label216;
        Object localObject1 = localIterator.next();
        paramWriter.write(quote(localObject1.toString()));
        paramWriter.write(58);
        if (paramInt1 > 0)
          paramWriter.write(32);
        writeValue(paramWriter, this.map.get(localObject1), paramInt1, paramInt2);
        paramWriter.write(125);
        return paramWriter;
        if (!localIterator.hasNext())
        {
          if (paramInt1 > 0)
            paramWriter.write(10);
          indent(paramWriter, paramInt2);
          continue;
        }
      }
      catch (IOException localIOException)
      {
        throw new JSONException(localIOException);
      }
      Object localObject2 = localIterator.next();
      if (k != 0)
        paramWriter.write(44);
      if (paramInt1 > 0)
        paramWriter.write(10);
      int j;
      indent(paramWriter, j);
      paramWriter.write(quote(localObject2.toString()));
      paramWriter.write(58);
      if (paramInt1 > 0)
        paramWriter.write(32);
      writeValue(paramWriter, this.map.get(localObject2), paramInt1, j);
      int k = 1;
      continue;
      label216: if (i != 0)
      {
        j = paramInt2 + paramInt1;
        k = 0;
      }
    }
  }

  private static final class Null
  {
    protected final Object clone()
    {
      return this;
    }

    public boolean equals(Object paramObject)
    {
      return (paramObject == null) || (paramObject == this);
    }

    public String toString()
    {
      return "null";
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.org.json.JSONObject
 * JD-Core Version:    0.6.2
 */