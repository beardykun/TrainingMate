package com.example.trainingmate.data.dataBase.repositories

import androidx.lifecycle.LiveData
import com.example.trainingmate.data.dataBase.dao.ExerciseInfoDao
import com.example.trainingmate.data.dataBase.objects.ExerciseInfoObject
import javax.inject.Inject

class ExerciseInfoRepository @Inject constructor(private val exerciseInfoDao: ExerciseInfoDao) {

    fun getExerciseInfoWithName(exName: String, exTrName: String): LiveData<ExerciseInfoObject?> {
        return exerciseInfoDao.getExerciseWithTrainingName(exName, exTrName)
    }

    fun count(exName: String, exTrName: String): LiveData<Int> {
        return exerciseInfoDao.count(exName, exTrName)
    }

    suspend fun insertExerciseInfoAsync(exerciseInfoObject: ExerciseInfoObject) {
        exerciseInfoDao.insertExercise(exerciseInfoObject)
    }

    suspend fun updateExerciseInfoAsync(exerciseInfoObject: ExerciseInfoObject) {
        exerciseInfoDao.updateExercise(exerciseInfoObject)
    }

    suspend fun deleteExerciseInfoAsync(exerciseInfoObject: ExerciseInfoObject) {
        exerciseInfoDao.deleteExercise(exerciseInfoObject)
    }
}