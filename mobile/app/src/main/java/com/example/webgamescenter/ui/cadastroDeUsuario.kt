package com.example.webgamescenter.ui

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.webgamescenter.Classes.Estado
import com.example.webgamescenter.Classes.Usuario
import com.example.webgamescenter.R
import com.example.webgamescenter.http.HttpHelperEstado
import com.example.webgamescenter.http.HttpHelperUsuario
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_cadastro_de_usuario.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import android.widget.ArrayAdapter as ArrayAdapter

class cadastroDeUsuario : AppCompatActivity(), View.OnClickListener  {

    private lateinit var adapter: ArrayAdapter<CharSequence>
    private lateinit var arrayAdapter: ArrayAdapter<CharSequence>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_cadastro_de_usuario)

        insertToolbar()
        buttonAbrirGaleria.setOnClickListener(this)
        buttonCadastro.setOnClickListener(this)


       // adapter = ArrayAdapter.createFromResource(this, estado, android.R.layout.simple_spinner_dropdown_item)
        cadastroSpinnerEstado.adapter = adapter

    }


    private fun preencherSpinnerEstado(view: View) {

            doAsync {
                val http = HttpHelperEstado()
                http.getEstados()
                var spinnerAdapter = ""
                uiThread {
                   // spinnerAdapter = ArrayAdapter.createFromResource(view.context, android.R.layout.simple_spinner_dropdown_item)
                    //    .toString()
                   // cadastroSpinnerEstado.adapter = spinnerAdapter
                }


            }



    }
    private fun objetoUsuario() {

        // criando um objeto usuario
        var usuario = Usuario()
        usuario.nome = cadastroNome.text.toString()
        usuario.senha = cadastroSenha.text.toString()
        usuario.dataDeNascimento = cadastroDataNascimento.text.toString()
        usuario.ultimoNome = cadastroUltimoNome.text.toString()
        usuario.email = cadastroEmail.text.toString()
        usuario.nickname = cadastroNickname.text.toString()

        // pega a posição do estado que foi selecionado
        val position = adapter.getPosition(usuario.estado)
        cadastroSpinnerEstado.setSelection(position)

        // instancia a clase estado
        var estado = Estado()

        // var responseBody = HttpHelper.getEstados()
        // val objectList = gson.fromJson(responseBody, Estado::class.java).asList()

        // resgata o codigo do estado que foi selecionado
        estado.codigo = cadastroSpinnerEstado.selectedItem.toString()

        //instanciar um objeto gson
        var gson = Gson()

        // convertendo o objeto usuario em um gson
        var usuarioJson = gson.toJson(usuario)

        doAsync {
            val http = HttpHelperUsuario()
            http.post(usuarioJson)
        }


    }

    private fun insertToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Novo cadastro"
    }

    override fun onClick(view: View) {
        val id = view.id

        when (id) {
            R.id.buttonAbrirGaleria -> {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                startActivityForResult(intent, 5000)
            }
            R.id.buttonCadastro -> {
                if(validaForm()){

                    objetoUsuario()

                    val intent = Intent(this, loginDeUsuario::class.java)
                    startActivity(intent)
                    limpaForm()
                }
            }
        }
    }

    private fun limpaForm() {
        cadastroNome.setText("")
        cadastroEmail.setText("")
        cadastroSenha.setText("")
        cadastroNickname.setText("")
        cadastroDataNascimento.setText("")
        cadastroUltimoNome.setText("")

    }

    private fun validaForm(): Boolean {
        var valida = true

        if(cadastroNome.length() < 3){
            inputTextCadastroNome.isErrorEnabled = true
            inputTextCadastroNome.error = "É necessario digitar seu nome"
            valida = false
        }
        else{
            inputTextCadastroNome.isCounterEnabled = false
            inputTextCadastroNome.error = null
        }
        if(cadastroEmail.length() < 3){
            inputTextcadastroEmail.isErrorEnabled = true
            inputTextcadastroEmail.error = "É necessario digitar seu email"
            valida = false
        }
        else{
            inputTextcadastroEmail.isCounterEnabled = false
            inputTextcadastroEmail.error = null
        }

        if(cadastroSenha.length() < 3){
            inputTextcadastroSenha.isErrorEnabled = true
            inputTextcadastroSenha.error = "É necessario digitar sua senha"
            valida = false
        }
        else{
            inputTextcadastroSenha.isCounterEnabled = false
            inputTextcadastroSenha.error = null
        }

        if(cadastroDataNascimento.length() < 3){
            inputTextCadastroDataNascimento.isErrorEnabled = true
            inputTextCadastroDataNascimento.error = "É necessario digitar sua data de nascimento"
            valida = false
        }
        else{
            inputTextCadastroDataNascimento.isCounterEnabled = false
            inputTextCadastroDataNascimento.error = null
        }

        if(cadastroNickname.length() < 3){
            inputTextlayoutNickname.isErrorEnabled = true
            inputTextlayoutNickname.error = "É necessario digitar seu nickname"
            valida = false
        }
        else{
            inputTextlayoutNickname.isCounterEnabled = false
            inputTextlayoutNickname.error = null
        }
        if(cadastroUltimoNome.length() < 3){
            inputTextCadastroNomeUltimo.isErrorEnabled = true
            inputTextCadastroNomeUltimo.error = "É necessario digitar seu ultimo nome"
            valida = false
        }
        else{
            inputTextCadastroNomeUltimo.isCounterEnabled = false
            inputTextCadastroNomeUltimo.error = null
        }

        return valida
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(data != null){
            val inputString = contentResolver.openInputStream(data.data!!)
            val bitmap = BitmapFactory.decodeStream(inputString)
            fotoPerfilUsuario.scaleType = ImageView.ScaleType.CENTER_CROP
            fotoPerfilUsuario.setImageBitmap(bitmap)
        }
    }
}
