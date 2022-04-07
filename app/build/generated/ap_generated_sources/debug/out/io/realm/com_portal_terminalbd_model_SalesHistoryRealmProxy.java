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
public class com_portal_terminalbd_model_SalesHistoryRealmProxy extends com.portal.terminalbd.model.SalesHistory
    implements RealmObjectProxy, com_portal_terminalbd_model_SalesHistoryRealmProxyInterface {

    static final class SalesHistoryColumnInfo extends ColumnInfo {
        long maxColumnIndexValue;
        long idIndex;
        long invoiceIdIndex;
        long subTotalIndex;
        long discountIndex;
        long discountTypeIndex;
        long discountCalculationIndex;
        long vatIndex;
        long totalIndex;
        long receiveIndex;
        long dueIndex;
        long customerIdIndex;
        long customerNameIndex;
        long customerMobileIndex;
        long transactionMethodIndex;
        long bankAccountIndex;
        long mobileBankAccountIndex;
        long paymentMobileIndex;
        long paymentCardIndex;
        long paymentCardNoIndex;
        long transactionIdIndex;
        long salesByIndex;
        long createdIndex;
        long createdByIndex;
        long slipNoIndex;
        long tokenNoIndex;
        long discountCouponIndex;
        long remarkIndex;

        SalesHistoryColumnInfo(OsSchemaInfo schemaInfo) {
            super(27);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("SalesHistory");
            this.idIndex = addColumnDetails("id", "id", objectSchemaInfo);
            this.invoiceIdIndex = addColumnDetails("invoiceId", "invoiceId", objectSchemaInfo);
            this.subTotalIndex = addColumnDetails("subTotal", "subTotal", objectSchemaInfo);
            this.discountIndex = addColumnDetails("discount", "discount", objectSchemaInfo);
            this.discountTypeIndex = addColumnDetails("discountType", "discountType", objectSchemaInfo);
            this.discountCalculationIndex = addColumnDetails("discountCalculation", "discountCalculation", objectSchemaInfo);
            this.vatIndex = addColumnDetails("vat", "vat", objectSchemaInfo);
            this.totalIndex = addColumnDetails("total", "total", objectSchemaInfo);
            this.receiveIndex = addColumnDetails("receive", "receive", objectSchemaInfo);
            this.dueIndex = addColumnDetails("due", "due", objectSchemaInfo);
            this.customerIdIndex = addColumnDetails("customerId", "customerId", objectSchemaInfo);
            this.customerNameIndex = addColumnDetails("customerName", "customerName", objectSchemaInfo);
            this.customerMobileIndex = addColumnDetails("customerMobile", "customerMobile", objectSchemaInfo);
            this.transactionMethodIndex = addColumnDetails("transactionMethod", "transactionMethod", objectSchemaInfo);
            this.bankAccountIndex = addColumnDetails("bankAccount", "bankAccount", objectSchemaInfo);
            this.mobileBankAccountIndex = addColumnDetails("mobileBankAccount", "mobileBankAccount", objectSchemaInfo);
            this.paymentMobileIndex = addColumnDetails("paymentMobile", "paymentMobile", objectSchemaInfo);
            this.paymentCardIndex = addColumnDetails("paymentCard", "paymentCard", objectSchemaInfo);
            this.paymentCardNoIndex = addColumnDetails("paymentCardNo", "paymentCardNo", objectSchemaInfo);
            this.transactionIdIndex = addColumnDetails("transactionId", "transactionId", objectSchemaInfo);
            this.salesByIndex = addColumnDetails("salesBy", "salesBy", objectSchemaInfo);
            this.createdIndex = addColumnDetails("created", "created", objectSchemaInfo);
            this.createdByIndex = addColumnDetails("createdBy", "createdBy", objectSchemaInfo);
            this.slipNoIndex = addColumnDetails("slipNo", "slipNo", objectSchemaInfo);
            this.tokenNoIndex = addColumnDetails("tokenNo", "tokenNo", objectSchemaInfo);
            this.discountCouponIndex = addColumnDetails("discountCoupon", "discountCoupon", objectSchemaInfo);
            this.remarkIndex = addColumnDetails("remark", "remark", objectSchemaInfo);
            this.maxColumnIndexValue = objectSchemaInfo.getMaxColumnIndex();
        }

        SalesHistoryColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new SalesHistoryColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final SalesHistoryColumnInfo src = (SalesHistoryColumnInfo) rawSrc;
            final SalesHistoryColumnInfo dst = (SalesHistoryColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.invoiceIdIndex = src.invoiceIdIndex;
            dst.subTotalIndex = src.subTotalIndex;
            dst.discountIndex = src.discountIndex;
            dst.discountTypeIndex = src.discountTypeIndex;
            dst.discountCalculationIndex = src.discountCalculationIndex;
            dst.vatIndex = src.vatIndex;
            dst.totalIndex = src.totalIndex;
            dst.receiveIndex = src.receiveIndex;
            dst.dueIndex = src.dueIndex;
            dst.customerIdIndex = src.customerIdIndex;
            dst.customerNameIndex = src.customerNameIndex;
            dst.customerMobileIndex = src.customerMobileIndex;
            dst.transactionMethodIndex = src.transactionMethodIndex;
            dst.bankAccountIndex = src.bankAccountIndex;
            dst.mobileBankAccountIndex = src.mobileBankAccountIndex;
            dst.paymentMobileIndex = src.paymentMobileIndex;
            dst.paymentCardIndex = src.paymentCardIndex;
            dst.paymentCardNoIndex = src.paymentCardNoIndex;
            dst.transactionIdIndex = src.transactionIdIndex;
            dst.salesByIndex = src.salesByIndex;
            dst.createdIndex = src.createdIndex;
            dst.createdByIndex = src.createdByIndex;
            dst.slipNoIndex = src.slipNoIndex;
            dst.tokenNoIndex = src.tokenNoIndex;
            dst.discountCouponIndex = src.discountCouponIndex;
            dst.remarkIndex = src.remarkIndex;
            dst.maxColumnIndexValue = src.maxColumnIndexValue;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private SalesHistoryColumnInfo columnInfo;
    private ProxyState<com.portal.terminalbd.model.SalesHistory> proxyState;

    com_portal_terminalbd_model_SalesHistoryRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (SalesHistoryColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.portal.terminalbd.model.SalesHistory>(this);
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
    public int realmGet$discountCalculation() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.discountCalculationIndex);
    }

    @Override
    public void realmSet$discountCalculation(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.discountCalculationIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.discountCalculationIndex, value);
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
    public int realmGet$receive() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.receiveIndex);
    }

    @Override
    public void realmSet$receive(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.receiveIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.receiveIndex, value);
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
    public String realmGet$customerName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.customerNameIndex);
    }

    @Override
    public void realmSet$customerName(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.customerNameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.customerNameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.customerNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.customerNameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$customerMobile() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.customerMobileIndex);
    }

    @Override
    public void realmSet$customerMobile(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.customerMobileIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.customerMobileIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.customerMobileIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.customerMobileIndex, value);
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
    public String realmGet$paymentMobile() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.paymentMobileIndex);
    }

    @Override
    public void realmSet$paymentMobile(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.paymentMobileIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.paymentMobileIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.paymentMobileIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.paymentMobileIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$paymentCard() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.paymentCardIndex);
    }

    @Override
    public void realmSet$paymentCard(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.paymentCardIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.paymentCardIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$paymentCardNo() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.paymentCardNoIndex);
    }

    @Override
    public void realmSet$paymentCardNo(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.paymentCardNoIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.paymentCardNoIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.paymentCardNoIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.paymentCardNoIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$transactionId() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionIdIndex);
    }

    @Override
    public void realmSet$transactionId(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.transactionIdIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.transactionIdIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionIdIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$salesBy() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.salesByIndex);
    }

    @Override
    public void realmSet$salesBy(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.salesByIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.salesByIndex, value);
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

    @Override
    @SuppressWarnings("cast")
    public int realmGet$createdBy() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.createdByIndex);
    }

    @Override
    public void realmSet$createdBy(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.createdByIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.createdByIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$slipNo() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.slipNoIndex);
    }

    @Override
    public void realmSet$slipNo(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.slipNoIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.slipNoIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.slipNoIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.slipNoIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$tokenNo() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.tokenNoIndex);
    }

    @Override
    public void realmSet$tokenNo(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.tokenNoIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.tokenNoIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.tokenNoIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.tokenNoIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$discountCoupon() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.discountCouponIndex);
    }

    @Override
    public void realmSet$discountCoupon(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.discountCouponIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.discountCouponIndex, value);
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

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("SalesHistory", 27, 0);
        builder.addPersistedProperty("id", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("invoiceId", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("subTotal", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("discount", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("discountType", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("discountCalculation", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("vat", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("total", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("receive", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("due", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("customerId", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("customerName", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("customerMobile", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("transactionMethod", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("bankAccount", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("mobileBankAccount", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("paymentMobile", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("paymentCard", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("paymentCardNo", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("transactionId", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("salesBy", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("created", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("createdBy", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("slipNo", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("tokenNo", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("discountCoupon", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("remark", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static SalesHistoryColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new SalesHistoryColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "SalesHistory";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "SalesHistory";
    }

    @SuppressWarnings("cast")
    public static com.portal.terminalbd.model.SalesHistory createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.portal.terminalbd.model.SalesHistory obj = null;
        if (update) {
            Table table = realm.getTable(com.portal.terminalbd.model.SalesHistory.class);
            SalesHistoryColumnInfo columnInfo = (SalesHistoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SalesHistory.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SalesHistory.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_portal_terminalbd_model_SalesHistoryRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.com_portal_terminalbd_model_SalesHistoryRealmProxy) realm.createObjectInternal(com.portal.terminalbd.model.SalesHistory.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_portal_terminalbd_model_SalesHistoryRealmProxy) realm.createObjectInternal(com.portal.terminalbd.model.SalesHistory.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final com_portal_terminalbd_model_SalesHistoryRealmProxyInterface objProxy = (com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) obj;
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
        if (json.has("discountCalculation")) {
            if (json.isNull("discountCalculation")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'discountCalculation' to null.");
            } else {
                objProxy.realmSet$discountCalculation((int) json.getInt("discountCalculation"));
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
        if (json.has("receive")) {
            if (json.isNull("receive")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'receive' to null.");
            } else {
                objProxy.realmSet$receive((int) json.getInt("receive"));
            }
        }
        if (json.has("due")) {
            if (json.isNull("due")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'due' to null.");
            } else {
                objProxy.realmSet$due((int) json.getInt("due"));
            }
        }
        if (json.has("customerId")) {
            if (json.isNull("customerId")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'customerId' to null.");
            } else {
                objProxy.realmSet$customerId((int) json.getInt("customerId"));
            }
        }
        if (json.has("customerName")) {
            if (json.isNull("customerName")) {
                objProxy.realmSet$customerName(null);
            } else {
                objProxy.realmSet$customerName((String) json.getString("customerName"));
            }
        }
        if (json.has("customerMobile")) {
            if (json.isNull("customerMobile")) {
                objProxy.realmSet$customerMobile(null);
            } else {
                objProxy.realmSet$customerMobile((String) json.getString("customerMobile"));
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
        if (json.has("paymentMobile")) {
            if (json.isNull("paymentMobile")) {
                objProxy.realmSet$paymentMobile(null);
            } else {
                objProxy.realmSet$paymentMobile((String) json.getString("paymentMobile"));
            }
        }
        if (json.has("paymentCard")) {
            if (json.isNull("paymentCard")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'paymentCard' to null.");
            } else {
                objProxy.realmSet$paymentCard((int) json.getInt("paymentCard"));
            }
        }
        if (json.has("paymentCardNo")) {
            if (json.isNull("paymentCardNo")) {
                objProxy.realmSet$paymentCardNo(null);
            } else {
                objProxy.realmSet$paymentCardNo((String) json.getString("paymentCardNo"));
            }
        }
        if (json.has("transactionId")) {
            if (json.isNull("transactionId")) {
                objProxy.realmSet$transactionId(null);
            } else {
                objProxy.realmSet$transactionId((String) json.getString("transactionId"));
            }
        }
        if (json.has("salesBy")) {
            if (json.isNull("salesBy")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'salesBy' to null.");
            } else {
                objProxy.realmSet$salesBy((int) json.getInt("salesBy"));
            }
        }
        if (json.has("created")) {
            if (json.isNull("created")) {
                objProxy.realmSet$created(null);
            } else {
                objProxy.realmSet$created((String) json.getString("created"));
            }
        }
        if (json.has("createdBy")) {
            if (json.isNull("createdBy")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'createdBy' to null.");
            } else {
                objProxy.realmSet$createdBy((int) json.getInt("createdBy"));
            }
        }
        if (json.has("slipNo")) {
            if (json.isNull("slipNo")) {
                objProxy.realmSet$slipNo(null);
            } else {
                objProxy.realmSet$slipNo((String) json.getString("slipNo"));
            }
        }
        if (json.has("tokenNo")) {
            if (json.isNull("tokenNo")) {
                objProxy.realmSet$tokenNo(null);
            } else {
                objProxy.realmSet$tokenNo((String) json.getString("tokenNo"));
            }
        }
        if (json.has("discountCoupon")) {
            if (json.isNull("discountCoupon")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'discountCoupon' to null.");
            } else {
                objProxy.realmSet$discountCoupon((int) json.getInt("discountCoupon"));
            }
        }
        if (json.has("remark")) {
            if (json.isNull("remark")) {
                objProxy.realmSet$remark(null);
            } else {
                objProxy.realmSet$remark((String) json.getString("remark"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.portal.terminalbd.model.SalesHistory createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.portal.terminalbd.model.SalesHistory obj = new com.portal.terminalbd.model.SalesHistory();
        final com_portal_terminalbd_model_SalesHistoryRealmProxyInterface objProxy = (com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) obj;
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
            } else if (name.equals("discountCalculation")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$discountCalculation((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'discountCalculation' to null.");
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
            } else if (name.equals("receive")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$receive((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'receive' to null.");
                }
            } else if (name.equals("due")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$due((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'due' to null.");
                }
            } else if (name.equals("customerId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$customerId((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'customerId' to null.");
                }
            } else if (name.equals("customerName")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$customerName((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$customerName(null);
                }
            } else if (name.equals("customerMobile")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$customerMobile((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$customerMobile(null);
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
            } else if (name.equals("paymentMobile")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$paymentMobile((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$paymentMobile(null);
                }
            } else if (name.equals("paymentCard")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$paymentCard((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'paymentCard' to null.");
                }
            } else if (name.equals("paymentCardNo")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$paymentCardNo((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$paymentCardNo(null);
                }
            } else if (name.equals("transactionId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$transactionId((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$transactionId(null);
                }
            } else if (name.equals("salesBy")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$salesBy((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'salesBy' to null.");
                }
            } else if (name.equals("created")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$created((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$created(null);
                }
            } else if (name.equals("createdBy")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$createdBy((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'createdBy' to null.");
                }
            } else if (name.equals("slipNo")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$slipNo((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$slipNo(null);
                }
            } else if (name.equals("tokenNo")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$tokenNo((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$tokenNo(null);
                }
            } else if (name.equals("discountCoupon")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$discountCoupon((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'discountCoupon' to null.");
                }
            } else if (name.equals("remark")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$remark((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$remark(null);
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

    private static com_portal_terminalbd_model_SalesHistoryRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SalesHistory.class), false, Collections.<String>emptyList());
        io.realm.com_portal_terminalbd_model_SalesHistoryRealmProxy obj = new io.realm.com_portal_terminalbd_model_SalesHistoryRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.portal.terminalbd.model.SalesHistory copyOrUpdate(Realm realm, SalesHistoryColumnInfo columnInfo, com.portal.terminalbd.model.SalesHistory object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.portal.terminalbd.model.SalesHistory) cachedRealmObject;
        }

        com.portal.terminalbd.model.SalesHistory realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.portal.terminalbd.model.SalesHistory.class);
            long pkColumnIndex = columnInfo.idIndex;
            String value = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$id();
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
                    realmObject = new io.realm.com_portal_terminalbd_model_SalesHistoryRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.portal.terminalbd.model.SalesHistory copy(Realm realm, SalesHistoryColumnInfo columnInfo, com.portal.terminalbd.model.SalesHistory newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.portal.terminalbd.model.SalesHistory) cachedRealmObject;
        }

        com_portal_terminalbd_model_SalesHistoryRealmProxyInterface realmObjectSource = (com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) newObject;

        Table table = realm.getTable(com.portal.terminalbd.model.SalesHistory.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);

        // Add all non-"object reference" fields
        builder.addString(columnInfo.idIndex, realmObjectSource.realmGet$id());
        builder.addString(columnInfo.invoiceIdIndex, realmObjectSource.realmGet$invoiceId());
        builder.addInteger(columnInfo.subTotalIndex, realmObjectSource.realmGet$subTotal());
        builder.addInteger(columnInfo.discountIndex, realmObjectSource.realmGet$discount());
        builder.addString(columnInfo.discountTypeIndex, realmObjectSource.realmGet$discountType());
        builder.addInteger(columnInfo.discountCalculationIndex, realmObjectSource.realmGet$discountCalculation());
        builder.addInteger(columnInfo.vatIndex, realmObjectSource.realmGet$vat());
        builder.addInteger(columnInfo.totalIndex, realmObjectSource.realmGet$total());
        builder.addInteger(columnInfo.receiveIndex, realmObjectSource.realmGet$receive());
        builder.addInteger(columnInfo.dueIndex, realmObjectSource.realmGet$due());
        builder.addInteger(columnInfo.customerIdIndex, realmObjectSource.realmGet$customerId());
        builder.addString(columnInfo.customerNameIndex, realmObjectSource.realmGet$customerName());
        builder.addString(columnInfo.customerMobileIndex, realmObjectSource.realmGet$customerMobile());
        builder.addString(columnInfo.transactionMethodIndex, realmObjectSource.realmGet$transactionMethod());
        builder.addInteger(columnInfo.bankAccountIndex, realmObjectSource.realmGet$bankAccount());
        builder.addInteger(columnInfo.mobileBankAccountIndex, realmObjectSource.realmGet$mobileBankAccount());
        builder.addString(columnInfo.paymentMobileIndex, realmObjectSource.realmGet$paymentMobile());
        builder.addInteger(columnInfo.paymentCardIndex, realmObjectSource.realmGet$paymentCard());
        builder.addString(columnInfo.paymentCardNoIndex, realmObjectSource.realmGet$paymentCardNo());
        builder.addString(columnInfo.transactionIdIndex, realmObjectSource.realmGet$transactionId());
        builder.addInteger(columnInfo.salesByIndex, realmObjectSource.realmGet$salesBy());
        builder.addString(columnInfo.createdIndex, realmObjectSource.realmGet$created());
        builder.addInteger(columnInfo.createdByIndex, realmObjectSource.realmGet$createdBy());
        builder.addString(columnInfo.slipNoIndex, realmObjectSource.realmGet$slipNo());
        builder.addString(columnInfo.tokenNoIndex, realmObjectSource.realmGet$tokenNo());
        builder.addInteger(columnInfo.discountCouponIndex, realmObjectSource.realmGet$discountCoupon());
        builder.addString(columnInfo.remarkIndex, realmObjectSource.realmGet$remark());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_portal_terminalbd_model_SalesHistoryRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.portal.terminalbd.model.SalesHistory object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.SalesHistory.class);
        long tableNativePtr = table.getNativePtr();
        SalesHistoryColumnInfo columnInfo = (SalesHistoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SalesHistory.class);
        long pkColumnIndex = columnInfo.idIndex;
        String primaryKeyValue = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$id();
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
        String realmGet$invoiceId = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$invoiceId();
        if (realmGet$invoiceId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.invoiceIdIndex, rowIndex, realmGet$invoiceId, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.subTotalIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$subTotal(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.discountIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$discount(), false);
        String realmGet$discountType = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$discountType();
        if (realmGet$discountType != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.discountTypeIndex, rowIndex, realmGet$discountType, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.discountCalculationIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$discountCalculation(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.vatIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$vat(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.totalIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$total(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.receiveIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$receive(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.dueIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$due(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.customerIdIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$customerId(), false);
        String realmGet$customerName = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$customerName();
        if (realmGet$customerName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.customerNameIndex, rowIndex, realmGet$customerName, false);
        }
        String realmGet$customerMobile = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$customerMobile();
        if (realmGet$customerMobile != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.customerMobileIndex, rowIndex, realmGet$customerMobile, false);
        }
        String realmGet$transactionMethod = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$transactionMethod();
        if (realmGet$transactionMethod != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.transactionMethodIndex, rowIndex, realmGet$transactionMethod, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.bankAccountIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$bankAccount(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.mobileBankAccountIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$mobileBankAccount(), false);
        String realmGet$paymentMobile = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$paymentMobile();
        if (realmGet$paymentMobile != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.paymentMobileIndex, rowIndex, realmGet$paymentMobile, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.paymentCardIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$paymentCard(), false);
        String realmGet$paymentCardNo = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$paymentCardNo();
        if (realmGet$paymentCardNo != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.paymentCardNoIndex, rowIndex, realmGet$paymentCardNo, false);
        }
        String realmGet$transactionId = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$transactionId();
        if (realmGet$transactionId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.transactionIdIndex, rowIndex, realmGet$transactionId, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.salesByIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$salesBy(), false);
        String realmGet$created = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$created();
        if (realmGet$created != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.createdIndex, rowIndex, realmGet$created, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.createdByIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$createdBy(), false);
        String realmGet$slipNo = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$slipNo();
        if (realmGet$slipNo != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.slipNoIndex, rowIndex, realmGet$slipNo, false);
        }
        String realmGet$tokenNo = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$tokenNo();
        if (realmGet$tokenNo != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tokenNoIndex, rowIndex, realmGet$tokenNo, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.discountCouponIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$discountCoupon(), false);
        String realmGet$remark = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$remark();
        if (realmGet$remark != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.remarkIndex, rowIndex, realmGet$remark, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.SalesHistory.class);
        long tableNativePtr = table.getNativePtr();
        SalesHistoryColumnInfo columnInfo = (SalesHistoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SalesHistory.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.portal.terminalbd.model.SalesHistory object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.SalesHistory) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            String primaryKeyValue = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$id();
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
            String realmGet$invoiceId = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$invoiceId();
            if (realmGet$invoiceId != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.invoiceIdIndex, rowIndex, realmGet$invoiceId, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.subTotalIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$subTotal(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.discountIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$discount(), false);
            String realmGet$discountType = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$discountType();
            if (realmGet$discountType != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.discountTypeIndex, rowIndex, realmGet$discountType, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.discountCalculationIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$discountCalculation(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.vatIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$vat(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.totalIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$total(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.receiveIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$receive(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.dueIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$due(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.customerIdIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$customerId(), false);
            String realmGet$customerName = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$customerName();
            if (realmGet$customerName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.customerNameIndex, rowIndex, realmGet$customerName, false);
            }
            String realmGet$customerMobile = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$customerMobile();
            if (realmGet$customerMobile != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.customerMobileIndex, rowIndex, realmGet$customerMobile, false);
            }
            String realmGet$transactionMethod = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$transactionMethod();
            if (realmGet$transactionMethod != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.transactionMethodIndex, rowIndex, realmGet$transactionMethod, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.bankAccountIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$bankAccount(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.mobileBankAccountIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$mobileBankAccount(), false);
            String realmGet$paymentMobile = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$paymentMobile();
            if (realmGet$paymentMobile != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.paymentMobileIndex, rowIndex, realmGet$paymentMobile, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.paymentCardIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$paymentCard(), false);
            String realmGet$paymentCardNo = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$paymentCardNo();
            if (realmGet$paymentCardNo != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.paymentCardNoIndex, rowIndex, realmGet$paymentCardNo, false);
            }
            String realmGet$transactionId = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$transactionId();
            if (realmGet$transactionId != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.transactionIdIndex, rowIndex, realmGet$transactionId, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.salesByIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$salesBy(), false);
            String realmGet$created = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$created();
            if (realmGet$created != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.createdIndex, rowIndex, realmGet$created, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.createdByIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$createdBy(), false);
            String realmGet$slipNo = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$slipNo();
            if (realmGet$slipNo != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.slipNoIndex, rowIndex, realmGet$slipNo, false);
            }
            String realmGet$tokenNo = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$tokenNo();
            if (realmGet$tokenNo != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.tokenNoIndex, rowIndex, realmGet$tokenNo, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.discountCouponIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$discountCoupon(), false);
            String realmGet$remark = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$remark();
            if (realmGet$remark != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.remarkIndex, rowIndex, realmGet$remark, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.portal.terminalbd.model.SalesHistory object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.SalesHistory.class);
        long tableNativePtr = table.getNativePtr();
        SalesHistoryColumnInfo columnInfo = (SalesHistoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SalesHistory.class);
        long pkColumnIndex = columnInfo.idIndex;
        String primaryKeyValue = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$id();
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
        String realmGet$invoiceId = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$invoiceId();
        if (realmGet$invoiceId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.invoiceIdIndex, rowIndex, realmGet$invoiceId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.invoiceIdIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.subTotalIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$subTotal(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.discountIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$discount(), false);
        String realmGet$discountType = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$discountType();
        if (realmGet$discountType != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.discountTypeIndex, rowIndex, realmGet$discountType, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.discountTypeIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.discountCalculationIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$discountCalculation(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.vatIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$vat(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.totalIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$total(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.receiveIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$receive(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.dueIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$due(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.customerIdIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$customerId(), false);
        String realmGet$customerName = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$customerName();
        if (realmGet$customerName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.customerNameIndex, rowIndex, realmGet$customerName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.customerNameIndex, rowIndex, false);
        }
        String realmGet$customerMobile = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$customerMobile();
        if (realmGet$customerMobile != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.customerMobileIndex, rowIndex, realmGet$customerMobile, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.customerMobileIndex, rowIndex, false);
        }
        String realmGet$transactionMethod = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$transactionMethod();
        if (realmGet$transactionMethod != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.transactionMethodIndex, rowIndex, realmGet$transactionMethod, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.transactionMethodIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.bankAccountIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$bankAccount(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.mobileBankAccountIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$mobileBankAccount(), false);
        String realmGet$paymentMobile = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$paymentMobile();
        if (realmGet$paymentMobile != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.paymentMobileIndex, rowIndex, realmGet$paymentMobile, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.paymentMobileIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.paymentCardIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$paymentCard(), false);
        String realmGet$paymentCardNo = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$paymentCardNo();
        if (realmGet$paymentCardNo != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.paymentCardNoIndex, rowIndex, realmGet$paymentCardNo, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.paymentCardNoIndex, rowIndex, false);
        }
        String realmGet$transactionId = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$transactionId();
        if (realmGet$transactionId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.transactionIdIndex, rowIndex, realmGet$transactionId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.transactionIdIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.salesByIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$salesBy(), false);
        String realmGet$created = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$created();
        if (realmGet$created != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.createdIndex, rowIndex, realmGet$created, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.createdIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.createdByIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$createdBy(), false);
        String realmGet$slipNo = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$slipNo();
        if (realmGet$slipNo != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.slipNoIndex, rowIndex, realmGet$slipNo, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.slipNoIndex, rowIndex, false);
        }
        String realmGet$tokenNo = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$tokenNo();
        if (realmGet$tokenNo != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tokenNoIndex, rowIndex, realmGet$tokenNo, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.tokenNoIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.discountCouponIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$discountCoupon(), false);
        String realmGet$remark = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$remark();
        if (realmGet$remark != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.remarkIndex, rowIndex, realmGet$remark, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.remarkIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.SalesHistory.class);
        long tableNativePtr = table.getNativePtr();
        SalesHistoryColumnInfo columnInfo = (SalesHistoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.SalesHistory.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.portal.terminalbd.model.SalesHistory object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.SalesHistory) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            String primaryKeyValue = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$id();
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
            String realmGet$invoiceId = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$invoiceId();
            if (realmGet$invoiceId != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.invoiceIdIndex, rowIndex, realmGet$invoiceId, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.invoiceIdIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.subTotalIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$subTotal(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.discountIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$discount(), false);
            String realmGet$discountType = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$discountType();
            if (realmGet$discountType != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.discountTypeIndex, rowIndex, realmGet$discountType, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.discountTypeIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.discountCalculationIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$discountCalculation(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.vatIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$vat(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.totalIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$total(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.receiveIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$receive(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.dueIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$due(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.customerIdIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$customerId(), false);
            String realmGet$customerName = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$customerName();
            if (realmGet$customerName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.customerNameIndex, rowIndex, realmGet$customerName, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.customerNameIndex, rowIndex, false);
            }
            String realmGet$customerMobile = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$customerMobile();
            if (realmGet$customerMobile != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.customerMobileIndex, rowIndex, realmGet$customerMobile, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.customerMobileIndex, rowIndex, false);
            }
            String realmGet$transactionMethod = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$transactionMethod();
            if (realmGet$transactionMethod != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.transactionMethodIndex, rowIndex, realmGet$transactionMethod, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.transactionMethodIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.bankAccountIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$bankAccount(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.mobileBankAccountIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$mobileBankAccount(), false);
            String realmGet$paymentMobile = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$paymentMobile();
            if (realmGet$paymentMobile != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.paymentMobileIndex, rowIndex, realmGet$paymentMobile, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.paymentMobileIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.paymentCardIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$paymentCard(), false);
            String realmGet$paymentCardNo = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$paymentCardNo();
            if (realmGet$paymentCardNo != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.paymentCardNoIndex, rowIndex, realmGet$paymentCardNo, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.paymentCardNoIndex, rowIndex, false);
            }
            String realmGet$transactionId = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$transactionId();
            if (realmGet$transactionId != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.transactionIdIndex, rowIndex, realmGet$transactionId, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.transactionIdIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.salesByIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$salesBy(), false);
            String realmGet$created = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$created();
            if (realmGet$created != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.createdIndex, rowIndex, realmGet$created, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.createdIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.createdByIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$createdBy(), false);
            String realmGet$slipNo = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$slipNo();
            if (realmGet$slipNo != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.slipNoIndex, rowIndex, realmGet$slipNo, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.slipNoIndex, rowIndex, false);
            }
            String realmGet$tokenNo = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$tokenNo();
            if (realmGet$tokenNo != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.tokenNoIndex, rowIndex, realmGet$tokenNo, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.tokenNoIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.discountCouponIndex, rowIndex, ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$discountCoupon(), false);
            String realmGet$remark = ((com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) object).realmGet$remark();
            if (realmGet$remark != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.remarkIndex, rowIndex, realmGet$remark, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.remarkIndex, rowIndex, false);
            }
        }
    }

    public static com.portal.terminalbd.model.SalesHistory createDetachedCopy(com.portal.terminalbd.model.SalesHistory realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.portal.terminalbd.model.SalesHistory unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.portal.terminalbd.model.SalesHistory();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.portal.terminalbd.model.SalesHistory) cachedObject.object;
            }
            unmanagedObject = (com.portal.terminalbd.model.SalesHistory) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_portal_terminalbd_model_SalesHistoryRealmProxyInterface unmanagedCopy = (com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) unmanagedObject;
        com_portal_terminalbd_model_SalesHistoryRealmProxyInterface realmSource = (com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$invoiceId(realmSource.realmGet$invoiceId());
        unmanagedCopy.realmSet$subTotal(realmSource.realmGet$subTotal());
        unmanagedCopy.realmSet$discount(realmSource.realmGet$discount());
        unmanagedCopy.realmSet$discountType(realmSource.realmGet$discountType());
        unmanagedCopy.realmSet$discountCalculation(realmSource.realmGet$discountCalculation());
        unmanagedCopy.realmSet$vat(realmSource.realmGet$vat());
        unmanagedCopy.realmSet$total(realmSource.realmGet$total());
        unmanagedCopy.realmSet$receive(realmSource.realmGet$receive());
        unmanagedCopy.realmSet$due(realmSource.realmGet$due());
        unmanagedCopy.realmSet$customerId(realmSource.realmGet$customerId());
        unmanagedCopy.realmSet$customerName(realmSource.realmGet$customerName());
        unmanagedCopy.realmSet$customerMobile(realmSource.realmGet$customerMobile());
        unmanagedCopy.realmSet$transactionMethod(realmSource.realmGet$transactionMethod());
        unmanagedCopy.realmSet$bankAccount(realmSource.realmGet$bankAccount());
        unmanagedCopy.realmSet$mobileBankAccount(realmSource.realmGet$mobileBankAccount());
        unmanagedCopy.realmSet$paymentMobile(realmSource.realmGet$paymentMobile());
        unmanagedCopy.realmSet$paymentCard(realmSource.realmGet$paymentCard());
        unmanagedCopy.realmSet$paymentCardNo(realmSource.realmGet$paymentCardNo());
        unmanagedCopy.realmSet$transactionId(realmSource.realmGet$transactionId());
        unmanagedCopy.realmSet$salesBy(realmSource.realmGet$salesBy());
        unmanagedCopy.realmSet$created(realmSource.realmGet$created());
        unmanagedCopy.realmSet$createdBy(realmSource.realmGet$createdBy());
        unmanagedCopy.realmSet$slipNo(realmSource.realmGet$slipNo());
        unmanagedCopy.realmSet$tokenNo(realmSource.realmGet$tokenNo());
        unmanagedCopy.realmSet$discountCoupon(realmSource.realmGet$discountCoupon());
        unmanagedCopy.realmSet$remark(realmSource.realmGet$remark());

        return unmanagedObject;
    }

    static com.portal.terminalbd.model.SalesHistory update(Realm realm, SalesHistoryColumnInfo columnInfo, com.portal.terminalbd.model.SalesHistory realmObject, com.portal.terminalbd.model.SalesHistory newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        com_portal_terminalbd_model_SalesHistoryRealmProxyInterface realmObjectTarget = (com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) realmObject;
        com_portal_terminalbd_model_SalesHistoryRealmProxyInterface realmObjectSource = (com_portal_terminalbd_model_SalesHistoryRealmProxyInterface) newObject;
        Table table = realm.getTable(com.portal.terminalbd.model.SalesHistory.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);
        builder.addString(columnInfo.idIndex, realmObjectSource.realmGet$id());
        builder.addString(columnInfo.invoiceIdIndex, realmObjectSource.realmGet$invoiceId());
        builder.addInteger(columnInfo.subTotalIndex, realmObjectSource.realmGet$subTotal());
        builder.addInteger(columnInfo.discountIndex, realmObjectSource.realmGet$discount());
        builder.addString(columnInfo.discountTypeIndex, realmObjectSource.realmGet$discountType());
        builder.addInteger(columnInfo.discountCalculationIndex, realmObjectSource.realmGet$discountCalculation());
        builder.addInteger(columnInfo.vatIndex, realmObjectSource.realmGet$vat());
        builder.addInteger(columnInfo.totalIndex, realmObjectSource.realmGet$total());
        builder.addInteger(columnInfo.receiveIndex, realmObjectSource.realmGet$receive());
        builder.addInteger(columnInfo.dueIndex, realmObjectSource.realmGet$due());
        builder.addInteger(columnInfo.customerIdIndex, realmObjectSource.realmGet$customerId());
        builder.addString(columnInfo.customerNameIndex, realmObjectSource.realmGet$customerName());
        builder.addString(columnInfo.customerMobileIndex, realmObjectSource.realmGet$customerMobile());
        builder.addString(columnInfo.transactionMethodIndex, realmObjectSource.realmGet$transactionMethod());
        builder.addInteger(columnInfo.bankAccountIndex, realmObjectSource.realmGet$bankAccount());
        builder.addInteger(columnInfo.mobileBankAccountIndex, realmObjectSource.realmGet$mobileBankAccount());
        builder.addString(columnInfo.paymentMobileIndex, realmObjectSource.realmGet$paymentMobile());
        builder.addInteger(columnInfo.paymentCardIndex, realmObjectSource.realmGet$paymentCard());
        builder.addString(columnInfo.paymentCardNoIndex, realmObjectSource.realmGet$paymentCardNo());
        builder.addString(columnInfo.transactionIdIndex, realmObjectSource.realmGet$transactionId());
        builder.addInteger(columnInfo.salesByIndex, realmObjectSource.realmGet$salesBy());
        builder.addString(columnInfo.createdIndex, realmObjectSource.realmGet$created());
        builder.addInteger(columnInfo.createdByIndex, realmObjectSource.realmGet$createdBy());
        builder.addString(columnInfo.slipNoIndex, realmObjectSource.realmGet$slipNo());
        builder.addString(columnInfo.tokenNoIndex, realmObjectSource.realmGet$tokenNo());
        builder.addInteger(columnInfo.discountCouponIndex, realmObjectSource.realmGet$discountCoupon());
        builder.addString(columnInfo.remarkIndex, realmObjectSource.realmGet$remark());

        builder.updateExistingObject();
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("SalesHistory = proxy[");
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
        stringBuilder.append("{discountCalculation:");
        stringBuilder.append(realmGet$discountCalculation());
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
        stringBuilder.append("{receive:");
        stringBuilder.append(realmGet$receive());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{due:");
        stringBuilder.append(realmGet$due());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{customerId:");
        stringBuilder.append(realmGet$customerId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{customerName:");
        stringBuilder.append(realmGet$customerName() != null ? realmGet$customerName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{customerMobile:");
        stringBuilder.append(realmGet$customerMobile() != null ? realmGet$customerMobile() : "null");
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
        stringBuilder.append("{paymentMobile:");
        stringBuilder.append(realmGet$paymentMobile() != null ? realmGet$paymentMobile() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{paymentCard:");
        stringBuilder.append(realmGet$paymentCard());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{paymentCardNo:");
        stringBuilder.append(realmGet$paymentCardNo() != null ? realmGet$paymentCardNo() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionId:");
        stringBuilder.append(realmGet$transactionId() != null ? realmGet$transactionId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{salesBy:");
        stringBuilder.append(realmGet$salesBy());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{created:");
        stringBuilder.append(realmGet$created() != null ? realmGet$created() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{createdBy:");
        stringBuilder.append(realmGet$createdBy());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{slipNo:");
        stringBuilder.append(realmGet$slipNo() != null ? realmGet$slipNo() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tokenNo:");
        stringBuilder.append(realmGet$tokenNo() != null ? realmGet$tokenNo() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{discountCoupon:");
        stringBuilder.append(realmGet$discountCoupon());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{remark:");
        stringBuilder.append(realmGet$remark() != null ? realmGet$remark() : "null");
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
        com_portal_terminalbd_model_SalesHistoryRealmProxy aSalesHistory = (com_portal_terminalbd_model_SalesHistoryRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aSalesHistory.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aSalesHistory.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aSalesHistory.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
