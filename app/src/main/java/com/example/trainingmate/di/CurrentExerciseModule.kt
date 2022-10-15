package com.example.trainingmate.di

import com.example.trainingmate.data.repositories.CurrentExerciseRepositoryImpl
import com.example.trainingmate.domain.useCases.cureentExerciseUseCases.CountdownTimerUseCase
import com.example.trainingmate.domain.useCases.cureentExerciseUseCases.GetCurrentExerciseInfoObjectUseCase
import com.example.trainingmate.domain.useCases.cureentExerciseUseCases.UpdateInfoExerciseUseCase
import com.example.trainingmate.domain.useCases.cureentExerciseUseCases.ValidateInputUseCase
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

    @Singleton
    @Provides
    fun provideValidateInputUseCase(repository: CurrentExerciseRepositoryImpl) =
        ValidateInputUseCase(repository)

    @Singleton
    @Provides
    fun provideCountdownTimerUseCase(repository: CurrentExerciseRepositoryImpl) =
        CountdownTimerUseCase(repository)
}