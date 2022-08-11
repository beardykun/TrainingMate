package com.example.trainingmate.ui.addExercise

import android.os.Parcelable
import com.example.trainingmate.dataBase.objects.ExerciseObject
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddExerciseListItem(val exerciseObject: ExerciseObject, var isSelected: Boolean) :
    Parcelable