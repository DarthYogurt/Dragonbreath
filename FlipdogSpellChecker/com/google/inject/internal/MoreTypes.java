package com.google.inject.internal;

import com.google.inject.ConfigurationException;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.Message;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;

public class MoreTypes
{
  public static final Type[] EMPTY_TYPE_ARRAY = new Type[0];
  private static final Map<TypeLiteral<?>, TypeLiteral<?>> PRIMITIVE_TO_WRAPPER = new ImmutableMap.Builder().put(TypeLiteral.get(Boolean.TYPE), TypeLiteral.get(Boolean.class)).put(TypeLiteral.get(Byte.TYPE), TypeLiteral.get(Byte.class)).put(TypeLiteral.get(Short.TYPE), TypeLiteral.get(Short.class)).put(TypeLiteral.get(Integer.TYPE), TypeLiteral.get(Integer.class)).put(TypeLiteral.get(Long.TYPE), TypeLiteral.get(Long.class)).put(TypeLiteral.get(Float.TYPE), TypeLiteral.get(Float.class)).put(TypeLiteral.get(Double.TYPE), TypeLiteral.get(Double.class)).put(TypeLiteral.get(Character.TYPE), TypeLiteral.get(Character.class)).put(TypeLiteral.get(Void.TYPE), TypeLiteral.get(Void.class)).build();

  public static Type canonicalize(Type paramType)
  {
    if (((paramType instanceof ParameterizedTypeImpl)) || ((paramType instanceof GenericArrayTypeImpl)) || ((paramType instanceof WildcardTypeImpl)));
    do
    {
      return paramType;
      if ((paramType instanceof ParameterizedType))
      {
        ParameterizedType localParameterizedType = (ParameterizedType)paramType;
        return new ParameterizedTypeImpl(localParameterizedType.getOwnerType(), localParameterizedType.getRawType(), localParameterizedType.getActualTypeArguments());
      }
      if ((paramType instanceof GenericArrayType))
        return new GenericArrayTypeImpl(((GenericArrayType)paramType).getGenericComponentType());
      if (((paramType instanceof Class)) && (((Class)paramType).isArray()))
        return new GenericArrayTypeImpl(((Class)paramType).getComponentType());
    }
    while (!(paramType instanceof WildcardType));
    WildcardType localWildcardType = (WildcardType)paramType;
    return new WildcardTypeImpl(localWildcardType.getUpperBounds(), localWildcardType.getLowerBounds());
  }

  private static void checkNotPrimitive(Type paramType, String paramString)
  {
    if ((!(paramType instanceof Class)) || (!((Class)paramType).isPrimitive()));
    for (boolean bool = true; ; bool = false)
    {
      Preconditions.checkArgument(bool, "Primitive types are not allowed in %s: %s", new Object[] { paramString, paramType });
      return;
    }
  }

  private static Class<?> declaringClassOf(TypeVariable paramTypeVariable)
  {
    GenericDeclaration localGenericDeclaration = paramTypeVariable.getGenericDeclaration();
    if ((localGenericDeclaration instanceof Class))
      return (Class)localGenericDeclaration;
    return null;
  }

