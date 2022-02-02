package edu.us.ischool.quizdroid

import android.content.Intent
import android.content.Intent.EXTRA_TEXT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class TopicOverview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview)

        // Get the Intent that started this activity and extract the string
        val message = intent.getStringExtra(EXTRA_TEXT)

        // Capture the layout's TextView and set the string as its text
        val quizTopicTV = findViewById<TextView>(R.id.tvTopic).apply {
            text = message
        }

        // get begin button reference
        val btnBegin = findViewById<Button>(R.id.btnBeginQuiz)
        // start quiz question activity on click
        btnBegin.setOnClickListener{
            //val quizTopic = quizTopicTV.text.toString()
            val intent = Intent(this, QuizQuestion::class.java)
            startActivity(intent)
        }
    }
}