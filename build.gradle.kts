import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.transformers.*
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

group = "com.aphisiit.springlambda"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

val springCloudVersion = "2020.0.3"
val awsLambdaCore = "1.2.1"
val awsLambdaEvents = "3.10.0"
val springCloudStater = "3.1.6"

plugins {
	id("org.springframework.boot") version "2.7.0"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("com.github.johnrengelman.shadow") version "7.1.2"
	id("maven-publish")
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.cloud:spring-cloud-starter-function-web:$springCloudStater")
	implementation("org.springframework.cloud:spring-cloud-function-core:$springCloudStater")
	implementation("org.springframework.cloud:spring-cloud-function-context:$springCloudStater")

	implementation("org.springframework.cloud:spring-cloud-function-adapter-aws:$springCloudStater")

	compileOnly("com.amazonaws:aws-lambda-java-core:$awsLambdaCore")
	compileOnly("com.amazonaws:aws-lambda-java-events:$awsLambdaEvents")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
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

tasks.withType<Jar> {
	manifest {
		attributes("Main-Class" to "com.aphisiit.springlambda.SpringLambdaApplication")
	}
}

tasks.assemble {
	dependsOn("shadowJar")
}

tasks.withType<ShadowJar> {
	archiveFileName.set("spring-lambda.jar")
	dependencies {
		exclude("org.springframework.cloud:spring-cloud-function-web:${springCloudVersion}")
	}
	// Required for Spring
	mergeServiceFiles()
	append("META-INF/spring.handlers")
	append("META-INF/spring.schemas")
	append("META-INF/spring.tooling")
	transform(PropertiesFileTransformer::class.java) {
		paths.add("META-INF/spring.factories")
		mergeStrategy = "append"
	}
}


