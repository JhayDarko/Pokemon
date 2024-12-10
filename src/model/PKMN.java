package model;

import java.util.Random;

public class PKMN implements Cloneable {

    static Random random = new Random();
    private final String name;
    private final Type type1;
    private final Type type2;
    private final int level;
    private final Move[] moves = new Move[4];
    private int HP;
    private int ivHP;
    private int evHP;
    private int attack;
    private int ivAttack;
    private int evAttack;
    private int defense;
    private int ivDefense;
    private int evDefense;
    private int specialAttack;
    private int ivSpecialAttack;
    private int evSpecialAttack;
    private int specialDefense;
    private int ivSpecialDefense;
    private int evSpecialDefense;
    private int speed;
    private int ivSpeed;
    private int evSpeed;
    private double precision;
    private double evasion;
    private int precisionLevel;
    private int evasionLevel;
    private int currentHP;
    private String status;

    public PKMN(String name, Type type1, Type type2, int HP, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.level = 50;
        this.ivHP = setIVs();
        this.evHP = generateEVs()[0];
        this.HP = calculateHP(HP, ivHP, evHP, level);
        this.ivAttack = setIVs();
        this.evAttack = generateEVs()[1];
        this.attack = calculateStats(attack, ivAttack, evAttack, level);
        this.ivDefense = setIVs();
        this.evDefense = generateEVs()[2];
        this.defense = calculateStats(defense, ivDefense, evDefense, level);
        this.ivSpecialAttack = setIVs();
        this.evSpecialAttack = generateEVs()[3];
        this.specialAttack = calculateStats(specialAttack, ivSpecialAttack, evSpecialAttack, level);
        this.ivSpecialDefense = setIVs();
        this.evSpecialDefense = generateEVs()[4];
        this.specialDefense = calculateStats(specialDefense, ivSpecialDefense, evSpecialDefense, level);
        this.ivSpeed = setIVs();
        this.evSpeed = generateEVs()[5];
        this.speed = calculateStats(speed, ivSpeed, evSpeed, level);
        this.currentHP = this.HP;
        this.precision = 1.0;
        this.precisionLevel = 0;
        this.evasion = 1.0;
        this.evasionLevel = 0;
        this.status = "Normal";
    }

    public void assignMove(Move move, int index) {
        if (index >= 0 && index < moves.length) {
            moves[index] = move;
        }
    }

