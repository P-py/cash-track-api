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
	implementation("org.apache.tomcat.embed:tomcat-embed-core:11.0.3")
	implementation("org.springframework.boot:spring-boot-starter-validation:3.4.5")
	implementation("org.springframework.boot:spring-boot-starter-web:3.4.5")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.3")
	implementation("org.jetbrains.kotlin:kotlin-reflect:2.1.20")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.1.20")
	implementation("org.springframework.boot:spring-boot-devtools:3.4.5")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.4.5")
	implementation("org.postgresql:postgresql:42.7.5")
	implementation("org.hibernate.orm:hibernate-core:6.6.13.Final")
	implementation("org.flywaydb:flyway-core:11.8.0")
	implementation("org.flywaydb:flyway-database-postgresql:11.8.0")
	implementation("org.springframework.boot:spring-boot-starter-cache:3.4.5")
	implementation("io.jsonwebtoken:jjwt-api:0.12.6")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")
	implementation("org.springframework.security:spring-security-web:6.3.5")
	implementation("org.springframework.boot:spring-boot-starter-security:3.4.5")
	implementation("org.springframework.boot:spring-boot-starter-data-redis:3.4.5")
	implementation("org.springframework:spring-context:6.1.14")

	testImplementation("org.springframework.boot:spring-boot-starter-test:3.4.5")
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
