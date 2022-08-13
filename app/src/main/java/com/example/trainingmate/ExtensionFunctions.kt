package com.example.trainingmate

import com.example.trainingmate.dataBase.objects.ExerciseObject
import com.example.trainingmate.dataBase.objects.TrainingObject
import com.example.trainingmate.ui.addExercise.SelectableExerciseListItem

fun List<ExerciseObject>.wrapWithSelectable(): List<SelectableExerciseListItem> {
    val selectableItemsList = mutableListOf<SelectableExerciseListItem>()
    this.forEach {
        selectableItemsList.add(SelectableExerciseListItem(it, true))
    }
    return selectableItemsList
}

fun List<ExerciseObject>.wrapWithSelectable(trainingObject: TrainingObject): List<SelectableExerciseListItem> {
    val selectableItemsList = mutableListOf<SelectableExerciseListItem>()
    this.forEach {
        selectableItemsList.add(
            SelectableExerciseListItem(
                it,
                trainingObject.trainingExerciseNameList.contains(it.exerciseName)
            )
        )
    }
    return selectableItemsList
}