package com.example.webgamescenter.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.webgamescenter.R
import com.example.webgamescenter.adapter.PublicacaoReciclerAdapter
import com.example.webgamescenter.data.Datasource
import com.example.webgamescenter.http.HttpHelperPublicacao
import com.example.webgamescenter.http.HttpHelperUsuario
import kotlinx.android.synthetic.main.activity_index.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.doAsync

class index : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        insertToolbar()
        novoPost.setOnClickListener(this)
         val recyclerView = recyclerViewPublicacao
         recyclerView.layoutManager = LinearLayoutManager(applicationContext)
         recyclerView.adapter = PublicacaoReciclerAdapter(Datasource.getCardPublicacao())

        // resgata as informaçoes gravadas no banco
         doAsync {
            val http = HttpHelperPublicacao()
            http.getPublicacao()
         }
    }



    private fun insertToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Publicações"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       when (item.itemId) {
           R.id.menuLoja -> {
               val intent = Intent(this, loja::class.java)
               startActivity(intent)
               //Toast.makeText(this, "menu Loja", Toast.LENGTH_SHORT).show()
           }
           R.id.menuPerfil -> {
                val intent = Intent(this, perfilUsuario::class.java)
               startActivity(intent)
           }
            else -> {
                onBackPressed()
            }

       }

        return true
    }

    override fun onClick(view: View) {
        val id = view.id
        when (id) {
            R.id.novoPost -> {
                val intent = Intent(this, novaPublicacao::class.java)
                startActivity(intent)
            }
        }
    }
}
