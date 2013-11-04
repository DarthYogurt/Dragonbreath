package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;
import com.google.inject.spi.InjectionPoint;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

class SingleMethodInjector
  implements SingleMemberInjector
{
  final InjectionPoint injectionPoint;
  final InjectorImpl.MethodInvoker methodInvoker;
  final SingleParameterInjector<?>[] parameterInjectors;

  public SingleMethodInjector(InjectorImpl paramInjectorImpl, InjectionPoint paramInjectionPoint, Errors paramErrors)
    throws ErrorsException
  {
    this.injectionPoint = paramInjectionPoint;
    this.methodInvoker = createMethodInvoker((Method)paramInjectionPoint.getMember());
    this.parameterInjectors = paramInjectorImpl.getParametersInjectors(paramInjectionPoint.getDependencies(), paramErrors);
  }

  private InjectorImpl.MethodInvoker createMethodInvoker(final Method paramMethod)
  {
    int i = paramMethod.getModifiers();
    if (((Modifier.isPrivate(i)) || (Modifier.isProtected(i))) || (!Modifier.isPublic(i)))
      paramMethod.setAccessible(true);
    return new InjectorImpl.MethodInvoker()
    {
      public Object invoke(Object paramAnonymousObject, Object[] paramAnonymousArrayOfObject)
        throws IllegalAccessException, InvocationTargetException
      {
        return paramMethod.invoke(paramAnonymousObject, paramAnonymousArrayOfObject);
      }
    };
  }

  public InjectionPoint getInjectionPoint()
  {
    return this.injectionPoint;
  }

  // ERROR //
  public void inject(Errors paramErrors, com.google.inject.internal.InternalContext paramInternalContext, Object paramObject)
  {
    // Byte code:
    //   0: aload_1
    //   1: aload_2
    //   2: aload_0
    //   3: getfield 48	com/google/inject/SingleMethodInjector:parameterInjectors	[Lcom/google/inject/SingleParameterInjector;
    //   6: invokestatic 87	com/google/inject/SingleParameterInjector:getAll	(Lcom/google/inject/internal/Errors;Lcom/google/inject/internal/InternalContext;[Lcom/google/inject/SingleParameterInjector;)[Ljava/lang/Object;
    //   9: astore 6
    //   11: aload_0
    //   12: getfield 36	com/google/inject/SingleMethodInjector:methodInvoker	Lcom/google/inject/InjectorImpl$MethodInvoker;
    //   15: aload_3
    //   16: aload 6
    //   18: invokeinterface 93 3 0
    //   23: pop
    //   24: return
    //   25: astore 4
    //   27: aload_1
    //   28: aload 4
    //   30: invokevirtual 97	com/google/inject/internal/ErrorsException:getErrors	()Lcom/google/inject/internal/Errors;
    //   33: invokevirtual 103	com/google/inject/internal/Errors:merge	(Lcom/google/inject/internal/Errors;)Lcom/google/inject/internal/Errors;
    //   36: pop
    //   37: return
    //   38: astore 10
    //   40: new 105	java/lang/AssertionError
    //   43: dup
    //   44: aload 10
    //   46: invokespecial 108	java/lang/AssertionError:<init>	(Ljava/lang/Object;)V
    //   49: athrow
    //   50: astore 7
    //   52: aload 7
    //   54: invokevirtual 112	java/lang/reflect/InvocationTargetException:getCause	()Ljava/lang/Throwable;
    //   57: ifnull +25 -> 82
    //   60: aload 7
    //   62: invokevirtual 112	java/lang/reflect/InvocationTargetException:getCause	()Ljava/lang/Throwable;
    //   65: astore 8
    //   67: aload_1
    //   68: aload_0
    //   69: getfield 22	com/google/inject/SingleMethodInjector:injectionPoint	Lcom/google/inject/spi/InjectionPoint;
    //   72: invokevirtual 116	com/google/inject/internal/Errors:withSource	(Ljava/lang/Object;)Lcom/google/inject/internal/Errors;
    //   75: aload 8
    //   77: invokevirtual 120	com/google/inject/internal/Errors:errorInjectingMethod	(Ljava/lang/Throwable;)Lcom/google/inject/internal/Errors;
    //   80: pop
    //   81: return
    //   82: aload 7
    //   84: astore 8
    //   86: goto -19 -> 67
    //
    // Exception table:
    //   from	to	target	type
    //   0	11	25	com/google/inject/internal/ErrorsException
    //   11	24	38	java/lang/IllegalAccessException
    //   11	24	50	java/lang/reflect/InvocationTargetException
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.SingleMethodInjector
 * JD-Core Version:    0.6.2
 */