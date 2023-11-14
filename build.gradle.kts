import com.adarshr.gradle.testlogger.theme.Theme
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("com.adarshr.test-logger") version "4.0.0"
	id("org.springframework.boot") version "3.1.5"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
}

group = "com.inatel"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("mysql:mysql-connector-java:8.0.28")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.mockk:mockk:1.13.8")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()

	testlogger{
		setTheme("mocha")
		setShowExceptions(true)
		setShowStackTraces(true)
		setShowFullStackTraces(false)
		setShowCauses(true)
		setSlowThreshold(2000)
		setShowSummary(true)
		setShowSimpleNames(false)
		setShowPassed(true)
		setShowSkipped(true)
		setShowFailed(true)
		setShowOnlySlow(false)
		setShowStandardStreams(false)
		setShowPassedStandardStreams(true)
		setShowSkippedStandardStreams(true)
		setShowFailedStandardStreams(true)
		setLogLevel("lifecycle")
	}
}
