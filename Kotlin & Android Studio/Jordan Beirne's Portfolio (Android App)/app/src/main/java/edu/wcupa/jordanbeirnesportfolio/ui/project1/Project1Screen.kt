package edu.wcupa.jordanbeirnesportfolio.ui.project1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Project1Screen() {
    var showMenu by remember { mutableStateOf(false) }
    var selectedHoliday by remember { mutableStateOf("Birthday") }
    var recipient by remember { mutableStateOf("Mike") }
    var sender by remember { mutableStateOf("Jordan") }

    val message = when (selectedHoliday) {
        "Birthday" -> "Happy Birthday $recipient!"
        "New Year" -> "Happy New Year $recipient!"
        "Anniversary" -> "Happy Anniversary $recipient!"
        "Christmas" -> "Merry Christmas $recipient!"
        "Hanukkah" -> "Happy Hanukkah $recipient!"
        "Eid" -> "Eid Mubarak $recipient!"
        else -> "Hello $recipient!"
    }

    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp)
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        GreetingText(
            message = message,
            from = "From $sender",
            modifier = Modifier.align(Alignment.Center)
        )

        Button(
            onClick = { showMenu = true },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp)
        ) {
            Text("Customize")
        }

        if (showMenu) {
            CustomizationDialog(
                selectedHoliday = selectedHoliday,
                onHolidaySelected = { selectedHoliday = it },
                recipient = recipient,
                onRecipientChange = { recipient = it },
                sender = sender,
                onSenderChange = { sender = it },
                onDismiss = { showMenu = false }
            )
        }
    }
}

@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(8.dp)
    ) {
        Text(
            text = message,
            fontSize = 60.sp,
            lineHeight = 70.sp,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Text(
            text = from,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.End)
        )
    }
}