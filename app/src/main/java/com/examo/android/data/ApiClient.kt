package com.examo.android.data
import com.examo.android.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object Session{var token:String?=null;var user:AuthUser?=null}
object ApiClient{
 private val auth=Interceptor{c->val b=c.request().newBuilder();Session.token?.let{b.header("Authorization","Bearer $it")};c.proceed(b.build())}
 private val log=HttpLoggingInterceptor().apply{level=HttpLoggingInterceptor.Level.BASIC}
 val api:ApiService=Retrofit.Builder().baseUrl(BuildConfig.API_BASE_URL)
  .client(OkHttpClient.Builder().addInterceptor(auth).addInterceptor(log).build())
  .addConverterFactory(GsonConverterFactory.create()).build().create(ApiService::class.java)
}
