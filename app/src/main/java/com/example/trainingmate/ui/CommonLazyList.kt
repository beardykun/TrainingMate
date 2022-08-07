package com.example.trainingmate.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.example.trainingmate.dataBase.objects.ExerciseObject
import com.example.trainingmate.dataBase.objects.TrainingObject

@Composable
fun CommonLazyListString(state: LazyListState, list: List<String>, onItemClick: () -> Unit) {
    LazyColumn(state = state) {
        items(list.size) {
            Text(
                list[it],
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().clickable {
                }
            )
        }
    }
}

@Composable
fun CommonLazyListTrainingObject(
    state: LazyListState,
    list: State<List<TrainingObject>?>,
    onItemClick: (TrainingObject) -> Unit
) {
    LazyColumn(state = state) {
        items(list.value?.size ?: 0) {
            Text(
                "${list.value?.get(it)?.trainingName}",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().clickable {
                    list.value?.get(it)
                        ?.let { training ->
                           onItemClick(training)
                        }
                }
            )
        }
    }
}

@Composable
fun CommonLazyListExerciseObject(
    state: LazyListState,
    list: State<List<ExerciseObject>?>,
    modifier: Modifier,
    onItemClick: (ExerciseObject) -> Unit
) {
    LazyColumn(state = state) {
        items(list.value?.size ?: 0) {
            val item = list.value?.get(it)
            Row {
                /*AsyncImage(model = item?.exerciseImage,
                contentDescription = "Exercise Icon")*/
                Text(
                    "${list.value?.get(it)?.exerciseName}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().clickable {
                        item?.let { exercise ->
                                onItemClick(exercise)
                            }
                    }
                )
            }
        }
    }
}