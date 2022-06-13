package com.example.mygitapplication.ui.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygitapplication.R
import com.example.mygitapplication.model.UserRepo
import com.example.mygitapplication.data.UserRepoImpl
import com.example.mygitapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = UserAdapter()
    private val userRepo: UserRepo = UserRepoImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

    }

    private fun initViews() {
        showProgressBar(false)
        initRefreshButton()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.userListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.userListRecyclerView.adapter = adapter
    }

    private fun initRefreshButton() {
        binding.refreshButton.setOnClickListener {
            showProgressBar(true)
            userRepo.getUsers(
                onSuccess = {
                    showProgressBar(false)
                    adapter.setData(it)
                },
                onError = {
                    showProgressBar(false)
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    private fun showProgressBar(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
        binding.userListRecyclerView.isVisible = !isLoading
    }
}