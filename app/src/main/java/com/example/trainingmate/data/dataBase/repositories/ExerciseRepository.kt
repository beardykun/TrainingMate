package com.example.trainingmate.data.dataBase.repositories

import androidx.lifecycle.LiveData
import com.example.trainingmate.data.dataBase.dao.ExerciseDao
import com.example.trainingmate.data.dataBase.objects.ExerciseObject
import javax.inject.Inject

class ExerciseRepository @Inject constructor(private val exerciseDao: ExerciseDao) {

    fun getAllExercises(): LiveData<List<ExerciseObject>> {
        return exerciseDao.getAllExercises()
    }

    fun getExerciseWithName(exName: String): LiveData<ExerciseObject> {
        return exerciseDao.getExerciseWithName(exName)
    }

    fun getAllExerciseWithGroup(exGroup: String): LiveData<List<ExerciseObject>> {
        return exerciseDao.getAllExercisesWithGroup(exGroup)
    }

    suspend fun insertExerciseAsync(exerciseObject: ExerciseObject) {
        exerciseDao.insertExercise(exerciseObject)
    }

    suspend fun updateExerciseAsync(exerciseObject: ExerciseObject) {
        exerciseDao.updateExercise(exerciseObject)
    }

    suspend fun deleteExerciseAsync(exerciseObject: ExerciseObject) {
        exerciseDao.deleteExercise(exerciseObject)
    }
}