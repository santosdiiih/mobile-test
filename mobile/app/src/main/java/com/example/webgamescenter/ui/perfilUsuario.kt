package com.example.webgamescenter.ui

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.webgamescenter.R
import com.example.webgamescenter.R.*
import com.example.webgamescenter.R.id.*
import kotlinx.android.synthetic.main.activity_cadastro_de_usuario.*
import kotlinx.android.synthetic.main.toolbar.*

class perfilUsuario : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_perfil_usuario)

        insertToolbar()
        buttonAbrirGaleria.setOnClickListener(this)

    }


    private fun insertToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Perfil Usuario"

    }

    override fun onClick(view: View) {
        val id = view.id

        when (id){
            R.id.buttonAbrirGaleria -> {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                startActivityForResult(intent, 5000)

            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null){
            val inputString = contentResolver.openInputStream( data.data!!)
            val bitmap = BitmapFactory.decodeStream(inputString)
            fotoPerfilUsuario.scaleType = ImageView.ScaleType.CENTER_CROP
            fotoPerfilUsuario.setImageBitmap(bitmap)
        }
    }
}