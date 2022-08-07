package com.example.trainingmate.dataBase.dbViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingmate.dataBase.objects.ExerciseInfoObject
import com.example.trainingmate.dataBase.objects.ExerciseObject
import com.example.trainingmate.dataBase.objects.TrainingObject
import com.example.trainingmate.dataBase.repositories.ExerciseInfoRepository
import com.example.trainingmate.dataBase.repositories.ExerciseRepository
import com.example.trainingmate.dataBase.repositories.TrainingRepository
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

    fun getExerciseInfoWithName(name: String, trainingName: String): LiveData<ExerciseInfoObject> {
        return exerciseInfoRepository.getExerciseInfoWithName(name, trainingName)
    }

    fun insertExercise(exerciseInfoObject: ExerciseInfoObject) = viewModelScope.launch {
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