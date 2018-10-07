package avaj.aircraft;

import avaj.weather.WeatherTower;

public interface Flyable {
    public Coordinates getCoordinates();
    public void updateConditions();
    public void registerTower(WeatherTower WeatherTower);
}
