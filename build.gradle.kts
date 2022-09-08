plugins {
    kotlin("jvm") version "1.7.10"
}

group = "org.github.damien-ir"
version = "2022-09-07"
val junitVersion = "5.9.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    implementation(kotlin("stdlib-jdk8"))
}


tasks.withType<Test> {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
    }
}