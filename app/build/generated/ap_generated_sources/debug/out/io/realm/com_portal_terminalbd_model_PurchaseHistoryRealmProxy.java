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
public class com_portal_terminalbd_model_PurchaseHistoryRealmProxy extends com.portal.terminalbd.model.PurchaseHistory
    implements RealmObjectProxy, com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface {

    static final class PurchaseHistoryColumnInfo extends ColumnInfo {
        long maxColumnIndexValue;
        long idIndex;
        long invoiceIdIndex;
        long subTotalIndex;
        long discountIndex;
        long discountTypeIndex;
        long discountCulculationIndex;
        long vatIndex;
        long totalIndex;
        long paymentIndex;
        long dueIndex;
        long transactionMethodIndex;
        long bankAccountIndex;
        long mobileBankAccountIndex;
        long receivedByIndex;
        long vendorIdIndex;
        long createdIndex;

        PurchaseHistoryColumnInfo(OsSchemaInfo schemaInfo) {
            super(16);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("PurchaseHistory");
            this.idIndex = addColumnDetails("id", "id", objectSchemaInfo);
            this.invoiceIdIndex = addColumnDetails("invoiceId", "invoiceId", objectSchemaInfo);
            this.subTotalIndex = addColumnDetails("subTotal", "subTotal", objectSchemaInfo);
            this.discountIndex = addColumnDetails("discount", "discount", objectSchemaInfo);
            this.discountTypeIndex = addColumnDetails("discountType", "discountType", objectSchemaInfo);
            this.discountCulculationIndex = addColumnDetails("discountCulculation", "discountCulculation", objectSchemaInfo);
            this.vatIndex = addColumnDetails("vat", "vat", objectSchemaInfo);
            this.totalIndex = addColumnDetails("total", "total", objectSchemaInfo);
            this.paymentIndex = addColumnDetails("payment", "payment", objectSchemaInfo);
            this.dueIndex = addColumnDetails("due", "due", objectSchemaInfo);
            this.transactionMethodIndex = addColumnDetails("transactionMethod", "transactionMethod", objectSchemaInfo);
            this.bankAccountIndex = addColumnDetails("bankAccount", "bankAccount", objectSchemaInfo);
            this.mobileBankAccountIndex = addColumnDetails("mobileBankAccount", "mobileBankAccount", objectSchemaInfo);
            this.receivedByIndex = addColumnDetails("receivedBy", "receivedBy", objectSchemaInfo);
            this.vendorIdIndex = addColumnDetails("vendorId", "vendorId", objectSchemaInfo);
            this.createdIndex = addColumnDetails("created", "created", objectSchemaInfo);
            this.maxColumnIndexValue = objectSchemaInfo.getMaxColumnIndex();
        }

        PurchaseHistoryColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new PurchaseHistoryColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final PurchaseHistoryColumnInfo src = (PurchaseHistoryColumnInfo) rawSrc;
            final PurchaseHistoryColumnInfo dst = (PurchaseHistoryColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.invoiceIdIndex = src.invoiceIdIndex;
            dst.subTotalIndex = src.subTotalIndex;
            dst.discountIndex = src.discountIndex;
            dst.discountTypeIndex = src.discountTypeIndex;
            dst.discountCulculationIndex = src.discountCulculationIndex;
            dst.vatIndex = src.vatIndex;
            dst.totalIndex = src.totalIndex;
            dst.paymentIndex = src.paymentIndex;
            dst.dueIndex = src.dueIndex;
            dst.transactionMethodIndex = src.transactionMethodIndex;
            dst.bankAccountIndex = src.bankAccountIndex;
            dst.mobileBankAccountIndex = src.mobileBankAccountIndex;
            dst.receivedByIndex = src.receivedByIndex;
            dst.vendorIdIndex = src.vendorIdIndex;
            dst.createdIndex = src.createdIndex;
            dst.maxColumnIndexValue = src.maxColumnIndexValue;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private PurchaseHistoryColumnInfo columnInfo;
    private ProxyState<com.portal.terminalbd.model.PurchaseHistory> proxyState;

    com_portal_terminalbd_model_PurchaseHistoryRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (PurchaseHistoryColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.portal.terminalbd.model.PurchaseHistory>(this);
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

    @Override
    @SuppressWarnings("cast")
    public int realmGet$discount() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.discountIndex);
    }

    @Override
    public void realmSet$discount(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.discountIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.discountIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$discountType() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.discountTypeIndex);
    }

    @Override
    public void realmSet$discountType(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.discountTypeIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.discountTypeIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.discountTypeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.discountTypeIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$discountCulculation() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.discountCulculationIndex);
    }

    @Override
    public void realmSet$discountCulculation(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.discountCulculationIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.discountCulculationIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$vat() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.vatIndex);
    }

    @Override
    public void realmSet$vat(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.vatIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.vatIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$total() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.totalIndex);
    }

    @Override
    public void realmSet$total(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.totalIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.totalIndex, value);
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
    public int realmGet$due() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.dueIndex);
    }

    @Override
    public void realmSet$due(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.dueIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.dueIndex, value);
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
    public int realmGet$receivedBy() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.receivedByIndex);
    }

    @Override
    public void realmSet$receivedBy(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.receivedByIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.receivedByIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$vendorId() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.vendorIdIndex);
    }

    @Override
    public void realmSet$vendorId(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.vendorIdIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.vendorIdIndex, value);
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
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("PurchaseHistory", 16, 0);
        builder.addPersistedProperty("id", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("invoiceId", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("subTotal", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("discount", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("discountType", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("discountCulculation", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("vat", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("total", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("payment", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("due", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("transactionMethod", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("bankAccount", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("mobileBankAccount", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("receivedBy", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("vendorId", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("created", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static PurchaseHistoryColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new PurchaseHistoryColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "PurchaseHistory";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "PurchaseHistory";
    }

    @SuppressWarnings("cast")
    public static com.portal.terminalbd.model.PurchaseHistory createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.portal.terminalbd.model.PurchaseHistory obj = null;
        if (update) {
            Table table = realm.getTable(com.portal.terminalbd.model.PurchaseHistory.class);
            PurchaseHistoryColumnInfo columnInfo = (PurchaseHistoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.PurchaseHistory.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.portal.terminalbd.model.PurchaseHistory.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_portal_terminalbd_model_PurchaseHistoryRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.com_portal_terminalbd_model_PurchaseHistoryRealmProxy) realm.createObjectInternal(com.portal.terminalbd.model.PurchaseHistory.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_portal_terminalbd_model_PurchaseHistoryRealmProxy) realm.createObjectInternal(com.portal.terminalbd.model.PurchaseHistory.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface objProxy = (com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) obj;
        if (json.has("invoiceId")) {
            if (json.isNull("invoiceId")) {
                objProxy.realmSet$invoiceId(null);
            } else {
                objProxy.realmSet$invoiceId((String) json.getString("invoiceId"));
            }
        }
        if (json.has("subTotal")) {
            if (json.isNull("subTotal")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'subTotal' to null.");
            } else {
                objProxy.realmSet$subTotal((int) json.getInt("subTotal"));
            }
        }
        if (json.has("discount")) {
            if (json.isNull("discount")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'discount' to null.");
            } else {
                objProxy.realmSet$discount((int) json.getInt("discount"));
            }
        }
        if (json.has("discountType")) {
            if (json.isNull("discountType")) {
                objProxy.realmSet$discountType(null);
            } else {
                objProxy.realmSet$discountType((String) json.getString("discountType"));
            }
        }
        if (json.has("discountCulculation")) {
            if (json.isNull("discountCulculation")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'discountCulculation' to null.");
            } else {
                objProxy.realmSet$discountCulculation((int) json.getInt("discountCulculation"));
            }
        }
        if (json.has("vat")) {
            if (json.isNull("vat")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'vat' to null.");
            } else {
                objProxy.realmSet$vat((int) json.getInt("vat"));
            }
        }
        if (json.has("total")) {
            if (json.isNull("total")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'total' to null.");
            } else {
                objProxy.realmSet$total((int) json.getInt("total"));
            }
        }
        if (json.has("payment")) {
            if (json.isNull("payment")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'payment' to null.");
            } else {
                objProxy.realmSet$payment((int) json.getInt("payment"));
            }
        }
        if (json.has("due")) {
            if (json.isNull("due")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'due' to null.");
            } else {
                objProxy.realmSet$due((int) json.getInt("due"));
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
        if (json.has("receivedBy")) {
            if (json.isNull("receivedBy")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'receivedBy' to null.");
            } else {
                objProxy.realmSet$receivedBy((int) json.getInt("receivedBy"));
            }
        }
        if (json.has("vendorId")) {
            if (json.isNull("vendorId")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'vendorId' to null.");
            } else {
                objProxy.realmSet$vendorId((int) json.getInt("vendorId"));
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
    public static com.portal.terminalbd.model.PurchaseHistory createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.portal.terminalbd.model.PurchaseHistory obj = new com.portal.terminalbd.model.PurchaseHistory();
        final com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface objProxy = (com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) obj;
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
            } else if (name.equals("subTotal")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$subTotal((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'subTotal' to null.");
                }
            } else if (name.equals("discount")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$discount((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'discount' to null.");
                }
            } else if (name.equals("discountType")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$discountType((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$discountType(null);
                }
            } else if (name.equals("discountCulculation")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$discountCulculation((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'discountCulculation' to null.");
                }
            } else if (name.equals("vat")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$vat((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'vat' to null.");
                }
            } else if (name.equals("total")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$total((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'total' to null.");
                }
            } else if (name.equals("payment")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$payment((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'payment' to null.");
                }
            } else if (name.equals("due")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$due((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'due' to null.");
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
            } else if (name.equals("receivedBy")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$receivedBy((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'receivedBy' to null.");
                }
            } else if (name.equals("vendorId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$vendorId((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'vendorId' to null.");
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

    private static com_portal_terminalbd_model_PurchaseHistoryRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.portal.terminalbd.model.PurchaseHistory.class), false, Collections.<String>emptyList());
        io.realm.com_portal_terminalbd_model_PurchaseHistoryRealmProxy obj = new io.realm.com_portal_terminalbd_model_PurchaseHistoryRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.portal.terminalbd.model.PurchaseHistory copyOrUpdate(Realm realm, PurchaseHistoryColumnInfo columnInfo, com.portal.terminalbd.model.PurchaseHistory object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.portal.terminalbd.model.PurchaseHistory) cachedRealmObject;
        }

        com.portal.terminalbd.model.PurchaseHistory realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.portal.terminalbd.model.PurchaseHistory.class);
            long pkColumnIndex = columnInfo.idIndex;
            String value = ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$id();
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
                    realmObject = new io.realm.com_portal_terminalbd_model_PurchaseHistoryRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.portal.terminalbd.model.PurchaseHistory copy(Realm realm, PurchaseHistoryColumnInfo columnInfo, com.portal.terminalbd.model.PurchaseHistory newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.portal.terminalbd.model.PurchaseHistory) cachedRealmObject;
        }

        com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface realmObjectSource = (com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) newObject;

        Table table = realm.getTable(com.portal.terminalbd.model.PurchaseHistory.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);

        // Add all non-"object reference" fields
        builder.addString(columnInfo.idIndex, realmObjectSource.realmGet$id());
        builder.addString(columnInfo.invoiceIdIndex, realmObjectSource.realmGet$invoiceId());
        builder.addInteger(columnInfo.subTotalIndex, realmObjectSource.realmGet$subTotal());
        builder.addInteger(columnInfo.discountIndex, realmObjectSource.realmGet$discount());
        builder.addString(columnInfo.discountTypeIndex, realmObjectSource.realmGet$discountType());
        builder.addInteger(columnInfo.discountCulculationIndex, realmObjectSource.realmGet$discountCulculation());
        builder.addInteger(columnInfo.vatIndex, realmObjectSource.realmGet$vat());
        builder.addInteger(columnInfo.totalIndex, realmObjectSource.realmGet$total());
        builder.addInteger(columnInfo.paymentIndex, realmObjectSource.realmGet$payment());
        builder.addInteger(columnInfo.dueIndex, realmObjectSource.realmGet$due());
        builder.addString(columnInfo.transactionMethodIndex, realmObjectSource.realmGet$transactionMethod());
        builder.addInteger(columnInfo.bankAccountIndex, realmObjectSource.realmGet$bankAccount());
        builder.addInteger(columnInfo.mobileBankAccountIndex, realmObjectSource.realmGet$mobileBankAccount());
        builder.addInteger(columnInfo.receivedByIndex, realmObjectSource.realmGet$receivedBy());
        builder.addInteger(columnInfo.vendorIdIndex, realmObjectSource.realmGet$vendorId());
        builder.addString(columnInfo.createdIndex, realmObjectSource.realmGet$created());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_portal_terminalbd_model_PurchaseHistoryRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.portal.terminalbd.model.PurchaseHistory object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.PurchaseHistory.class);
        long tableNativePtr = table.getNativePtr();
        PurchaseHistoryColumnInfo columnInfo = (PurchaseHistoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.PurchaseHistory.class);
        long pkColumnIndex = columnInfo.idIndex;
        String primaryKeyValue = ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$id();
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
        String realmGet$invoiceId = ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$invoiceId();
        if (realmGet$invoiceId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.invoiceIdIndex, rowIndex, realmGet$invoiceId, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.subTotalIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$subTotal(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.discountIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$discount(), false);
        String realmGet$discountType = ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$discountType();
        if (realmGet$discountType != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.discountTypeIndex, rowIndex, realmGet$discountType, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.discountCulculationIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$discountCulculation(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.vatIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$vat(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.totalIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$total(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.paymentIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$payment(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.dueIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$due(), false);
        String realmGet$transactionMethod = ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$transactionMethod();
        if (realmGet$transactionMethod != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.transactionMethodIndex, rowIndex, realmGet$transactionMethod, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.bankAccountIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$bankAccount(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.mobileBankAccountIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$mobileBankAccount(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.receivedByIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$receivedBy(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.vendorIdIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$vendorId(), false);
        String realmGet$created = ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$created();
        if (realmGet$created != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.createdIndex, rowIndex, realmGet$created, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.PurchaseHistory.class);
        long tableNativePtr = table.getNativePtr();
        PurchaseHistoryColumnInfo columnInfo = (PurchaseHistoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.PurchaseHistory.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.portal.terminalbd.model.PurchaseHistory object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.PurchaseHistory) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            String primaryKeyValue = ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$id();
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
            String realmGet$invoiceId = ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$invoiceId();
            if (realmGet$invoiceId != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.invoiceIdIndex, rowIndex, realmGet$invoiceId, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.subTotalIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$subTotal(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.discountIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$discount(), false);
            String realmGet$discountType = ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$discountType();
            if (realmGet$discountType != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.discountTypeIndex, rowIndex, realmGet$discountType, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.discountCulculationIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$discountCulculation(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.vatIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$vat(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.totalIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$total(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.paymentIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$payment(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.dueIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$due(), false);
            String realmGet$transactionMethod = ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$transactionMethod();
            if (realmGet$transactionMethod != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.transactionMethodIndex, rowIndex, realmGet$transactionMethod, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.bankAccountIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$bankAccount(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.mobileBankAccountIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$mobileBankAccount(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.receivedByIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$receivedBy(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.vendorIdIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$vendorId(), false);
            String realmGet$created = ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$created();
            if (realmGet$created != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.createdIndex, rowIndex, realmGet$created, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.portal.terminalbd.model.PurchaseHistory object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.PurchaseHistory.class);
        long tableNativePtr = table.getNativePtr();
        PurchaseHistoryColumnInfo columnInfo = (PurchaseHistoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.PurchaseHistory.class);
        long pkColumnIndex = columnInfo.idIndex;
        String primaryKeyValue = ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$id();
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
        String realmGet$invoiceId = ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$invoiceId();
        if (realmGet$invoiceId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.invoiceIdIndex, rowIndex, realmGet$invoiceId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.invoiceIdIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.subTotalIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$subTotal(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.discountIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$discount(), false);
        String realmGet$discountType = ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$discountType();
        if (realmGet$discountType != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.discountTypeIndex, rowIndex, realmGet$discountType, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.discountTypeIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.discountCulculationIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$discountCulculation(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.vatIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$vat(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.totalIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$total(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.paymentIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$payment(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.dueIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$due(), false);
        String realmGet$transactionMethod = ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$transactionMethod();
        if (realmGet$transactionMethod != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.transactionMethodIndex, rowIndex, realmGet$transactionMethod, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.transactionMethodIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.bankAccountIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$bankAccount(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.mobileBankAccountIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$mobileBankAccount(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.receivedByIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$receivedBy(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.vendorIdIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$vendorId(), false);
        String realmGet$created = ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$created();
        if (realmGet$created != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.createdIndex, rowIndex, realmGet$created, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.createdIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.PurchaseHistory.class);
        long tableNativePtr = table.getNativePtr();
        PurchaseHistoryColumnInfo columnInfo = (PurchaseHistoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.PurchaseHistory.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.portal.terminalbd.model.PurchaseHistory object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.PurchaseHistory) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            String primaryKeyValue = ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$id();
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
            String realmGet$invoiceId = ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$invoiceId();
            if (realmGet$invoiceId != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.invoiceIdIndex, rowIndex, realmGet$invoiceId, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.invoiceIdIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.subTotalIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$subTotal(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.discountIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$discount(), false);
            String realmGet$discountType = ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$discountType();
            if (realmGet$discountType != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.discountTypeIndex, rowIndex, realmGet$discountType, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.discountTypeIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.discountCulculationIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$discountCulculation(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.vatIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$vat(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.totalIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$total(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.paymentIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$payment(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.dueIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$due(), false);
            String realmGet$transactionMethod = ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$transactionMethod();
            if (realmGet$transactionMethod != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.transactionMethodIndex, rowIndex, realmGet$transactionMethod, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.transactionMethodIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.bankAccountIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$bankAccount(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.mobileBankAccountIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$mobileBankAccount(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.receivedByIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$receivedBy(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.vendorIdIndex, rowIndex, ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$vendorId(), false);
            String realmGet$created = ((com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) object).realmGet$created();
            if (realmGet$created != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.createdIndex, rowIndex, realmGet$created, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.createdIndex, rowIndex, false);
            }
        }
    }

    public static com.portal.terminalbd.model.PurchaseHistory createDetachedCopy(com.portal.terminalbd.model.PurchaseHistory realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.portal.terminalbd.model.PurchaseHistory unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.portal.terminalbd.model.PurchaseHistory();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.portal.terminalbd.model.PurchaseHistory) cachedObject.object;
            }
            unmanagedObject = (com.portal.terminalbd.model.PurchaseHistory) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface unmanagedCopy = (com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) unmanagedObject;
        com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface realmSource = (com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$invoiceId(realmSource.realmGet$invoiceId());
        unmanagedCopy.realmSet$subTotal(realmSource.realmGet$subTotal());
        unmanagedCopy.realmSet$discount(realmSource.realmGet$discount());
        unmanagedCopy.realmSet$discountType(realmSource.realmGet$discountType());
        unmanagedCopy.realmSet$discountCulculation(realmSource.realmGet$discountCulculation());
        unmanagedCopy.realmSet$vat(realmSource.realmGet$vat());
        unmanagedCopy.realmSet$total(realmSource.realmGet$total());
        unmanagedCopy.realmSet$payment(realmSource.realmGet$payment());
        unmanagedCopy.realmSet$due(realmSource.realmGet$due());
        unmanagedCopy.realmSet$transactionMethod(realmSource.realmGet$transactionMethod());
        unmanagedCopy.realmSet$bankAccount(realmSource.realmGet$bankAccount());
        unmanagedCopy.realmSet$mobileBankAccount(realmSource.realmGet$mobileBankAccount());
        unmanagedCopy.realmSet$receivedBy(realmSource.realmGet$receivedBy());
        unmanagedCopy.realmSet$vendorId(realmSource.realmGet$vendorId());
        unmanagedCopy.realmSet$created(realmSource.realmGet$created());

        return unmanagedObject;
    }

    static com.portal.terminalbd.model.PurchaseHistory update(Realm realm, PurchaseHistoryColumnInfo columnInfo, com.portal.terminalbd.model.PurchaseHistory realmObject, com.portal.terminalbd.model.PurchaseHistory newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface realmObjectTarget = (com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) realmObject;
        com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface realmObjectSource = (com_portal_terminalbd_model_PurchaseHistoryRealmProxyInterface) newObject;
        Table table = realm.getTable(com.portal.terminalbd.model.PurchaseHistory.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);
        builder.addString(columnInfo.idIndex, realmObjectSource.realmGet$id());
        builder.addString(columnInfo.invoiceIdIndex, realmObjectSource.realmGet$invoiceId());
        builder.addInteger(columnInfo.subTotalIndex, realmObjectSource.realmGet$subTotal());
        builder.addInteger(columnInfo.discountIndex, realmObjectSource.realmGet$discount());
        builder.addString(columnInfo.discountTypeIndex, realmObjectSource.realmGet$discountType());
        builder.addInteger(columnInfo.discountCulculationIndex, realmObjectSource.realmGet$discountCulculation());
        builder.addInteger(columnInfo.vatIndex, realmObjectSource.realmGet$vat());
        builder.addInteger(columnInfo.totalIndex, realmObjectSource.realmGet$total());
        builder.addInteger(columnInfo.paymentIndex, realmObjectSource.realmGet$payment());
        builder.addInteger(columnInfo.dueIndex, realmObjectSource.realmGet$due());
        builder.addString(columnInfo.transactionMethodIndex, realmObjectSource.realmGet$transactionMethod());
        builder.addInteger(columnInfo.bankAccountIndex, realmObjectSource.realmGet$bankAccount());
        builder.addInteger(columnInfo.mobileBankAccountIndex, realmObjectSource.realmGet$mobileBankAccount());
        builder.addInteger(columnInfo.receivedByIndex, realmObjectSource.realmGet$receivedBy());
        builder.addInteger(columnInfo.vendorIdIndex, realmObjectSource.realmGet$vendorId());
        builder.addString(columnInfo.createdIndex, realmObjectSource.realmGet$created());

        builder.updateExistingObject();
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("PurchaseHistory = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{invoiceId:");
        stringBuilder.append(realmGet$invoiceId() != null ? realmGet$invoiceId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{subTotal:");
        stringBuilder.append(realmGet$subTotal());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{discount:");
        stringBuilder.append(realmGet$discount());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{discountType:");
        stringBuilder.append(realmGet$discountType() != null ? realmGet$discountType() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{discountCulculation:");
        stringBuilder.append(realmGet$discountCulculation());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{vat:");
        stringBuilder.append(realmGet$vat());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{total:");
        stringBuilder.append(realmGet$total());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{payment:");
        stringBuilder.append(realmGet$payment());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{due:");
        stringBuilder.append(realmGet$due());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionMethod:");
        stringBuilder.append(realmGet$transactionMethod() != null ? realmGet$transactionMethod() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{bankAccount:");
        stringBuilder.append(realmGet$bankAccount());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{mobileBankAccount:");
        stringBuilder.append(realmGet$mobileBankAccount());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{receivedBy:");
        stringBuilder.append(realmGet$receivedBy());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{vendorId:");
        stringBuilder.append(realmGet$vendorId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{created:");
        stringBuilder.append(realmGet$created() != null ? realmGet$created() : "null");
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
        com_portal_terminalbd_model_PurchaseHistoryRealmProxy aPurchaseHistory = (com_portal_terminalbd_model_PurchaseHistoryRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aPurchaseHistory.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aPurchaseHistory.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aPurchaseHistory.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
