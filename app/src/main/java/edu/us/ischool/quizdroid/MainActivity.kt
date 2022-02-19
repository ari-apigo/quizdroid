package edu.us.ischool.quizdroid

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.HttpURLConnection
import java.net.URL
import java.util.jar.Manifest
import kotlin.concurrent.thread

class QuizTest(topic: String) {
    val topic = topic
    val desc = "This is a description of the quiz topic."
    val totalQuestions = 9
    var currentQuestion = 1

    fun increaseQuestion() = currentQuestion + 1
}

class MainActivity : AppCompatActivity() {

    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        linearLayoutManager = LinearLayoutManager(this)

        // part 4 stuff
        checkPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, 100)

        val app = this.application as QuizApp

        val topicRepo = app.getTopicRepo()

        val quizTopics = topicRepo.quizTopics

//        val quizTopics = arrayListOf<QuizTest>(QuizTest("Math"), QuizTest("Physics"), QuizTest("Marvel Super Heroes"),
//            QuizTest("Pokemon Types"), QuizTest("Pokemon Names"))

        // access recyclerView from XML file
        var rv = findViewById<RecyclerView>(R.id.listQuiz)
        rv.layoutManager = linearLayoutManager
        adapter = RecyclerAdapter(quizTopics)
        rv.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_preferences -> {
            // User chose the "Preferences" item, show the preferences UI
            val intent = Intent(this, Preferences::class.java)
            this.startActivity(intent)
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    // Function to check and request permission.
    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this@MainActivity, permission) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(permission), requestCode)
        } else {
            Toast.makeText(this@MainActivity, "Permission already granted", Toast.LENGTH_SHORT).show()

            val t = thread {
                val server = URL("https://gist.githubusercontent.com/ari-apigo/49d8504e856bff71b60e06a0e3972d7b/raw/5495145cdbb914d1579b1dadbb59ff99ea247a69/customjson_aapigo.json")
                val client: HttpURLConnection = server.openConnection() as HttpURLConnection
                client.requestMethod = "GET"

                val result = BufferedReader(InputStreamReader(client.getInputStream()))
                //val result = InputStreamReader(client.getInputStream())

                val path = Environment.getExternalStorageDirectory().absolutePath + File.separator
                val newFile = File(path + "questionsTest.json")
                Log.i("MainActivity", path + "questionsTest.json")
                val writer = PrintWriter(newFile)
                //val fos: FileOutputStream = openFileOutput("questionsTest.json", Context.MODE_PRIVATE)
                var inputLine: String?
                while (result.readLine().also { inputLine = it } != null)
                    writer.println(inputLine)
                    writer.close()

                result.close()

                // check if questions.json already exists
                var questionsData = File(path + "questions.json")
                if (questionsData.exists()) {
                    // replace original w/ newly downloaded data file
                    questionsData.delete()
                }
                // rename newly downloaded data file as "questions.json"
                newFile.renameTo(questionsData)

                //            var inputLine: String?
                //            while (result.readLine().also { inputLine = it } != null)
                //                Log.i("QuizApp", inputLine!!)
                //            result.close()
            }
            t.join()
        }
    }

    fun downloadData(url: String) {
        val server = URL(url)
        val client: HttpURLConnection = server.openConnection() as HttpURLConnection
        client.requestMethod = "GET"

        val result = BufferedReader(InputStreamReader(client.getInputStream()))
        //val result = InputStreamReader(client.getInputStream())

        val path = Environment.getExternalStorageDirectory().absolutePath + File.separator
        val newFile = File(path + "questionsTest.json")
        Log.i("MainActivity", path + "questionsTest.json")
        val writer = PrintWriter(newFile)
        //val fos: FileOutputStream = openFileOutput("questionsTest.json", Context.MODE_PRIVATE)
        var inputLine: String?
        while (result.readLine().also { inputLine = it } != null)
            writer.println(inputLine)
        writer.close()

        result.close()

        // check if questions.json already exists
        var questionsData = File(path + "questions.json")
        if (questionsData.exists()) {
            // replace original w/ newly downloaded data file
            questionsData.delete()
        }
        // rename newly downloaded data file as "questions.json"
        newFile.renameTo(questionsData)
    }

    // This function is called when the user accepts or decline the permission.
    // Request Code is used to check which permission called this function.
    // This request code is provided when the user is prompt for permission.
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity, "Camera Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}