FROM eclipse-temurin:21.0.3_9-jre

COPY --link --chown=1000:1000 build/bootDir/app/spring-boot-loader/ .
COPY --link --chown=1000:1000 build/bootDir/app/dependencies/ .
COPY --link --chown=1000:1000 build/bootDir/app/snapshot-dependencies/ .
COPY --link --chown=1000:1000 build/bootDir/app/application/ .

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
