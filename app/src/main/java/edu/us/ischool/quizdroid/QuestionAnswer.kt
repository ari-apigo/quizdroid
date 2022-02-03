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

        // ----- populate activity w/ intent -----
        // Get the Intent that started this activity and extract data
        val userAnswer = intent.getStringExtra("USER_ANSWER")
        val questionNum = intent.getIntExtra("QUESTION_NUM", 1)
        var numCorrect = intent.getIntExtra("NUM_CORRECT", 1)

        // TODO get next vs. finished status
        val quizStatus: String
        // HARDCODED: assumes all quizzes are 9 questions long
        if (questionNum < 9) {
            quizStatus = "Next"
        } else quizStatus = "Finish"

        // get reference to user answer textView and set its text
        val tvUserAnswer = findViewById<TextView>(R.id.tvUserAnswer).apply {
            text = userAnswer
        }

        // get reference to next/finish button and set its text
        val btnNextFinish = findViewById<Button>(R.id.btnNextFinish).apply {
            text = quizStatus
        }

        // ----- this activity -----
        // compare user answer to correct answer
        if (userAnswer.equals("Answer 1")) {
            // HARDCODED: assumes all correct answers are "Answer 1"
            numCorrect++
        }

        // get reference to score TextView and set its text
        val quizScoreTV = findViewById<TextView>(R.id.tvQuizScore).apply {
            // HARDCODED: assumes all quizzes are 9 questions long
            text = "You have $numCorrect out of 9 correct."
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