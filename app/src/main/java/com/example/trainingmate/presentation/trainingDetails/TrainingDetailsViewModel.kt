package com.example.trainingmate.presentation.trainingDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingmate.data.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.data.dataBase.objects.ExerciseObject
import com.example.trainingmate.data.dataBase.objects.TrainingObject
import com.example.trainingmate.domain.useCases.trainingDetailsUseCases.AddCurrentExerciseInfoObjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainingDetailsViewModel @Inject constructor(
    private val dbViewModel: DBViewModel,
    private val addCurrentExerciseInfoObjectUseCase: AddCurrentExerciseInfoObjectUseCase
) :
    ViewModel() {

    fun addCurrentExerciseInfoObject(
        exerciseObject: ExerciseObject,
        trainingObject: TrainingObject
    ) {
        viewModelScope.launch {
            addCurrentExerciseInfoObjectUseCase.invoke(
                exerciseObject,
                trainingObject,
                dbViewModel
            )
        }
    }
}