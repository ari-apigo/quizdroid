package edu.us.ischool.quizdroid

import android.content.Intent.EXTRA_TEXT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }
}