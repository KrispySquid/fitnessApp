package com.example.fitnessapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WorkoutAdapter(private var workoutArrayList: ArrayList<Workout>) :
    RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WorkoutViewHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)

        return WorkoutViewHolder(v)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val workout = workoutArrayList[position]

        holder.name.text = workout.name
    }

    override fun getItemCount(): Int {
        return workoutArrayList.size
    }

    class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name : TextView = itemView.findViewById(R.id.workoutNameText)
    }

}
