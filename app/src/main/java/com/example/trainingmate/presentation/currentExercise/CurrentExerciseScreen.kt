package com.example.trainingmate.presentation.currentExercise

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trainingmate.data.dataBase.objects.ExerciseObject
import com.example.trainingmate.data.dataBase.objects.ExerciseSet
import com.example.trainingmate.data.dataBase.objects.TrainingObject
import com.example.trainingmate.presentation.commonComposables.ScaffordWithAppBar
import com.example.trainingmate.presentation.destinations.TrainingsDetailDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun CurrentExerciseScreen(
    navigator: DestinationsNavigator,
    exerciseObject: ExerciseObject,
    trainingObject: TrainingObject
) {
    val viewModel: CurrentExerciseViewModel = hiltViewModel()

    viewModel.getCurrentExerciseInfoObject(exerciseObject, trainingObject)

    ScaffordWithAppBar(appBarTitle = exerciseObject.exerciseName,
        navigate = { navigator.navigate(TrainingsDetailDestination(trainingObject)) },
        content = {
            val editReps = remember { mutableStateOf("") }
            val editWeight = remember { mutableStateOf("") }

            Column {
                Row(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = editWeight.value,
                        onValueChange = {
                            editWeight.value = it
                        },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal).copy(
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    TextField(
                        value = editReps.value,
                        onValueChange = {
                            editReps.value = it
                        },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number).copy(
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }
                ExerciseInfoList(viewModel)
                Button(onClick = {
                    viewModel.updateInfoExercise(
                        reps = editReps.value,
                        weight = editWeight.value
                    )
                }) {
                    Text("Add set")
                }
            }
        })
}

@Composable
fun ExerciseInfoList(viewModel: CurrentExerciseViewModel) {
    val scrollState = rememberLazyListState()
    val eio by viewModel.exerciseInfoObject.collectAsState()
    Log.i("TAGGER", "drawn")
    LazyColumn(
        state = scrollState
    ) {
        items(eio?.exerciseSet?.size ?: 0) { position ->
            ExerciseInforListItem(eio?.exerciseSet?.get(position))
        }
    }
}

@Composable
fun ExerciseInforListItem(set: ExerciseSet?) {
    Text(
        "${set?.weight} x ${set?.reps}",
        modifier = Modifier.fillMaxWidth()
    )
}
