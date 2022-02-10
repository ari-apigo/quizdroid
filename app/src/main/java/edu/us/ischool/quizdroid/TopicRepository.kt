package edu.us.ischool.quizdroid

import java.io.File
import android.util.JsonReader
import java.io.FileReader


//interface TopicRepository {
//    val elements: List<Topic>
//}

open class TopicRepository(file: File) {
    // part 2 hardcoded variables
//    private val questions: ArrayList<Quiz> = arrayListOf(Quiz("New Question 1", "Answer 1", "Answer 2", "Answer 3", "Answer 4", 1),
//        Quiz("Question 2", "Answer 1", "Answer 2", "Answer 3", "Answer 4", 1),
//        Quiz("Question 3", "Answer 1", "Answer 2", "Answer 3", "Answer 4", 1))
//
//    val quizTopics = arrayListOf<Topic>(Topic("New Math", "This is a math quiz.", "This is a longer description about new math.", questions, icon),
//        Topic("Physics", "This is a physics quiz.", "This is a longer description.", questions, icon),
//        Topic("Marvel Super Heroes", "This is a Marvel Super Heroes quiz.", "This is a longer description.", questions, icon),
//        Topic("Pokemon Types", "This is a Pokemon Types quiz.", "This is a longer description.", questions, icon),
//        Topic("Pokemon Names", "This is a Pokemon Names quiz.", "This is a longer description.", questions, icon))

    private val icon = R.mipmap.ic_launcher_round

    // part 3 read from local json file
    val quizTopics: ArrayList<Topic> = readJsonStream(file)

    fun readJsonStream(file: File): ArrayList<Topic> {
        val reader = JsonReader(FileReader(file))
        return try {
            readTopicsArray(reader)
        } finally {
            reader.close()
        }
    }

    fun readTopicsArray(reader: JsonReader): ArrayList<Topic> {
        val topics = ArrayList<Topic>()

        reader.beginArray()
        while (reader.hasNext()) {
            topics.add(readTopic(reader))
        }
        reader.endArray()
        return topics
    }


    fun readTopic(reader: JsonReader): Topic {
        var title: String = ""
        var desc: String = ""
        var questions = ArrayList<Quiz>()

        reader.beginObject();
        while (reader.hasNext()) {
            var name = reader.nextName()
            if (name.equals("title")) {
                title = reader.nextString()
            } else if (name.equals("desc")) {
                desc = reader.nextString()
            } else if (name.equals("questions")) {
                questions = readQuestions(reader)
            } else {
                reader.skipValue()
            }
        }
        reader.endObject()
        return Topic(title, desc, desc, questions, icon)
    }

    fun readQuestions(reader: JsonReader): ArrayList<Quiz> {
        var questions = ArrayList<Quiz>()

        reader.beginArray()
        while (reader.hasNext()) {
            var text: String = ""
            var answer: Int = -1
            var answers = ArrayList<String>()

            reader.beginObject();
            while (reader.hasNext()) {
                var name = reader.nextName()
                if (name.equals("text")) {
                    text = reader.nextString()
                } else if (name.equals("answer")) {
                    answer = reader.nextInt()
                } else if (name.equals("answers")) {
                    answers = readStringsArray(reader)
                } else {
                    reader.skipValue()
                }
            }
            reader.endObject();
            questions.add(Quiz(text, answers[0], answers[1], answers[2], answers[3], answer))
        }

        reader.endArray()

        return questions
    }

    fun readStringsArray(reader: JsonReader): ArrayList<String> {
        var strings = ArrayList<String>()

        reader.beginArray()
        while (reader.hasNext()) {
            strings.add(reader.nextString())
        }
        reader.endArray()
        return strings
    }

//    lateinit var quizTopics: ArrayList<Topic>

//    init {
//        val quizTopics = arrayListOf<Topic>(Topic("New Math", "This is a math quiz.", "This is a longer description.", questions),
//            Topic("Physics", "This is a physics quiz.", "This is a longer description.", questions),
//            Topic("Marvel Super Heroes", "This is a Marvel Super Heroes quiz.", "This is a longer description.", questions),
//            Topic("Pokemon Types", "This is a Pokemon Types quiz.", "This is a longer description.", questions),
//            Topic("Pokemon Names", "This is a Pokemon Names quiz.", "This is a longer description.", questions))
//    }
}

class Topic(title: String, shortDesc: String, longDesc: String, questions: ArrayList<Quiz>, icon: Int) {
    val topicTitle = title
    val topicSDesc = shortDesc
    val topicLDesc = longDesc
    val topicQuestions = questions
    val topicIcon = icon
}

class Quiz(qText: String, a1: String, a2: String, a3: String, a4: String, correct: Int) {
    val quizText = qText
    val quizA1 = a1
    val quizA2 = a2
    val quizA3 = a3
    val quizA4 = a4
    val quizCorrect = correct
}