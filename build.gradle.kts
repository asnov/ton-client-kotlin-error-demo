//import java.util.regex.Pattern.compile

plugins {
    kotlin("jvm") version "1.8.20"
    application
    id("com.adarshr.test-logger") version "3.2.0"
}

group = "me.alexnew"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    implementation("ch.qos.logback:logback-classic:1.4.7")

    // https://mvnrepository.com/artifact/org.slf4j/slf4j-api
    implementation("org.slf4j:slf4j-api:2.0.7")

    // Everscale support https://github.com/mdorofeev/ton-client-kotlin
    implementation("ee.nx-01.tonclient:ton-client-kotlin:0.0.62")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}