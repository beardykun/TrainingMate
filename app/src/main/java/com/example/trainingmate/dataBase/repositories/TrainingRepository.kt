package com.example.trainingmate.dataBase.repositories

import androidx.lifecycle.LiveData
import com.example.trainingmate.dataBase.dao.TrainingDao
import com.example.trainingmate.dataBase.objects.TrainingObject
import javax.inject.Inject

class TrainingRepository @Inject constructor(private val trainingDao: TrainingDao) {

    fun returnAllTrainings(): LiveData<List<TrainingObject>> {
        return trainingDao.getAllTrainings()
    }

    suspend fun insertTrainingAsync(trainingObject: TrainingObject) {
        trainingDao.insertTraining(trainingObject)
    }

    suspend fun updateTrainingAsync(trainingObject: TrainingObject) {
        trainingDao.updateTraining(trainingObject)
    }

    suspend fun deleteTrainingAsync(trainingObject: TrainingObject) {
        trainingDao.deleteTraining(trainingObject)
    }

    fun getTraining(name: String): LiveData<TrainingObject> {
        return trainingDao.getTraining(name)
    }

    fun getTrainingWithDate(name: String): LiveData<TrainingObject> {
        return trainingDao.getTrainingWithDate(name)
    }
}