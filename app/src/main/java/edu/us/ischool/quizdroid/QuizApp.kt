package edu.us.ischool.quizdroid

import android.app.Application
import android.util.Log
import android.view.MenuItem
import java.io.File

class QuizApp : Application() {

    // extra credit custom JSON at:
    //  https://gist.github.com/ari-apigo/49d8504e856bff71b60e06a0e3972d7b

    fun getTopicRepo(): TopicRepository {
        val file = File(filesDir, "questions.json")

        // use this file for custom json extra credit
        //val file = File(filesDir, "customjson_aapigo.json")

        return TopicRepository(file)
    }

    override fun onCreate() {
        super.onCreate()

        Log.i("ActivityManager","QuizDroid is loading.")
    }

}