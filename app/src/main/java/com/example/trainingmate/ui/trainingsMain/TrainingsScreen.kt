package com.example.trainingmate.ui.trainingsMain

import android.app.Activity
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trainingmate.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.ui.CommonLazyListTrainingObject
import com.example.trainingmate.ui.ScaffordWithAppBar
import com.example.trainingmate.ui.destinations.TrainingsDetailDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun TrainingsMain(navigator: DestinationsNavigator) {
    val activity = (LocalContext.current as? Activity)
    ScaffordWithAppBar(
        appBarTitle = "Training Screen",
        navigate = {
            activity?.finish()
        },
        content = {
            val state = rememberLazyListState()
            val dbViewModel: DBViewModel = hiltViewModel()
            val list = dbViewModel.getTrainings().observeAsState()

            CommonLazyListTrainingObject(state, list) { training ->
                navigator.navigate(TrainingsDetailDestination(training))
            }
        })
}