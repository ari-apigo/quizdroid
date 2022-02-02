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

        // Get the Intent that started this activity and extract data
        val userAnswer = intent.getStringExtra(Intent.EXTRA_TEXT)
        // TODO get next vs. finished status
        // testing
        //val quizStatus = "Finish"
        val quizStatus = "Next"

        // get reference to user answer textView and set its text
        val tvUserAnswer = findViewById<TextView>(R.id.tvUserAnswer).apply {
            text = userAnswer
        }

        // TODO get reference to next/finish button and set its text
        // testing
        val btnNextFinish = findViewById<Button>(R.id.btnNextFinish).apply {
            text = quizStatus
        }

        // open next activity based on quiz status
        btnNextFinish.setOnClickListener{
            if (quizStatus.equals("Finish")) {
                // if finish, return to main activity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                // else (next), return to quiz question activity with next question
                val intent = Intent(this, QuizQuestion::class.java)
                startActivity(intent)
            }
        }
    }
}