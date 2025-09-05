package com.moontech.archetype.domain.model

/**
 * DTO para manejar la entrada y salida de datos en la API.
 * No forma parte del dominio.
 *
 * @author Felipe Monzón
 * @since 05 sept, 2025
 **/
data class Authority(
    /** Identificador del perfil.  */
    val id: Long,
    /** Descripción del perfil.  */
    val name: String
)