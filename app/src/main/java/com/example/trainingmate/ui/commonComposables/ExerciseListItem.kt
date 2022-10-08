package com.example.trainingmate.ui.commonComposables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.trainingmate.ui.addExercise.SelectableExerciseListItem

@Composable
fun ExerciseListSelectableItem(
    modifier: Modifier,
    items: List<SelectableExerciseListItem>,
    onItemClick: (Int) -> Unit,
    i: Int,
    listItem: SelectableExerciseListItem
) {
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
            modifier.size(48.dp)
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

@Composable
fun ExerciseListItem(
    modifier: Modifier,
    onItemClick: (SelectableExerciseListItem) -> Unit,
    listItem: SelectableExerciseListItem
) {
    Row(
        modifier = modifier.fillMaxWidth().clickable {
            onItemClick(listItem)
        }.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        val icon = listItem.exerciseObject.exerciseImage
        Image(
            painter = rememberAsyncImagePainter(
                model = icon
            ),
            contentDescription = "Exercise Icon",
            modifier.size(48.dp)
        )
        Text(
            text = listItem.exerciseObject.exerciseName,
            textAlign = TextAlign.Start,
        )
    }
}

