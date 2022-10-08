package com.example.trainingmate.domain.useCases.cureentExerciseUseCases

import com.example.trainingmate.data.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.data.dataBase.objects.ExerciseInfoObject
import com.example.trainingmate.data.dataBase.objects.ExerciseObject
import com.example.trainingmate.data.dataBase.objects.TrainingObject
import com.example.trainingmate.domain.repositories.ICurrentExerciseRepository
import kotlinx.coroutines.flow.Flow

class GetCurrentExerciseInfoObjectUseCase(private val repository: ICurrentExerciseRepository) {

    suspend fun invoke(
        exerciseObject: ExerciseObject,
        trainingObject: TrainingObject, dbViewModel: DBViewModel
    ): Flow<ExerciseInfoObject?> {
        return repository.getCurrentExerciseInfoObject(exerciseObject, trainingObject, dbViewModel)
    }
}