plugins {
    application
    idea
    jacoco
    checkstyle
    id("io.freefair.lombok") version "8.4"
    id("se.patrikerdes.use-latest-versions") version "0.2.18"
    id("com.github.ben-manes.versions") version "0.51.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("org.sonarqube") version "4.4.1.3373"
    id("io.spring.dependency-management") version "1.1.4"


}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
    testImplementation("org.assertj:assertj-core:3.25.2")
    testImplementation("org.mockito:mockito-core:5.10.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.10.0")
    testImplementation("com.squareup.okhttp3:mockwebserver:5.0.0-alpha.12")

    implementation("com.konghq:unirest-java:3.14.5")
    implementation("com.konghq:unirest-objectmapper-jackson:3.14.5")
    implementation("org.jsoup:jsoup:1.17.2")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    implementation("org.apache.commons:commons-text:1.11.0")
    implementation("commons-validator:commons-validator:1.8.0")

    implementation("gg.jte:jte:3.1.9")
    implementation("org.slf4j:slf4j-simple:2.1.0-alpha1")

    implementation("io.javalin:javalin:5.6.3")
    implementation("io.javalin:javalin-bundle:5.6.3")
    implementation("io.javalin:javalin-rendering:5.6.3")

    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("com.h2database:h2:2.2.224")
    implementation("org.postgresql:postgresql:42.7.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    reports {
        xml.required = true
        html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
    }
}

sonar {
    properties {
        property("sonar.projectKey", "sergeloie_java-project-72")
        property("sonar.organization", "sergeloie")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

application {
    mainClass.set("hexlet.code.App")
}
