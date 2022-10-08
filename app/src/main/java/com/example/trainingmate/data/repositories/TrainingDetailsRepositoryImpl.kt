package com.example.trainingmate.data.repositories

import androidx.lifecycle.asFlow
import com.example.trainingmate.data.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.data.dataBase.objects.ExerciseInfoObject
import com.example.trainingmate.data.dataBase.objects.ExerciseObject
import com.example.trainingmate.data.dataBase.objects.TrainingObject
import com.example.trainingmate.domain.repositories.ITrainingDetailsRepository
import com.example.trainingmate.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

object TrainingDetailsRepositoryImpl : ITrainingDetailsRepository {

    override suspend fun addCurrentExerciseInfoObject(
        exerciseObject: ExerciseObject,
        trainingObject: TrainingObject,
        dbViewModel: DBViewModel
    ) {
        val count = withContext(Dispatchers.IO) {
            dbViewModel.count(
                exerciseObject.exerciseName,
                Utils.getTrainingNameWithDate(trainingObject.trainingName)
            )
        }

        count.asFlow().flowOn(Dispatchers.IO).collect {
            if (it == 0) {
                dbViewModel.insertExerciseInfo(
                    ExerciseInfoObject(
                        id = null,
                        exerciseName = exerciseObject.exerciseName,
                        exerciseTrainingName = Utils.getTrainingNameWithDate(trainingObject.trainingName),
                        exerciseSet = listOf(),
                        exerciseMaxWeight = 0.0f
                    )
                )
            }
        }
    }
}