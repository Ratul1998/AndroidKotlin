package com.example.myapp.util

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.myapp.ui.LoginActivity
import com.example.myapp.ui.fragments.MovieFragment
import com.example.myapp.viewmodel.AuthViewModel
import kotlin.reflect.KProperty

fun Context.toast(message :String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}

operator fun Any.setValue(fragment: Fragment, property: KProperty<*>, viewModel: ViewModel) {

}

operator fun Any.setValue(activity: AppCompatActivity, property: KProperty<*>, viewModel: ViewModel) {

}