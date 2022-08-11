package com.example.trainingmate.ui.trainingDetails

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.trainingmate.dataBase.objects.TrainingObject
import com.example.trainingmate.ui.CommonLazyListString
import com.example.trainingmate.ui.ScaffordWithAppBar
import com.example.trainingmate.ui.destinations.AddExerciseScreenDestination
import com.example.trainingmate.ui.destinations.TrainingsMainDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun TrainingsDetail(navigator: DestinationsNavigator, trainingObject: TrainingObject) {
    ScaffordWithAppBar(
        appBarTitle = "Training Detail Screen",
        navigate = { navigator.navigate(TrainingsMainDestination()) },
        content = {
            val state = rememberLazyListState()
            val list = remember { trainingObject.trainingExerciseNameList }
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