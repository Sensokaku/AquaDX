import java.time.Instant
import java.time.format.DateTimeFormatter
import java.time.ZoneId

plugins {
    java
    kotlin("plugin.lombok") version "1.9.22"
    kotlin("jvm") version "1.9.22"
    id("io.freefair.lombok") version "8.1.0"
    id("org.springframework.boot") version "2.7.11"
}

apply(plugin = "io.spring.dependency-management")

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    // Spring boot
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-jetty")
    implementation("io.netty:netty-all")
    implementation("org.apache.commons:commons-lang3")
    implementation("org.apache.httpcomponents:httpclient")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.springframework.security:spring-security-test")
    
    // Database
    runtimeOnly("com.mysql:mysql-connector-j:8.3.0")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client:3.3.2")
    runtimeOnly("org.xerial:sqlite-jdbc:3.45.1.0")
    implementation("com.github.gwenn:sqlite-dialect:0.1.4")
    
    // JSR305 for nullable
    implementation("com.google.code.findbugs:jsr305:3.0.2")

    // Others
    implementation("commons-fileupload:commons-fileupload:1.5")
}

group = "icu.samnya"
version = "0.0.47"
description = "Aqua Server"
java.sourceCompatibility = JavaVersion.VERSION_17

val buildTime: String by extra(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z").withZone(ZoneId.of("UTC")).format(Instant.now()))

tasks.processResources {
    filesMatching("**/application.properties") {
        expand(project.properties)
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}
