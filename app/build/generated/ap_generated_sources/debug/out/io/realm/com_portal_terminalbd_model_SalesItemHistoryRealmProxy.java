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
public class com_portal_terminalbd_model_SalesItemHistoryRealmProxy extends com.portal.terminalbd.model.SalesItemHistory
    implements RealmObjectProxy, com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface {

    static final class SalesItemHistoryColumnInfo extends ColumnInfo {
        long maxColumnIndexValue;
        long idIndex;
        long salesIdIndex;
        long stockIdIndex;
        long unitPriceIndex;
        long quantityIndex;
        long subTotalIndex;

        SalesItemHistoryColumnInfo(OsSchemaInfo schemaInfo) {
            super(6);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("SalesItemHistory");
            this.idIndex = addColumnDetails("id", "id", objectSchemaInfo);
            this.salesIdIndex = addColumnDetails("salesId", "salesId", objectSchemaInfo);
            this.stockIdIndex = addColumnDetails("stockId", "stockId", objectSchemaInfo);
            this.unitPriceIndex = addColumnDetails("unitPrice", "unitPrice", objectSchemaInfo);
            this.quantityIndex = addColumnDetails("quantity", "quantity", objectSchemaInfo);
            this.subTotalIndex = addColumnDetails("subTotal", "subTotal", objectSchemaInfo);
            this.maxColumnIndexValue = objectSchemaInfo.getMaxColumnIndex();
        }

        SalesItemHistoryColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new SalesItemHistoryColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final SalesItemHistoryColumnInfo src = (SalesItemHistoryColumnInfo) rawSrc;
            final SalesItemHistoryColumnInfo dst = (SalesItemHistoryColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.salesIdIndex = src.salesIdIndex;
            dst.stockIdIndex = src.stockIdIndex;
            dst.unitPriceIndex = src.unitPriceIndex;
            dst.quantityIndex = src.quantityIndex;
            dst.subTotalIndex = src.subTotalIndex;
            dst.maxColumnIndexValue = src.maxColumnIndexValue;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private SalesItemHistoryColumnInfo columnInfo;
    private ProxyState<com.portal.terminalbd.model.SalesItemHistory> proxyState;

    com_portal_terminalbd_model_SalesItemHistoryRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (SalesItemHistoryColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.portal.terminalbd.model.SalesItemHistory>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.idIndex);
    }

    @Override
    public void realmSet$id(String value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'id' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$salesId() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.salesIdIndex);
    }

    @Override
    public void realmSet$salesId(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.salesIdIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.salesIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$stockId() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.stockIdIndex);
    }

    @Override
    public void realmSet$stockId(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.stockIdIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.stockIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public double realmGet$unitPrice() {
        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.unitPriceIndex);
    }

    @Override
    public void realmSet$unitPrice(double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.unitPriceIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.unitPriceIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$quantity() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.quantityIndex);
    }

    @Override
    public void realmSet$quantity(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.quantityIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.quantityIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$subTotal() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.subTotalIndex);
    }

    @Override
    public void realmSet$subTotal(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.subTotalIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.subTotalIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("SalesItemHistory", 6, 0);
        builder.addPersistedProperty("id", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("salesId", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("stockId", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("unitPrice", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("quantity", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("subTotal", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static SalesItemHistoryColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new SalesItemHistoryColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "SalesItemHistory";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "SalesItemHistory";
    }

    @SuppressWarnings("cast")
    public static com.portal.terminalbd.model.SalesItemHistory createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.portal.terminalbd.model.SalesItemHistory obj = null;
        if (update) {
            Table table = realm.getTable(com.portal.terminalbd.model.SalesItemHistory.class);
            SalesItemHistoryColumnInfo columnInfo = (SalesItemHistoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SalesItemHistory.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = Table.NO_MATCH;
            if (json.isNull("id")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("id"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SalesItemHistory.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_portal_terminalbd_model_SalesItemHistoryRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.com_portal_terminalbd_model_SalesItemHistoryRealmProxy) realm.createObjectInternal(com.portal.terminalbd.model.SalesItemHistory.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_portal_terminalbd_model_SalesItemHistoryRealmProxy) realm.createObjectInternal(com.portal.terminalbd.model.SalesItemHistory.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface objProxy = (com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) obj;
        if (json.has("salesId")) {
            if (json.isNull("salesId")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'salesId' to null.");
            } else {
                objProxy.realmSet$salesId((int) json.getInt("salesId"));
            }
        }
        if (json.has("stockId")) {
            if (json.isNull("stockId")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'stockId' to null.");
            } else {
                objProxy.realmSet$stockId((int) json.getInt("stockId"));
            }
        }
        if (json.has("unitPrice")) {
            if (json.isNull("unitPrice")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'unitPrice' to null.");
            } else {
                objProxy.realmSet$unitPrice((double) json.getDouble("unitPrice"));
            }
        }
        if (json.has("quantity")) {
            if (json.isNull("quantity")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'quantity' to null.");
            } else {
                objProxy.realmSet$quantity((int) json.getInt("quantity"));
            }
        }
        if (json.has("subTotal")) {
            if (json.isNull("subTotal")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'subTotal' to null.");
            } else {
                objProxy.realmSet$subTotal((int) json.getInt("subTotal"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.portal.terminalbd.model.SalesItemHistory createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.portal.terminalbd.model.SalesItemHistory obj = new com.portal.terminalbd.model.SalesItemHistory();
        final com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface objProxy = (com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$id((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$id(null);
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("salesId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$salesId((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'salesId' to null.");
                }
            } else if (name.equals("stockId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$stockId((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'stockId' to null.");
                }
            } else if (name.equals("unitPrice")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$unitPrice((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'unitPrice' to null.");
                }
            } else if (name.equals("quantity")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$quantity((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'quantity' to null.");
                }
            } else if (name.equals("subTotal")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$subTotal((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'subTotal' to null.");
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
        }
        return realm.copyToRealm(obj);
    }

    private static com_portal_terminalbd_model_SalesItemHistoryRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SalesItemHistory.class), false, Collections.<String>emptyList());
        io.realm.com_portal_terminalbd_model_SalesItemHistoryRealmProxy obj = new io.realm.com_portal_terminalbd_model_SalesItemHistoryRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.portal.terminalbd.model.SalesItemHistory copyOrUpdate(Realm realm, SalesItemHistoryColumnInfo columnInfo, com.portal.terminalbd.model.SalesItemHistory object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.portal.terminalbd.model.SalesItemHistory) cachedRealmObject;
        }

        com.portal.terminalbd.model.SalesItemHistory realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.portal.terminalbd.model.SalesItemHistory.class);
            long pkColumnIndex = columnInfo.idIndex;
            String value = ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$id();
            long rowIndex = Table.NO_MATCH;
            if (value == null) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, value);
            }
            if (rowIndex == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), columnInfo, false, Collections.<String> emptyList());
                    realmObject = new io.realm.com_portal_terminalbd_model_SalesItemHistoryRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.portal.terminalbd.model.SalesItemHistory copy(Realm realm, SalesItemHistoryColumnInfo columnInfo, com.portal.terminalbd.model.SalesItemHistory newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.portal.terminalbd.model.SalesItemHistory) cachedRealmObject;
        }

        com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface realmObjectSource = (com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) newObject;

        Table table = realm.getTable(com.portal.terminalbd.model.SalesItemHistory.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);

        // Add all non-"object reference" fields
        builder.addString(columnInfo.idIndex, realmObjectSource.realmGet$id());
        builder.addInteger(columnInfo.salesIdIndex, realmObjectSource.realmGet$salesId());
        builder.addInteger(columnInfo.stockIdIndex, realmObjectSource.realmGet$stockId());
        builder.addDouble(columnInfo.unitPriceIndex, realmObjectSource.realmGet$unitPrice());
        builder.addInteger(columnInfo.quantityIndex, realmObjectSource.realmGet$quantity());
        builder.addInteger(columnInfo.subTotalIndex, realmObjectSource.realmGet$subTotal());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_portal_terminalbd_model_SalesItemHistoryRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.portal.terminalbd.model.SalesItemHistory object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.SalesItemHistory.class);
        long tableNativePtr = table.getNativePtr();
        SalesItemHistoryColumnInfo columnInfo = (SalesItemHistoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SalesItemHistory.class);
        long pkColumnIndex = columnInfo.idIndex;
        String primaryKeyValue = ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$id();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.salesIdIndex, rowIndex, ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$salesId(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.stockIdIndex, rowIndex, ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$stockId(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.unitPriceIndex, rowIndex, ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$unitPrice(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.quantityIndex, rowIndex, ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$quantity(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.subTotalIndex, rowIndex, ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$subTotal(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.SalesItemHistory.class);
        long tableNativePtr = table.getNativePtr();
        SalesItemHistoryColumnInfo columnInfo = (SalesItemHistoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SalesItemHistory.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.portal.terminalbd.model.SalesItemHistory object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.SalesItemHistory) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            String primaryKeyValue = ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$id();
            long rowIndex = Table.NO_MATCH;
            if (primaryKeyValue == null) {
                rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
            } else {
                rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.salesIdIndex, rowIndex, ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$salesId(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.stockIdIndex, rowIndex, ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$stockId(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.unitPriceIndex, rowIndex, ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$unitPrice(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.quantityIndex, rowIndex, ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$quantity(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.subTotalIndex, rowIndex, ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$subTotal(), false);
        }
    }

    public static long insertOrUpdate(Realm realm, com.portal.terminalbd.model.SalesItemHistory object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.SalesItemHistory.class);
        long tableNativePtr = table.getNativePtr();
        SalesItemHistoryColumnInfo columnInfo = (SalesItemHistoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SalesItemHistory.class);
        long pkColumnIndex = columnInfo.idIndex;
        String primaryKeyValue = ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$id();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
        }
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.salesIdIndex, rowIndex, ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$salesId(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.stockIdIndex, rowIndex, ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$stockId(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.unitPriceIndex, rowIndex, ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$unitPrice(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.quantityIndex, rowIndex, ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$quantity(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.subTotalIndex, rowIndex, ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$subTotal(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.SalesItemHistory.class);
        long tableNativePtr = table.getNativePtr();
        SalesItemHistoryColumnInfo columnInfo = (SalesItemHistoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SalesItemHistory.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.portal.terminalbd.model.SalesItemHistory object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.SalesItemHistory) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            String primaryKeyValue = ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$id();
            long rowIndex = Table.NO_MATCH;
            if (primaryKeyValue == null) {
                rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
            } else {
                rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
            }
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.salesIdIndex, rowIndex, ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$salesId(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.stockIdIndex, rowIndex, ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$stockId(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.unitPriceIndex, rowIndex, ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$unitPrice(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.quantityIndex, rowIndex, ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$quantity(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.subTotalIndex, rowIndex, ((com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) object).realmGet$subTotal(), false);
        }
    }

    public static com.portal.terminalbd.model.SalesItemHistory createDetachedCopy(com.portal.terminalbd.model.SalesItemHistory realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.portal.terminalbd.model.SalesItemHistory unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.portal.terminalbd.model.SalesItemHistory();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.portal.terminalbd.model.SalesItemHistory) cachedObject.object;
            }
            unmanagedObject = (com.portal.terminalbd.model.SalesItemHistory) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface unmanagedCopy = (com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) unmanagedObject;
        com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface realmSource = (com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$salesId(realmSource.realmGet$salesId());
        unmanagedCopy.realmSet$stockId(realmSource.realmGet$stockId());
        unmanagedCopy.realmSet$unitPrice(realmSource.realmGet$unitPrice());
        unmanagedCopy.realmSet$quantity(realmSource.realmGet$quantity());
        unmanagedCopy.realmSet$subTotal(realmSource.realmGet$subTotal());

        return unmanagedObject;
    }

    static com.portal.terminalbd.model.SalesItemHistory update(Realm realm, SalesItemHistoryColumnInfo columnInfo, com.portal.terminalbd.model.SalesItemHistory realmObject, com.portal.terminalbd.model.SalesItemHistory newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface realmObjectTarget = (com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) realmObject;
        com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface realmObjectSource = (com_portal_terminalbd_model_SalesItemHistoryRealmProxyInterface) newObject;
        Table table = realm.getTable(com.portal.terminalbd.model.SalesItemHistory.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);
        builder.addString(columnInfo.idIndex, realmObjectSource.realmGet$id());
        builder.addInteger(columnInfo.salesIdIndex, realmObjectSource.realmGet$salesId());
        builder.addInteger(columnInfo.stockIdIndex, realmObjectSource.realmGet$stockId());
        builder.addDouble(columnInfo.unitPriceIndex, realmObjectSource.realmGet$unitPrice());
        builder.addInteger(columnInfo.quantityIndex, realmObjectSource.realmGet$quantity());
        builder.addInteger(columnInfo.subTotalIndex, realmObjectSource.realmGet$subTotal());

        builder.updateExistingObject();
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("SalesItemHistory = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{salesId:");
        stringBuilder.append(realmGet$salesId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{stockId:");
        stringBuilder.append(realmGet$stockId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{unitPrice:");
        stringBuilder.append(realmGet$unitPrice());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{quantity:");
        stringBuilder.append(realmGet$quantity());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{subTotal:");
        stringBuilder.append(realmGet$subTotal());
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
        com_portal_terminalbd_model_SalesItemHistoryRealmProxy aSalesItemHistory = (com_portal_terminalbd_model_SalesItemHistoryRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aSalesItemHistory.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aSalesItemHistory.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aSalesItemHistory.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
