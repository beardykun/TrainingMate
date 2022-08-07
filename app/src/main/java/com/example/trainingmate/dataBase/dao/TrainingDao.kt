package com.example.trainingmate.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.trainingmate.dataBase.objects.TrainingObject

@Dao
interface TrainingDao {

    @Query("SELECT * FROM table_training")
    fun getAllTrainings(): LiveData<List<TrainingObject>>

    @Query("SELECT * FROM table_training WHERE trainingName LIKE :name LIMIT 1")
    fun getTraining(name: String): LiveData<TrainingObject>

    @Query("SELECT * FROM table_training WHERE trainingNameWithDate LIKE :name LIMIT 1")
    fun getTrainingWithDate(name: String): LiveData<TrainingObject>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTraining(trainingObject: TrainingObject)

    @Update
    suspend fun updateTraining(trainingObject: TrainingObject)

    @Delete
    suspend fun deleteTraining(trainingObject: TrainingObject)
}