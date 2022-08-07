package com.example.trainingmate.di

import android.content.Context
import androidx.room.Room
import com.example.trainingmate.dataBase.DatabaseContract
import com.example.trainingmate.dataBase.TrainingDatabase
import com.example.trainingmate.dataBase.dao.ExerciseDao
import com.example.trainingmate.dataBase.dao.ExerciseInfoDao
import com.example.trainingmate.dataBase.dao.TrainingDao
import com.example.trainingmate.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.dataBase.repositories.ExerciseInfoRepository
import com.example.trainingmate.dataBase.repositories.ExerciseRepository
import com.example.trainingmate.dataBase.repositories.TrainingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideTrainingDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, TrainingDatabase::class.java, DatabaseContract.DATABASE_NAME)
        .build()

    @Singleton
    @Provides
    fun provideTrainingDao(database: TrainingDatabase) = database.trainingDao()

    @Singleton
    @Provides
    fun provideExerciseInfoDao(database: TrainingDatabase) = database.exerciseInfoDao()

    @Singleton
    @Provides
    fun provideExerciseDao(database: TrainingDatabase) = database.exerciseDao()

    @Singleton
    @Provides
    fun provideExerciseRepository(exerciseDao: ExerciseDao): ExerciseRepository =
        ExerciseRepository(exerciseDao)

    @Singleton
    @Provides
    fun provideExerciseDBViewModel(
        exerciseRepository: ExerciseRepository,
        exerciseInfoRepository: ExerciseInfoRepository,
        trainingRepository: TrainingRepository
    ): DBViewModel =
        DBViewModel(exerciseRepository, exerciseInfoRepository, trainingRepository)

    @Singleton
    @Provides
    fun provideExerciseInfoRepository(exerciseInfoDao: ExerciseInfoDao): ExerciseInfoRepository =
        ExerciseInfoRepository(exerciseInfoDao)

    @Singleton
    @Provides
    fun provideTrainingRepository(trainingDao: TrainingDao): TrainingRepository =
        TrainingRepository(trainingDao)
}