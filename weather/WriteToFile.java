package avaj.weather;

import java.io.*;

public class WriteToFile {
    public void WriteFile(String sentence) {
        BufferedWriter bw = null;
        try {
            File simulationFile = new File("simulation.txt");

            if (!simulationFile.exists()) {
                simulationFile.createNewFile();
            }

            if (simulationFile == null) {
                FileWriter fw = new FileWriter(simulationFile);
                bw = new BufferedWriter(fw);
                bw.write(sentence);
            }
            else {
                FileWriter fw = new FileWriter(simulationFile, true);
                bw = new BufferedWriter(fw);
                bw.append(sentence);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (Exception ex) {
                System.out.println("Error in closing the BufferedWriter " + ex);
            }
        }
    }
}