package org.koin.core.registry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.koin.core.KoinApplication;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.error.DefinitionOverrideException;
import org.koin.core.error.NoBeanDefFoundException;
import org.koin.core.instance.DefinitionInstance;
import org.koin.core.logger.Level;
import org.koin.core.module.Module;
import org.koin.core.qualifier.Qualifier;
import org.koin.ext.KClassExtKt;

/* compiled from: BeanRegistry.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0010\u001a\u00020\u0011J,\u0010\u0012\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\rj\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005`\u000e2\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0002J\u0017\u0010\u0014\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0015H\u0000¢\u0006\u0002\b\u0016J$\u0010\u0017\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u000bJ\u0016\u0010\u001b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\u0006\u0010\u001c\u001a\u00020\tH\u0002J\u001a\u0010\u001d\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0002J\u001a\u0010\u001f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0002J\u0010\u0010 \u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0015J\u0018\u0010!\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u000bJ\u001c\u0010\"\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050#2\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u000bJ\u0014\u0010$\u001a\u00020\u00112\f\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&J\u0014\u0010(\u001a\u00020\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002J\u0014\u0010*\u001a\u00020\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002J \u0010+\u001a\u00020\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u00052\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0002J\u0014\u0010,\u001a\u00020\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002J\u0014\u0010-\u001a\u00020\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002J\u0010\u0010.\u001a\u00020\u00112\u0006\u0010/\u001a\u00020'H\u0002J\u0012\u00100\u001a\u00020\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0005J\u0014\u00101\u001a\u00020\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002J \u00102\u001a\u00020\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u00052\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0002J\u0014\u00103\u001a\u00020\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002J\u0014\u00104\u001a\u00020\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002J \u00105\u001a\u00020\u00112\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u000b2\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002J\u0014\u00106\u001a\u00020\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002J\u0010\u00107\u001a\u00020\u00112\u0006\u0010/\u001a\u00020'H\u0002J\u0006\u00108\u001a\u000209J\u001b\u0010:\u001a\u00020\u00112\f\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&H\u0000¢\u0006\u0002\b;J0\u0010<\u001a\u00020\u0011*\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005`\u00062\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002R&\u0010\u0003\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\n\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000R6\u0010\f\u001a*\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u001c\u0012\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\rj\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005`\u000e0\bX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u000f\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lorg/koin/core/registry/BeanRegistry;", "", "()V", "definitions", "Ljava/util/HashSet;", "Lorg/koin/core/definition/BeanDefinition;", "Lkotlin/collections/HashSet;", "definitionsNames", "", "", "definitionsPrimaryTypes", "Lkotlin/reflect/KClass;", "definitionsSecondaryTypes", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "definitionsToCreate", "close", "", "createSecondaryType", "type", "findAllCreatedAtStartDefinition", "", "findAllCreatedAtStartDefinition$koin_core", "findDefinition", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "clazz", "findDefinitionByName", "name", "findDefinitionBySecondaryType", "kClass", "findDefinitionByType", "getAllDefinitions", "getDefinition", "getDefinitionsForClass", "", "loadModules", "modules", "", "Lorg/koin/core/module/Module;", "removeDefinition", "definition", "removeDefinitionForName", "removeDefinitionForSecondaryType", "removeDefinitionForSecondaryTypes", "removeDefinitionForTypes", "removeDefinitions", "module", "saveDefinition", "saveDefinitionForName", "saveDefinitionForSecondaryType", "saveDefinitionForSecondaryTypes", "saveDefinitionForStart", "saveDefinitionForType", "saveDefinitionForTypes", "saveDefinitions", "size", "", "unloadModules", "unloadModules$koin_core", "addDefinition", "koin-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class BeanRegistry {
    private final HashSet<BeanDefinition<?>> definitions = new HashSet<>();
    private final Map<String, BeanDefinition<?>> definitionsNames = new ConcurrentHashMap();
    private final Map<KClass<?>, BeanDefinition<?>> definitionsPrimaryTypes = new ConcurrentHashMap();
    private final Map<KClass<?>, ArrayList<BeanDefinition<?>>> definitionsSecondaryTypes = new ConcurrentHashMap();
    private final HashSet<BeanDefinition<?>> definitionsToCreate = new HashSet<>();

    private final void removeDefinitions(Module module) {
        Iterator<T> it = module.getDefinitions$koin_core().iterator();
        while (it.hasNext()) {
            removeDefinition((BeanDefinition) it.next());
        }
    }

    private final void saveDefinitions(Module module) {
        Iterator<T> it = module.getDefinitions$koin_core().iterator();
        while (it.hasNext()) {
            saveDefinition((BeanDefinition) it.next());
        }
    }

    public final Set<BeanDefinition<?>> getAllDefinitions() {
        return this.definitions;
    }

    private final void removeDefinition(BeanDefinition<?> definition) {
        DefinitionInstance<?> beanDefinition = definition.getInstance();
        if (beanDefinition != null) {
            beanDefinition.close();
        }
        this.definitions.remove(definition);
        if (definition.getQualifier() != null) {
            removeDefinitionForName(definition);
        } else {
            removeDefinitionForTypes(definition);
        }
        removeDefinitionForSecondaryTypes(definition);
    }

    public final void saveDefinition(BeanDefinition<?> definition) {
        Intrinsics.checkParameterIsNotNull(definition, "definition");
        addDefinition(this.definitions, definition);
        definition.createInstanceHolder();
        if (definition.getQualifier() != null) {
            saveDefinitionForName(definition);
        } else {
            saveDefinitionForTypes(definition);
        }
        if (!definition.getSecondaryTypes().isEmpty()) {
            saveDefinitionForSecondaryTypes(definition);
        }
        if (definition.getOptions().isCreatedAtStart()) {
            saveDefinitionForStart(definition);
        }
    }

    private final void saveDefinitionForSecondaryTypes(BeanDefinition<?> definition) {
        Iterator<T> it = definition.getSecondaryTypes().iterator();
        while (it.hasNext()) {
            saveDefinitionForSecondaryType(definition, (KClass) it.next());
        }
    }

    private final void saveDefinitionForSecondaryType(BeanDefinition<?> definition, KClass<?> type) {
        ArrayList<BeanDefinition<?>> arrayListCreateSecondaryType = this.definitionsSecondaryTypes.get(type);
        if (arrayListCreateSecondaryType == null) {
            arrayListCreateSecondaryType = createSecondaryType(type);
        }
        arrayListCreateSecondaryType.add(definition);
        if (KoinApplication.INSTANCE.getLogger().isAt(Level.INFO)) {
            KoinApplication.INSTANCE.getLogger().info("bind secondary type:'" + KClassExtKt.getFullName(type) + "' ~ " + definition);
        }
    }

    private final ArrayList<BeanDefinition<?>> createSecondaryType(KClass<?> type) {
        this.definitionsSecondaryTypes.put(type, new ArrayList<>());
        ArrayList<BeanDefinition<?>> arrayList = this.definitionsSecondaryTypes.get(type);
        if (arrayList == null) {
            Intrinsics.throwNpe();
        }
        return arrayList;
    }

    private final void saveDefinitionForStart(BeanDefinition<?> definition) {
        this.definitionsToCreate.add(definition);
    }

    private final void addDefinition(HashSet<BeanDefinition<?>> hashSet, BeanDefinition<?> beanDefinition) throws DefinitionOverrideException {
        if (hashSet.add(beanDefinition) || beanDefinition.getOptions().getOverride()) {
            return;
        }
        throw new DefinitionOverrideException("Already existing definition or try to override an existing one: " + beanDefinition);
    }

    private final void saveDefinitionForTypes(BeanDefinition<?> definition) throws DefinitionOverrideException {
        saveDefinitionForType(definition.getPrimaryType(), definition);
    }

    private final void removeDefinitionForSecondaryTypes(BeanDefinition<?> definition) {
        Iterator<T> it = definition.getSecondaryTypes().iterator();
        while (it.hasNext()) {
            removeDefinitionForSecondaryType(definition, (KClass) it.next());
        }
    }

    private final void removeDefinitionForSecondaryType(BeanDefinition<?> definition, KClass<?> type) {
        ArrayList<BeanDefinition<?>> arrayList = this.definitionsSecondaryTypes.get(type);
        boolean zRemove = arrayList != null ? arrayList.remove(definition) : false;
        if (KoinApplication.INSTANCE.getLogger().isAt(Level.DEBUG) && zRemove) {
            KoinApplication.INSTANCE.getLogger().info("unbind secondary type:'" + KClassExtKt.getFullName(type) + "' ~ " + definition);
        }
    }

    private final void removeDefinitionForTypes(BeanDefinition<?> definition) {
        KClass<?> primaryType = definition.getPrimaryType();
        if (Intrinsics.areEqual(this.definitionsPrimaryTypes.get(primaryType), definition)) {
            this.definitionsPrimaryTypes.remove(primaryType);
            if (KoinApplication.INSTANCE.getLogger().isAt(Level.DEBUG)) {
                KoinApplication.INSTANCE.getLogger().info("unbind type:'" + KClassExtKt.getFullName(primaryType) + "' ~ " + definition);
            }
        }
    }

    private final void saveDefinitionForType(KClass<?> type, BeanDefinition<?> definition) throws DefinitionOverrideException {
        if (this.definitionsPrimaryTypes.get(type) != null && !definition.getOptions().getOverride()) {
            throw new DefinitionOverrideException("Already existing definition or try to override an existing one with type '" + type + "' and " + definition + " but has already registered " + this.definitionsPrimaryTypes.get(type));
        }
        this.definitionsPrimaryTypes.put(type, definition);
        if (KoinApplication.INSTANCE.getLogger().isAt(Level.INFO)) {
            KoinApplication.INSTANCE.getLogger().info("bind type:'" + KClassExtKt.getFullName(type) + "' ~ " + definition);
        }
    }

    private final void removeDefinitionForName(BeanDefinition<?> definition) {
        Qualifier qualifier = definition.getQualifier();
        if (qualifier != null) {
            String string = qualifier.toString();
            if (Intrinsics.areEqual(this.definitionsNames.get(string), definition)) {
                this.definitionsNames.remove(string);
                if (KoinApplication.INSTANCE.getLogger().isAt(Level.DEBUG)) {
                    KoinApplication.INSTANCE.getLogger().info("unbind qualifier:'" + string + "' ~ " + definition);
                }
            }
        }
    }

    private final void saveDefinitionForName(BeanDefinition<?> definition) throws DefinitionOverrideException {
        Qualifier qualifier = definition.getQualifier();
        if (qualifier != null) {
            if (this.definitionsNames.get(qualifier.toString()) != null && !definition.getOptions().getOverride()) {
                throw new DefinitionOverrideException("Already existing definition or try to override an existing one with qualifier '" + qualifier + "' with " + definition + " but has already registered " + this.definitionsNames.get(qualifier.toString()));
            }
            this.definitionsNames.put(qualifier.toString(), definition);
            if (KoinApplication.INSTANCE.getLogger().isAt(Level.INFO)) {
                KoinApplication.INSTANCE.getLogger().info("bind qualifier:'" + definition.getQualifier() + "' ~ " + definition);
            }
        }
    }

    public static /* synthetic */ BeanDefinition findDefinition$default(BeanRegistry beanRegistry, Qualifier qualifier, KClass kClass, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        return beanRegistry.findDefinition(qualifier, kClass);
    }

    public final BeanDefinition<?> findDefinition(Qualifier qualifier, KClass<?> clazz) {
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        if (qualifier != null) {
            return findDefinitionByName(qualifier.toString());
        }
        BeanDefinition<?> beanDefinitionFindDefinitionByType = findDefinitionByType(clazz);
        return beanDefinitionFindDefinitionByType != null ? beanDefinitionFindDefinitionByType : findDefinitionBySecondaryType(clazz);
    }

    private final BeanDefinition<?> findDefinitionByType(KClass<?> kClass) {
        return this.definitionsPrimaryTypes.get(kClass);
    }

    private final BeanDefinition<?> findDefinitionBySecondaryType(KClass<?> kClass) throws NoBeanDefFoundException {
        ArrayList<BeanDefinition<?>> arrayList = this.definitionsSecondaryTypes.get(kClass);
        if (arrayList != null && arrayList.size() == 1) {
            return arrayList.get(0);
        }
        if (arrayList == null || arrayList.size() <= 1) {
            return null;
        }
        throw new NoBeanDefFoundException("Found multiple definitions for type '" + KClassExtKt.getFullName(kClass) + "': " + arrayList + ". Please use the 'bind<P,S>()' function to bind your instance from primary and secondary types.");
    }

    private final BeanDefinition<?> findDefinitionByName(String name) {
        return this.definitionsNames.get(name);
    }

    public final Set<BeanDefinition<?>> findAllCreatedAtStartDefinition$koin_core() {
        return this.definitionsToCreate;
    }

    public final int size() {
        return this.definitions.size();
    }

    public final BeanDefinition<?> getDefinition(KClass<?> clazz) {
        Object next;
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        Iterator<T> it = this.definitions.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            BeanDefinition beanDefinition = (BeanDefinition) next;
            if (Intrinsics.areEqual(beanDefinition.getPrimaryType(), clazz) || beanDefinition.getSecondaryTypes().contains(clazz)) {
                break;
            }
        }
        return (BeanDefinition) next;
    }

    public final void close() {
        Iterator<T> it = this.definitions.iterator();
        while (it.hasNext()) {
            ((BeanDefinition) it.next()).close();
        }
        this.definitions.clear();
        this.definitionsNames.clear();
        this.definitionsPrimaryTypes.clear();
        this.definitionsToCreate.clear();
    }

    public final List<BeanDefinition<?>> getDefinitionsForClass(KClass<?> clazz) {
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        Set<BeanDefinition<?>> allDefinitions = getAllDefinitions();
        ArrayList arrayList = new ArrayList();
        for (Object obj : allDefinitions) {
            BeanDefinition beanDefinition = (BeanDefinition) obj;
            if (Intrinsics.areEqual(beanDefinition.getPrimaryType(), clazz) || (beanDefinition.getSecondaryTypes().contains(clazz) && !beanDefinition.hasScopeSet())) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final void loadModules(Iterable<Module> modules) {
        Intrinsics.checkParameterIsNotNull(modules, "modules");
        Iterator<Module> it = modules.iterator();
        while (it.hasNext()) {
            saveDefinitions(it.next());
        }
    }

    public final void unloadModules$koin_core(Iterable<Module> modules) {
        Intrinsics.checkParameterIsNotNull(modules, "modules");
        Iterator<Module> it = modules.iterator();
        while (it.hasNext()) {
            removeDefinitions(it.next());
        }
    }
}
