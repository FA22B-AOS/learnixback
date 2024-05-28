plugins {
	java
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "de.szut"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
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
	implementation ("org.springframework.boot:spring-boot-starter-web")
	testImplementation ("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
	}
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
	implementation ("io.jsonwebtoken:jjwt-api:0.12.5")
	runtimeOnly ("io.jsonwebtoken:jjwt-impl:0.12.5")
	runtimeOnly ("io.jsonwebtoken:jjwt-jackson:0.12.5") // or jjwt-gson or jjwt-orgjson
	implementation ("javax.annotation:javax.annotation-api:1.3.2")
	implementation ("com.auth0:java-jwt:4.4.0")
	implementation ("org.apache.httpcomponents:httpclient:4.5.13")
////	implementation("org.springframework.security:spring-security-oauth2-resource-server")
////	implementation ("org.springframework.security:spring-security-oauth2-jose")
////	implementation ("org.keycloak:keycloak-core:24.0.4")
//	implementation ("org.springframework.boot:spring-boot-starter-security")
	implementation("com.auth0:java-jwt:4.4.0")
	implementation("com.auth0:jwks-rsa:0.22.1")





}

tasks.withType<Test> {
	useJUnitPlatform()
}
