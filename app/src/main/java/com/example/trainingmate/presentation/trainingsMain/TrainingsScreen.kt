package com.example.trainingmate.presentation.trainingsMain

import android.app.Activity
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trainingmate.data.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.presentation.commonComposables.CommonLazyListTrainingObject
import com.example.trainingmate.presentation.commonComposables.ScaffordWithAppBar
import com.example.trainingmate.presentation.destinations.TrainingsDetailDestination
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
        },
    fab = { })
}