  public static boolean equals(Type paramType1, Type paramType2)
  {
    boolean bool1 = true;
    boolean bool3;
    if (paramType1 == paramType2)
      bool3 = bool1;
    boolean bool4;
    do
    {
      boolean bool2;
      do
      {
        boolean bool5;
        do
        {
          boolean bool6;
          do
          {
            boolean bool7;
            do
            {
              return bool3;
              if ((paramType1 instanceof Class))
                return paramType1.equals(paramType2);
              if (!(paramType1 instanceof ParameterizedType))
                break;
              bool7 = paramType2 instanceof ParameterizedType;
              bool3 = false;
            }
            while (!bool7);
            ParameterizedType localParameterizedType1 = (ParameterizedType)paramType1;
            ParameterizedType localParameterizedType2 = (ParameterizedType)paramType2;
            if ((Objects.equal(localParameterizedType1.getOwnerType(), localParameterizedType2.getOwnerType())) && (localParameterizedType1.getRawType().equals(localParameterizedType2.getRawType())) && (Arrays.equals(localParameterizedType1.getActualTypeArguments(), localParameterizedType2.getActualTypeArguments())));
            while (true)
            {
              return bool1;
              bool1 = false;
            }
            if (!(paramType1 instanceof GenericArrayType))
              break;
            bool6 = paramType2 instanceof GenericArrayType;
            bool3 = false;
          }
          while (!bool6);
          GenericArrayType localGenericArrayType1 = (GenericArrayType)paramType1;
          GenericArrayType localGenericArrayType2 = (GenericArrayType)paramType2;
          return equals(localGenericArrayType1.getGenericComponentType(), localGenericArrayType2.getGenericComponentType());
          if (!(paramType1 instanceof WildcardType))
            break;
          bool5 = paramType2 instanceof WildcardType;
          bool3 = false;
        }
        while (!bool5);
        WildcardType localWildcardType1 = (WildcardType)paramType1;
        WildcardType localWildcardType2 = (WildcardType)paramType2;
        if ((Arrays.equals(localWildcardType1.getUpperBounds(), localWildcardType2.getUpperBounds())) && (Arrays.equals(localWildcardType1.getLowerBounds(), localWildcardType2.getLowerBounds())));
        while (true)
        {
          return bool1;
          bool1 = false;
        }
        bool2 = paramType1 instanceof TypeVariable;
        bool3 = false;
      }
      while (!bool2);
      bool4 = paramType2 instanceof TypeVariable;
      bool3 = false;
    }
    while (!bool4);
    TypeVariable localTypeVariable1 = (TypeVariable)paramType1;
    TypeVariable localTypeVariable2 = (TypeVariable)paramType2;
    if ((localTypeVariable1.getGenericDeclaration() == localTypeVariable2.getGenericDeclaration()) && (localTypeVariable1.getName().equals(localTypeVariable2.getName())));
    while (true)
    {
      return bool1;
      bool1 = false;
    }
  }

  public static Type getGenericSupertype(Type paramType, Class<?> paramClass1, Class<?> paramClass2)
  {
    if (paramClass2 == paramClass1)
      return paramType;
    if (paramClass2.isInterface())
    {
      Class[] arrayOfClass = paramClass1.getInterfaces();
      int i = 0;
      int j = arrayOfClass.length;
      while (i < j)
      {
        if (arrayOfClass[i] == paramClass2)
          return paramClass1.getGenericInterfaces()[i];
        if (paramClass2.isAssignableFrom(arrayOfClass[i]))
          return getGenericSupertype(paramClass1.getGenericInterfaces()[i], arrayOfClass[i], paramClass2);
        i++;
      }
    }
    if (!paramClass1.isInterface())
      while (paramClass1 != Object.class)
      {
        Class localClass = paramClass1.getSuperclass();
        if (localClass == paramClass2)
          return paramClass1.getGenericSuperclass();
        if (paramClass2.isAssignableFrom(localClass))
          return getGenericSupertype(paramClass1.getGenericSuperclass(), localClass, paramClass2);
        paramClass1 = localClass;
      }
    return paramClass2;
  }

  public static Class<?> getRawType(Type paramType)
  {
    if ((paramType instanceof Class))
      return (Class)paramType;
    if ((paramType instanceof ParameterizedType))
    {
      Type localType = ((ParameterizedType)paramType).getRawType();
      boolean bool = localType instanceof Class;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = paramType;
      arrayOfObject[1] = paramType.getClass().getName();
      Preconditions.checkArgument(bool, "Expected a Class, but <%s> is of type %s", arrayOfObject);
      return (Class)localType;
    }
    if ((paramType instanceof GenericArrayType))
      return [Ljava.lang.Object.class;
    if ((paramType instanceof TypeVariable))
      return Object.class;
    throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + paramType + "> is of type " + paramType.getClass().getName());
  }

  public static int hashCode(Type paramType)
  {
    if ((paramType instanceof Class))
      return paramType.hashCode();
    if ((paramType instanceof ParameterizedType))
    {
      ParameterizedType localParameterizedType = (ParameterizedType)paramType;
      return Arrays.hashCode(localParameterizedType.getActualTypeArguments()) ^ localParameterizedType.getRawType().hashCode() ^ hashCodeOrZero(localParameterizedType.getOwnerType());
    }
    if ((paramType instanceof GenericArrayType))
      return hashCode(((GenericArrayType)paramType).getGenericComponentType());
    if ((paramType instanceof WildcardType))
    {
      WildcardType localWildcardType = (WildcardType)paramType;
      return Arrays.hashCode(localWildcardType.getLowerBounds()) ^ Arrays.hashCode(localWildcardType.getUpperBounds());
    }
    return hashCodeOrZero(paramType);
  }

