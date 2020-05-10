package com.romanidze.studeeper.modules.facility.dto

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 10.05.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
data class AnalysisResult(

    @JsonProperty("result")
    val analysisInfo: List<Float>

)