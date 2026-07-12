plugins {
    java
    id("io.qameta.allure") version "2.12.0"
}



group = "com.ust.sdet"
version = "0.1.0"


val seleniumVersion = "4.45.0"
val selenideVersion = "7.16.2"
val junitVersion = "5.13.4"
val cucumberVersion = "7.34.3"
val allureVersion = "2.33.0"
val extentVersion = "5.1.2"
val extentCucumberAdapterVersion = "1.14.0"
val slf4jVersion = "2.0.17"
val testcontainersVersion = "2.0.5"
val flywayVersion = "10.22.0"
val postgresqlVersion = "42.7.4"

// Java
java {
    sourceCompatibility = JavaVersion.VERSION_22
    targetCompatibility = JavaVersion.VERSION_22
}

dependencies {
    // BOMs
    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation(platform("io.cucumber:cucumber-bom:$cucumberVersion"))
    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    testImplementation(platform("org.testcontainers:testcontainers-bom:$testcontainersVersion"))

    // Selenium
    testImplementation("org.seleniumhq.selenium:selenium-java:$seleniumVersion")
    testImplementation("com.codeborne:selenide:$selenideVersion")

    // JUnit
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.platform:junit-platform-suite")
    testImplementation("org.junit.jupiter:junit-jupiter:5.13.4")

    //REST Assured
    testImplementation("io.rest-assured:rest-assured:5.5.6")
    testImplementation("io.rest-assured:json-schema-validator:5.5.6")


    // Jackson
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.19.2")
    // Assertions
    testImplementation("org.assertj:assertj-core:3.27.6")

    // Cucumber
    testImplementation("io.cucumber:cucumber-java:7.20.1")
    testImplementation("io.cucumber:cucumber-junit-platform-engine")
    testImplementation("io.cucumber:cucumber-picocontainer")

    // Reporting
    testImplementation("io.qameta.allure:allure-junit5")
    testImplementation("io.qameta.allure:allure-cucumber7-jvm")
    testImplementation("com.aventstack:extentreports:$extentVersion")
    testImplementation("tech.grasshopper:extentreports-cucumber7-adapter:$extentCucumberAdapterVersion")

    // Logging
    testImplementation("org.slf4j:slf4j-simple:$slf4jVersion")

    // Database
    testImplementation("io.github.cdimascio:java-dotenv:5.2.2")
    testImplementation("com.mysql:mysql-connector-j:9.3.0")

    // Testcontainers
    testImplementation("org.testcontainers:testcontainers-junit-jupiter:$testcontainersVersion")
    testImplementation("org.testcontainers:testcontainers-postgresql:$testcontainersVersion")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release.set(22)
}

// Common configuration for all test tasks
tasks.withType<Test>().configureEach {

    useJUnitPlatform()

    systemProperty(
        "baseUrl",
        providers.gradleProperty("baseUrl")
            .orElse("http://localhost:5173")
            .get()
    )

    systemProperty(
        "headless",
        providers.gradleProperty("headless")
            .orElse("false")
            .get()
    )

    systemProperty(
        "browser",
        providers.gradleProperty("browser")
            .orElse("chrome")
            .get()
    )

    systemProperty(
        "build.label",
        providers.gradleProperty("buildLabel")
            .orElse("gradle-local")
            .get()
    )

    systemProperty("cucumber.publish.quiet", "true")

    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
        exceptionFormat =
            org.gradle.api.tasks.testing.logging.TestExceptionFormat.SHORT
    }
}

fun Test.useProjectTestClasses() {
    testClassesDirs = sourceSets.test.get().output.classesDirs
    classpath = sourceSets.test.get().runtimeClasspath
}


val testSourceSet = sourceSets.test.get()

fun Test.configureTestTask() {

    testClassesDirs = testSourceSet.output.classesDirs
    classpath = testSourceSet.runtimeClasspath

    useJUnitPlatform()

    systemProperty(
        "cucumber.plugin",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    )
}



val apiTest by tasks.registering(Test::class) {

    description = "Runs all API tests"

    group = "verification"

    useJUnitPlatform()

    useProjectTestClasses()

    include("**/*ApiTest.class")
}


val integrationTest by tasks.registering(Test::class) {

    description = "Runs database integration tests."

    group = "verification"

    useProjectTestClasses()

    include("**/OrderRepositoryIntegrationTest.class")

    maxParallelForks = 1
}


val cucumberTest by tasks.registering(Test::class) {

    description = "Runs Cucumber Scenarios"

    group = "verification"

    useJUnitPlatform()

    useProjectTestClasses()

    include("**/RunCucumberTest.class")
}

// Smoke

val smoke by tasks.registering(Test::class) {

    description = "Runs Smoke scenarios"

    group = "verification"

    configureTestTask()

    systemProperty("cucumber.filter.tags", "@smoke")
}

// API

val api by tasks.registering(Test::class) {

    description = "Runs API scenarios"

    group = "verification"

    configureTestTask()

    systemProperty("cucumber.filter.tags", "@api")
}

// UI

val ui by tasks.registering(Test::class) {

    description = "Runs UI scenarios"

    group = "verification"

    configureTestTask()

    systemProperty("cucumber.filter.tags", "@ui")
}

// Database

val db by tasks.registering(Test::class) {

    description = "Runs Database scenarios"

    group = "verification"

    configureTestTask()

    systemProperty("cucumber.filter.tags", "@db")
}

// Negative

val negative by tasks.registering(Test::class) {

    description = "Runs Negative scenarios"

    group = "verification"

    configureTestTask()

    systemProperty("cucumber.filter.tags", "@negative")
}

// Security

val security by tasks.registering(Test::class) {

    description = "Runs Security scenarios"

    group = "verification"

    configureTestTask()

    systemProperty("cucumber.filter.tags", "@security")
}

// E2E

val e2e by tasks.registering(Test::class) {

    description = "Runs End-to-End scenarios"

    group = "verification"

    configureTestTask()

    systemProperty("cucumber.filter.tags", "@e2e")
}

// Reporting

val reporting by tasks.registering(Test::class) {

    description = "Runs Reporting framework tests"

    group = "verification"

    configureTestTask()

    include("**/ReportingConfigurationTest.class")
}

tasks.register("projectBuildSummary") {

    description = "Displays the Gradle command reference."

    group = "help"

    doLast {

        println(
            """
                PROJECT COMMAND REFERENCE
                Build Project
                -------------
                ./gradlew clean build
                
                Compile Test Classes
                --------------------
                ./gradlew testClasses
                
                Run All UI Tests
                ----------------
                ./gradlew test
                
                Run End-to-End Test
                -------------------
                ./gradlew e2eTest --no-configuration-cache
                
                Run Functional Test
                -------------------
                ./gradlew functionalTest --no-configuration-cache
                
                Run Integration Test
                --------------------
                ./gradlew integrationTest --no-configuration-cache
                
                Run Cucumber Smoke Tests
                ------------------------
                ./gradlew cucumberSmoke -Pheadless=true --no-configuration-cache
                
                ========================================================
            """.trimIndent()
        )
    }

    allure {

        version.set("2.34.1")

        adapter {

            aspectjWeaver.set(true)

            frameworks {

                junit5 {
                    adapterVersion.set("2.34.1")
                }
            }
        }
    }
}