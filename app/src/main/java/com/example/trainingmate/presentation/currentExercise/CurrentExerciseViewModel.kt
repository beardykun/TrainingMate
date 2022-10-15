package com.example.trainingmate.presentation.currentExercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingmate.addNewSet
import com.example.trainingmate.data.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.data.dataBase.objects.ExerciseInfoObject
import com.example.trainingmate.data.dataBase.objects.ExerciseObject
import com.example.trainingmate.data.dataBase.objects.TrainingObject
import com.example.trainingmate.domain.useCases.cureentExerciseUseCases.CountdownTimerUseCase
import com.example.trainingmate.domain.useCases.cureentExerciseUseCases.GetCurrentExerciseInfoObjectUseCase
import com.example.trainingmate.domain.useCases.cureentExerciseUseCases.UpdateInfoExerciseUseCase
import com.example.trainingmate.domain.useCases.cureentExerciseUseCases.ValidateInputUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CurrentExerciseViewModel @Inject constructor(
    private val dbViewModel: DBViewModel,
    private val getCurrentExerciseInfoObjectUseCase: GetCurrentExerciseInfoObjectUseCase,
    private val updateInfoExerciseUseCase: UpdateInfoExerciseUseCase,
    private val validateInputUseCase: ValidateInputUseCase,
    private val countdownTimerUseCase: CountdownTimerUseCase
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

    fun updateInfoExercise(reps: String, weight: String, onError: (error: String) -> Unit) {
        viewModelScope.launch {
            val validationOutput = validateInputUseCase.invoke(weight, reps)
            if (validationOutput != "") {
                onError(validationOutput)
                return@launch
            }
            _exerciseInfoObject.value?.addNewSet(weight, reps)
                ?.let {
                    withContext(Dispatchers.IO) {
                        updateInfoExerciseUseCase.invoke(it, dbViewModel, onError)
                    }
                }
        }
    }

    fun startCountdownTimer(time: Int) {
        viewModelScope.launch {
            countdownTimerUseCase.invoke(time).collect()
        }
    }
}