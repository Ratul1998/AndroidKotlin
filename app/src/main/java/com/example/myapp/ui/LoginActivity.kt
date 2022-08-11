package com.example.myapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapp.LocalDatabase
import com.example.myapp.R
import com.example.myapp.data.user.User
import com.example.myapp.databinding.ActivityLoginBinding
import com.example.myapp.repositories.AuthRepository
import com.example.myapp.util.AuthViewModelFactory
import com.example.myapp.util.toast
import com.example.myapp.viewmodel.AuthViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val userDao = LocalDatabase.getDatabase(application).userDao()
        val authRepository = AuthRepository(userDao)
        val authViewModelFactory = AuthViewModelFactory(authRepository)

        viewModel = ViewModelProvider(this,authViewModelFactory)[AuthViewModel::class.java]

        viewModel.getInitialUser(this)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        binding.buttonSignIn.setOnClickListener(View.OnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            if(email.isEmpty() || password.isEmpty()){
                toast("Email and Password cannot be empty")
                return@OnClickListener
            }
            else{

                val user = User(0,email,password)

                viewModel.initLogin(user,this)

            }

        })

    }
}