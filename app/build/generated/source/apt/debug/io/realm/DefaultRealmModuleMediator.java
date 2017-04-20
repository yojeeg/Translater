package io.realm;


import android.util.JsonReader;
import io.realm.RealmObjectSchema;
import io.realm.internal.ColumnInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@io.realm.annotations.RealmModule
class DefaultRealmModuleMediator extends RealmProxyMediator {

    private static final Set<Class<? extends RealmModel>> MODEL_CLASSES;
    static {
        Set<Class<? extends RealmModel>> modelClasses = new HashSet<Class<? extends RealmModel>>();
        modelClasses.add(com.example.rartamonov.translater.realm.Directions.class);
        modelClasses.add(com.example.rartamonov.translater.realm.Langs.class);
        modelClasses.add(com.example.rartamonov.translater.realm.FavouriteHistory.class);
        MODEL_CLASSES = Collections.unmodifiableSet(modelClasses);
    }

    @Override
    public RealmObjectSchema createRealmObjectSchema(Class<? extends RealmModel> clazz, RealmSchema realmSchema) {
        checkClass(clazz);

        if (clazz.equals(com.example.rartamonov.translater.realm.Directions.class)) {
            return io.realm.DirectionsRealmProxy.createRealmObjectSchema(realmSchema);
        }
        if (clazz.equals(com.example.rartamonov.translater.realm.Langs.class)) {
            return io.realm.LangsRealmProxy.createRealmObjectSchema(realmSchema);
        }
        if (clazz.equals(com.example.rartamonov.translater.realm.FavouriteHistory.class)) {
            return io.realm.FavouriteHistoryRealmProxy.createRealmObjectSchema(realmSchema);
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public ColumnInfo validateTable(Class<? extends RealmModel> clazz, SharedRealm sharedRealm, boolean allowExtraColumns) {
        checkClass(clazz);

        if (clazz.equals(com.example.rartamonov.translater.realm.Directions.class)) {
            return io.realm.DirectionsRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        }
        if (clazz.equals(com.example.rartamonov.translater.realm.Langs.class)) {
            return io.realm.LangsRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        }
        if (clazz.equals(com.example.rartamonov.translater.realm.FavouriteHistory.class)) {
            return io.realm.FavouriteHistoryRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public List<String> getFieldNames(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(com.example.rartamonov.translater.realm.Directions.class)) {
            return io.realm.DirectionsRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.example.rartamonov.translater.realm.Langs.class)) {
            return io.realm.LangsRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.example.rartamonov.translater.realm.FavouriteHistory.class)) {
            return io.realm.FavouriteHistoryRealmProxy.getFieldNames();
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public String getTableName(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(com.example.rartamonov.translater.realm.Directions.class)) {
            return io.realm.DirectionsRealmProxy.getTableName();
        }
        if (clazz.equals(com.example.rartamonov.translater.realm.Langs.class)) {
            return io.realm.LangsRealmProxy.getTableName();
        }
        if (clazz.equals(com.example.rartamonov.translater.realm.FavouriteHistory.class)) {
            return io.realm.FavouriteHistoryRealmProxy.getTableName();
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E newInstance(Class<E> clazz, Object baseRealm, Row row, ColumnInfo columnInfo, boolean acceptDefaultValue, List<String> excludeFields) {
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        try {
            objectContext.set((BaseRealm) baseRealm, row, columnInfo, acceptDefaultValue, excludeFields);
            checkClass(clazz);

            if (clazz.equals(com.example.rartamonov.translater.realm.Directions.class)) {
                return clazz.cast(new io.realm.DirectionsRealmProxy());
            }
            if (clazz.equals(com.example.rartamonov.translater.realm.Langs.class)) {
                return clazz.cast(new io.realm.LangsRealmProxy());
            }
            if (clazz.equals(com.example.rartamonov.translater.realm.FavouriteHistory.class)) {
                return clazz.cast(new io.realm.FavouriteHistoryRealmProxy());
            }
            throw getMissingProxyClassException(clazz);
        } finally {
            objectContext.clear();
        }
    }

    @Override
    public Set<Class<? extends RealmModel>> getModelClasses() {
        return MODEL_CLASSES;
    }

    @Override
    public <E extends RealmModel> E copyOrUpdate(Realm realm, E obj, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.example.rartamonov.translater.realm.Directions.class)) {
            return clazz.cast(io.realm.DirectionsRealmProxy.copyOrUpdate(realm, (com.example.rartamonov.translater.realm.Directions) obj, update, cache));
        }
        if (clazz.equals(com.example.rartamonov.translater.realm.Langs.class)) {
            return clazz.cast(io.realm.LangsRealmProxy.copyOrUpdate(realm, (com.example.rartamonov.translater.realm.Langs) obj, update, cache));
        }
        if (clazz.equals(com.example.rartamonov.translater.realm.FavouriteHistory.class)) {
            return clazz.cast(io.realm.FavouriteHistoryRealmProxy.copyOrUpdate(realm, (com.example.rartamonov.translater.realm.FavouriteHistory) obj, update, cache));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public void insert(Realm realm, RealmModel object, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

        if (clazz.equals(com.example.rartamonov.translater.realm.Directions.class)) {
            io.realm.DirectionsRealmProxy.insert(realm, (com.example.rartamonov.translater.realm.Directions) object, cache);
        } else if (clazz.equals(com.example.rartamonov.translater.realm.Langs.class)) {
            io.realm.LangsRealmProxy.insert(realm, (com.example.rartamonov.translater.realm.Langs) object, cache);
        } else if (clazz.equals(com.example.rartamonov.translater.realm.FavouriteHistory.class)) {
            io.realm.FavouriteHistoryRealmProxy.insert(realm, (com.example.rartamonov.translater.realm.FavouriteHistory) object, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insert(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.example.rartamonov.translater.realm.Directions.class)) {
                io.realm.DirectionsRealmProxy.insert(realm, (com.example.rartamonov.translater.realm.Directions) object, cache);
            } else if (clazz.equals(com.example.rartamonov.translater.realm.Langs.class)) {
                io.realm.LangsRealmProxy.insert(realm, (com.example.rartamonov.translater.realm.Langs) object, cache);
            } else if (clazz.equals(com.example.rartamonov.translater.realm.FavouriteHistory.class)) {
                io.realm.FavouriteHistoryRealmProxy.insert(realm, (com.example.rartamonov.translater.realm.FavouriteHistory) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.example.rartamonov.translater.realm.Directions.class)) {
                    io.realm.DirectionsRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.example.rartamonov.translater.realm.Langs.class)) {
                    io.realm.LangsRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.example.rartamonov.translater.realm.FavouriteHistory.class)) {
                    io.realm.FavouriteHistoryRealmProxy.insert(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, RealmModel obj, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.example.rartamonov.translater.realm.Directions.class)) {
            io.realm.DirectionsRealmProxy.insertOrUpdate(realm, (com.example.rartamonov.translater.realm.Directions) obj, cache);
        } else if (clazz.equals(com.example.rartamonov.translater.realm.Langs.class)) {
            io.realm.LangsRealmProxy.insertOrUpdate(realm, (com.example.rartamonov.translater.realm.Langs) obj, cache);
        } else if (clazz.equals(com.example.rartamonov.translater.realm.FavouriteHistory.class)) {
            io.realm.FavouriteHistoryRealmProxy.insertOrUpdate(realm, (com.example.rartamonov.translater.realm.FavouriteHistory) obj, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.example.rartamonov.translater.realm.Directions.class)) {
                io.realm.DirectionsRealmProxy.insertOrUpdate(realm, (com.example.rartamonov.translater.realm.Directions) object, cache);
            } else if (clazz.equals(com.example.rartamonov.translater.realm.Langs.class)) {
                io.realm.LangsRealmProxy.insertOrUpdate(realm, (com.example.rartamonov.translater.realm.Langs) object, cache);
            } else if (clazz.equals(com.example.rartamonov.translater.realm.FavouriteHistory.class)) {
                io.realm.FavouriteHistoryRealmProxy.insertOrUpdate(realm, (com.example.rartamonov.translater.realm.FavouriteHistory) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.example.rartamonov.translater.realm.Directions.class)) {
                    io.realm.DirectionsRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.example.rartamonov.translater.realm.Langs.class)) {
                    io.realm.LangsRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.example.rartamonov.translater.realm.FavouriteHistory.class)) {
                    io.realm.FavouriteHistoryRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public <E extends RealmModel> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update)
        throws JSONException {
        checkClass(clazz);

        if (clazz.equals(com.example.rartamonov.translater.realm.Directions.class)) {
            return clazz.cast(io.realm.DirectionsRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.example.rartamonov.translater.realm.Langs.class)) {
            return clazz.cast(io.realm.LangsRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.example.rartamonov.translater.realm.FavouriteHistory.class)) {
            return clazz.cast(io.realm.FavouriteHistoryRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader)
        throws IOException {
        checkClass(clazz);

        if (clazz.equals(com.example.rartamonov.translater.realm.Directions.class)) {
            return clazz.cast(io.realm.DirectionsRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.example.rartamonov.translater.realm.Langs.class)) {
            return clazz.cast(io.realm.LangsRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.example.rartamonov.translater.realm.FavouriteHistory.class)) {
            return clazz.cast(io.realm.FavouriteHistoryRealmProxy.createUsingJsonStream(realm, reader));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) realmObject.getClass().getSuperclass();

        if (clazz.equals(com.example.rartamonov.translater.realm.Directions.class)) {
            return clazz.cast(io.realm.DirectionsRealmProxy.createDetachedCopy((com.example.rartamonov.translater.realm.Directions) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.example.rartamonov.translater.realm.Langs.class)) {
            return clazz.cast(io.realm.LangsRealmProxy.createDetachedCopy((com.example.rartamonov.translater.realm.Langs) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.example.rartamonov.translater.realm.FavouriteHistory.class)) {
            return clazz.cast(io.realm.FavouriteHistoryRealmProxy.createDetachedCopy((com.example.rartamonov.translater.realm.FavouriteHistory) realmObject, 0, maxDepth, cache));
        }
        throw getMissingProxyClassException(clazz);
    }

}
