package com.example.fitnessapp

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*


class ExistingWorkoutsActivity : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var workoutArrayList : ArrayList<Workout>
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_existing_workouts)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        FirebaseApp.initializeApp(this)

        println("TEST: $db")
        testfunc()

        recyclerView = findViewById(R.id.workoutRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(this))

        workoutArrayList = arrayListOf()
        retrieveData()

        val backBtn = findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener {
            val intent = Intent(this, BuildActivity::class.java)
            startActivity(intent)
        }

        val newWorkoutBtn = findViewById<Button>(R.id.newWorkoutBtn)
        newWorkoutBtn.setOnClickListener {
            val intent = Intent(this, WorkoutCreationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun retrieveData() {
        db.collection("workouts")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    workoutArrayList.add(document.toObject(Workout::class.java))
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

        recyclerView.adapter = WorkoutAdapter(workoutArrayList)
    }

    private fun testfunc() {
        val query = db.collection("test")
        query.get().addOnSuccessListener { result ->
            if (result != null) {
                println("WORKS")
            }
        }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Error getting documents: ", exception)
            }
    }
}


//class ExistingWorkoutsActivity : AppCompatActivity() {
//
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var workoutArrayList: ArrayList<Workout>
//    private lateinit var db: FirebaseFirestore
//    private lateinit var workoutAdapter: WorkoutAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_existing_workouts)
//
//        // Initialize Firebase first
//        FirebaseApp.initializeApp(this)
//        db = FirebaseFirestore.getInstance()
//
//        // Initialize RecyclerView
//        recyclerView = findViewById(R.id.workoutRecyclerView)
//        recyclerView.setHasFixedSize(true)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        // Initialize ArrayList and Adapter
//        workoutArrayList = ArrayList()
//        workoutAdapter = WorkoutAdapter(workoutArrayList)
//        recyclerView.adapter = workoutAdapter
//
//        // Setup window insets
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//        // Set up buttons
//        setupButtons()
//
//        // Retrieve data
//        retrieveData()
//    }
//
//    private fun setupButtons() {
//        findViewById<Button>(R.id.backBtn).setOnClickListener {
//            startActivity(Intent(this, BuildActivity::class.java))
//        }
//
//        findViewById<Button>(R.id.newWorkoutBtn).setOnClickListener {
//            startActivity(Intent(this, WorkoutCreationActivity::class.java))
//        }
//    }
//
//    private fun retrieveData() {
//        db.collection("workouts")
//            .get()
//            .addOnSuccessListener { result ->
//                workoutArrayList.clear()
//                for (document in result) {
//                    val workout = document.toObject(Workout::class.java)
//                    workoutArrayList.add(workout)
//                    Log.d(TAG, "${document.id} => ${document.data}")
//                }
//                workoutAdapter.notifyDataSetChanged()
//            }
//            .addOnFailureListener { exception ->
//                Log.e(TAG, "Error getting documents: ", exception)
//            }
//    }
