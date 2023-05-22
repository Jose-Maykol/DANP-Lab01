package com.example.danplab01

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun Form(
    attendee: Attendee? = null,
    onRegisterClick: (Attendee?) -> Unit
) {
    var fullName by remember { mutableStateOf(attendee?.fullName.orEmpty()) }
    var registrationDate by remember { mutableStateOf(attendee?.registrationDate.orEmpty()) }
    var bloodType by remember { mutableStateOf(attendee?.bloodType.orEmpty()) }
    var phone by remember { mutableStateOf(attendee?.phone.orEmpty()) }
    var email by remember { mutableStateOf(attendee?.email.orEmpty()) }
    var amountPaid by remember { mutableStateOf(attendee?.amountPaid?.toString().orEmpty()) }

    Column(modifier = Modifier.padding(32.dp)) {
        TextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text(text = "Nombres completos") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = registrationDate,
            onValueChange = { registrationDate = it },
            label = { Text(text = "Fecha de inscripción") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = bloodType,
            onValueChange = { bloodType = it },
            label = { Text(text = "Tipo de sangre") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text(text = "Teléfono") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Correo electrónico") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = amountPaid,
            onValueChange = { amountPaid = it },
            label = { Text(text = "Monto pagado") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val newAttendee = Attendee(
                    fullName = fullName,
                    registrationDate = registrationDate,
                    bloodType = bloodType,
                    phone = phone,
                    email = email,
                    amountPaid = amountPaid.toDoubleOrNull() ?: 0.0
                )
                if (attendee != null) {
                    attendee.fullName = fullName
                    attendee.registrationDate = registrationDate
                    attendee.bloodType = bloodType
                    attendee.phone = phone
                    attendee.email = email
                    attendee.amountPaid = amountPaid.toDoubleOrNull() ?: 0.0
                    onRegisterClick(null)
                } else {
                    onRegisterClick(newAttendee)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = if (attendee != null) "Guardar cambios" else "Registrar asistente")
        }
    }
}


