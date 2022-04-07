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
public class com_portal_terminalbd_model_ExpenseItemRealmProxy extends com.portal.terminalbd.model.ExpenseItem
    implements RealmObjectProxy, com_portal_terminalbd_model_ExpenseItemRealmProxyInterface {

    static final class ExpenseItemColumnInfo extends ColumnInfo {
        long maxColumnIndexValue;
        long idIndex;
        long invoiceIdIndex;
        long paymentIndex;
        long transactionMethodIndex;
        long bankAccountIndex;
        long mobileBankAccountIndex;
        long toUserIndex;
        long remarkIndex;
        long expenseCategoryIndex;
        long createdIndex;

        ExpenseItemColumnInfo(OsSchemaInfo schemaInfo) {
            super(10);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("ExpenseItem");
            this.idIndex = addColumnDetails("id", "id", objectSchemaInfo);
            this.invoiceIdIndex = addColumnDetails("invoiceId", "invoiceId", objectSchemaInfo);
            this.paymentIndex = addColumnDetails("payment", "payment", objectSchemaInfo);
            this.transactionMethodIndex = addColumnDetails("transactionMethod", "transactionMethod", objectSchemaInfo);
            this.bankAccountIndex = addColumnDetails("bankAccount", "bankAccount", objectSchemaInfo);
            this.mobileBankAccountIndex = addColumnDetails("mobileBankAccount", "mobileBankAccount", objectSchemaInfo);
            this.toUserIndex = addColumnDetails("toUser", "toUser", objectSchemaInfo);
            this.remarkIndex = addColumnDetails("remark", "remark", objectSchemaInfo);
            this.expenseCategoryIndex = addColumnDetails("expenseCategory", "expenseCategory", objectSchemaInfo);
            this.createdIndex = addColumnDetails("created", "created", objectSchemaInfo);
            this.maxColumnIndexValue = objectSchemaInfo.getMaxColumnIndex();
        }

        ExpenseItemColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new ExpenseItemColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final ExpenseItemColumnInfo src = (ExpenseItemColumnInfo) rawSrc;
            final ExpenseItemColumnInfo dst = (ExpenseItemColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.invoiceIdIndex = src.invoiceIdIndex;
            dst.paymentIndex = src.paymentIndex;
            dst.transactionMethodIndex = src.transactionMethodIndex;
            dst.bankAccountIndex = src.bankAccountIndex;
            dst.mobileBankAccountIndex = src.mobileBankAccountIndex;
            dst.toUserIndex = src.toUserIndex;
            dst.remarkIndex = src.remarkIndex;
            dst.expenseCategoryIndex = src.expenseCategoryIndex;
            dst.createdIndex = src.createdIndex;
            dst.maxColumnIndexValue = src.maxColumnIndexValue;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private ExpenseItemColumnInfo columnInfo;
    private ProxyState<com.portal.terminalbd.model.ExpenseItem> proxyState;

    com_portal_terminalbd_model_ExpenseItemRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (ExpenseItemColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.portal.terminalbd.model.ExpenseItem>(this);
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
    public String realmGet$invoiceId() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.invoiceIdIndex);
    }

    @Override
    public void realmSet$invoiceId(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.invoiceIdIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.invoiceIdIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.invoiceIdIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.invoiceIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$payment() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.paymentIndex);
    }

    @Override
    public void realmSet$payment(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.paymentIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.paymentIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$transactionMethod() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionMethodIndex);
    }

    @Override
    public void realmSet$transactionMethod(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.transactionMethodIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.transactionMethodIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionMethodIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionMethodIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$bankAccount() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.bankAccountIndex);
    }

    @Override
    public void realmSet$bankAccount(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.bankAccountIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.bankAccountIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$mobileBankAccount() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.mobileBankAccountIndex);
    }

    @Override
    public void realmSet$mobileBankAccount(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.mobileBankAccountIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.mobileBankAccountIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$toUser() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.toUserIndex);
    }

    @Override
    public void realmSet$toUser(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.toUserIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.toUserIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$remark() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.remarkIndex);
    }

    @Override
    public void realmSet$remark(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.remarkIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.remarkIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.remarkIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.remarkIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$expenseCategory() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.expenseCategoryIndex);
    }

    @Override
    public void realmSet$expenseCategory(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.expenseCategoryIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.expenseCategoryIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$created() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.createdIndex);
    }

    @Override
    public void realmSet$created(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.createdIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.createdIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.createdIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.createdIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("ExpenseItem", 10, 0);
        builder.addPersistedProperty("id", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("invoiceId", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("payment", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("transactionMethod", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("bankAccount", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("mobileBankAccount", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("toUser", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("remark", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("expenseCategory", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("created", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ExpenseItemColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new ExpenseItemColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "ExpenseItem";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "ExpenseItem";
    }

    @SuppressWarnings("cast")
    public static com.portal.terminalbd.model.ExpenseItem createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.portal.terminalbd.model.ExpenseItem obj = null;
        if (update) {
            Table table = realm.getTable(com.portal.terminalbd.model.ExpenseItem.class);
            ExpenseItemColumnInfo columnInfo = (ExpenseItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.ExpenseItem.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.portal.terminalbd.model.ExpenseItem.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_portal_terminalbd_model_ExpenseItemRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.com_portal_terminalbd_model_ExpenseItemRealmProxy) realm.createObjectInternal(com.portal.terminalbd.model.ExpenseItem.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_portal_terminalbd_model_ExpenseItemRealmProxy) realm.createObjectInternal(com.portal.terminalbd.model.ExpenseItem.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final com_portal_terminalbd_model_ExpenseItemRealmProxyInterface objProxy = (com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) obj;
        if (json.has("invoiceId")) {
            if (json.isNull("invoiceId")) {
                objProxy.realmSet$invoiceId(null);
            } else {
                objProxy.realmSet$invoiceId((String) json.getString("invoiceId"));
            }
        }
        if (json.has("payment")) {
            if (json.isNull("payment")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'payment' to null.");
            } else {
                objProxy.realmSet$payment((int) json.getInt("payment"));
            }
        }
        if (json.has("transactionMethod")) {
            if (json.isNull("transactionMethod")) {
                objProxy.realmSet$transactionMethod(null);
            } else {
                objProxy.realmSet$transactionMethod((String) json.getString("transactionMethod"));
            }
        }
        if (json.has("bankAccount")) {
            if (json.isNull("bankAccount")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'bankAccount' to null.");
            } else {
                objProxy.realmSet$bankAccount((int) json.getInt("bankAccount"));
            }
        }
        if (json.has("mobileBankAccount")) {
            if (json.isNull("mobileBankAccount")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'mobileBankAccount' to null.");
            } else {
                objProxy.realmSet$mobileBankAccount((int) json.getInt("mobileBankAccount"));
            }
        }
        if (json.has("toUser")) {
            if (json.isNull("toUser")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'toUser' to null.");
            } else {
                objProxy.realmSet$toUser((int) json.getInt("toUser"));
            }
        }
        if (json.has("remark")) {
            if (json.isNull("remark")) {
                objProxy.realmSet$remark(null);
            } else {
                objProxy.realmSet$remark((String) json.getString("remark"));
            }
        }
        if (json.has("expenseCategory")) {
            if (json.isNull("expenseCategory")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'expenseCategory' to null.");
            } else {
                objProxy.realmSet$expenseCategory((int) json.getInt("expenseCategory"));
            }
        }
        if (json.has("created")) {
            if (json.isNull("created")) {
                objProxy.realmSet$created(null);
            } else {
                objProxy.realmSet$created((String) json.getString("created"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.portal.terminalbd.model.ExpenseItem createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.portal.terminalbd.model.ExpenseItem obj = new com.portal.terminalbd.model.ExpenseItem();
        final com_portal_terminalbd_model_ExpenseItemRealmProxyInterface objProxy = (com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) obj;
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
            } else if (name.equals("invoiceId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$invoiceId((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$invoiceId(null);
                }
            } else if (name.equals("payment")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$payment((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'payment' to null.");
                }
            } else if (name.equals("transactionMethod")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$transactionMethod((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$transactionMethod(null);
                }
            } else if (name.equals("bankAccount")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$bankAccount((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'bankAccount' to null.");
                }
            } else if (name.equals("mobileBankAccount")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$mobileBankAccount((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'mobileBankAccount' to null.");
                }
            } else if (name.equals("toUser")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$toUser((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'toUser' to null.");
                }
            } else if (name.equals("remark")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$remark((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$remark(null);
                }
            } else if (name.equals("expenseCategory")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$expenseCategory((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'expenseCategory' to null.");
                }
            } else if (name.equals("created")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$created((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$created(null);
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

    private static com_portal_terminalbd_model_ExpenseItemRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.portal.terminalbd.model.ExpenseItem.class), false, Collections.<String>emptyList());
        io.realm.com_portal_terminalbd_model_ExpenseItemRealmProxy obj = new io.realm.com_portal_terminalbd_model_ExpenseItemRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.portal.terminalbd.model.ExpenseItem copyOrUpdate(Realm realm, ExpenseItemColumnInfo columnInfo, com.portal.terminalbd.model.ExpenseItem object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.portal.terminalbd.model.ExpenseItem) cachedRealmObject;
        }

        com.portal.terminalbd.model.ExpenseItem realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.portal.terminalbd.model.ExpenseItem.class);
            long pkColumnIndex = columnInfo.idIndex;
            String value = ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$id();
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
                    realmObject = new io.realm.com_portal_terminalbd_model_ExpenseItemRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.portal.terminalbd.model.ExpenseItem copy(Realm realm, ExpenseItemColumnInfo columnInfo, com.portal.terminalbd.model.ExpenseItem newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.portal.terminalbd.model.ExpenseItem) cachedRealmObject;
        }

        com_portal_terminalbd_model_ExpenseItemRealmProxyInterface realmObjectSource = (com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) newObject;

        Table table = realm.getTable(com.portal.terminalbd.model.ExpenseItem.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);

        // Add all non-"object reference" fields
        builder.addString(columnInfo.idIndex, realmObjectSource.realmGet$id());
        builder.addString(columnInfo.invoiceIdIndex, realmObjectSource.realmGet$invoiceId());
        builder.addInteger(columnInfo.paymentIndex, realmObjectSource.realmGet$payment());
        builder.addString(columnInfo.transactionMethodIndex, realmObjectSource.realmGet$transactionMethod());
        builder.addInteger(columnInfo.bankAccountIndex, realmObjectSource.realmGet$bankAccount());
        builder.addInteger(columnInfo.mobileBankAccountIndex, realmObjectSource.realmGet$mobileBankAccount());
        builder.addInteger(columnInfo.toUserIndex, realmObjectSource.realmGet$toUser());
        builder.addString(columnInfo.remarkIndex, realmObjectSource.realmGet$remark());
        builder.addInteger(columnInfo.expenseCategoryIndex, realmObjectSource.realmGet$expenseCategory());
        builder.addString(columnInfo.createdIndex, realmObjectSource.realmGet$created());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_portal_terminalbd_model_ExpenseItemRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.portal.terminalbd.model.ExpenseItem object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.ExpenseItem.class);
        long tableNativePtr = table.getNativePtr();
        ExpenseItemColumnInfo columnInfo = (ExpenseItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.ExpenseItem.class);
        long pkColumnIndex = columnInfo.idIndex;
        String primaryKeyValue = ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$id();
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
        String realmGet$invoiceId = ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$invoiceId();
        if (realmGet$invoiceId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.invoiceIdIndex, rowIndex, realmGet$invoiceId, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.paymentIndex, rowIndex, ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$payment(), false);
        String realmGet$transactionMethod = ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$transactionMethod();
        if (realmGet$transactionMethod != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.transactionMethodIndex, rowIndex, realmGet$transactionMethod, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.bankAccountIndex, rowIndex, ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$bankAccount(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.mobileBankAccountIndex, rowIndex, ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$mobileBankAccount(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.toUserIndex, rowIndex, ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$toUser(), false);
        String realmGet$remark = ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$remark();
        if (realmGet$remark != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.remarkIndex, rowIndex, realmGet$remark, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.expenseCategoryIndex, rowIndex, ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$expenseCategory(), false);
        String realmGet$created = ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$created();
        if (realmGet$created != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.createdIndex, rowIndex, realmGet$created, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.ExpenseItem.class);
        long tableNativePtr = table.getNativePtr();
        ExpenseItemColumnInfo columnInfo = (ExpenseItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.ExpenseItem.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.portal.terminalbd.model.ExpenseItem object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.ExpenseItem) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            String primaryKeyValue = ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$id();
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
            String realmGet$invoiceId = ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$invoiceId();
            if (realmGet$invoiceId != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.invoiceIdIndex, rowIndex, realmGet$invoiceId, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.paymentIndex, rowIndex, ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$payment(), false);
            String realmGet$transactionMethod = ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$transactionMethod();
            if (realmGet$transactionMethod != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.transactionMethodIndex, rowIndex, realmGet$transactionMethod, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.bankAccountIndex, rowIndex, ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$bankAccount(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.mobileBankAccountIndex, rowIndex, ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$mobileBankAccount(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.toUserIndex, rowIndex, ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$toUser(), false);
            String realmGet$remark = ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$remark();
            if (realmGet$remark != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.remarkIndex, rowIndex, realmGet$remark, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.expenseCategoryIndex, rowIndex, ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$expenseCategory(), false);
            String realmGet$created = ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$created();
            if (realmGet$created != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.createdIndex, rowIndex, realmGet$created, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.portal.terminalbd.model.ExpenseItem object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.ExpenseItem.class);
        long tableNativePtr = table.getNativePtr();
        ExpenseItemColumnInfo columnInfo = (ExpenseItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.ExpenseItem.class);
        long pkColumnIndex = columnInfo.idIndex;
        String primaryKeyValue = ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$id();
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
        String realmGet$invoiceId = ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$invoiceId();
        if (realmGet$invoiceId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.invoiceIdIndex, rowIndex, realmGet$invoiceId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.invoiceIdIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.paymentIndex, rowIndex, ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$payment(), false);
        String realmGet$transactionMethod = ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$transactionMethod();
        if (realmGet$transactionMethod != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.transactionMethodIndex, rowIndex, realmGet$transactionMethod, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.transactionMethodIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.bankAccountIndex, rowIndex, ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$bankAccount(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.mobileBankAccountIndex, rowIndex, ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$mobileBankAccount(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.toUserIndex, rowIndex, ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$toUser(), false);
        String realmGet$remark = ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$remark();
        if (realmGet$remark != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.remarkIndex, rowIndex, realmGet$remark, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.remarkIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.expenseCategoryIndex, rowIndex, ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$expenseCategory(), false);
        String realmGet$created = ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$created();
        if (realmGet$created != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.createdIndex, rowIndex, realmGet$created, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.createdIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.ExpenseItem.class);
        long tableNativePtr = table.getNativePtr();
        ExpenseItemColumnInfo columnInfo = (ExpenseItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.ExpenseItem.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.portal.terminalbd.model.ExpenseItem object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.ExpenseItem) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            String primaryKeyValue = ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$id();
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
            String realmGet$invoiceId = ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$invoiceId();
            if (realmGet$invoiceId != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.invoiceIdIndex, rowIndex, realmGet$invoiceId, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.invoiceIdIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.paymentIndex, rowIndex, ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$payment(), false);
            String realmGet$transactionMethod = ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$transactionMethod();
            if (realmGet$transactionMethod != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.transactionMethodIndex, rowIndex, realmGet$transactionMethod, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.transactionMethodIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.bankAccountIndex, rowIndex, ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$bankAccount(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.mobileBankAccountIndex, rowIndex, ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$mobileBankAccount(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.toUserIndex, rowIndex, ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$toUser(), false);
            String realmGet$remark = ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$remark();
            if (realmGet$remark != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.remarkIndex, rowIndex, realmGet$remark, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.remarkIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.expenseCategoryIndex, rowIndex, ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$expenseCategory(), false);
            String realmGet$created = ((com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) object).realmGet$created();
            if (realmGet$created != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.createdIndex, rowIndex, realmGet$created, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.createdIndex, rowIndex, false);
            }
        }
    }

    public static com.portal.terminalbd.model.ExpenseItem createDetachedCopy(com.portal.terminalbd.model.ExpenseItem realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.portal.terminalbd.model.ExpenseItem unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.portal.terminalbd.model.ExpenseItem();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.portal.terminalbd.model.ExpenseItem) cachedObject.object;
            }
            unmanagedObject = (com.portal.terminalbd.model.ExpenseItem) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_portal_terminalbd_model_ExpenseItemRealmProxyInterface unmanagedCopy = (com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) unmanagedObject;
        com_portal_terminalbd_model_ExpenseItemRealmProxyInterface realmSource = (com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$invoiceId(realmSource.realmGet$invoiceId());
        unmanagedCopy.realmSet$payment(realmSource.realmGet$payment());
        unmanagedCopy.realmSet$transactionMethod(realmSource.realmGet$transactionMethod());
        unmanagedCopy.realmSet$bankAccount(realmSource.realmGet$bankAccount());
        unmanagedCopy.realmSet$mobileBankAccount(realmSource.realmGet$mobileBankAccount());
        unmanagedCopy.realmSet$toUser(realmSource.realmGet$toUser());
        unmanagedCopy.realmSet$remark(realmSource.realmGet$remark());
        unmanagedCopy.realmSet$expenseCategory(realmSource.realmGet$expenseCategory());
        unmanagedCopy.realmSet$created(realmSource.realmGet$created());

        return unmanagedObject;
    }

    static com.portal.terminalbd.model.ExpenseItem update(Realm realm, ExpenseItemColumnInfo columnInfo, com.portal.terminalbd.model.ExpenseItem realmObject, com.portal.terminalbd.model.ExpenseItem newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        com_portal_terminalbd_model_ExpenseItemRealmProxyInterface realmObjectTarget = (com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) realmObject;
        com_portal_terminalbd_model_ExpenseItemRealmProxyInterface realmObjectSource = (com_portal_terminalbd_model_ExpenseItemRealmProxyInterface) newObject;
        Table table = realm.getTable(com.portal.terminalbd.model.ExpenseItem.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);
        builder.addString(columnInfo.idIndex, realmObjectSource.realmGet$id());
        builder.addString(columnInfo.invoiceIdIndex, realmObjectSource.realmGet$invoiceId());
        builder.addInteger(columnInfo.paymentIndex, realmObjectSource.realmGet$payment());
        builder.addString(columnInfo.transactionMethodIndex, realmObjectSource.realmGet$transactionMethod());
        builder.addInteger(columnInfo.bankAccountIndex, realmObjectSource.realmGet$bankAccount());
        builder.addInteger(columnInfo.mobileBankAccountIndex, realmObjectSource.realmGet$mobileBankAccount());
        builder.addInteger(columnInfo.toUserIndex, realmObjectSource.realmGet$toUser());
        builder.addString(columnInfo.remarkIndex, realmObjectSource.realmGet$remark());
        builder.addInteger(columnInfo.expenseCategoryIndex, realmObjectSource.realmGet$expenseCategory());
        builder.addString(columnInfo.createdIndex, realmObjectSource.realmGet$created());

        builder.updateExistingObject();
        return realmObject;
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
        com_portal_terminalbd_model_ExpenseItemRealmProxy aExpenseItem = (com_portal_terminalbd_model_ExpenseItemRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aExpenseItem.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aExpenseItem.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aExpenseItem.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
