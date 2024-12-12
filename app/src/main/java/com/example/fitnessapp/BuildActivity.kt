package com.example.fitnessapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BuildActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_build)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val homeBtn = findViewById<Button>(R.id.homeBtn)
        homeBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val summaryBtn = findViewById<Button>(R.id.summaryBtn)
        summaryBtn.setOnClickListener {
            val intent = Intent(this, SummaryActivity::class.java)
            startActivity(intent)
        }

        val profileBtn = findViewById<Button>(R.id.profileBtn)
        profileBtn.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        val workoutBtn = findViewById<Button>(R.id.workoutBtn)
        workoutBtn.setOnClickListener {
            val intent = Intent(this, ExistingWorkoutsActivity::class.java)
            startActivity(intent)
        }

        val routineBtn = findViewById<Button>(R.id.routineBtn)
        routineBtn.setOnClickListener {
            val intent = Intent(this, ExistingRoutineActivity::class.java)
            startActivity(intent)
        }

        val scheduleBtn = findViewById<Button>(R.id.scheduleBtn)
        scheduleBtn.setOnClickListener {
            val intent = Intent(this, ExistingScheduleActivity::class.java)
            startActivity(intent)
        }
    }
}