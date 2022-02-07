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

        // connect to TopicRepository
        val app = this.application as QuizApp
        val topicRepo = app.getTopicRepo().quizTopics

        // ----- populate activity w/ intent -----
        // Get the Intent that started this activity and extract the string
        val topic = intent.getStringExtra(EXTRA_TEXT)

        // get topic data
        val topicID = intent.getIntExtra("TOPIC_ID", 1)
        val topicData = topicRepo.get(topicID)

        // Capture the layout's TextViews and set their text
        val quizTopicTV = findViewById<TextView>(R.id.tvTopic).apply {
            text = topic
        }
        val quizLDescTV = findViewById<TextView>(R.id.tvLongDesc).apply {
            text = topicData.topicLDesc
        }
        val quizQCountTV = findViewById<TextView>(R.id.tvQuestionCount).apply {
            text = "${topicData.topicQuestions.size} Questions"
        }

        // ----- next activity -----
        // get reference to begin button
        val btnBegin = findViewById<Button>(R.id.btnBeginQuiz)
        // start quiz question activity on click
        btnBegin.setOnClickListener{
            val intent = Intent(this, QuizQuestion::class.java).apply {
                putExtra("QUESTION_NUM", 1)
                putExtra("TOPIC_ID", topicID)
            }
            startActivity(intent)
            finish()
        }
    }
}