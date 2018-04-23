package com.gmail.eliterr.sample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.gmail.eliterr.sample.activity.MyActivity
import com.gmail.eliterr.sample.fragment.FragmentHostActivity

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainView = LinearLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            gravity = Gravity.CENTER_HORIZONTAL
            orientation = LinearLayout.VERTICAL
            addView(Button(context).apply {
                layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                text = "Activity Sample"
                setOnClickListener {_ -> startActivitySample() }
            })
            addView(Button(context).apply {
                layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                text = "Fragment Sample"
                setOnClickListener {_ -> startFragmentSample() }
            })
        }
        setContentView(mainView)
    }

    private fun startActivitySample() {
        startActivity(Intent(this, MyActivity::class.java))
    }

    private fun startFragmentSample() {
        startActivity(Intent(this, FragmentHostActivity::class.java))
    }
}