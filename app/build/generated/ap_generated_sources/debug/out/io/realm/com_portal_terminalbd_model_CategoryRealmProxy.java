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
public class com_portal_terminalbd_model_CategoryRealmProxy extends com.portal.terminalbd.model.Category
    implements RealmObjectProxy, com_portal_terminalbd_model_CategoryRealmProxyInterface {

    static final class CategoryColumnInfo extends ColumnInfo {
        long maxColumnIndexValue;
        long categoryIdIndex;
        long nameIndex;
        long slugIndex;

        CategoryColumnInfo(OsSchemaInfo schemaInfo) {
            super(3);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Category");
            this.categoryIdIndex = addColumnDetails("categoryId", "categoryId", objectSchemaInfo);
            this.nameIndex = addColumnDetails("name", "name", objectSchemaInfo);
            this.slugIndex = addColumnDetails("slug", "slug", objectSchemaInfo);
            this.maxColumnIndexValue = objectSchemaInfo.getMaxColumnIndex();
        }

        CategoryColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new CategoryColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final CategoryColumnInfo src = (CategoryColumnInfo) rawSrc;
            final CategoryColumnInfo dst = (CategoryColumnInfo) rawDst;
            dst.categoryIdIndex = src.categoryIdIndex;
            dst.nameIndex = src.nameIndex;
            dst.slugIndex = src.slugIndex;
            dst.maxColumnIndexValue = src.maxColumnIndexValue;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private CategoryColumnInfo columnInfo;
    private ProxyState<com.portal.terminalbd.model.Category> proxyState;

    com_portal_terminalbd_model_CategoryRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (CategoryColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.portal.terminalbd.model.Category>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
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
    public String realmGet$slug() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.slugIndex);
    }

    @Override
    public void realmSet$slug(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.slugIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.slugIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.slugIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.slugIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Category", 3, 0);
        builder.addPersistedProperty("categoryId", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("slug", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static CategoryColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new CategoryColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Category";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Category";
    }

    @SuppressWarnings("cast")
    public static com.portal.terminalbd.model.Category createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.portal.terminalbd.model.Category obj = realm.createObjectInternal(com.portal.terminalbd.model.Category.class, true, excludeFields);

        final com_portal_terminalbd_model_CategoryRealmProxyInterface objProxy = (com_portal_terminalbd_model_CategoryRealmProxyInterface) obj;
        if (json.has("categoryId")) {
            if (json.isNull("categoryId")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'categoryId' to null.");
            } else {
                objProxy.realmSet$categoryId((int) json.getInt("categoryId"));
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("slug")) {
            if (json.isNull("slug")) {
                objProxy.realmSet$slug(null);
            } else {
                objProxy.realmSet$slug((String) json.getString("slug"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.portal.terminalbd.model.Category createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.portal.terminalbd.model.Category obj = new com.portal.terminalbd.model.Category();
        final com_portal_terminalbd_model_CategoryRealmProxyInterface objProxy = (com_portal_terminalbd_model_CategoryRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("categoryId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$categoryId((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'categoryId' to null.");
                }
            } else if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (name.equals("slug")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$slug((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$slug(null);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    private static com_portal_terminalbd_model_CategoryRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.portal.terminalbd.model.Category.class), false, Collections.<String>emptyList());
        io.realm.com_portal_terminalbd_model_CategoryRealmProxy obj = new io.realm.com_portal_terminalbd_model_CategoryRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.portal.terminalbd.model.Category copyOrUpdate(Realm realm, CategoryColumnInfo columnInfo, com.portal.terminalbd.model.Category object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.portal.terminalbd.model.Category) cachedRealmObject;
        }

        return copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.portal.terminalbd.model.Category copy(Realm realm, CategoryColumnInfo columnInfo, com.portal.terminalbd.model.Category newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.portal.terminalbd.model.Category) cachedRealmObject;
        }

        com_portal_terminalbd_model_CategoryRealmProxyInterface realmObjectSource = (com_portal_terminalbd_model_CategoryRealmProxyInterface) newObject;

        Table table = realm.getTable(com.portal.terminalbd.model.Category.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);

        // Add all non-"object reference" fields
        builder.addInteger(columnInfo.categoryIdIndex, realmObjectSource.realmGet$categoryId());
        builder.addString(columnInfo.nameIndex, realmObjectSource.realmGet$name());
        builder.addString(columnInfo.slugIndex, realmObjectSource.realmGet$slug());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_portal_terminalbd_model_CategoryRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.portal.terminalbd.model.Category object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.Category.class);
        long tableNativePtr = table.getNativePtr();
        CategoryColumnInfo columnInfo = (CategoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.Category.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.categoryIdIndex, rowIndex, ((com_portal_terminalbd_model_CategoryRealmProxyInterface) object).realmGet$categoryId(), false);
        String realmGet$name = ((com_portal_terminalbd_model_CategoryRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        String realmGet$slug = ((com_portal_terminalbd_model_CategoryRealmProxyInterface) object).realmGet$slug();
        if (realmGet$slug != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.slugIndex, rowIndex, realmGet$slug, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.Category.class);
        long tableNativePtr = table.getNativePtr();
        CategoryColumnInfo columnInfo = (CategoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.Category.class);
        com.portal.terminalbd.model.Category object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.Category) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.categoryIdIndex, rowIndex, ((com_portal_terminalbd_model_CategoryRealmProxyInterface) object).realmGet$categoryId(), false);
            String realmGet$name = ((com_portal_terminalbd_model_CategoryRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            }
            String realmGet$slug = ((com_portal_terminalbd_model_CategoryRealmProxyInterface) object).realmGet$slug();
            if (realmGet$slug != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.slugIndex, rowIndex, realmGet$slug, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.portal.terminalbd.model.Category object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.portal.terminalbd.model.Category.class);
        long tableNativePtr = table.getNativePtr();
        CategoryColumnInfo columnInfo = (CategoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.Category.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.categoryIdIndex, rowIndex, ((com_portal_terminalbd_model_CategoryRealmProxyInterface) object).realmGet$categoryId(), false);
        String realmGet$name = ((com_portal_terminalbd_model_CategoryRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        String realmGet$slug = ((com_portal_terminalbd_model_CategoryRealmProxyInterface) object).realmGet$slug();
        if (realmGet$slug != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.slugIndex, rowIndex, realmGet$slug, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.slugIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.portal.terminalbd.model.Category.class);
        long tableNativePtr = table.getNativePtr();
        CategoryColumnInfo columnInfo = (CategoryColumnInfo) realm.getSchema().getColumnInfo(com.portal.terminalbd.model.Category.class);
        com.portal.terminalbd.model.Category object = null;
        while (objects.hasNext()) {
            object = (com.portal.terminalbd.model.Category) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.categoryIdIndex, rowIndex, ((com_portal_terminalbd_model_CategoryRealmProxyInterface) object).realmGet$categoryId(), false);
            String realmGet$name = ((com_portal_terminalbd_model_CategoryRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
            }
            String realmGet$slug = ((com_portal_terminalbd_model_CategoryRealmProxyInterface) object).realmGet$slug();
            if (realmGet$slug != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.slugIndex, rowIndex, realmGet$slug, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.slugIndex, rowIndex, false);
            }
        }
    }

    public static com.portal.terminalbd.model.Category createDetachedCopy(com.portal.terminalbd.model.Category realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.portal.terminalbd.model.Category unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.portal.terminalbd.model.Category();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.portal.terminalbd.model.Category) cachedObject.object;
            }
            unmanagedObject = (com.portal.terminalbd.model.Category) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_portal_terminalbd_model_CategoryRealmProxyInterface unmanagedCopy = (com_portal_terminalbd_model_CategoryRealmProxyInterface) unmanagedObject;
        com_portal_terminalbd_model_CategoryRealmProxyInterface realmSource = (com_portal_terminalbd_model_CategoryRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$categoryId(realmSource.realmGet$categoryId());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$slug(realmSource.realmGet$slug());

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Category = proxy[");
        stringBuilder.append("{categoryId:");
        stringBuilder.append(realmGet$categoryId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{slug:");
        stringBuilder.append(realmGet$slug() != null ? realmGet$slug() : "null");
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
        com_portal_terminalbd_model_CategoryRealmProxy aCategory = (com_portal_terminalbd_model_CategoryRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aCategory.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aCategory.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aCategory.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
