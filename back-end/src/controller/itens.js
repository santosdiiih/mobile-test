// const { Op } = require("sequelize");
const jwt = require("jsonwebtoken");
const Itens = require("../models/Itens");
const Loja = require("../models/Loja");

module.exports = {

    // Listar todos itens
    async list(request, response) {
        const itens = await Itens.findAll();

        response.send(itens);
    },

    //Criação de ITENS
    async store(request, response) {

        const { itemId } = request.params;

        const { nome, imagem, descricao, valor, quantidade_de_item } = request.body;

        const loja = await Loja.findByPk(itemId);

        if (!loja)
            return response.status(404).send({ erro: "Loja não encontrada" })


        let itens = await loja.createItem(
            { nome, imagem, descricao, valor, quantidade_de_item });

        response.status(201).send({
            itens: {
                nome: itens.nome,
                imagem: itens.imagem,
                descricao: itens.descricao,
                valor: itens.valor,
                quantidade_de_Item: itens.quantidade_de_Item
            }
        });
    },

    async delete(req, res) {
        //pegando o id do item a apagar
        const { idItem } = req.params;

        //procura o item pelo id
        let item = await Itens.findByPk(idItem);

        //se a item não existir, retorna not found
        if (!item) {
            return res.status(404).send({ error: "Item não encontrada" })
        }

        // efetua a exclusão do item
        await item.destroy();

        res.status(204).send();
    },

    //editar itens
    async editar(request, response) {
        const { nome, imagem, descricao, valor, qquantidade_de_Item } = request.body;
        const { idItem } = request.params;
        let itens = await Itens.findByPk(idItem);

        await itens.update({ nome, imagem, descricao, valor, quantidade_de_Item });

        response.status(201).send({
            itens: {
                nome: itens.nome,
                imagem: itens.imagem,
                descricao: itens.descricao,
                valor: itens.valor,
                quantidade_de_Item: itens.quantidade_de_Item,
            }
        });
    }
}