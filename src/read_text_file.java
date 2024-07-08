import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import org.math.plot.Plot2DPanel;

public class read_text_file {

    // Function to read data from file
    private static double[][] readDataFromFile(String filePath) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(filePath));

        int size = 0;
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            size++;
        }
        scanner.close();

        scanner = new Scanner(new File(filePath));
        double[][] data = new double[size][2];

        for (int i = 0; i < size; i++) {

            String[] line = scanner.nextLine().split(",");
            data[i][0] = Double.parseDouble(line[0]);
            data[i][1] = Double.parseDouble(line[1]);

        }
        scanner.close();

        return data;
    }

    public static void main(String[] args) {

        // Prompt the user to select a file
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        // System.out.println("Printing the result variable : " + result);

        if (result == 1){
            System.out.println("Please select a dataset in order to see the visualizations..!!");
        }
        else {
            System.out.println("The visualizations of your dataset are shown in windows..!!");
        }

        if (result != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null, "No file selected.");
            return;
        }
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();

        try {
            // Read data from file
            double[][] data = readDataFromFile(filePath);

            System.out.println("here are the fetched dataset values");
            System.out.println("  X   " + "   Y");
            for(int i = 0; i < data.length; i++){
                System.out.print(data[i][0] + ", ");
                System.out.print(data[i][1]);
                System.out.println();
            }

            // Creating a scatter plot
            Plot2DPanel scatterPanel = new Plot2DPanel();
            scatterPanel.addScatterPlot("Scatter Plot", Color.BLUE, data);
            scatterPanel.setAxisLabels("Weight", "Height");


            // Creating a histogram
            double[] histData1 = new double[data.length];
            for (int i = 0; i < data.length; i++) {
                histData1[i] = data[i][1];
            }

            double[] histData2 = new double[data.length];
            for (int i = 0; i < data.length; i++) {
                histData2[i] = data[i][0];
            }

            Plot2DPanel histPanel1 = new Plot2DPanel();
            histPanel1.addHistogramPlot("Histogram for Y axis variables", Color.RED, histData1, 10);
            histPanel1.setAxisLabels("Height", "Frequency");

            Plot2DPanel histPanel2 = new Plot2DPanel();
            histPanel2.addHistogramPlot("Histogram for X axis variables", Color.RED, histData2, 10);
            histPanel2.setAxisLabels("Weight", "Frequency");

            // Create and configure frame for scatter plot
            JFrame scatterFrame = new JFrame("Scatter Plot");
            scatterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            scatterFrame.setSize(500, 500);
            scatterFrame.setLocationRelativeTo(null);

            // Create and configure frame for histogram plot
            JFrame histFrame1 = new JFrame("Histogram");
            histFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            histFrame1.setSize(500, 500);
            histFrame1.setLocationRelativeTo(null);

            // Create and configure frame for histogram plot
            JFrame histFrame2 = new JFrame("Histogram");
            histFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            histFrame2.setSize(500, 500);
            histFrame2.setLocationRelativeTo(null);

            // Adding scatter panel to the containers
            JPanel scatterContainer = new JPanel(new BorderLayout());
            scatterContainer.add(scatterPanel, BorderLayout.CENTER);
            scatterContainer.setBackground(Color.WHITE);
            scatterFrame.setContentPane(scatterContainer);

            // Adding histogram panel to the containers
            JPanel histContainer1 = new JPanel(new BorderLayout());
            histContainer1.add(histPanel1, BorderLayout.CENTER);
            histContainer1.setBackground(Color.WHITE);
            histFrame1.setContentPane(histContainer1);

            // Adding histogram panel to the containers
            JPanel histContainer2 = new JPanel(new BorderLayout());
            histContainer2.add(histPanel2, BorderLayout.CENTER);
            histContainer2.setBackground(Color.WHITE);
            histFrame2.setContentPane(histContainer2);


            // Creating a line plot
            Plot2DPanel linePanel = new Plot2DPanel();
            linePanel.addLinePlot("Line Plot", Color.GREEN, data);
            linePanel.setAxisLabels("Weight", "Height");

            // Create and configure frame
            JFrame lineFrame = new JFrame("Line Plot");
            lineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            lineFrame.setSize(500, 500);
            lineFrame.setLocationRelativeTo(null);

            // Add panel to container
            JPanel lineContainer = new JPanel(new BorderLayout());
            lineContainer.add(linePanel, BorderLayout.CENTER);
            lineContainer.setBackground(Color.WHITE);
            lineFrame.setContentPane(lineContainer);


            // Creating a bar plot
            Plot2DPanel barPanel = new Plot2DPanel();
            barPanel.addBarPlot("Bar Plot", Color.RED, data);
            barPanel.setAxisLabels("Weight", "Height");


            // Create and configure frame
            JFrame barFrame = new JFrame("Bar Plot");
            barFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            barFrame.setSize(500, 500);
            barFrame.setLocationRelativeTo(null);

            // Add panel to container
            JPanel barContainer = new JPanel(new BorderLayout());
            barContainer.add(barPanel, BorderLayout.CENTER);
            barContainer.setBackground(Color.WHITE);
            barFrame.setContentPane(barContainer);


            System.out.println("Choose the visualization method from bellow");
            System.out.println("1. Scatter plot");
            System.out.println("2. Histogram");
            System.out.println("3. Line plot");
            System.out.println("4. Bar plot");
            System.out.println("5. Show all the plots");

            System.out.print("Enter : ");
            int choose = new Scanner(System.in).nextInt();

            if(choose == 1){
                scatterFrame.setVisible(true);
            }
            else if (choose == 2) {
                histFrame1.setVisible(true);
                histFrame2.setVisible(true);
            }
            else if (choose == 3) {
                lineFrame.setVisible(true);
            }
            else if (choose == 4) {
                barFrame.setVisible(true);
            }
            else if (choose == 5) {
                // Show All frames
                lineFrame.setVisible(true);
                barFrame.setVisible(true);
                histFrame1.setVisible(true);
                histFrame2.setVisible(true);
                scatterFrame.setVisible(true);
            }
            else {
                System.out.println("please select a valid option");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
        }

    }
}