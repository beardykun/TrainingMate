package com.example.trainingmate.domain.useCases.cureentExerciseUseCases

import com.example.trainingmate.data.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.data.dataBase.objects.ExerciseInfoObject
import com.example.trainingmate.domain.repositories.ICurrentExerciseRepository

class UpdateInfoExerciseUseCase(private val repository: ICurrentExerciseRepository) {

    suspend fun invoke(
        exerciseInfoObject: ExerciseInfoObject,
        dbViewModel: DBViewModel
    ) {
        repository.updateInfoExercise(exerciseInfoObject, dbViewModel)
    }
}