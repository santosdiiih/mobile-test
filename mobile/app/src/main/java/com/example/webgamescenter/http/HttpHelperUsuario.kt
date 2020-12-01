package com.example.webgamescenter.http

import com.example.webgamescenter.Classes.Estado
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

class HttpHelperUsuario {

    fun post (json: String) : String {

        // definir a URL do servidor
        val URL = "http://192.168.100.106:3333/usuarios"

        // definindo o cabeçalho
        val headerHttp = MediaType.parse("application/json; charset=uf-8")

        // criando um cliente que dispara a requisição
        val client = OkHttpClient()

        // criando o body da requisição
        val body = RequestBody.create(headerHttp, json)

        // construir a requisição http para o servidor
        var request = Request.Builder().url(URL).post(body).build()

        // utiliza o client para fazer a requisição e recebr a resposta
        var response = client.newCall(request).execute()

        return response.body().toString()

    }



}