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
public class com_portal_terminalbd_model_SaleItemRealmProxy extends com.portal.terminalbd.model.SaleItem
    implements RealmObjectProxy, com_portal_terminalbd_model_SaleItemRealmProxyInterface {

    static final class SaleItemColumnInfo extends ColumnInfo {
        long maxColumnIndexValue;
        long idIndex;
        long saleStockIdIndex;
        long saleItemNameIndex;
        long salePpPriceIndex;
        long saleMrpPriceIndex;
        long saleQuantityIndex;
        long saleSubTotalIndex;

        SaleItemColumnInfo(OsSchemaInfo schemaInfo) {
            super(7);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("SaleItem");
            this.idIndex = addColumnDetails("id", "id", objectSchemaInfo);
            this.saleStockIdIndex = addColumnDetails("saleStockId", "saleStockId", objectSchemaInfo);
            this.saleItemNameIndex = addColumnDetails("saleItemName", "saleItemName", objectSchemaInfo);
            this.salePpPriceIndex = addColumnDetails("salePpPrice", "salePpPrice", objectSchemaInfo);
            this.saleMrpPriceIndex = addColumnDetails("saleMrpPrice", "saleMrpPrice", objectSchemaInfo);
            this.saleQuantityIndex = addColumnDetails("saleQuantity", "saleQuantity", objectSchemaInfo);
            this.saleSubTotalIndex = addColumnDetails("saleSubTotal", "saleSubTotal", objectSchemaInfo);
            this.maxColumnIndexValue = objectSchemaInfo.getMaxColumnIndex();
        }

        SaleItemColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new SaleItemColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final SaleItemColumnInfo src = (SaleItemColumnInfo) rawSrc;
            final SaleItemColumnInfo dst = (SaleItemColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.saleStockIdIndex = src.saleStockIdIndex;
            dst.saleItemNameIndex = src.saleItemNameIndex;
            dst.salePpPriceIndex = src.salePpPriceIndex;
            dst.saleMrpPriceIndex = src.saleMrpPriceIndex;
            dst.saleQuantityIndex = src.saleQuantityIndex;
            dst.saleSubTotalIndex = src.saleSubTotalIndex;
            dst.maxColumnIndexValue = src.maxColumnIndexValue;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private SaleItemColumnInfo columnInfo;
    private ProxyState<com.portal.terminalbd.model.SaleItem> proxyState;

    com_portal_terminalbd_model_SaleItemRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (SaleItemColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.portal.terminalbd.model.SaleItem>(this);
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
    public String realmGet$saleStockId() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.saleStockIdIndex);
    }

    @Override
    public void realmSet$saleStockId(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.saleStockIdIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.saleStockIdIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.saleStockIdIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.saleStockIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$saleItemName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.saleItemNameIndex);
    }

    @Override
    public void realmSet$saleItemName(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.saleItemNameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.saleItemNameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.saleItemNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.saleItemNameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$salePpPrice() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.salePpPriceIndex);
    }

    @Override
    public void realmSet$salePpPrice(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.salePpPriceIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.salePpPriceIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.salePpPriceIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.salePpPriceIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$saleMrpPrice() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.saleMrpPriceIndex);
    }

    @Override
    public void realmSet$saleMrpPrice(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.saleMrpPriceIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.saleMrpPriceIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.saleMrpPriceIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.saleMrpPriceIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$saleQuantity() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.saleQuantityIndex);
    }

    @Override
    public void realmSet$saleQuantity(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.saleQuantityIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.saleQuantityIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.saleQuantityIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.saleQuantityIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$saleSubTotal() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.saleSubTotalIndex);
    }

    @Override
    public void realmSet$saleSubTotal(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.saleSubTotalIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.saleSubTotalIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.saleSubTotalIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.saleSubTotalIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("SaleItem", 7, 0);
        builder.addPersistedProperty("id", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("saleStockId", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("saleItemName", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("salePpPrice", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("saleMrpPrice", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("saleQuantity", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("saleSubTotal", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static SaleItemColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new SaleItemColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "SaleItem";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "SaleItem";
    }

    @SuppressWarnings("cast")
    public static com.portal.terminalbd.model.SaleItem createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.portal.terminalbd.model.SaleItem obj = null;
        if (update) {
            Table table = realm.getTable(com.portal.terminalbd.model.SaleItem.class);
            SaleItemColumnInfo columnInfo = (SaleItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SaleItem.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SaleItem.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_portal_terminalbd_model_SaleItemRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.com_portal_terminalbd_model_SaleItemRealmProxy) realm.createObjectInternal(com.portal.terminalbd.model.SaleItem.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_portal_terminalbd_model_SaleItemRealmProxy) realm.createObjectInternal(com.portal.terminalbd.model.SaleItem.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final com_portal_terminalbd_model_SaleItemRealmProxyInterface objProxy = (com_portal_terminalbd_model_SaleItemRealmProxyInterface) obj;
        if (json.has("saleStockId")) {
            if (json.isNull("saleStockId")) {
                objProxy.realmSet$saleStockId(null);
            } else {
                objProxy.realmSet$saleStockId((String) json.getString("saleStockId"));
            }
        }
        if (json.has("saleItemName")) {
            if (json.isNull("saleItemName")) {
                objProxy.realmSet$saleItemName(null);
            } else {
                objProxy.realmSet$saleItemName((String) json.getString("saleItemName"));
            }
        }
        if (json.has("salePpPrice")) {
            if (json.isNull("salePpPrice")) {
                objProxy.realmSet$salePpPrice(null);
            } else {
                objProxy.realmSet$salePpPrice((String) json.getString("salePpPrice"));
            }
        }
        if (json.has("saleMrpPrice")) {
            if (json.isNull("saleMrpPrice")) {
                objProxy.realmSet$saleMrpPrice(null);
            } else {
                objProxy.realmSet$saleMrpPrice((String) json.getString("saleMrpPrice"));
            }
        }
        if (json.has("saleQuantity")) {
            if (json.isNull("saleQuantity")) {
                objProxy.realmSet$saleQuantity(null);
            } else {
                objProxy.realmSet$saleQuantity((String) json.getString("saleQuantity"));
            }
        }
        if (json.has("saleSubTotal")) {
            if (json.isNull("saleSubTotal")) {
                objProxy.realmSet$saleSubTotal(null);
            } else {
                objProxy.realmSet$saleSubTotal((String) json.getString("saleSubTotal"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.portal.terminalbd.model.SaleItem createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.portal.terminalbd.model.SaleItem obj = new com.portal.terminalbd.model.SaleItem();
        final com_portal_terminalbd_model_SaleItemRealmProxyInterface objProxy = (com_portal_terminalbd_model_SaleItemRealmProxyInterface) obj;
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
            } else if (name.equals("saleStockId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$saleStockId((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$saleStockId(null);
                }
            } else if (name.equals("saleItemName")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$saleItemName((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$saleItemName(null);
                }
            } else if (name.equals("salePpPrice")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$salePpPrice((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$salePpPrice(null);
                }
            } else if (name.equals("saleMrpPrice")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$saleMrpPrice((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$saleMrpPrice(null);
                }
            } else if (name.equals("saleQuantity")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$saleQuantity((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$saleQuantity(null);
                }
            } else if (name.equals("saleSubTotal")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$saleSubTotal((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$saleSubTotal(null);
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

    private static com_portal_terminalbd_model_SaleItemRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SaleItem.class), false, Collections.<String>emptyList());
        io.realm.com_portal_terminalbd_model_SaleItemRealmProxy obj = new io.realm.com_portal_terminalbd_model_SaleItemRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.portal.terminalbd.model.SaleItem copyOrUpdate(Realm realm, SaleItemColumnInfo columnInfo, com.portal.terminalbd.model.SaleItem object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.portal.terminalbd.model.SaleItem) cachedRealmObject;
        }

        com.portal.terminalbd.model.SaleItem realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.portal.terminalbd.model.SaleItem.class);
            long pkColumnIndex = columnInfo.idIndex;
            String value = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$id();
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
                    realmObject = new io.realm.com_portal_terminalbd_model_SaleItemRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.portal.terminalbd.model.SaleItem copy(Realm realm, SaleItemColumnInfo columnInfo, com.portal.terminalbd.model.SaleItem newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.portal.terminalbd.model.SaleItem) cachedRealmObject;
        }

        com_portal_terminalbd_model_SaleItemRealmProxyInterface realmObjectSource = (com_portal_terminalbd_model_SaleItemRealmProxyInterface) newObject;

        Table table = realm.getTable(com.portal.terminalbd.model.SaleItem.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);

        // Add all non-"object reference" fields
        builder.addString(columnInfo.idIndex, realmObjectSource.realmGet$id());
        builder.addString(columnInfo.saleStockIdIndex, realmObjectSource.realmGet$saleStockId());
        builder.addString(columnInfo.saleItemNameIndex, realmObjectSource.realmGet$saleItemName());
        builder.addString(columnInfo.salePpPriceIndex, realmObjectSource.realmGet$salePpPrice());
        builder.addString(columnInfo.saleMrpPriceIndex, realmObjectSource.realmGet$saleMrpPrice());
        builder.addString(columnInfo.saleQuantityIndex, realmObjectSource.realmGet$saleQuantity());
        builder.addString(columnInfo.saleSubTotalIndex, realmObjectSource.realmGet$saleSubTotal());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_portal_terminalbd_model_SaleItemRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.portal.terminalbd.model.SaleItem object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.SaleItem.class);
        long tableNativePtr = table.getNativePtr();
        SaleItemColumnInfo columnInfo = (SaleItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SaleItem.class);
        long pkColumnIndex = columnInfo.idIndex;
        String primaryKeyValue = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$id();
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
        String realmGet$saleStockId = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$saleStockId();
        if (realmGet$saleStockId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.saleStockIdIndex, rowIndex, realmGet$saleStockId, false);
        }
        String realmGet$saleItemName = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$saleItemName();
        if (realmGet$saleItemName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.saleItemNameIndex, rowIndex, realmGet$saleItemName, false);
        }
        String realmGet$salePpPrice = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$salePpPrice();
        if (realmGet$salePpPrice != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.salePpPriceIndex, rowIndex, realmGet$salePpPrice, false);
        }
        String realmGet$saleMrpPrice = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$saleMrpPrice();
        if (realmGet$saleMrpPrice != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.saleMrpPriceIndex, rowIndex, realmGet$saleMrpPrice, false);
        }
        String realmGet$saleQuantity = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$saleQuantity();
        if (realmGet$saleQuantity != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.saleQuantityIndex, rowIndex, realmGet$saleQuantity, false);
        }
        String realmGet$saleSubTotal = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$saleSubTotal();
        if (realmGet$saleSubTotal != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.saleSubTotalIndex, rowIndex, realmGet$saleSubTotal, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.SaleItem.class);
        long tableNativePtr = table.getNativePtr();
        SaleItemColumnInfo columnInfo = (SaleItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SaleItem.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.portal.terminalbd.model.SaleItem object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.SaleItem) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            String primaryKeyValue = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$id();
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
            String realmGet$saleStockId = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$saleStockId();
            if (realmGet$saleStockId != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.saleStockIdIndex, rowIndex, realmGet$saleStockId, false);
            }
            String realmGet$saleItemName = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$saleItemName();
            if (realmGet$saleItemName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.saleItemNameIndex, rowIndex, realmGet$saleItemName, false);
            }
            String realmGet$salePpPrice = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$salePpPrice();
            if (realmGet$salePpPrice != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.salePpPriceIndex, rowIndex, realmGet$salePpPrice, false);
            }
            String realmGet$saleMrpPrice = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$saleMrpPrice();
            if (realmGet$saleMrpPrice != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.saleMrpPriceIndex, rowIndex, realmGet$saleMrpPrice, false);
            }
            String realmGet$saleQuantity = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$saleQuantity();
            if (realmGet$saleQuantity != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.saleQuantityIndex, rowIndex, realmGet$saleQuantity, false);
            }
            String realmGet$saleSubTotal = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$saleSubTotal();
            if (realmGet$saleSubTotal != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.saleSubTotalIndex, rowIndex, realmGet$saleSubTotal, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.portal.terminalbd.model.SaleItem object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.SaleItem.class);
        long tableNativePtr = table.getNativePtr();
        SaleItemColumnInfo columnInfo = (SaleItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SaleItem.class);
        long pkColumnIndex = columnInfo.idIndex;
        String primaryKeyValue = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$id();
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
        String realmGet$saleStockId = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$saleStockId();
        if (realmGet$saleStockId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.saleStockIdIndex, rowIndex, realmGet$saleStockId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.saleStockIdIndex, rowIndex, false);
        }
        String realmGet$saleItemName = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$saleItemName();
        if (realmGet$saleItemName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.saleItemNameIndex, rowIndex, realmGet$saleItemName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.saleItemNameIndex, rowIndex, false);
        }
        String realmGet$salePpPrice = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$salePpPrice();
        if (realmGet$salePpPrice != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.salePpPriceIndex, rowIndex, realmGet$salePpPrice, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.salePpPriceIndex, rowIndex, false);
        }
        String realmGet$saleMrpPrice = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$saleMrpPrice();
        if (realmGet$saleMrpPrice != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.saleMrpPriceIndex, rowIndex, realmGet$saleMrpPrice, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.saleMrpPriceIndex, rowIndex, false);
        }
        String realmGet$saleQuantity = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$saleQuantity();
        if (realmGet$saleQuantity != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.saleQuantityIndex, rowIndex, realmGet$saleQuantity, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.saleQuantityIndex, rowIndex, false);
        }
        String realmGet$saleSubTotal = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$saleSubTotal();
        if (realmGet$saleSubTotal != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.saleSubTotalIndex, rowIndex, realmGet$saleSubTotal, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.saleSubTotalIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.SaleItem.class);
        long tableNativePtr = table.getNativePtr();
        SaleItemColumnInfo columnInfo = (SaleItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SaleItem.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.portal.terminalbd.model.SaleItem object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.SaleItem) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            String primaryKeyValue = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$id();
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
            String realmGet$saleStockId = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$saleStockId();
            if (realmGet$saleStockId != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.saleStockIdIndex, rowIndex, realmGet$saleStockId, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.saleStockIdIndex, rowIndex, false);
            }
            String realmGet$saleItemName = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$saleItemName();
            if (realmGet$saleItemName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.saleItemNameIndex, rowIndex, realmGet$saleItemName, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.saleItemNameIndex, rowIndex, false);
            }
            String realmGet$salePpPrice = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$salePpPrice();
            if (realmGet$salePpPrice != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.salePpPriceIndex, rowIndex, realmGet$salePpPrice, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.salePpPriceIndex, rowIndex, false);
            }
            String realmGet$saleMrpPrice = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$saleMrpPrice();
            if (realmGet$saleMrpPrice != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.saleMrpPriceIndex, rowIndex, realmGet$saleMrpPrice, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.saleMrpPriceIndex, rowIndex, false);
            }
            String realmGet$saleQuantity = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$saleQuantity();
            if (realmGet$saleQuantity != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.saleQuantityIndex, rowIndex, realmGet$saleQuantity, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.saleQuantityIndex, rowIndex, false);
            }
            String realmGet$saleSubTotal = ((com_portal_terminalbd_model_SaleItemRealmProxyInterface) object).realmGet$saleSubTotal();
            if (realmGet$saleSubTotal != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.saleSubTotalIndex, rowIndex, realmGet$saleSubTotal, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.saleSubTotalIndex, rowIndex, false);
            }
        }
    }

    public static com.portal.terminalbd.model.SaleItem createDetachedCopy(com.portal.terminalbd.model.SaleItem realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.portal.terminalbd.model.SaleItem unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.portal.terminalbd.model.SaleItem();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.portal.terminalbd.model.SaleItem) cachedObject.object;
            }
            unmanagedObject = (com.portal.terminalbd.model.SaleItem) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_portal_terminalbd_model_SaleItemRealmProxyInterface unmanagedCopy = (com_portal_terminalbd_model_SaleItemRealmProxyInterface) unmanagedObject;
        com_portal_terminalbd_model_SaleItemRealmProxyInterface realmSource = (com_portal_terminalbd_model_SaleItemRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$saleStockId(realmSource.realmGet$saleStockId());
        unmanagedCopy.realmSet$saleItemName(realmSource.realmGet$saleItemName());
        unmanagedCopy.realmSet$salePpPrice(realmSource.realmGet$salePpPrice());
        unmanagedCopy.realmSet$saleMrpPrice(realmSource.realmGet$saleMrpPrice());
        unmanagedCopy.realmSet$saleQuantity(realmSource.realmGet$saleQuantity());
        unmanagedCopy.realmSet$saleSubTotal(realmSource.realmGet$saleSubTotal());

        return unmanagedObject;
    }

    static com.portal.terminalbd.model.SaleItem update(Realm realm, SaleItemColumnInfo columnInfo, com.portal.terminalbd.model.SaleItem realmObject, com.portal.terminalbd.model.SaleItem newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        com_portal_terminalbd_model_SaleItemRealmProxyInterface realmObjectTarget = (com_portal_terminalbd_model_SaleItemRealmProxyInterface) realmObject;
        com_portal_terminalbd_model_SaleItemRealmProxyInterface realmObjectSource = (com_portal_terminalbd_model_SaleItemRealmProxyInterface) newObject;
        Table table = realm.getTable(com.portal.terminalbd.model.SaleItem.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);
        builder.addString(columnInfo.idIndex, realmObjectSource.realmGet$id());
        builder.addString(columnInfo.saleStockIdIndex, realmObjectSource.realmGet$saleStockId());
        builder.addString(columnInfo.saleItemNameIndex, realmObjectSource.realmGet$saleItemName());
        builder.addString(columnInfo.salePpPriceIndex, realmObjectSource.realmGet$salePpPrice());
        builder.addString(columnInfo.saleMrpPriceIndex, realmObjectSource.realmGet$saleMrpPrice());
        builder.addString(columnInfo.saleQuantityIndex, realmObjectSource.realmGet$saleQuantity());
        builder.addString(columnInfo.saleSubTotalIndex, realmObjectSource.realmGet$saleSubTotal());

        builder.updateExistingObject();
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("SaleItem = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{saleStockId:");
        stringBuilder.append(realmGet$saleStockId() != null ? realmGet$saleStockId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{saleItemName:");
        stringBuilder.append(realmGet$saleItemName() != null ? realmGet$saleItemName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{salePpPrice:");
        stringBuilder.append(realmGet$salePpPrice() != null ? realmGet$salePpPrice() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{saleMrpPrice:");
        stringBuilder.append(realmGet$saleMrpPrice() != null ? realmGet$saleMrpPrice() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{saleQuantity:");
        stringBuilder.append(realmGet$saleQuantity() != null ? realmGet$saleQuantity() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{saleSubTotal:");
        stringBuilder.append(realmGet$saleSubTotal() != null ? realmGet$saleSubTotal() : "null");
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
        com_portal_terminalbd_model_SaleItemRealmProxy aSaleItem = (com_portal_terminalbd_model_SaleItemRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aSaleItem.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aSaleItem.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aSaleItem.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
