package com.example.myapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.myapp.R
import com.example.myapp.data.user.User
import com.example.myapp.databinding.ActivityLoginBinding
import com.example.myapp.util.setValue
import com.example.myapp.util.toast
import com.example.myapp.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    private var viewModel: AuthViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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




