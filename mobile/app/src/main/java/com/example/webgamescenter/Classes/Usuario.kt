package com.example.webgamescenter.Classes

class Usuario {

    var nome = ""
    var email = ""
    var ultimoNome = ""
    var senha = ""
    var dataDeNascimento = ""
    var nickname = ""
    var sexo = ""
    var estado = ""

    override fun toString(): String {
        return "Usuario(nome='$nome', email='$email', senha='$senha', confirmaSenha='$dataDeNascimento', nickname='$nickname')"
    }


}