package com.portal.terminalbd.network;



import com.portal.terminalbd.model.ModelAddProduct;
import com.portal.terminalbd.model.ModelUser;
import com.portal.terminalbd.model.ResponseModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface ApiInterface {


    @Multipart
    @POST("stock-create")
    Call<ResponseModel> productUpload(
            @Part("sender_information") RequestBody description,
            @Part MultipartBody.Part file);


    @Multipart
    @POST("bengal_upload_table.php")
    Call<ResponseModel> tableDetailsUpload(
            @Part("sender_information") RequestBody description,
            @Part MultipartBody.Part file);

    @Multipart
    @POST("upload_product.php")
    Call<ResponseModel> fileUpload(
            @Part("sender_information") RequestBody description,
            @Part MultipartBody.Part file);


    @Multipart
    @POST("upload_category_bengal.php")
    Call<ResponseModel> categoryfileUpload(
            @Part("sender_information") RequestBody description,
            @Part MultipartBody.Part file);


    @GET("bengal_get_shop_type.php")
    Call<List<ModelAddProduct>> getShopType();

    @POST("bengal_get_category.php")
    Call<List<ModelAddProduct>> getCategory(@Body ModelAddProduct modelAddProduct);

    @POST("bengal_get_table.php")
    Call<List<ModelAddProduct>> getTables(@Body ModelAddProduct modelAddProduct);

    @POST("delete_bengal_category.php")
    Call<ModelAddProduct> deleteCategory(@Body ModelAddProduct modelAddProduct);


    @POST("delete_bengal_product.php")
    Call<ModelAddProduct> deleteProduct(@Body ModelAddProduct modelAddProduct);

    @POST("delete_bengal_table.php")
    Call<ModelAddProduct> deleteTable(@Body ModelAddProduct modelAddProduct);

    @POST("bengal_create_setup.php")
    Call<ModelUser> createsetup(@Body ModelUser modelUsers);


    @POST("bengal_create_user.php")
    Call<ModelUser> createuser(@Body ModelUser modelUsers);

    @POST("bengal_get_users.php")
    Call<List<ModelUser>> getUsers(@Body ModelUser modelUsers);

    @POST("bengal_get_products.php")
    Call<List<ModelAddProduct>> getproducts(@Body ModelAddProduct modelAddProduct);

}
