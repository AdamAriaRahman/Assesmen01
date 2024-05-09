package org.d3if3083.assesmen01.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if3083.assesmen01.model.Mbti

@Database(entities = [Mbti::class], version = 1, exportSchema = false)
abstract class MbtiDb : RoomDatabase() {

    abstract val dao: MbtiDao

    companion object {

        @Volatile
        private var INSTANCE: MbtiDb? = null

        fun getInstance(context: Context): MbtiDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MbtiDb::class.java,
                        "mbti.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}