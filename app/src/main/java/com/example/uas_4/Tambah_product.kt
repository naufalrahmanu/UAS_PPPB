package com.example.uas_4

import androidx.appcompat.app.AppCompatActivity
import com.example.uas_4.databinding.ActivityAdminAddProductBinding
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.uas_4.model.Product
import com.example.uas_4.network.ApiClient
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Tambah_product : AppCompatActivity() {
    private lateinit var binding: ActivityAdminAddProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSimpan.setOnClickListener {
            val nama = binding.inputNama.text.toString()
            val harga = binding.inputHarga.text.toString()
            val deskripsi = binding.inputDeskripsi.text.toString()
            val gambar = binding.imagePreview.toString()

            add_product(nama, harga, deskripsi, gambar)
        }

        binding.buttonPilihGambar.setOnClickListener {
            // Tambahkan logika untuk memilih gambar dari galeri atau kamera di sini
        }

        binding.imagePreview.setOnClickListener {
            // Tambahkan logika untuk melihat gambar yang dipilih di sini
        }


    }

    private fun add_product(nama: String, harga: String, deskripsi: String, gambar: String) {
        Log.d("EventUpload", "nama: $nama, Tipe Data: ${nama.javaClass.simpleName}")
        Log.d("EventUpload", "harga: $harga, Tipe Data: ${harga.javaClass.simpleName}")
        Log.d("EventUpload", "Deskripsi: $deskripsi, Tipe Data: ${deskripsi.javaClass.simpleName}")
        Log.d("EventUpload", "Foto: $gambar, Tipe Data: ${gambar.javaClass.simpleName}")

        val jsonData = Gson().toJsonTree(
            mapOf(
                "nama" to nama,
                "deskripsi" to deskripsi,
                "harga" to harga,
                "gambar" to gambar
            )
        ).toString()

        val requestBody = jsonData.toRequestBody("application/json".toMediaType())

        ApiClient.getInstance().addEvent(requestBody).enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if (response.isSuccessful) {
                    // Tangani jika data berhasil dikirimkan
                    Toast.makeText(applicationContext, "Data berhasil dikirim!", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                    println("Data berhasil dikirim: ${response.body()}")
                } else {
                    // Tangani jika ada error
                    println("Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                // Tangani jika terjadi kesalahan jaringan atau lainnya
                println("Koneksi gagal: ${t.message}")
            }
        })

    }

}