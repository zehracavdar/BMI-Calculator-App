public class BMICalculator {

    public double calculate(double height, double weight){
        double heightM = height / 100;

        return weight / (heightM * heightM);
    }

    public String BMItoCategory(double bmi){
        if(bmi < 18.15){
            return "Underweight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            return "Healthy";
        } else if (bmi > 24.9 && bmi <= 29.9) {
            return "Overweight";
        } else if (bmi > 30) {
            return "Obese";
        }
        return null;
    }
}
