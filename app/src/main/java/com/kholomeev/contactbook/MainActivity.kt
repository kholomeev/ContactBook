package com.kholomeev.contactbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigationevent.NavigationEventInfo
import com.kholomeev.contactbook.ui.theme.ContactBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactBookTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContactBookMainMenu(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ContactBookMainMenu(modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()) {
        Button(onClick = {phoneCall()}) {
            Text("Позвонить")
        }
        Button(onClick = {sendEmail()}) {
            Text("Написать email")
        }
        Button(onClick = {officeOnMap()}) {
            Text("Показать офис на карте")
        }
        Button(onClick = {shareContact()}) {
            Text("Поделиться контактом")
        }
    }
}

fun phoneCall() {

}

fun sendEmail() {

}

fun officeOnMap() {

}

fun shareContact() {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContactBookTheme {
        ContactBookMainMenu()
    }
}