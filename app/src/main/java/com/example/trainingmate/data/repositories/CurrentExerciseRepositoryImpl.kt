package com.example.trainingmate.data.repositories

import android.os.Build
import androidx.lifecycle.asFlow
import com.example.trainingmate.data.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.data.dataBase.objects.ExerciseInfoObject
import com.example.trainingmate.data.dataBase.objects.ExerciseObject
import com.example.trainingmate.data.dataBase.objects.TrainingObject
import com.example.trainingmate.domain.repositories.ICurrentExerciseRepository
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object CurrentExerciseRepositoryImpl : ICurrentExerciseRepository {

    override suspend fun getCurrentExerciseInfoObject(
        exerciseObject: ExerciseObject,
        trainingObject: TrainingObject, dbViewModel: DBViewModel
    ): Flow<ExerciseInfoObject?> =
        dbViewModel.getExerciseInfoWithName(
            exerciseObject.exerciseName,
            getTrainingNameWithDate(trainingObject.trainingName)
        ).asFlow()

    override suspend fun updateInfoExercise(
        exerciseInfoObject: ExerciseInfoObject,
        dbViewModel: DBViewModel,
        onError: (error: String) -> Unit
    ) {
        dbViewModel.updateInfoExercise(exerciseInfoObject)
    }

    override suspend fun validateInput(weight: String, reps: String): String {
        return if (weight == "0.0" || weight == "0") {
            "Weight can't be 0"
        } else if (weight.contains(",")) {
            "Please use '.' instead of ',' for weight"
        } else if (reps.contains(",") || reps.contains(".")) {
            "reps should not be a floating point number"

        } else if (reps == "0") {
            "Reps should not be 0"
        } else ""
    }


    private fun getTrainingNameWithDate(name: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            name + current.format(formatter)
        } else {
            val date = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.UK)
            name + dateFormat.format(date)
        }
    }
}