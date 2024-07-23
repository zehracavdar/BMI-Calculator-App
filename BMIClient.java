import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;


class BMIClient {
    public static void main(String[] args) {
        
        //creating gui
        JFrame frame = new JFrame("BMI Calculator");
        JPanel panel = new JPanel();
        frame.setSize(600, 200);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel weightLabel = new JLabel("Weight (kg): ");
        JTextField weightText = new JTextField(10);
        JLabel heightLabel = new JLabel("Height (cm): ");
        JTextField heightText = new JTextField(10);
        JLabel resultLabel = new JLabel("BMI Result: ");

        JButton calculateButton = new JButton("Calculate");

        
        //actionListener for the calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (
                        Socket socket = new Socket("localhost", 12345);
                        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
                ) {
                    //sending user input to server
                    double weight = Double.parseDouble(weightText.getText().trim());
                    double height = Double.parseDouble(heightText.getText().trim());

                    out.writeObject(weight);
                    out.writeObject(height);

                    //receiving result from server
                    double bmi = (double) in.readObject();
                    String category = (String) in.readObject();

                    //displaying results in the gui
                    resultLabel.setText(String.format("%.1f - %s", bmi, category));
                } catch (IOException | ClassNotFoundException e1) {
                    resultLabel.setText("Error communicating with the server");
                    e1.printStackTrace();
                }
            }
        });

        JButton resetButton = new JButton("Reset");

        //actionListener for the reset button
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                weightText.setText("");
                heightText.setText("");
                resultLabel.setText("BMI Result: ");
            }
        });
        
        panel.add(weightLabel);
        panel.add(weightText);
        panel.add(heightLabel);
        panel.add(heightText);
        panel.add(calculateButton);
        panel.add(resetButton);
        panel.add(resultLabel);
        frame.add(panel);
        frame.setVisible(true);
    }
}
