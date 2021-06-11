package com.example.guests.service.constants

/**
 * Todas as constantes utilizadas no banco de dados
 * Tabelas, Colunas
 */
class DataBaseConstants private constructor() {

    /**
     * Tabelas disponíveis no banco de dados com suas colunas
     */
    object GUEST {
        const val TABLE_NAME = "Guest"
        const val DB_NAME = "guestDB"

        object COLUMNS {
            const val ID = "id"
            const val NAME = "name"
            const val PHONE = "phone"
            const val PRESENCE = "presence"
        }
    }
}