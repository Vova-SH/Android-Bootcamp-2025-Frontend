package ru.sicampus.bootcamp2025.ui.navigation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sicampus.bootcamp2025.R

@Composable
fun ProfileScreen(onEditClick: () -> Unit,onCancel: () -> Unit) {
    var name = "Анастасия Волочкова"
    var stazh = 5
    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
    ){
        Box(
            modifier = Modifier.fillMaxWidth()
                .height(378.dp)
                .background( color = Color(0xFFC37B5C)),
        ){
            Text(
                text = "Профиль",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.offset(x = 132.dp, y = 56.dp),
                textAlign = TextAlign.Center
            )
            IconButton(
                onClick = onEditClick,
                modifier = Modifier.size(50.dp)
                    .offset(345.dp,42.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.g2151),
                    contentDescription = "Иконка для редактирования профиля",
                    tint = Color.White
                )
            }

            IconButton(
                onClick = onCancel,
                modifier = Modifier.offset(x = 7.dp,y= 38.dp)
                    .size(50.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow),
                    contentDescription = "Иконка выхода",

                )
            }

            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = "Аватар",
                modifier = Modifier.offset(x = 137.dp,y= 115.dp)
                    .width(130.dp)
                    .height(130.dp)
            )
            Text(
                text = name,
                fontSize = 20.sp,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.offset(x = 99.dp, y = 265.dp)
                    .height(28.dp),
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(R.drawable.rectangle_4),
                contentDescription = null,
                modifier = Modifier.offset(x = 121.dp,y= 307.dp)
            )
            Text(
                text = "Стаж "+ stazh + " лет",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                modifier = Modifier.offset(x = 147.dp, y = 315.dp),
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier.offset(x=36.dp,y=409.dp)

        ) {
            InfoItem("Электронная почта","example123@gmail.com")
            InfoItem("Возраст","25 лет")
            InfoItem("О себе", "Информация о себе")
        }

    }

}

@Composable
fun InfoItem(title: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

