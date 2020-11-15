package com.example.missionquiz

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_question_page.*

class QuestionPageActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Questions>? = null
    private var mSelectedOption: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_page)

        mQuestionList = Constants.getQuestions()

        setQuestions()

        option_1.setOnClickListener(this)
        option_2.setOnClickListener(this)
        option_3.setOnClickListener(this)
        option_4.setOnClickListener(this)
        submit_button.setOnClickListener(this)
    }

    private fun setQuestions() {
        val question =  mQuestionList!![mCurrentPosition - 1]

        defaultOptionsView()

        if(mCurrentPosition == mQuestionList!!.size) {
            submit_button.text = "FINISH"
        }
        else {
            submit_button.text = "SUBMIT"
        }

        progress_bar.progress = mCurrentPosition
        progress_bar_text.text = "$mCurrentPosition" + "/" + progress_bar.max

        question_text.text = question.question

        option_1.text = question.option1
        option_2.text = question.option2
        option_3.text = question.option3
        option_4.text = question.option4

    }
    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, option_1)
        options.add(1, option_2)
        options.add(2, option_3)
        options.add(3, option_4)

        for(option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg)
        }
    }

    override fun onClick(v: View?) {
when(v?.id) {
    R.id.option_1 -> {
        selectedOptionView(option_1,1)
    }
    R.id.option_2 -> {
        selectedOptionView(option_2,2)
    }
    R.id.option_3 -> {
        selectedOptionView(option_3,3)
    }
    R.id.option_4 -> {
        selectedOptionView(option_4,4)
    }
    R.id.submit_button -> {
        if(mSelectedOption == 0) {
            mCurrentPosition++

            when {
                mCurrentPosition <= mQuestionList!!.size -> {
                    setQuestions()
                }
                else -> {
                    Toast.makeText(this, "Quiz is finished", Toast.LENGTH_SHORT)
                }
            }
        } else {
            val question = mQuestionList?.get(mCurrentPosition - 1)
            if(question!!.correctAnswer != mSelectedOption) {
                answerView(mSelectedOption, R.drawable.wrong_option_border_bg)
            }
            answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

            if(mCurrentPosition == mQuestionList!!.size) {
                submit_button.text = "FINISH"
            }
            else {
                submit_button.text = "GO TO NEXT QUESTION"
            }
            mSelectedOption = 0;
        }
    }
}    }

    private fun answerView(answer: Int, drawableView: Int) {
        when(answer) {
            1 -> {
                option_1.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                option_2.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 -> {
                option_3.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4 -> {
                option_4.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()

        mSelectedOption = selectedOptionNum

        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.background = ContextCompat.getDrawable(this,R.drawable.selected_option_border)
    }
}
