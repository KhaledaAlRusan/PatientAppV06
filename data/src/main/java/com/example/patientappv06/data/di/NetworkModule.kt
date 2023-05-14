package com.example.patientappv06.data.di

import com.example.patientappv06.data.datasource.PatientDataSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{
    val baseUrl = "https://patients-app-api.herokuapp.com/"

    @Provides
    @Singleton
    fun provideRetrofit(factory: Factory):Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(factory)
            .build()
    }

    @Provides
    @Singleton
    fun providePatients(retrofit: Retrofit): PatientDataSource {
        return retrofit.create(PatientDataSource::class.java)
    }


    @Provides
    @Reusable
    fun provideMoshi():Moshi{
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Reusable
    fun provideConverterFactory(moshi: Moshi):Factory{
        return MoshiConverterFactory.create(moshi)
    }


}