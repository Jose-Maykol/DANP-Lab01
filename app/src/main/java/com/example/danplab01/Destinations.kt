package com.example.danplab01

import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class Destinations(val route: String) {
    object FormScreen : Destinations("form/{attendeeId}")
    object AttendeeListScreen : Destinations("attendeeList")
}

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    val attendeeList = remember { mutableStateListOf<Attendee>() }
    val newAttendee = remember { mutableStateOf<Attendee?>(null) }

    NavHost(navController, startDestination = Destinations.FormScreen.route) {
        composable(Destinations.FormScreen.route) {
            Form (
                attendee = newAttendee.value,
                onRegisterClick = { attendee -> registerAttendee(attendee, attendeeList, navController) }
            )
        }
        composable(Destinations.AttendeeListScreen.route) {
            AttendeeList(
                attendees = attendeeList,
                onEditClick = { attendee -> modifyAttendee(attendee, newAttendee, navController) },
                onDeleteClick = { attendee -> deleteAttendee(attendee, attendeeList, navController) }
            )
        }
    }
}

private fun registerAttendee(
    attendee: Attendee?,
    attendeeList: MutableList<Attendee>,
    navController: NavHostController
) {
    if (attendee != null) {
        attendeeList.add(attendee)
        Toast.makeText(navController.context, "Asistente registrado exitosamente", Toast.LENGTH_SHORT).show()
        navController.navigate(Destinations.AttendeeListScreen.route)
    } else {
        Toast.makeText(navController.context, "Datos de asistente cambiados exitosamente", Toast.LENGTH_SHORT).show()
        navController.navigate(Destinations.AttendeeListScreen.route)
    }
}


private fun modifyAttendee(
    modifiedAttendee: Attendee,
    newAttendee: MutableState<Attendee?>,
    navController: NavHostController
) {
    newAttendee.value = modifiedAttendee
    navController.navigate(Destinations.FormScreen.route)
}

private fun deleteAttendee(
    attendee: Attendee,
    attendeeList: MutableList<Attendee>,
    navController: NavHostController
) {
    attendeeList.remove(attendee)
    Toast.makeText(navController.context, "Asistente eliminado exitosamente", Toast.LENGTH_SHORT).show()
}