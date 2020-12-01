package com.example.webgamescenter.ui

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.webgamescenter.R
import kotlinx.android.synthetic.main.activity_cadastro_de_usuario.*
import kotlinx.android.synthetic.main.activity_nova_publicacao.*
import kotlinx.android.synthetic.main.activity_perfil_usuario.*
import kotlinx.android.synthetic.main.activity_perfil_usuario.buttonAbrirGaleria
import kotlinx.android.synthetic.main.activity_perfil_usuario.fotoPerfilUsuario
import kotlinx.android.synthetic.main.toolbar.*

class novaPublicacao : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_publicacao)

        insertToolbar()
        buttonAbrirGaleria.setOnClickListener(this)

        enviarPublicacao.setOnClickListener(this)
    }

    private fun insertToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Nova Publicação"
    }

    override fun onClick(view: View) {
        val id = view.id

        when (id) {
            R.id.buttonAbrirGaleria -> {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                startActivityForResult(intent, 5000)
            }
            R.id.enviarPublicacao -> {
                validaFor()
            }
        }
    }

    private fun validaFor() {
        var valida = true

        if (textTituloPublicacao.length() < 3){
            inputTextPublicacaoTitulo.isErrorEnabled = true
            inputTextPublicacaoTitulo.error = "É necessario Digitar um titulo"
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null){
            val inputString = contentResolver.openInputStream( data.data!!)
            val bitmap = BitmapFactory.decodeStream(inputString)
            fotoPublicacao.scaleType = ImageView.ScaleType.CENTER_CROP
            fotoPublicacao.setImageBitmap(bitmap)
        }
    }
}