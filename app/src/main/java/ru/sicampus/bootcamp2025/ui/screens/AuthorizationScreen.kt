package ru.sicampus.bootcamp2025.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.ui.viewModels.AuthorizationViewModel
import ru.sicampus.bootcamp2025.ui.viewModels.AuthorizationViewModel.State


@Preview(showBackground = true)
@Composable
fun PreviewAuthorizationScreenScreen(){
    AuthorizationScreen()
}


@Composable
fun AuthorizationScreen(viewModel: AuthorizationViewModel = viewModel()){

    val state = viewModel.state.collectAsState().value
    when (state){
        is ru.sicampus.bootcamp2025.ui.viewModels.AuthorizationViewModel.State.Loading -> LoadingScreen()
        is ru.sicampus.bootcamp2025.ui.viewModels.AuthorizationViewModel.State.Success -> SuccessScreen()
        is ru.sicampus.bootcamp2025.ui.viewModels.AuthorizationViewModel.State.Input -> InputScreen(
            onAuthorize = { email, password -> viewModel.authorize(email, password) }
        )
        is ru.sicampus.bootcamp2025.ui.viewModels.AuthorizationViewModel.State.Error -> ErrorScreen(
            message = (state as ru.sicampus.bootcamp2025.ui.viewModels.AuthorizationViewModel.State.Error).text,
            onRetry = { viewModel.resetState() }
        )
    }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceAround ) {
        Row(
            modifier = Modifier.fillMaxSize().weight(1.5f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)
        {
            Text(modifier = Modifier,text ="Volunteer",fontSize = 40.sp, color = Color.Red)
        }
        Row(
            modifier = Modifier.fillMaxSize().weight(1.5f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(text = "Добро пожаловать!",fontSize = 30.sp)
        }

        Row(
            modifier = Modifier.fillMaxSize().weight(0.7f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Top
            ){
            Text(fontSize = 16.sp, text = "Нет аккаунта?")

            Text(fontSize = 16.sp,text = "Зарегистрироваться")
        }

        Box(modifier = Modifier.fillMaxSize().weight(6f)) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 180.dp)
                    .align(Alignment.BottomCenter),
                painter = painterResource(id = R.drawable.people),
                contentDescription = "Описание изображения",
            )
        }
    }
}

@Composable
fun InputScreen(onAuthorize: (String, String) -> Unit) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .border(
                    width = 2.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(16.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column {
                Text("Электронная почта", fontSize = 16.sp, modifier = Modifier.padding(10.dp))
                TextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    placeholder = { Text("Введите почту") }
                )
            }

            Column {
                Text("Пароль", fontSize = 16.sp, modifier = Modifier.padding(10.dp))
                TextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    placeholder = { Text("Введите пароль") }
                )
            }

            Button(
                onClick = { onAuthorize(email.value, password.value) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Text("Войти")
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Загрузка...")
    }
}

@Composable
fun SuccessScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Успешная авторизация!")
    }
}
@Composable
fun ErrorScreen(message: String, onRetry: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Ошибка: $message", textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("Попробовать снова", color = Color.White)
            }
        }
    }
}