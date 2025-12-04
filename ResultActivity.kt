package com.example.finalproject

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast

class ResultActivity: Activity() {
    lateinit var resultTextView: TextView
    lateinit var resultImageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result)

        val voteResult = intent.getIntArrayExtra("VoteCount") ?: return
        val imageName = intent.getStringArrayExtra("ImageName") ?: return

        val tvIds = arrayOf(
            R.id.tv1, R.id.tv2, R.id.tv3,
            R.id.tv4, R.id.tv5, R.id.tv6,
            R.id.tv7, R.id.tv8, R.id.tv9)
        val rbarIds = arrayOf(
            R.id.rbar1, R.id.rbar2, R.id.rbar3,
            R.id.rbar4, R.id.rbar5, R.id.rbar6,
            R.id.rbar7, R.id.rbar8, R.id.rbar9,)

        for(i in voteResult.indices){
            findViewById<TextView>(tvIds[i]).text = imageName[i]
            findViewById<RatingBar>(rbarIds[i]).rating = voteResult[i].toFloat()
        }
        var btnReturn = findViewById<Button>(R.id.btnReturn)
        btnReturn.setOnClickListener {
            finish()
        }
        resultTextView = findViewById(R.id.resultTextView)
        resultImageView = findViewById(R.id.resultImageView)

        var max = 0
        for(i in voteResult.indices){
            if(voteResult[max] < voteResult[i]){
                max = i
            }
        }
        resultTextView.text = imageName[max]
        resultImageView.setImageResource(
            resources.getIdentifier("pic$max", "drawable", packageName)
        )

        for(i in voteResult.indices){
            if(voteResult[max] == voteResult[i] && max != i){
                Toast.makeText(applicationContext, "동률이 발생했습니다.", Toast.LENGTH_SHORT).show()

            }
        }
    }
}