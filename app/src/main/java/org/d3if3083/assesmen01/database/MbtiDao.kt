package org.d3if3083.assesmen01.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if3083.assesmen01.model.Mbti

@Dao
interface MbtiDao {

    @Insert
    suspend fun insert(mbti: Mbti)

    @Update
    suspend fun update(mbti: Mbti)

    @Query("SELECT * FROM mbti ORDER BY kelasNya ASC")
    fun getMbti(): Flow<List<Mbti>>

    @Query("SELECT * FROM mbti WHERE id = :id")
    suspend fun getMbtiById(id: Long): Mbti?

    @Query("DELETE FROM mbti WHERE id = :id")
    suspend fun deleteById(id: Long)
}