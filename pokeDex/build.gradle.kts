import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.jvm.tasks.Jar

plugins {
	id("org.springframework.boot") version "2.4.4"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.31"
	kotlin("plugin.spring") version "1.4.31"
	kotlin("plugin.jpa") version "1.4.31"
	kotlin("plugin.serialization") version "1.4.30"
}

group = "com.slearning"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	jcenter()
}

extra["springCloudVersion"] = "2020.0.2"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation ("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation ("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign:2.2.7.RELEASE")
	implementation("org.json:json:20210307")
	implementation("org.postgresql:postgresql:42.2.20")
	implementation("org.flywaydb:flyway-maven-plugin:7.8.2")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
	testImplementation("io.mockk:mockk:1.10.6")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.amshove.kluent:kluent:1.50")
	testImplementation("org.junit.jupiter:junit-jupiter:5.5.0")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

val fatJar = task("fatJar", type = Jar::class) {
	archiveBaseName.set("${project.name}-fat")
	manifest {
		attributes["Implementation-Title"] = "Gradle Jar File With All Dependencies"
		attributes["Implementation-Version"] = archiveVersion
		attributes["Main-Class"] = "com.slearning.pokedex.Main"
	}

	from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
	with(tasks.jar.get() as CopySpec)
}

tasks {
	"build" {
		dependsOn(fatJar)
	}
}
