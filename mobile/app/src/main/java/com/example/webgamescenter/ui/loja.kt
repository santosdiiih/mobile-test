package com.example.webgamescenter.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.webgamescenter.R
import com.example.webgamescenter.adapter.LojaReciclerViewAdapter
import com.example.webgamescenter.data.DatasourceLoja
import kotlinx.android.synthetic.main.activity_index.*
import kotlinx.android.synthetic.main.activity_loja.*
import kotlinx.android.synthetic.main.poup_up_loja.*
import kotlinx.android.synthetic.main.poup_up_loja.view.*
import kotlinx.android.synthetic.main.toolbar.*
import org.w3c.dom.Text

class loja : AppCompatActivity(), View.OnClickListener {

    private var contador: Int = 0
    private lateinit var somar: ImageButton
    private lateinit var subtrair: ImageButton
    private lateinit var txtContador: TextView
    private lateinit var cancelar:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loja)

        val recyclerView = recyclerViewLoja
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = LojaReciclerViewAdapter(DatasourceLoja.getLoja())

        insertToolbar()
    }
    private fun insertToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Loja"
    }



    fun abrirDialog(view: View) {
        val alertDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.poup_up_loja, null)

        somar = view.findViewById(R.id.soma)
        somar.setOnClickListener(this)

        subtrair = view.findViewById(R.id.subtrai)
        subtrair.setOnClickListener(this)

        txtContador = view.findViewById(R.id.quantidade)


        cancelar = view.findViewById(R.id.btnCancelar)
        cancelar.setOnClickListener(this)

        alertDialog.setView(view)

        val dialog = alertDialog.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    fun incrementar() {
        contador++
        txtContador.text = contador.toString()
    }

    fun decrementar() {
        if (contador > 0){
            contador--
            txtContador.text = contador.toString()
        }
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.soma ->{
                incrementar()
            }
            R.id.subtrai ->{
                decrementar()
            }
            R.id.btnCancelar -> {
                onBackPressed()
            }

        }
    }


}