  private static int hashCodeOrZero(Object paramObject)
  {
    if (paramObject != null)
      return paramObject.hashCode();
    return 0;
  }

  private static int indexOf(Object[] paramArrayOfObject, Object paramObject)
  {
    for (int i = 0; i < paramArrayOfObject.length; i++)
      if (paramObject.equals(paramArrayOfObject[i]))
        return i;
    throw new NoSuchElementException();
  }

  private static boolean isFullySpecified(Type paramType)
  {
    if ((paramType instanceof Class))
      return true;
    if ((paramType instanceof CompositeType))
      return ((CompositeType)paramType).isFullySpecified();
    if ((paramType instanceof TypeVariable))
      return false;
    return ((CompositeType)canonicalize(paramType)).isFullySpecified();
  }

  public static <T> TypeLiteral<T> makeKeySafe(TypeLiteral<T> paramTypeLiteral)
  {
    if (!isFullySpecified(paramTypeLiteral.getType()))
      throw new ConfigurationException(ImmutableSet.of(new Message(paramTypeLiteral + " cannot be used as a key; It is not fully specified.")));
    TypeLiteral localTypeLiteral = (TypeLiteral)PRIMITIVE_TO_WRAPPER.get(paramTypeLiteral);
    if (localTypeLiteral != null)
      return localTypeLiteral;
    return paramTypeLiteral;
  }

  public static String memberKey(Member paramMember)
  {
    Preconditions.checkNotNull(paramMember, "member");
    return "<NO_MEMBER_KEY>";
  }

  public static Class<? extends Member> memberType(Member paramMember)
  {
    Preconditions.checkNotNull(paramMember, "member");
    if ((paramMember instanceof MemberImpl))
      return ((MemberImpl)paramMember).memberType;
    if ((paramMember instanceof Field))
      return Field.class;
    if ((paramMember instanceof Method))
      return Method.class;
    if ((paramMember instanceof Constructor))
      return Constructor.class;
    throw new IllegalArgumentException("Unsupported implementation class for Member, " + paramMember.getClass());
  }

  public static Type resolveTypeVariable(Type paramType, Class<?> paramClass, TypeVariable paramTypeVariable)
  {
    Class localClass = declaringClassOf(paramTypeVariable);
    if (localClass == null);
    Type localType;
    do
    {
      return paramTypeVariable;
      localType = getGenericSupertype(paramType, paramClass, localClass);
    }
    while (!(localType instanceof ParameterizedType));
    int i = indexOf(localClass.getTypeParameters(), paramTypeVariable);
    return ((ParameterizedType)localType).getActualTypeArguments()[i];
  }

  public static Member serializableCopy(Member paramMember)
  {
    if ((paramMember instanceof MemberImpl))
      return paramMember;
    return new MemberImpl(paramMember, null);
  }

  public static String toString(Member paramMember)
  {
    Class localClass = memberType(paramMember);
    if (localClass == Method.class)
      return paramMember.getDeclaringClass().getName() + "." + paramMember.getName() + "()";
    if (localClass == Field.class)
      return paramMember.getDeclaringClass().getName() + "." + paramMember.getName();
    if (localClass == Constructor.class)
      return paramMember.getDeclaringClass().getName() + ".<init>()";
    throw new AssertionError();
  }

