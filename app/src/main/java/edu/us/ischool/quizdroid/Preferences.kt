package edu.us.ischool.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Preferences : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        findViewById<Button>(R.id.btnUpdatePref).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("URL", findViewById<EditText>(R.id.editTextURL).text.toString())
                putExtra("TIMER_INTERVAL", findViewById<EditText>(R.id.editTextMinutes).text.toString().toInt())
            }
            this.startActivity(intent)
        }
    }
}