plugins {
    java
    kotlin("jvm") version "2.0.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

sourceSets {
    main {
        java {
            srcDirs(
                *file("LeetCode").listFiles()!!
            )
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("script-runtime"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}