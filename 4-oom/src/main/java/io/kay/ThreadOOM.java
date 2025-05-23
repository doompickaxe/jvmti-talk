package io.kay;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ThreadOOM {

    public static void execute() {
        System.out.println("Thread ~fork bombing the JVM ...");
        List<Thread> list = new ArrayList<>();
        try {
            for (int i = 0; i < 100_000; i++) {
                Thread thread = new Thread(() -> {
                    while (true) {
                        try {
                            Thread.sleep(Duration.ofMinutes(5));
                            System.out.println("Thread is stopping regularly");
                        } catch (InterruptedException ign) {
                            System.out.println("Thread is interrupted");
                        }
                    }
                });
                thread.start();
                // Hold onto the thread
                list.add(thread);
                if (list.size() % 5_000 == 0) {
                    System.out.println("Spawned [" + list.size() + "] threads ...");
                }
            }
        } catch (Exception t) {
            System.out.println(t);
        } finally {
            System.out.println("final list size: " + list.size());
        }
    }
}
