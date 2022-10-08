package com.example.trainingmate.data.repositories

import android.os.Build
import androidx.lifecycle.asFlow
import com.example.trainingmate.addNewSet
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
        dbViewModel: DBViewModel
    ) {
        dbViewModel.updateInfoExercise(exerciseInfoObject)
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