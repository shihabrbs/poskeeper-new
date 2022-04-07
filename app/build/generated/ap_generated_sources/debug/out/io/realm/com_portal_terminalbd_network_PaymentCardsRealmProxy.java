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
public class com_portal_terminalbd_network_PaymentCardsRealmProxy extends com.portal.terminalbd.network.PaymentCards
    implements RealmObjectProxy, com_portal_terminalbd_network_PaymentCardsRealmProxyInterface {

    static final class PaymentCardsColumnInfo extends ColumnInfo {
        long maxColumnIndexValue;
        long itemIdIndex;
        long nameIndex;

        PaymentCardsColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("PaymentCards");
            this.itemIdIndex = addColumnDetails("itemId", "itemId", objectSchemaInfo);
            this.nameIndex = addColumnDetails("name", "name", objectSchemaInfo);
            this.maxColumnIndexValue = objectSchemaInfo.getMaxColumnIndex();
        }

        PaymentCardsColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new PaymentCardsColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final PaymentCardsColumnInfo src = (PaymentCardsColumnInfo) rawSrc;
            final PaymentCardsColumnInfo dst = (PaymentCardsColumnInfo) rawDst;
            dst.itemIdIndex = src.itemIdIndex;
            dst.nameIndex = src.nameIndex;
            dst.maxColumnIndexValue = src.maxColumnIndexValue;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private PaymentCardsColumnInfo columnInfo;
    private ProxyState<com.portal.terminalbd.network.PaymentCards> proxyState;

    com_portal_terminalbd_network_PaymentCardsRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (PaymentCardsColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.portal.terminalbd.network.PaymentCards>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
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

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("PaymentCards", 2, 0);
        builder.addPersistedProperty("itemId", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static PaymentCardsColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new PaymentCardsColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "PaymentCards";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "PaymentCards";
    }

    @SuppressWarnings("cast")
    public static com.portal.terminalbd.network.PaymentCards createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.portal.terminalbd.network.PaymentCards obj = realm.createObjectInternal(com.portal.terminalbd.network.PaymentCards.class, true, excludeFields);

        final com_portal_terminalbd_network_PaymentCardsRealmProxyInterface objProxy = (com_portal_terminalbd_network_PaymentCardsRealmProxyInterface) obj;
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
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.portal.terminalbd.network.PaymentCards createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.portal.terminalbd.network.PaymentCards obj = new com.portal.terminalbd.network.PaymentCards();
        final com_portal_terminalbd_network_PaymentCardsRealmProxyInterface objProxy = (com_portal_terminalbd_network_PaymentCardsRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
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
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    private static com_portal_terminalbd_network_PaymentCardsRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.portal.terminalbd.network.PaymentCards.class), false, Collections.<String>emptyList());
        io.realm.com_portal_terminalbd_network_PaymentCardsRealmProxy obj = new io.realm.com_portal_terminalbd_network_PaymentCardsRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.portal.terminalbd.network.PaymentCards copyOrUpdate(Realm realm, PaymentCardsColumnInfo columnInfo, com.portal.terminalbd.network.PaymentCards object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.portal.terminalbd.network.PaymentCards) cachedRealmObject;
        }

        return copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.portal.terminalbd.network.PaymentCards copy(Realm realm, PaymentCardsColumnInfo columnInfo, com.portal.terminalbd.network.PaymentCards newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.portal.terminalbd.network.PaymentCards) cachedRealmObject;
        }

        com_portal_terminalbd_network_PaymentCardsRealmProxyInterface realmObjectSource = (com_portal_terminalbd_network_PaymentCardsRealmProxyInterface) newObject;

        Table table = realm.getTable(com.portal.terminalbd.network.PaymentCards.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);

        // Add all non-"object reference" fields
        builder.addInteger(columnInfo.itemIdIndex, realmObjectSource.realmGet$itemId());
        builder.addString(columnInfo.nameIndex, realmObjectSource.realmGet$name());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_portal_terminalbd_network_PaymentCardsRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.portal.terminalbd.network.PaymentCards object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.network.PaymentCards.class);
        long tableNativePtr = table.getNativePtr();
        PaymentCardsColumnInfo columnInfo = (PaymentCardsColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.network.PaymentCards.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.itemIdIndex, rowIndex, ((com_portal_terminalbd_network_PaymentCardsRealmProxyInterface) object).realmGet$itemId(), false);
        String realmGet$name = ((com_portal_terminalbd_network_PaymentCardsRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.network.PaymentCards.class);
        long tableNativePtr = table.getNativePtr();
        PaymentCardsColumnInfo columnInfo = (PaymentCardsColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.network.PaymentCards.class);
        com.portal.terminalbd.network.PaymentCards object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.network.PaymentCards) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.itemIdIndex, rowIndex, ((com_portal_terminalbd_network_PaymentCardsRealmProxyInterface) object).realmGet$itemId(), false);
            String realmGet$name = ((com_portal_terminalbd_network_PaymentCardsRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.portal.terminalbd.network.PaymentCards object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.network.PaymentCards.class);
        long tableNativePtr = table.getNativePtr();
        PaymentCardsColumnInfo columnInfo = (PaymentCardsColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.network.PaymentCards.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.itemIdIndex, rowIndex, ((com_portal_terminalbd_network_PaymentCardsRealmProxyInterface) object).realmGet$itemId(), false);
        String realmGet$name = ((com_portal_terminalbd_network_PaymentCardsRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.network.PaymentCards.class);
        long tableNativePtr = table.getNativePtr();
        PaymentCardsColumnInfo columnInfo = (PaymentCardsColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.network.PaymentCards.class);
        com.portal.terminalbd.network.PaymentCards object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.network.PaymentCards) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.itemIdIndex, rowIndex, ((com_portal_terminalbd_network_PaymentCardsRealmProxyInterface) object).realmGet$itemId(), false);
            String realmGet$name = ((com_portal_terminalbd_network_PaymentCardsRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
            }
        }
    }

    public static com.portal.terminalbd.network.PaymentCards createDetachedCopy(com.portal.terminalbd.network.PaymentCards realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.portal.terminalbd.network.PaymentCards unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.portal.terminalbd.network.PaymentCards();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.portal.terminalbd.network.PaymentCards) cachedObject.object;
            }
            unmanagedObject = (com.portal.terminalbd.network.PaymentCards) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_portal_terminalbd_network_PaymentCardsRealmProxyInterface unmanagedCopy = (com_portal_terminalbd_network_PaymentCardsRealmProxyInterface) unmanagedObject;
        com_portal_terminalbd_network_PaymentCardsRealmProxyInterface realmSource = (com_portal_terminalbd_network_PaymentCardsRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$itemId(realmSource.realmGet$itemId());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("PaymentCards = proxy[");
        stringBuilder.append("{itemId:");
        stringBuilder.append(realmGet$itemId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
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
        com_portal_terminalbd_network_PaymentCardsRealmProxy aPaymentCards = (com_portal_terminalbd_network_PaymentCardsRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aPaymentCards.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aPaymentCards.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aPaymentCards.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
