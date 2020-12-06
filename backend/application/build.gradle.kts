
plugins {
	application
	idea
	`java-library`
	id("org.springframework.boot") version "2.3.0.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm")
	kotlin("plugin.spring") version "1.3.72"
	kotlin("plugin.jpa") version "1.3.70"
}

buildscript {
	configurations.classpath
			.resolutionStrategy.force("com.github.pinterest:ktlint:0.36.0")
}

group = "com.newlight77.application"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {

	implementation(project(":domain:authentication"))
	implementation(project(":domain:metafile"))

	implementation(project(":infrastructure:encryption"))
	implementation(project(":infrastructure:login"))
	implementation(project(":infrastructure:notification"))
	implementation(project(":infrastructure:signup"))
	implementation(project(":infrastructure:storage"))

	implementation(project(":infrastructure:iam-client:iam-client-interface"))
	implementation(project(":infrastructure:iam-client:okta-client"))

	implementation("org.springframework.boot:spring-boot-devtools")

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")

	implementation("org.springframework.security:spring-security-oauth2-client")
	implementation("org.springframework.security:spring-security-oauth2-jose")

	implementation("com.okta.spring:okta-spring-boot-starter:1.4.0")
	implementation("com.okta.jwt:okta-jwt-verifier:0.4.0")
	implementation("com.okta.jwt:okta-jwt-verifier-impl:0.4.0")

	implementation("io.jsonwebtoken:jjwt:0.9.1")

	implementation("com.github.ben-manes.caffeine:caffeine:2.8.1")


	implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.3.0.RELEASE")
	// implementation("org.postgresql:postgresql")
	implementation("com.h2database:h2")

	implementation("org.liquibase:liquibase-core:3.8.9")


	// Testing
	testImplementation("org.junit.jupiter:junit-jupiter:5.6.0")
	testImplementation("org.mockito:mockito-junit-jupiter:3.6.0")
	testImplementation("org.mockito:mockito-inline:3.6.0")
	testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
	testImplementation("org.assertj:assertj-core:3.11.1")

	testImplementation("io.rest-assured:spring-mock-mvc:4.3.0") {
		exclude("com.sun.xml.bind:jaxb-osgi")
	}
	testImplementation("io.rest-assured:xml-path:4.2.0")

	testImplementation("io.cucumber:cucumber-java:5.6.0")
	testImplementation("io.cucumber:cucumber-java8:5.6.0")
	testImplementation("io.cucumber:cucumber-junit-platform-engine:5.6.0")
	testImplementation("io.cucumber:cucumber-spring:5.6.0")
	testImplementation("com.github.cukedoctor:cukedoctor-converter:1.2.1")


	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		exclude("junit", "junit")
	}

	testImplementation("com.icegreen:greenmail:1.5.13")

}

application {
	mainClassName = "com.newlight77.core.OktaAuthApplicationKt"
}

tasks.withType<Jar>() {
    baseName = "app-auth-okta"
}

testlogger {
	theme = com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA_PARALLEL
	showExceptions = true
	showStackTraces = true
	showFullStackTraces = true
	showCauses = true
	showSummary = true
	showStandardStreams = false
	showPassedStandardStreams = true
	showSkippedStandardStreams = true
	showFailedStandardStreams = true
}
