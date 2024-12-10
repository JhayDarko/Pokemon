package model;

public class Team {

    private final PKMN[] team;
    private int count;
    private final int teamSize;

    public Team(int teamSize) {
        this.teamSize = teamSize;
        team = new PKMN[teamSize];
        count = 0;
    }

    public boolean addToTeam(PKMN pokemon) {
        if (count < teamSize) {
            team[count] = pokemon;
            count++;
            return true;
        } else {
            System.out.println("El equipo ya está completo. No puedes agregar más Pokémon.");
            return false;
        }
    }

    public boolean removeFromTeam(int index) {
        if (index >= 0 && index < count) {
            for (int i = index; i < count - 1; i++) {
                team[i] = team[i + 1];
            }
            team[count - 1] = null;
            count--;
            return true;
        } else {
            System.out.println("Índice no válido. No se pudo eliminar el Pokémon.");
            return false;
        }
    }

    public boolean removePokemon(PKMN pokemon) {
        for (int i = 0; i < count; i++) {
            if (team[i].equals(pokemon)) {
                for (int j = i; j < count - 1; j++) {
                    team[j] = team[j + 1];
                }
                team[count - 1] = null;
                count--;
                return true;
            }
        }
        return false;
    }

    public PKMN getPokemon(int index) {
        return team[index];
    }

    public void showTeam() {
        if (count == 0) {
            System.out.println("El equipo está vacío.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + ". " + team[i].getName());
            }
        }
    }

    public int getTeamSize() {
        return teamSize;
    }

    public int getCurrentSize() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == teamSize;
    }
}
