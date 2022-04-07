package com.portal.terminalbd.network;


import com.portal.terminalbd.model.AnonymousCustomer;
import com.portal.terminalbd.model.BankAccount;
import com.portal.terminalbd.model.Category;
import com.portal.terminalbd.model.CustomerInvoice;
import com.portal.terminalbd.model.CustomerLedeger;
import com.portal.terminalbd.model.DIMSItem;
import com.portal.terminalbd.model.ExpenseCategory;
import com.portal.terminalbd.model.MobileAccount;
import com.portal.terminalbd.model.ModelCategory;
import com.portal.terminalbd.model.ModelCreateProduct;
import com.portal.terminalbd.model.ModelCustomerInvoice;
import com.portal.terminalbd.model.ModelPaymentReceive;
import com.portal.terminalbd.model.ModelUnit;
import com.portal.terminalbd.model.SyncRequestBody.SalesItemRequestBody;
import com.portal.terminalbd.model.Setup;
import com.portal.terminalbd.model.StockItem;
import com.portal.terminalbd.model.SystemUser;
import com.portal.terminalbd.model.TokenNo;
import com.portal.terminalbd.model.TransactionMethod;
import com.portal.terminalbd.model.Vendor;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * APIRequests contains all the Network Request Methods with relevant API Endpoints
 **/

public interface APIRequests {

    @GET("stock-category")
    Observable<Response<List<Category>>> getCategory(@Header("X-API-SECRET") String secret);

    @FormUrlEncoded
    @POST("customer-ledeger")
    Observable<Response<List<CustomerLedeger>>> getCustomerLedeger(@Header("X-API-SECRET") String secret,@Field("customerId") String id);

    @FormUrlEncoded
    @POST("customer-ledeger")
    Observable<Response<List<CustomerLedeger>>> getCustomerLedegerFilter(@Header("X-API-SECRET") String secret,@Field("customerId") String id,@Field("startDate") String startDate,@Field("enddate") String enddate,@Field("invoice") String invoice);

    @GET("invoice-message")
    Observable<Response<List<CustomerLedeger>>> getPrintMessage(@Header("X-API-SECRET") String secret);

    @FormUrlEncoded
    @POST("customer-invoice-details")
    Observable<Response<CustomerInvoice>> getCustomerIncoice(@Header("X-API-SECRET") String secret, @Field("invoice") String invoice);


    @GET("product-unit")
    Observable<Response<List<ModelUnit>>> getUnit(@Header("X-API-SECRET") String secret);

    @GET("stock-category")
    Observable<Response<List<ModelCategory>>> getCategories(@Header("X-API-SECRET") String secret);

    @POST("stock-edit")
    Observable<Response<ModelCategory>> getEditProduct(@Header("X-API-SECRET") String secret,@Query("id") String id);

    @GET("token-no")
    Observable<Response<List<TokenNo>>> getTokenNo(@Header("X-API-SECRET") String secret);

    @GET("stock-item")
    Observable<Response<List<StockItem>>> getStockItem(@Header("X-API-SECRET") String secret);



    @FormUrlEncoded
    @POST("setup")
    Single<Response<Setup>> setup(@Field("mobile") String mobile,
                                  @Field("uniqueCode") String uniqueCode,
                                  @Field("deviceId") String deviceId);

    @FormUrlEncoded
    @POST("otp-login")
    Single<Response<Setup>> otpLogin(@Field("mobile") String mobile);


    @GET("system-user")
    Observable<Response<List<SystemUser>>> getSystemUser(@Header("X-API-SECRET") String secret);

    @GET("tarnsaction-method")
    Observable<Response<List<TransactionMethod>>> getTransactionMethod(@Header("X-API-SECRET") String secret);


    @GET("anonymiuse-customer")
    Observable<Response<List<AnonymousCustomer>>> getAnonymousCustomer(@Header("X-API-SECRET") String secret);



    @GET("online-bank-acccount")
    Observable<Response<List<BankAccount>>> getOnlineBankAccount(@Header("X-API-SECRET") String secret);

    @GET("mobile-account")
    Observable<Response<List<MobileAccount>>> getMobileAccount(@Header("X-API-SECRET") String secret);

    @GET("vendor")
    Observable<Response<List<Vendor>>> getVendor(@Header("X-API-SECRET") String secret);

    @GET("expense-category")
    Observable<Response<List<ExpenseCategory>>> getExpenseCategory(@Header("X-API-SECRET") String secret);

    @GET("medicine-dims")
    Observable<Response<List<DIMSItem>>> getDIMS(@Header("X-API-SECRET") String secret);

