package com.example.my_whishlist_app

sealed class Screen (val route:String){
    object HomeScreen:Screen("home_screen")
    object AddScreen:Screen("Add_screen")
}