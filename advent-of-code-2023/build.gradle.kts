plugins {
    kotlin("jvm") version "1.9.20"
    application
}

group = "tech.compile"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit:junit:4.13.1")
    testImplementation("org.amshove.kluent:kluent:1.73")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.8.2")
}

kotlin {
    jvmToolchain(19)
}

application {
    mainClass.set("MainKt")
}