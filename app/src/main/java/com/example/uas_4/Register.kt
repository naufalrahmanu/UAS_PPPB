package com.example.uas_4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.uas_4.databinding.FragmentRegisterBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Register : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        with(binding){
            buttonRegister.setOnClickListener {
                val username = RegisterTextUsername.text.toString()
                val password = RegisterTextPassword.text.toString()

                if (username.isNotEmpty() && password.isNotEmpty()) {
                    saveUserData(username, password)
                    Toast.makeText(requireContext(),"Register Berhasil", Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(requireContext(),"Register Gagal", Toast.LENGTH_SHORT).show()
                }

            }
        }


    }

    private fun saveUserData(username: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.apply()

    }
    override fun onResume() {
        super.onResume()
        // Mengosongkan input setelah halaman registrasi dilihat
        with(binding) {
            RegisterTextUsername.text?.clear()
            RegisterTextPassword.text?.clear()

        }
    }

}