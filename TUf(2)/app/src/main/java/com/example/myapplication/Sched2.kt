package com.example.myapplication
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@Composable
fun Sched2(navController: NavHostController, selectedExperience: String) {
    val events = remember { mutableStateOf<List<Event>>(emptyList()) }
    val latestEventTitle = remember { mutableStateOf<String?>(null) }
    val newEventAdded = remember { mutableStateOf(false) }
    val notificationHelper = NotificationHelper(LocalContext.current)
    val onNewEventAdded: (String) -> Unit = { eventTitle ->
        latestEventTitle.value = eventTitle
        newEventAdded.value = true

        val message = RemoteMessage.Builder("event_channel_id")
            .setData(mapOf("eventTitle" to eventTitle))
            .build()

        FirebaseMessaging.getInstance().send(message)
    }

    LaunchedEffect(Unit) {
        val database = FirebaseDatabase.getInstance("https://ctuf-6a9e3-default-rtdb.europe-west1.firebasedatabase.app/")
        val myRef = database.getReference("events")

        myRef.addValueEventListener(object : ValueEventListener {
            private var previousSize: Int = 0

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val newEvents = mutableListOf<Event>()

                for (eventSnapshot in dataSnapshot.children) {
                    val id = eventSnapshot.child("id").getValue(Long::class.java)
                    val title = eventSnapshot.child("title").getValue(String::class.java)
                    val day = eventSnapshot.child("day").getValue(String::class.java)
                    val time = eventSnapshot.child("time").getValue(String::class.java)

                    if (id != null && title != null && day != null && time != null) {
                        val event = Event(id.toInt(), title, day, time)
                        newEvents.add(event)
                    }
                }

                val newSize = newEvents.size

                if (newSize > previousSize) {
                    val latestTitle = newEvents.lastOrNull()?.title
                    latestTitle?.let {
                        notificationHelper.showEventNotification(it)
                    }
                }

                events.value = newEvents
                previousSize = newSize
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
    Column {
    LazyColumn (
        modifier = Modifier.background(
            Color(
                red = 0.6392157f,
                green = 0.5803922f,
                blue = 0.38039216f,
                alpha = 1f
            )
        ).weight(1f)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .background(Color(red = 0f, green = 0f, blue = 0f, alpha = 1f))
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.tus),
                    contentDescription = null,
                    modifier = Modifier
                        .size(210.dp)
                        .align(Alignment.CenterStart)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = null,
                    modifier = Modifier
                        .size(85.dp)
                        .align(Alignment.CenterEnd)
                        .clickable {
                            navController.navigate("Account")
                        }
                )
            }
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                Text(
                    text = "Timetable",
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        items(events.value) { event ->
            EventItem(event = event, onEventSelected = { selectedEvent ->
                Log.d("Sched2", "Navigating to Booking with event ID: ${selectedEvent.id}")
                navController.navigate("Booking/${selectedEvent.id}")
            })
        }
    }
        Footer(navController = navController, selectedExperience = selectedExperience)
    }
}
@Composable
fun Footer(navController: NavHostController, selectedExperience: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(Color.Black),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ClickableImage(
                resourceId = R.drawable.meals,
                onClick = {
                    navController.navigate("Meals/$selectedExperience")
                },
                modifier = Modifier.size(80.dp)
            )
            ClickableImage(
                resourceId = R.drawable.exercises,
                onClick = {
                    navController.navigate("Exercise")
                },
                modifier = Modifier.size(80.dp)
            )
            ClickableImage(
                resourceId = R.drawable.booking,
                onClick = {
                    navController.navigate("Booking")
                },
                modifier = Modifier.size(80.dp)
            )
        }
    }
}
@Composable
fun EventItem(event: Event, onEventSelected: (Event) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onEventSelected(event) }
            .background(
                Color(
                    red = 0.8f,
                    green = 0.8f,
                    blue = 0.7f,
                    alpha = 1f
                )
                    .copy(alpha = 0.5f)
            )
            .border(1.dp, Color(
                red = 0.5f,
                green = 0.55f,
                blue = 0.4f,
                alpha = 1f
            ), MaterialTheme.shapes.small)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = event.title, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "${event.day}, ${event.time}")
        }
    }
}


data class Event(val id: Int, val title: String, val day: String, val time: String) {
    override fun equals(other: Any?): Boolean {
        return (other is Event) && (other.id == this.id)
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

var notificationId = 1
//@Composable
//fun showEventNotification(eventTitle: String, notificationId: Int) {
//    val context = LocalContext.current
//
//    val notificationManager = ContextCompat.getSystemService(
//        context,
//        NotificationManager::class.java
//    ) as NotificationManager
//
//    val notification = NotificationCompat.Builder(context, "event_channel_id")
//        .setContentTitle("New Event Added")
//        .setContentText(eventTitle)
//        .setSmallIcon(R.drawable.ic_launcher_foreground)
//        .setPriority(NotificationCompat.PRIORITY_HIGH)
//        .build()
//
//    notificationManager.notify(notificationId, notification)
//}
class MyFirebaseMessagingService : FirebaseMessagingService() {

    interface NotificationCallback {
        fun onEventNotificationReceived(eventTitle: String)
    }

    companion object {
        private var notificationCallback: NotificationCallback? = null

        fun registerCallback(callback: NotificationCallback) {
            notificationCallback = callback
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        remoteMessage.data.isNotEmpty().let {
            if (it) {
                remoteMessage.data["eventTitle"]?.let { eventTitle ->
                    notificationCallback?.onEventNotificationReceived(eventTitle)
                }
            } else {
                remoteMessage.notification?.let {
                    val title = it.title ?: "New Event Added"
                    val body = it.body ?: "Check out the latest event!"
                    notificationCallback?.onEventNotificationReceived(title)
                }
            }
        }
    }

    override fun onNewToken(token: String) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FCM", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            Log.d("FCM", "FCM Token: $token")
        })
    }

}




