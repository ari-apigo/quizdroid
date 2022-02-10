package edu.us.ischool.quizdroid

import android.app.Application
import android.util.Log
import java.io.File

class QuizApp : Application() {

    fun getTopicRepo(): TopicRepository {
        val file = File(filesDir, "questions.json")
        return TopicRepository(file)
    }

    override fun onCreate() {
        super.onCreate()

        Log.i("ActivityManager","QuizDroid is loading.")
    }
}