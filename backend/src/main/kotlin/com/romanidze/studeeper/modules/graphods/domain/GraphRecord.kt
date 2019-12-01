package com.romanidze.studeeper.modules.graphods.domain

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Property
import org.neo4j.ogm.id.UuidStrategy
import java.time.LocalDateTime

/**
 * 01.12.2019
 *
 * Domain for record in Neo4J
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@NodeEntity
data class GraphRecord (


        @Id
        @GeneratedValue(strategy = UuidStrategy::class)
        val id: String,

        @Property(name = "specialization")
        val specialization: String,

        @Property(name = "graduation")
        val graduation: LocalDateTime,

        @Property(name = "profile_id")
        val profileID: Long
)