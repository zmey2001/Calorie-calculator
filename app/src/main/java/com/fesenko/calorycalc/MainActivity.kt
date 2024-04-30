package com.fesenko.calorycalc



import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fesenko.calorycalc.ui.theme.GreenScafold

class MainActivity : ComponentActivity() {




    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var CaloriesGram by remember {
                mutableStateOf(0)
            }
            Column {
                Scaffold(
                    topBar = {
                        @OptIn(ExperimentalMaterial3Api::class)
                        TopAppBar(
                            title = { Text("Калькулятор калорий", fontSize = 22.sp) },
                            navigationIcon = {
                                IconButton({ }) {
                                    Icon(
                                        Icons.Filled.Menu,
                                        contentDescription = "Меню"
                                    )
                                }
                            },
                            actions = {
                                IconButton({ }) {
                                    Icon(
                                        Icons.Filled.Info,
                                        contentDescription = "О приложении"
                                    )
                                }
                                IconButton({ }) {
                                    Icon(
                                        Icons.Filled.Search,
                                        contentDescription = "Поиск"
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = GreenScafold,
                                titleContentColor = Color.White,
                                navigationIconContentColor = Color.LightGray,
                                actionIconContentColor = Color.Yellow
                            )
                        )
                    },
                    bottomBar = {
                        BottomAppBar(
                            containerColor = GreenScafold,
                            contentColor = Color.Yellow, modifier = Modifier.align(Alignment.CenterHorizontally),
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(), // Заполняем всё доступное пространство
                                contentAlignment = Alignment.Center // Выравнивание содержимого по центру
                            ) {
                                Row {
                                    Spacer(Modifier.weight(1f))
                                    Spacer(Modifier.weight(1f))
                                    Text("Cумма калорий: $CaloriesGram", textAlign = TextAlign.Center, fontSize = 20.sp, modifier = Modifier.padding(top=10.dp), color = Color.White)
                                    Spacer(Modifier.weight(1f))
                                    IconButton( modifier = Modifier.align(Alignment.CenterVertically),
                                        onClick = {
                                            CaloriesGram = calculateTotalCalories(sampleFood)
                                        }
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.svgviewer_output),
                                            contentDescription = "Избранное"
                                        )

                                    }
                                }
                                //

                            }
                        }
                    }
                ) {

                    LazyColumn( modifier= Modifier.padding(top=70.dp)

                    ) {

                        itemsIndexed(sampleFood)
                        { index, item ->
                            ListFood(
                                item
                            )
                        }
                    }
//
                }
            }
        }

    }

}



//карточка продуктов



val sampleFood = mutableListOf(
    ProductsList("Огурец",R.drawable.cucumber, 100, isСhecked=false, numberOfGrams=0),
    ProductsList("Помидор",R.drawable.tomato, 200, isСhecked=false, numberOfGrams=0),
            ProductsList("Помидор",R.drawable.tomato, 200, isСhecked=false, numberOfGrams=0),
    ProductsList("Помидор",R.drawable.tomato, 200, isСhecked=false, numberOfGrams=0),ProductsList("Помидор",R.drawable.tomato, 200, isСhecked=false, numberOfGrams=0),
    ProductsList("Помидор",R.drawable.tomato, 200, isСhecked=false, numberOfGrams=0)
    ,
    ProductsList("Помидор",R.drawable.tomato, 200, isСhecked=false, numberOfGrams=0)
)

fun calculateTotalCalories(sampleFoodItems: List<ProductsList>): Int {
    return sampleFoodItems.filter { it.isСhecked}  // Фильтрация только отмеченных элементов
        .sumOf {(it.caloriesPer100g*it.numberOfGrams)/100}     // Подсчёт суммы калорий отмеченных элементов
}