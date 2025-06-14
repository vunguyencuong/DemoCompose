package com.vunguyencuong.democompose.navigation

import com.vunguyencuong.democompose.R

sealed class NavigationItem(
    val route: String,
    val icon: Int,
    val iconSelected: Int,
    val title: String) {

    object Home: NavigationItem(
        "home",
        R.drawable.ic_home_unselected,
        R.drawable.ic_home_selected,
        "Home"
    )
    object Library: NavigationItem(
        "library",
        R.drawable.ic_library_unselected,
        R.drawable.ic_library_selected,
         "Library")
    object Settings: NavigationItem(
        "settings",
        R.drawable.ic_setting_unselected,
        R.drawable.ic_setting_selected,
        "Settings")

}