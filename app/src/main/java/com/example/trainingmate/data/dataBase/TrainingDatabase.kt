package com.example.trainingmate.data.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.trainingmate.data.dataBase.dao.ExerciseDao
import com.example.trainingmate.data.dataBase.dao.ExerciseInfoDao
import com.example.trainingmate.data.dataBase.dao.TrainingDao
import com.example.trainingmate.data.dataBase.objects.ExerciseInfoObject
import com.example.trainingmate.data.dataBase.objects.ExerciseObject
import com.example.trainingmate.data.dataBase.objects.TrainingExercisesTypeConverter
import com.example.trainingmate.data.dataBase.objects.TrainingObject

@Database(
    entities = [ExerciseObject::class, TrainingObject::class, ExerciseInfoObject::class],
    version = 1
)
@TypeConverters(TrainingExercisesTypeConverter::class, ExerciseInfoObject.ExerciseSetTypeConverter::class)
abstract class TrainingDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao
    abstract fun trainingDao(): TrainingDao
    abstract fun exerciseInfoDao(): ExerciseInfoDao
}