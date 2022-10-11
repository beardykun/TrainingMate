package com.example.trainingmate.di

import com.example.trainingmate.data.repositories.TrainingDetailsRepositoryImpl
import com.example.trainingmate.domain.useCases.trainingDetailsUseCases.AddCurrentExerciseInfoObjectUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrainingDetailModule {

    @Singleton
    @Provides
    fun provideITrainingDetailsRepository() =
        TrainingDetailsRepositoryImpl

    @Singleton
    @Provides
    fun provideAddCurrentExerciseInfoObjectUseCase(repository: TrainingDetailsRepositoryImpl) =
        AddCurrentExerciseInfoObjectUseCase(repository)
}