package model;

import java.util.HashMap;

public class Type {

    private final String name;
    private final HashMap<String, Double> effectivenessMap;

    public Type(String name) {
        this.name = name;
        effectivenessMap = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addEffectiveness(String otherType, double effectivenessValue) {
        effectivenessMap.put(otherType, effectivenessValue);
    }

    public double getEffectiveness(Type otherType) {
        if (otherType == null) {
            throw new IllegalArgumentException("El tipo no puede ser nulo");
        }
        return effectivenessMap.getOrDefault(otherType.getName(), 1.0);
    }
}
