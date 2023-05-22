package com.example.danplab01

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun AttendeeList(
    attendees: List<Attendee>,
    onEditClick: (Attendee) -> Unit,
    onDeleteClick: (Attendee) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 25.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Lista de asistentes",
                )
            }
        }
        items(attendees)  { attendee ->
            AttendeeCard(attendee, onEditClick, onDeleteClick)
            Divider(startIndent = 8.dp, thickness = 1.dp, color = Color.Gray)
        }
    }
}

@Composable
fun AttendeeCard(
    attendee: Attendee,
    onEditClick: (Attendee) -> Unit,
    onDeleteClick: (Attendee) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Nombre: ${attendee.fullName}")
        Text(text = "Fecha de inscripción: ${attendee.registrationDate}")
        Text(text = "Tipo de sangre: ${attendee.bloodType}")
        Text(text = "Teléfono: ${attendee.phone}")
        Text(text = "Correo electrónico: ${attendee.email}")
        Text(text = "Monto pagado: ${attendee.amountPaid}")
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            IconButton(onClick = { onEditClick(attendee) }) {
                Icon(Icons.Filled.Edit, contentDescription = "Editar")
            }

            IconButton(onClick = { onDeleteClick(attendee) }) {
                Icon(Icons.Filled.Delete, contentDescription = "Eliminar")
            }
        }
    }
}
