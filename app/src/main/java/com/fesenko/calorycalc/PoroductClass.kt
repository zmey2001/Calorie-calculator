package com.fesenko.calorycalc

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fesenko.calorycalc.ui.theme.GreenBottomTopBar
import com.fesenko.calorycalc.ui.theme.GreenOvalsKal
import com.fesenko.calorycalc.ui.theme.GreenScafold
import com.fesenko.calorycalc.ui.theme.ProductBlock
import kotlinx.coroutines.delay

/* Перегрузка функций для обновлений списка
* Перегрузка  функции для обновления списка выбранных продуктов */
fun UpdateMutList(ProductListMut: ProductsList,isChecked: Boolean){
    ProductListMut.isСhecked=isChecked
}
/* Перегрузка  функции для обновления введённых грамм употреблённого проудкта */
fun UpdateMutList(ProductListMut: ProductsList, numberOfGrams: String){
    ProductListMut.numberOfGrams= numberOfGrams.replace(Regex("[^\\d]"), "").toInt()

}

@Composable
fun ListFood(ProductListMut: ProductsList) {
//Флаг для чекбокса
    var isСhecked by remember {  mutableStateOf(false)
    }
    var counter by remember {
        mutableStateOf(0)
    }
    var СalorieСount by remember {
        mutableStateOf("")
    }

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape=  RoundedCornerShape(20.dp),



        ){
        Box (
            modifier= Modifier.background(Color.Green),

        )

        {


            Row (verticalAlignment = Alignment.CenterVertically,
                modifier= Modifier.background(
                    ProductBlock
                )) {
                Column {

                    Box(
                        modifier = Modifier
                            .padding(start = 1.dp)
                            .clip(CircleShape)
                            .background(GreenOvalsKal)
                            .size(width = 110.dp, height = 30.dp)


                        ){
                        Text(text = "${ProductListMut.caloriesPer100g} кал / 100 г", color= Color.Black, fontWeight =  FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 7.dp)
                        )
                    }
                    Image(painter = painterResource(id = ProductListMut.srcFile ), contentDescription = "image",

                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(start = 15.dp, bottom = 10.dp)
                            .size(width = 64.dp, height = 46.dp)
                            .clip(
                                RoundedCornerShape(
                                    topStart = 0.dp, // Большой радиус для верхнего левого угла
                                    topEnd = 0.dp, // Маленький радиус для верхнего правого угла
                                    bottomEnd = 30.dp, // Большой радиус для нижнего правого угла
                                    bottomStart = 30.dp // Маленький радиус для нижнего левого угла
                                )
                            ),
                    )

                }
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    ){
                    Text(ProductListMut.name)
                    Spacer(modifier = Modifier.weight(1f))


                    OutlinedTextField( modifier = Modifier.padding(start = 10.dp).size(width = 80.dp, height = 50.dp),
                        value = СalorieСount, onValueChange = {newText ->
                            if (newText.all { it.isDigit() && newText !=" "}) {

                                СalorieСount=newText
                                if (newText != "") {
                                    UpdateMutList(ProductListMut,СalorieСount)
                                }

                                Log.d("MyTag", newText)
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                        textStyle = TextStyle(fontSize =  18.sp, color =Color.Black, textAlign = TextAlign.Center),
                        placeholder = { Text("гр.")
                        }
                    )
                    Checkbox(
                        checked = isСhecked,
                        onCheckedChange = {
                            isСhecked=!isСhecked
                            UpdateMutList(ProductListMut,isСhecked)

                        }
                    )

                }
            }
        }
    }
}


