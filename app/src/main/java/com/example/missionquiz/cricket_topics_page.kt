package com.example.missionquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_cricket_topics_page.*
import kotlinx.android.synthetic.main.activity_select_quiz_topic.*
import kotlinx.android.synthetic.main.activity_select_quiz_topic.card_01
import kotlinx.android.synthetic.main.activity_select_quiz_topic.card_02
import kotlinx.android.synthetic.main.activity_select_quiz_topic.card_03
import kotlinx.android.synthetic.main.activity_select_quiz_topic.card_04
import kotlinx.android.synthetic.main.activity_select_quiz_topic.card_05
import kotlinx.android.synthetic.main.activity_select_quiz_topic.card_06
import kotlinx.android.synthetic.main.activity_select_quiz_topic.card_07
import kotlinx.android.synthetic.main.activity_select_quiz_topic.card_08


class cricket_topics_page : AppCompatActivity(), View.OnClickListener {

    var mUserName: String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cricket_topics_page)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        card_01.setOnClickListener(this)
        card_02.setOnClickListener(this)
        card_03.setOnClickListener(this)
        card_04.setOnClickListener(this)
        card_05.setOnClickListener(this)
        card_06.setOnClickListener(this)
        card_07.setOnClickListener(this)
        card_08.setOnClickListener(this)
    }

    override fun onClick(v:View?) {
        val intent = Intent(this, QuestionPageActivityV2::class.java)
        intent.putExtra(Constants.USER_NAME, mUserName)
        when(v?.id) {
            R.id.card_01 ->
                startActivity(Intent(this, QuestionPageActivityV2::class.java))
        }
    }
}
