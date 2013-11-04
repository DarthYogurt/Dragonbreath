package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;
import com.google.inject.internal.InternalContext;
import com.google.inject.internal.InternalFactory;
import com.google.inject.spi.Dependency;
import com.google.inject.spi.InjectionPoint;
import java.lang.reflect.Field;
import java.util.List;

class SingleFieldInjector
  implements SingleMemberInjector
{
  final Dependency<?> dependency;
  final InternalFactory<?> factory;
  final Field field;
  final InjectionPoint injectionPoint;

  public SingleFieldInjector(InjectorImpl paramInjectorImpl, InjectionPoint paramInjectionPoint, Errors paramErrors)
    throws ErrorsException
  {
    this.injectionPoint = paramInjectionPoint;
    this.field = ((Field)paramInjectionPoint.getMember());
    this.dependency = ((Dependency)paramInjectionPoint.getDependencies().get(0));
    this.field.setAccessible(true);
    this.factory = paramInjectorImpl.getInternalFactory(this.dependency.getKey(), paramErrors);
  }

  public InjectionPoint getInjectionPoint()
  {
    return this.injectionPoint;
  }

  public void inject(Errors paramErrors, InternalContext paramInternalContext, Object paramObject)
  {
    Errors localErrors = paramErrors.withSource(this.dependency);
    paramInternalContext.setDependency(this.dependency);
    try
    {
      Object localObject2 = this.factory.get(localErrors, paramInternalContext, this.dependency);
      this.field.set(paramObject, localObject2);
      return;
    }
    catch (ErrorsException localErrorsException)
    {
      localErrors.withSource(this.injectionPoint).merge(localErrorsException.getErrors());
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new AssertionError(localIllegalAccessException);
    }
    finally
    {
      paramInternalContext.setDependency(null);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.SingleFieldInjector
 * JD-Core Version:    0.6.2
 */