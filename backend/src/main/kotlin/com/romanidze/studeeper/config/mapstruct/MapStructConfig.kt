package com.romanidze.studeeper.config.mapstruct

import org.mapstruct.MapperConfig
import org.mapstruct.InjectionStrategy
import org.mapstruct.ReportingPolicy

/**
 *
 * Interface with config for MapStruct
 *
 * 17.11.2019
 * @author Andrey Romanov
 *
 */
@MapperConfig(
   componentModel = "spring",
   unmappedTargetPolicy = ReportingPolicy.IGNORE,
   injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
interface MapStructConfig