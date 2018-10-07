package avaj.weather;

import java.util.Random;

public class WeatherProvider {
    private static WeatherProvider weatherProvider;
    private String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {

    }

    public static WeatherProvider getProvider() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider();
        }
        return (weatherProvider);
    }

    public String getCurrentWeather() {
        Random randomGenerator = new Random();

        int randomInt = randomGenerator.nextInt(4);
        String selectedWeather = weather[randomInt];
        return (selectedWeather);
    }
}
