package com.example.uas_4

import androidx.appcompat.app.AppCompatActivity

import com.example.uas_4.databinding.ActivityAdminEditProductBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.example.uas_4.model.Product
import com.example.uas_4.network.ApiClient

class Edit_product : AppCompatActivity() {
    private lateinit var binding: ActivityAdminEditProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminEditProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")
        val nama = intent.getStringExtra("nama")
        val harga = intent.getStringExtra("harga")
        val deskripsi = intent.getStringExtra("deskripsi")
        val gambar = intent.getStringExtra("gambar")


        binding.edtInputNama.setText(nama)
        binding.edtInputHarga.setText(harga)
        binding.edtInputDeskripsi.setText(deskripsi)
        binding.edtImagePreview.setImageURI(gambar)

        binding.edtButtonSimpan.setOnClickListener {
            val nama = binding.edtInputNama.text.toString()
            val harga = binding.edtInputHarga.text.toString()
            val deskripsi = binding.edtInputDeskripsi.text.toString()
            val gambar = binding.edtImagePreview.toString()

            if (id != null) {
                val updateProduct = Product(
                    id = id,
                    nama =  nama,
                    harga =  harga,
                    deskripsi =  deskripsi,
                    gambar =  gambar)

                Log.d("API Request", "Updating Event with ID: $id")
                Log.d("Updated Event", "ID: ${updateProduct.id}, nama: ${updateProduct.nama}, deskripsi: ${updateProduct.deskripsi}, Lokasi: ${updateProduct.harga}")


                val apiService =ApiClient.getInstance()
                apiService.updateEvent(id,updateProduct).enqueue(object : Callback<Product> {
                    override fun onResponse(call: Call<Product>, response: Response<Product>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@Edit_product, "Event berhasil diupdate", Toast.LENGTH_SHORT).show()
                            finish()
                    } else {
                            Toast.makeText(this@Edit_product, "Gagal mengupdate event", Toast.LENGTH_SHORT).show()
                            Log.e("API Error", "Response Code: ${response.code()}")

                        }
                }
                override fun onFailure(call: Call<Product>, t: Throwable) {
                    Toast.makeText(this@Edit_product, "Gagal mengupdate event", Toast.LENGTH_SHORT).show()
                    Log.e("API Error", "Error: ${t.message}")
                }
                })



            } else {
                Toast.makeText(this@Edit_product, "ID product tidak ditemukan", Toast.LENGTH_SHORT).show()
            }


        }

    }
}

private fun ImageView.setImageURI(gambar: String?) {



}
