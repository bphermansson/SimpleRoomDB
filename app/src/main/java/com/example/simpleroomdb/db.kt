package com.example.simpleroomdb

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity(tableName = "person")
data class Person(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String
)

@Dao
interface PersonDao {
    @Query("SELECT * FROM person WHERE id=:id")
    suspend fun getPersonById(id: Long): Person?
    @Query("SELECT * FROM person")
    suspend fun getAllPersons(): List<Person>
}

@Database(entities = [Person::class], version = 1)
abstract class MyDatabase : RoomDatabase(){
    abstract fun personDao(): PersonDao
}