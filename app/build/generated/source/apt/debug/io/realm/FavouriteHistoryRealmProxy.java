package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FavouriteHistoryRealmProxy extends com.example.rartamonov.translater.realm.FavouriteHistory
    implements RealmObjectProxy, FavouriteHistoryRealmProxyInterface {

    static final class FavouriteHistoryColumnInfo extends ColumnInfo
        implements Cloneable {

        public long wordFromIndex;
        public long wordToIndex;
        public long directIndex;
        public long historyIndex;
        public long favouriteIndex;

        FavouriteHistoryColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(5);
            this.wordFromIndex = getValidColumnIndex(path, table, "FavouriteHistory", "wordFrom");
            indicesMap.put("wordFrom", this.wordFromIndex);
            this.wordToIndex = getValidColumnIndex(path, table, "FavouriteHistory", "wordTo");
            indicesMap.put("wordTo", this.wordToIndex);
            this.directIndex = getValidColumnIndex(path, table, "FavouriteHistory", "direct");
            indicesMap.put("direct", this.directIndex);
            this.historyIndex = getValidColumnIndex(path, table, "FavouriteHistory", "history");
            indicesMap.put("history", this.historyIndex);
            this.favouriteIndex = getValidColumnIndex(path, table, "FavouriteHistory", "favourite");
            indicesMap.put("favourite", this.favouriteIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final FavouriteHistoryColumnInfo otherInfo = (FavouriteHistoryColumnInfo) other;
            this.wordFromIndex = otherInfo.wordFromIndex;
            this.wordToIndex = otherInfo.wordToIndex;
            this.directIndex = otherInfo.directIndex;
            this.historyIndex = otherInfo.historyIndex;
            this.favouriteIndex = otherInfo.favouriteIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final FavouriteHistoryColumnInfo clone() {
            return (FavouriteHistoryColumnInfo) super.clone();
        }

    }
    private FavouriteHistoryColumnInfo columnInfo;
    private ProxyState<com.example.rartamonov.translater.realm.FavouriteHistory> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("wordFrom");
        fieldNames.add("wordTo");
        fieldNames.add("direct");
        fieldNames.add("history");
        fieldNames.add("favourite");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    FavouriteHistoryRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (FavouriteHistoryColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.rartamonov.translater.realm.FavouriteHistory>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$wordFrom() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.wordFromIndex);
    }

    @Override
    public void realmSet$wordFrom(String value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'wordFrom' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$wordTo() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.wordToIndex);
    }

    @Override
    public void realmSet$wordTo(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.wordToIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.wordToIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.wordToIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.wordToIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$direct() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.directIndex);
    }

    @Override
    public void realmSet$direct(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.directIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.directIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.directIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.directIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public Boolean realmGet$history() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.historyIndex)) {
            return null;
        }
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.historyIndex);
    }

    @Override
    public void realmSet$history(Boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.historyIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setBoolean(columnInfo.historyIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.historyIndex);
            return;
        }
        proxyState.getRow$realm().setBoolean(columnInfo.historyIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public Boolean realmGet$favourite() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.favouriteIndex)) {
            return null;
        }
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.favouriteIndex);
    }

    @Override
    public void realmSet$favourite(Boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.favouriteIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setBoolean(columnInfo.favouriteIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.favouriteIndex);
            return;
        }
        proxyState.getRow$realm().setBoolean(columnInfo.favouriteIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("FavouriteHistory")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("FavouriteHistory");
            realmObjectSchema.add("wordFrom", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("wordTo", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("direct", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("history", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("favourite", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            return realmObjectSchema;
        }
        return realmSchema.get("FavouriteHistory");
    }

    public static FavouriteHistoryColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_FavouriteHistory")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'FavouriteHistory' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_FavouriteHistory");
        final long columnCount = table.getColumnCount();
        if (columnCount != 5) {
            if (columnCount < 5) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 5 but was " + columnCount);
            }
            if (allowExtraColumns) {
                RealmLog.debug("Field count is more than expected - expected 5 but was %1$d", columnCount);
            } else {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 5 but was " + columnCount);
            }
        }
        Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
        for (long i = 0; i < columnCount; i++) {
            columnTypes.put(table.getColumnName(i), table.getColumnType(i));
        }

        final FavouriteHistoryColumnInfo columnInfo = new FavouriteHistoryColumnInfo(sharedRealm.getPath(), table);

        if (!table.hasPrimaryKey()) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'wordFrom' in existing Realm file. @PrimaryKey was added.");
        } else {
            if (table.getPrimaryKey() != columnInfo.wordFromIndex) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key annotation definition was changed, from field " + table.getColumnName(table.getPrimaryKey()) + " to field wordFrom");
            }
        }

        if (!columnTypes.containsKey("wordFrom")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'wordFrom' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("wordFrom") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'wordFrom' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.wordFromIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(),"@PrimaryKey field 'wordFrom' does not support null values in the existing Realm file. Migrate using RealmObjectSchema.setNullable(), or mark the field as @Required.");
        }
        if (!table.hasSearchIndex(table.getColumnIndex("wordFrom"))) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'wordFrom' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
        }
        if (!columnTypes.containsKey("wordTo")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'wordTo' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("wordTo") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'wordTo' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.wordToIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'wordTo' is required. Either set @Required to field 'wordTo' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("direct")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'direct' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("direct") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'direct' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.directIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'direct' is required. Either set @Required to field 'direct' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("history")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'history' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("history") != RealmFieldType.BOOLEAN) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Boolean' for field 'history' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.historyIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(),"Field 'history' does not support null values in the existing Realm file. Either set @Required, use the primitive type for field 'history' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("favourite")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'favourite' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("favourite") != RealmFieldType.BOOLEAN) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Boolean' for field 'favourite' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.favouriteIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(),"Field 'favourite' does not support null values in the existing Realm file. Either set @Required, use the primitive type for field 'favourite' or migrate using RealmObjectSchema.setNullable().");
        }

        return columnInfo;
    }

    public static String getTableName() {
        return "class_FavouriteHistory";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.example.rartamonov.translater.realm.FavouriteHistory createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.example.rartamonov.translater.realm.FavouriteHistory obj = null;
        if (update) {
            Table table = realm.getTable(com.example.rartamonov.translater.realm.FavouriteHistory.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = Table.NO_MATCH;
            if (json.isNull("wordFrom")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("wordFrom"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.example.rartamonov.translater.realm.FavouriteHistory.class), false, Collections.<String> emptyList());
                    obj = new io.realm.FavouriteHistoryRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("wordFrom")) {
                if (json.isNull("wordFrom")) {
                    obj = (io.realm.FavouriteHistoryRealmProxy) realm.createObjectInternal(com.example.rartamonov.translater.realm.FavouriteHistory.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.FavouriteHistoryRealmProxy) realm.createObjectInternal(com.example.rartamonov.translater.realm.FavouriteHistory.class, json.getString("wordFrom"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'wordFrom'.");
            }
        }
        if (json.has("wordTo")) {
            if (json.isNull("wordTo")) {
                ((FavouriteHistoryRealmProxyInterface) obj).realmSet$wordTo(null);
            } else {
                ((FavouriteHistoryRealmProxyInterface) obj).realmSet$wordTo((String) json.getString("wordTo"));
            }
        }
        if (json.has("direct")) {
            if (json.isNull("direct")) {
                ((FavouriteHistoryRealmProxyInterface) obj).realmSet$direct(null);
            } else {
                ((FavouriteHistoryRealmProxyInterface) obj).realmSet$direct((String) json.getString("direct"));
            }
        }
        if (json.has("history")) {
            if (json.isNull("history")) {
                ((FavouriteHistoryRealmProxyInterface) obj).realmSet$history(null);
            } else {
                ((FavouriteHistoryRealmProxyInterface) obj).realmSet$history((boolean) json.getBoolean("history"));
            }
        }
        if (json.has("favourite")) {
            if (json.isNull("favourite")) {
                ((FavouriteHistoryRealmProxyInterface) obj).realmSet$favourite(null);
            } else {
                ((FavouriteHistoryRealmProxyInterface) obj).realmSet$favourite((boolean) json.getBoolean("favourite"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.rartamonov.translater.realm.FavouriteHistory createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.example.rartamonov.translater.realm.FavouriteHistory obj = new com.example.rartamonov.translater.realm.FavouriteHistory();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("wordFrom")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((FavouriteHistoryRealmProxyInterface) obj).realmSet$wordFrom(null);
                } else {
                    ((FavouriteHistoryRealmProxyInterface) obj).realmSet$wordFrom((String) reader.nextString());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("wordTo")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((FavouriteHistoryRealmProxyInterface) obj).realmSet$wordTo(null);
                } else {
                    ((FavouriteHistoryRealmProxyInterface) obj).realmSet$wordTo((String) reader.nextString());
                }
            } else if (name.equals("direct")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((FavouriteHistoryRealmProxyInterface) obj).realmSet$direct(null);
                } else {
                    ((FavouriteHistoryRealmProxyInterface) obj).realmSet$direct((String) reader.nextString());
                }
            } else if (name.equals("history")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((FavouriteHistoryRealmProxyInterface) obj).realmSet$history(null);
                } else {
                    ((FavouriteHistoryRealmProxyInterface) obj).realmSet$history((boolean) reader.nextBoolean());
                }
            } else if (name.equals("favourite")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((FavouriteHistoryRealmProxyInterface) obj).realmSet$favourite(null);
                } else {
                    ((FavouriteHistoryRealmProxyInterface) obj).realmSet$favourite((boolean) reader.nextBoolean());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'wordFrom'.");
        }
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.example.rartamonov.translater.realm.FavouriteHistory copyOrUpdate(Realm realm, com.example.rartamonov.translater.realm.FavouriteHistory object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.example.rartamonov.translater.realm.FavouriteHistory) cachedRealmObject;
        } else {
            com.example.rartamonov.translater.realm.FavouriteHistory realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.example.rartamonov.translater.realm.FavouriteHistory.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((FavouriteHistoryRealmProxyInterface) object).realmGet$wordFrom();
                long rowIndex = Table.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != Table.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.example.rartamonov.translater.realm.FavouriteHistory.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.FavouriteHistoryRealmProxy();
                        cache.put(object, (RealmObjectProxy) realmObject);
                    } finally {
                        objectContext.clear();
                    }
                } else {
                    canUpdate = false;
                }
            }

            if (canUpdate) {
                return update(realm, realmObject, object, cache);
            } else {
                return copy(realm, object, update, cache);
            }
        }
    }

    public static com.example.rartamonov.translater.realm.FavouriteHistory copy(Realm realm, com.example.rartamonov.translater.realm.FavouriteHistory newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.rartamonov.translater.realm.FavouriteHistory) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.example.rartamonov.translater.realm.FavouriteHistory realmObject = realm.createObjectInternal(com.example.rartamonov.translater.realm.FavouriteHistory.class, ((FavouriteHistoryRealmProxyInterface) newObject).realmGet$wordFrom(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((FavouriteHistoryRealmProxyInterface) realmObject).realmSet$wordTo(((FavouriteHistoryRealmProxyInterface) newObject).realmGet$wordTo());
            ((FavouriteHistoryRealmProxyInterface) realmObject).realmSet$direct(((FavouriteHistoryRealmProxyInterface) newObject).realmGet$direct());
            ((FavouriteHistoryRealmProxyInterface) realmObject).realmSet$history(((FavouriteHistoryRealmProxyInterface) newObject).realmGet$history());
            ((FavouriteHistoryRealmProxyInterface) realmObject).realmSet$favourite(((FavouriteHistoryRealmProxyInterface) newObject).realmGet$favourite());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.example.rartamonov.translater.realm.FavouriteHistory object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.example.rartamonov.translater.realm.FavouriteHistory.class);
        long tableNativePtr = table.getNativeTablePointer();
        FavouriteHistoryColumnInfo columnInfo = (FavouriteHistoryColumnInfo) realm.schema.getColumnInfo(com.example.rartamonov.translater.realm.FavouriteHistory.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((FavouriteHistoryRealmProxyInterface) object).realmGet$wordFrom();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$wordTo = ((FavouriteHistoryRealmProxyInterface)object).realmGet$wordTo();
        if (realmGet$wordTo != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.wordToIndex, rowIndex, realmGet$wordTo, false);
        }
        String realmGet$direct = ((FavouriteHistoryRealmProxyInterface)object).realmGet$direct();
        if (realmGet$direct != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.directIndex, rowIndex, realmGet$direct, false);
        }
        Boolean realmGet$history = ((FavouriteHistoryRealmProxyInterface)object).realmGet$history();
        if (realmGet$history != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.historyIndex, rowIndex, realmGet$history, false);
        }
        Boolean realmGet$favourite = ((FavouriteHistoryRealmProxyInterface)object).realmGet$favourite();
        if (realmGet$favourite != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.favouriteIndex, rowIndex, realmGet$favourite, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.rartamonov.translater.realm.FavouriteHistory.class);
        long tableNativePtr = table.getNativeTablePointer();
        FavouriteHistoryColumnInfo columnInfo = (FavouriteHistoryColumnInfo) realm.schema.getColumnInfo(com.example.rartamonov.translater.realm.FavouriteHistory.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.example.rartamonov.translater.realm.FavouriteHistory object = null;
        while (objects.hasNext()) {
            object = (com.example.rartamonov.translater.realm.FavouriteHistory) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((FavouriteHistoryRealmProxyInterface) object).realmGet$wordFrom();
                long rowIndex = Table.NO_MATCH;
                if (primaryKeyValue == null) {
                    rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                } else {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
                } else {
                    Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                }
                cache.put(object, rowIndex);
                String realmGet$wordTo = ((FavouriteHistoryRealmProxyInterface)object).realmGet$wordTo();
                if (realmGet$wordTo != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.wordToIndex, rowIndex, realmGet$wordTo, false);
                }
                String realmGet$direct = ((FavouriteHistoryRealmProxyInterface)object).realmGet$direct();
                if (realmGet$direct != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.directIndex, rowIndex, realmGet$direct, false);
                }
                Boolean realmGet$history = ((FavouriteHistoryRealmProxyInterface)object).realmGet$history();
                if (realmGet$history != null) {
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.historyIndex, rowIndex, realmGet$history, false);
                }
                Boolean realmGet$favourite = ((FavouriteHistoryRealmProxyInterface)object).realmGet$favourite();
                if (realmGet$favourite != null) {
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.favouriteIndex, rowIndex, realmGet$favourite, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.rartamonov.translater.realm.FavouriteHistory object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.example.rartamonov.translater.realm.FavouriteHistory.class);
        long tableNativePtr = table.getNativeTablePointer();
        FavouriteHistoryColumnInfo columnInfo = (FavouriteHistoryColumnInfo) realm.schema.getColumnInfo(com.example.rartamonov.translater.realm.FavouriteHistory.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((FavouriteHistoryRealmProxyInterface) object).realmGet$wordFrom();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
        }
        cache.put(object, rowIndex);
        String realmGet$wordTo = ((FavouriteHistoryRealmProxyInterface)object).realmGet$wordTo();
        if (realmGet$wordTo != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.wordToIndex, rowIndex, realmGet$wordTo, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.wordToIndex, rowIndex, false);
        }
        String realmGet$direct = ((FavouriteHistoryRealmProxyInterface)object).realmGet$direct();
        if (realmGet$direct != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.directIndex, rowIndex, realmGet$direct, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.directIndex, rowIndex, false);
        }
        Boolean realmGet$history = ((FavouriteHistoryRealmProxyInterface)object).realmGet$history();
        if (realmGet$history != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.historyIndex, rowIndex, realmGet$history, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.historyIndex, rowIndex, false);
        }
        Boolean realmGet$favourite = ((FavouriteHistoryRealmProxyInterface)object).realmGet$favourite();
        if (realmGet$favourite != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.favouriteIndex, rowIndex, realmGet$favourite, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.favouriteIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.rartamonov.translater.realm.FavouriteHistory.class);
        long tableNativePtr = table.getNativeTablePointer();
        FavouriteHistoryColumnInfo columnInfo = (FavouriteHistoryColumnInfo) realm.schema.getColumnInfo(com.example.rartamonov.translater.realm.FavouriteHistory.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.example.rartamonov.translater.realm.FavouriteHistory object = null;
        while (objects.hasNext()) {
            object = (com.example.rartamonov.translater.realm.FavouriteHistory) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((FavouriteHistoryRealmProxyInterface) object).realmGet$wordFrom();
                long rowIndex = Table.NO_MATCH;
                if (primaryKeyValue == null) {
                    rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                } else {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
                }
                cache.put(object, rowIndex);
                String realmGet$wordTo = ((FavouriteHistoryRealmProxyInterface)object).realmGet$wordTo();
                if (realmGet$wordTo != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.wordToIndex, rowIndex, realmGet$wordTo, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.wordToIndex, rowIndex, false);
                }
                String realmGet$direct = ((FavouriteHistoryRealmProxyInterface)object).realmGet$direct();
                if (realmGet$direct != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.directIndex, rowIndex, realmGet$direct, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.directIndex, rowIndex, false);
                }
                Boolean realmGet$history = ((FavouriteHistoryRealmProxyInterface)object).realmGet$history();
                if (realmGet$history != null) {
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.historyIndex, rowIndex, realmGet$history, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.historyIndex, rowIndex, false);
                }
                Boolean realmGet$favourite = ((FavouriteHistoryRealmProxyInterface)object).realmGet$favourite();
                if (realmGet$favourite != null) {
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.favouriteIndex, rowIndex, realmGet$favourite, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.favouriteIndex, rowIndex, false);
                }
            }
        }
    }

    public static com.example.rartamonov.translater.realm.FavouriteHistory createDetachedCopy(com.example.rartamonov.translater.realm.FavouriteHistory realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.rartamonov.translater.realm.FavouriteHistory unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.rartamonov.translater.realm.FavouriteHistory)cachedObject.object;
            } else {
                unmanagedObject = (com.example.rartamonov.translater.realm.FavouriteHistory)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.example.rartamonov.translater.realm.FavouriteHistory();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((FavouriteHistoryRealmProxyInterface) unmanagedObject).realmSet$wordFrom(((FavouriteHistoryRealmProxyInterface) realmObject).realmGet$wordFrom());
        ((FavouriteHistoryRealmProxyInterface) unmanagedObject).realmSet$wordTo(((FavouriteHistoryRealmProxyInterface) realmObject).realmGet$wordTo());
        ((FavouriteHistoryRealmProxyInterface) unmanagedObject).realmSet$direct(((FavouriteHistoryRealmProxyInterface) realmObject).realmGet$direct());
        ((FavouriteHistoryRealmProxyInterface) unmanagedObject).realmSet$history(((FavouriteHistoryRealmProxyInterface) realmObject).realmGet$history());
        ((FavouriteHistoryRealmProxyInterface) unmanagedObject).realmSet$favourite(((FavouriteHistoryRealmProxyInterface) realmObject).realmGet$favourite());
        return unmanagedObject;
    }

    static com.example.rartamonov.translater.realm.FavouriteHistory update(Realm realm, com.example.rartamonov.translater.realm.FavouriteHistory realmObject, com.example.rartamonov.translater.realm.FavouriteHistory newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((FavouriteHistoryRealmProxyInterface) realmObject).realmSet$wordTo(((FavouriteHistoryRealmProxyInterface) newObject).realmGet$wordTo());
        ((FavouriteHistoryRealmProxyInterface) realmObject).realmSet$direct(((FavouriteHistoryRealmProxyInterface) newObject).realmGet$direct());
        ((FavouriteHistoryRealmProxyInterface) realmObject).realmSet$history(((FavouriteHistoryRealmProxyInterface) newObject).realmGet$history());
        ((FavouriteHistoryRealmProxyInterface) realmObject).realmSet$favourite(((FavouriteHistoryRealmProxyInterface) newObject).realmGet$favourite());
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("FavouriteHistory = [");
        stringBuilder.append("{wordFrom:");
        stringBuilder.append(realmGet$wordFrom() != null ? realmGet$wordFrom() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{wordTo:");
        stringBuilder.append(realmGet$wordTo() != null ? realmGet$wordTo() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{direct:");
        stringBuilder.append(realmGet$direct() != null ? realmGet$direct() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{history:");
        stringBuilder.append(realmGet$history() != null ? realmGet$history() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{favourite:");
        stringBuilder.append(realmGet$favourite() != null ? realmGet$favourite() : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavouriteHistoryRealmProxy aFavouriteHistory = (FavouriteHistoryRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aFavouriteHistory.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aFavouriteHistory.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aFavouriteHistory.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
