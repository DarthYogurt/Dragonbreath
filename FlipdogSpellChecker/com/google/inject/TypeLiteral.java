package com.google.inject;

import com.google.inject.internal.ImmutableList;
import com.google.inject.internal.MoreTypes;
import com.google.inject.internal.Preconditions;
import com.google.inject.util.Types;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;

public class TypeLiteral<T>
{
  final int hashCode;
  final Class<? super T> rawType;
  final Type type;

  protected TypeLiteral()
  {
    this.type = getSuperclassTypeParameter(getClass());
    this.rawType = MoreTypes.getRawType(this.type);
    this.hashCode = MoreTypes.hashCode(this.type);
  }

  TypeLiteral(Type paramType)
  {
    this.type = MoreTypes.canonicalize((Type)Preconditions.checkNotNull(paramType, "type"));
    this.rawType = MoreTypes.getRawType(this.type);
    this.hashCode = MoreTypes.hashCode(this.type);
  }

  static TypeLiteral<?> fromSuperclassTypeParameter(Class<?> paramClass)
  {
    return new TypeLiteral(getSuperclassTypeParameter(paramClass));
  }

  public static <T> TypeLiteral<T> get(Class<T> paramClass)
  {
    return new TypeLiteral(paramClass);
  }

  public static TypeLiteral<?> get(Type paramType)
  {
    return new TypeLiteral(paramType);
  }

  static Type getSuperclassTypeParameter(Class<?> paramClass)
  {
    Type localType = paramClass.getGenericSuperclass();
    if ((localType instanceof Class))
      throw new RuntimeException("Missing type parameter.");
    return MoreTypes.canonicalize(((ParameterizedType)localType).getActualTypeArguments()[0]);
  }

  private List<TypeLiteral<?>> resolveAll(Type[] paramArrayOfType)
  {
    TypeLiteral[] arrayOfTypeLiteral = new TypeLiteral[paramArrayOfType.length];
    for (int i = 0; i < paramArrayOfType.length; i++)
      arrayOfTypeLiteral[i] = resolve(paramArrayOfType[i]);
    return ImmutableList.of(arrayOfTypeLiteral);
  }

  public final boolean equals(Object paramObject)
  {
    return ((paramObject instanceof TypeLiteral)) && (MoreTypes.equals(this.type, ((TypeLiteral)paramObject).type));
  }

  public List<TypeLiteral<?>> getExceptionTypes(Member paramMember)
  {
    Method localMethod;
    if ((paramMember instanceof Method))
    {
      localMethod = (Method)paramMember;
      boolean bool2 = localMethod.getDeclaringClass().isAssignableFrom(this.rawType);
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = localMethod;
      arrayOfObject2[1] = this.type;
      Preconditions.checkArgument(bool2, "%s is not defined by a supertype of %s", arrayOfObject2);
    }
    Constructor localConstructor;
    for (Type[] arrayOfType = localMethod.getGenericExceptionTypes(); ; arrayOfType = localConstructor.getGenericExceptionTypes())
    {
      return resolveAll(arrayOfType);
      if (!(paramMember instanceof Constructor))
        break;
      localConstructor = (Constructor)paramMember;
      boolean bool1 = localConstructor.getDeclaringClass().isAssignableFrom(this.rawType);
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = localConstructor;
      arrayOfObject1[1] = this.type;
      Preconditions.checkArgument(bool1, "%s does not construct a supertype of %s", arrayOfObject1);
    }
    throw new IllegalArgumentException("Not a method or a constructor: " + paramMember);
  }

  public TypeLiteral<?> getFieldType(Field paramField)
  {
    boolean bool = paramField.getDeclaringClass().isAssignableFrom(this.rawType);
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramField;
    arrayOfObject[1] = this.type;
    Preconditions.checkArgument(bool, "%s is not defined by a supertype of %s", arrayOfObject);
    return resolve(paramField.getGenericType());
  }

  public List<TypeLiteral<?>> getParameterTypes(Member paramMember)
  {
    Method localMethod;
    if ((paramMember instanceof Method))
    {
      localMethod = (Method)paramMember;
      boolean bool2 = localMethod.getDeclaringClass().isAssignableFrom(this.rawType);
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = localMethod;
      arrayOfObject2[1] = this.type;
      Preconditions.checkArgument(bool2, "%s is not defined by a supertype of %s", arrayOfObject2);
    }
    Constructor localConstructor;
    for (Type[] arrayOfType = localMethod.getGenericParameterTypes(); ; arrayOfType = localConstructor.getGenericParameterTypes())
    {
      return resolveAll(arrayOfType);
      if (!(paramMember instanceof Constructor))
        break;
      localConstructor = (Constructor)paramMember;
      boolean bool1 = localConstructor.getDeclaringClass().isAssignableFrom(this.rawType);
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = localConstructor;
      arrayOfObject1[1] = this.type;
      Preconditions.checkArgument(bool1, "%s does not construct a supertype of %s", arrayOfObject1);
    }
    throw new IllegalArgumentException("Not a method or a constructor: " + paramMember);
  }

