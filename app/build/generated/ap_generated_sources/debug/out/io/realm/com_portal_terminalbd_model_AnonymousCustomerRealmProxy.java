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
public class com_portal_terminalbd_model_AnonymousCustomerRealmProxy extends com.portal.terminalbd.model.AnonymousCustomer
    implements RealmObjectProxy, com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface {

    static final class AnonymousCustomerColumnInfo extends ColumnInfo {
        long maxColumnIndexValue;
        long globalIdIndex;
        long customerIdIndex;
        long nameIndex;
        long mobileIndex;
        long addressIndex;
        long emailIndex;
        long openingBalanceIndex;
        long userIdIndex;
        long statusIndex;

        AnonymousCustomerColumnInfo(OsSchemaInfo schemaInfo) {
            super(9);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("AnonymousCustomer");
            this.globalIdIndex = addColumnDetails("globalId", "globalId", objectSchemaInfo);
            this.customerIdIndex = addColumnDetails("customerId", "customerId", objectSchemaInfo);
            this.nameIndex = addColumnDetails("name", "name", objectSchemaInfo);
            this.mobileIndex = addColumnDetails("mobile", "mobile", objectSchemaInfo);
            this.addressIndex = addColumnDetails("address", "address", objectSchemaInfo);
            this.emailIndex = addColumnDetails("email", "email", objectSchemaInfo);
            this.openingBalanceIndex = addColumnDetails("openingBalance", "openingBalance", objectSchemaInfo);
            this.userIdIndex = addColumnDetails("userId", "userId", objectSchemaInfo);
            this.statusIndex = addColumnDetails("status", "status", objectSchemaInfo);
            this.maxColumnIndexValue = objectSchemaInfo.getMaxColumnIndex();
        }

        AnonymousCustomerColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new AnonymousCustomerColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final AnonymousCustomerColumnInfo src = (AnonymousCustomerColumnInfo) rawSrc;
            final AnonymousCustomerColumnInfo dst = (AnonymousCustomerColumnInfo) rawDst;
            dst.globalIdIndex = src.globalIdIndex;
            dst.customerIdIndex = src.customerIdIndex;
            dst.nameIndex = src.nameIndex;
            dst.mobileIndex = src.mobileIndex;
            dst.addressIndex = src.addressIndex;
            dst.emailIndex = src.emailIndex;
            dst.openingBalanceIndex = src.openingBalanceIndex;
            dst.userIdIndex = src.userIdIndex;
            dst.statusIndex = src.statusIndex;
            dst.maxColumnIndexValue = src.maxColumnIndexValue;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private AnonymousCustomerColumnInfo columnInfo;
    private ProxyState<com.portal.terminalbd.model.AnonymousCustomer> proxyState;

    com_portal_terminalbd_model_AnonymousCustomerRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (AnonymousCustomerColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.portal.terminalbd.model.AnonymousCustomer>(this);
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
    public int realmGet$customerId() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.customerIdIndex);
    }

    @Override
    public void realmSet$customerId(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.customerIdIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.customerIdIndex, value);
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
    public String realmGet$mobile() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.mobileIndex);
    }

    @Override
    public void realmSet$mobile(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.mobileIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.mobileIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.mobileIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.mobileIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$address() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.addressIndex);
    }

    @Override
    public void realmSet$address(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.addressIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.addressIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.addressIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.addressIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$email() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.emailIndex);
    }

    @Override
    public void realmSet$email(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.emailIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.emailIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.emailIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.emailIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$openingBalance() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.openingBalanceIndex);
    }

    @Override
    public void realmSet$openingBalance(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.openingBalanceIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.openingBalanceIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.openingBalanceIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.openingBalanceIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$userId() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.userIdIndex);
    }

    @Override
    public void realmSet$userId(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.userIdIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.userIdIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.userIdIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.userIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$status() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.statusIndex);
    }

    @Override
    public void realmSet$status(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.statusIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.statusIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.statusIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.statusIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("AnonymousCustomer", 9, 0);
        builder.addPersistedProperty("globalId", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("customerId", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("mobile", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("address", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("email", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("openingBalance", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("userId", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("status", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static AnonymousCustomerColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new AnonymousCustomerColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "AnonymousCustomer";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "AnonymousCustomer";
    }

    @SuppressWarnings("cast")
    public static com.portal.terminalbd.model.AnonymousCustomer createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.portal.terminalbd.model.AnonymousCustomer obj = realm.createObjectInternal(com.portal.terminalbd.model.AnonymousCustomer.class, true, excludeFields);

        final com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface objProxy = (com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) obj;
        if (json.has("globalId")) {
            if (json.isNull("globalId")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'globalId' to null.");
            } else {
                objProxy.realmSet$globalId((int) json.getInt("globalId"));
            }
        }
        if (json.has("customerId")) {
            if (json.isNull("customerId")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'customerId' to null.");
            } else {
                objProxy.realmSet$customerId((int) json.getInt("customerId"));
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("mobile")) {
            if (json.isNull("mobile")) {
                objProxy.realmSet$mobile(null);
            } else {
                objProxy.realmSet$mobile((String) json.getString("mobile"));
            }
        }
        if (json.has("address")) {
            if (json.isNull("address")) {
                objProxy.realmSet$address(null);
            } else {
                objProxy.realmSet$address((String) json.getString("address"));
            }
        }
        if (json.has("email")) {
            if (json.isNull("email")) {
                objProxy.realmSet$email(null);
            } else {
                objProxy.realmSet$email((String) json.getString("email"));
            }
        }
        if (json.has("openingBalance")) {
            if (json.isNull("openingBalance")) {
                objProxy.realmSet$openingBalance(null);
            } else {
                objProxy.realmSet$openingBalance((String) json.getString("openingBalance"));
            }
        }
        if (json.has("userId")) {
            if (json.isNull("userId")) {
                objProxy.realmSet$userId(null);
            } else {
                objProxy.realmSet$userId((String) json.getString("userId"));
            }
        }
        if (json.has("status")) {
            if (json.isNull("status")) {
                objProxy.realmSet$status(null);
            } else {
                objProxy.realmSet$status((String) json.getString("status"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.portal.terminalbd.model.AnonymousCustomer createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.portal.terminalbd.model.AnonymousCustomer obj = new com.portal.terminalbd.model.AnonymousCustomer();
        final com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface objProxy = (com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) obj;
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
            } else if (name.equals("customerId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$customerId((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'customerId' to null.");
                }
            } else if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (name.equals("mobile")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$mobile((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$mobile(null);
                }
            } else if (name.equals("address")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$address((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$address(null);
                }
            } else if (name.equals("email")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$email((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$email(null);
                }
            } else if (name.equals("openingBalance")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$openingBalance((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$openingBalance(null);
                }
            } else if (name.equals("userId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$userId((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$userId(null);
                }
            } else if (name.equals("status")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$status((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$status(null);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    private static com_portal_terminalbd_model_AnonymousCustomerRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.portal.terminalbd.model.AnonymousCustomer.class), false, Collections.<String>emptyList());
        io.realm.com_portal_terminalbd_model_AnonymousCustomerRealmProxy obj = new io.realm.com_portal_terminalbd_model_AnonymousCustomerRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.portal.terminalbd.model.AnonymousCustomer copyOrUpdate(Realm realm, AnonymousCustomerColumnInfo columnInfo, com.portal.terminalbd.model.AnonymousCustomer object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.portal.terminalbd.model.AnonymousCustomer) cachedRealmObject;
        }

        return copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.portal.terminalbd.model.AnonymousCustomer copy(Realm realm, AnonymousCustomerColumnInfo columnInfo, com.portal.terminalbd.model.AnonymousCustomer newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.portal.terminalbd.model.AnonymousCustomer) cachedRealmObject;
        }

        com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface realmObjectSource = (com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) newObject;

        Table table = realm.getTable(com.portal.terminalbd.model.AnonymousCustomer.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);

        // Add all non-"object reference" fields
        builder.addInteger(columnInfo.globalIdIndex, realmObjectSource.realmGet$globalId());
        builder.addInteger(columnInfo.customerIdIndex, realmObjectSource.realmGet$customerId());
        builder.addString(columnInfo.nameIndex, realmObjectSource.realmGet$name());
        builder.addString(columnInfo.mobileIndex, realmObjectSource.realmGet$mobile());
        builder.addString(columnInfo.addressIndex, realmObjectSource.realmGet$address());
        builder.addString(columnInfo.emailIndex, realmObjectSource.realmGet$email());
        builder.addString(columnInfo.openingBalanceIndex, realmObjectSource.realmGet$openingBalance());
        builder.addString(columnInfo.userIdIndex, realmObjectSource.realmGet$userId());
        builder.addString(columnInfo.statusIndex, realmObjectSource.realmGet$status());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_portal_terminalbd_model_AnonymousCustomerRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.portal.terminalbd.model.AnonymousCustomer object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.AnonymousCustomer.class);
        long tableNativePtr = table.getNativePtr();
        AnonymousCustomerColumnInfo columnInfo = (AnonymousCustomerColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.AnonymousCustomer.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.globalIdIndex, rowIndex, ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$globalId(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.customerIdIndex, rowIndex, ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$customerId(), false);
        String realmGet$name = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        String realmGet$mobile = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$mobile();
        if (realmGet$mobile != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mobileIndex, rowIndex, realmGet$mobile, false);
        }
        String realmGet$address = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$address();
        if (realmGet$address != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.addressIndex, rowIndex, realmGet$address, false);
        }
        String realmGet$email = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$email();
        if (realmGet$email != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.emailIndex, rowIndex, realmGet$email, false);
        }
        String realmGet$openingBalance = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$openingBalance();
        if (realmGet$openingBalance != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.openingBalanceIndex, rowIndex, realmGet$openingBalance, false);
        }
        String realmGet$userId = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$userId();
        if (realmGet$userId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.userIdIndex, rowIndex, realmGet$userId, false);
        }
        String realmGet$status = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$status();
        if (realmGet$status != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.AnonymousCustomer.class);
        long tableNativePtr = table.getNativePtr();
        AnonymousCustomerColumnInfo columnInfo = (AnonymousCustomerColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.AnonymousCustomer.class);
        com.portal.terminalbd.model.AnonymousCustomer object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.AnonymousCustomer) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.globalIdIndex, rowIndex, ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$globalId(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.customerIdIndex, rowIndex, ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$customerId(), false);
            String realmGet$name = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            }
            String realmGet$mobile = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$mobile();
            if (realmGet$mobile != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.mobileIndex, rowIndex, realmGet$mobile, false);
            }
            String realmGet$address = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$address();
            if (realmGet$address != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.addressIndex, rowIndex, realmGet$address, false);
            }
            String realmGet$email = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$email();
            if (realmGet$email != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.emailIndex, rowIndex, realmGet$email, false);
            }
            String realmGet$openingBalance = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$openingBalance();
            if (realmGet$openingBalance != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.openingBalanceIndex, rowIndex, realmGet$openingBalance, false);
            }
            String realmGet$userId = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$userId();
            if (realmGet$userId != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.userIdIndex, rowIndex, realmGet$userId, false);
            }
            String realmGet$status = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$status();
            if (realmGet$status != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.portal.terminalbd.model.AnonymousCustomer object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.AnonymousCustomer.class);
        long tableNativePtr = table.getNativePtr();
        AnonymousCustomerColumnInfo columnInfo = (AnonymousCustomerColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.AnonymousCustomer.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.globalIdIndex, rowIndex, ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$globalId(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.customerIdIndex, rowIndex, ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$customerId(), false);
        String realmGet$name = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        String realmGet$mobile = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$mobile();
        if (realmGet$mobile != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mobileIndex, rowIndex, realmGet$mobile, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.mobileIndex, rowIndex, false);
        }
        String realmGet$address = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$address();
        if (realmGet$address != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.addressIndex, rowIndex, realmGet$address, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.addressIndex, rowIndex, false);
        }
        String realmGet$email = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$email();
        if (realmGet$email != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.emailIndex, rowIndex, realmGet$email, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.emailIndex, rowIndex, false);
        }
        String realmGet$openingBalance = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$openingBalance();
        if (realmGet$openingBalance != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.openingBalanceIndex, rowIndex, realmGet$openingBalance, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.openingBalanceIndex, rowIndex, false);
        }
        String realmGet$userId = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$userId();
        if (realmGet$userId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.userIdIndex, rowIndex, realmGet$userId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.userIdIndex, rowIndex, false);
        }
        String realmGet$status = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$status();
        if (realmGet$status != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.statusIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.AnonymousCustomer.class);
        long tableNativePtr = table.getNativePtr();
        AnonymousCustomerColumnInfo columnInfo = (AnonymousCustomerColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.AnonymousCustomer.class);
        com.portal.terminalbd.model.AnonymousCustomer object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.AnonymousCustomer) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.globalIdIndex, rowIndex, ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$globalId(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.customerIdIndex, rowIndex, ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$customerId(), false);
            String realmGet$name = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
            }
            String realmGet$mobile = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$mobile();
            if (realmGet$mobile != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.mobileIndex, rowIndex, realmGet$mobile, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.mobileIndex, rowIndex, false);
            }
            String realmGet$address = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$address();
            if (realmGet$address != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.addressIndex, rowIndex, realmGet$address, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.addressIndex, rowIndex, false);
            }
            String realmGet$email = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$email();
            if (realmGet$email != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.emailIndex, rowIndex, realmGet$email, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.emailIndex, rowIndex, false);
            }
            String realmGet$openingBalance = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$openingBalance();
            if (realmGet$openingBalance != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.openingBalanceIndex, rowIndex, realmGet$openingBalance, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.openingBalanceIndex, rowIndex, false);
            }
            String realmGet$userId = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$userId();
            if (realmGet$userId != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.userIdIndex, rowIndex, realmGet$userId, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.userIdIndex, rowIndex, false);
            }
            String realmGet$status = ((com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) object).realmGet$status();
            if (realmGet$status != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.statusIndex, rowIndex, false);
            }
        }
    }

    public static com.portal.terminalbd.model.AnonymousCustomer createDetachedCopy(com.portal.terminalbd.model.AnonymousCustomer realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.portal.terminalbd.model.AnonymousCustomer unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.portal.terminalbd.model.AnonymousCustomer();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.portal.terminalbd.model.AnonymousCustomer) cachedObject.object;
            }
            unmanagedObject = (com.portal.terminalbd.model.AnonymousCustomer) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface unmanagedCopy = (com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) unmanagedObject;
        com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface realmSource = (com_portal_terminalbd_model_AnonymousCustomerRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$globalId(realmSource.realmGet$globalId());
        unmanagedCopy.realmSet$customerId(realmSource.realmGet$customerId());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$mobile(realmSource.realmGet$mobile());
        unmanagedCopy.realmSet$address(realmSource.realmGet$address());
        unmanagedCopy.realmSet$email(realmSource.realmGet$email());
        unmanagedCopy.realmSet$openingBalance(realmSource.realmGet$openingBalance());
        unmanagedCopy.realmSet$userId(realmSource.realmGet$userId());
        unmanagedCopy.realmSet$status(realmSource.realmGet$status());

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("AnonymousCustomer = proxy[");
        stringBuilder.append("{globalId:");
        stringBuilder.append(realmGet$globalId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{customerId:");
        stringBuilder.append(realmGet$customerId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{mobile:");
        stringBuilder.append(realmGet$mobile() != null ? realmGet$mobile() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{address:");
        stringBuilder.append(realmGet$address() != null ? realmGet$address() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{email:");
        stringBuilder.append(realmGet$email() != null ? realmGet$email() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{openingBalance:");
        stringBuilder.append(realmGet$openingBalance() != null ? realmGet$openingBalance() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{userId:");
        stringBuilder.append(realmGet$userId() != null ? realmGet$userId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{status:");
        stringBuilder.append(realmGet$status() != null ? realmGet$status() : "null");
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
        com_portal_terminalbd_model_AnonymousCustomerRealmProxy aAnonymousCustomer = (com_portal_terminalbd_model_AnonymousCustomerRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aAnonymousCustomer.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aAnonymousCustomer.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aAnonymousCustomer.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
