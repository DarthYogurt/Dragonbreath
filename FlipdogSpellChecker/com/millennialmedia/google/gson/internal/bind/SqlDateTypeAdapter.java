package com.millennialmedia.google.gson.internal.bind;

import com.millennialmedia.google.gson.Gson;
import com.millennialmedia.google.gson.JsonSyntaxException;
import com.millennialmedia.google.gson.TypeAdapter;
import com.millennialmedia.google.gson.TypeAdapterFactory;
import com.millennialmedia.google.gson.reflect.TypeToken;
import com.millennialmedia.google.gson.stream.JsonReader;
import com.millennialmedia.google.gson.stream.JsonToken;
import com.millennialmedia.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class SqlDateTypeAdapter extends TypeAdapter<java.sql.Date>
{
  public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory()
  {
    public <T> TypeAdapter<T> create(Gson paramAnonymousGson, TypeToken<T> paramAnonymousTypeToken)
    {
      if (paramAnonymousTypeToken.getRawType() == java.sql.Date.class)
        return new SqlDateTypeAdapter();
      return null;
    }
  };
  private final DateFormat format = new SimpleDateFormat("MMM d, yyyy");

  public java.sql.Date read(JsonReader paramJsonReader)
    throws IOException
  {
    try
    {
      java.sql.Date localDate;
      if (paramJsonReader.peek() == JsonToken.NULL)
      {
        paramJsonReader.nextNull();
        localDate = null;
      }
      while (true)
      {
        return localDate;
        try
        {
          localDate = new java.sql.Date(this.format.parse(paramJsonReader.nextString()).getTime());
        }
        catch (ParseException localParseException)
        {
          throw new JsonSyntaxException(localParseException);
        }
      }
    }
    finally
    {
    }
  }

  public void write(JsonWriter paramJsonWriter, java.sql.Date paramDate)
    throws IOException
  {
    Object localObject2;
    if (paramDate == null)
      localObject2 = null;
    try
    {
      while (true)
      {
        paramJsonWriter.value((String)localObject2);
        return;
        String str = this.format.format(paramDate);
        localObject2 = str;
      }
    }
    finally
    {
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.google.gson.internal.bind.SqlDateTypeAdapter
 * JD-Core Version:    0.6.2
 */