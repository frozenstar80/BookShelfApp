package com.example.bookshelfapp.di

import android.app.Application
import androidx.room.Room
import com.example.bookshelfapp.domain.api.ApiService
import com.example.bookshelfapp.domain.local.BookDatabase
import com.example.bookshelfapp.util.Constants
import com.google.gson.GsonBuilder
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
class AppModule {

    //Retrofit Builder Class using base url and converter factory
    @Provides
    @Singleton
    fun retrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BOOK_LIST_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
        .client(
            OkHttpClient.Builder().apply {
                readTimeout(600,TimeUnit.SECONDS)
                writeTimeout(600,TimeUnit.SECONDS)
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)) // added interceptor to log the data

    }.build()
    )
        .build()
    // Retrofit Builder Class for Network Calling

    @Provides
    @Singleton
    fun providesDatabase(app: Application): BookDatabase {
        return Room.databaseBuilder(
            app,
            BookDatabase::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    // Room Database Builder Class for Network Calling


    @Provides
    @Singleton
    fun getApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}