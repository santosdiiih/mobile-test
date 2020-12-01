package com.example.webgamescenter.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.webgamescenter.R
import com.example.webgamescenter.http.HttpHelperLogin
import com.example.webgamescenter.http.HttpHelperUsuario
import com.example.webgamescenter.model.Login
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login_de_usuario.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class loginDeUsuario : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_de_usuario)

        buttonLogin.setOnClickListener(this)
        insertToolbar()



     }

    private fun validaForm() : Boolean {
        var valida = true
        if(loginEmail.length() < 3){
            textInputLayoutLoginEmail.isErrorEnabled = true
            textInputLayoutLoginEmail.error = "O email é obrigatorio!!!"
            valida = false
        }
        else{
            textInputLayoutLoginEmail.isErrorEnabled = false
            textInputLayoutLoginEmail.error = null
        }

        if (loginSenha.length() < 3){
            textInputLayoutLoginSenha.isErrorEnabled = true
            textInputLayoutLoginSenha.error = "A senha é obrigatoria"
            valida = false
        }
        else{
            textInputLayoutLoginSenha.isErrorEnabled = false
            textInputLayoutLoginSenha.error = null
        }
        return valida
    }

    private fun insertToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Login"

    }

    override fun onClick(v: View) {
        if(v.id == R.id.buttonLogin){
            if(validaForm()){
                objetoLogin()

            }
        }
    }

    private fun objetoLogin() {
        val login = Login()
        login.email = loginEmail.text.toString()
        login.senha = loginSenha.text.toString()

        var gson = Gson()

        // convertendo o objeto usuario em um gson
        var loginJson = gson.toJson(login)

        doAsync {
            val http = HttpHelperLogin()
            http.login(loginJson)

            uiThread {
                if(login.email.isEmpty()){
                   abrirActivity()
                }
                else {
                    toast("Senha ou email Incorretos, Tente novamente")
                }
            }
        }
    }

    private fun abrirActivity() {
        val intent = Intent(this, index::class.java)
        startActivity(intent)
        limpaForm()
    }

    private fun limpaForm() {
        loginEmail.setText("")
        loginSenha.setText("")
        loginEmail.requestFocus()
    }
}
