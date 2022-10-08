package com.example.trainingmate.domain.useCases.trainingDetailsUseCases

import com.example.trainingmate.data.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.data.dataBase.objects.ExerciseObject
import com.example.trainingmate.data.dataBase.objects.TrainingObject
import com.example.trainingmate.domain.repositories.ITrainingDetailsRepository

class AddCurrentExerciseInfoObjectUseCase(private val repository: ITrainingDetailsRepository) {

    suspend fun invoke(
        exerciseObject: ExerciseObject,
        trainingObject: TrainingObject,
        dbViewModel: DBViewModel
    ) {
        repository.addCurrentExerciseInfoObject(exerciseObject, trainingObject, dbViewModel)
    }
}