package com.gumigames.presentation.util

import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView

fun hideBottomNavigation(bottomNavigationView: BottomNavigationView){
    bottomNavigationView.visibility = View.GONE
}
fun showBottomNavigation(bottomNavigationView: BottomNavigationView){
    bottomNavigationView.visibility = View.VISIBLE
}