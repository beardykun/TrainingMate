package com.example.trainingmate.presentation.currentExercise

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trainingmate.data.dataBase.objects.ExerciseObject
import com.example.trainingmate.data.dataBase.objects.ExerciseSet
import com.example.trainingmate.data.dataBase.objects.TrainingObject
import com.example.trainingmate.presentation.commonComposables.ScaffordWithAppBar
import com.example.trainingmate.presentation.commonComposables.texts.MateTextFieldDouble
import com.example.trainingmate.presentation.commonComposables.texts.MateTextFieldInt
import com.example.trainingmate.presentation.destinations.TrainingsDetailDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun CurrentExerciseScreen(
    navigator: DestinationsNavigator,
    exerciseObject: ExerciseObject,
    trainingObject: TrainingObject,
    modifier: Modifier = Modifier
) {
    val viewModel: CurrentExerciseViewModel = hiltViewModel()

    viewModel.getCurrentExerciseInfoObject(exerciseObject, trainingObject)

    ScaffordWithAppBar(appBarTitle = exerciseObject.exerciseName,
        navigate = { navigator.navigate(TrainingsDetailDestination(trainingObject)) },
        content = {
            val editReps = remember { mutableStateOf("") }
            val editWeight = remember { mutableStateOf("") }

            Column(modifier = modifier.padding(horizontal = 16.dp)) {
                Row(
                    modifier = modifier.padding(16.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MateTextFieldDouble(editWeight, modifier.weight(1f))
                    Spacer(modifier = modifier.width(16.dp))
                    MateTextFieldInt(editReps, modifier.weight(1f))
                }
                ExerciseInfoList(viewModel, modifier = modifier)
                val context  = LocalContext.current
                Button(onClick = {
                    viewModel.updateInfoExercise(
                        reps = editReps.value,
                        weight = editWeight.value,
                        onError = {
                            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        }
                    )
                }, modifier = modifier.align(Alignment.CenterHorizontally)) {
                    Text("Add set")
                }
            }
        })
}

@Composable
fun ExerciseInfoList(viewModel: CurrentExerciseViewModel, modifier: Modifier) {
    val scrollState = rememberLazyGridState()
    val eio by viewModel.exerciseInfoObject.collectAsState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        state = scrollState
    ) {
        items(eio?.exerciseSet?.size ?: 0) { position ->
            ExerciseInforListItem(eio?.exerciseSet?.get(position), modifier)
        }
    }
}

@Composable
fun ExerciseInforListItem(set: ExerciseSet?, modifier: Modifier) {
    Text(
        "${set?.weight} x ${set?.reps}",
        modifier = modifier.fillMaxWidth().padding(vertical = 16.dp)
    )
}
