package com.example.trainingmate

import com.example.trainingmate.data.dataBase.objects.ExerciseInfoObject
import com.example.trainingmate.data.dataBase.objects.ExerciseObject
import com.example.trainingmate.data.dataBase.objects.ExerciseSet
import com.example.trainingmate.data.dataBase.objects.TrainingObject
import com.example.trainingmate.presentation.addExercise.SelectableExerciseListItem

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

fun ExerciseInfoObject.addNewSet(weight: String, reps: String): ExerciseInfoObject {
    val list = mutableListOf<ExerciseSet>()
    list.addAll(this.exerciseSet)
    list.add(ExerciseSet(weight.toFloat(), reps.toInt()))
    return this.copy(exerciseSet = list)
}