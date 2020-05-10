package com.romanidze.studeeper.modules.facility.analysis.client

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * 10.05.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
@ConfigurationProperties("clients.analysis")
class AnalysisServiceProperties {

    lateinit var host: String
    lateinit var port: String

}