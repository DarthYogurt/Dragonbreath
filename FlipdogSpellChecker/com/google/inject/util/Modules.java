package com.google.inject.util;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Binding;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.PrivateBinder;
import com.google.inject.Scope;
import com.google.inject.internal.ImmutableSet;
import com.google.inject.internal.Lists;
import com.google.inject.internal.Maps;
import com.google.inject.internal.Sets;
import com.google.inject.spi.DefaultBindingScopingVisitor;
import com.google.inject.spi.DefaultElementVisitor;
import com.google.inject.spi.Element;
import com.google.inject.spi.Elements;
import com.google.inject.spi.PrivateElements;
import com.google.inject.spi.ScopeBinding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Modules
{
  public static final Module EMPTY_MODULE = new Module()
  {
    public void configure(Binder paramAnonymousBinder)
    {
    }
  };

  public static Module combine(Iterable<? extends Module> paramIterable)
  {
    return new Module()
    {
      public void configure(Binder paramAnonymousBinder)
      {
        Class[] arrayOfClass = new Class[1];
        arrayOfClass[0] = getClass();
        Binder localBinder = paramAnonymousBinder.skipSources(arrayOfClass);
        Iterator localIterator = this.val$modulesSet.iterator();
        while (localIterator.hasNext())
          localBinder.install((Module)localIterator.next());
      }
    };
  }

  public static Module combine(Module[] paramArrayOfModule)
  {
    return combine(ImmutableSet.of(paramArrayOfModule));
  }

  public static OverriddenModuleBuilder override(Iterable<? extends Module> paramIterable)
  {
    return new RealOverriddenModuleBuilder(paramIterable, null);
  }

  public static OverriddenModuleBuilder override(Module[] paramArrayOfModule)
  {
    return new RealOverriddenModuleBuilder(Arrays.asList(paramArrayOfModule), null);
  }

  private static class ModuleWriter extends DefaultElementVisitor<Void>
  {
    protected final Binder binder;

    ModuleWriter(Binder paramBinder)
    {
      this.binder = paramBinder;
    }

    protected Void visitOther(Element paramElement)
    {
      paramElement.applyTo(this.binder);
      return null;
    }

    void writeAll(Iterable<? extends Element> paramIterable)
    {
      Iterator localIterator = paramIterable.iterator();
      while (localIterator.hasNext())
        ((Element)localIterator.next()).acceptVisitor(this);
    }
  }

  public static abstract interface OverriddenModuleBuilder
  {
    public abstract Module with(Iterable<? extends Module> paramIterable);

    public abstract Module with(Module[] paramArrayOfModule);
  }

  private static final class RealOverriddenModuleBuilder
    implements Modules.OverriddenModuleBuilder
  {
    private final ImmutableSet<Module> baseModules;

    private RealOverriddenModuleBuilder(Iterable<? extends Module> paramIterable)
    {
      this.baseModules = ImmutableSet.copyOf(paramIterable);
    }

    public Module with(final Iterable<? extends Module> paramIterable)
    {
      return new AbstractModule()
      {
        private Scope getScopeInstanceOrNull(Binding<?> paramAnonymousBinding)
        {
          return (Scope)paramAnonymousBinding.acceptScopingVisitor(new DefaultBindingScopingVisitor()
          {
            public Scope visitScope(Scope paramAnonymous2Scope)
            {
              return paramAnonymous2Scope;
            }
          });
        }

        public void configure()
        {
          List localList1 = Elements.getElements(Modules.RealOverriddenModuleBuilder.this.baseModules);
          List localList2 = Elements.getElements(paramIterable);
          final HashSet localHashSet1 = Sets.newHashSet();
          final HashSet localHashSet2 = Sets.newHashSet();
          new Modules.ModuleWriter(binder())
          {
            public <T> Void visit(Binding<T> paramAnonymous2Binding)
            {
              localHashSet1.add(paramAnonymous2Binding.getKey());
              return (Void)super.visit(paramAnonymous2Binding);
            }

            public Void visit(PrivateElements paramAnonymous2PrivateElements)
            {
              localHashSet1.addAll(paramAnonymous2PrivateElements.getExposedKeys());
              return (Void)super.visit(paramAnonymous2PrivateElements);
            }

            public Void visit(ScopeBinding paramAnonymous2ScopeBinding)
            {
              localHashSet2.add(paramAnonymous2ScopeBinding.getAnnotationType());
              return (Void)super.visit(paramAnonymous2ScopeBinding);
            }
          }
          .writeAll(localList2);
          final HashMap localHashMap = Maps.newHashMap();
          final ArrayList localArrayList = Lists.newArrayList();
          new Modules.ModuleWriter(binder())
          {
            public <T> Void visit(Binding<T> paramAnonymous2Binding)
            {
              if (!localHashSet1.remove(paramAnonymous2Binding.getKey()))
              {
                super.visit(paramAnonymous2Binding);
                Scope localScope = Modules.RealOverriddenModuleBuilder.1.this.getScopeInstanceOrNull(paramAnonymous2Binding);
                if (localScope != null)
                  localHashMap.put(localScope, paramAnonymous2Binding.getSource());
              }
              return null;
            }

            public Void visit(PrivateElements paramAnonymous2PrivateElements)
            {
              PrivateBinder localPrivateBinder = this.binder.withSource(paramAnonymous2PrivateElements.getSource()).newPrivateBinder();
              HashSet localHashSet = Sets.newHashSet();
              Iterator localIterator1 = paramAnonymous2PrivateElements.getExposedKeys().iterator();
              while (localIterator1.hasNext())
              {
                Key localKey = (Key)localIterator1.next();
                if (localHashSet1.remove(localKey))
                  localHashSet.add(localKey);
                else
                  localPrivateBinder.withSource(paramAnonymous2PrivateElements.getExposedSource(localKey)).expose(localKey);
              }
              Iterator localIterator2 = paramAnonymous2PrivateElements.getElements().iterator();
              while (localIterator2.hasNext())
              {
                Element localElement = (Element)localIterator2.next();
                if ((!(localElement instanceof Binding)) || (!localHashSet.contains(((Binding)localElement).getKey())))
                  localElement.applyTo(localPrivateBinder);
              }
              return null;
            }

            public Void visit(ScopeBinding paramAnonymous2ScopeBinding)
            {
              localArrayList.add(paramAnonymous2ScopeBinding);
              return null;
            }
          }
          .writeAll(localList1);
          new Modules.ModuleWriter(binder())
          {
            public Void visit(ScopeBinding paramAnonymous2ScopeBinding)
            {
              if (!localHashSet2.remove(paramAnonymous2ScopeBinding.getAnnotationType()))
                super.visit(paramAnonymous2ScopeBinding);
              while (true)
              {
                return null;
                Object localObject = localHashMap.get(paramAnonymous2ScopeBinding.getScope());
                if (localObject != null)
                {
                  Binder localBinder = Modules.RealOverriddenModuleBuilder.1.this.binder().withSource(localObject);
                  Object[] arrayOfObject = new Object[1];
                  arrayOfObject[0] = paramAnonymous2ScopeBinding.getAnnotationType().getSimpleName();
                  localBinder.addError("The scope for @%s is bound directly and cannot be overridden.", arrayOfObject);
                }
              }
            }
          }
          .writeAll(localArrayList);
        }
      };
    }

    public Module with(Module[] paramArrayOfModule)
    {
      return with(Arrays.asList(paramArrayOfModule));
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.util.Modules
 * JD-Core Version:    0.6.2
 */