package com.example.uas_4.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.uas_4.model.Product

@Dao
interface ProductDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(product: ProductEntity)

    @Query("SELECT * FROM product")
    suspend fun getAllEvents(): List<ProductEntity>

    @Delete
    suspend fun deleteEvent(event : ProductEntity)

    @Query("DELETE FROM product")
    suspend fun deleteAllEvents()


    @Query("DELETE FROM product WHERE id = :eventId")
    suspend fun deleteEventById(eventId: String)

}