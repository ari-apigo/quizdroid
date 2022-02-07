package edu.us.ischool.quizdroid

import android.app.Application
import android.util.Log

class QuizApp : Application() {

    fun getTopicRepo(): TopicRepository {
        return TopicRepository()
    }

    override fun onCreate() {
        super.onCreate()

        Log.i("ActivityManager","QuizDroid is loading.")
    }
}