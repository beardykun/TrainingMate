package com.example.trainingmate

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.AndroidViewModel
import com.example.trainingmate.dataBase.Constants
import com.example.trainingmate.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.dataBase.objects.TrainingObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val dbViewModel: DBViewModel,
    private val dataStore: DataStore<Preferences>
) : AndroidViewModel(application) {

    private val EXAMPLE_COUNTER = intPreferencesKey("firstLoad")
    val firstLaunchFlow = dataStore.data
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
        dataStore.edit { settings ->
            val currentCounterValue = settings[EXAMPLE_COUNTER] ?: 0
            settings[EXAMPLE_COUNTER] = currentCounterValue + 1
        }
    }

    private fun putInDB() {
        insertDefaultExercises(dbViewModel)
    }
}