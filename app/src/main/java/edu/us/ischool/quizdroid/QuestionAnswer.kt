package edu.us.ischool.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView

class QuestionAnswer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_answer)

        // connect to TopicRepository
        val app = this.application as QuizApp
        val topicRepo = app.getTopicRepo().quizTopics

        // get topic data
        val topicID = intent.getIntExtra("TOPIC_ID", 1)
        val topicData = topicRepo.get(topicID)

        // ----- populate activity w/ intent -----
        // Get the Intent that started this activity and extract data
        val userAnswer = intent.getStringExtra("USER_ANSWER")
        val questionNum = intent.getIntExtra("QUESTION_NUM", 1)
        var numCorrect = intent.getIntExtra("NUM_CORRECT", 1)

        // determine whether quiz has next question or is finished
        val quizStatus: String
        val quizSize = topicData.topicQuestions.size
        if (questionNum < quizSize) {
            quizStatus = "Next"
        } else quizStatus = "Finish"

        // get reference to user answer textView and set its text
        findViewById<TextView>(R.id.tvUserAnswer).apply {
            text = userAnswer
        }

        // get reference to next/finish button and set its text
        val btnNextFinish = findViewById<Button>(R.id.btnNextFinish).apply {
            text = quizStatus
        }

        // ----- this activity -----
        // get question data
        val currentQuestion = topicData.topicQuestions[questionNum - 1]
        // get correct answer
        lateinit var correctAnswer: String
        when (currentQuestion.quizCorrect) {
            1 -> correctAnswer = currentQuestion.quizA1
            2 -> correctAnswer = currentQuestion.quizA2
            3 -> correctAnswer = currentQuestion.quizA3
            4 -> correctAnswer = currentQuestion.quizA4
        }
        // display correct answer
        findViewById<TextView>(R.id.tvCorrectAnswer).apply{
            text = correctAnswer
        }

        // compare user answer to correct answer
        if (userAnswer.equals(correctAnswer)) {
            numCorrect++
        }

        // get reference to score TextView and set its text
        val quizScoreTV = findViewById<TextView>(R.id.tvQuizScore).apply {
            // HARDCODED: assumes all quizzes are 9 questions long
            text = "You have $numCorrect out of $quizSize correct."
        }

        // ----- next activity -----
        // open next activity based on quiz status
        btnNextFinish.setOnClickListener{
            if (quizStatus.equals("Finish")) {
                // if finish, return to main activity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                // else (next), return to quiz question activity with next question
                val intent = Intent(this, QuizQuestion::class.java).apply {
                    putExtra("QUESTION_NUM", questionNum + 1)
                    putExtra("NUM_CORRECT", numCorrect)
                }
                startActivity(intent)
                finish()
            }
        }
    }
}