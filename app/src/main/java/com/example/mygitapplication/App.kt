package com.example.mygitapplication

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.example.mygitapplication.data.UserRepoImpl
import com.example.mygitapplication.model.UserRepo

class App : Application() {
    val userRepo: UserRepo by lazy { UserRepoImpl() }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App