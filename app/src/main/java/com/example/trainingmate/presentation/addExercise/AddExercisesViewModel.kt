package com.example.trainingmate.presentation.addExercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import com.example.trainingmate.R
import com.example.trainingmate.data.dataBase.Constants
import com.example.trainingmate.data.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.data.dataBase.objects.TrainingObject
import com.example.trainingmate.wrapWithSelectable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class AddExercisesViewModel @Inject constructor() : ViewModel() {

    suspend fun getUpdateExerciseList(
        page: Int,
        viewModel: DBViewModel,
        trainingObject: TrainingObject,
        func: (List<SelectableExerciseListItem>) -> Unit
    ) {
        viewModel.getAllExerciseWithGroup(getGroupName(page))
            .asFlow().map {
                it.wrapWithSelectable(trainingObject)
            }.collect {
                func(it)
            }
    }

    fun getGroupName(position: Int): String {
        return when (position) {
            0 -> Constants.BICEPS_GROUP
            1 -> Constants.TRICEPS_GROUP
            2 -> Constants.SHOULDERS_GROUP
            3 -> Constants.BACK_GROUP
            4 -> Constants.CHEST_GROUP
            5 -> Constants.LEGS_GROUP
            6 -> Constants.TRAPS_GROUP
            else -> Constants.ABS_GROUP
        }
    }

    fun getGroupIcon(position: Int): Int {
        return when (position) {
            0 -> R.mipmap.arni_biceps
            1 -> R.mipmap.arni_triceps
            2 -> R.mipmap.arni_shoulders
            3 -> R.mipmap.arni_back
            4 -> R.mipmap.arni_chest
            5 -> R.mipmap.arni_legs
            6 -> R.mipmap.arni_traps
            else ->R.mipmap.arni_abs
        }
    }
}