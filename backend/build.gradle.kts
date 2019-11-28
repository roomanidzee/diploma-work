import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

project.extra.set("versions", mapOf(
        "kotlin" to "1.3.50",
        "logback" to "1.2.3",
        "slf4j" to "1.7.26",
        "jjwt" to "0.9.1",
        "jaxb" to "2.3.1",
        "mapstruct" to "1.3.0.Beta2",
        "jackson" to "2.10.0",
        "kotlin_test" to "3.4.2",
        "fake_mongo" to "1.23.0"
))

plugins {
    id("org.springframework.boot") version "2.2.1.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    kotlin("jvm") version "1.3.50"
    kotlin("plugin.spring") version "1.3.50"
    kotlin("kapt") version "1.3.50"
}

tasks.bootJar {
    archiveName = "app.jar"
}

val versions : Map<String, String> by project.extra

group = "com.romanidze"
version = "0.0.1"

java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

val developmentOnly: Configuration by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
}

repositories {
    mavenCentral()
    mavenLocal()
    maven(url = "https://jitpack.io")
    gradlePluginPortal()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-quartz")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${versions["jackson"]}")

    implementation("org.jetbrains.kotlin:kotlin-reflect:${versions["kotlin"]}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${versions["kotlin"]}")

    implementation("com.github.pozo.mapstruct-kotlin:mapstruct-kotlin:${versions["mapstruct"]}")
    compile("org.mapstruct:mapstruct:${versions["mapstruct"]}")
    kapt("org.mapstruct:mapstruct-processor:${versions["mapstruct"]}")
    kapt("com.github.pozo.mapstruct-kotlin:mapstruct-kotlin-processor:${versions["mapstruct"]}")

    compile("org.springframework.boot:spring-boot-configuration-processor")
    kapt("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    compile("ch.qos.logback:logback-classic:${versions["logback"]}")
    compile("ch.qos.logback:logback-access:${versions["logback"]}")

    compile("org.slf4j:slf4j-api:${versions["slf4j"]}")
    compile("org.slf4j:jul-to-slf4j:${versions["slf4j"]}")
    compile("org.slf4j:log4j-over-slf4j:${versions["slf4j"]}")

    compile("io.jsonwebtoken:jjwt:${versions["jjwt"]}")

    compile("javax.xml.bind:jaxb-api:${versions["jaxb"]}")


    testImplementation("io.kotlintest:kotlintest-runner-junit5:${versions["kotlin_test"]}")
    testImplementation("io.kotlintest:kotlintest-extensions-spring:${versions["kotlin_test"]}")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("de.bwaldvogel:mongo-java-server:${versions["fake_mongo"]}")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
