import java.util.concurrent.CompletableFuture;

public class ServiceA {
    private final ServiceB serviceB;

    public ServiceA(ServiceB serviceB) {
        this.serviceB = serviceB;
    }

    public CompletableFuture<String> processRequest(String request) {
        CompletableFuture<String> result = new CompletableFuture<>();

        CompletableFuture<String> serviceBFuture = CompletableFuture.supplyAsync(() -> serviceB.processRequest(request));

        CompletableFuture<Void> timeoutFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(100);
                if (!result.isDone()) {
                    result.completeExceptionally(new RuntimeException("Не успеваю обработать, попробуйте еще раз"));
                }
            } catch (InterruptedException e) {
                // Обработка исключения
            }
        });

        serviceBFuture.thenAccept(response -> {
            timeoutFuture.cancel(false);
            result.complete(response);
        });

        return result;
    }
}
