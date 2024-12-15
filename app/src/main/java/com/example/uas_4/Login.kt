package com.example.uas_4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.uas_4.databinding.FragmentLoginBinding


class Login : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        with(binding) {
            // Memeriksa apakah pengguna sudah login sebelumnya
            if (getLoginStatus()) {
                val username = getStoredUsername()
                activity?.finishAffinity()
            }

            // Mengatur tombol login
            buttonLogin.setOnClickListener {
                val email = editTextUsername.text.toString()
                val password = editTextPassword.text.toString()

                if (email.isNotEmpty() && password.isNotEmpty()) {
                    // Mendapatkan email dan password yang disimpan dari SharedPreferences
                    val storedEmail = sharedPreferences.getString("EMAIL", "")
                    val storedPassword = sharedPreferences.getString("PASSWORD", "")

                    // Memeriksa apakah email dan password yang dimasukkan cocok
                    if (email == storedEmail && password == storedPassword) {
                        // Menyimpan status login
                        saveLoginStatus(true, email)

                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Email atau password salah",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Harap isi semua kolom", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun getStoredUsername(): String {
        return sharedPreferences.getString("USERNAME", "") ?: ""
    }

    private fun saveLoginStatus(isLoggedIn: Boolean, username: String) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", isLoggedIn)
        editor.putString("USERNAME", username)
        editor.apply()
    }

    private fun getLoginStatus(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }
}