    @Override
    @SuppressWarnings({"CallToPrintStackTrace", "CloneDeclaresCloneNotSupported"})
    public PKMN clone() {
        try {
            PKMN clonedPokemon = (PKMN) super.clone();

            clonedPokemon.ivHP = setIVs();
            clonedPokemon.evHP = PKMN.generateEVs()[0];
            clonedPokemon.HP = calculateHP(this.HP, clonedPokemon.ivHP, clonedPokemon.evHP, this.level);
    
            clonedPokemon.ivAttack = setIVs();
            clonedPokemon.evAttack = PKMN.generateEVs()[1];
            clonedPokemon.attack = calculateStats(this.attack, clonedPokemon.ivAttack, clonedPokemon.evAttack, this.level);
    
            clonedPokemon.ivDefense = setIVs();
            clonedPokemon.evDefense = PKMN.generateEVs()[2];
            clonedPokemon.defense = calculateStats(this.defense, clonedPokemon.ivDefense, clonedPokemon.evDefense, this.level);
    
            clonedPokemon.ivSpecialAttack = setIVs();
            clonedPokemon.evSpecialAttack = PKMN.generateEVs()[3];
            clonedPokemon.specialAttack = calculateStats(this.specialAttack, clonedPokemon.ivSpecialAttack, clonedPokemon.evSpecialAttack, this.level);
    
            clonedPokemon.ivSpecialDefense = setIVs();
            clonedPokemon.evSpecialDefense = PKMN.generateEVs()[4];
            clonedPokemon.specialDefense = calculateStats(this.specialDefense, clonedPokemon.ivSpecialDefense, clonedPokemon.evSpecialDefense, this.level);
    
            clonedPokemon.ivSpeed = setIVs();
            clonedPokemon.evSpeed = PKMN.generateEVs()[5];
            clonedPokemon.speed = calculateStats(this.speed, clonedPokemon.ivSpeed, clonedPokemon.evSpeed, this.level);

            clonedPokemon.currentHP = clonedPokemon.HP;
            clonedPokemon.status = this.status;
    
            for (int i = 0; i < this.moves.length; i++) {
                if (this.moves[i] != null) {
                    clonedPokemon.moves[i] = this.moves[i].clone();
                }
            }
            return clonedPokemon;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }    

    public void showInfo() {
        System.out.println("\n----INFO----");
        System.out.println("\nNombre: " + name);
        System.out.println("Tipo 1: " + type1.getName());
        if (type2 != null) {
            System.out.println("Tipo 2: " + type2.getName());
        }
        System.out.println("\nPS: " + HP);
        System.out.println("Ataque: " + attack);
        System.out.println("Defensa: " + defense);
        System.out.println("Ataque Sp.: " + specialAttack);
        System.out.println("Defensa Sp.: " + specialDefense);
        System.out.println("Velocidad: " + speed);
        showMoves();
    }

    public void showMoves() {
        System.out.println("\nMovimientos:");
        int index = 1;
        for (Move move : moves) {
            if (move != null) {
                System.out.println(index + ". " + move.getName());
                index++;
            }
        }
    }

    public void showCurrentHP() {
        System.out.printf("HP: %s/%s%n", currentHP, HP);
    }

    public static int setIVs() {
        return random.nextInt(32);
    }

    public static int[] generateEVs() {
        int maxStatEVs = 252;
        int maxTotalEVs = 510;
        int[] EVs = new int[6];
        int sumEVs = 0;
        for (int i = 0; i < 6; i++) {
            int max = Math.min(maxStatEVs, maxTotalEVs - sumEVs);
            EVs[i] = random.nextInt(max + 1);
            sumEVs += EVs[i];
        }
        int[] resultado = new int[6];
        boolean[] usados = new boolean[6];
        for (int EV : EVs) {
            int index;
            do {
                index = random.nextInt(6);
            } while (usados[index]);
            resultado[index] = EV;
            usados[index] = true;
        }
        return resultado;
    }

    public static int calculateHP(int HP, int iv, int ev, int level) {
        return (int) ((((2 * HP + iv + (ev / 4)) * level) / 100) + level + 10);
    }

    public static int calculateStats(int stat, int iv, int ev, int level) {
        return (int) ((((2 * stat + iv + (ev / 4)) * level) / 100) + 5);
    }

    public void takeDamage(int damage) {
        currentHP -= damage;
        if (currentHP < 0) {
            currentHP = 0;
        }
    }

    public static boolean isFainted(PKMN pokemon) {
        return pokemon.getCurrentHP() <= 0;
    }

    public void heal(int amount) {
        currentHP += amount;
        if (currentHP > HP) {
            currentHP = HP;
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public Type getType1() {
        return type1;
    }

    public Type getType2() {
        return type2;
    }

    public int getlevel() {
        return level;
    }

    public int getHP() {
        return HP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public double getPrecision() {
        return precision;
    }

    public double getEvasion() {
        return evasion;
    }
    
    public int getPrecisionLevel() {
        return precisionLevel;
    }

    public int getEvasionLevel() {
        return evasionLevel;
    }
    
    public void setPrecisionLevel(int level) {
        this.precisionLevel = Math.max(-6, Math.min(level, 6));
        this.precision = calculateModifier(this.precisionLevel);
    }
    
    public void setEvasionLevel(int level) {
        this.evasionLevel = Math.max(-6, Math.min(level, 6));
        this.evasion = calculateModifier(this.evasionLevel);
    }

    private double calculateModifier(int level) {
        if (level > 0) {
            return 1 + (level * 0.33);
        } else if (level < 0) {
            return 1 / (1 - (level * 0.25));
        }
        return 1.0;
    }

    public void modifyPrecision(int level) {
        setPrecisionLevel(precisionLevel + level);
    }
    
    public void modifyEvasion(int level) {
        setEvasionLevel(evasionLevel + level);
    } 

    public Move[] getMoves() {
        return moves;
    }
}
