package com.example.trainingmate.ui.trainingDetails

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import com.example.trainingmate.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.dataBase.objects.TrainingObject
import com.example.trainingmate.ui.addExercise.SelectableExerciseListItem
import com.example.trainingmate.ui.commonComposables.CommonLazyListString
import com.example.trainingmate.ui.commonComposables.ScaffordWithAppBar
import com.example.trainingmate.ui.destinations.AddExerciseScreenDestination
import com.example.trainingmate.ui.destinations.CurrentExerciseScreenDestination
import com.example.trainingmate.ui.destinations.TrainingsMainDestination
import com.example.trainingmate.wrapWithSelectable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun TrainingsDetail(navigator: DestinationsNavigator, trainingObject: TrainingObject) {
    val viewModel: TrainingDetailsViewModel = hiltViewModel()
    val dbViewModel: DBViewModel = hiltViewModel()
    var exercises by rememberSaveable { mutableStateOf(listOf<SelectableExerciseListItem>()) }

    val state = rememberLazyListState()
    LaunchedEffect(key1 = exercises) {
        dbViewModel.getAllExercises().asFlow().map { list ->
            list.filter { trainingObject.trainingExerciseNameList.contains(it.exerciseName) }
        }.collect { ex ->
            exercises = ex.wrapWithSelectable()
        }
    }
    ScaffordWithAppBar(
        appBarTitle = "Training Detail Screen",
        navigate = { navigator.navigate(TrainingsMainDestination()) },
        content = {
            if (exercises.isEmpty()) {
                Text(
                    "Add some Exercises to your training",
                    modifier = Modifier.fillMaxSize().padding(top = 120.dp)
                        .padding(horizontal = 32.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )
                return@ScaffordWithAppBar
            }
            CommonLazyListString(state, exercises) {
                viewModel.addCurrentExerciseInfoObject(it.exerciseObject, trainingObject)
                navigator.navigate(
                    CurrentExerciseScreenDestination(
                        it.exerciseObject,
                        trainingObject
                    )
                )
            }
        },
        fab = {
            FloatingActionButton(onClick = {
                navigator.navigate(AddExerciseScreenDestination(trainingObject))
            }, shape = RoundedCornerShape(10.dp)) {
            }
        }
    )
}