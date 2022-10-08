package com.example.trainingmate.domain.repositories

import com.example.trainingmate.data.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.data.dataBase.objects.ExerciseObject
import com.example.trainingmate.data.dataBase.objects.TrainingObject

interface ITrainingDetailsRepository {

    suspend fun addCurrentExerciseInfoObject(
        exerciseObject: ExerciseObject,
        trainingObject: TrainingObject,
        dbViewModel: DBViewModel
    )
}