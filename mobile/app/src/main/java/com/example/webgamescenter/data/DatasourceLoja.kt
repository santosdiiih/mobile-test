package com.example.webgamescenter.data

import com.example.webgamescenter.model.Loja

class DatasourceLoja {

    companion object {
        fun getLoja() : ArrayList<Loja>{

            var produto = ArrayList<Loja>()
            produto.add(Loja(1, "Gift Cards"))
            produto.add(Loja(2, "pokemon"))
            produto.add(Loja(3,"Lalaland"))

            return produto
        }
    }

}