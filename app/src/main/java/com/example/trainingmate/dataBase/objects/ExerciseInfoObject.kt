package com.example.trainingmate.dataBase.objects

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.trainingmate.dataBase.DatabaseContract
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = DatabaseContract.TABLE_EXERCISE_INFO)
data class ExerciseInfoObject(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = DatabaseContract.ExerciseInfoColumns.EXERCISE_NAME) var exerciseName: String,
    @ColumnInfo(name = DatabaseContract.ExerciseInfoColumns.EXERCISE_TRAINING_NAME) var exerciseTrainingName: String,
    @ColumnInfo(name = DatabaseContract.ExerciseInfoColumns.EXERCISE_REPS) var exerciseReps: Int,
    @ColumnInfo(name = DatabaseContract.ExerciseInfoColumns.EXERCISE_MAX_WEIGHT) var exerciseMaxWeight: Float
) : Parcelable