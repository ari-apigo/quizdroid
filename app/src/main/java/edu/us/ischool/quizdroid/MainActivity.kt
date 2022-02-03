package edu.us.ischool.quizdroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Quiz(topic: String) {
    val topic = topic
    val desc = "This is a description of the quiz topic."
    val totalQuestions = 9
    var currentQuestion = 1

    fun increaseQuestion() = currentQuestion + 1
}

class MainActivity : AppCompatActivity() {

    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this)

        val quizTopics = arrayListOf<Quiz>(Quiz("Math"), Quiz("Physics"), Quiz("Marvel Super Heroes"),
            Quiz("Pokemon Types"), Quiz("Pokemon Names"))

        // access recyclerView from XML file
        var rv = findViewById<RecyclerView>(R.id.listQuiz)
        rv.layoutManager = linearLayoutManager
        adapter = RecyclerAdapter(quizTopics)
        rv.adapter = adapter

    }
}