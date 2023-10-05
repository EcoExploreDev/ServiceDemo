public class ServiceB {
    public String processRequest(String request) {
        try {
            // Имитация обработки запроса
            Thread.sleep(50);
        } catch (InterruptedException e) {
            // Обработка исключения
        }

        return "Результат обработки запроса";
    }
}
