package com.example.trainingmate.di

import com.example.trainingmate.data.repositories.CurrentExerciseRepositoryImpl
import com.example.trainingmate.domain.useCases.cureentExerciseUseCases.GetCurrentExerciseInfoObjectUseCase
import com.example.trainingmate.domain.useCases.cureentExerciseUseCases.UpdateInfoExerciseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrentExerciseModule {

    @Provides
    @Singleton
    fun provideCurrentExerciseRepositoryImpl() = CurrentExerciseRepositoryImpl

    @Singleton
    @Provides
    fun provideGetCurrentExerciseInfoObjectUseCase(repository: CurrentExerciseRepositoryImpl) =
        GetCurrentExerciseInfoObjectUseCase(repository)

    @Singleton
    @Provides
    fun provideUpdateInfoExerciseUseCase(repository: CurrentExerciseRepositoryImpl) =
        UpdateInfoExerciseUseCase(repository)
}