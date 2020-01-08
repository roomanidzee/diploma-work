package com.romanidze.studeeper.modules.files.domain

import com.github.pozo.KotlinBuilder
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.UUID

/**
 * 06.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Document(collection = "files")
@TypeAlias("file")
@KotlinBuilder
data class FileInfo(

   @Id
   val id: String? = UUID.randomUUID().toString(),

   @Field("user_id")
   val userID: String,

   @Field("path")
   val path: String,

   @Field("type")
   val fileType: String,

   @Field("size")
   val size: Int

)