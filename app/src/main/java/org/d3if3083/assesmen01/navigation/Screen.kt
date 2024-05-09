package org.d3if3083.assesmen01.navigation

import android.health.connect.datatypes.ExerciseRoute
import org.d3if3083.assesmen01.ui.screen.KEY_ID_MBTI

sealed class Screen(val route: String) { //sealed memanggil tipe data diri nya sendiri
    data object Home: Screen("mainScreen")
    data object FormBaru : Screen("detailScreen")
    data object FormUbah : Screen("detailScreen/{$KEY_ID_MBTI}") {
        fun withId(id: Long) = "detailScreen/$id"
    }

}