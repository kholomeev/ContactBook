package com.kholomeev.contactbook

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigationevent.NavigationEventInfo
import com.kholomeev.contactbook.ui.theme.ContactBookTheme
import androidx.core.net.toUri

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
    val context = LocalContext.current

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()) {
        Button(onClick = {phoneCall(context)}) {
            Text("Позвонить")
        }
        Button(onClick = {sendEmail(context)}) {
            Text("Написать email")
        }
        Button(onClick = {officeOnMap(context)}) {
            Text("Показать офис на карте")
        }
        Button(onClick = {shareContact(context)}) {
            Text("Поделиться контактом")
        }
    }
}

fun phoneCall(context: Context) {
    val intent = Intent(Intent.ACTION_DIAL, "tel:+74951234567".toUri())
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
    else {
        Toast.makeText(context, "Нет приложения-телефона", Toast.LENGTH_SHORT).show()
    }
}

fun sendEmail(context: Context) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = "mailto:contact@example.com".toUri()
        putExtra(Intent.EXTRA_EMAIL, "contact@example.com")
        putExtra(Intent.EXTRA_SUBJECT, "Обращение")
    }
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
    else {
        Toast.makeText(context, "Нет почты", Toast.LENGTH_SHORT).show()
    }
}

fun officeOnMap(context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, "geo:60.0237, 30.2289".toUri())
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
    else {
        Toast.makeText(context, "Нет карт", Toast.LENGTH_SHORT).show()
    }
}

fun shareContact(context: Context) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, "Контакт: +7 (495) 123-45-67, contact@example.com".toUri())
    }
    val chooser = Intent.createChooser(intent, "Поделиться через...")
    context.startActivity(chooser)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContactBookTheme {
        ContactBookMainMenu()
    }
}
