package com.millennialmedia.google.gson;

import com.millennialmedia.google.gson.stream.JsonReader;
import com.millennialmedia.google.gson.stream.JsonToken;
import com.millennialmedia.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public final class JsonParser
{
  // ERROR //
  public JsonElement parse(JsonReader paramJsonReader)
    throws JsonIOException, JsonSyntaxException
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 24	com/millennialmedia/google/gson/stream/JsonReader:isLenient	()Z
    //   4: istore_2
    //   5: aload_1
    //   6: iconst_1
    //   7: invokevirtual 28	com/millennialmedia/google/gson/stream/JsonReader:setLenient	(Z)V
    //   10: aload_1
    //   11: invokestatic 32	com/millennialmedia/google/gson/internal/Streams:parse	(Lcom/millennialmedia/google/gson/stream/JsonReader;)Lcom/millennialmedia/google/gson/JsonElement;
    //   14: astore 6
    //   16: aload_1
    //   17: iload_2
    //   18: invokevirtual 28	com/millennialmedia/google/gson/stream/JsonReader:setLenient	(Z)V
    //   21: aload 6
    //   23: areturn
    //   24: astore 5
    //   26: new 34	com/millennialmedia/google/gson/JsonParseException
    //   29: dup
    //   30: new 36	java/lang/StringBuilder
    //   33: dup
    //   34: invokespecial 37	java/lang/StringBuilder:<init>	()V
    //   37: ldc 39
    //   39: invokevirtual 43	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: aload_1
    //   43: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   46: ldc 48
    //   48: invokevirtual 43	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: invokevirtual 52	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   54: aload 5
    //   56: invokespecial 55	com/millennialmedia/google/gson/JsonParseException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   59: athrow
    //   60: astore 4
    //   62: aload_1
    //   63: iload_2
    //   64: invokevirtual 28	com/millennialmedia/google/gson/stream/JsonReader:setLenient	(Z)V
    //   67: aload 4
    //   69: athrow
    //   70: astore_3
    //   71: new 34	com/millennialmedia/google/gson/JsonParseException
    //   74: dup
    //   75: new 36	java/lang/StringBuilder
    //   78: dup
    //   79: invokespecial 37	java/lang/StringBuilder:<init>	()V
    //   82: ldc 39
    //   84: invokevirtual 43	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: aload_1
    //   88: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   91: ldc 48
    //   93: invokevirtual 43	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: invokevirtual 52	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   99: aload_3
    //   100: invokespecial 55	com/millennialmedia/google/gson/JsonParseException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   103: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   10	16	24	java/lang/StackOverflowError
    //   10	16	60	finally
    //   26	60	60	finally
    //   71	104	60	finally
    //   10	16	70	java/lang/OutOfMemoryError
  }

  public JsonElement parse(Reader paramReader)
    throws JsonIOException, JsonSyntaxException
  {
    JsonElement localJsonElement;
    try
    {
      JsonReader localJsonReader = new JsonReader(paramReader);
      localJsonElement = parse(localJsonReader);
      if ((!localJsonElement.isJsonNull()) && (localJsonReader.peek() != JsonToken.END_DOCUMENT))
        throw new JsonSyntaxException("Did not consume the entire document.");
    }
    catch (MalformedJsonException localMalformedJsonException)
    {
      throw new JsonSyntaxException(localMalformedJsonException);
    }
    catch (IOException localIOException)
    {
      throw new JsonIOException(localIOException);
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw new JsonSyntaxException(localNumberFormatException);
    }
    return localJsonElement;
  }

  public JsonElement parse(String paramString)
    throws JsonSyntaxException
  {
    return parse(new StringReader(paramString));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.google.gson.JsonParser
 * JD-Core Version:    0.6.2
 */