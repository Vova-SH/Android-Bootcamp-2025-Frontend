package ru.sicampus.bootcamp2025.ui.navigation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sicampus.bootcamp2025.R

@Preview(showBackground = true)
@Composable
fun PreviewRegistrationScreenScreen() {
    RegistrationScreen()
}


@Composable
fun RegistrationScreen() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceAround) {
        Row(
            modifier = Modifier.fillMaxSize().weight(1.3f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "Volunteer",
                fontSize = 40.sp,
                color = Color.Red)
        }

        Row(
            modifier = Modifier.fillMaxSize().padding(start = 20.dp).weight(0.8f) ){
            Icon(painter = painterResource(id = R.drawable.arrow),
                contentDescription = "Описание изображения")
        }

        Row(
            modifier = Modifier.fillMaxSize().weight(0.9f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(text = "Регистрация", fontSize = 30.sp)
        }

        Column(modifier = Modifier.fillMaxSize().weight(7f)) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(20.dp)
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(16.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            )
            {
                Column() {
                    Text(
                        modifier = Modifier.padding(vertical = 10.dp),
                        fontSize = 16.sp,
                        text = "Электронная почта"
                    )

                    TextField(
                        modifier = Modifier.padding(bottom = 10.dp),
                        value = "Введите почту",
                        onValueChange = {}
                    )
                }

                Column {
                    Text(
                        modifier = Modifier.padding(vertical = 10.dp),
                        fontSize = 16.sp,
                        text = "Пароль"
                    )

                    TextField(value = "Введите пароль", onValueChange = {})
                }

                Row(modifier = Modifier.padding(end = 30.dp, top = 10.dp),){
                    Checkbox(
                        checked = true,
                        onCheckedChange = {},
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.Black,
                            uncheckedColor = Color.White,
                            checkmarkColor = Color.White)
                    )
                    Text(modifier = Modifier.padding(top = 15.dp),fontSize = 16.sp, text = "Обработка данных")
                }

            }

        }

        Box(modifier = Modifier.fillMaxSize().weight(2f)) {
            Button(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                onClick = {},

                )
            {
                Text(text = "Зарегистрироваться")
            }

        }

        Box(modifier = Modifier.fillMaxSize().weight(6f)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.autumn),
                contentDescription = "Описание изображения",
            )
        }

    }
}