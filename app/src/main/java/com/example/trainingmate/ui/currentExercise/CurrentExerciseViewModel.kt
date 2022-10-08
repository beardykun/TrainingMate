package com.example.trainingmate.ui.currentExercise

import android.os.Build
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.trainingmate.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.dataBase.objects.ExerciseInfoObject
import com.example.trainingmate.dataBase.objects.ExerciseObject
import com.example.trainingmate.dataBase.objects.TrainingObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CurrentExerciseViewModel @Inject constructor(private val dbViewModel: DBViewModel) :
    ViewModel() {

    private var _exerciseInfoObject:MutableStateFlow<ExerciseInfoObject?> = MutableStateFlow(null)
    val exerciseInfoObject = _exerciseInfoObject.asStateFlow()

    fun getCurrentExerciseInfoObject(
        exerciseObject: ExerciseObject,
        trainingObject: TrainingObject
    ) {
        viewModelScope.launch {
            dbViewModel.getExerciseInfoWithName(
                exerciseObject.exerciseName,
                getTrainingNameWithDate(trainingObject.trainingName)
            ).asFlow().collectLatest { exerciseObject ->
                Log.i("TAGGER", "got object ${exerciseObject.id}")
                _exerciseInfoObject.value = exerciseObject
            }
        }
    }


    fun getTrainingNameWithDate(name: String): String {
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

    fun updateInfoExercise(exerciseInfoObject: ExerciseInfoObject) {
        dbViewModel.updateInfoExercise(exerciseInfoObject)
    }
}