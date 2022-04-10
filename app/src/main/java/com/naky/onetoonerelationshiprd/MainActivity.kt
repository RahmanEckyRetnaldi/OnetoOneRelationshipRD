package com.naky.onetoonerelationshiprd

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.naky.onetoonerelationshiprd.entity.Library
import com.naky.onetoonerelationshiprd.entity.User
import com.naky.onetoonerelationshiprd.ui.theme.OnetoOneRelationshipRDTheme
import com.naky.onetoonerelationshiprd.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnetoOneRelationshipRDTheme {
                // A surface container using the 'background' color from the theme
                OneToOneRelationShip()
            }
        }
    }
}

val userData = listOf(
    User(userId = 1, name = "Andi", age = 23),
    User(userId = 2, name = "Anton", age = 23),
    User(userId = 3, name = "Agil", age = 23),
    User(userId = 4, name = "dara", age = 23),
    User(userId = 5, name = "yuma", age = 23),
)

val libraryData = listOf(
    Library(id = 1, title = "librarry 1", userOwnerId = 1),
    Library(id = 2, title = "librarry 2", userOwnerId = 2),
    Library(id = 3, title = "librarry 3", userOwnerId = 3),
    Library(id = 4, title = "librarry 4", userOwnerId = 4),
    Library(id = 5, title = "librarry 5", userOwnerId = 5),


    )

@Composable
fun OneToOneRelationShip(
    modifier: Modifier = Modifier,
) {
    val ctx = LocalContext.current
    val userviewModel: UserViewModel = viewModel()
    userviewModel.addUser(userData)
    userviewModel.addLibrary(libraryData)

    val getUserRecord = userviewModel.readAllData.observeAsState(listOf()).value

    val userId = remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            Column(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "One to One Relationship",
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600)
                    )
                )
            }
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = userId.value,
                onValueChange = {
                    userId.value = it
                },
                label = { Text(text = "Enter User Id") },
                singleLine = true,
                modifier = modifier.fillMaxWidth(0.8f)
            )
            Spacer(modifier = Modifier.height(25.dp))
            Button(
                onClick = {
                    scope.launch {
                        userviewModel.getUser(userId.value.toInt())
                    }
                },
                modifier = modifier
                    .fillMaxWidth(0.5f)
                    .height(44.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Start Collect")
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(15.dp)
            ) {
                Text(
                    text = "User id",
                    modifier = modifier.fillMaxWidth(0.3f),
                    color = Color.White,
                    fontWeight = FontWeight(500)
                )
                Text(
                    text = "User Name",
                    modifier = modifier.fillMaxWidth(0.3f),
                    color = Color.White,
                    fontWeight = FontWeight(500)
                )
                Text(
                    text = "Library id",
                    modifier = modifier.fillMaxWidth(0.3f),
                    color = Color.White,
                    fontWeight = FontWeight(500)
                )
            }
            if(getUserRecord.isNotEmpty()){
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(Color.Blue)
                        .padding(15.dp)
                ) {
                    Text(
                        text = getUserRecord[0].user.userId.toString(),
                        modifier = modifier.fillMaxWidth(0.3f),
                        color = Color.White,
                        fontWeight = FontWeight(500)
                    )
                    Text(
                        text = getUserRecord[0].user.name,
                        modifier = modifier.fillMaxWidth(0.3f),
                        color = Color.White,
                        fontWeight = FontWeight(500)
                    )
                    Text(
                        text = getUserRecord[0].library.id.toString(),
                        modifier = modifier.fillMaxWidth(0.3f),
                        color = Color.White,
                        fontWeight = FontWeight(500)
                    )
                }
            }
        }
    }
}

