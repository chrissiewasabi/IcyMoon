package xyz.megundo.icymoon.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import xyz.megundo.icymoon.data.Information

interface InfoApiService {
    @GET("fakeJson/db")
    fun getResponse(): Call<Information>

    companion object RetrofitProvider {
        private const val baseUrl = "https://my-json-server.typicode.com/chrissiewasabi/"

        fun provideRetrofit(): InfoApiService {
            /* using this Okhttp logging service for purposes of debugging , not intended for production */
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build()

            return retrofit.create(InfoApiService::class.java)
        }
    }
}