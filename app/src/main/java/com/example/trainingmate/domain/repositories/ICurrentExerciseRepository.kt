package com.example.trainingmate.domain.repositories

import com.example.trainingmate.data.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.data.dataBase.objects.ExerciseInfoObject
import com.example.trainingmate.data.dataBase.objects.ExerciseObject
import com.example.trainingmate.data.dataBase.objects.TrainingObject
import kotlinx.coroutines.flow.Flow

interface ICurrentExerciseRepository {

    suspend fun getCurrentExerciseInfoObject(
        exerciseObject: ExerciseObject,
        trainingObject: TrainingObject, dbViewModel: DBViewModel
    ): Flow<ExerciseInfoObject?>

    suspend fun updateInfoExercise(
        exerciseInfoObject: ExerciseInfoObject,
        dbViewModel: DBViewModel,
        onError: (error: String) -> Unit
    )

    suspend fun validateInput(weight: String, reps: String): String

    fun countdownTimer(time: Int): Flow<String>
}