package edu.us.ischool.quizdroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class QuizTest(topic: String) {
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

        val app = this.application as QuizApp

        val topicRepo = app.getTopicRepo()

        val quizTopics = topicRepo.quizTopics

//        val quizTopics = arrayListOf<QuizTest>(QuizTest("Math"), QuizTest("Physics"), QuizTest("Marvel Super Heroes"),
//            QuizTest("Pokemon Types"), QuizTest("Pokemon Names"))

        // access recyclerView from XML file
        var rv = findViewById<RecyclerView>(R.id.listQuiz)
        rv.layoutManager = linearLayoutManager
        adapter = RecyclerAdapter(quizTopics)
        rv.adapter = adapter

    }
}