  public static String toString(Type paramType)
  {
    if ((paramType instanceof Class))
      return ((Class)paramType).getName();
    if ((paramType instanceof ParameterizedType))
    {
      ParameterizedType localParameterizedType = (ParameterizedType)paramType;
      Type[] arrayOfType3 = localParameterizedType.getActualTypeArguments();
      Type localType = localParameterizedType.getOwnerType();
      StringBuilder localStringBuilder = new StringBuilder();
      if (localType != null)
        localStringBuilder.append(toString(localType)).append(".");
      localStringBuilder.append(toString(localParameterizedType.getRawType()));
      if (arrayOfType3.length > 0)
      {
        localStringBuilder.append("<").append(toString(arrayOfType3[0]));
        for (int i = 1; i < arrayOfType3.length; i++)
          localStringBuilder.append(", ").append(toString(arrayOfType3[i]));
      }
      return ">";
    }
    if ((paramType instanceof GenericArrayType))
      return toString(((GenericArrayType)paramType).getGenericComponentType()) + "[]";
    if ((paramType instanceof WildcardType))
    {
      WildcardType localWildcardType = (WildcardType)paramType;
      Type[] arrayOfType1 = localWildcardType.getLowerBounds();
      Type[] arrayOfType2 = localWildcardType.getUpperBounds();
      if ((arrayOfType2.length != 1) || (arrayOfType1.length > 1))
        throw new UnsupportedOperationException("Unsupported wildcard type " + paramType);
      if (arrayOfType1.length == 1)
      {
        if (arrayOfType2[0] != Object.class)
          throw new UnsupportedOperationException("Unsupported wildcard type " + paramType);
        return "? super " + toString(arrayOfType1[0]);
      }
      if (arrayOfType2[0] == Object.class)
        return "?";
      return "? extends " + toString(arrayOfType2[0]);
    }
    return paramType.toString();
  }

  private static abstract interface CompositeType
  {
    public abstract boolean isFullySpecified();
  }

