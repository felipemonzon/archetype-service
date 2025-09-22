import org.gradle.kotlin.dsl.implementation

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.5.5"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("plugin.jpa") version "1.9.25"
    id("io.github.ximtech.jasypt-encrypt-plugin") version "1.3.3"
    id("com.diffplug.spotless") version "7.2.1"
}

group = "com.moontech"
version = "0.0.1-SNAPSHOT"
description = "Archetype kotlin project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

tasks.compileJava {
    sourceCompatibility = "21"
    targetCompatibility = "21"
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-mysql")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:${project.properties["jasypt_version"] as String}")
    implementation("io.jsonwebtoken:jjwt-api:${project.properties["jwt_version"] as String}")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:${project.properties["jwt_version"] as String}")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:${project.properties["jwt_version"] as String}")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:mysql")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

sourceSets {
    main {
        resources.srcDir("src/generated/resources")
    }
}

tasks.encryptProperties {
    System.getenv("JASYPT_ENCRYPTOR_PASSWORD")
}

tasks.decryptProperties {
    System.getenv("JASYPT_ENCRYPTOR_PASSWORD")
}

spotless {
    kotlin {
        ktfmt().googleStyle()
    }
}
