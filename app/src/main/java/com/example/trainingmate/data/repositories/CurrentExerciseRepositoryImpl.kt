package com.example.trainingmate.data.repositories

import android.os.Build
import android.util.Log
import androidx.lifecycle.asFlow
import com.example.trainingmate.data.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.data.dataBase.objects.ExerciseInfoObject
import com.example.trainingmate.data.dataBase.objects.ExerciseObject
import com.example.trainingmate.data.dataBase.objects.TrainingObject
import com.example.trainingmate.domain.repositories.ICurrentExerciseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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
        } else if (weight.isBlank() || reps.isBlank()) {
            "Both fields should not be empty"
        } else if (reps.contains(",") || reps.contains(".")) {
            "reps should not be a floating point number"

        } else if (reps == "0") {
            "Reps should not be 0"
        } else ""
    }

    override fun countdownTimer(time: Int): Flow<String> = flow {
        emit(time.toString())
        var counter = time
        while (counter > 0) {
            delay(1000)
            counter--
            emit(counter.toString())
            Log.i("TAGGER", counter.toString())
        }
    }.flowOn(Dispatchers.Default)

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