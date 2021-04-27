package com.edimariyanto.meal.data.network

import com.edimariyanto.meal.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteDataSources {

    companion object {
        private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    }

    fun <Api> buildApi(
            api: Class<Api>,
            authToken: String? = null
    ) : Api{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                    OkHttpClient.Builder()
                            .addInterceptor { chain ->
                                chain.proceed(chain.request().newBuilder().also {
                                    it.addHeader("Authorization", "Bearer $authToken")
                                }.build())
                            }
                            .also {
                                if (BuildConfig.DEBUG) {
                                    val logging = HttpLoggingInterceptor()
                                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                                    it.addInterceptor(logging)
                                }
                                it.connectTimeout(60, TimeUnit.SECONDS)
                                it.readTimeout(60, TimeUnit.SECONDS)
                                it.writeTimeout(60, TimeUnit.SECONDS)
                                it.addInterceptor { chain ->
                                    val original = chain.request()
                                    val requestBuilder = original.newBuilder()
                                            .header("X-App-Version", BuildConfig.VERSION_NAME)

                                    val request = requestBuilder.build()
                                    chain.proceed(request)
                                }
                            }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

}

//client.writeTimeout(60, TimeUnit.SECONDS)
//client.addInterceptor(
//new Interceptor () {
