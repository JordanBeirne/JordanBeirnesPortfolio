package edu.wcupa.jordanbeirnesportfolio.ui.project1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomizationDialog(
    selectedHoliday: String,
    onHolidaySelected: (String) -> Unit,
    recipient: String,
    onRecipientChange: (String) -> Unit,
    sender: String,
    onSenderChange: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val holidays = listOf(
        "Birthday",
        "New Year",
        "Anniversary",
        "Christmas",
        "Hanukkah",
        "Eid"
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text("Customize Greeting") },
        text = {
            Column {
                Text("Select Holiday:")

                holidays.forEach { holiday ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = (holiday == selectedHoliday),
                            onClick = { onHolidaySelected(holiday) }
                        )
                        Text(holiday)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = recipient,
                    onValueChange = onRecipientChange,
                    label = { Text("Recipient Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = sender,
                    onValueChange = onSenderChange,
                    label = { Text("Sender Name") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}