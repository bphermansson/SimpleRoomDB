package com.example.simpleroomdb

/*
https://www.daniweb.com/programming/mobile-development/tutorials/537384/android-native-how-to-prepopulate-a-room-database

To prepopulate the db.
Select the app directory in the Project view.
Click File/New/Foilder/Assets Folder
Start app in debug mode
Open App inspection
Run a statement to insert data, like 'INSERT INTO person VALUES (1, "Terence", "Landon")'
Highlight the db name and right-click, export as a .db file to thw assets dir created above
Load the db as prepopulated data:
   MyDatabase::class.java,
   "my-database")
   .createFromAsset("my-database.db")
   .build()
 */

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            MyDatabase::class.java, "my-database"
        )
            .createFromAsset("my-database.db")
            .build()

        lifecycleScope.launch {
            var n: Long = 0
            while(db.personDao().getPersonById(n)?.firstName?.isNullOrEmpty() == false){
                var fname = db.personDao().getPersonById(n)?.firstName
                Log.i("pia11", "Name: " + fname.toString())
                n++
            }
            Log.i("pia11", "Name: " +  db.personDao().getPersonById(1)?.firstName.toString())


        }
    }
}