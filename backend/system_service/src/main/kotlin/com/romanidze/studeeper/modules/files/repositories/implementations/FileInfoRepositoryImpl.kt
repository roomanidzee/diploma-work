package com.romanidze.studeeper.modules.files.repositories.implementations

import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import com.romanidze.studeeper.modules.files.domain.FileInfo
import com.romanidze.studeeper.modules.files.repositories.interfaces.FileInfoRepository
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * 06.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Repository
class FileInfoRepositoryImpl(private val mongoTemplate: ReactiveMongoTemplate): FileInfoRepository {

    override fun findByType(type: String): Flux<FileInfo> {

        val searchQuery = Query.query(
            Criteria.where("type").`is`(type)
        )

        return this.mongoTemplate.find(searchQuery, FileInfo::class.java)

    }

    override fun findBySize(start: Int, end: Int): Flux<FileInfo> {

        val searchQuery = Query()

        searchQuery.addCriteria(
           Criteria.where("size").gte(start).lte(end)
        )

        return this.mongoTemplate.find(searchQuery, FileInfo::class.java)

    }

    override fun findByID(id: String): Mono<FileInfo> {
        return this.mongoTemplate.findById(id, FileInfo::class.java)
    }

    override fun findAll(): Flux<FileInfo> {
        return this.mongoTemplate.findAll(FileInfo::class.java)
    }

    override fun save(item: FileInfo): Mono<FileInfo> {
        return this.mongoTemplate.save(item)
    }

    override fun delete(item: FileInfo): Mono<DeleteResult> {
        return this.mongoTemplate.remove(item)
    }

    override fun update(item: FileInfo): Mono<UpdateResult> {

        val updateQuery = Query.query(
          Criteria.where("_id").`is`(item.id)
        )

        val updateFunc = Update()

        updateFunc.set("user_id", item.userID)
        updateFunc.set("path", item.path)
        updateFunc.set("type", item.fileType)
        updateFunc.set("size", item.size)

        return this.mongoTemplate.updateFirst(updateQuery, updateFunc, FileInfo::class.java)

    }
}