package com.example.missionquiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_question_page.*

class QuestionPageActivityV2 : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Questions>? = null
    private var mSelectedOption: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_page)
        mUserName = intent.getStringExtra(Constants.USER_NAME)
        mQuestionList = Constants.getQuestions()

        defaultOptionsView()
        setQuestions()

        option_1.setOnClickListener(this)
        option_2.setOnClickListener(this)
        option_3.setOnClickListener(this)
        option_4.setOnClickListener(this)
    }

    private fun setQuestions() {
        val question =  mQuestionList!![mCurrentPosition - 1]

        progress_bar.progress = mCurrentPosition
        progress_bar_text.text = "$mCurrentPosition" + "/" + progress_bar.max

        question_text.text = question.question

        option_1.text = question.option1
        option_2.text = question.option2
        option_3.text = question.option3
        option_4.text = question.option4
        object : CountDownTimer(5000, 1000) {
            var counter = 0
            override fun onTick(millisUntilFinished: Long) {
                timer_shape.text = (5 - counter).toString()
                counter++
            }
            override fun onFinish() {
                mCurrentPosition++
                if(mCurrentPosition <= mQuestionList!!.size){
                    setQuestions()
                }
                else {
                    val intent = Intent(this@QuestionPageActivityV2, ResultsPage::class.java)
                    intent.putExtra(Constants.USER_NAME, mUserName)
                    intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                    intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList!!.size)
                    startActivity(intent)
                    finish()
                }
            }
        }.start()
        defaultOptionsView()
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
        Log.e("Nadal On Click","$mCurrentPosition")
        when (v?.id) {
            R.id.option_1 -> {
                mSelectedOption = 1
            }
            R.id.option_2 -> {
                mSelectedOption = 2
            }
            R.id.option_3 -> {
                mSelectedOption = 3
            }
            R.id.option_4 -> {
                mSelectedOption = 4
            }
        }
        val question = mQuestionList?.get(mCurrentPosition - 1)
        if (question!!.correctAnswer != mSelectedOption) {
            answerView(mSelectedOption, R.drawable.wrong_option_border_bg)
        }
        if (question!!.correctAnswer == mSelectedOption) {
            mCorrectAnswers++
        }
        answerView(question!!.correctAnswer, R.drawable.correct_option_border_bg)
      }

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
        //defaultOptionsView()

        mSelectedOption = selectedOptionNum

        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.background = ContextCompat.getDrawable(this,R.drawable.selected_option_border)
    }
}
