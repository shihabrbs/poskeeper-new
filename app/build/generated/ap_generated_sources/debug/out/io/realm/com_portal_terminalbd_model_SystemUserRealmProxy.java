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
public class com_portal_terminalbd_model_SystemUserRealmProxy extends com.portal.terminalbd.model.SystemUser
    implements RealmObjectProxy, com_portal_terminalbd_model_SystemUserRealmProxyInterface {

    static final class SystemUserColumnInfo extends ColumnInfo {
        long maxColumnIndexValue;
        long userIdIndex;
        long usernameIndex;
        long emailIndex;
        long fullNameIndex;
        long passwordIndex;
        long rolesIndex;

        SystemUserColumnInfo(OsSchemaInfo schemaInfo) {
            super(6);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("SystemUser");
            this.userIdIndex = addColumnDetails("userId", "userId", objectSchemaInfo);
            this.usernameIndex = addColumnDetails("username", "username", objectSchemaInfo);
            this.emailIndex = addColumnDetails("email", "email", objectSchemaInfo);
            this.fullNameIndex = addColumnDetails("fullName", "fullName", objectSchemaInfo);
            this.passwordIndex = addColumnDetails("password", "password", objectSchemaInfo);
            this.rolesIndex = addColumnDetails("roles", "roles", objectSchemaInfo);
            this.maxColumnIndexValue = objectSchemaInfo.getMaxColumnIndex();
        }

        SystemUserColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new SystemUserColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final SystemUserColumnInfo src = (SystemUserColumnInfo) rawSrc;
            final SystemUserColumnInfo dst = (SystemUserColumnInfo) rawDst;
            dst.userIdIndex = src.userIdIndex;
            dst.usernameIndex = src.usernameIndex;
            dst.emailIndex = src.emailIndex;
            dst.fullNameIndex = src.fullNameIndex;
            dst.passwordIndex = src.passwordIndex;
            dst.rolesIndex = src.rolesIndex;
            dst.maxColumnIndexValue = src.maxColumnIndexValue;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private SystemUserColumnInfo columnInfo;
    private ProxyState<com.portal.terminalbd.model.SystemUser> proxyState;

    com_portal_terminalbd_model_SystemUserRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (SystemUserColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.portal.terminalbd.model.SystemUser>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$userId() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.userIdIndex);
    }

    @Override
    public void realmSet$userId(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.userIdIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.userIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$username() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.usernameIndex);
    }

    @Override
    public void realmSet$username(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.usernameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.usernameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.usernameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.usernameIndex, value);
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
    public String realmGet$fullName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.fullNameIndex);
    }

    @Override
    public void realmSet$fullName(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.fullNameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.fullNameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.fullNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.fullNameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$password() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.passwordIndex);
    }

    @Override
    public void realmSet$password(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.passwordIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.passwordIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.passwordIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.passwordIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$roles() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.rolesIndex);
    }

    @Override
    public void realmSet$roles(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.rolesIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.rolesIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.rolesIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.rolesIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("SystemUser", 6, 0);
        builder.addPersistedProperty("userId", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("username", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("email", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("fullName", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("password", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("roles", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static SystemUserColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new SystemUserColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "SystemUser";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "SystemUser";
    }

    @SuppressWarnings("cast")
    public static com.portal.terminalbd.model.SystemUser createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.portal.terminalbd.model.SystemUser obj = realm.createObjectInternal(com.portal.terminalbd.model.SystemUser.class, true, excludeFields);

        final com_portal_terminalbd_model_SystemUserRealmProxyInterface objProxy = (com_portal_terminalbd_model_SystemUserRealmProxyInterface) obj;
        if (json.has("userId")) {
            if (json.isNull("userId")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'userId' to null.");
            } else {
                objProxy.realmSet$userId((int) json.getInt("userId"));
            }
        }
        if (json.has("username")) {
            if (json.isNull("username")) {
                objProxy.realmSet$username(null);
            } else {
                objProxy.realmSet$username((String) json.getString("username"));
            }
        }
        if (json.has("email")) {
            if (json.isNull("email")) {
                objProxy.realmSet$email(null);
            } else {
                objProxy.realmSet$email((String) json.getString("email"));
            }
        }
        if (json.has("fullName")) {
            if (json.isNull("fullName")) {
                objProxy.realmSet$fullName(null);
            } else {
                objProxy.realmSet$fullName((String) json.getString("fullName"));
            }
        }
        if (json.has("password")) {
            if (json.isNull("password")) {
                objProxy.realmSet$password(null);
            } else {
                objProxy.realmSet$password((String) json.getString("password"));
            }
        }
        if (json.has("roles")) {
            if (json.isNull("roles")) {
                objProxy.realmSet$roles(null);
            } else {
                objProxy.realmSet$roles((String) json.getString("roles"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.portal.terminalbd.model.SystemUser createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.portal.terminalbd.model.SystemUser obj = new com.portal.terminalbd.model.SystemUser();
        final com_portal_terminalbd_model_SystemUserRealmProxyInterface objProxy = (com_portal_terminalbd_model_SystemUserRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("userId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$userId((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'userId' to null.");
                }
            } else if (name.equals("username")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$username((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$username(null);
                }
            } else if (name.equals("email")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$email((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$email(null);
                }
            } else if (name.equals("fullName")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$fullName((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$fullName(null);
                }
            } else if (name.equals("password")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$password((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$password(null);
                }
            } else if (name.equals("roles")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$roles((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$roles(null);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    private static com_portal_terminalbd_model_SystemUserRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SystemUser.class), false, Collections.<String>emptyList());
        io.realm.com_portal_terminalbd_model_SystemUserRealmProxy obj = new io.realm.com_portal_terminalbd_model_SystemUserRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.portal.terminalbd.model.SystemUser copyOrUpdate(Realm realm, SystemUserColumnInfo columnInfo, com.portal.terminalbd.model.SystemUser object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.portal.terminalbd.model.SystemUser) cachedRealmObject;
        }

        return copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.portal.terminalbd.model.SystemUser copy(Realm realm, SystemUserColumnInfo columnInfo, com.portal.terminalbd.model.SystemUser newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.portal.terminalbd.model.SystemUser) cachedRealmObject;
        }

        com_portal_terminalbd_model_SystemUserRealmProxyInterface realmObjectSource = (com_portal_terminalbd_model_SystemUserRealmProxyInterface) newObject;

        Table table = realm.getTable(com.portal.terminalbd.model.SystemUser.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);

        // Add all non-"object reference" fields
        builder.addInteger(columnInfo.userIdIndex, realmObjectSource.realmGet$userId());
        builder.addString(columnInfo.usernameIndex, realmObjectSource.realmGet$username());
        builder.addString(columnInfo.emailIndex, realmObjectSource.realmGet$email());
        builder.addString(columnInfo.fullNameIndex, realmObjectSource.realmGet$fullName());
        builder.addString(columnInfo.passwordIndex, realmObjectSource.realmGet$password());
        builder.addString(columnInfo.rolesIndex, realmObjectSource.realmGet$roles());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_portal_terminalbd_model_SystemUserRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.portal.terminalbd.model.SystemUser object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.SystemUser.class);
        long tableNativePtr = table.getNativePtr();
        SystemUserColumnInfo columnInfo = (SystemUserColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SystemUser.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.userIdIndex, rowIndex, ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$userId(), false);
        String realmGet$username = ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$username();
        if (realmGet$username != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.usernameIndex, rowIndex, realmGet$username, false);
        }
        String realmGet$email = ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$email();
        if (realmGet$email != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.emailIndex, rowIndex, realmGet$email, false);
        }
        String realmGet$fullName = ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$fullName();
        if (realmGet$fullName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.fullNameIndex, rowIndex, realmGet$fullName, false);
        }
        String realmGet$password = ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$password();
        if (realmGet$password != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.passwordIndex, rowIndex, realmGet$password, false);
        }
        String realmGet$roles = ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$roles();
        if (realmGet$roles != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.rolesIndex, rowIndex, realmGet$roles, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.SystemUser.class);
        long tableNativePtr = table.getNativePtr();
        SystemUserColumnInfo columnInfo = (SystemUserColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SystemUser.class);
        com.portal.terminalbd.model.SystemUser object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.SystemUser) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.userIdIndex, rowIndex, ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$userId(), false);
            String realmGet$username = ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$username();
            if (realmGet$username != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.usernameIndex, rowIndex, realmGet$username, false);
            }
            String realmGet$email = ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$email();
            if (realmGet$email != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.emailIndex, rowIndex, realmGet$email, false);
            }
            String realmGet$fullName = ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$fullName();
            if (realmGet$fullName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.fullNameIndex, rowIndex, realmGet$fullName, false);
            }
            String realmGet$password = ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$password();
            if (realmGet$password != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.passwordIndex, rowIndex, realmGet$password, false);
            }
            String realmGet$roles = ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$roles();
            if (realmGet$roles != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.rolesIndex, rowIndex, realmGet$roles, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.portal.terminalbd.model.SystemUser object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.SystemUser.class);
        long tableNativePtr = table.getNativePtr();
        SystemUserColumnInfo columnInfo = (SystemUserColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SystemUser.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.userIdIndex, rowIndex, ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$userId(), false);
        String realmGet$username = ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$username();
        if (realmGet$username != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.usernameIndex, rowIndex, realmGet$username, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.usernameIndex, rowIndex, false);
        }
        String realmGet$email = ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$email();
        if (realmGet$email != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.emailIndex, rowIndex, realmGet$email, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.emailIndex, rowIndex, false);
        }
        String realmGet$fullName = ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$fullName();
        if (realmGet$fullName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.fullNameIndex, rowIndex, realmGet$fullName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.fullNameIndex, rowIndex, false);
        }
        String realmGet$password = ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$password();
        if (realmGet$password != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.passwordIndex, rowIndex, realmGet$password, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.passwordIndex, rowIndex, false);
        }
        String realmGet$roles = ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$roles();
        if (realmGet$roles != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.rolesIndex, rowIndex, realmGet$roles, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.rolesIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.SystemUser.class);
        long tableNativePtr = table.getNativePtr();
        SystemUserColumnInfo columnInfo = (SystemUserColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SystemUser.class);
        com.portal.terminalbd.model.SystemUser object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.SystemUser) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.userIdIndex, rowIndex, ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$userId(), false);
            String realmGet$username = ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$username();
            if (realmGet$username != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.usernameIndex, rowIndex, realmGet$username, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.usernameIndex, rowIndex, false);
            }
            String realmGet$email = ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$email();
            if (realmGet$email != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.emailIndex, rowIndex, realmGet$email, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.emailIndex, rowIndex, false);
            }
            String realmGet$fullName = ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$fullName();
            if (realmGet$fullName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.fullNameIndex, rowIndex, realmGet$fullName, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.fullNameIndex, rowIndex, false);
            }
            String realmGet$password = ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$password();
            if (realmGet$password != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.passwordIndex, rowIndex, realmGet$password, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.passwordIndex, rowIndex, false);
            }
            String realmGet$roles = ((com_portal_terminalbd_model_SystemUserRealmProxyInterface) object).realmGet$roles();
            if (realmGet$roles != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.rolesIndex, rowIndex, realmGet$roles, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.rolesIndex, rowIndex, false);
            }
        }
    }

    public static com.portal.terminalbd.model.SystemUser createDetachedCopy(com.portal.terminalbd.model.SystemUser realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.portal.terminalbd.model.SystemUser unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.portal.terminalbd.model.SystemUser();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.portal.terminalbd.model.SystemUser) cachedObject.object;
            }
            unmanagedObject = (com.portal.terminalbd.model.SystemUser) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_portal_terminalbd_model_SystemUserRealmProxyInterface unmanagedCopy = (com_portal_terminalbd_model_SystemUserRealmProxyInterface) unmanagedObject;
        com_portal_terminalbd_model_SystemUserRealmProxyInterface realmSource = (com_portal_terminalbd_model_SystemUserRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$userId(realmSource.realmGet$userId());
        unmanagedCopy.realmSet$username(realmSource.realmGet$username());
        unmanagedCopy.realmSet$email(realmSource.realmGet$email());
        unmanagedCopy.realmSet$fullName(realmSource.realmGet$fullName());
        unmanagedCopy.realmSet$password(realmSource.realmGet$password());
        unmanagedCopy.realmSet$roles(realmSource.realmGet$roles());

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("SystemUser = proxy[");
        stringBuilder.append("{userId:");
        stringBuilder.append(realmGet$userId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{username:");
        stringBuilder.append(realmGet$username() != null ? realmGet$username() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{email:");
        stringBuilder.append(realmGet$email() != null ? realmGet$email() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{fullName:");
        stringBuilder.append(realmGet$fullName() != null ? realmGet$fullName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{password:");
        stringBuilder.append(realmGet$password() != null ? realmGet$password() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{roles:");
        stringBuilder.append(realmGet$roles() != null ? realmGet$roles() : "null");
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
        com_portal_terminalbd_model_SystemUserRealmProxy aSystemUser = (com_portal_terminalbd_model_SystemUserRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aSystemUser.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aSystemUser.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aSystemUser.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
