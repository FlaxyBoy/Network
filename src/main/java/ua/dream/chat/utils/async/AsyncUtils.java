package ua.dream.chat.utils.async;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class AsyncUtils {

    private static final Logger logger = LogManager.getLogger(AsyncUtils.class);

    public static void runAsync(@NotNull Runnable runnable) {
        CompletableFuture.runAsync(() -> {
            try {
                runnable.run();
            } catch (Exception e) {
                logger.trace(e);
            }
        });
    }

}
