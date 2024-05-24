package kz.schol.okhttpretrofit

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

fun createRetrofitClient(){
    Retrofit.Builder()
        .baseUrl("http:/api.com")
        .client(createClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}

fun createClient(): OkHttpClient{
    return OkHttpClient
        .Builder()
        .addInterceptor(HttpLoggingInterceptor{
            Log.d("HTTP", it)
        }.also {
            it.level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor{ chain ->
            val req = chain.request()
            val newReq =  req.newBuilder().header("autorization", "").build()
            chain.proceed(newReq)
        }
        .build()
}


interface ApiService{
    @POST("/endpoint-name")
    suspend fun create(@Body entity: SomeEntity):Response<Any>

    @GET("/endpoint-name")
    suspend fun get(): Response<SomeEntity>
}

data class SomeEntity(val id: String, val icon: String)