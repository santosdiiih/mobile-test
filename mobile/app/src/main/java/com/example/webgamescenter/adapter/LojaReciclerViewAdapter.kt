package com.example.webgamescenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.webgamescenter.R
import com.example.webgamescenter.model.Loja

import kotlinx.android.synthetic.main.layout_card_loja_view_holder.view.*

class LojaReciclerViewAdapter (var listarProdutos: ArrayList<Loja>) : RecyclerView.Adapter<LojaReciclerViewAdapter.LojaViewHolder>() {
    class LojaViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(loja: Loja){
            itemView.titleProduto.text = loja.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int) : LojaViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_card_loja_view_holder, parent, false)
        return LojaViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return listarProdutos.size
    }

    override fun onBindViewHolder(holder: LojaReciclerViewAdapter.LojaViewHolder, position: Int) {
        val produto = listarProdutos[position]
        holder.bind(produto)
    }
}