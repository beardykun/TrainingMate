package com.example.trainingmate.presentation.currentExercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingmate.addNewSet
import com.example.trainingmate.data.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.data.dataBase.objects.ExerciseInfoObject
import com.example.trainingmate.data.dataBase.objects.ExerciseObject
import com.example.trainingmate.data.dataBase.objects.TrainingObject
import com.example.trainingmate.domain.useCases.cureentExerciseUseCases.GetCurrentExerciseInfoObjectUseCase
import com.example.trainingmate.domain.useCases.cureentExerciseUseCases.UpdateInfoExerciseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentExerciseViewModel @Inject constructor(
    private val dbViewModel: DBViewModel,
    private val getCurrentExerciseInfoObjectUseCase: GetCurrentExerciseInfoObjectUseCase,
    private val updateInfoExerciseUseCase: UpdateInfoExerciseUseCase
) :
    ViewModel() {

    private var _exerciseInfoObject: MutableStateFlow<ExerciseInfoObject?> = MutableStateFlow(null)
    val exerciseInfoObject = _exerciseInfoObject.asStateFlow()

    fun getCurrentExerciseInfoObject(
        exerciseObject: ExerciseObject,
        trainingObject: TrainingObject
    ) {
        viewModelScope.launch {
            getCurrentExerciseInfoObjectUseCase.invoke(exerciseObject, trainingObject, dbViewModel)
                .collectLatest { eio ->
                    eio?.let { eioNonNull ->
                        _exerciseInfoObject.update { eioNonNull.copy() }
                    }
                }
        }
    }

    fun updateInfoExercise(reps: String, weight: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _exerciseInfoObject.value?.addNewSet(reps, weight)
                ?.let { updateInfoExerciseUseCase.invoke(it, dbViewModel) }
        }
    }
}