package ru.sicampus.bootcamp2025.ui.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sicampus.bootcamp2025.R

//@Preview(showBackground = true)
//@Composable
//fun PreviewEditScreen(){
//    EditProfileScreen()
//}

@Composable
fun EditProfileScreen(
    onCancel: () -> Unit,
    onSave: () -> Unit
) {
    var name = remember { mutableStateOf("") }
    var lastName = remember { mutableStateOf("") }
    var age = remember { mutableStateOf("") }
    var experience = remember { mutableStateOf("") }
    var bio =  remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(288.dp)
                .background(
                    color = Color(0xFFC37B5C),
                )
        ) {
            Text(
                text = "Профиль",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 56.dp),
                textAlign = TextAlign.Center
            )

            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = "Аватар",
                modifier = Modifier
                    .size(130.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = 115.dp)
                    .clip(CircleShape)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(top = 306.dp,start = 32.dp)
                .align(Alignment.Center)

        ) {

            Text(
                text= "Имя",
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 60.dp )
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it },
                modifier = Modifier.width(280.dp)
                    .height(50.dp)
                    .padding(start= 60.dp) ,
                shape = RoundedCornerShape(8.dp),
                placeholder = {
                    Text(
                        text = "Введите имя",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text= "Фамилия",
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 60.dp )
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = lastName.value,
                onValueChange = { lastName.value = it },
                modifier = Modifier.width(280.dp)
                    .height(50.dp)
                    .padding(start= 60.dp) ,
                shape = RoundedCornerShape(8.dp),
                label = {
                    Text(
                        text = "Введите фамилию",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text= "Возраст",
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 60.dp )
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = age.value,
                onValueChange = { age.value = it },
                modifier = Modifier.width(280.dp)
                    .height(50.dp)
                    .padding(start= 60.dp) ,
                shape = RoundedCornerShape(8.dp),
                label = {
                    Text(
                        text = "Введите возраст",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text= "Стаж",
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 60.dp )
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = experience.value,
                onValueChange = { experience.value = it },
                modifier = Modifier.width(280.dp)
                    .height(50.dp)
                    .padding(start= 60.dp) ,
                shape = RoundedCornerShape(8.dp),
                label = {
                    Text(
                        text = "Введите стаж",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text= "О себе",
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 60.dp )
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = bio.value,
                onValueChange = { bio.value = it },
                modifier = Modifier.width(280.dp)
                    .height(90.dp)
                    .padding(start= 60.dp) ,
                shape = RoundedCornerShape(8.dp),
                label = {
                    Text(
                        text = "Напишите о себе",
                        style = MaterialTheme.typography.bodyMedium

                    )
                }
            )

            Spacer(modifier = Modifier.height(20.dp))


            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = onCancel,
                    modifier = Modifier.width(140.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color(0xFF757575),
                        contentColor = Color.White,
                        disabledContainerColor = Color.LightGray,
                        disabledContentColor = Color.Gray
                    ),
                    shape = RoundedCornerShape(8.dp),

                ) {
                    Text("Отменить")
                }

                Button(
                    onClick = onSave,
                    modifier = Modifier.width(140.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color(0xFFC94027),
                        contentColor = Color.White,
                        disabledContainerColor = Color.LightGray,
                        disabledContentColor = Color.Gray
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Сохранить")
                }
            }

        }
    }
}

