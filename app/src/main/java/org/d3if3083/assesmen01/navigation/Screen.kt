package org.d3if3083.assesmen01.navigation

import android.health.connect.datatypes.ExerciseRoute

sealed class Screen(val route: String){
    data object Home : Screen("mainScreen")
}