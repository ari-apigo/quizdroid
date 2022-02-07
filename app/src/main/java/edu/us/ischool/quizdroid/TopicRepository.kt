package edu.us.ischool.quizdroid

//interface TopicRepository {
//    val elements: List<Topic>
//}

open class TopicRepository {
    private val questions: ArrayList<Quiz> = arrayListOf(Quiz("New Question 1", "Answer 1", "Answer 2", "Answer 3", "Answer 4", 1),
        Quiz("Question 2", "Answer 1", "Answer 2", "Answer 3", "Answer 4", 1),
        Quiz("Question 3", "Answer 1", "Answer 2", "Answer 3", "Answer 4", 1))

    private val icon = R.mipmap.ic_launcher_round

    val quizTopics = arrayListOf<Topic>(Topic("New Math", "This is a math quiz.", "This is a longer description about new math.", questions, icon),
        Topic("Physics", "This is a physics quiz.", "This is a longer description.", questions, icon),
        Topic("Marvel Super Heroes", "This is a Marvel Super Heroes quiz.", "This is a longer description.", questions, icon),
        Topic("Pokemon Types", "This is a Pokemon Types quiz.", "This is a longer description.", questions, icon),
        Topic("Pokemon Names", "This is a Pokemon Names quiz.", "This is a longer description.", questions, icon))
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