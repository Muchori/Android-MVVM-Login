package me.muchori.joseph.android_mvvm_login.data.database

import androidx.room.Dao

@Dao
interface UserDao {

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun addUser(user: User)
//
//    @Query("SELECT * FROM USER_DETAILS ORDER BY id ASC")
//    fun readUserData(): LiveData<User>
}