package com.fesenko.calorycalc



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListFood("Zac",1)

        }
    }
}


   @Composable
   private fun FoodList() {
       Box(
           modifier = Modifier
               .fillMaxSize()
               .background(Color(0xffffb6c1))  // Задаем фоновый цвет
       ) {

           LazyColumn {
               items(sampleFoodItems) { item -> Row () {(Text(text = "${item.name} - ${item.caloriesPer100g} калорий/100г", modifier = Modifier.padding(8.dp)))
                   val checkedState = remember { mutableStateOf(false) }
                   Checkbox(
                       checked = checkedState.value,
                       onCheckedChange = { checkedState.value = it } ,
                       colors  = CheckboxDefaults.colors(checkedColor = Color(0xffffb6c1), checkmarkColor = Color.Green)
                   )
               }
               }
           }
       }

   }

@Preview
@Composable
private fun ListFood(name: String, kal: Int) {

    Card (
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        shape=  RoundedCornerShape(15.dp),

    ){

        Box ( modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
            )

            {
            Text(name)
        }
    }



}



data class FoodItem(val name: String, val caloriesPer100g: Int)

val sampleFoodItems = listOf(
    FoodItem("Яблоко", 52),
    FoodItem("Банан", 89),
    FoodItem("Хлеб", 265),
    FoodItem("Молоко", 42),
    FoodItem("Куриное филе", 165)
)

