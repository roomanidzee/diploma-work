package com.romanidze.studeeper.modules.graphods.domain

import com.github.pozo.KotlinBuilder
import org.neo4j.springframework.data.core.schema.Id
import org.neo4j.springframework.data.core.schema.Node
import org.neo4j.springframework.data.core.schema.Property

import java.time.LocalDateTime

/**
 * 01.12.2019
 *
 * Domain for record in Neo4J
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@KotlinBuilder
@Node("GraphRecord")
data class GraphRecord (


        @Id
        val id: String,

        @Property(name = "specialization")
        val specialization: String,

        @Property(name = "graduation")
        val graduation: LocalDateTime,

        @Property(name = "profile_id")
        val profileID: Long
)