package com.google.inject.name;

import com.google.inject.internal.Preconditions;
import java.io.Serializable;
import java.lang.annotation.Annotation;

class NamedImpl
  implements Named, Serializable
{
  private static final long serialVersionUID;
  private final String value;

  public NamedImpl(String paramString)
  {
    this.value = ((String)Preconditions.checkNotNull(paramString, "name"));
  }

  public Class<? extends Annotation> annotationType()
  {
    return Serializable.class;
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Serializable))
      return false;
    Named localNamed = (Serializable)paramObject;
    return this.value.equals(localNamed.value());
  }

  public int hashCode()
  {
    return 127 * "value".hashCode() ^ this.value.hashCode();
  }

  public String toString()
  {
    return "@" + Serializable.class.getName() + "(value=" + this.value + ")";
  }

  public String value()
  {
    return this.value;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.name.NamedImpl
 * JD-Core Version:    0.6.2
 */