package avaj.weather;

import avaj.aircraft.*;
import java.util.ArrayList;

public abstract class Tower {
    private ArrayList<Flyable> observers = new ArrayList<Flyable>();
    private ArrayList<Flyable> unregistered = new ArrayList<Flyable>();


    public void register(Flyable flyable) {
        if (observers.contains(flyable))
            return;
        observers.add(flyable);

    }

    public void unregister(Flyable flyable) {
        unregistered.add(flyable);
    }

    protected void conditionsChanged() {
        for (Flyable flyable : observers) {
            flyable.updateConditions();
            if (flyable.getCoordinates().getHeight() <= 0)
                this.unregister(flyable);
        }
        observers.removeAll(unregistered);
    }
}
