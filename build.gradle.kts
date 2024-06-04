buildscript {
	configurations.classpath {
		// lock build plugin versions
		resolutionStrategy.activateDependencyLocking()
	}
}

plugins {
	application
	alias(libs.plugins.springBoot)
	alias(libs.plugins.confResolution)
}

java {
	sourceCompatibility = JavaVersion.VERSION_21
	targetCompatibility = JavaVersion.VERSION_21
}

group = "dev.nakamorichi"
version = "0.0.1-SNAPSHOT"

springBoot.buildInfo {
	// include additional properties, such as Git commit & branch info into
	// build artifact so that the information can be used e.g. when building container image
	properties {
		additional.set(mapOf(
			"commit" to "TODO",
			"branch" to "TODO"
		))
	}
	excludes.set(setOf("time")) // exclude build time from build artifact in order to improve image build caching
}

// creates JAR artifact suitable for image build
tasks.bootJar {
	archiveFileName.set("app.jar") // use same artifact name in each service to simplify image build job logic
	manifest {
		attributes(
			"Build-Revision" to "TODO",
			"Build-Jdk" to "${System.getProperty("java.vendor")} ${System.getProperty("java.version")}",
			"Build-OS" to "${System.getProperty("os.name")} ${System.getProperty("os.arch")} ${System.getProperty("os.version")}",
			"Created-By" to "Gradle ${gradle.gradleVersion}"
		)
	}
}

// creates bootable directory structure suitable for layered cache-optimized container image build
tasks.register<JavaExec>("bootDir") {
	val outputDirectory = project.layout.buildDirectory.dir("bootDir")
	outputs.dir(outputDirectory)
	classpath(tasks.named("bootJar"))
	systemProperty("jarmode", "tools")
	args("extract", "--layers", "--launcher")
	workingDir(outputDirectory)
}

dependencyLocking {
	// lock dependency versions
	lockAllConfigurations()
	lockMode.set(LockMode.STRICT)
}

repositories {
	// set dependency repositories (if want to use e.g. internal one, modify this)
	mavenCentral()
}

dependencies {
	implementation(platform(libs.springCloudDependencies))
	implementation(platform(libs.springBootDependencies))
	implementation(libs.bundles.implementation)
	compileOnly(libs.bundles.compileOnly)
	runtimeOnly(libs.bundles.runtimeOnly)
	testImplementation(libs.bundles.testImplementation)
	testRuntimeOnly(libs.bundles.testRuntimeOnly)
	annotationProcessor(libs.lombok)
}

jvmDependencyConflicts {
	logging {
		// enforce same logging backend for all dependencies
		enforceLogback()
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
