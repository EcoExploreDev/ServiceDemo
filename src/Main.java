import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {

        ServiceB serviceB = new ServiceB();
        ServiceA serviceA = new ServiceA(serviceB);

        CompletableFuture<String> result = serviceA.processRequest("Запрос");

        result.whenComplete((response, error) -> {
            if (error != null) {
                System.out.println("Ошибка: " + error.getMessage());
            } else {
                System.out.println("Результат: " + response);
            }
        });
    }

}