    @GET("payment-card")
    Observable<Response<List<PaymentCards>>> getPaymentCards(@Header("X-API-SECRET") String secret);

//    @POST("sales")
//    Observable<Response<ResponseBody>> syncSales(
//            @Header("X-DEVICE-ID") String deviceID,
//            @Header("X-API-SECRET") String secret,
//            @Body SalesRequestBody salesRequestBody);

    @FormUrlEncoded
    @POST("sales")
    Observable<Response<ResponseBody>> syncSales(
            @Header("X-DEVICE-ID") String deviceID,
            @Header("X-API-SECRET") String secret,
            @Field("item") String salesItem,
            @Field("itemCount") int itemCount,
            @Field("subItem") String subItems,
            @Field("subItemCount") int subItemCount);

    @FormUrlEncoded
    @POST("customer-create")
    Observable<Response<AnonymousCustomer>> createCustomer(
            @Header("X-API-SECRET") String secret,
            @Field("name") String name,
            @Field("mobile") String mobile,
            @Field("address") String address,
            @Field("email") String email,
            @Field("openingBalance") String openingBalance,
            @Field("userId") String userid
    );

    @FormUrlEncoded
    @POST("payment-receive")
    Observable<Response<ModelPaymentReceive>> createPaymentReceive(
            @Header("X-API-SECRET") String secret,
            @Field("userId") String userId,
            @Field("customerId") String customerId,
            @Field("method") String method,
            @Field("amount") String amount,
            @Field("mode") String mode,
            @Field("remark") String remark,
            @Field("bankAccount") String bankAccount,
            @Field("mobileAccount") String mobileAccount,
            @Field("transactionId") String transactionId,
            @Field("mobileNo") String mobileNo
    );


    @POST("sales-item")
    Observable<Response<ResponseBody>> syncSalesItems(
            @Header("X-DEVICE-ID") String deviceID,
            @Header("X-API-SECRET") String secret,
            @Body SalesItemRequestBody salesItemRequestBody);

//    @POST("purchase")
//    Observable<Response<ResponseBody>> syncPurchase(
//            @Header("X-DEVICE-ID") String deviceID,
//            @Header("X-API-SECRET") String secret,
//            @Body PurchaseHistory purchaseHistory);

    @FormUrlEncoded
    @POST("purchase")
    Observable<Response<ResponseBody>> syncPurchase(
            @Header("X-DEVICE-ID") String deviceID,
            @Header("X-API-SECRET") String secret,
            @Field("item") String purchaseItem,
            @Field("itemCount") int itemCount,
            @Field("subItem") String subItems,
            @Field("subItemCount") int subItemCount);

//    @POST("purchase-item")
//    Observable<Response<ResponseBody>> syncPurchaseItem(
//            @Header("X-DEVICE-ID") String deviceID,
//            @Header("X-API-SECRET") String secret,
//            @Body PurchaseItemHistory purchaseItemHistory);

//    @POST("expense")
//    Observable<Response<ResponseBody>> syncExpense(
//            @Header("X-DEVICE-ID") String deviceID,
//            @Header("X-API-SECRET") String secret,
//            @Body ExpenseItem expenseItem);


    @FormUrlEncoded
    @POST("expense")
    Observable<Response<ResponseBody>> syncExpense(
            @Header("X-DEVICE-ID") String deviceID,
            @Header("X-API-SECRET") String secret,
            @Field("item") String expenseItem,
            @Field("itemCount") int itemCount);


    @POST("stock-create")
    Observable<Response<ModelCreateProduct>> createStockItem(@Header("X-API-SECRET") String secret, @Body ModelCreateProduct modelCreateProduct);

    @POST("stock-update")
    Observable<Response<ModelCreateProduct>> updateStockItem(@Header("X-API-SECRET") String secret, @Body ModelCreateProduct modelCreateProduct);



    @FormUrlEncoded
    @POST("stock-create")
    Observable<Response<ResponseBody>> createStock(
            @Header("X-DEVICE-ID") String deviceID,
            @Header("X-API-SECRET") String secret,
            @Field("name") String name,
            @Field("category") String category,
            @Field("brand") String brand,
            @Field("unit") String unit,
            @Field("purchasePrice") String purchasePrice,
            @Field("salesPrice") String salesPrice,
            @Field("minQuantity") String minQuantity,
            @Field("openingQuantity") String openingQuantity,
            @Field("discountPrice") String discountPrice,
            @Field("description") String description
    );

}

