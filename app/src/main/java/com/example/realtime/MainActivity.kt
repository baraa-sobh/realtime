package com.example.realtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.realtime.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val database = Firebase.database
        val myRef = database.getReference()

        binding.btnSave.setOnClickListener {

            val name = binding.tvName.text.toString()
            val id = binding.tvId.text.toString()
            val age = binding.tvAge.text.toString()
            val person = hashMapOf("name" to name, "id" to id, "age" to age)

            myRef.child("person").child("$count").setValue(person)
            count++
            Toast.makeText(applicationContext, "success", Toast.LENGTH_LONG).show()
        }


        binding.git.setOnClickListener {

            myRef.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.getValue().toString()
                    Toast.makeText(applicationContext, "success${value}", Toast.LENGTH_LONG).show()
                    binding.tvText.text = value.toString()

                    //Log.d("bar", "Value is: " + value)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("bar", "Failed to read value.", error.toException())
                }

            })

        }


    }
}