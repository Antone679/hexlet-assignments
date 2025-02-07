package exercise;

import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.io.File;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String src1, String src2, String dest) {
        CompletableFuture<String> readFirstFile = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(Path.of(src1));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).exceptionally(ex -> {
            System.out.println("Ooops..." + ex.getMessage());
            return null;

        });

        CompletableFuture<String> readSecondFile = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(Path.of(src2));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).exceptionally(ex -> {
            System.out.println("Ooops..." + ex.getMessage());
            return null;

        });

        return readFirstFile.thenCombine(readSecondFile, (first, second) -> {
            String result = "";

            if (first != null && second != null) {

                result = first.trim() + " " + second.trim();
                try {
                    Files.writeString(Path.of(dest), result, StandardOpenOption.APPEND,
                            StandardOpenOption.CREATE);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return result;
        });

    }

    public static CompletableFuture<Long> getDirectorySize(String dir) {
        return CompletableFuture.supplyAsync(
                () -> {
                    long sum = 0;
                    for (File file : Objects.requireNonNull(new File(dir).listFiles())) {
                        if (file.isFile()) {
                            sum += file.length();
                        }
                    }
                    return sum;
                }).exceptionally(ex -> {
            System.out.println("Ooops..." + ex.getMessage());
            return null;
        });
    }


    public static void main(String[] args) throws Exception {
        // BEGIN
        String data = unionFiles("src/main/resources/file1.txt",
                "src/main/resources/file2.txt", "src/main/resources/file3.txt").get();
        System.out.println(data);

        long size = getDirectorySize("src/main/resources").get();
        System.out.println(size);
        // END
    }
}

