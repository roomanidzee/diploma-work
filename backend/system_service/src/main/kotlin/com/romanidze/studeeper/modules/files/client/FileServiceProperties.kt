package com.romanidze.studeeper.modules.files.client

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * 06.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
@ConfigurationProperties("clients.files")
class FileServiceProperties {

    lateinit var host: String
    lateinit var port: String

}