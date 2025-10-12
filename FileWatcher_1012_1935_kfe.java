// 代码生成时间: 2025-10-12 19:35:47
package com.example.filewatcher;

import io.micronaut.context.annotation.Requires;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.runtime.Micronaut;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Requires(env = "filewatcher")
public class FileWatcher {

    private final WatchService watchService = FileSystems.getDefault().newWatchService();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Path directoryToWatch;
    private volatile boolean running = true;

    public FileWatcher(@NonNull String directoryPath) {
        this.directoryToWatch = Path.of(directoryPath);
    }

    public void start() {
        executorService.submit(this::monitorDirectory);
    }

    private void monitorDirectory() {
        try {
            directoryToWatch.register(watchService,
                    java.nio.file.StandardWatchEventKinds.ENTRY_CREATE,
                    java.nio.file.StandardWatchEventKinds.ENTRY_DELETE,
                    java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY);

            while (running) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    if (kind == java.nio.file.StandardWatchEventKinds.OVERFLOW) {
                        continue;
                    }

                    @SuppressWarnings("unchecked")
                    WatchEvent<Path> pathEvent = (WatchEvent<Path>) event;
                    Path filePath = pathEvent.context();
                    System.out.println("File Change Detected: " + filePath);

                    notifyChange(filePath, kind);
                }

                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ClosedWatchServiceException e) {
            System.err.println("WatchService has been closed.");
        } catch (Exception e) {
            System.err.println("An error occurred while monitoring directory: " + e.getMessage());
        } finally {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
            }
        }
    }

    private void notifyChange(Path filePath, WatchEvent.Kind<?> kind) {
        // Implement notification logic here
        // For demo purposes, just print to console
        System.out.println("Change type: " + kind + ", File: " + filePath);
    }

    public void stop() {
        running = false;
    }

    public static void main(String[] args) {
        Micronaut.run(FileWatcher.class, args);
    }
}
