package avaj.aircraft;

import avaj.weather.*;

class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    private WriteToFile fileWrite = new WriteToFile();

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        this.weatherTower = new WeatherTower();
        String selectedWeather = weatherTower.getWeather(coordinates);
 
        switch (selectedWeather) {
            case "SUN":
                this.coordinates.setLongitude(this.coordinates.getLongitude() + 2);
                this.coordinates.setHeight(this.coordinates.getHeight() + 4);
                if (coordinates.getHeight() > 100)
                    this.coordinates.setHeight(100);
                fileWrite.WriteFile("Baloon#" + this.name + "(" + this.id + "):" + " When the sun is shining, I can do anything.\n");
                break;
            case "RAIN":
                this.coordinates.setHeight(this.coordinates.getHeight() - 5);
                fileWrite.WriteFile("Baloon#" + this.name + "(" + this.id + "):" + " I don't fly in rain.\n");
                break;
            case "FOG":
                this.coordinates.setHeight(this.coordinates.getHeight() - 3);
                fileWrite.WriteFile("Baloon#" + this.name + "(" + this.id + "):" + " The fog is rising.\n");
                break;
            case "SNOW":
                this.coordinates.setHeight(this.coordinates.getHeight() - 15);
                fileWrite.WriteFile("Baloon#" + this.name + "(" + this.id + "):" + " Snow happens\n");
                break;
        }

        if (this.coordinates.getHeight() <= 0) {
            fileWrite.WriteFile("Baloon#" + this.name + "(" + this.id + ")" + " has landed\n");
            fileWrite.WriteFile("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.\n");
        }
    }

    public void registerTower(WeatherTower WeatherTower) {
        this.weatherTower = WeatherTower;
        weatherTower.register(this);
        fileWrite.WriteFile("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.\n");
    }
}
