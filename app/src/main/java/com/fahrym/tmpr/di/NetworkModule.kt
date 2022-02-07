package com.fahrym.tmpr.di

import com.fahrym.tmpr.data.network.TmprApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://temper.works/"

    @Singleton
    @Provides
    fun provideOkHttpClient(
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                var request = chain.request()
                val url = request.url.newBuilder()
                    .build()
                request = request.newBuilder()
                    .url(url)
                    .build()
                chain.proceed(request)
            }
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        builder.addNetworkInterceptor(logging)

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideTmprApi(
        okHttpClient: OkHttpClient
    ): TmprApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TmprApi::class.java)
    }
}

