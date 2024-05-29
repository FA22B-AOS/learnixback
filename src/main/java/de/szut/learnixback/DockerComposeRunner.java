package de.szut.learnixback;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

@Component
public class DockerComposeRunner implements DisposableBean {

    public static void startDockerCompose() throws IOException, InterruptedException {
        String osName = System.getProperty("os.name").toLowerCase();
        System.out.println("Betriebssystem: " + osName);

        if (!osName.contains("win")) {
            String scriptPath = "./docker/pg-init-scripts/create-multiple-postgresql-databases.sh";
            ProcessBuilder builder = new ProcessBuilder("chmod", "777", scriptPath);
            Process process = builder.start();
            int exitCode = process.waitFor();
            System.out.println("chmod ausgeführt mit Exit-Code: " + exitCode);
        }

        String composeFile = Paths.get("docker", "docker-compose.yml").toString();
        String projectDirectory = System.getProperty("user.dir");


        System.out.println("Docker Container werden gestartet...");
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("docker-compose", "-f", composeFile, "up", "-d");
        builder.directory(new java.io.File(projectDirectory));
        executeProcess(builder);
    }

    public static void stopDockerCompose() throws IOException, InterruptedException {
        String composeFile = Paths.get("docker", "docker-compose.yml").toString();

        System.out.println("Docker Container werden beendet...");
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("docker-compose", "-f", composeFile, "stop");
        builder.directory(new java.io.File(System.getProperty("user.dir"))); // Verwende das aktuelle Arbeitsverzeichnis
        executeProcess(builder);
    }

    private static void executeProcess(ProcessBuilder builder) throws IOException, InterruptedException {
        Process process = builder.start();
        printProcessOutput(process);

        int exitCode = process.waitFor();
        System.out.println("Prozess beendet mit Exit-Code: " + exitCode);
    }

    private static void printProcessOutput(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    @Override
    public void destroy() throws Exception {
        stopDockerCompose();
    }
}