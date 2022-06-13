package com.example.mygitapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
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

        initRefreshButton()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.userListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.userListRecyclerView.adapter = adapter
    }
    private fun initRefreshButton() {
        binding.refreshButton.setOnClickListener {

            userRepo.getUsers(
                onSuccess = adapter::setData,
                onError = {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}