import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.3.1"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "2.0.0"
	kotlin("plugin.spring") version "2.0.0"
}

group = "com.cashtrack"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-validation:3.3.1")
	implementation("org.springframework.boot:spring-boot-starter-web:3.3.0")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.2")
	implementation("org.jetbrains.kotlin:kotlin-reflect:2.0.0")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.0.0")
	implementation("org.springframework.boot:spring-boot-devtools:3.3.2")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.5")
	implementation("org.postgresql:postgresql:42.7.3")
	implementation("org.hibernate.orm:hibernate-core:6.5.2.Final")
	implementation("org.flywaydb:flyway-core:10.16.0")
	implementation("org.flywaydb:flyway-database-postgresql:10.16.0")
	implementation("org.springframework.boot:spring-boot-starter-cache:3.3.2")
	implementation("io.jsonwebtoken:jjwt-api:0.12.6")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")

	testImplementation("org.springframework.boot:spring-boot-starter-test:3.3.2")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:2.0.0")
	testImplementation("org.jetbrains.kotlin:kotlin-test:2.0.0")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.assertj:assertj-core:3.26.3")
	testImplementation("io.mockk:mockk-jvm:1.13.12")
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.3")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.3")
	testImplementation("org.testcontainers:postgresql")
	testImplementation("org.testcontainers:junit-jupiter")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
