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
public class com_portal_terminalbd_model_StockItemRealmProxy extends com.portal.terminalbd.model.StockItem
    implements RealmObjectProxy, com_portal_terminalbd_model_StockItemRealmProxyInterface {

    static final class StockItemColumnInfo extends ColumnInfo {
        long maxColumnIndexValue;
        long countIndex;
        long globalIdIndex;
        long itemIdIndex;
        long categoryIdIndex;
        long categoryNameIndex;
        long unitIdIndex;
        long unitIndex;
        long nameIndex;
        long imagePathIndex;
        long printNameIndex;
        long quantityIndex;
        long salesPriceIndex;
        long purchasePriceIndex;
        long printHiddenIndex;
        long imageIndex;

        StockItemColumnInfo(OsSchemaInfo schemaInfo) {
            super(15);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("StockItem");
            this.countIndex = addColumnDetails("count", "count", objectSchemaInfo);
            this.globalIdIndex = addColumnDetails("globalId", "globalId", objectSchemaInfo);
            this.itemIdIndex = addColumnDetails("itemId", "itemId", objectSchemaInfo);
            this.categoryIdIndex = addColumnDetails("categoryId", "categoryId", objectSchemaInfo);
            this.categoryNameIndex = addColumnDetails("categoryName", "categoryName", objectSchemaInfo);
            this.unitIdIndex = addColumnDetails("unitId", "unitId", objectSchemaInfo);
            this.unitIndex = addColumnDetails("unit", "unit", objectSchemaInfo);
            this.nameIndex = addColumnDetails("name", "name", objectSchemaInfo);
            this.imagePathIndex = addColumnDetails("imagePath", "imagePath", objectSchemaInfo);
            this.printNameIndex = addColumnDetails("printName", "printName", objectSchemaInfo);
            this.quantityIndex = addColumnDetails("quantity", "quantity", objectSchemaInfo);
            this.salesPriceIndex = addColumnDetails("salesPrice", "salesPrice", objectSchemaInfo);
            this.purchasePriceIndex = addColumnDetails("purchasePrice", "purchasePrice", objectSchemaInfo);
            this.printHiddenIndex = addColumnDetails("printHidden", "printHidden", objectSchemaInfo);
            this.imageIndex = addColumnDetails("image", "image", objectSchemaInfo);
            this.maxColumnIndexValue = objectSchemaInfo.getMaxColumnIndex();
        }

        StockItemColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new StockItemColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final StockItemColumnInfo src = (StockItemColumnInfo) rawSrc;
            final StockItemColumnInfo dst = (StockItemColumnInfo) rawDst;
            dst.countIndex = src.countIndex;
            dst.globalIdIndex = src.globalIdIndex;
            dst.itemIdIndex = src.itemIdIndex;
            dst.categoryIdIndex = src.categoryIdIndex;
            dst.categoryNameIndex = src.categoryNameIndex;
            dst.unitIdIndex = src.unitIdIndex;
            dst.unitIndex = src.unitIndex;
            dst.nameIndex = src.nameIndex;
            dst.imagePathIndex = src.imagePathIndex;
            dst.printNameIndex = src.printNameIndex;
            dst.quantityIndex = src.quantityIndex;
            dst.salesPriceIndex = src.salesPriceIndex;
            dst.purchasePriceIndex = src.purchasePriceIndex;
            dst.printHiddenIndex = src.printHiddenIndex;
            dst.imageIndex = src.imageIndex;
            dst.maxColumnIndexValue = src.maxColumnIndexValue;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private StockItemColumnInfo columnInfo;
    private ProxyState<com.portal.terminalbd.model.StockItem> proxyState;

    com_portal_terminalbd_model_StockItemRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (StockItemColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.portal.terminalbd.model.StockItem>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$count() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.countIndex);
    }

    @Override
    public void realmSet$count(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.countIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.countIndex, value);
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
    public int realmGet$itemId() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.itemIdIndex);
    }

    @Override
    public void realmSet$itemId(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.itemIdIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.itemIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$categoryId() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.categoryIdIndex);
    }

    @Override
    public void realmSet$categoryId(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.categoryIdIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.categoryIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$categoryName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.categoryNameIndex);
    }

    @Override
    public void realmSet$categoryName(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.categoryNameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.categoryNameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.categoryNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.categoryNameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$unitId() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.unitIdIndex);
    }

    @Override
    public void realmSet$unitId(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.unitIdIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.unitIdIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.unitIdIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.unitIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$unit() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.unitIndex);
    }

    @Override
    public void realmSet$unit(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.unitIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.unitIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.unitIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.unitIndex, value);
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
    public String realmGet$imagePath() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.imagePathIndex);
    }

    @Override
    public void realmSet$imagePath(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.imagePathIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.imagePathIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.imagePathIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.imagePathIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$printName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.printNameIndex);
    }

    @Override
    public void realmSet$printName(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.printNameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.printNameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.printNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.printNameIndex, value);
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
    public String realmGet$printHidden() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.printHiddenIndex);
    }

    @Override
    public void realmSet$printHidden(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.printHiddenIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.printHiddenIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.printHiddenIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.printHiddenIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public byte[] realmGet$image() {
        proxyState.getRealm$realm().checkIfValid();
        return (byte[]) proxyState.getRow$realm().getBinaryByteArray(columnInfo.imageIndex);
    }

    @Override
    public void realmSet$image(byte[] value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.imageIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setBinaryByteArray(columnInfo.imageIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.imageIndex);
            return;
        }
        proxyState.getRow$realm().setBinaryByteArray(columnInfo.imageIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("StockItem", 15, 0);
        builder.addPersistedProperty("count", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("globalId", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("itemId", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("categoryId", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("categoryName", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("unitId", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("unit", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("imagePath", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("printName", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("quantity", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("salesPrice", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("purchasePrice", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("printHidden", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("image", RealmFieldType.BINARY, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static StockItemColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new StockItemColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "StockItem";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "StockItem";
    }

    @SuppressWarnings("cast")
    public static com.portal.terminalbd.model.StockItem createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.portal.terminalbd.model.StockItem obj = realm.createObjectInternal(com.portal.terminalbd.model.StockItem.class, true, excludeFields);

        final com_portal_terminalbd_model_StockItemRealmProxyInterface objProxy = (com_portal_terminalbd_model_StockItemRealmProxyInterface) obj;
        if (json.has("count")) {
            if (json.isNull("count")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'count' to null.");
            } else {
                objProxy.realmSet$count((int) json.getInt("count"));
            }
        }
        if (json.has("globalId")) {
            if (json.isNull("globalId")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'globalId' to null.");
            } else {
                objProxy.realmSet$globalId((int) json.getInt("globalId"));
            }
        }
        if (json.has("itemId")) {
            if (json.isNull("itemId")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'itemId' to null.");
            } else {
                objProxy.realmSet$itemId((int) json.getInt("itemId"));
            }
        }
        if (json.has("categoryId")) {
            if (json.isNull("categoryId")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'categoryId' to null.");
            } else {
                objProxy.realmSet$categoryId((int) json.getInt("categoryId"));
            }
        }
        if (json.has("categoryName")) {
            if (json.isNull("categoryName")) {
                objProxy.realmSet$categoryName(null);
            } else {
                objProxy.realmSet$categoryName((String) json.getString("categoryName"));
            }
        }
        if (json.has("unitId")) {
            if (json.isNull("unitId")) {
                objProxy.realmSet$unitId(null);
            } else {
                objProxy.realmSet$unitId((String) json.getString("unitId"));
            }
        }
        if (json.has("unit")) {
            if (json.isNull("unit")) {
                objProxy.realmSet$unit(null);
            } else {
                objProxy.realmSet$unit((String) json.getString("unit"));
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("imagePath")) {
            if (json.isNull("imagePath")) {
                objProxy.realmSet$imagePath(null);
            } else {
                objProxy.realmSet$imagePath((String) json.getString("imagePath"));
            }
        }
        if (json.has("printName")) {
            if (json.isNull("printName")) {
                objProxy.realmSet$printName(null);
            } else {
                objProxy.realmSet$printName((String) json.getString("printName"));
            }
        }
        if (json.has("quantity")) {
            if (json.isNull("quantity")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'quantity' to null.");
            } else {
                objProxy.realmSet$quantity((int) json.getInt("quantity"));
            }
        }
        if (json.has("salesPrice")) {
            if (json.isNull("salesPrice")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'salesPrice' to null.");
            } else {
                objProxy.realmSet$salesPrice((double) json.getDouble("salesPrice"));
            }
        }
        if (json.has("purchasePrice")) {
            if (json.isNull("purchasePrice")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'purchasePrice' to null.");
            } else {
                objProxy.realmSet$purchasePrice((double) json.getDouble("purchasePrice"));
            }
        }
        if (json.has("printHidden")) {
            if (json.isNull("printHidden")) {
                objProxy.realmSet$printHidden(null);
            } else {
                objProxy.realmSet$printHidden((String) json.getString("printHidden"));
            }
        }
        if (json.has("image")) {
            if (json.isNull("image")) {
                objProxy.realmSet$image(null);
            } else {
                objProxy.realmSet$image(JsonUtils.stringToBytes(json.getString("image")));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.portal.terminalbd.model.StockItem createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.portal.terminalbd.model.StockItem obj = new com.portal.terminalbd.model.StockItem();
        final com_portal_terminalbd_model_StockItemRealmProxyInterface objProxy = (com_portal_terminalbd_model_StockItemRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("count")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$count((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'count' to null.");
                }
            } else if (name.equals("globalId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$globalId((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'globalId' to null.");
                }
            } else if (name.equals("itemId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$itemId((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'itemId' to null.");
                }
            } else if (name.equals("categoryId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$categoryId((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'categoryId' to null.");
                }
            } else if (name.equals("categoryName")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$categoryName((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$categoryName(null);
                }
            } else if (name.equals("unitId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$unitId((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$unitId(null);
                }
            } else if (name.equals("unit")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$unit((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$unit(null);
                }
            } else if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (name.equals("imagePath")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$imagePath((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$imagePath(null);
                }
            } else if (name.equals("printName")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$printName((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$printName(null);
                }
            } else if (name.equals("quantity")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$quantity((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'quantity' to null.");
                }
            } else if (name.equals("salesPrice")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$salesPrice((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'salesPrice' to null.");
                }
            } else if (name.equals("purchasePrice")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$purchasePrice((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'purchasePrice' to null.");
                }
            } else if (name.equals("printHidden")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$printHidden((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$printHidden(null);
                }
            } else if (name.equals("image")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$image(JsonUtils.stringToBytes(reader.nextString()));
                } else {
                    reader.skipValue();
                    objProxy.realmSet$image(null);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    private static com_portal_terminalbd_model_StockItemRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.portal.terminalbd.model.StockItem.class), false, Collections.<String>emptyList());
        io.realm.com_portal_terminalbd_model_StockItemRealmProxy obj = new io.realm.com_portal_terminalbd_model_StockItemRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.portal.terminalbd.model.StockItem copyOrUpdate(Realm realm, StockItemColumnInfo columnInfo, com.portal.terminalbd.model.StockItem object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.portal.terminalbd.model.StockItem) cachedRealmObject;
        }

        return copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.portal.terminalbd.model.StockItem copy(Realm realm, StockItemColumnInfo columnInfo, com.portal.terminalbd.model.StockItem newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.portal.terminalbd.model.StockItem) cachedRealmObject;
        }

        com_portal_terminalbd_model_StockItemRealmProxyInterface realmObjectSource = (com_portal_terminalbd_model_StockItemRealmProxyInterface) newObject;

        Table table = realm.getTable(com.portal.terminalbd.model.StockItem.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);

        // Add all non-"object reference" fields
        builder.addInteger(columnInfo.countIndex, realmObjectSource.realmGet$count());
        builder.addInteger(columnInfo.globalIdIndex, realmObjectSource.realmGet$globalId());
        builder.addInteger(columnInfo.itemIdIndex, realmObjectSource.realmGet$itemId());
        builder.addInteger(columnInfo.categoryIdIndex, realmObjectSource.realmGet$categoryId());
        builder.addString(columnInfo.categoryNameIndex, realmObjectSource.realmGet$categoryName());
        builder.addString(columnInfo.unitIdIndex, realmObjectSource.realmGet$unitId());
        builder.addString(columnInfo.unitIndex, realmObjectSource.realmGet$unit());
        builder.addString(columnInfo.nameIndex, realmObjectSource.realmGet$name());
        builder.addString(columnInfo.imagePathIndex, realmObjectSource.realmGet$imagePath());
        builder.addString(columnInfo.printNameIndex, realmObjectSource.realmGet$printName());
        builder.addInteger(columnInfo.quantityIndex, realmObjectSource.realmGet$quantity());
        builder.addDouble(columnInfo.salesPriceIndex, realmObjectSource.realmGet$salesPrice());
        builder.addDouble(columnInfo.purchasePriceIndex, realmObjectSource.realmGet$purchasePrice());
        builder.addString(columnInfo.printHiddenIndex, realmObjectSource.realmGet$printHidden());
        builder.addByteArray(columnInfo.imageIndex, realmObjectSource.realmGet$image());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_portal_terminalbd_model_StockItemRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.portal.terminalbd.model.StockItem object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.StockItem.class);
        long tableNativePtr = table.getNativePtr();
        StockItemColumnInfo columnInfo = (StockItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.StockItem.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.countIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$count(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.globalIdIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$globalId(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.itemIdIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$itemId(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.categoryIdIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$categoryId(), false);
        String realmGet$categoryName = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$categoryName();
        if (realmGet$categoryName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.categoryNameIndex, rowIndex, realmGet$categoryName, false);
        }
        String realmGet$unitId = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$unitId();
        if (realmGet$unitId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.unitIdIndex, rowIndex, realmGet$unitId, false);
        }
        String realmGet$unit = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$unit();
        if (realmGet$unit != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.unitIndex, rowIndex, realmGet$unit, false);
        }
        String realmGet$name = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        String realmGet$imagePath = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$imagePath();
        if (realmGet$imagePath != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.imagePathIndex, rowIndex, realmGet$imagePath, false);
        }
        String realmGet$printName = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$printName();
        if (realmGet$printName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.printNameIndex, rowIndex, realmGet$printName, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.quantityIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$quantity(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.salesPriceIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$salesPrice(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.purchasePriceIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$purchasePrice(), false);
        String realmGet$printHidden = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$printHidden();
        if (realmGet$printHidden != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.printHiddenIndex, rowIndex, realmGet$printHidden, false);
        }
        byte[] realmGet$image = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$image();
        if (realmGet$image != null) {
            Table.nativeSetByteArray(tableNativePtr, columnInfo.imageIndex, rowIndex, realmGet$image, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.StockItem.class);
        long tableNativePtr = table.getNativePtr();
        StockItemColumnInfo columnInfo = (StockItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.StockItem.class);
        com.portal.terminalbd.model.StockItem object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.StockItem) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.countIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$count(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.globalIdIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$globalId(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.itemIdIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$itemId(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.categoryIdIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$categoryId(), false);
            String realmGet$categoryName = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$categoryName();
            if (realmGet$categoryName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.categoryNameIndex, rowIndex, realmGet$categoryName, false);
            }
            String realmGet$unitId = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$unitId();
            if (realmGet$unitId != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.unitIdIndex, rowIndex, realmGet$unitId, false);
            }
            String realmGet$unit = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$unit();
            if (realmGet$unit != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.unitIndex, rowIndex, realmGet$unit, false);
            }
            String realmGet$name = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            }
            String realmGet$imagePath = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$imagePath();
            if (realmGet$imagePath != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.imagePathIndex, rowIndex, realmGet$imagePath, false);
            }
            String realmGet$printName = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$printName();
            if (realmGet$printName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.printNameIndex, rowIndex, realmGet$printName, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.quantityIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$quantity(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.salesPriceIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$salesPrice(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.purchasePriceIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$purchasePrice(), false);
            String realmGet$printHidden = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$printHidden();
            if (realmGet$printHidden != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.printHiddenIndex, rowIndex, realmGet$printHidden, false);
            }
            byte[] realmGet$image = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$image();
            if (realmGet$image != null) {
                Table.nativeSetByteArray(tableNativePtr, columnInfo.imageIndex, rowIndex, realmGet$image, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.portal.terminalbd.model.StockItem object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.StockItem.class);
        long tableNativePtr = table.getNativePtr();
        StockItemColumnInfo columnInfo = (StockItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.StockItem.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.countIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$count(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.globalIdIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$globalId(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.itemIdIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$itemId(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.categoryIdIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$categoryId(), false);
        String realmGet$categoryName = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$categoryName();
        if (realmGet$categoryName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.categoryNameIndex, rowIndex, realmGet$categoryName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.categoryNameIndex, rowIndex, false);
        }
        String realmGet$unitId = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$unitId();
        if (realmGet$unitId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.unitIdIndex, rowIndex, realmGet$unitId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.unitIdIndex, rowIndex, false);
        }
        String realmGet$unit = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$unit();
        if (realmGet$unit != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.unitIndex, rowIndex, realmGet$unit, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.unitIndex, rowIndex, false);
        }
        String realmGet$name = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        String realmGet$imagePath = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$imagePath();
        if (realmGet$imagePath != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.imagePathIndex, rowIndex, realmGet$imagePath, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.imagePathIndex, rowIndex, false);
        }
        String realmGet$printName = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$printName();
        if (realmGet$printName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.printNameIndex, rowIndex, realmGet$printName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.printNameIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.quantityIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$quantity(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.salesPriceIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$salesPrice(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.purchasePriceIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$purchasePrice(), false);
        String realmGet$printHidden = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$printHidden();
        if (realmGet$printHidden != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.printHiddenIndex, rowIndex, realmGet$printHidden, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.printHiddenIndex, rowIndex, false);
        }
        byte[] realmGet$image = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$image();
        if (realmGet$image != null) {
            Table.nativeSetByteArray(tableNativePtr, columnInfo.imageIndex, rowIndex, realmGet$image, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.imageIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.StockItem.class);
        long tableNativePtr = table.getNativePtr();
        StockItemColumnInfo columnInfo = (StockItemColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.StockItem.class);
        com.portal.terminalbd.model.StockItem object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.StockItem) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.countIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$count(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.globalIdIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$globalId(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.itemIdIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$itemId(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.categoryIdIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$categoryId(), false);
            String realmGet$categoryName = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$categoryName();
            if (realmGet$categoryName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.categoryNameIndex, rowIndex, realmGet$categoryName, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.categoryNameIndex, rowIndex, false);
            }
            String realmGet$unitId = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$unitId();
            if (realmGet$unitId != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.unitIdIndex, rowIndex, realmGet$unitId, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.unitIdIndex, rowIndex, false);
            }
            String realmGet$unit = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$unit();
            if (realmGet$unit != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.unitIndex, rowIndex, realmGet$unit, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.unitIndex, rowIndex, false);
            }
            String realmGet$name = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
            }
            String realmGet$imagePath = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$imagePath();
            if (realmGet$imagePath != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.imagePathIndex, rowIndex, realmGet$imagePath, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.imagePathIndex, rowIndex, false);
            }
            String realmGet$printName = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$printName();
            if (realmGet$printName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.printNameIndex, rowIndex, realmGet$printName, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.printNameIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.quantityIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$quantity(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.salesPriceIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$salesPrice(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.purchasePriceIndex, rowIndex, ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$purchasePrice(), false);
            String realmGet$printHidden = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$printHidden();
            if (realmGet$printHidden != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.printHiddenIndex, rowIndex, realmGet$printHidden, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.printHiddenIndex, rowIndex, false);
            }
            byte[] realmGet$image = ((com_portal_terminalbd_model_StockItemRealmProxyInterface) object).realmGet$image();
            if (realmGet$image != null) {
                Table.nativeSetByteArray(tableNativePtr, columnInfo.imageIndex, rowIndex, realmGet$image, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.imageIndex, rowIndex, false);
            }
        }
    }

    public static com.portal.terminalbd.model.StockItem createDetachedCopy(com.portal.terminalbd.model.StockItem realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.portal.terminalbd.model.StockItem unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.portal.terminalbd.model.StockItem();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.portal.terminalbd.model.StockItem) cachedObject.object;
            }
            unmanagedObject = (com.portal.terminalbd.model.StockItem) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_portal_terminalbd_model_StockItemRealmProxyInterface unmanagedCopy = (com_portal_terminalbd_model_StockItemRealmProxyInterface) unmanagedObject;
        com_portal_terminalbd_model_StockItemRealmProxyInterface realmSource = (com_portal_terminalbd_model_StockItemRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$count(realmSource.realmGet$count());
        unmanagedCopy.realmSet$globalId(realmSource.realmGet$globalId());
        unmanagedCopy.realmSet$itemId(realmSource.realmGet$itemId());
        unmanagedCopy.realmSet$categoryId(realmSource.realmGet$categoryId());
        unmanagedCopy.realmSet$categoryName(realmSource.realmGet$categoryName());
        unmanagedCopy.realmSet$unitId(realmSource.realmGet$unitId());
        unmanagedCopy.realmSet$unit(realmSource.realmGet$unit());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$imagePath(realmSource.realmGet$imagePath());
        unmanagedCopy.realmSet$printName(realmSource.realmGet$printName());
        unmanagedCopy.realmSet$quantity(realmSource.realmGet$quantity());
        unmanagedCopy.realmSet$salesPrice(realmSource.realmGet$salesPrice());
        unmanagedCopy.realmSet$purchasePrice(realmSource.realmGet$purchasePrice());
        unmanagedCopy.realmSet$printHidden(realmSource.realmGet$printHidden());
        unmanagedCopy.realmSet$image(realmSource.realmGet$image());

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("StockItem = proxy[");
        stringBuilder.append("{count:");
        stringBuilder.append(realmGet$count());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{globalId:");
        stringBuilder.append(realmGet$globalId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{itemId:");
        stringBuilder.append(realmGet$itemId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{categoryId:");
        stringBuilder.append(realmGet$categoryId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{categoryName:");
        stringBuilder.append(realmGet$categoryName() != null ? realmGet$categoryName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{unitId:");
        stringBuilder.append(realmGet$unitId() != null ? realmGet$unitId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{unit:");
        stringBuilder.append(realmGet$unit() != null ? realmGet$unit() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{imagePath:");
        stringBuilder.append(realmGet$imagePath() != null ? realmGet$imagePath() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{printName:");
        stringBuilder.append(realmGet$printName() != null ? realmGet$printName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{quantity:");
        stringBuilder.append(realmGet$quantity());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{salesPrice:");
        stringBuilder.append(realmGet$salesPrice());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{purchasePrice:");
        stringBuilder.append(realmGet$purchasePrice());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{printHidden:");
        stringBuilder.append(realmGet$printHidden() != null ? realmGet$printHidden() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{image:");
        stringBuilder.append(realmGet$image() != null ? realmGet$image() : "null");
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
        com_portal_terminalbd_model_StockItemRealmProxy aStockItem = (com_portal_terminalbd_model_StockItemRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aStockItem.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aStockItem.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aStockItem.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
