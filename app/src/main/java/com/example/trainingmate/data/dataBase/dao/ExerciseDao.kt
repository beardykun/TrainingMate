package com.example.trainingmate.data.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.trainingmate.data.dataBase.objects.ExerciseObject

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM table_exercise ORDER BY exerciseName ASC")
    fun getAllExercises(): LiveData<List<ExerciseObject>>

    @Query("SELECT * FROM table_exercise WHERE exerciseGroup LIKE :exGroup ORDER BY exerciseName ASC")
    fun getAllExercisesWithGroup(exGroup: String): LiveData<List<ExerciseObject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exerciseObject: ExerciseObject)

    @Update
    suspend fun updateExercise(exerciseObject: ExerciseObject)

    @Delete
    suspend fun deleteExercise(exerciseObject: ExerciseObject)

    @Query("SELECT * FROM table_exercise WHERE exerciseName LIKE :exName")
    fun getExerciseWithName(exName: String): LiveData<ExerciseObject>
}