package me.muchori.joseph.android_mvvm_login.data.database

import androidx.room.RoomDatabase

//@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

//    abstract fun userDao(): UserDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: UserDatabase? = null
//
//        fun getDatabase(context: Context) : UserDatabase {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    UserDatabase::class.java,
//                    USER_DETAILS
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
}