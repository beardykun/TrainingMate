package com.example.trainingmate.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.trainingmate.ui.addExercise.AddExerciseListItem
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
    items: List<AddExerciseListItem>,
    modifier: Modifier,
    onItemClick: (Int) -> Unit
) {
        LazyColumn(state = state, modifier = modifier) {
            items(items.size) { i ->
                val listItem = items[i]
                Row(
                    modifier = modifier.fillMaxWidth().clickable {
                            onItemClick(i)
                    }.padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    val icon = listItem.exerciseObject.exerciseImage
                    AsyncImage(
                        model = icon,
                        contentDescription = "Exercise Icon",
                        modifier.size(32.dp)
                    )
                    Text(
                        text = listItem.exerciseObject.exerciseName,
                        textAlign = TextAlign.Start,
                    )
                    if (items[i].isSelected) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected",
                            tint = Color.Green,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }


