package com.example.sgionoteskt.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sgionoteskt.data.model.UserPreference

@Dao
interface UserPreferenceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setPreference(preference: UserPreference)

    @Query("SELECT pref_value FROM user_preferences WHERE pref_key = :key")
    suspend fun getPreference(key: String): String?

}
