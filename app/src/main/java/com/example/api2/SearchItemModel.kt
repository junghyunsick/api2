package com.example.api2

data class SearchItemModel(
    var tilte: String,
    var dataTime: String,
    val url:String,
    val isLike:Boolean = false)