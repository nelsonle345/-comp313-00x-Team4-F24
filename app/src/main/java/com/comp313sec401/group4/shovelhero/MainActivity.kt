package com.comp313sec401.group4.shovelhero

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.RecyclerView
import com.comp313sec401.group4.shovelhero.ui.theme.ShovelHeroTheme
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mainAdapter: MainAdapter
    private lateinit var floatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.userView)
        recyclerView.layoutManager = CustomLinearLayoutManager(this)

        val options = FirebaseRecyclerOptions.Builder<User>()
            .setQuery(FirebaseDatabase.getInstance().getReference("users"), User::class.java)
            .build()

        mainAdapter = MainAdapter(options)
        recyclerView.adapter = mainAdapter

        floatingActionButton = findViewById(R.id.floatingAddUserButton)
        floatingActionButton.setOnClickListener {
            startActivity(Intent(applicationContext, CreateUserActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        mainAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        mainAdapter.stopListening()
    }
}