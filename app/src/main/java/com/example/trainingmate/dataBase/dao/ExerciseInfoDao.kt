package com.example.trainingmate.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.trainingmate.dataBase.objects.ExerciseInfoObject

@Dao
interface ExerciseInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exerciseInfoObject: ExerciseInfoObject)

    @Update
    suspend fun updateExercise(exerciseInfoObject: ExerciseInfoObject)

    @Delete
    suspend fun deleteExercise(exerciseInfoObject: ExerciseInfoObject)

    @Query("SELECT * FROM table_exercise_info WHERE exerciseName LIKE :exName AND exerciseTrainingName LIKE :exTrName")
    fun getExerciseWithTrainingName(exName: String, exTrName: String): LiveData<ExerciseInfoObject>
}