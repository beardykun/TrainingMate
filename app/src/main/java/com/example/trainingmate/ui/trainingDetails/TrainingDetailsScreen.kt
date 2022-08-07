package com.example.trainingmate.ui.trainingDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.trainingmate.dataBase.objects.TrainingObject
import com.example.trainingmate.ui.CommonLazyListString
import com.example.trainingmate.ui.destinations.AddExerciseScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun TrainingsDetail(navigator: DestinationsNavigator, trainingObject: TrainingObject) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navigator.navigate(AddExerciseScreenDestination())
            }, shape = RoundedCornerShape(50)) {
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { padding ->
            Column(modifier = Modifier.fillMaxSize().padding(padding)) {
                val state = rememberLazyListState()
                val list = remember { trainingObject.trainingExerciseNameList }

                CommonLazyListString(state, list) {}
            }
        }
    )
}