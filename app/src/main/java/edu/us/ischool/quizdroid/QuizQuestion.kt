package edu.us.ischool.quizdroid

import android.content.Intent
import android.content.Intent.EXTRA_TEXT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup

class QuizQuestion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

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

        // start question answer activity on submit
        btnSubmit.setOnClickListener{
            val selectedRB = radioGroupAnswers.checkedRadioButtonId
            val answer = findViewById<RadioButton>(selectedRB).text.toString()
            val intent = Intent(this, QuestionAnswer::class.java).apply {
                putExtra(EXTRA_TEXT, answer)
            }
            startActivity(intent)
        }
    }
}