  public final Class<? super T> getRawType()
  {
    return this.rawType;
  }

  public TypeLiteral<?> getReturnType(Method paramMethod)
  {
    boolean bool = paramMethod.getDeclaringClass().isAssignableFrom(this.rawType);
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramMethod;
    arrayOfObject[1] = this.type;
    Preconditions.checkArgument(bool, "%s is not defined by a supertype of %s", arrayOfObject);
    return resolve(paramMethod.getGenericReturnType());
  }

  public TypeLiteral<?> getSupertype(Class<?> paramClass)
  {
    boolean bool = paramClass.isAssignableFrom(this.rawType);
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramClass;
    arrayOfObject[1] = this.type;
    Preconditions.checkArgument(bool, "%s is not a supertype of %s", arrayOfObject);
    return resolve(MoreTypes.getGenericSupertype(this.type, this.rawType, paramClass));
  }

  public final Type getType()
  {
    return this.type;
  }

  public final int hashCode()
  {
    return this.hashCode;
  }

  final TypeLiteral<Provider<T>> providerType()
  {
    return get(Types.providerOf(getType()));
  }

  TypeLiteral<?> resolve(Type paramType)
  {
    return get(resolveType(paramType));
  }

  Type resolveType(Type paramType)
  {
    Object localObject;
    while ((paramType instanceof TypeVariable))
    {
      TypeVariable localTypeVariable = (TypeVariable)paramType;
      paramType = MoreTypes.resolveTypeVariable(this.type, this.rawType, localTypeVariable);
      if (paramType == localTypeVariable)
        localObject = paramType;
    }
    Type[] arrayOfType2;
    Type localType1;
    do
    {
      do
      {
        Type[] arrayOfType1;
        Type localType2;
        do
        {
          Type localType4;
          int i;
          Type[] arrayOfType3;
          do
          {
            Type localType6;
            Type localType7;
            do
            {
              return localObject;
              if (!(paramType instanceof GenericArrayType))
                break;
              localObject = (GenericArrayType)paramType;
              localType6 = ((GenericArrayType)localObject).getGenericComponentType();
              localType7 = resolveType(localType6);
            }
            while (localType6 == localType7);
            return Types.arrayOf(localType7);
            if (!(paramType instanceof ParameterizedType))
              break;
            localObject = (ParameterizedType)paramType;
            Type localType3 = ((ParameterizedType)localObject).getOwnerType();
            localType4 = resolveType(localType3);
            if (localType4 != localType3);
            for (i = 1; ; i = 0)
            {
              arrayOfType3 = ((ParameterizedType)localObject).getActualTypeArguments();
              int j = 0;
              int k = arrayOfType3.length;
              while (j < k)
              {
                Type localType5 = resolveType(arrayOfType3[j]);
                if (localType5 != arrayOfType3[j])
                {
                  if (i == 0)
                  {
                    arrayOfType3 = (Type[])arrayOfType3.clone();
                    i = 1;
                  }
                  arrayOfType3[j] = localType5;
                }
                j++;
              }
            }
          }
          while (i == 0);
          return Types.newParameterizedTypeWithOwner(localType4, ((ParameterizedType)localObject).getRawType(), arrayOfType3);
          if (!(paramType instanceof WildcardType))
            break label304;
          localObject = (WildcardType)paramType;
          arrayOfType1 = ((WildcardType)localObject).getLowerBounds();
          arrayOfType2 = ((WildcardType)localObject).getUpperBounds();
          if (arrayOfType1.length != 1)
            break;
          localType2 = resolveType(arrayOfType1[0]);
        }
        while (localType2 == arrayOfType1[0]);
        return Types.supertypeOf(localType2);
      }
      while (arrayOfType2.length != 1);
      localType1 = resolveType(arrayOfType2[0]);
    }
    while (localType1 == arrayOfType2[0]);
    return Types.subtypeOf(localType1);
    label304: return paramType;
  }

  public final String toString()
  {
    return MoreTypes.toString(this.type);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.TypeLiteral
 * JD-Core Version:    0.6.2
 */