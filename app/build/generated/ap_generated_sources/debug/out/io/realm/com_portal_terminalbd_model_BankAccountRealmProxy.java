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
public class com_portal_terminalbd_model_BankAccountRealmProxy extends com.portal.terminalbd.model.BankAccount
    implements RealmObjectProxy, com_portal_terminalbd_model_BankAccountRealmProxyInterface {

    static final class BankAccountColumnInfo extends ColumnInfo {
        long maxColumnIndexValue;
        long globalIdIndex;
        long itemIdIndex;
        long nameIndex;
        long service_chargeIndex;

        BankAccountColumnInfo(OsSchemaInfo schemaInfo) {
            super(4);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("BankAccount");
            this.globalIdIndex = addColumnDetails("globalId", "globalId", objectSchemaInfo);
            this.itemIdIndex = addColumnDetails("itemId", "itemId", objectSchemaInfo);
            this.nameIndex = addColumnDetails("name", "name", objectSchemaInfo);
            this.service_chargeIndex = addColumnDetails("service_charge", "service_charge", objectSchemaInfo);
            this.maxColumnIndexValue = objectSchemaInfo.getMaxColumnIndex();
        }

        BankAccountColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new BankAccountColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final BankAccountColumnInfo src = (BankAccountColumnInfo) rawSrc;
            final BankAccountColumnInfo dst = (BankAccountColumnInfo) rawDst;
            dst.globalIdIndex = src.globalIdIndex;
            dst.itemIdIndex = src.itemIdIndex;
            dst.nameIndex = src.nameIndex;
            dst.service_chargeIndex = src.service_chargeIndex;
            dst.maxColumnIndexValue = src.maxColumnIndexValue;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private BankAccountColumnInfo columnInfo;
    private ProxyState<com.portal.terminalbd.model.BankAccount> proxyState;

    com_portal_terminalbd_model_BankAccountRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (BankAccountColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.portal.terminalbd.model.BankAccount>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$globalId() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.globalIdIndex);
    }

    @Override
    public void realmSet$globalId(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.globalIdIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.globalIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$itemId() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.itemIdIndex);
    }

    @Override
    public void realmSet$itemId(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.itemIdIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.itemIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$name() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nameIndex);
    }

    @Override
    public void realmSet$name(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.nameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.nameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.nameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.nameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$service_charge() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.service_chargeIndex);
    }

    @Override
    public void realmSet$service_charge(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.service_chargeIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.service_chargeIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.service_chargeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.service_chargeIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("BankAccount", 4, 0);
        builder.addPersistedProperty("globalId", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("itemId", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("service_charge", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static BankAccountColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new BankAccountColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "BankAccount";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "BankAccount";
    }

    @SuppressWarnings("cast")
    public static com.portal.terminalbd.model.BankAccount createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.portal.terminalbd.model.BankAccount obj = realm.createObjectInternal(com.portal.terminalbd.model.BankAccount.class, true, excludeFields);

        final com_portal_terminalbd_model_BankAccountRealmProxyInterface objProxy = (com_portal_terminalbd_model_BankAccountRealmProxyInterface) obj;
        if (json.has("globalId")) {
            if (json.isNull("globalId")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'globalId' to null.");
            } else {
                objProxy.realmSet$globalId((int) json.getInt("globalId"));
            }
        }
        if (json.has("itemId")) {
            if (json.isNull("itemId")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'itemId' to null.");
            } else {
                objProxy.realmSet$itemId((int) json.getInt("itemId"));
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("service_charge")) {
            if (json.isNull("service_charge")) {
                objProxy.realmSet$service_charge(null);
            } else {
                objProxy.realmSet$service_charge((String) json.getString("service_charge"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.portal.terminalbd.model.BankAccount createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.portal.terminalbd.model.BankAccount obj = new com.portal.terminalbd.model.BankAccount();
        final com_portal_terminalbd_model_BankAccountRealmProxyInterface objProxy = (com_portal_terminalbd_model_BankAccountRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("globalId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$globalId((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'globalId' to null.");
                }
            } else if (name.equals("itemId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$itemId((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'itemId' to null.");
                }
            } else if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (name.equals("service_charge")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$service_charge((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$service_charge(null);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    private static com_portal_terminalbd_model_BankAccountRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.portal.terminalbd.model.BankAccount.class), false, Collections.<String>emptyList());
        io.realm.com_portal_terminalbd_model_BankAccountRealmProxy obj = new io.realm.com_portal_terminalbd_model_BankAccountRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.portal.terminalbd.model.BankAccount copyOrUpdate(Realm realm, BankAccountColumnInfo columnInfo, com.portal.terminalbd.model.BankAccount object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.portal.terminalbd.model.BankAccount) cachedRealmObject;
        }

        return copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.portal.terminalbd.model.BankAccount copy(Realm realm, BankAccountColumnInfo columnInfo, com.portal.terminalbd.model.BankAccount newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.portal.terminalbd.model.BankAccount) cachedRealmObject;
        }

        com_portal_terminalbd_model_BankAccountRealmProxyInterface realmObjectSource = (com_portal_terminalbd_model_BankAccountRealmProxyInterface) newObject;

        Table table = realm.getTable(com.portal.terminalbd.model.BankAccount.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);

        // Add all non-"object reference" fields
        builder.addInteger(columnInfo.globalIdIndex, realmObjectSource.realmGet$globalId());
        builder.addInteger(columnInfo.itemIdIndex, realmObjectSource.realmGet$itemId());
        builder.addString(columnInfo.nameIndex, realmObjectSource.realmGet$name());
        builder.addString(columnInfo.service_chargeIndex, realmObjectSource.realmGet$service_charge());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_portal_terminalbd_model_BankAccountRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.portal.terminalbd.model.BankAccount object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.BankAccount.class);
        long tableNativePtr = table.getNativePtr();
        BankAccountColumnInfo columnInfo = (BankAccountColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.BankAccount.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.globalIdIndex, rowIndex, ((com_portal_terminalbd_model_BankAccountRealmProxyInterface) object).realmGet$globalId(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.itemIdIndex, rowIndex, ((com_portal_terminalbd_model_BankAccountRealmProxyInterface) object).realmGet$itemId(), false);
        String realmGet$name = ((com_portal_terminalbd_model_BankAccountRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        String realmGet$service_charge = ((com_portal_terminalbd_model_BankAccountRealmProxyInterface) object).realmGet$service_charge();
        if (realmGet$service_charge != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.service_chargeIndex, rowIndex, realmGet$service_charge, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.BankAccount.class);
        long tableNativePtr = table.getNativePtr();
        BankAccountColumnInfo columnInfo = (BankAccountColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.BankAccount.class);
        com.portal.terminalbd.model.BankAccount object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.BankAccount) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.globalIdIndex, rowIndex, ((com_portal_terminalbd_model_BankAccountRealmProxyInterface) object).realmGet$globalId(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.itemIdIndex, rowIndex, ((com_portal_terminalbd_model_BankAccountRealmProxyInterface) object).realmGet$itemId(), false);
            String realmGet$name = ((com_portal_terminalbd_model_BankAccountRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            }
            String realmGet$service_charge = ((com_portal_terminalbd_model_BankAccountRealmProxyInterface) object).realmGet$service_charge();
            if (realmGet$service_charge != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.service_chargeIndex, rowIndex, realmGet$service_charge, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.portal.terminalbd.model.BankAccount object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.BankAccount.class);
        long tableNativePtr = table.getNativePtr();
        BankAccountColumnInfo columnInfo = (BankAccountColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.BankAccount.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.globalIdIndex, rowIndex, ((com_portal_terminalbd_model_BankAccountRealmProxyInterface) object).realmGet$globalId(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.itemIdIndex, rowIndex, ((com_portal_terminalbd_model_BankAccountRealmProxyInterface) object).realmGet$itemId(), false);
        String realmGet$name = ((com_portal_terminalbd_model_BankAccountRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        String realmGet$service_charge = ((com_portal_terminalbd_model_BankAccountRealmProxyInterface) object).realmGet$service_charge();
        if (realmGet$service_charge != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.service_chargeIndex, rowIndex, realmGet$service_charge, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.service_chargeIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.BankAccount.class);
        long tableNativePtr = table.getNativePtr();
        BankAccountColumnInfo columnInfo = (BankAccountColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.BankAccount.class);
        com.portal.terminalbd.model.BankAccount object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.BankAccount) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.globalIdIndex, rowIndex, ((com_portal_terminalbd_model_BankAccountRealmProxyInterface) object).realmGet$globalId(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.itemIdIndex, rowIndex, ((com_portal_terminalbd_model_BankAccountRealmProxyInterface) object).realmGet$itemId(), false);
            String realmGet$name = ((com_portal_terminalbd_model_BankAccountRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
            }
            String realmGet$service_charge = ((com_portal_terminalbd_model_BankAccountRealmProxyInterface) object).realmGet$service_charge();
            if (realmGet$service_charge != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.service_chargeIndex, rowIndex, realmGet$service_charge, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.service_chargeIndex, rowIndex, false);
            }
        }
    }

    public static com.portal.terminalbd.model.BankAccount createDetachedCopy(com.portal.terminalbd.model.BankAccount realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.portal.terminalbd.model.BankAccount unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.portal.terminalbd.model.BankAccount();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.portal.terminalbd.model.BankAccount) cachedObject.object;
            }
            unmanagedObject = (com.portal.terminalbd.model.BankAccount) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_portal_terminalbd_model_BankAccountRealmProxyInterface unmanagedCopy = (com_portal_terminalbd_model_BankAccountRealmProxyInterface) unmanagedObject;
        com_portal_terminalbd_model_BankAccountRealmProxyInterface realmSource = (com_portal_terminalbd_model_BankAccountRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$globalId(realmSource.realmGet$globalId());
        unmanagedCopy.realmSet$itemId(realmSource.realmGet$itemId());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$service_charge(realmSource.realmGet$service_charge());

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("BankAccount = proxy[");
        stringBuilder.append("{globalId:");
        stringBuilder.append(realmGet$globalId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{itemId:");
        stringBuilder.append(realmGet$itemId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{service_charge:");
        stringBuilder.append(realmGet$service_charge() != null ? realmGet$service_charge() : "null");
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
        com_portal_terminalbd_model_BankAccountRealmProxy aBankAccount = (com_portal_terminalbd_model_BankAccountRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aBankAccount.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aBankAccount.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aBankAccount.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
