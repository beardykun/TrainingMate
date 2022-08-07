package com.example.trainingmate

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import com.example.trainingmate.dataBase.Constants
import com.example.trainingmate.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.dataBase.objects.ExerciseObject
import com.example.trainingmate.dataBase.objects.TrainingObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val dbViewModel: DBViewModel
) : AndroidViewModel(application) {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "dataStore")

    val EXAMPLE_COUNTER = intPreferencesKey("firstLoad")
    val exampleCounterFlow = getApplication<TMApplication>().applicationContext.dataStore.data
        .map { preferences ->
            // No type safety.
            val firstTime = preferences[EXAMPLE_COUNTER] ?: 0
            if (firstTime == 0) {
                dbViewModel.insertTraining(TrainingObject(null, Constants.DEFAULT_SET))

                putInDB()
                incrementCounter()
            }
        }

    private suspend fun incrementCounter() {
        getApplication<TMApplication>().applicationContext.dataStore.edit { settings ->
            val currentCounterValue = settings[EXAMPLE_COUNTER] ?: 0
            settings[EXAMPLE_COUNTER] = currentCounterValue + 1
        }
    }

    private fun putInDB() {
        Log.i("TAGGER", "putInDB")
        dbViewModel.insertExercise(
            ExerciseObject(
                null,
                "Biceps Curls",
                Constants.BICEPS_GROUP,
                R.drawable.ic_fitness_center_black_24dp.toString()
            )
        )
        dbViewModel.insertExercise(
            ExerciseObject(
                null,
                "Shoulder Press",
                Constants.SHOULDERS_GROUP,
                R.drawable.ic_fitness_center_black_24dp.toString()
            )
        )
        dbViewModel.insertExercise(
            ExerciseObject(
                null,
                "Triceps Press",
                Constants.TRICEPS_GROUP,
                R.drawable.ic_fitness_center_black_24dp.toString()
            )
        )
        dbViewModel.insertExercise(
            ExerciseObject(
                null,
                "Pull ups",
                Constants.BACK_GROUP,
                R.drawable.ic_fitness_center_black_24dp.toString()
            )
        )
        dbViewModel.insertExercise(
            ExerciseObject(
                null,
                "Bench Press",
                Constants.CHEST_GROUP,
                R.drawable.ic_fitness_center_black_24dp.toString()
            )
        )
        dbViewModel.insertExercise(
            ExerciseObject(
                null,
                "Squats",
                Constants.LEGS_GROUP,
                R.drawable.ic_fitness_center_black_24dp.toString()
            )
        )
        dbViewModel.insertExercise(
            ExerciseObject(
                null,
                "Curls",
                Constants.ABS_GROUP,
                R.drawable.ic_fitness_center_black_24dp.toString()
            )
        )
    }
}