package com.example.trainingmate.ui.addExercise

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trainingmate.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.ui.CommonLazyListExerciseObject
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterial3Api
@Composable
@Destination
fun AddExerciseScreen(navigator: DestinationsNavigator) {
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        decayAnimationSpec,
        rememberTopAppBarState()
    )
    val viewModel: DBViewModel = viewModel()
    val list = viewModel.getAllExercises().observeAsState()
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text("Add New Exercise") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "home"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        content = { paddingValues ->
            val state = rememberLazyListState()
            CommonLazyListExerciseObject(state, list, Modifier.padding(paddingValues)) {

            }
        })
}
