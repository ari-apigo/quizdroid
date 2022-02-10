package edu.us.ischool.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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

        setSupportActionBar(findViewById(R.id.toolbar))

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_preferences -> {
            // User chose the "Preferences" item, show the preferences UI
            val intent = Intent(this, Preferences::class.java)
            this.startActivity(intent)
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}