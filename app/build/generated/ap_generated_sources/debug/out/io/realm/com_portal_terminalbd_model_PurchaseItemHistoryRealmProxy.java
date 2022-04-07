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
public class com_portal_terminalbd_model_PurchaseItemHistoryRealmProxy extends com.portal.terminalbd.model.PurchaseItemHistory
    implements RealmObjectProxy, com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface {

    static final class PurchaseItemHistoryColumnInfo extends ColumnInfo {
        long maxColumnIndexValue;
        long idIndex;
        long purchaseIdIndex;
        long stockIdIndex;
        long purchasePriceIndex;
        long salesPriceIndex;
        long quantityIndex;
        long subTotalIndex;

        PurchaseItemHistoryColumnInfo(OsSchemaInfo schemaInfo) {
            super(7);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("PurchaseItemHistory");
            this.idIndex = addColumnDetails("id", "id", objectSchemaInfo);
            this.purchaseIdIndex = addColumnDetails("purchaseId", "purchaseId", objectSchemaInfo);
            this.stockIdIndex = addColumnDetails("stockId", "stockId", objectSchemaInfo);
            this.purchasePriceIndex = addColumnDetails("purchasePrice", "purchasePrice", objectSchemaInfo);
            this.salesPriceIndex = addColumnDetails("salesPrice", "salesPrice", objectSchemaInfo);
            this.quantityIndex = addColumnDetails("quantity", "quantity", objectSchemaInfo);
            this.subTotalIndex = addColumnDetails("subTotal", "subTotal", objectSchemaInfo);
            this.maxColumnIndexValue = objectSchemaInfo.getMaxColumnIndex();
        }

        PurchaseItemHistoryColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new PurchaseItemHistoryColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final PurchaseItemHistoryColumnInfo src = (PurchaseItemHistoryColumnInfo) rawSrc;
            final PurchaseItemHistoryColumnInfo dst = (PurchaseItemHistoryColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.purchaseIdIndex = src.purchaseIdIndex;
            dst.stockIdIndex = src.stockIdIndex;
            dst.purchasePriceIndex = src.purchasePriceIndex;
            dst.salesPriceIndex = src.salesPriceIndex;
            dst.quantityIndex = src.quantityIndex;
            dst.subTotalIndex = src.subTotalIndex;
            dst.maxColumnIndexValue = src.maxColumnIndexValue;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private PurchaseItemHistoryColumnInfo columnInfo;
    private ProxyState<com.portal.terminalbd.model.PurchaseItemHistory> proxyState;

    com_portal_terminalbd_model_PurchaseItemHistoryRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (PurchaseItemHistoryColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.portal.terminalbd.model.PurchaseItemHistory>(this);
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
    public int realmGet$purchaseId() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.purchaseIdIndex);
    }

    @Override
    public void realmSet$purchaseId(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.purchaseIdIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.purchaseIdIndex, value);
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
    public double realmGet$purchasePrice() {
        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.purchasePriceIndex);
    }

    @Override
    public void realmSet$purchasePrice(double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.purchasePriceIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.purchasePriceIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public double realmGet$salesPrice() {
        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.salesPriceIndex);
    }

    @Override
    public void realmSet$salesPrice(double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.salesPriceIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.salesPriceIndex, value);
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
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("PurchaseItemHistory", 7, 0);
        builder.addPersistedProperty("id", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("purchaseId", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("stockId", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("purchasePrice", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("salesPrice", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("quantity", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("subTotal", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static PurchaseItemHistoryColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new PurchaseItemHistoryColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "PurchaseItemHistory";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "PurchaseItemHistory";
    }

    @SuppressWarnings("cast")
    public static com.portal.terminalbd.model.PurchaseItemHistory createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.portal.terminalbd.model.PurchaseItemHistory obj = null;
        if (update) {
            Table table = realm.getTable(com.portal.terminalbd.model.PurchaseItemHistory.class);
            PurchaseItemHistoryColumnInfo columnInfo = (PurchaseItemHistoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.PurchaseItemHistory.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.portal.terminalbd.model.PurchaseItemHistory.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_portal_terminalbd_model_PurchaseItemHistoryRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.com_portal_terminalbd_model_PurchaseItemHistoryRealmProxy) realm.createObjectInternal(com.portal.terminalbd.model.PurchaseItemHistory.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_portal_terminalbd_model_PurchaseItemHistoryRealmProxy) realm.createObjectInternal(com.portal.terminalbd.model.PurchaseItemHistory.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface objProxy = (com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) obj;
        if (json.has("purchaseId")) {
            if (json.isNull("purchaseId")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'purchaseId' to null.");
            } else {
                objProxy.realmSet$purchaseId((int) json.getInt("purchaseId"));
            }
        }
        if (json.has("stockId")) {
            if (json.isNull("stockId")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'stockId' to null.");
            } else {
                objProxy.realmSet$stockId((int) json.getInt("stockId"));
            }
        }
        if (json.has("purchasePrice")) {
            if (json.isNull("purchasePrice")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'purchasePrice' to null.");
            } else {
                objProxy.realmSet$purchasePrice((double) json.getDouble("purchasePrice"));
            }
        }
        if (json.has("salesPrice")) {
            if (json.isNull("salesPrice")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'salesPrice' to null.");
            } else {
                objProxy.realmSet$salesPrice((double) json.getDouble("salesPrice"));
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
    public static com.portal.terminalbd.model.PurchaseItemHistory createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.portal.terminalbd.model.PurchaseItemHistory obj = new com.portal.terminalbd.model.PurchaseItemHistory();
        final com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface objProxy = (com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) obj;
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
            } else if (name.equals("purchaseId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$purchaseId((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'purchaseId' to null.");
                }
            } else if (name.equals("stockId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$stockId((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'stockId' to null.");
                }
            } else if (name.equals("purchasePrice")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$purchasePrice((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'purchasePrice' to null.");
                }
            } else if (name.equals("salesPrice")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$salesPrice((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'salesPrice' to null.");
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

    private static com_portal_terminalbd_model_PurchaseItemHistoryRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.portal.terminalbd.model.PurchaseItemHistory.class), false, Collections.<String>emptyList());
        io.realm.com_portal_terminalbd_model_PurchaseItemHistoryRealmProxy obj = new io.realm.com_portal_terminalbd_model_PurchaseItemHistoryRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.portal.terminalbd.model.PurchaseItemHistory copyOrUpdate(Realm realm, PurchaseItemHistoryColumnInfo columnInfo, com.portal.terminalbd.model.PurchaseItemHistory object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.portal.terminalbd.model.PurchaseItemHistory) cachedRealmObject;
        }

        com.portal.terminalbd.model.PurchaseItemHistory realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.portal.terminalbd.model.PurchaseItemHistory.class);
            long pkColumnIndex = columnInfo.idIndex;
            String value = ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$id();
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
                    realmObject = new io.realm.com_portal_terminalbd_model_PurchaseItemHistoryRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.portal.terminalbd.model.PurchaseItemHistory copy(Realm realm, PurchaseItemHistoryColumnInfo columnInfo, com.portal.terminalbd.model.PurchaseItemHistory newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.portal.terminalbd.model.PurchaseItemHistory) cachedRealmObject;
        }

        com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface realmObjectSource = (com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) newObject;

        Table table = realm.getTable(com.portal.terminalbd.model.PurchaseItemHistory.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);

        // Add all non-"object reference" fields
        builder.addString(columnInfo.idIndex, realmObjectSource.realmGet$id());
        builder.addInteger(columnInfo.purchaseIdIndex, realmObjectSource.realmGet$purchaseId());
        builder.addInteger(columnInfo.stockIdIndex, realmObjectSource.realmGet$stockId());
        builder.addDouble(columnInfo.purchasePriceIndex, realmObjectSource.realmGet$purchasePrice());
        builder.addDouble(columnInfo.salesPriceIndex, realmObjectSource.realmGet$salesPrice());
        builder.addInteger(columnInfo.quantityIndex, realmObjectSource.realmGet$quantity());
        builder.addInteger(columnInfo.subTotalIndex, realmObjectSource.realmGet$subTotal());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_portal_terminalbd_model_PurchaseItemHistoryRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.portal.terminalbd.model.PurchaseItemHistory object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.PurchaseItemHistory.class);
        long tableNativePtr = table.getNativePtr();
        PurchaseItemHistoryColumnInfo columnInfo = (PurchaseItemHistoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.PurchaseItemHistory.class);
        long pkColumnIndex = columnInfo.idIndex;
        String primaryKeyValue = ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$id();
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
        Table.nativeSetLong(tableNativePtr, columnInfo.purchaseIdIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$purchaseId(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.stockIdIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$stockId(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.purchasePriceIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$purchasePrice(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.salesPriceIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$salesPrice(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.quantityIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$quantity(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.subTotalIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$subTotal(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.PurchaseItemHistory.class);
        long tableNativePtr = table.getNativePtr();
        PurchaseItemHistoryColumnInfo columnInfo = (PurchaseItemHistoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.PurchaseItemHistory.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.portal.terminalbd.model.PurchaseItemHistory object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.PurchaseItemHistory) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            String primaryKeyValue = ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$id();
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
            Table.nativeSetLong(tableNativePtr, columnInfo.purchaseIdIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$purchaseId(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.stockIdIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$stockId(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.purchasePriceIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$purchasePrice(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.salesPriceIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$salesPrice(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.quantityIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$quantity(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.subTotalIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$subTotal(), false);
        }
    }

    public static long insertOrUpdate(Realm realm, com.portal.terminalbd.model.PurchaseItemHistory object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.PurchaseItemHistory.class);
        long tableNativePtr = table.getNativePtr();
        PurchaseItemHistoryColumnInfo columnInfo = (PurchaseItemHistoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.PurchaseItemHistory.class);
        long pkColumnIndex = columnInfo.idIndex;
        String primaryKeyValue = ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$id();
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
        Table.nativeSetLong(tableNativePtr, columnInfo.purchaseIdIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$purchaseId(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.stockIdIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$stockId(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.purchasePriceIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$purchasePrice(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.salesPriceIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$salesPrice(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.quantityIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$quantity(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.subTotalIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$subTotal(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.PurchaseItemHistory.class);
        long tableNativePtr = table.getNativePtr();
        PurchaseItemHistoryColumnInfo columnInfo = (PurchaseItemHistoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.PurchaseItemHistory.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.portal.terminalbd.model.PurchaseItemHistory object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.PurchaseItemHistory) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            String primaryKeyValue = ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$id();
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
            Table.nativeSetLong(tableNativePtr, columnInfo.purchaseIdIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$purchaseId(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.stockIdIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$stockId(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.purchasePriceIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$purchasePrice(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.salesPriceIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$salesPrice(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.quantityIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$quantity(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.subTotalIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) object).realmGet$subTotal(), false);
        }
    }

    public static com.portal.terminalbd.model.PurchaseItemHistory createDetachedCopy(com.portal.terminalbd.model.PurchaseItemHistory realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.portal.terminalbd.model.PurchaseItemHistory unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.portal.terminalbd.model.PurchaseItemHistory();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.portal.terminalbd.model.PurchaseItemHistory) cachedObject.object;
            }
            unmanagedObject = (com.portal.terminalbd.model.PurchaseItemHistory) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface unmanagedCopy = (com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) unmanagedObject;
        com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface realmSource = (com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$purchaseId(realmSource.realmGet$purchaseId());
        unmanagedCopy.realmSet$stockId(realmSource.realmGet$stockId());
        unmanagedCopy.realmSet$purchasePrice(realmSource.realmGet$purchasePrice());
        unmanagedCopy.realmSet$salesPrice(realmSource.realmGet$salesPrice());
        unmanagedCopy.realmSet$quantity(realmSource.realmGet$quantity());
        unmanagedCopy.realmSet$subTotal(realmSource.realmGet$subTotal());

        return unmanagedObject;
    }

    static com.portal.terminalbd.model.PurchaseItemHistory update(Realm realm, PurchaseItemHistoryColumnInfo columnInfo, com.portal.terminalbd.model.PurchaseItemHistory realmObject, com.portal.terminalbd.model.PurchaseItemHistory newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface realmObjectTarget = (com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) realmObject;
        com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface realmObjectSource = (com_portal_terminalbd_model_PurchaseItemHistoryRealmProxyInterface) newObject;
        Table table = realm.getTable(com.portal.terminalbd.model.PurchaseItemHistory.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);
        builder.addString(columnInfo.idIndex, realmObjectSource.realmGet$id());
        builder.addInteger(columnInfo.purchaseIdIndex, realmObjectSource.realmGet$purchaseId());
        builder.addInteger(columnInfo.stockIdIndex, realmObjectSource.realmGet$stockId());
        builder.addDouble(columnInfo.purchasePriceIndex, realmObjectSource.realmGet$purchasePrice());
        builder.addDouble(columnInfo.salesPriceIndex, realmObjectSource.realmGet$salesPrice());
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
        StringBuilder stringBuilder = new StringBuilder("PurchaseItemHistory = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{purchaseId:");
        stringBuilder.append(realmGet$purchaseId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{stockId:");
        stringBuilder.append(realmGet$stockId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{purchasePrice:");
        stringBuilder.append(realmGet$purchasePrice());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{salesPrice:");
        stringBuilder.append(realmGet$salesPrice());
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
        com_portal_terminalbd_model_PurchaseItemHistoryRealmProxy aPurchaseItemHistory = (com_portal_terminalbd_model_PurchaseItemHistoryRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aPurchaseItemHistory.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aPurchaseItemHistory.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aPurchaseItemHistory.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
