package com.example.trainingmate.ui.trainingDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.trainingmate.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.dataBase.objects.ExerciseInfoObject
import com.example.trainingmate.dataBase.objects.ExerciseObject
import com.example.trainingmate.dataBase.objects.TrainingObject
import com.example.trainingmate.utils.Utils.getTrainingNameWithDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TrainingDetailsViewModel @Inject constructor(private val dbViewModel: DBViewModel) :
    ViewModel() {

    fun addCurrentExerciseInfoObject(
        exerciseObject: ExerciseObject,
        trainingObject: TrainingObject
    ) {
        viewModelScope.launch {
            val count = withContext(Dispatchers.IO) {
                dbViewModel.count(
                    exerciseObject.exerciseName,
                    getTrainingNameWithDate(trainingObject.trainingName)
                )
            }

            count.asFlow().flowOn(Dispatchers.IO).collect {
                if (it == 0) {
                    dbViewModel.insertExerciseInfo(
                        ExerciseInfoObject(
                            id = null,
                            exerciseName = exerciseObject.exerciseName,
                            exerciseTrainingName = getTrainingNameWithDate(trainingObject.trainingName),
                            exerciseSet = listOf(),
                            exerciseMaxWeight = 0.0f
                        )
                    )
                }
            }
        }
    }
}