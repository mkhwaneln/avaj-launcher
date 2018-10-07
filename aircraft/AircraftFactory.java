package avaj.aircraft;

public abstract class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coords = new Coordinates(longitude, latitude, height);

        if ("Helicopter".equalsIgnoreCase(type)) {
            return new Helicopter(name, coords);
        }
        else if ("JetPlane".equalsIgnoreCase(type)) {
            return new JetPlane(name, coords);
        }
        else if ("Baloon".equalsIgnoreCase(type)) {
            return new Baloon(name, coords);
        }
        else {
            return null;
        }
    }
}
