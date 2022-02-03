package edu.us.ischool.quizdroid

import android.content.Intent
import android.content.Intent.EXTRA_TEXT
import android.content.Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class QuizQuestion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        // ----- populate activity w/ intent -----
        // Get the Intent that started this activity and extract the question number
        val questionNum = intent.getIntExtra("QUESTION_NUM", 1)
        val numCorrect = intent.getIntExtra("NUM_CORRECT", 0)

        // Capture the layout's TextView and set the string as its text
        val tvQuestionTracker = findViewById<TextView>(R.id.tvQuestionTracker).apply {
            text = questionNum.toString()
        }

        // ----- this activity -----
        // get reference to answers radio group
        val radioGroupAnswers = findViewById<RadioGroup>(R.id.radioGroupAnswers)
        // get reference to submit button
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        // if submit button is disabled, enable on answer selected
        radioGroupAnswers.setOnCheckedChangeListener { _, _ ->
            if (!btnSubmit.isEnabled) {
                btnSubmit.isEnabled = true
            }
        }

        // ----- next activity -----
        // start question answer activity on submit
        btnSubmit.setOnClickListener{
            val selectedRB = radioGroupAnswers.checkedRadioButtonId
            val answer = findViewById<RadioButton>(selectedRB).text.toString()
            val intent = Intent(this, QuestionAnswer::class.java).apply {
                putExtra("USER_ANSWER", answer)
                putExtra("QUESTION_NUM", questionNum)
                putExtra("NUM_CORRECT", numCorrect)
            }
            startActivity(intent)
        }
    }
}