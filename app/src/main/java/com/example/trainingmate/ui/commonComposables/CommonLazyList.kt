package com.example.trainingmate.ui.commonComposables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.trainingmate.dataBase.objects.TrainingObject
import com.example.trainingmate.ui.addExercise.SelectableExerciseListItem

@Composable
fun CommonLazyListString(
    state: LazyListState,
    list: List<SelectableExerciseListItem>,
    onItemClick: (SelectableExerciseListItem) -> Unit
) {
    LazyColumn(state = state) {
        items(list.size) { i ->
            val listItem = list[i]
            ExerciseListItem(Modifier, onItemClick, listItem)
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
    items: List<SelectableExerciseListItem>,
    modifier: Modifier,
    onItemClick: (Int) -> Unit
) {
    LazyColumn(state = state, modifier = modifier) {
        items(items.size) { i ->
            val listItem = items[i]
            ExerciseListSelectableItem(modifier, items, onItemClick, i, listItem)
        }
    }
}


