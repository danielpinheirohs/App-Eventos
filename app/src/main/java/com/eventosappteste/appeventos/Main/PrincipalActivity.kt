package com.eventosappteste.appeventos.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.eventosappteste.appeventos.Model.Evento
import com.eventosappteste.appeventos.R
import com.eventosappteste.appeventos.Servicos.Endpoint
import com.eventosappteste.appeventos.Servicos.NetworkService
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class PrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        supportActionBar?.hide()
    }
}