package avaj.weather;

import avaj.aircraft.*;
import java.io.*;
import java.util.ArrayList;

public class Simulator {
    private static WeatherTower weatherTower;
    private static ArrayList<Flyable> flyables = new ArrayList<Flyable>();
    private static WriteToFile fileWrite = new WriteToFile();

    private static int verifyInteger(String line) {
        int result = 0;

        try {
            result = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.println("Input is not an integer! \n" + e.getMessage());
            System.exit(1);
        }
        return (result);
    }

    private static int readNumberOfSimulations(String line) {
        int numOfSimulations = 0;

        numOfSimulations = verifyInteger(line);

        if (numOfSimulations < 0) {
            System.out.println("Invalid simulations count " + numOfSimulations);
            System.exit(1);
        }
        return numOfSimulations;
    }

    private static void checkInput(String line) {
        String aircraft = line.split(" ")[0];
        int l = 0;
        int lat = 0;
        int h = 0;

        if (!("Helicopter".equalsIgnoreCase(aircraft)) && !("JetPlane".equalsIgnoreCase(aircraft)) && !("Baloon".equalsIgnoreCase(aircraft))) {
            System.out.println("Incorrect input = " + aircraft + "\nPlease specify one of the following aircrafts: Helicopter, JetPlane, Baloon");
            System.exit(1);
        }

        l = verifyInteger(line.split(" ")[2]);
        lat = verifyInteger(line.split(" ")[3]);
        h = verifyInteger(line.split(" ")[4]);
    }

    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                System.out.println("Only one argument is required: [arg].txt");
                System.exit(1);
            }
            String fileName = args[0];
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();

            if (line != null) {
                weatherTower = new WeatherTower();
                int noOfSimulations = readNumberOfSimulations(line);

                while ((line = bufferedReader.readLine()) != null) {
                    checkInput(line);
                    Flyable flyable = AircraftFactory.newAircraft(line.split(" ")[0], line.split(" ")[1],
                            Integer.parseInt(line.split(" ")[2]), Integer.parseInt(line.split(" ")[3]),
                            Integer.parseInt(line.split(" ")[4]));
                    flyables.add(flyable);
                }

                for (Flyable flyable : flyables) {
                    flyable.registerTower(weatherTower);
                }

                for (int i = 1; i <= noOfSimulations; i++) {
                    fileWrite.WriteFile("\n");
                    weatherTower.changeWeather();
                }
            }
            else
                System.out.println("Text file has no input");

            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
