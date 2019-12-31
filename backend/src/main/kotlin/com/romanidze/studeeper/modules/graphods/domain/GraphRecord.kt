package com.romanidze.studeeper.modules.graphods.domain

import com.github.pozo.KotlinBuilder
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Property

import java.time.LocalDateTime
import java.util.UUID

/**
 * 01.12.2019
 *
 * Domain for record in Neo4J
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@KotlinBuilder
@NodeEntity("GraphRecord")
data class GraphRecord (

        @Id
        val id: String? = UUID.randomUUID().toString(),

        @Property(name = "specialization")
        val specialization: String,

        @Property(name = "graduation")
        val graduation: LocalDateTime,

        @Property(name = "profile_id")
        val profileID: String
)