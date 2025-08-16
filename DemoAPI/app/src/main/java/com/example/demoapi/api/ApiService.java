package com.example.demoapi.api;


import com.example.demoapi.models.Product;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {
    @GET("products")
    Call<List<Product>> getProducts();

    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") int productId);

    @POST("products")
    Call<Product> createProduct(@Body Product product);

    @PUT("products/{id}")
    Call<Product> updateProduct(@Path("id") int productId, @Body Product product);

    @DELETE("products/{id}")
    Call<Void> deleteProduct(@Path("id") int productId);
}
