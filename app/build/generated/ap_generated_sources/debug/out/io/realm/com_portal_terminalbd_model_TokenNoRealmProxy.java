package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.ImportFlag;
import io.realm.ProxyUtils;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsList;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.Property;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.internal.objectstore.OsObjectBuilder;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("all")
public class com_portal_terminalbd_model_TokenNoRealmProxy extends com.portal.terminalbd.model.TokenNo
    implements RealmObjectProxy, com_portal_terminalbd_model_TokenNoRealmProxyInterface {

    static final class TokenNoColumnInfo extends ColumnInfo {
        long maxColumnIndexValue;
        long tokenIdIndex;
        long tokenNameIndex;

        TokenNoColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("TokenNo");
            this.tokenIdIndex = addColumnDetails("tokenId", "tokenId", objectSchemaInfo);
            this.tokenNameIndex = addColumnDetails("tokenName", "tokenName", objectSchemaInfo);
            this.maxColumnIndexValue = objectSchemaInfo.getMaxColumnIndex();
        }

        TokenNoColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new TokenNoColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final TokenNoColumnInfo src = (TokenNoColumnInfo) rawSrc;
            final TokenNoColumnInfo dst = (TokenNoColumnInfo) rawDst;
            dst.tokenIdIndex = src.tokenIdIndex;
            dst.tokenNameIndex = src.tokenNameIndex;
            dst.maxColumnIndexValue = src.maxColumnIndexValue;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private TokenNoColumnInfo columnInfo;
    private ProxyState<com.portal.terminalbd.model.TokenNo> proxyState;

    com_portal_terminalbd_model_TokenNoRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (TokenNoColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.portal.terminalbd.model.TokenNo>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$tokenId() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.tokenIdIndex);
    }

    @Override
    public void realmSet$tokenId(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.tokenIdIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.tokenIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$tokenName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.tokenNameIndex);
    }

    @Override
    public void realmSet$tokenName(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.tokenNameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.tokenNameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.tokenNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.tokenNameIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("TokenNo", 2, 0);
        builder.addPersistedProperty("tokenId", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("tokenName", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static TokenNoColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new TokenNoColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "TokenNo";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "TokenNo";
    }

    @SuppressWarnings("cast")
    public static com.portal.terminalbd.model.TokenNo createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.portal.terminalbd.model.TokenNo obj = realm.createObjectInternal(com.portal.terminalbd.model.TokenNo.class, true, excludeFields);

        final com_portal_terminalbd_model_TokenNoRealmProxyInterface objProxy = (com_portal_terminalbd_model_TokenNoRealmProxyInterface) obj;
        if (json.has("tokenId")) {
            if (json.isNull("tokenId")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'tokenId' to null.");
            } else {
                objProxy.realmSet$tokenId((int) json.getInt("tokenId"));
            }
        }
        if (json.has("tokenName")) {
            if (json.isNull("tokenName")) {
                objProxy.realmSet$tokenName(null);
            } else {
                objProxy.realmSet$tokenName((String) json.getString("tokenName"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.portal.terminalbd.model.TokenNo createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.portal.terminalbd.model.TokenNo obj = new com.portal.terminalbd.model.TokenNo();
        final com_portal_terminalbd_model_TokenNoRealmProxyInterface objProxy = (com_portal_terminalbd_model_TokenNoRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("tokenId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$tokenId((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'tokenId' to null.");
                }
            } else if (name.equals("tokenName")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$tokenName((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$tokenName(null);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    private static com_portal_terminalbd_model_TokenNoRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.portal.terminalbd.model.TokenNo.class), false, Collections.<String>emptyList());
        io.realm.com_portal_terminalbd_model_TokenNoRealmProxy obj = new io.realm.com_portal_terminalbd_model_TokenNoRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.portal.terminalbd.model.TokenNo copyOrUpdate(Realm realm, TokenNoColumnInfo columnInfo, com.portal.terminalbd.model.TokenNo object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null) {
            final BaseRealm otherRealm = ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm();
            if (otherRealm.threadId != realm.threadId) {
                throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
            }
            if (otherRealm.getPath().equals(realm.getPath())) {
                return object;
            }
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.portal.terminalbd.model.TokenNo) cachedRealmObject;
        }

        return copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.portal.terminalbd.model.TokenNo copy(Realm realm, TokenNoColumnInfo columnInfo, com.portal.terminalbd.model.TokenNo newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.portal.terminalbd.model.TokenNo) cachedRealmObject;
        }

        com_portal_terminalbd_model_TokenNoRealmProxyInterface realmObjectSource = (com_portal_terminalbd_model_TokenNoRealmProxyInterface) newObject;

        Table table = realm.getTable(com.portal.terminalbd.model.TokenNo.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);

        // Add all non-"object reference" fields
        builder.addInteger(columnInfo.tokenIdIndex, realmObjectSource.realmGet$tokenId());
        builder.addString(columnInfo.tokenNameIndex, realmObjectSource.realmGet$tokenName());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_portal_terminalbd_model_TokenNoRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.portal.terminalbd.model.TokenNo object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.TokenNo.class);
        long tableNativePtr = table.getNativePtr();
        TokenNoColumnInfo columnInfo = (TokenNoColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.TokenNo.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.tokenIdIndex, rowIndex, ((com_portal_terminalbd_model_TokenNoRealmProxyInterface) object).realmGet$tokenId(), false);
        String realmGet$tokenName = ((com_portal_terminalbd_model_TokenNoRealmProxyInterface) object).realmGet$tokenName();
        if (realmGet$tokenName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tokenNameIndex, rowIndex, realmGet$tokenName, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.TokenNo.class);
        long tableNativePtr = table.getNativePtr();
        TokenNoColumnInfo columnInfo = (TokenNoColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.TokenNo.class);
        com.portal.terminalbd.model.TokenNo object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.TokenNo) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.tokenIdIndex, rowIndex, ((com_portal_terminalbd_model_TokenNoRealmProxyInterface) object).realmGet$tokenId(), false);
            String realmGet$tokenName = ((com_portal_terminalbd_model_TokenNoRealmProxyInterface) object).realmGet$tokenName();
            if (realmGet$tokenName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.tokenNameIndex, rowIndex, realmGet$tokenName, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.portal.terminalbd.model.TokenNo object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.TokenNo.class);
        long tableNativePtr = table.getNativePtr();
        TokenNoColumnInfo columnInfo = (TokenNoColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.TokenNo.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.tokenIdIndex, rowIndex, ((com_portal_terminalbd_model_TokenNoRealmProxyInterface) object).realmGet$tokenId(), false);
        String realmGet$tokenName = ((com_portal_terminalbd_model_TokenNoRealmProxyInterface) object).realmGet$tokenName();
        if (realmGet$tokenName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tokenNameIndex, rowIndex, realmGet$tokenName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.tokenNameIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.TokenNo.class);
        long tableNativePtr = table.getNativePtr();
        TokenNoColumnInfo columnInfo = (TokenNoColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.TokenNo.class);
        com.portal.terminalbd.model.TokenNo object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.TokenNo) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.tokenIdIndex, rowIndex, ((com_portal_terminalbd_model_TokenNoRealmProxyInterface) object).realmGet$tokenId(), false);
            String realmGet$tokenName = ((com_portal_terminalbd_model_TokenNoRealmProxyInterface) object).realmGet$tokenName();
            if (realmGet$tokenName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.tokenNameIndex, rowIndex, realmGet$tokenName, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.tokenNameIndex, rowIndex, false);
            }
        }
    }

    public static com.portal.terminalbd.model.TokenNo createDetachedCopy(com.portal.terminalbd.model.TokenNo realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.portal.terminalbd.model.TokenNo unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.portal.terminalbd.model.TokenNo();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.portal.terminalbd.model.TokenNo) cachedObject.object;
            }
            unmanagedObject = (com.portal.terminalbd.model.TokenNo) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_portal_terminalbd_model_TokenNoRealmProxyInterface unmanagedCopy = (com_portal_terminalbd_model_TokenNoRealmProxyInterface) unmanagedObject;
        com_portal_terminalbd_model_TokenNoRealmProxyInterface realmSource = (com_portal_terminalbd_model_TokenNoRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$tokenId(realmSource.realmGet$tokenId());
        unmanagedCopy.realmSet$tokenName(realmSource.realmGet$tokenName());

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("TokenNo = proxy[");
        stringBuilder.append("{tokenId:");
        stringBuilder.append(realmGet$tokenId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tokenName:");
        stringBuilder.append(realmGet$tokenName() != null ? realmGet$tokenName() : "null");
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
        com_portal_terminalbd_model_TokenNoRealmProxy aTokenNo = (com_portal_terminalbd_model_TokenNoRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aTokenNo.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTokenNo.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aTokenNo.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
