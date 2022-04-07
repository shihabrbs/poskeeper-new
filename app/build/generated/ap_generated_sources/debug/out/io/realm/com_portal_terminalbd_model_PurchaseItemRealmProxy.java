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
public class com_portal_terminalbd_model_PurchaseItemRealmProxy extends com.portal.terminalbd.model.PurchaseItem
    implements RealmObjectProxy, com_portal_terminalbd_model_PurchaseItemRealmProxyInterface {

    static final class PurchaseItemColumnInfo extends ColumnInfo {
        long maxColumnIndexValue;
        long idIndex;
        long purchaseStockIdIndex;
        long purchaseItemNameIndex;
        long purchasePpPriceIndex;
        long purchaseMrpPriceIndex;
        long purchaseQuantityIndex;
        long purchaseSubTotalIndex;

        PurchaseItemColumnInfo(OsSchemaInfo schemaInfo) {
            super(7);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("PurchaseItem");
            this.idIndex = addColumnDetails("id", "id", objectSchemaInfo);
            this.purchaseStockIdIndex = addColumnDetails("purchaseStockId", "purchaseStockId", objectSchemaInfo);
            this.purchaseItemNameIndex = addColumnDetails("purchaseItemName", "purchaseItemName", objectSchemaInfo);
            this.purchasePpPriceIndex = addColumnDetails("purchasePpPrice", "purchasePpPrice", objectSchemaInfo);
            this.purchaseMrpPriceIndex = addColumnDetails("purchaseMrpPrice", "purchaseMrpPrice", objectSchemaInfo);
            this.purchaseQuantityIndex = addColumnDetails("purchaseQuantity", "purchaseQuantity", objectSchemaInfo);
            this.purchaseSubTotalIndex = addColumnDetails("purchaseSubTotal", "purchaseSubTotal", objectSchemaInfo);
            this.maxColumnIndexValue = objectSchemaInfo.getMaxColumnIndex();
        }

        PurchaseItemColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new PurchaseItemColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final PurchaseItemColumnInfo src = (PurchaseItemColumnInfo) rawSrc;
            final PurchaseItemColumnInfo dst = (PurchaseItemColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.purchaseStockIdIndex = src.purchaseStockIdIndex;
            dst.purchaseItemNameIndex = src.purchaseItemNameIndex;
            dst.purchasePpPriceIndex = src.purchasePpPriceIndex;
            dst.purchaseMrpPriceIndex = src.purchaseMrpPriceIndex;
            dst.purchaseQuantityIndex = src.purchaseQuantityIndex;
            dst.purchaseSubTotalIndex = src.purchaseSubTotalIndex;
            dst.maxColumnIndexValue = src.maxColumnIndexValue;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private PurchaseItemColumnInfo columnInfo;
    private ProxyState<com.portal.terminalbd.model.PurchaseItem> proxyState;

    com_portal_terminalbd_model_PurchaseItemRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (PurchaseItemColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.portal.terminalbd.model.PurchaseItem>(this);
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
    public String realmGet$purchaseStockId() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.purchaseStockIdIndex);
    }

    @Override
    public void realmSet$purchaseStockId(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.purchaseStockIdIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.purchaseStockIdIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.purchaseStockIdIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.purchaseStockIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$purchaseItemName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.purchaseItemNameIndex);
    }

    @Override
    public void realmSet$purchaseItemName(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.purchaseItemNameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.purchaseItemNameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.purchaseItemNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.purchaseItemNameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$purchasePpPrice() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.purchasePpPriceIndex);
    }

    @Override
    public void realmSet$purchasePpPrice(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.purchasePpPriceIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.purchasePpPriceIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.purchasePpPriceIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.purchasePpPriceIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$purchaseMrpPrice() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.purchaseMrpPriceIndex);
    }

    @Override
    public void realmSet$purchaseMrpPrice(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.purchaseMrpPriceIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.purchaseMrpPriceIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.purchaseMrpPriceIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.purchaseMrpPriceIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$purchaseQuantity() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.purchaseQuantityIndex);
    }

    @Override
    public void realmSet$purchaseQuantity(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.purchaseQuantityIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.purchaseQuantityIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.purchaseQuantityIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.purchaseQuantityIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$purchaseSubTotal() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.purchaseSubTotalIndex);
    }

    @Override
    public void realmSet$purchaseSubTotal(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.purchaseSubTotalIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.purchaseSubTotalIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.purchaseSubTotalIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.purchaseSubTotalIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("PurchaseItem", 7, 0);
        builder.addPersistedProperty("id", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("purchaseStockId", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("purchaseItemName", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("purchasePpPrice", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("purchaseMrpPrice", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("purchaseQuantity", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("purchaseSubTotal", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static PurchaseItemColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new PurchaseItemColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "PurchaseItem";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "PurchaseItem";
    }

    @SuppressWarnings("cast")
    public static com.portal.terminalbd.model.PurchaseItem createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.portal.terminalbd.model.PurchaseItem obj = null;
        if (update) {
            Table table = realm.getTable(com.portal.terminalbd.model.PurchaseItem.class);
            PurchaseItemColumnInfo columnInfo = (PurchaseItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.PurchaseItem.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.portal.terminalbd.model.PurchaseItem.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_portal_terminalbd_model_PurchaseItemRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.com_portal_terminalbd_model_PurchaseItemRealmProxy) realm.createObjectInternal(com.portal.terminalbd.model.PurchaseItem.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_portal_terminalbd_model_PurchaseItemRealmProxy) realm.createObjectInternal(com.portal.terminalbd.model.PurchaseItem.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final com_portal_terminalbd_model_PurchaseItemRealmProxyInterface objProxy = (com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) obj;
        if (json.has("purchaseStockId")) {
            if (json.isNull("purchaseStockId")) {
                objProxy.realmSet$purchaseStockId(null);
            } else {
                objProxy.realmSet$purchaseStockId((String) json.getString("purchaseStockId"));
            }
        }
        if (json.has("purchaseItemName")) {
            if (json.isNull("purchaseItemName")) {
                objProxy.realmSet$purchaseItemName(null);
            } else {
                objProxy.realmSet$purchaseItemName((String) json.getString("purchaseItemName"));
            }
        }
        if (json.has("purchasePpPrice")) {
            if (json.isNull("purchasePpPrice")) {
                objProxy.realmSet$purchasePpPrice(null);
            } else {
                objProxy.realmSet$purchasePpPrice((String) json.getString("purchasePpPrice"));
            }
        }
        if (json.has("purchaseMrpPrice")) {
            if (json.isNull("purchaseMrpPrice")) {
                objProxy.realmSet$purchaseMrpPrice(null);
            } else {
                objProxy.realmSet$purchaseMrpPrice((String) json.getString("purchaseMrpPrice"));
            }
        }
        if (json.has("purchaseQuantity")) {
            if (json.isNull("purchaseQuantity")) {
                objProxy.realmSet$purchaseQuantity(null);
            } else {
                objProxy.realmSet$purchaseQuantity((String) json.getString("purchaseQuantity"));
            }
        }
        if (json.has("purchaseSubTotal")) {
            if (json.isNull("purchaseSubTotal")) {
                objProxy.realmSet$purchaseSubTotal(null);
            } else {
                objProxy.realmSet$purchaseSubTotal((String) json.getString("purchaseSubTotal"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.portal.terminalbd.model.PurchaseItem createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.portal.terminalbd.model.PurchaseItem obj = new com.portal.terminalbd.model.PurchaseItem();
        final com_portal_terminalbd_model_PurchaseItemRealmProxyInterface objProxy = (com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) obj;
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
            } else if (name.equals("purchaseStockId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$purchaseStockId((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$purchaseStockId(null);
                }
            } else if (name.equals("purchaseItemName")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$purchaseItemName((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$purchaseItemName(null);
                }
            } else if (name.equals("purchasePpPrice")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$purchasePpPrice((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$purchasePpPrice(null);
                }
            } else if (name.equals("purchaseMrpPrice")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$purchaseMrpPrice((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$purchaseMrpPrice(null);
                }
            } else if (name.equals("purchaseQuantity")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$purchaseQuantity((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$purchaseQuantity(null);
                }
            } else if (name.equals("purchaseSubTotal")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$purchaseSubTotal((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$purchaseSubTotal(null);
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

    private static com_portal_terminalbd_model_PurchaseItemRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.portal.terminalbd.model.PurchaseItem.class), false, Collections.<String>emptyList());
        io.realm.com_portal_terminalbd_model_PurchaseItemRealmProxy obj = new io.realm.com_portal_terminalbd_model_PurchaseItemRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.portal.terminalbd.model.PurchaseItem copyOrUpdate(Realm realm, PurchaseItemColumnInfo columnInfo, com.portal.terminalbd.model.PurchaseItem object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.portal.terminalbd.model.PurchaseItem) cachedRealmObject;
        }

        com.portal.terminalbd.model.PurchaseItem realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.portal.terminalbd.model.PurchaseItem.class);
            long pkColumnIndex = columnInfo.idIndex;
            String value = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$id();
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
                    realmObject = new io.realm.com_portal_terminalbd_model_PurchaseItemRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.portal.terminalbd.model.PurchaseItem copy(Realm realm, PurchaseItemColumnInfo columnInfo, com.portal.terminalbd.model.PurchaseItem newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.portal.terminalbd.model.PurchaseItem) cachedRealmObject;
        }

        com_portal_terminalbd_model_PurchaseItemRealmProxyInterface realmObjectSource = (com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) newObject;

        Table table = realm.getTable(com.portal.terminalbd.model.PurchaseItem.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);

        // Add all non-"object reference" fields
        builder.addString(columnInfo.idIndex, realmObjectSource.realmGet$id());
        builder.addString(columnInfo.purchaseStockIdIndex, realmObjectSource.realmGet$purchaseStockId());
        builder.addString(columnInfo.purchaseItemNameIndex, realmObjectSource.realmGet$purchaseItemName());
        builder.addString(columnInfo.purchasePpPriceIndex, realmObjectSource.realmGet$purchasePpPrice());
        builder.addString(columnInfo.purchaseMrpPriceIndex, realmObjectSource.realmGet$purchaseMrpPrice());
        builder.addString(columnInfo.purchaseQuantityIndex, realmObjectSource.realmGet$purchaseQuantity());
        builder.addString(columnInfo.purchaseSubTotalIndex, realmObjectSource.realmGet$purchaseSubTotal());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_portal_terminalbd_model_PurchaseItemRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.portal.terminalbd.model.PurchaseItem object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.PurchaseItem.class);
        long tableNativePtr = table.getNativePtr();
        PurchaseItemColumnInfo columnInfo = (PurchaseItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.PurchaseItem.class);
        long pkColumnIndex = columnInfo.idIndex;
        String primaryKeyValue = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$id();
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
        String realmGet$purchaseStockId = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchaseStockId();
        if (realmGet$purchaseStockId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.purchaseStockIdIndex, rowIndex, realmGet$purchaseStockId, false);
        }
        String realmGet$purchaseItemName = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchaseItemName();
        if (realmGet$purchaseItemName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.purchaseItemNameIndex, rowIndex, realmGet$purchaseItemName, false);
        }
        String realmGet$purchasePpPrice = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchasePpPrice();
        if (realmGet$purchasePpPrice != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.purchasePpPriceIndex, rowIndex, realmGet$purchasePpPrice, false);
        }
        String realmGet$purchaseMrpPrice = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchaseMrpPrice();
        if (realmGet$purchaseMrpPrice != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.purchaseMrpPriceIndex, rowIndex, realmGet$purchaseMrpPrice, false);
        }
        String realmGet$purchaseQuantity = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchaseQuantity();
        if (realmGet$purchaseQuantity != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.purchaseQuantityIndex, rowIndex, realmGet$purchaseQuantity, false);
        }
        String realmGet$purchaseSubTotal = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchaseSubTotal();
        if (realmGet$purchaseSubTotal != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.purchaseSubTotalIndex, rowIndex, realmGet$purchaseSubTotal, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.PurchaseItem.class);
        long tableNativePtr = table.getNativePtr();
        PurchaseItemColumnInfo columnInfo = (PurchaseItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.PurchaseItem.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.portal.terminalbd.model.PurchaseItem object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.PurchaseItem) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            String primaryKeyValue = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$id();
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
            String realmGet$purchaseStockId = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchaseStockId();
            if (realmGet$purchaseStockId != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.purchaseStockIdIndex, rowIndex, realmGet$purchaseStockId, false);
            }
            String realmGet$purchaseItemName = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchaseItemName();
            if (realmGet$purchaseItemName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.purchaseItemNameIndex, rowIndex, realmGet$purchaseItemName, false);
            }
            String realmGet$purchasePpPrice = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchasePpPrice();
            if (realmGet$purchasePpPrice != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.purchasePpPriceIndex, rowIndex, realmGet$purchasePpPrice, false);
            }
            String realmGet$purchaseMrpPrice = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchaseMrpPrice();
            if (realmGet$purchaseMrpPrice != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.purchaseMrpPriceIndex, rowIndex, realmGet$purchaseMrpPrice, false);
            }
            String realmGet$purchaseQuantity = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchaseQuantity();
            if (realmGet$purchaseQuantity != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.purchaseQuantityIndex, rowIndex, realmGet$purchaseQuantity, false);
            }
            String realmGet$purchaseSubTotal = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchaseSubTotal();
            if (realmGet$purchaseSubTotal != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.purchaseSubTotalIndex, rowIndex, realmGet$purchaseSubTotal, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.portal.terminalbd.model.PurchaseItem object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.PurchaseItem.class);
        long tableNativePtr = table.getNativePtr();
        PurchaseItemColumnInfo columnInfo = (PurchaseItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.PurchaseItem.class);
        long pkColumnIndex = columnInfo.idIndex;
        String primaryKeyValue = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$id();
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
        String realmGet$purchaseStockId = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchaseStockId();
        if (realmGet$purchaseStockId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.purchaseStockIdIndex, rowIndex, realmGet$purchaseStockId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.purchaseStockIdIndex, rowIndex, false);
        }
        String realmGet$purchaseItemName = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchaseItemName();
        if (realmGet$purchaseItemName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.purchaseItemNameIndex, rowIndex, realmGet$purchaseItemName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.purchaseItemNameIndex, rowIndex, false);
        }
        String realmGet$purchasePpPrice = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchasePpPrice();
        if (realmGet$purchasePpPrice != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.purchasePpPriceIndex, rowIndex, realmGet$purchasePpPrice, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.purchasePpPriceIndex, rowIndex, false);
        }
        String realmGet$purchaseMrpPrice = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchaseMrpPrice();
        if (realmGet$purchaseMrpPrice != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.purchaseMrpPriceIndex, rowIndex, realmGet$purchaseMrpPrice, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.purchaseMrpPriceIndex, rowIndex, false);
        }
        String realmGet$purchaseQuantity = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchaseQuantity();
        if (realmGet$purchaseQuantity != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.purchaseQuantityIndex, rowIndex, realmGet$purchaseQuantity, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.purchaseQuantityIndex, rowIndex, false);
        }
        String realmGet$purchaseSubTotal = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchaseSubTotal();
        if (realmGet$purchaseSubTotal != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.purchaseSubTotalIndex, rowIndex, realmGet$purchaseSubTotal, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.purchaseSubTotalIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.PurchaseItem.class);
        long tableNativePtr = table.getNativePtr();
        PurchaseItemColumnInfo columnInfo = (PurchaseItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.PurchaseItem.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.portal.terminalbd.model.PurchaseItem object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.PurchaseItem) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            String primaryKeyValue = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$id();
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
            String realmGet$purchaseStockId = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchaseStockId();
            if (realmGet$purchaseStockId != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.purchaseStockIdIndex, rowIndex, realmGet$purchaseStockId, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.purchaseStockIdIndex, rowIndex, false);
            }
            String realmGet$purchaseItemName = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchaseItemName();
            if (realmGet$purchaseItemName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.purchaseItemNameIndex, rowIndex, realmGet$purchaseItemName, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.purchaseItemNameIndex, rowIndex, false);
            }
            String realmGet$purchasePpPrice = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchasePpPrice();
            if (realmGet$purchasePpPrice != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.purchasePpPriceIndex, rowIndex, realmGet$purchasePpPrice, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.purchasePpPriceIndex, rowIndex, false);
            }
            String realmGet$purchaseMrpPrice = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchaseMrpPrice();
            if (realmGet$purchaseMrpPrice != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.purchaseMrpPriceIndex, rowIndex, realmGet$purchaseMrpPrice, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.purchaseMrpPriceIndex, rowIndex, false);
            }
            String realmGet$purchaseQuantity = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchaseQuantity();
            if (realmGet$purchaseQuantity != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.purchaseQuantityIndex, rowIndex, realmGet$purchaseQuantity, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.purchaseQuantityIndex, rowIndex, false);
            }
            String realmGet$purchaseSubTotal = ((com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) object).realmGet$purchaseSubTotal();
            if (realmGet$purchaseSubTotal != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.purchaseSubTotalIndex, rowIndex, realmGet$purchaseSubTotal, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.purchaseSubTotalIndex, rowIndex, false);
            }
        }
    }

    public static com.portal.terminalbd.model.PurchaseItem createDetachedCopy(com.portal.terminalbd.model.PurchaseItem realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.portal.terminalbd.model.PurchaseItem unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.portal.terminalbd.model.PurchaseItem();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.portal.terminalbd.model.PurchaseItem) cachedObject.object;
            }
            unmanagedObject = (com.portal.terminalbd.model.PurchaseItem) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_portal_terminalbd_model_PurchaseItemRealmProxyInterface unmanagedCopy = (com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) unmanagedObject;
        com_portal_terminalbd_model_PurchaseItemRealmProxyInterface realmSource = (com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$purchaseStockId(realmSource.realmGet$purchaseStockId());
        unmanagedCopy.realmSet$purchaseItemName(realmSource.realmGet$purchaseItemName());
        unmanagedCopy.realmSet$purchasePpPrice(realmSource.realmGet$purchasePpPrice());
        unmanagedCopy.realmSet$purchaseMrpPrice(realmSource.realmGet$purchaseMrpPrice());
        unmanagedCopy.realmSet$purchaseQuantity(realmSource.realmGet$purchaseQuantity());
        unmanagedCopy.realmSet$purchaseSubTotal(realmSource.realmGet$purchaseSubTotal());

        return unmanagedObject;
    }

    static com.portal.terminalbd.model.PurchaseItem update(Realm realm, PurchaseItemColumnInfo columnInfo, com.portal.terminalbd.model.PurchaseItem realmObject, com.portal.terminalbd.model.PurchaseItem newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        com_portal_terminalbd_model_PurchaseItemRealmProxyInterface realmObjectTarget = (com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) realmObject;
        com_portal_terminalbd_model_PurchaseItemRealmProxyInterface realmObjectSource = (com_portal_terminalbd_model_PurchaseItemRealmProxyInterface) newObject;
        Table table = realm.getTable(com.portal.terminalbd.model.PurchaseItem.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);
        builder.addString(columnInfo.idIndex, realmObjectSource.realmGet$id());
        builder.addString(columnInfo.purchaseStockIdIndex, realmObjectSource.realmGet$purchaseStockId());
        builder.addString(columnInfo.purchaseItemNameIndex, realmObjectSource.realmGet$purchaseItemName());
        builder.addString(columnInfo.purchasePpPriceIndex, realmObjectSource.realmGet$purchasePpPrice());
        builder.addString(columnInfo.purchaseMrpPriceIndex, realmObjectSource.realmGet$purchaseMrpPrice());
        builder.addString(columnInfo.purchaseQuantityIndex, realmObjectSource.realmGet$purchaseQuantity());
        builder.addString(columnInfo.purchaseSubTotalIndex, realmObjectSource.realmGet$purchaseSubTotal());

        builder.updateExistingObject();
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("PurchaseItem = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{purchaseStockId:");
        stringBuilder.append(realmGet$purchaseStockId() != null ? realmGet$purchaseStockId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{purchaseItemName:");
        stringBuilder.append(realmGet$purchaseItemName() != null ? realmGet$purchaseItemName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{purchasePpPrice:");
        stringBuilder.append(realmGet$purchasePpPrice() != null ? realmGet$purchasePpPrice() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{purchaseMrpPrice:");
        stringBuilder.append(realmGet$purchaseMrpPrice() != null ? realmGet$purchaseMrpPrice() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{purchaseQuantity:");
        stringBuilder.append(realmGet$purchaseQuantity() != null ? realmGet$purchaseQuantity() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{purchaseSubTotal:");
        stringBuilder.append(realmGet$purchaseSubTotal() != null ? realmGet$purchaseSubTotal() : "null");
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
        com_portal_terminalbd_model_PurchaseItemRealmProxy aPurchaseItem = (com_portal_terminalbd_model_PurchaseItemRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aPurchaseItem.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aPurchaseItem.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aPurchaseItem.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
