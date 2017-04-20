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

public class DirectionsRealmProxy extends com.example.rartamonov.translater.realm.Directions
    implements RealmObjectProxy, DirectionsRealmProxyInterface {

    static final class DirectionsColumnInfo extends ColumnInfo
        implements Cloneable {

        public long fromIndex;
        public long toIndex;

        DirectionsColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(2);
            this.fromIndex = getValidColumnIndex(path, table, "Directions", "from");
            indicesMap.put("from", this.fromIndex);
            this.toIndex = getValidColumnIndex(path, table, "Directions", "to");
            indicesMap.put("to", this.toIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final DirectionsColumnInfo otherInfo = (DirectionsColumnInfo) other;
            this.fromIndex = otherInfo.fromIndex;
            this.toIndex = otherInfo.toIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final DirectionsColumnInfo clone() {
            return (DirectionsColumnInfo) super.clone();
        }

    }
    private DirectionsColumnInfo columnInfo;
    private ProxyState<com.example.rartamonov.translater.realm.Directions> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("from");
        fieldNames.add("to");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    DirectionsRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (DirectionsColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.rartamonov.translater.realm.Directions>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$from() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.fromIndex);
    }

    @Override
    public void realmSet$from(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'from' to null.");
            }
            row.getTable().setString(columnInfo.fromIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'from' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.fromIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$to() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.toIndex);
    }

    @Override
    public void realmSet$to(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'to' to null.");
            }
            row.getTable().setString(columnInfo.toIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'to' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.toIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("Directions")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Directions");
            realmObjectSchema.add("from", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
            realmObjectSchema.add("to", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
            return realmObjectSchema;
        }
        return realmSchema.get("Directions");
    }

    public static DirectionsColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_Directions")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Directions' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_Directions");
        final long columnCount = table.getColumnCount();
        if (columnCount != 2) {
            if (columnCount < 2) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 2 but was " + columnCount);
            }
            if (allowExtraColumns) {
                RealmLog.debug("Field count is more than expected - expected 2 but was %1$d", columnCount);
            } else {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 2 but was " + columnCount);
            }
        }
        Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
        for (long i = 0; i < columnCount; i++) {
            columnTypes.put(table.getColumnName(i), table.getColumnType(i));
        }

        final DirectionsColumnInfo columnInfo = new DirectionsColumnInfo(sharedRealm.getPath(), table);

        if (table.hasPrimaryKey()) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key defined for field " + table.getColumnName(table.getPrimaryKey()) + " was removed.");
        }

        if (!columnTypes.containsKey("from")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'from' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("from") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'from' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.fromIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'from' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'from' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("to")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'to' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("to") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'to' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.toIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'to' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'to' or migrate using RealmObjectSchema.setNullable().");
        }

        return columnInfo;
    }

    public static String getTableName() {
        return "class_Directions";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.example.rartamonov.translater.realm.Directions createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.example.rartamonov.translater.realm.Directions obj = realm.createObjectInternal(com.example.rartamonov.translater.realm.Directions.class, true, excludeFields);
        if (json.has("from")) {
            if (json.isNull("from")) {
                ((DirectionsRealmProxyInterface) obj).realmSet$from(null);
            } else {
                ((DirectionsRealmProxyInterface) obj).realmSet$from((String) json.getString("from"));
            }
        }
        if (json.has("to")) {
            if (json.isNull("to")) {
                ((DirectionsRealmProxyInterface) obj).realmSet$to(null);
            } else {
                ((DirectionsRealmProxyInterface) obj).realmSet$to((String) json.getString("to"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.rartamonov.translater.realm.Directions createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        com.example.rartamonov.translater.realm.Directions obj = new com.example.rartamonov.translater.realm.Directions();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("from")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((DirectionsRealmProxyInterface) obj).realmSet$from(null);
                } else {
                    ((DirectionsRealmProxyInterface) obj).realmSet$from((String) reader.nextString());
                }
            } else if (name.equals("to")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((DirectionsRealmProxyInterface) obj).realmSet$to(null);
                } else {
                    ((DirectionsRealmProxyInterface) obj).realmSet$to((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.example.rartamonov.translater.realm.Directions copyOrUpdate(Realm realm, com.example.rartamonov.translater.realm.Directions object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.example.rartamonov.translater.realm.Directions) cachedRealmObject;
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static com.example.rartamonov.translater.realm.Directions copy(Realm realm, com.example.rartamonov.translater.realm.Directions newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.rartamonov.translater.realm.Directions) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.example.rartamonov.translater.realm.Directions realmObject = realm.createObjectInternal(com.example.rartamonov.translater.realm.Directions.class, false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((DirectionsRealmProxyInterface) realmObject).realmSet$from(((DirectionsRealmProxyInterface) newObject).realmGet$from());
            ((DirectionsRealmProxyInterface) realmObject).realmSet$to(((DirectionsRealmProxyInterface) newObject).realmGet$to());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.example.rartamonov.translater.realm.Directions object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.example.rartamonov.translater.realm.Directions.class);
        long tableNativePtr = table.getNativeTablePointer();
        DirectionsColumnInfo columnInfo = (DirectionsColumnInfo) realm.schema.getColumnInfo(com.example.rartamonov.translater.realm.Directions.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$from = ((DirectionsRealmProxyInterface)object).realmGet$from();
        if (realmGet$from != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.fromIndex, rowIndex, realmGet$from, false);
        }
        String realmGet$to = ((DirectionsRealmProxyInterface)object).realmGet$to();
        if (realmGet$to != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.toIndex, rowIndex, realmGet$to, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.rartamonov.translater.realm.Directions.class);
        long tableNativePtr = table.getNativeTablePointer();
        DirectionsColumnInfo columnInfo = (DirectionsColumnInfo) realm.schema.getColumnInfo(com.example.rartamonov.translater.realm.Directions.class);
        com.example.rartamonov.translater.realm.Directions object = null;
        while (objects.hasNext()) {
            object = (com.example.rartamonov.translater.realm.Directions) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$from = ((DirectionsRealmProxyInterface)object).realmGet$from();
                if (realmGet$from != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.fromIndex, rowIndex, realmGet$from, false);
                }
                String realmGet$to = ((DirectionsRealmProxyInterface)object).realmGet$to();
                if (realmGet$to != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.toIndex, rowIndex, realmGet$to, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.rartamonov.translater.realm.Directions object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.example.rartamonov.translater.realm.Directions.class);
        long tableNativePtr = table.getNativeTablePointer();
        DirectionsColumnInfo columnInfo = (DirectionsColumnInfo) realm.schema.getColumnInfo(com.example.rartamonov.translater.realm.Directions.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$from = ((DirectionsRealmProxyInterface)object).realmGet$from();
        if (realmGet$from != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.fromIndex, rowIndex, realmGet$from, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.fromIndex, rowIndex, false);
        }
        String realmGet$to = ((DirectionsRealmProxyInterface)object).realmGet$to();
        if (realmGet$to != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.toIndex, rowIndex, realmGet$to, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.toIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.rartamonov.translater.realm.Directions.class);
        long tableNativePtr = table.getNativeTablePointer();
        DirectionsColumnInfo columnInfo = (DirectionsColumnInfo) realm.schema.getColumnInfo(com.example.rartamonov.translater.realm.Directions.class);
        com.example.rartamonov.translater.realm.Directions object = null;
        while (objects.hasNext()) {
            object = (com.example.rartamonov.translater.realm.Directions) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$from = ((DirectionsRealmProxyInterface)object).realmGet$from();
                if (realmGet$from != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.fromIndex, rowIndex, realmGet$from, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.fromIndex, rowIndex, false);
                }
                String realmGet$to = ((DirectionsRealmProxyInterface)object).realmGet$to();
                if (realmGet$to != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.toIndex, rowIndex, realmGet$to, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.toIndex, rowIndex, false);
                }
            }
        }
    }

    public static com.example.rartamonov.translater.realm.Directions createDetachedCopy(com.example.rartamonov.translater.realm.Directions realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.rartamonov.translater.realm.Directions unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.rartamonov.translater.realm.Directions)cachedObject.object;
            } else {
                unmanagedObject = (com.example.rartamonov.translater.realm.Directions)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.example.rartamonov.translater.realm.Directions();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((DirectionsRealmProxyInterface) unmanagedObject).realmSet$from(((DirectionsRealmProxyInterface) realmObject).realmGet$from());
        ((DirectionsRealmProxyInterface) unmanagedObject).realmSet$to(((DirectionsRealmProxyInterface) realmObject).realmGet$to());
        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Directions = [");
        stringBuilder.append("{from:");
        stringBuilder.append(realmGet$from());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{to:");
        stringBuilder.append(realmGet$to());
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
        DirectionsRealmProxy aDirections = (DirectionsRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aDirections.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aDirections.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aDirections.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
