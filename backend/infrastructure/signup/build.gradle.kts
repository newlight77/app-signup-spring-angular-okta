import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm")
	kotlin("plugin.spring") version "1.3.72"
	kotlin("plugin.jpa") version "1.3.72"
}

group = "com.newlight77.infrastructure"
version = "0.0.1-SNAPSHOT"


repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://kotlin.bintray.com/kotlinx") }
}

dependencies {
	implementation(project(":domain:authentication"))
	implementation(project(":domain:metafile"))
	implementation(project(":infrastructure:encryption"))
	implementation(project(":infrastructure:notification"))
	implementation(project(":infrastructure:storage"))

	implementation(project(":infrastructure:iam-client:iam-client-interface"))

	implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.3.0.RELEASE")
	implementation("org.springframework.boot:spring-boot-starter-validation:2.3.0.RELEASE")

}

tasks.withType<Jar>() {
	baseName = "infra-signup"
}