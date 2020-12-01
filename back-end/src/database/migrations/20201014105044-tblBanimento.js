'use strict';

module.exports = {
  up: async (queryInterface, Sequelize) => {
    return queryInterface.createTable("tblBanimento", {
      id: {
          type: Sequelize.INTEGER,
          primaryKey: true,
          autoIncrement: true
      },
      usuario_id: {
        type: Sequelize.INTEGER,
        references: {
            model:'tblUsuario',
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

  down: async (queryInterface, Sequelize) => {
    return queryInterface.dropTable("tblBanimento", {})
  }
};
