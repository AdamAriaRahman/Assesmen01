package org.d3if3083.assesmen01.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mbti")
data class Mbti(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val namaNya: String,
    val nimNya: String,
    val kelasNya: String


)