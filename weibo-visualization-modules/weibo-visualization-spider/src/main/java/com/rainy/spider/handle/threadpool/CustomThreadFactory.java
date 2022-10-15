package com.rainy.spider.handle.threadpool;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadFactory implements ThreadFactory {

    private final String namePrefix;
    private final AtomicInteger atomicInteger = new AtomicInteger(1);

    public CustomThreadFactory(String whatNamePrefix) {
        namePrefix = "From CustomThreadFactory" + whatNamePrefix + "-Worker-";
    }

    @Override
    public Thread newThread(@NotNull Runnable r) {
        String threadName = namePrefix + atomicInteger.getAndIncrement();
        Thread thread = new Thread(r, threadName);
        System.out.println(thread.getName());
        return thread;
    }
}
