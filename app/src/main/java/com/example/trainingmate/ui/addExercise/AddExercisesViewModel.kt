package com.example.trainingmate.ui.addExercise

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.example.trainingmate.dataBase.objects.ExerciseObject
import com.example.trainingmate.dataBase.objects.TrainingObject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddExercisesViewModel @Inject constructor() : ViewModel() {

    private var _selectedList: List<SelectableExerciseListItem>? = null

    fun getSelectedList(
        list: State<List<ExerciseObject>?>,
        trainingObject: TrainingObject
    ): List<SelectableExerciseListItem>? {
        _selectedList = list.value?.map { exerciseObject ->
            val isSelected =
                trainingObject.trainingExerciseNameList.contains(exerciseObject.exerciseName)
            SelectableExerciseListItem(exerciseObject, isSelected)
        }
        return _selectedList
    }

    fun updateSelectedList(value: List<SelectableExerciseListItem>) {
        _selectedList = value.toList()
    }
}