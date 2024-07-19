import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("Convert2Lambda")
public class BMICalculatorApp {
    public static void main(String [] args) {
        JFrame frame = new JFrame ("BMI Calculator");
        JPanel panel = new JPanel();
        frame.setSize(600, 200);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel weightLabel = new JLabel("Weight (kg): ");
        JTextField weightText = new JTextField(10);
        JLabel heightLabel = new JLabel("Height (cm): ");
        JTextField heightText = new JTextField(10);
        JLabel resultLabel = new JLabel ("BMI Result");

        JButton calculateButton = new JButton("Calculate");

        calculateButton.addActionListener(new ActionListener() {

                                              @Override
                                              public void actionPerformed(ActionEvent e) {
                                                  // TODO Auto-generated method stub
                                                  try {
                                                      double weight = Double.parseDouble(weightText.getText().trim());
                                                      double height = Double.parseDouble(heightText.getText().trim());

                                                      BMICalculator calculator = new BMICalculator();
                                                      double bmi = calculator.calculate(height, weight);
                                                      String category = calculator.BMItoCategory(bmi);

                                                      resultLabel.setText(String.format("%.1f - %s", bmi, category));
                                                  }
                                                  catch (NumberFormatException e1) {
                                                      resultLabel.setText("Invalid input");
                                                  }
                                              }
                                          }
        );


        JButton resetButton = new JButton("Reset");

        resetButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

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
