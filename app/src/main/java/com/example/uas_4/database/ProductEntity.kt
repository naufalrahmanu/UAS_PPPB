package com.example.uas_4.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val nama: String,
    val harga: String,
    val deskripsi: String,
    val gambar: String

)
