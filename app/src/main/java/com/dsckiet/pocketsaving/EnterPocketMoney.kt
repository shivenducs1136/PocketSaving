package com.dsckiet.pocketsaving

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.android.material.card.MaterialCardView
import android.content.SharedPreferences
import android.util.Log


class EnterPocketMoney : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_pocket_money)

        val enteredmoney= findViewById<EditText>(R.id.pocketmoney_edittext)
        val nextbtn = findViewById<MaterialCardView>(R.id.poketmoney_addbtn)

        nextbtn.setOnClickListener {


            val settings = getSharedPreferences("mt",Context.MODE_PRIVATE)
            val editor = settings.edit()
            editor.putString("PocketMoney", enteredmoney.text.toString())
            editor.commit()
            editor.apply()
            val sharedPref = this?.getSharedPreferences("mt",Context.MODE_PRIVATE) ?: return@setOnClickListener
            val highScore = sharedPref.getString("PocketMoney", "0")
            Log.e("EnterPocketMoney",highScore.toString())
            val i  = Intent(this,MainActivity::class.java)
            startActivity(i)
            finish()

        }
    }
}