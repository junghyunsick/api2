package com.example.api2

data class SearchItemModel(
    var title: String,
    var dateTime: String,
    val url:String,
    var isLike:Boolean = false)