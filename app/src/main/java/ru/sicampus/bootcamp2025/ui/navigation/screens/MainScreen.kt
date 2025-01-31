package ru.sicampus.bootcamp2025.ui.navigation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sicampus.bootcamp2025.R

@Preview(showBackground = true)
@Composable
fun PreviewMainScreenScreen() {
    MainScreen()
}


@Composable
fun MainScreen(){
    Column() {
        Row(
            modifier = Modifier.fillMaxSize().weight(2f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(modifier = Modifier,text ="Volunteer",fontSize = 40.sp, color = Color.Red)
        }

        Row(
            modifier = Modifier.fillMaxSize().weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Активные волонтеры",fontSize = 30.sp)
        }

        Column(modifier = Modifier.fillMaxSize().weight(7f)){
            Column(
                modifier = Modifier.fillMaxSize().weight(4f)
                    .padding(20.dp)
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(16.dp)
                    ),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {

                Row(modifier = Modifier.padding(start = 25.dp, top = 30.dp, bottom = 15.dp)){
                    Icon(modifier = Modifier.size(30.dp).padding(end = 5.dp),
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "Описание изображения")
                    Icon(modifier = Modifier.size(30.dp).padding(end = 5.dp),
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "Описание изображения")
                    Icon(modifier = Modifier.size(30.dp).padding(end = 5.dp),
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "Описание изображения")
                    Icon(modifier = Modifier.size(30.dp).padding(end = 5.dp),
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "Описание изображения")
                    Icon(modifier = Modifier.size(30.dp).padding(end = 5.dp),
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "Описание изображения")
                }

                Text(text = "Волонтер",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 25.dp)
                )
                Text(text = "Центральный пункт волонтеров",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 25.dp, top = 5.dp)
                )



                Row(modifier = Modifier.padding(start = 25.dp, top = 25.dp).fillMaxWidth()) {
                    Box(modifier = Modifier.size(50.dp).clip(CircleShape), ){
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(id = R.drawable.avatar),
                            contentDescription = "Описание изображения",
                        )
                    }
                    Column {
                        Text(text = "Greg", Modifier
                            .padding(start = 20.dp, top = 5.dp),
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text ="10.10.2002",
                            modifier = Modifier.padding(start = 19.dp, top = 5.dp),
                            color = Color.Gray)
                    }
//                    Column(Modifier.fillMaxSize()){
//
//                        Text(
//                            modifier = Modifier.size(28.dp),
//                            text = "Greg",
//                            color = Color.DarkGray,
//                            )
//
//                        Text(text = "10.10.2002", color = Color.Gray)
//                    }
                }



            }
            Column(
                modifier = Modifier.fillMaxSize().weight(4f)
                    .padding(20.dp)
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(16.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {}

        }


        Row(
            modifier = Modifier.fillMaxSize().weight(0.7f).background(Color(0xFFD0634B)),
            horizontalArrangement =Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(id = R.drawable.exit),
                contentDescription = "Описание изображения")

            Box(contentAlignment = Alignment.Center){
                Icon(painter = painterResource(id = R.drawable.map),
                    contentDescription = "Описание изображения")
                Icon(modifier = Modifier.padding(bottom = 5.dp),painter = painterResource(id = R.drawable.circleinmap),
                    contentDescription = "Описание изображения")
            }


            Icon(painter = painterResource(id = R.drawable.profile),
                contentDescription = "Описание изображения")

        }

    }
}

