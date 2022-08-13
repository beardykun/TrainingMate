package com.example.trainingmate.ui.addExercise

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.trainingmate.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.dataBase.objects.TrainingObject
import com.example.trainingmate.ui.commonComposables.CommonLazyListExerciseObject
import com.example.trainingmate.ui.commonComposables.ScaffordWithAppBar
import com.example.trainingmate.ui.destinations.TrainingsDetailDestination
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@ExperimentalMaterial3Api
@Composable
@Destination
fun AddExerciseScreen(navigator: DestinationsNavigator, trainingObject: TrainingObject) {
    val dbViewModel: DBViewModel = hiltViewModel()
    val viewModel: AddExercisesViewModel = hiltViewModel()

    var exercises by rememberSaveable { mutableStateOf(listOf<SelectableExerciseListItem>()) }
    val corScope = rememberCoroutineScope()
    ScaffordWithAppBar(
        appBarTitle = "Add New Exercise",
        navigate = {
            dbViewModel.updateTraining(trainingObject)
            navigator.navigate(TrainingsDetailDestination(trainingObject))
        },
        content = {
            val pagerState = rememberPagerState(initialPage = 0)

            val scope = rememberCoroutineScope()
            val pages = (0 until 8).map { it }

            LaunchedEffect(key1 = pagerState) {
                snapshotFlow { pagerState.currentPage }.collect { page ->
                    corScope.launch {
                        viewModel.getUpdateExerciseList(page, dbViewModel, trainingObject) {
                            exercises = it
                        }
                    }
                }
            }
            Column {
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                        )
                    }
                ) {
                    pages.forEach { index ->
                        Tab(
                            icon = {
                                Image(
                                    painter = rememberAsyncImagePainter(model = viewModel.getGroupIcon(index)),
                                    contentDescription = null
                                )
                            },
                            //text = { Text(text = viewModel.getGroupName(index)) },
                            selected = pagerState.currentPage == index,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                        )
                    }
                }
                HorizontalPager(count = 8, state = pagerState) { page ->


                    val state = rememberLazyListState()

                    CommonLazyListExerciseObject(
                        state,
                        exercises,
                        Modifier.fillMaxHeight()
                    ) { i ->
                        exercises = exercises.mapIndexed { j, item ->
                            if (i == j) {
                                if (trainingObject.trainingExerciseNameList.contains(item.exerciseObject.exerciseName)) {
                                    trainingObject.trainingExerciseNameList.remove(item.exerciseObject.exerciseName)
                                } else {
                                    trainingObject.trainingExerciseNameList.add(item.exerciseObject.exerciseName)
                                }
                                item.copy(isSelected = !item.isSelected)
                            } else item
                        }
                    }
                }
            }
        }
    )
}