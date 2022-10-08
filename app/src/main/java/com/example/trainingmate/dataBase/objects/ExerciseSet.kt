package com.example.trainingmate.dataBase.objects

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExerciseSet(val weight: Float, val reps: Int) : Parcelable