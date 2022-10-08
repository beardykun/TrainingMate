package com.example.trainingmate

import com.example.trainingmate.dataBase.Constants
import com.example.trainingmate.dataBase.dbViewModels.DBViewModel
import com.example.trainingmate.dataBase.objects.ExerciseObject

fun insertDefaultExercises(dbViewModel: DBViewModel) {
    //biceps
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Barbell curls",
            Constants.BICEPS_GROUP,
            R.mipmap.barbell_curl
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Standing reverse curl",
            Constants.BICEPS_GROUP,
            R.mipmap.standing_reverse_curl
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Standing biceps cable curl",
            Constants.BICEPS_GROUP,
            R.mipmap.standing_biceps_cable_curl
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Overhead cable curl",
            Constants.BICEPS_GROUP,
            R.mipmap.overhead_cable_curl
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Seated dumbbell curls",
            Constants.BICEPS_GROUP,
            R.mipmap.seated_dumbbell_curls
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Dumbbell preacher curl",
            Constants.BICEPS_GROUP,
            R.mipmap.dumbbell_preacher_curl
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Preacher curl",
            Constants.BICEPS_GROUP,
            R.mipmap.preacher_curl
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Concentration curls",
            Constants.BICEPS_GROUP,
            R.mipmap.concentration_curls
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Dumbbell bicep curl",
            Constants.BICEPS_GROUP,
            R.mipmap.dumbbell_bicep_curl
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Dumbbell alternate bicep curl",
            Constants.BICEPS_GROUP,
            R.mipmap.dumbbell_alternate_bicep_curl
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Cross body hammer curl",
            Constants.BICEPS_GROUP,
            R.mipmap.cross_body_hammer_curl
        )
    )
    //triceps
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Lying Triceps Press",
            Constants.TRICEPS_GROUP,
            R.mipmap.lying_triceps_press
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Standing triceps press",
            Constants.TRICEPS_GROUP,
            R.mipmap.standing_triceps_press
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Triceps dumbbell kickback",
            Constants.TRICEPS_GROUP,
            R.mipmap.tricep_dumbbell_kickback
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Triceps push-down",
            Constants.TRICEPS_GROUP,
            R.mipmap.triceps_pushdown
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Push ups close triceps position",
            Constants.TRICEPS_GROUP,
            R.mipmap.push_ups_close_triceps_position
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Dips triceps",
            Constants.TRICEPS_GROUP,
            R.mipmap.dips_triceps
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Close grip barbell press",
            Constants.TRICEPS_GROUP,
            R.mipmap.close_grip_barbell_press
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Cable rope overhead extension",
            Constants.TRICEPS_GROUP,
            R.mipmap.cable_rope_overhead_extention
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Bench dips",
            Constants.TRICEPS_GROUP,
            R.mipmap.bench_dips
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Cable one arm triceps extension",
            Constants.TRICEPS_GROUP,
            R.mipmap.cable_one_arm_triceps_extension
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Dumbbell one arm triceps extension",
            Constants.TRICEPS_GROUP,
            R.mipmap.dumbbell_one_arm_triceps_extension
        )
    )

    //Shoulders
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Barbell Shoulder Press",
            Constants.SHOULDERS_GROUP,
            R.mipmap.barbell_shoulder_press
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Shoulder Press",
            Constants.SHOULDERS_GROUP,
            R.mipmap.shoulder_press
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Reverse machine flyes",
            Constants.SHOULDERS_GROUP,
            R.mipmap.reverse_machine_flyes
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Front dumbbell raise",
            Constants.SHOULDERS_GROUP,
            R.mipmap.front_dumbbell_raise
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Side lateral raise",
            Constants.SHOULDERS_GROUP,
            R.mipmap.side_lateral_raise
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Dumbbell shoulder press",
            Constants.SHOULDERS_GROUP,
            R.mipmap.dumbbell_shoulder_press
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Cable rear delt fly",
            Constants.SHOULDERS_GROUP,
            R.mipmap.cable_rear_delt_fly
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Bent over dumbbell rear delt raise",
            Constants.SHOULDERS_GROUP,
            R.mipmap.bent_over_dumbbell_rear_delt_raise
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Barbell rea delt rows",
            Constants.SHOULDERS_GROUP,
            R.mipmap.barbell_rea_delt_rows
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Arnold dumbbell press",
            Constants.SHOULDERS_GROUP,
            R.mipmap.arnold_dumbbell_press
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Alternating deltoid raise",
            Constants.SHOULDERS_GROUP,
            R.mipmap.alternating_deltoid_raise
        )
    )

    //back
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Chin ups",
            Constants.BACK_GROUP,
            R.mipmap.chin_up
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Reverse grip bent over rows",
            Constants.BACK_GROUP,
            R.mipmap.reverse_grip_bent_over_rows
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Lying T-bar row",
            Constants.BACK_GROUP,
            R.mipmap.lying_t_bar_row
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Close grip front lat pull-down",
            Constants.BACK_GROUP,
            R.mipmap.close_grip_front_lat_pulldown
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Bent over barbell row",
            Constants.BACK_GROUP,
            R.mipmap.bent_over_barbell_row
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Wide grip lat pull-down",
            Constants.BACK_GROUP,
            R.mipmap.wide_grip_lat_pulldown
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Underhand cable pull-down",
            Constants.BACK_GROUP,
            R.mipmap.underhand_cable_pulldown
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Wide grip lat pull-down",
            Constants.BACK_GROUP,
            R.mipmap.wide_grip_lat_pulldown
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "One arm dumbbell row",
            Constants.BACK_GROUP,
            R.mipmap.one_arm_dumbbell_row
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Seated cable row",
            Constants.BACK_GROUP,
            R.mipmap.seated_cable_row
        )
    )

    //chest
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Bench Press",
            Constants.CHEST_GROUP,
            R.mipmap.bench_press
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Machine flye",
            Constants.CHEST_GROUP,
            R.mipmap.machine_flye
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Machine bench press",
            Constants.CHEST_GROUP,
            R.mipmap.machine_bench_press
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Dumbbell flyes",
            Constants.CHEST_GROUP,
            R.mipmap.dumbbell_flyes
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Decline dumbbell bench press",
            Constants.CHEST_GROUP,
            R.mipmap.decline_dumbbell_bench_press
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Decline bench press",
            Constants.CHEST_GROUP,
            R.mipmap.decline_bench_press
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Barbell incline bench",
            Constants.CHEST_GROUP,
            R.mipmap.barbell_incline_bench
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Incline dumbbell bench press",
            Constants.CHEST_GROUP,
            R.mipmap.incline_dumbbell_bench_press
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Incline dumbbell flyes",
            Constants.CHEST_GROUP,
            R.mipmap.incline_dumbbell_flyes
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Low cable crossover",
            Constants.CHEST_GROUP,
            R.mipmap.low_cable_crossover
        )
    )

    //legs
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Squats",
            Constants.LEGS_GROUP,
            R.mipmap.squat
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Romanian deadlift from deficit",
            Constants.LEGS_GROUP,
            R.mipmap.romanian_deadlift_from_deficit
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Romanian deadlift",
            Constants.LEGS_GROUP,
            R.mipmap.romanian_deadlift
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Leg press",
            Constants.LEGS_GROUP,
            R.mipmap.leg_press
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Machine squat",
            Constants.LEGS_GROUP,
            R.mipmap.machine_squat
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Leg extensions",
            Constants.LEGS_GROUP,
            R.mipmap.leg_extensions
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Glute ham raise",
            Constants.LEGS_GROUP,
            R.mipmap.glute_ham_raise
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Good mornings",
            Constants.LEGS_GROUP,
            R.mipmap.good_morning
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Barbell squat",
            Constants.LEGS_GROUP,
            R.mipmap.barbell_squat
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Barbell lunge",
            Constants.LEGS_GROUP,
            R.mipmap.barbell_lunge
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Dumbbell lunges",
            Constants.LEGS_GROUP,
            R.mipmap.dumbbell_lunges
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Dumbbell step ups",
            Constants.LEGS_GROUP,
            R.mipmap.dumbbell_step_ups
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Smith machine squat",
            Constants.LEGS_GROUP,
            R.mipmap.smith_machine_squat
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Smith machine front squat",
            Constants.LEGS_GROUP,
            R.mipmap.smith_machine_front_squat
        )
    )

    //abs
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Oblique crunches on the flor",
            Constants.ABS_GROUP,
            R.mipmap.oblique_crunches_on_the_flor
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Legs rise on bars",
            Constants.ABS_GROUP,
            R.mipmap.legs_rise_on_bars
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Flat bench leg pull in",
            Constants.ABS_GROUP,
            R.mipmap.flat_bench_leg_pull_in
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Hanging pike",
            Constants.ABS_GROUP,
            R.mipmap.hanging_pike
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Janda sit up",
            Constants.ABS_GROUP,
            R.mipmap.janda_sit_up
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Knee hip raise on bars",
            Constants.ABS_GROUP,
            R.mipmap.knee_hip_raise_on_bars
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Kneeling rope crunch",
            Constants.ABS_GROUP,
            R.mipmap.kneeling_rope_crunch
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Seated leg tucks",
            Constants.ABS_GROUP,
            R.mipmap.seated_leg_tucks
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Oblique crunches on the flor",
            Constants.ABS_GROUP,
            R.mipmap.oblique_crunches_on_the_flor
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Tuck crunch",
            Constants.ABS_GROUP,
            R.mipmap.tuck_crunch
        )
    )

    //traps
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Barbell shrugs behind the back",
            Constants.TRAPS_GROUP,
            R.mipmap.barbell_shrag_behind_the_back
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Barbell shrug",
            Constants.TRAPS_GROUP,
            R.mipmap.barbell_shrug
        )
    )
    dbViewModel.insertExercise(
        ExerciseObject(
            null,
            "Dumbbell shrug",
            Constants.TRAPS_GROUP,
            R.mipmap.dumbbell_shrug
        )
    )
}