package com.millennialmedia.google.gson.internal.bind;

import com.millennialmedia.google.gson.Gson;
import com.millennialmedia.google.gson.TypeAdapter;
import com.millennialmedia.google.gson.TypeAdapterFactory;
import com.millennialmedia.google.gson.internal..Gson.Types;
import com.millennialmedia.google.gson.internal.ConstructorConstructor;
import com.millennialmedia.google.gson.internal.ObjectConstructor;
import com.millennialmedia.google.gson.reflect.TypeToken;
import com.millennialmedia.google.gson.stream.JsonReader;
import com.millennialmedia.google.gson.stream.JsonToken;
import com.millennialmedia.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

public final class CollectionTypeAdapterFactory
  implements TypeAdapterFactory
{
  private final ConstructorConstructor constructorConstructor;

  public CollectionTypeAdapterFactory(ConstructorConstructor paramConstructorConstructor)
  {
    this.constructorConstructor = paramConstructorConstructor;
  }

  public <T> TypeAdapter<T> create(Gson paramGson, TypeToken<T> paramTypeToken)
  {
    Type localType1 = paramTypeToken.getType();
    Class localClass = paramTypeToken.getRawType();
    if (!Collection.class.isAssignableFrom(localClass))
      return null;
    Type localType2 = .Gson.Types.getCollectionElementType(localType1, localClass);
    return new Adapter(paramGson, localType2, paramGson.getAdapter(TypeToken.get(localType2)), this.constructorConstructor.get(paramTypeToken));
  }

  private static final class Adapter<E> extends TypeAdapter<Collection<E>>
  {
    private final ObjectConstructor<? extends Collection<E>> constructor;
    private final TypeAdapter<E> elementTypeAdapter;

    public Adapter(Gson paramGson, Type paramType, TypeAdapter<E> paramTypeAdapter, ObjectConstructor<? extends Collection<E>> paramObjectConstructor)
    {
      this.elementTypeAdapter = new TypeAdapterRuntimeTypeWrapper(paramGson, paramTypeAdapter, paramType);
      this.constructor = paramObjectConstructor;
    }

    public Collection<E> read(JsonReader paramJsonReader)
      throws IOException
    {
      if (paramJsonReader.peek() == JsonToken.NULL)
      {
        paramJsonReader.nextNull();
        return null;
      }
      Collection localCollection = (Collection)this.constructor.construct();
      paramJsonReader.beginArray();
      while (paramJsonReader.hasNext())
        localCollection.add(this.elementTypeAdapter.read(paramJsonReader));
      paramJsonReader.endArray();
      return localCollection;
    }

    public void write(JsonWriter paramJsonWriter, Collection<E> paramCollection)
      throws IOException
    {
      if (paramCollection == null)
      {
        paramJsonWriter.nullValue();
        return;
      }
      paramJsonWriter.beginArray();
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        this.elementTypeAdapter.write(paramJsonWriter, localObject);
      }
      paramJsonWriter.endArray();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.google.gson.internal.bind.CollectionTypeAdapterFactory
 * JD-Core Version:    0.6.2
 */