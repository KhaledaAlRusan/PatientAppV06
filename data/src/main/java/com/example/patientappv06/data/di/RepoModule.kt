package com.example.patientappv06.data.di

import com.example.patientappv06.data.datasource.PatientDataSource
import com.example.patientappv06.data.repo.PatientRepoImpl
import com.example.patientappv06.domain.repo.PatientRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepoModule {



    // When I call Patient Repo in my code it's gonna return  -> PatientRepoImpl(patientDataSource)
    @Provides
    fun provideRepoPatients(patientDataSource: PatientDataSource):PatientRepo{
        return PatientRepoImpl(patientDataSource)
    }
}