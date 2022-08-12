package com.example.myapp.viewmodel

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.user.User
import com.example.myapp.repositories.AuthRepository
import com.example.myapp.ui.HomeActivity
import com.example.myapp.util.SharedPreferenceUtil
import com.example.myapp.util.toast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {


    private fun addUser(user : User){
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.addUser(user)
        }
    }

    fun getInitialUser(activity: Activity){
        val currentUserId = SharedPreferenceUtil.getSharedPreference("current_user_id",activity)
        if(currentUserId != -1){
            val intent = Intent(activity,HomeActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
    }

    fun initLogin(user : User,activity: Activity){

        viewModelScope.launch(Dispatchers.IO) {
            val usersList = authRepository.getUserByEmail(user.email)

            if(usersList.isEmpty()){
               addUser(user)
               SharedPreferenceUtil.writeSharedPreference("current_user_id",activity,user.id)
               val intent = Intent(activity,HomeActivity::class.java)
               activity.startActivity(intent)
               activity.finish()
            }
            else{
                val currentUser = usersList[0]
                if(currentUser.password == user.password){
                    SharedPreferenceUtil.writeSharedPreference("current_user_id",activity,currentUser.id)
                    val intent = Intent(activity,HomeActivity::class.java)
                    activity.startActivity(intent)
                    activity.finish()
                }
                else{
                    activity.applicationContext.toast("Invalid Password. Correct password is ${currentUser.password}")
                }

            }

        }

    }

}