package avaj.aircraft;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    Coordinates(int longitude, int latitude, int height) {
        setLongitude(longitude);
        setLatitude(latitude);
        setHeight(height);
    }

    public int getLongitude() {
        return this.longitude;
    }

    public int getLatitude() {
        return this.latitude;
    }

    public int getHeight() {
        return this.height;
    }

    public void setLongitude(int _long) {
        this.longitude = _long;
    }

    public void setLatitude(int _lati) {
        this.latitude = _lati;
    }

    public void setHeight(int _height) {
        this.height = _height;
    }
}
