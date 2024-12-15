package com.example.uas_4.model

import com.google.gson.annotations.SerializedName

data class Product(

    @SerializedName("id")
    val id: String?,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("harga")
    val harga: String,
    @SerializedName("deskripsi")
    val deskripsi: String,
    @SerializedName("gambar")
    val gambar: String
)
