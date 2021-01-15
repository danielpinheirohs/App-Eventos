package com.eventosappteste.appeventos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eventosappteste.appeventos.Main.PrincipalActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val intent = Intent(this, PrincipalActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}