package edu.us.ischool.quizdroid

import android.app.Application
import android.content.Context
import android.os.Environment
import android.util.Log
import android.view.MenuItem
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import kotlin.concurrent.thread

class QuizApp : Application() {

    // part 3 extra credit custom JSON at:
    //  https://gist.github.com/ari-apigo/49d8504e856bff71b60e06a0e3972d7b

    fun getTopicRepo(): TopicRepository {
        // part 3: local file source
        //val file = File(filesDir, "questions.json")

        // use this file for custom json extra credit
        //val file = File(filesDir, "customjson_aapigo.json")

        // part 4: downloaded file source
        val file = File(Environment.getExternalStorageDirectory().absolutePath + File.separator + "questions.json")
        return TopicRepository(file)
    }

    override fun onCreate() {
        super.onCreate()

        Log.i("ActivityManager","QuizDroid is loading.")
    }

}