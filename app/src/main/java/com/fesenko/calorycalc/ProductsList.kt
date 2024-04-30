package com.fesenko.calorycalc

data class ProductsList(
    val name: String, val srcFile: Int, var caloriesPer100g: Int, var isСhecked: Boolean=false, var numberOfGrams: Int=0, val about: String=""
)
