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

        // ----- populate activity w/ intent -----
        // Get the Intent that started this activity and extract the string
        val topic = intent.getStringExtra(EXTRA_TEXT)

        // Capture the layout's TextView and set the string as its text
        val quizTopicTV = findViewById<TextView>(R.id.tvTopic).apply {
            text = topic
        }

        // ----- next activity -----
        // get reference to begin button
        val btnBegin = findViewById<Button>(R.id.btnBeginQuiz)
        // start quiz question activity on click
        btnBegin.setOnClickListener{
            //val quizTopic = quizTopicTV.text.toString()
            val intent = Intent(this, QuizQuestion::class.java).apply {
                putExtra("QUESTION_NUM", 1)
            }
            startActivity(intent)
        }
    }
}