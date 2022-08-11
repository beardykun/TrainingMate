package com.example.trainingmate.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.trainingmate.dataBase.objects.ExerciseObject

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM table_exercise ORDER BY exerciseName ASC")
    fun getAllExercises(): LiveData<List<ExerciseObject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exerciseObject: ExerciseObject)

    @Update
    suspend fun updateExercise(exerciseObject: ExerciseObject)

    @Delete
    suspend fun deleteExercise(exerciseObject: ExerciseObject)

    @Query("SELECT * FROM table_exercise WHERE exerciseName LIKE :exName")
    fun getExerciseWithName(exName: String): LiveData<ExerciseObject>
}