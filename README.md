# SimpleRoomDB

A project that shows the basics with Kotlins Room database with prepopulated data. 
Room is a good modern way to store data in an Android app.

To prepopulate the db.
-Select the app directory in the Project view.
-Click File/New/Foilder/Assets Folder
-Start app in debug mode
-Open App inspection
-Run a statement to insert data, like 'INSERT INTO person VALUES (1, "Terence", "Landon")'
-Highlight the db name and right-click, export as a .db file to the assets dir created above
-Load the db as prepopulated data in the code:
MyDatabase::class.java,"my-database").createFromAsset("my-database.db").build()

Ref: https://www.daniweb.com/programming/mobile-development/tutorials/537384/android-native-how-to-prepopulate-a-room-database