package com.example.missionquiz

object Constants {

    const val USER_NAME = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Questions> {
val questionList = ArrayList<Questions>()
        val que1 = Questions(1,"Who has hit most sixes in test cricket?",
            "Adam Gilchrist",
            "Brendon Mccullum",
            "VIrendar Sehwag",
            "Chris Gayle",
            2)
        questionList.add(que1)

        val que2 = Questions(2,"Who has hit most sixes in ODI cricket?",
            "Sanath Jaysurya",
            "Shahid Afridi",
            "MS Dhoni",
            "Rohit Sharma",
            2)
        questionList.add(que2)

        val que3 = Questions(3,"Who has hit most sixes in T20 cricket?",
            "Chris Gayle",
            "Shahid Afridi",
            "MS Dhoni",
            "Rohit Sharma",
            1)
        questionList.add(que3)
        return questionList
    }
}