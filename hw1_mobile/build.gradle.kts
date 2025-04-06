plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "ir.sharif.mobile"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
}

application {
    mainClass.set("MainKt")
}