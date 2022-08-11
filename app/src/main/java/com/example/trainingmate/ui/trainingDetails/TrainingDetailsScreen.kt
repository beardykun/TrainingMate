package com.example.trainingmate.ui.trainingDetails

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trainingmate.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.dataBase.objects.TrainingObject
import com.example.trainingmate.ui.addExercise.SelectableExerciseListItem
import com.example.trainingmate.ui.commonComposables.CommonLazyListString
import com.example.trainingmate.ui.commonComposables.ScaffordWithAppBar
import com.example.trainingmate.ui.destinations.AddExerciseScreenDestination
import com.example.trainingmate.ui.destinations.TrainingsMainDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun TrainingsDetail(navigator: DestinationsNavigator, trainingObject: TrainingObject) {
    val viewModel: TrainingDetailsViewModel = hiltViewModel()
    val dbViewModel: DBViewModel = hiltViewModel()

    val state = rememberLazyListState()
    val allExercises = dbViewModel.getAllExercises().observeAsState()
    allExercises.value?.let {
        val exercises = mutableListOf<SelectableExerciseListItem>()
        allExercises.value?.forEach { ex ->
            if (trainingObject.trainingExerciseNameList.contains(ex.exerciseName))
                exercises.add(
                    SelectableExerciseListItem(ex, true)
                )
        }
        val list = remember { exercises }

        ScaffordWithAppBar(
            appBarTitle = "Training Detail Screen",
            navigate = { navigator.navigate(TrainingsMainDestination()) },
            content = {
                if (list.isEmpty()) {
                    Text(
                        "Add some Exercises to your training",
                        modifier = Modifier.fillMaxSize().padding(top = 120.dp)
                            .padding(horizontal = 32.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 30.sp
                    )
                    return@ScaffordWithAppBar
                }
                CommonLazyListString(state, list) {}
            },
            fab = {
                FloatingActionButton(onClick = {
                    navigator.navigate(AddExerciseScreenDestination(trainingObject))
                }, shape = RoundedCornerShape(50)) {
                }
            }
        )
    }
}