  public static class GenericArrayTypeImpl
    implements GenericArrayType, Serializable, MoreTypes.CompositeType
  {
    private static final long serialVersionUID;
    private final Type componentType;

    public GenericArrayTypeImpl(Type paramType)
    {
      this.componentType = MoreTypes.canonicalize(paramType);
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof MoreTypes.CompositeType)) && (MoreTypes.equals(this, (MoreTypes.CompositeType)paramObject));
    }

    public Type getGenericComponentType()
    {
      return this.componentType;
    }

    public int hashCode()
    {
      return MoreTypes.hashCode(this);
    }

    public boolean isFullySpecified()
    {
      return MoreTypes.isFullySpecified(this.componentType);
    }

    public String toString()
    {
      return MoreTypes.toString(this);
    }
  }

  public static class MemberImpl
    implements Member, Serializable
  {
    private final Class<?> declaringClass;
    private final String memberKey;
    private final Class<? extends Member> memberType;
    private final int modifiers;
    private final String name;
    private final boolean synthetic;

    private MemberImpl(Member paramMember)
    {
      this.declaringClass = paramMember.getDeclaringClass();
      this.name = paramMember.getName();
      this.modifiers = paramMember.getModifiers();
      this.synthetic = paramMember.isSynthetic();
      this.memberType = MoreTypes.memberType(paramMember);
      this.memberKey = MoreTypes.memberKey(paramMember);
    }

    public Class getDeclaringClass()
    {
      return this.declaringClass;
    }

    public int getModifiers()
    {
      return this.modifiers;
    }

    public String getName()
    {
      return this.name;
    }

    public boolean isSynthetic()
    {
      return this.synthetic;
    }

    public String toString()
    {
      return MoreTypes.toString(this);
    }
  }

  public static class ParameterizedTypeImpl
    implements ParameterizedType, Serializable, MoreTypes.CompositeType
  {
    private static final long serialVersionUID;
    private final Type ownerType;
    private final Type rawType;
    private final Type[] typeArguments;

    public ParameterizedTypeImpl(Type paramType1, Type paramType2, Type[] paramArrayOfType)
    {
      boolean bool1;
      boolean bool2;
      if ((paramType2 instanceof Class))
      {
        Class localClass = (Class)paramType2;
        if ((paramType1 != null) || (localClass.getEnclosingClass() == null))
        {
          bool1 = true;
          Preconditions.checkArgument(bool1, "No owner type for enclosed %s", new Object[] { paramType2 });
          if ((paramType1 != null) && (localClass.getEnclosingClass() == null))
            break label176;
          bool2 = true;
          label62: Preconditions.checkArgument(bool2, "Owner type for unenclosed %s", new Object[] { paramType2 });
        }
      }
      else
      {
        if (paramType1 != null)
          break label182;
      }
      label176: label182: for (Type localType = null; ; localType = MoreTypes.canonicalize(paramType1))
      {
        this.ownerType = localType;
        this.rawType = MoreTypes.canonicalize(paramType2);
        this.typeArguments = ((Type[])paramArrayOfType.clone());
        for (int i = 0; i < this.typeArguments.length; i++)
        {
          Preconditions.checkNotNull(this.typeArguments[i], "type parameter");
          MoreTypes.checkNotPrimitive(this.typeArguments[i], "type parameters");
          this.typeArguments[i] = MoreTypes.canonicalize(this.typeArguments[i]);
        }
        bool1 = false;
        break;
        bool2 = false;
        break label62;
      }
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof MoreTypes.CompositeType)) && (MoreTypes.equals(this, (MoreTypes.CompositeType)paramObject));
    }

    public Type[] getActualTypeArguments()
    {
      return (Type[])this.typeArguments.clone();
    }

    public Type getOwnerType()
    {
      return this.ownerType;
    }

    public Type getRawType()
    {
      return this.rawType;
    }

    public int hashCode()
    {
      return MoreTypes.hashCode(this);
    }

    public boolean isFullySpecified()
    {
      if ((this.ownerType != null) && (!MoreTypes.isFullySpecified(this.ownerType)));
      while (!MoreTypes.isFullySpecified(this.rawType))
        return false;
      Type[] arrayOfType = this.typeArguments;
      int i = arrayOfType.length;
      for (int j = 0; ; j++)
      {
        if (j >= i)
          break label59;
        if (!MoreTypes.isFullySpecified(arrayOfType[j]))
          break;
      }
      label59: return true;
    }

    public String toString()
    {
      return MoreTypes.toString(this);
    }
  }

  public static class WildcardTypeImpl
    implements WildcardType, Serializable, MoreTypes.CompositeType
  {
    private static final long serialVersionUID;
    private final Type lowerBound;
    private final Type upperBound;

    public WildcardTypeImpl(Type[] paramArrayOfType1, Type[] paramArrayOfType2)
    {
      if (paramArrayOfType2.length <= i)
      {
        int k = i;
        Preconditions.checkArgument(k, "Must have at most one lower bound.");
        if (paramArrayOfType1.length != i)
          break label98;
        int n = i;
        label31: Preconditions.checkArgument(n, "Must have exactly one upper bound.");
        if (paramArrayOfType2.length != i)
          break label109;
        Preconditions.checkNotNull(paramArrayOfType2[0], "lowerBound");
        MoreTypes.checkNotPrimitive(paramArrayOfType2[0], "wildcard bounds");
        if (paramArrayOfType1[0] != Object.class)
          break label104;
      }
      while (true)
      {
        Preconditions.checkArgument(i, "bounded both ways");
        this.lowerBound = MoreTypes.canonicalize(paramArrayOfType2[0]);
        this.upperBound = Object.class;
        return;
        int m = 0;
        break;
        label98: int i1 = 0;
        break label31;
        label104: int j = 0;
      }
      label109: Preconditions.checkNotNull(paramArrayOfType1[0], "upperBound");
      MoreTypes.checkNotPrimitive(paramArrayOfType1[0], "wildcard bounds");
      this.lowerBound = null;
      this.upperBound = MoreTypes.canonicalize(paramArrayOfType1[0]);
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof MoreTypes.CompositeType)) && (MoreTypes.equals(this, (MoreTypes.CompositeType)paramObject));
    }

    public Type[] getLowerBounds()
    {
      if (this.lowerBound != null)
      {
        Type[] arrayOfType = new Type[1];
        arrayOfType[0] = this.lowerBound;
        return arrayOfType;
      }
      return MoreTypes.EMPTY_TYPE_ARRAY;
    }

    public Type[] getUpperBounds()
    {
      Type[] arrayOfType = new Type[1];
      arrayOfType[0] = this.upperBound;
      return arrayOfType;
    }

    public int hashCode()
    {
      return MoreTypes.hashCode(this);
    }

    public boolean isFullySpecified()
    {
      return (MoreTypes.isFullySpecified(this.upperBound)) && ((this.lowerBound == null) || (MoreTypes.isFullySpecified(this.lowerBound)));
    }

    public String toString()
    {
      return MoreTypes.toString(this);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.MoreTypes
 * JD-Core Version:    0.6.2
 */