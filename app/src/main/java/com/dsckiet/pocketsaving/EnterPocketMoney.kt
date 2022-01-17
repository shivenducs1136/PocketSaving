package com.dsckiet.pocketsaving

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.android.material.card.MaterialCardView

class EnterPocketMoney : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_pocket_money)

        val enteredmoney= findViewById<EditText>(R.id.pocketmoney_edittext)
        val nextbtn = findViewById<MaterialCardView>(R.id.poketmoney_addbtn)

        nextbtn.setOnClickListener {


            val i  = Intent(this,MainActivity::class.java)
            i.putExtra("PocketMoney",enteredmoney.text.toString())
            startActivity(i)
            finish()

        }


    }
}