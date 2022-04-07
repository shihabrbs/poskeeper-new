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
public class com_portal_terminalbd_model_DIMSItemRealmProxy extends com.portal.terminalbd.model.DIMSItem
    implements RealmObjectProxy, com_portal_terminalbd_model_DIMSItemRealmProxyInterface {

    static final class DIMSItemColumnInfo extends ColumnInfo {
        long maxColumnIndexValue;
        long globalIdIndex;
        long medicineIdIndex;
        long medicineNameIndex;
        long salesPriceIndex;
        long medicineFormIndex;
        long strengthIndex;
        long genericNameIndex;
        long brandIndex;
        long useForIndex;

        DIMSItemColumnInfo(OsSchemaInfo schemaInfo) {
            super(9);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("DIMSItem");
            this.globalIdIndex = addColumnDetails("globalId", "globalId", objectSchemaInfo);
            this.medicineIdIndex = addColumnDetails("medicineId", "medicineId", objectSchemaInfo);
            this.medicineNameIndex = addColumnDetails("medicineName", "medicineName", objectSchemaInfo);
            this.salesPriceIndex = addColumnDetails("salesPrice", "salesPrice", objectSchemaInfo);
            this.medicineFormIndex = addColumnDetails("medicineForm", "medicineForm", objectSchemaInfo);
            this.strengthIndex = addColumnDetails("strength", "strength", objectSchemaInfo);
            this.genericNameIndex = addColumnDetails("genericName", "genericName", objectSchemaInfo);
            this.brandIndex = addColumnDetails("brand", "brand", objectSchemaInfo);
            this.useForIndex = addColumnDetails("useFor", "useFor", objectSchemaInfo);
            this.maxColumnIndexValue = objectSchemaInfo.getMaxColumnIndex();
        }

        DIMSItemColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new DIMSItemColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final DIMSItemColumnInfo src = (DIMSItemColumnInfo) rawSrc;
            final DIMSItemColumnInfo dst = (DIMSItemColumnInfo) rawDst;
            dst.globalIdIndex = src.globalIdIndex;
            dst.medicineIdIndex = src.medicineIdIndex;
            dst.medicineNameIndex = src.medicineNameIndex;
            dst.salesPriceIndex = src.salesPriceIndex;
            dst.medicineFormIndex = src.medicineFormIndex;
            dst.strengthIndex = src.strengthIndex;
            dst.genericNameIndex = src.genericNameIndex;
            dst.brandIndex = src.brandIndex;
            dst.useForIndex = src.useForIndex;
            dst.maxColumnIndexValue = src.maxColumnIndexValue;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private DIMSItemColumnInfo columnInfo;
    private ProxyState<com.portal.terminalbd.model.DIMSItem> proxyState;

    com_portal_terminalbd_model_DIMSItemRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (DIMSItemColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.portal.terminalbd.model.DIMSItem>(this);
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
    public int realmGet$medicineId() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.medicineIdIndex);
    }

    @Override
    public void realmSet$medicineId(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.medicineIdIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.medicineIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$medicineName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.medicineNameIndex);
    }

    @Override
    public void realmSet$medicineName(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.medicineNameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.medicineNameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.medicineNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.medicineNameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$salesPrice() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.salesPriceIndex);
    }

    @Override
    public void realmSet$salesPrice(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.salesPriceIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.salesPriceIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.salesPriceIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.salesPriceIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$medicineForm() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.medicineFormIndex);
    }

    @Override
    public void realmSet$medicineForm(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.medicineFormIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.medicineFormIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.medicineFormIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.medicineFormIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$strength() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.strengthIndex);
    }

    @Override
    public void realmSet$strength(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.strengthIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.strengthIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.strengthIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.strengthIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$genericName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.genericNameIndex);
    }

    @Override
    public void realmSet$genericName(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.genericNameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.genericNameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.genericNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.genericNameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$brand() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.brandIndex);
    }

    @Override
    public void realmSet$brand(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.brandIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.brandIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.brandIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.brandIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$useFor() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.useForIndex);
    }

    @Override
    public void realmSet$useFor(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.useForIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.useForIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.useForIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.useForIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("DIMSItem", 9, 0);
        builder.addPersistedProperty("globalId", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("medicineId", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("medicineName", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("salesPrice", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("medicineForm", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("strength", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("genericName", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("brand", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("useFor", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static DIMSItemColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new DIMSItemColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "DIMSItem";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "DIMSItem";
    }

    @SuppressWarnings("cast")
    public static com.portal.terminalbd.model.DIMSItem createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.portal.terminalbd.model.DIMSItem obj = realm.createObjectInternal(com.portal.terminalbd.model.DIMSItem.class, true, excludeFields);

        final com_portal_terminalbd_model_DIMSItemRealmProxyInterface objProxy = (com_portal_terminalbd_model_DIMSItemRealmProxyInterface) obj;
        if (json.has("globalId")) {
            if (json.isNull("globalId")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'globalId' to null.");
            } else {
                objProxy.realmSet$globalId((int) json.getInt("globalId"));
            }
        }
        if (json.has("medicineId")) {
            if (json.isNull("medicineId")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'medicineId' to null.");
            } else {
                objProxy.realmSet$medicineId((int) json.getInt("medicineId"));
            }
        }
        if (json.has("medicineName")) {
            if (json.isNull("medicineName")) {
                objProxy.realmSet$medicineName(null);
            } else {
                objProxy.realmSet$medicineName((String) json.getString("medicineName"));
            }
        }
        if (json.has("salesPrice")) {
            if (json.isNull("salesPrice")) {
                objProxy.realmSet$salesPrice(null);
            } else {
                objProxy.realmSet$salesPrice((String) json.getString("salesPrice"));
            }
        }
        if (json.has("medicineForm")) {
            if (json.isNull("medicineForm")) {
                objProxy.realmSet$medicineForm(null);
            } else {
                objProxy.realmSet$medicineForm((String) json.getString("medicineForm"));
            }
        }
        if (json.has("strength")) {
            if (json.isNull("strength")) {
                objProxy.realmSet$strength(null);
            } else {
                objProxy.realmSet$strength((String) json.getString("strength"));
            }
        }
        if (json.has("genericName")) {
            if (json.isNull("genericName")) {
                objProxy.realmSet$genericName(null);
            } else {
                objProxy.realmSet$genericName((String) json.getString("genericName"));
            }
        }
        if (json.has("brand")) {
            if (json.isNull("brand")) {
                objProxy.realmSet$brand(null);
            } else {
                objProxy.realmSet$brand((String) json.getString("brand"));
            }
        }
        if (json.has("useFor")) {
            if (json.isNull("useFor")) {
                objProxy.realmSet$useFor(null);
            } else {
                objProxy.realmSet$useFor((String) json.getString("useFor"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.portal.terminalbd.model.DIMSItem createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.portal.terminalbd.model.DIMSItem obj = new com.portal.terminalbd.model.DIMSItem();
        final com_portal_terminalbd_model_DIMSItemRealmProxyInterface objProxy = (com_portal_terminalbd_model_DIMSItemRealmProxyInterface) obj;
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
            } else if (name.equals("medicineId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$medicineId((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'medicineId' to null.");
                }
            } else if (name.equals("medicineName")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$medicineName((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$medicineName(null);
                }
            } else if (name.equals("salesPrice")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$salesPrice((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$salesPrice(null);
                }
            } else if (name.equals("medicineForm")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$medicineForm((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$medicineForm(null);
                }
            } else if (name.equals("strength")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$strength((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$strength(null);
                }
            } else if (name.equals("genericName")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$genericName((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$genericName(null);
                }
            } else if (name.equals("brand")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$brand((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$brand(null);
                }
            } else if (name.equals("useFor")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$useFor((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$useFor(null);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    private static com_portal_terminalbd_model_DIMSItemRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.portal.terminalbd.model.DIMSItem.class), false, Collections.<String>emptyList());
        io.realm.com_portal_terminalbd_model_DIMSItemRealmProxy obj = new io.realm.com_portal_terminalbd_model_DIMSItemRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.portal.terminalbd.model.DIMSItem copyOrUpdate(Realm realm, DIMSItemColumnInfo columnInfo, com.portal.terminalbd.model.DIMSItem object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.portal.terminalbd.model.DIMSItem) cachedRealmObject;
        }

        return copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.portal.terminalbd.model.DIMSItem copy(Realm realm, DIMSItemColumnInfo columnInfo, com.portal.terminalbd.model.DIMSItem newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.portal.terminalbd.model.DIMSItem) cachedRealmObject;
        }

        com_portal_terminalbd_model_DIMSItemRealmProxyInterface realmObjectSource = (com_portal_terminalbd_model_DIMSItemRealmProxyInterface) newObject;

        Table table = realm.getTable(com.portal.terminalbd.model.DIMSItem.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);

        // Add all non-"object reference" fields
        builder.addInteger(columnInfo.globalIdIndex, realmObjectSource.realmGet$globalId());
        builder.addInteger(columnInfo.medicineIdIndex, realmObjectSource.realmGet$medicineId());
        builder.addString(columnInfo.medicineNameIndex, realmObjectSource.realmGet$medicineName());
        builder.addString(columnInfo.salesPriceIndex, realmObjectSource.realmGet$salesPrice());
        builder.addString(columnInfo.medicineFormIndex, realmObjectSource.realmGet$medicineForm());
        builder.addString(columnInfo.strengthIndex, realmObjectSource.realmGet$strength());
        builder.addString(columnInfo.genericNameIndex, realmObjectSource.realmGet$genericName());
        builder.addString(columnInfo.brandIndex, realmObjectSource.realmGet$brand());
        builder.addString(columnInfo.useForIndex, realmObjectSource.realmGet$useFor());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_portal_terminalbd_model_DIMSItemRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.portal.terminalbd.model.DIMSItem object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.DIMSItem.class);
        long tableNativePtr = table.getNativePtr();
        DIMSItemColumnInfo columnInfo = (DIMSItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.DIMSItem.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.globalIdIndex, rowIndex, ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$globalId(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.medicineIdIndex, rowIndex, ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$medicineId(), false);
        String realmGet$medicineName = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$medicineName();
        if (realmGet$medicineName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.medicineNameIndex, rowIndex, realmGet$medicineName, false);
        }
        String realmGet$salesPrice = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$salesPrice();
        if (realmGet$salesPrice != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.salesPriceIndex, rowIndex, realmGet$salesPrice, false);
        }
        String realmGet$medicineForm = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$medicineForm();
        if (realmGet$medicineForm != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.medicineFormIndex, rowIndex, realmGet$medicineForm, false);
        }
        String realmGet$strength = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$strength();
        if (realmGet$strength != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.strengthIndex, rowIndex, realmGet$strength, false);
        }
        String realmGet$genericName = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$genericName();
        if (realmGet$genericName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.genericNameIndex, rowIndex, realmGet$genericName, false);
        }
        String realmGet$brand = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$brand();
        if (realmGet$brand != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.brandIndex, rowIndex, realmGet$brand, false);
        }
        String realmGet$useFor = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$useFor();
        if (realmGet$useFor != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.useForIndex, rowIndex, realmGet$useFor, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.DIMSItem.class);
        long tableNativePtr = table.getNativePtr();
        DIMSItemColumnInfo columnInfo = (DIMSItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.DIMSItem.class);
        com.portal.terminalbd.model.DIMSItem object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.DIMSItem) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.globalIdIndex, rowIndex, ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$globalId(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.medicineIdIndex, rowIndex, ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$medicineId(), false);
            String realmGet$medicineName = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$medicineName();
            if (realmGet$medicineName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.medicineNameIndex, rowIndex, realmGet$medicineName, false);
            }
            String realmGet$salesPrice = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$salesPrice();
            if (realmGet$salesPrice != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.salesPriceIndex, rowIndex, realmGet$salesPrice, false);
            }
            String realmGet$medicineForm = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$medicineForm();
            if (realmGet$medicineForm != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.medicineFormIndex, rowIndex, realmGet$medicineForm, false);
            }
            String realmGet$strength = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$strength();
            if (realmGet$strength != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.strengthIndex, rowIndex, realmGet$strength, false);
            }
            String realmGet$genericName = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$genericName();
            if (realmGet$genericName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.genericNameIndex, rowIndex, realmGet$genericName, false);
            }
            String realmGet$brand = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$brand();
            if (realmGet$brand != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.brandIndex, rowIndex, realmGet$brand, false);
            }
            String realmGet$useFor = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$useFor();
            if (realmGet$useFor != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.useForIndex, rowIndex, realmGet$useFor, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.portal.terminalbd.model.DIMSItem object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.DIMSItem.class);
        long tableNativePtr = table.getNativePtr();
        DIMSItemColumnInfo columnInfo = (DIMSItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.DIMSItem.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.globalIdIndex, rowIndex, ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$globalId(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.medicineIdIndex, rowIndex, ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$medicineId(), false);
        String realmGet$medicineName = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$medicineName();
        if (realmGet$medicineName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.medicineNameIndex, rowIndex, realmGet$medicineName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.medicineNameIndex, rowIndex, false);
        }
        String realmGet$salesPrice = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$salesPrice();
        if (realmGet$salesPrice != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.salesPriceIndex, rowIndex, realmGet$salesPrice, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.salesPriceIndex, rowIndex, false);
        }
        String realmGet$medicineForm = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$medicineForm();
        if (realmGet$medicineForm != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.medicineFormIndex, rowIndex, realmGet$medicineForm, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.medicineFormIndex, rowIndex, false);
        }
        String realmGet$strength = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$strength();
        if (realmGet$strength != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.strengthIndex, rowIndex, realmGet$strength, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.strengthIndex, rowIndex, false);
        }
        String realmGet$genericName = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$genericName();
        if (realmGet$genericName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.genericNameIndex, rowIndex, realmGet$genericName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.genericNameIndex, rowIndex, false);
        }
        String realmGet$brand = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$brand();
        if (realmGet$brand != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.brandIndex, rowIndex, realmGet$brand, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.brandIndex, rowIndex, false);
        }
        String realmGet$useFor = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$useFor();
        if (realmGet$useFor != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.useForIndex, rowIndex, realmGet$useFor, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.useForIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.DIMSItem.class);
        long tableNativePtr = table.getNativePtr();
        DIMSItemColumnInfo columnInfo = (DIMSItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.DIMSItem.class);
        com.portal.terminalbd.model.DIMSItem object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.DIMSItem) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.globalIdIndex, rowIndex, ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$globalId(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.medicineIdIndex, rowIndex, ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$medicineId(), false);
            String realmGet$medicineName = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$medicineName();
            if (realmGet$medicineName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.medicineNameIndex, rowIndex, realmGet$medicineName, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.medicineNameIndex, rowIndex, false);
            }
            String realmGet$salesPrice = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$salesPrice();
            if (realmGet$salesPrice != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.salesPriceIndex, rowIndex, realmGet$salesPrice, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.salesPriceIndex, rowIndex, false);
            }
            String realmGet$medicineForm = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$medicineForm();
            if (realmGet$medicineForm != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.medicineFormIndex, rowIndex, realmGet$medicineForm, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.medicineFormIndex, rowIndex, false);
            }
            String realmGet$strength = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$strength();
            if (realmGet$strength != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.strengthIndex, rowIndex, realmGet$strength, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.strengthIndex, rowIndex, false);
            }
            String realmGet$genericName = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$genericName();
            if (realmGet$genericName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.genericNameIndex, rowIndex, realmGet$genericName, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.genericNameIndex, rowIndex, false);
            }
            String realmGet$brand = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$brand();
            if (realmGet$brand != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.brandIndex, rowIndex, realmGet$brand, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.brandIndex, rowIndex, false);
            }
            String realmGet$useFor = ((com_portal_terminalbd_model_DIMSItemRealmProxyInterface) object).realmGet$useFor();
            if (realmGet$useFor != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.useForIndex, rowIndex, realmGet$useFor, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.useForIndex, rowIndex, false);
            }
        }
    }

    public static com.portal.terminalbd.model.DIMSItem createDetachedCopy(com.portal.terminalbd.model.DIMSItem realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.portal.terminalbd.model.DIMSItem unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.portal.terminalbd.model.DIMSItem();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.portal.terminalbd.model.DIMSItem) cachedObject.object;
            }
            unmanagedObject = (com.portal.terminalbd.model.DIMSItem) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_portal_terminalbd_model_DIMSItemRealmProxyInterface unmanagedCopy = (com_portal_terminalbd_model_DIMSItemRealmProxyInterface) unmanagedObject;
        com_portal_terminalbd_model_DIMSItemRealmProxyInterface realmSource = (com_portal_terminalbd_model_DIMSItemRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$globalId(realmSource.realmGet$globalId());
        unmanagedCopy.realmSet$medicineId(realmSource.realmGet$medicineId());
        unmanagedCopy.realmSet$medicineName(realmSource.realmGet$medicineName());
        unmanagedCopy.realmSet$salesPrice(realmSource.realmGet$salesPrice());
        unmanagedCopy.realmSet$medicineForm(realmSource.realmGet$medicineForm());
        unmanagedCopy.realmSet$strength(realmSource.realmGet$strength());
        unmanagedCopy.realmSet$genericName(realmSource.realmGet$genericName());
        unmanagedCopy.realmSet$brand(realmSource.realmGet$brand());
        unmanagedCopy.realmSet$useFor(realmSource.realmGet$useFor());

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("DIMSItem = proxy[");
        stringBuilder.append("{globalId:");
        stringBuilder.append(realmGet$globalId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{medicineId:");
        stringBuilder.append(realmGet$medicineId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{medicineName:");
        stringBuilder.append(realmGet$medicineName() != null ? realmGet$medicineName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{salesPrice:");
        stringBuilder.append(realmGet$salesPrice() != null ? realmGet$salesPrice() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{medicineForm:");
        stringBuilder.append(realmGet$medicineForm() != null ? realmGet$medicineForm() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{strength:");
        stringBuilder.append(realmGet$strength() != null ? realmGet$strength() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{genericName:");
        stringBuilder.append(realmGet$genericName() != null ? realmGet$genericName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{brand:");
        stringBuilder.append(realmGet$brand() != null ? realmGet$brand() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{useFor:");
        stringBuilder.append(realmGet$useFor() != null ? realmGet$useFor() : "null");
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
        com_portal_terminalbd_model_DIMSItemRealmProxy aDIMSItem = (com_portal_terminalbd_model_DIMSItemRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aDIMSItem.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aDIMSItem.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aDIMSItem.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
