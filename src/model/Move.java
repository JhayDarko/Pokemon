package model;

public class Move implements Cloneable {

    private final String name;
    private final Type type;
    private final int priority;
    private int pp;
    private final int power;
    private final int accuracy;
    private final String category;
    private final String effect;

    public Move(String name, Type type, int priority, int pp, int power, int accuracy, String secondaryEffect, String category) {
        this.name = name;
        this.type = type;
        this.priority = priority;
        this.pp = pp;
        this.power = power;
        this.accuracy = accuracy;
        this.category = category;
        this.effect = null;
    }

    @Override
    @SuppressWarnings({"CloneDeclaresCloneNotSupported", "CallToPrintStackTrace"})
    public Move clone() {
        try {
            return (Move) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Move(String name, Type type, int priority, int pp, String effect) {
        this.name = name;
        this.type = type;
        this.priority = priority;
        this.pp = pp;
        this.power = 0;
        this.accuracy = 0;
        this.category = null;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }

    public int getPp(int pp) {
        return pp;
    }

    public void reducePp() {
        this.pp--;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public String getCategory() {
        return category;
    }

    public String getEffect() {
        return effect;
    }

    public void showMoveInfo() {
        System.out.println("\n----INFO----");
        System.err.println("\nNombre: " + name);
        System.err.println("Tipo: " + type.getName());
        System.err.println("PP: " + pp);
        System.err.println("Potencia: " + power);
        System.err.println("Precisión: " + accuracy);
        System.err.println("Categoría: " + category);
    }
}
