package avaj.aircraft;

import avaj.weather.*;

class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    private WriteToFile fileWrite = new WriteToFile();

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        this.weatherTower = new WeatherTower();
        String selectedWeather = weatherTower.getWeather(coordinates);

        switch (selectedWeather) {
            case "SUN":
                this.coordinates.setLatitude(this.coordinates.getLongitude() + 10);
                this.coordinates.setHeight(this.coordinates.getHeight() + 2);
                if (coordinates.getHeight() > 100)
                    this.coordinates.setHeight(100);
                fileWrite.WriteFile("JetPlane#" + this.name + "(" + this.id + "):" + " Soaring high in the sky!\n");
                break;
            case "RAIN":
                this.coordinates.setLatitude(this.coordinates.getLatitude() + 5);
                fileWrite.WriteFile("JetPlane#" + this.name + "(" + this.id + "):" + " Wipers going out of control because of this rain\n");
                break;
            case "FOG":
                this.coordinates.setLatitude(this.coordinates.getLatitude() + 1);
                fileWrite.WriteFile("JetPlane#" + this.name + "(" + this.id + "):" + " Kind of hard to see with all this fog\n");
                break;
            case "SNOW":
                this.coordinates.setHeight(this.coordinates.getHeight() - 7);
                fileWrite.WriteFile("JetPlane#" + this.name + "(" + this.id + "):" + " I'm not afraid of flying in snow\n");
                break;
        }

        if (this.coordinates.getHeight() <= 0) {
            fileWrite.WriteFile("JetPlane#" + this.name + "(" + this.id + ")" + " has landed\n");
            fileWrite.WriteFile("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.\n");
        }
    }

    public void registerTower(WeatherTower WeatherTower) {
        this.weatherTower = WeatherTower;
        weatherTower.register(this);
        fileWrite.WriteFile("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.\n");
    }
}
