package com.example.trainingmate.data.dataBase.dbViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingmate.data.dataBase.objects.ExerciseInfoObject
import com.example.trainingmate.data.dataBase.objects.ExerciseObject
import com.example.trainingmate.data.dataBase.objects.TrainingObject
import com.example.trainingmate.data.dataBase.repositories.ExerciseInfoRepository
import com.example.trainingmate.data.dataBase.repositories.ExerciseRepository
import com.example.trainingmate.data.dataBase.repositories.TrainingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DBViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
    private val exerciseInfoRepository: ExerciseInfoRepository,
    private val trainingRepository: TrainingRepository
) :
    ViewModel() {

    fun getAllExercises(): LiveData<List<ExerciseObject>> {
        return exerciseRepository.getAllExercises()
    }

    fun getAllExerciseWithGroup(exGroup: String): LiveData<List<ExerciseObject>> {
        return exerciseRepository.getAllExerciseWithGroup(exGroup)
    }

    fun getExerciseWithName(name: String): LiveData<ExerciseObject> {
        return exerciseRepository.getExerciseWithName(name)
    }

    fun insertExercise(exerciseObject: ExerciseObject) = viewModelScope.launch {
        exerciseRepository.insertExerciseAsync(exerciseObject)
    }

    fun deleteExercise(exerciseObject: ExerciseObject) = viewModelScope.launch {
        exerciseRepository.deleteExerciseAsync(exerciseObject)
    }

    fun updateExercise(exerciseObject: ExerciseObject) = viewModelScope.launch {
        exerciseRepository.updateExerciseAsync(exerciseObject)
    }

    fun getExerciseInfoWithName(exerciseName: String, trainingName: String): LiveData<ExerciseInfoObject?> {
        return exerciseInfoRepository.getExerciseInfoWithName(exerciseName, trainingName)
    }

    fun count(exName: String, exTrName: String): LiveData<Int> {
        return exerciseInfoRepository.count(exName, exTrName)
    }

    fun insertExerciseInfo(exerciseInfoObject: ExerciseInfoObject) = viewModelScope.launch {
        exerciseInfoRepository.insertExerciseInfoAsync(exerciseInfoObject)
    }

    fun deleteExercise(exerciseInfoObject: ExerciseInfoObject) = viewModelScope.launch {
        exerciseInfoRepository.deleteExerciseInfoAsync(exerciseInfoObject)
    }

    fun updateInfoExercise(exerciseInfoObject: ExerciseInfoObject) = viewModelScope.launch {
        exerciseInfoRepository.updateExerciseInfoAsync(exerciseInfoObject)
    }

    fun getTrainings(): LiveData<List<TrainingObject>> {
        return trainingRepository.returnAllTrainings()
    }

    fun getTraining(trainingName: String): LiveData<TrainingObject> {
        return trainingRepository.getTraining(trainingName)
    }

    fun getTrainingWithDate(trainingName: String): LiveData<TrainingObject> {
        return trainingRepository.getTrainingWithDate(trainingName)
    }

    fun insertTraining(trainingObject: TrainingObject) = viewModelScope.launch {
        trainingRepository.insertTrainingAsync(trainingObject)
    }

    fun updateTraining(trainingObject: TrainingObject) = viewModelScope.launch {
        trainingRepository.updateTrainingAsync(trainingObject)
    }

    fun deleteTraining(trainingName: String) {
        trainingRepository.getTraining(trainingName)
    }
}