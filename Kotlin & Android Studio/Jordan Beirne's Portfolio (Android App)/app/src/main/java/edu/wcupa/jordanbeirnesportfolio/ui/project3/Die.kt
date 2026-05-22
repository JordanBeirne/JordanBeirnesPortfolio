package edu.wcupa.jordanbeirnesportfolio.ui.project3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import edu.wcupa.jordanbeirnesportfolio.R

enum class Die(val sides: Int) {
    D20(20),
    D12(12),
    D10(10),
    D8(8),
    D6(6),
    D4(4),
    PERCENTILE(100);

    fun roll(): Int {
        return if (this == PERCENTILE) {
            (1..100).random()  // percentile is 0–99
        } else {
            (1..sides).random()
        }
    }
}

@Composable
fun DiceRollerApp() {
    DiceApp(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun DiceApp(modifier: Modifier = Modifier) {
    var selectedDie by remember { mutableStateOf(Die.D6) }
    var result by remember { mutableStateOf(1) }
    var expanded by remember { mutableStateOf(false) }
    androidx.compose.foundation.layout.Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.project3background),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )

        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Dropdown
            Button(onClick = { expanded = true }) {
                Text("Die: ${selectedDie.name.lowercase()}")
            }

            androidx.compose.material3.DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                Die.values().forEach { die ->
                    androidx.compose.material3.DropdownMenuItem(
                        text = { Text(die.name) },
                        onClick = {
                            selectedDie = die
                            result = die.roll()
                            expanded = false
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            DiceDisplay(selectedDie, result)

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                result = selectedDie.roll()
            }) {
                Text(stringResource(R.string.roll))
            }
        }
    }
}

@Composable
fun DiceDisplay(die: Die, result: Int) {

    val imageRes = when (die) {
        Die.D20 -> R.drawable.d20
        Die.D12 -> R.drawable.d12
        Die.D10 -> R.drawable.d10
        Die.D8 -> R.drawable.d8
        Die.D6 -> R.drawable.d6
        Die.D4 -> R.drawable.d4
        Die.PERCENTILE -> R.drawable.d20
    }

    androidx.compose.foundation.layout.Box(
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = die.name
        )

        val displayText = if (die == Die.PERCENTILE) {
            if (result == 100) "00" else result.toString()
        } else {
            result.toString()
        }

        Text(
            text = displayText,
            style = androidx.compose.material3.MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}