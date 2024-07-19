import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class BMIServer {
    public static void main(String[] args) throws IOException {
        @SuppressWarnings("resource")
        ServerSocket serverSocket = new ServerSocket(12345);

        while (true) {
            System.out.println("Waiting for client...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            // handle client request in a separate thread for processing
            new Thread(new Runnable() {
                @Override
                public void run() {
                    handleClient(clientSocket);
                }
            }).start();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                //creating object streams for sending/receiving data to client
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())
        ) {
            // reading user input from client
            double weight = (double) in.readObject();
            double height = (double) in.readObject();

            // calculating BMI
            BMICalculator calculator = new BMICalculator();
            double bmi = calculator.calculate(height, weight);
            String category = calculator.BMItoCategory(bmi);

            // sending the result back to client
            out.writeObject(bmi);
            out.writeObject(category);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}