package com.example.webgamescenter.http

import com.example.webgamescenter.Classes.Usuario
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

class HttpHelperLogin {

    // determina a url a ser solicitada
    val URL = "http://192.168.100.106:3333/sessao"

    // criando um cliente http
    val usuario = OkHttpClient()

    fun login(json: String) : Usuario {

        // definindo o cabeçalho
        val headerHttp = MediaType.parse("application/json; charset=uf-8")

        // criando o body da requisição
        val body = RequestBody.create(headerHttp, json)

        // construir a requisição http para o servidor
        var request = Request.Builder().url(URL).post(body).build()

        // utiliza o client para fazer a requisição e recebr a resposta
        var response = usuario.newCall(request).execute()

        val responseBody = response.body()

        // instanciando a clase usuario
        var usuario = Usuario()

        if(responseBody!!.contentLength().toInt() != 0){
            var json = responseBody.string()
            var gson = Gson()
            usuario = gson.fromJson(json, Usuario::class.java)
        }

        return usuario

    }
}