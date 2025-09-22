package com.moontech.archetype.infrastructure.persistence.entity

import jakarta.persistence.*

/**
 * Entidad de Persistencia, que es distinta de la entidad de dominio. Sigue el Principio de
 * Segregación de Interfaces (ISP).
 *
 * @author Felipe Monzón
 * @since 05 sept, 2025
 */
@Entity
@Table(name = "roles")
data class RoleEntity(
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
  @Column(name = "name", unique = true, nullable = false) val name: String,
)
