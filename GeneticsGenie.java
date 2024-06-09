import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GeneticsGenie
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Biology Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton bmiButton = new JButton("BMI Calculator");
        JButton monoButton = new JButton("Monohybrid Cross");
        JButton dihyButton = new JButton("Dihybrid Cross");
        JButton heightButton = new JButton("Child Height Predictor (60% Accuracy)");

        bmiButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        monoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        dihyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        heightButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(bmiButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(monoButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(dihyButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(heightButton);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setVisible(true);

        bmiButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {bmiCalculator();}});

        monoButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {monohybridCross();}});

        dihyButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {dihybridCross();}});

        heightButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {childHeightPredictor();}
        });
    }

    public static void bmiCalculator()
    {
        JFrame frame = new JFrame("BMI Calculator");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel weightLabel = new JLabel(" Enter your Weight (LBs): ");
        JTextField weightField = new JTextField();
        JLabel heightLabel = new JLabel(" Enter your Height (Inches): ");
        JTextField heightField = new JTextField();

        JButton calculateButton = new JButton("Calculate BMI");
        JLabel resultLabel = new JLabel("BMI: ");

        panel.add(weightLabel);
        panel.add(weightField);
        panel.add(heightLabel);
        panel.add(heightField);
        panel.add(calculateButton);
        panel.add(resultLabel);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setVisible(true);

        calculateButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                double weight = Double.parseDouble(weightField.getText());
                double height = Double.parseDouble(heightField.getText());
                double bmi = (weight * 703 / Math.pow(height, 2));

                String bmiCategory;
                if (bmi < 18.5)
                {
                    bmiCategory = "Underweight";
                }
                else if (bmi < 24.9)
                {
                    bmiCategory = "Normal Weight";
                }
                else if (bmi < 29.9)
                {
                    bmiCategory = "Overweight";
                }
                else
                {
                    bmiCategory = "Obese";
                }
                resultLabel.setText(String.format("BMI: %.2f (%s)", bmi, bmiCategory));
            }
        });
    }

    public static void monohybridCross()
    {
        JFrame frame = new JFrame("Monohybrid Cross");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));

        JLabel parent1Label = new JLabel(" Enter 1st Parent Genotype: ");
        JTextField parent1Field = new JTextField(5);
        JLabel parent2Label = new JLabel(" Enter 2nd Parent Genotype: ");
        JTextField parent2Field = new JTextField(5);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setPreferredSize(new Dimension(100, 25));
        JLabel resultLabel = new JLabel("Result:");

        panel.add(parent1Label);
        panel.add(parent1Field);
        panel.add(parent2Label);
        panel.add(parent2Field);
        panel.add(calculateButton);
        panel.add(resultLabel);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setVisible(true);

        calculateButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String one = parent1Field.getText();
                String two = parent2Field.getText();

                String[][] punnettSquare = new String[2][2];
                punnettSquare[0][0] = "" + one.charAt(0) + two.charAt(0);
                punnettSquare[0][1] = "" + one.charAt(0) + two.charAt(1);
                punnettSquare[1][0] = "" + one.charAt(1) + two.charAt(0);
                punnettSquare[1][1] = "" + one.charAt(1) + two.charAt(1);

                BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = image.createGraphics();
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(2));

                // Draw the grid
                g2d.drawLine(100, 100, 300, 100);
                g2d.drawLine(100, 200, 300, 200);
                g2d.drawLine(100, 300, 300, 300);
                g2d.drawLine(100, 100, 100, 300);
                g2d.drawLine(200, 100, 200, 300);
                g2d.drawLine(300, 100, 300, 300);

                g2d.drawString(punnettSquare[0][0], 140, 180);
                g2d.drawString(punnettSquare[0][1], 240, 180);
                g2d.drawString(punnettSquare[1][0], 140, 280);
                g2d.drawString(punnettSquare[1][1], 240, 280);

                g2d.dispose();

                JLabel imageLabel = new JLabel(new ImageIcon(image));
                JOptionPane.showMessageDialog(null, imageLabel, "Punnett Square", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    public static void dihybridCross()
    {
        JFrame frame = new JFrame("Dihybrid Cross");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));

        JLabel parent1Label = new JLabel(" Enter 1st Parent Genotype: ");
        JTextField parent1Field = new JTextField(5);
        JLabel parent2Label = new JLabel(" Enter 2nd Parent Genotype: ");
        JTextField parent2Field = new JTextField(5);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setPreferredSize(new Dimension(100, 25));
        JLabel resultLabel = new JLabel("Result:");

        panel.add(parent1Label);
        panel.add(parent1Field);
        panel.add(parent2Label);
        panel.add(parent2Field);
        panel.add(calculateButton);
        panel.add(resultLabel);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setVisible(true);

        calculateButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String parent1 = parent1Field.getText();
                String parent2 = parent2Field.getText();

                String[] parent1Gametes = generateGametes(parent1);
                String[] parent2Gametes = generateGametes(parent2);
                String[][] punnettSquare2 = new String[4][4];

                for (int i = 0; i < 4; i++)
                {
                    for (int j = 0; j < 4; j++)
                    {
                        punnettSquare2[i][j] = parent1Gametes[i] + parent2Gametes[j];
                    }
                }

                BufferedImage image = new BufferedImage(550, 550, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = image.createGraphics();
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(2));

                // Draw the grid
                for (int i = 0; i <= 4; i++)
                {
                    g2d.drawLine(50, 50 + i * 100, 450, 50 + i * 100);
                    g2d.drawLine(50 + i * 100, 50, 50 + i * 100, 450);
                }

                for (int i = 0; i < 4; i++)
                {
                    for (int j = 0; j < 4; j++)
                    {
                        g2d.drawString(punnettSquare2[i][j], 75 + j * 100, 75 + i * 100);
                    }
                }

                g2d.dispose();

                JLabel imageLabel = new JLabel(new ImageIcon(image));
                JOptionPane.showMessageDialog(null, imageLabel, "Punnett Square", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    public static void childHeightPredictor()
    {
        JFrame frame = new JFrame("Child Height Predictor");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 250);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel fatherHeightLabel = new JLabel(" Enter your Father's Height (Inches): ");
        JTextField fatherHeightField = new JTextField();
        JLabel motherHeightLabel = new JLabel(" Enter your Mother's Height (Inches): ");
        JTextField motherHeightField = new JTextField();

        JLabel genderLabel = new JLabel(" Pick your Gender (#): 1.) Male 2.) Female");
        JTextField genderField = new JTextField();

        JButton calculateButton = new JButton("Calculate");
        JLabel resultLabel = new JLabel("Predicted Height: ");

        panel.add(fatherHeightLabel);
        panel.add(fatherHeightField);
        panel.add(motherHeightLabel);
        panel.add(motherHeightField);
        panel.add(genderLabel);
        panel.add(genderField);
        panel.add(calculateButton);
        panel.add(resultLabel);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setVisible(true);

        calculateButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int fHeight = Integer.parseInt(fatherHeightField.getText());
                int mHeight = Integer.parseInt(motherHeightField.getText());
                int gender = Integer.parseInt(genderField.getText());

                double childHeight = (fHeight + mHeight) / 2.0;
                if (gender == 1)
                {
                    childHeight += 2.5;
                }
                else if (gender == 2)
                {
                    childHeight -= 2.5;
                }

                resultLabel.setText("Predicted Height: " + (int)(childHeight / 12) + "'" + Math.round(childHeight % 12) + "\"");
            }
        });
    }

    public static String[] generateGametes(String parent)
    {
        String[] gametes = new String[4];
        gametes[0] = "" + parent.charAt(0) + parent.charAt(2);
        gametes[1] = "" + parent.charAt(0) + parent.charAt(3);
        gametes[2] = "" + parent.charAt(1) + parent.charAt(2);
        gametes[3] = "" + parent.charAt(1) + parent.charAt(3);
        return gametes;
    }
}



