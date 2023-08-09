package com.example.simpleroomdb

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    var logTag: String  = "SimpleRoomDBLog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            MyDatabase::class.java, "my-database"
        )
            .createFromAsset("my-database.db")
            .build()

        lifecycleScope.launch {
            var n: Long = 1
            while(db.personDao().getPersonById(n)?.firstName?.isNullOrEmpty() == false){
                var fname = db.personDao().getPersonById(n)?.firstName
                var lname = db.personDao().getPersonById(n)?.lastName
                Log.i(logTag, n.toString() + " Name: " + fname.toString() + " " + lname.toString())
                n++
            }
            var personList = db.personDao().getAllPersons()
            for (person in personList)
                Log.i(logTag, "Name: " + person)
        }
    }
}