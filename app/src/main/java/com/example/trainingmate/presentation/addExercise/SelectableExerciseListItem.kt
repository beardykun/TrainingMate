package com.example.trainingmate.presentation.addExercise

import android.os.Parcelable
import com.example.trainingmate.data.dataBase.objects.ExerciseObject
import kotlinx.parcelize.Parcelize

@Parcelize
data class SelectableExerciseListItem(val exerciseObject: ExerciseObject, var isSelected: Boolean) :
    Parcelable