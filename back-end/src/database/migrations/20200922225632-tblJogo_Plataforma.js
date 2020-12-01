'use strict';

module.exports = {
    up: async(queryInterface, Sequelize) => {
        return queryInterface.createTable("tblJogo_Plataforma", {
            id: {
                type: Sequelize.INTEGER,
                primaryKey: true,
                autoIncrement: true
            },
            jogo_id: {
                type: Sequelize.INTEGER,
                allowNull: false,
                references: {
                    model:'tblJogo',
                    key: 'id'
                },
                onUpdate: "CASCADE",
                onDelete: "CASCADE"
            },
            plataforma_id: {
                type: Sequelize.INTEGER,
                allowNull: false,
                references: {
                    model:'tblPlataforma',
                    key: 'id'
                },
                onUpdate: "CASCADE",
                onDelete: "CASCADE"
            },
            created_at: {
                type: Sequelize.DATE,
                allowNull: false
            },
            updated_at: {
                type: Sequelize.DATE,
                allowNull: false
            }
        })
    },

    down: async(queryInterface, Sequelize) => {
        return queryInterface.dropTable("tblJogoPlataforma", {})
    }
};