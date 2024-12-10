package model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Dex {

    private final TreeMap<Integer, PKMN> pokedex;

    public Dex() {
        pokedex = new TreeMap<>();
    }

    public void addPKMN(int DexNumber, PKMN pokemon) {
        pokedex.put(DexNumber, pokemon);
    }

    public PKMN getPKMN(int DexNumber) {
        return pokedex.get(DexNumber);
    }

    public PKMN deletePKMN(int DexNumber) {
        return pokedex.remove(DexNumber);
    }

    public Integer[] getEntrys() {
        Set<Integer> keySet = pokedex.keySet();
        Integer[] entrys = keySet.toArray(Integer[]::new);
        return entrys;
    } 

    public void showDex() {
        System.out.println("\n--Pokedex--\n");
        for (Map.Entry<Integer, PKMN> entrada : pokedex.entrySet()) {
            System.out.println("Nº Pokédex: " + entrada.getKey() + " - " + entrada.getValue().getName());
        }
    }

    public int getSize() {
        return pokedex.size();
    }

    public boolean isEmpty() {
        return pokedex.isEmpty();
    }
}
