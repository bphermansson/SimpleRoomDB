package com.example.simpleroomdb

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.simpleroomdb.ui.theme.SimpleRoomDBTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lateinit var db : AppDatabase
        Log.i("SimpleRoomDB","Start")

        try {
                db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
                )
                .build()
        } catch (exception: Exception) {
            Log.i("SimpleRoomDB","Id: " + exception)
        }

        CoroutineScope(Dispatchers.IO).launch { // Run in another thread
            val userDao = db.userDao()
            val firstname = "Firstname1"
            val lastname = "Lastname1"
            val nyperson = User(0, firstname, lastname)

            try {
                userDao.insertAll(nyperson)
            } catch (exception: Exception) {
                Log.i("SimpleRoomDB", "Id: " + exception)
            }


            Log.i("SimpleRoomDB", "Get all users")
            val users = userDao.getAll()

            for (currentuser in users) {
                Log.i("SimpleRoomDB", currentuser.uid.toString())
                Log.i("SimpleRoomDB", currentuser.first_name!!)
                Log.i("SimpleRoomDB", currentuser.last_name!!)
            }
        }

        setContent {
            SimpleRoomDBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleRoomDBTheme {
        Greeting("Android")
    }
}