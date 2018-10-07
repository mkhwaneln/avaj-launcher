package avaj.aircraft;

import avaj.weather.*;

class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    private WriteToFile fileWrite = new WriteToFile();

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        this.weatherTower = new WeatherTower();
        String selectedWeather = weatherTower.getWeather(coordinates);

        switch (selectedWeather) {
            case "SUN":
                this.coordinates.setLongitude(this.coordinates.getLongitude() + 10);
                this.coordinates.setHeight(this.coordinates.getHeight() + 2);
                if (coordinates.getHeight() > 100)
                    this.coordinates.setHeight(100);
                fileWrite.WriteFile("Helicopter#" + this.name + "(" + this.id + "):" + " Flying straight to the sun\n");
                break;
            case "RAIN":
                this.coordinates.setLongitude(this.coordinates.getLongitude() + 5);
                fileWrite.WriteFile("Helicopter#" + this.name + "(" + this.id + "):" + " It's a rainy day, must land\n");
                break;
            case "FOG":
                this.coordinates.setLongitude(this.coordinates.getLongitude() + 1);
                fileWrite.WriteFile("Helicopter#" + this.name + "(" + this.id + "):" + " So misty up here, need some sunlight\n");
                break;
            case "SNOW":
                this.coordinates.setHeight(this.coordinates.getHeight() - 12);
                fileWrite.WriteFile("Helicopter#" + this.name + "(" + this.id + "):" + " Oh it's snowing, the turbulence has begun\n");
                break;
        }

        if (this.coordinates.getHeight() <= 0) {
            fileWrite.WriteFile("Helicopter#" + this.name + "(" + this.id + ")" + " has landed\n");
            fileWrite.WriteFile("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.\n");
        }
    }

    public void registerTower(WeatherTower WeatherTower) {
        this.weatherTower = WeatherTower;
        weatherTower.register(this);
        fileWrite.WriteFile("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.\n");
    }
}
