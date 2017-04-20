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

public class LangsRealmProxy extends com.example.rartamonov.translater.realm.Langs
    implements RealmObjectProxy, LangsRealmProxyInterface {

    static final class LangsColumnInfo extends ColumnInfo
        implements Cloneable {

        public long codeIndex;
        public long definitionIndex;

        LangsColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(2);
            this.codeIndex = getValidColumnIndex(path, table, "Langs", "code");
            indicesMap.put("code", this.codeIndex);
            this.definitionIndex = getValidColumnIndex(path, table, "Langs", "definition");
            indicesMap.put("definition", this.definitionIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final LangsColumnInfo otherInfo = (LangsColumnInfo) other;
            this.codeIndex = otherInfo.codeIndex;
            this.definitionIndex = otherInfo.definitionIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final LangsColumnInfo clone() {
            return (LangsColumnInfo) super.clone();
        }

    }
    private LangsColumnInfo columnInfo;
    private ProxyState<com.example.rartamonov.translater.realm.Langs> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("code");
        fieldNames.add("definition");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    LangsRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (LangsColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.rartamonov.translater.realm.Langs>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$code() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.codeIndex);
    }

    @Override
    public void realmSet$code(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'code' to null.");
            }
            row.getTable().setString(columnInfo.codeIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'code' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.codeIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$definition() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.definitionIndex);
    }

    @Override
    public void realmSet$definition(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'definition' to null.");
            }
            row.getTable().setString(columnInfo.definitionIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'definition' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.definitionIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("Langs")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Langs");
            realmObjectSchema.add("code", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
            realmObjectSchema.add("definition", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
            return realmObjectSchema;
        }
        return realmSchema.get("Langs");
    }

    public static LangsColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_Langs")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Langs' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_Langs");
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

        final LangsColumnInfo columnInfo = new LangsColumnInfo(sharedRealm.getPath(), table);

        if (table.hasPrimaryKey()) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key defined for field " + table.getColumnName(table.getPrimaryKey()) + " was removed.");
        }

        if (!columnTypes.containsKey("code")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'code' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("code") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'code' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.codeIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'code' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'code' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("definition")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'definition' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("definition") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'definition' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.definitionIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'definition' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'definition' or migrate using RealmObjectSchema.setNullable().");
        }

        return columnInfo;
    }

    public static String getTableName() {
        return "class_Langs";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.example.rartamonov.translater.realm.Langs createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.example.rartamonov.translater.realm.Langs obj = realm.createObjectInternal(com.example.rartamonov.translater.realm.Langs.class, true, excludeFields);
        if (json.has("code")) {
            if (json.isNull("code")) {
                ((LangsRealmProxyInterface) obj).realmSet$code(null);
            } else {
                ((LangsRealmProxyInterface) obj).realmSet$code((String) json.getString("code"));
            }
        }
        if (json.has("definition")) {
            if (json.isNull("definition")) {
                ((LangsRealmProxyInterface) obj).realmSet$definition(null);
            } else {
                ((LangsRealmProxyInterface) obj).realmSet$definition((String) json.getString("definition"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.rartamonov.translater.realm.Langs createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        com.example.rartamonov.translater.realm.Langs obj = new com.example.rartamonov.translater.realm.Langs();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("code")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((LangsRealmProxyInterface) obj).realmSet$code(null);
                } else {
                    ((LangsRealmProxyInterface) obj).realmSet$code((String) reader.nextString());
                }
            } else if (name.equals("definition")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((LangsRealmProxyInterface) obj).realmSet$definition(null);
                } else {
                    ((LangsRealmProxyInterface) obj).realmSet$definition((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.example.rartamonov.translater.realm.Langs copyOrUpdate(Realm realm, com.example.rartamonov.translater.realm.Langs object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.example.rartamonov.translater.realm.Langs) cachedRealmObject;
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static com.example.rartamonov.translater.realm.Langs copy(Realm realm, com.example.rartamonov.translater.realm.Langs newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.rartamonov.translater.realm.Langs) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.example.rartamonov.translater.realm.Langs realmObject = realm.createObjectInternal(com.example.rartamonov.translater.realm.Langs.class, false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((LangsRealmProxyInterface) realmObject).realmSet$code(((LangsRealmProxyInterface) newObject).realmGet$code());
            ((LangsRealmProxyInterface) realmObject).realmSet$definition(((LangsRealmProxyInterface) newObject).realmGet$definition());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.example.rartamonov.translater.realm.Langs object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.example.rartamonov.translater.realm.Langs.class);
        long tableNativePtr = table.getNativeTablePointer();
        LangsColumnInfo columnInfo = (LangsColumnInfo) realm.schema.getColumnInfo(com.example.rartamonov.translater.realm.Langs.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$code = ((LangsRealmProxyInterface)object).realmGet$code();
        if (realmGet$code != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.codeIndex, rowIndex, realmGet$code, false);
        }
        String realmGet$definition = ((LangsRealmProxyInterface)object).realmGet$definition();
        if (realmGet$definition != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.definitionIndex, rowIndex, realmGet$definition, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.rartamonov.translater.realm.Langs.class);
        long tableNativePtr = table.getNativeTablePointer();
        LangsColumnInfo columnInfo = (LangsColumnInfo) realm.schema.getColumnInfo(com.example.rartamonov.translater.realm.Langs.class);
        com.example.rartamonov.translater.realm.Langs object = null;
        while (objects.hasNext()) {
            object = (com.example.rartamonov.translater.realm.Langs) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$code = ((LangsRealmProxyInterface)object).realmGet$code();
                if (realmGet$code != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.codeIndex, rowIndex, realmGet$code, false);
                }
                String realmGet$definition = ((LangsRealmProxyInterface)object).realmGet$definition();
                if (realmGet$definition != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.definitionIndex, rowIndex, realmGet$definition, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.rartamonov.translater.realm.Langs object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.example.rartamonov.translater.realm.Langs.class);
        long tableNativePtr = table.getNativeTablePointer();
        LangsColumnInfo columnInfo = (LangsColumnInfo) realm.schema.getColumnInfo(com.example.rartamonov.translater.realm.Langs.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$code = ((LangsRealmProxyInterface)object).realmGet$code();
        if (realmGet$code != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.codeIndex, rowIndex, realmGet$code, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.codeIndex, rowIndex, false);
        }
        String realmGet$definition = ((LangsRealmProxyInterface)object).realmGet$definition();
        if (realmGet$definition != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.definitionIndex, rowIndex, realmGet$definition, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.definitionIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.rartamonov.translater.realm.Langs.class);
        long tableNativePtr = table.getNativeTablePointer();
        LangsColumnInfo columnInfo = (LangsColumnInfo) realm.schema.getColumnInfo(com.example.rartamonov.translater.realm.Langs.class);
        com.example.rartamonov.translater.realm.Langs object = null;
        while (objects.hasNext()) {
            object = (com.example.rartamonov.translater.realm.Langs) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$code = ((LangsRealmProxyInterface)object).realmGet$code();
                if (realmGet$code != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.codeIndex, rowIndex, realmGet$code, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.codeIndex, rowIndex, false);
                }
                String realmGet$definition = ((LangsRealmProxyInterface)object).realmGet$definition();
                if (realmGet$definition != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.definitionIndex, rowIndex, realmGet$definition, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.definitionIndex, rowIndex, false);
                }
            }
        }
    }

    public static com.example.rartamonov.translater.realm.Langs createDetachedCopy(com.example.rartamonov.translater.realm.Langs realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.rartamonov.translater.realm.Langs unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.rartamonov.translater.realm.Langs)cachedObject.object;
            } else {
                unmanagedObject = (com.example.rartamonov.translater.realm.Langs)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.example.rartamonov.translater.realm.Langs();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((LangsRealmProxyInterface) unmanagedObject).realmSet$code(((LangsRealmProxyInterface) realmObject).realmGet$code());
        ((LangsRealmProxyInterface) unmanagedObject).realmSet$definition(((LangsRealmProxyInterface) realmObject).realmGet$definition());
        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Langs = [");
        stringBuilder.append("{code:");
        stringBuilder.append(realmGet$code());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{definition:");
        stringBuilder.append(realmGet$definition());
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
        LangsRealmProxy aLangs = (LangsRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aLangs.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aLangs.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aLangs.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
