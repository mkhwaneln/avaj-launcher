package avaj.weather;

import avaj.aircraft.Coordinates;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates) {
        return (WeatherProvider.getProvider().getCurrentWeather());
    }

    void changeWeather() {
        this.conditionsChanged();
    }
}
