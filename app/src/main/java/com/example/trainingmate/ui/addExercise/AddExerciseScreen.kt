package com.example.trainingmate.ui.addExercise

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trainingmate.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.dataBase.objects.TrainingObject
import com.example.trainingmate.ui.CommonLazyListExerciseObject
import com.example.trainingmate.ui.ScaffordWithAppBar
import com.example.trainingmate.ui.destinations.TrainingsDetailDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterial3Api
@Composable
@Destination
fun AddExerciseScreen(navigator: DestinationsNavigator, trainingObject: TrainingObject) {

    val viewModel: DBViewModel = hiltViewModel()
    val list = viewModel.getAllExercises().observeAsState()
    val selectedList = list.value?.map { exerciseObject ->
        val isSelected =
            trainingObject.trainingExerciseNameList.contains(exerciseObject.exerciseName)
        AddExerciseListItem(exerciseObject, isSelected)
    }
    selectedList?.let {
        var items by rememberSaveable {
            mutableStateOf(
                selectedList
            )
        }

        ScaffordWithAppBar(
            appBarTitle = "Add New Exercise",
            navigate = {
                val trainingEx = mutableListOf<String>()
                items.forEach {
                    if (it.isSelected)
                        trainingEx.add(it.exerciseObject.exerciseName)
                }
                trainingObject.trainingExerciseNameList = trainingEx
                viewModel.updateTraining(trainingObject)
                navigator.navigate(TrainingsDetailDestination(trainingObject))
            },
            content = {
                val state = rememberLazyListState()

                CommonLazyListExerciseObject(
                    state,
                    items,
                    Modifier.fillMaxHeight()
                ) { i ->
                    items = items.mapIndexed { j, item ->
                        if (i == j) {
                            item.copy(isSelected = !item.isSelected)
                        } else item
                    }
                }
            }
        )
    }
}
