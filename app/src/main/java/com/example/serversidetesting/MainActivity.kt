package com.example.serversidetesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent

class MainActivity : ComponentActivity() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = Firebase.analytics
        firebaseAnalytics.logEvent("on_create") {}
        setContent {
            MyApp(firebaseAnalytics)
        }
    }
}

@Composable
fun MyApp(fb: FirebaseAnalytics) {
    var clickCount by remember { mutableIntStateOf(0)  }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    clickCount++
                    logButtonClick(fb)
                          },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1AFD0)),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp)
            ) {
                Text("Click Me", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Clicked $clickCount times!",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

fun logButtonClick(fb: FirebaseAnalytics) {
    fb.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT) {
        param("content_type", "button")
        param("content_id", "click me")
    }
}
