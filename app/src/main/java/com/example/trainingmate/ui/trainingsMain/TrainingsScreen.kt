package com.example.trainingmate.ui.trainingsMain

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trainingmate.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.ui.CommonLazyListTrainingObject
import com.example.trainingmate.ui.destinations.TrainingsDetailDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun TrainingsMain(navigator: DestinationsNavigator) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val state = rememberLazyListState()
        val dbViewModel: DBViewModel = hiltViewModel()
        val list = dbViewModel.getTrainings().observeAsState()

        CommonLazyListTrainingObject(state, list) { training ->
            navigator.navigate(TrainingsDetailDestination(training))
        }
    }
}