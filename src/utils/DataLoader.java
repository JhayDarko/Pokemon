package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DataLoader {

    private static final String POKE_API_BASE_URL = "https://pokeapi.co/api/v2/";
    private static final String POKEMON_SPECIES_ENDPOINT = POKE_API_BASE_URL + "pokemon-species?limit=10000";
    private static final String MOVES_ENDPOINT = POKE_API_BASE_URL + "move?limit=10000";
    private static final String TYPES_ENDPOINT = POKE_API_BASE_URL + "type";
    private static final String OUTPUT_FILE = "data/base.json";

    public static void main(String[] args) {
        try {

            final HttpClient client = HttpClient.newHttpClient();
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Map<String, Object> result = new HashMap<>();

            System.out.println("Obteniendo Pokémon...");
            List<String> pokemonList = fetchDefaultPokemon(client);
            result.put("pokemon", pokemonList);

            System.out.println("Obteniendo movimientos de ataque...");
            List<String> attackMoves = fetchAttackMoves(client);
            result.put("moves", attackMoves);

            System.out.println("Obteniendo tipos...");
            List<String> types = fetchTypes(client);
            result.put("types", types);

            saveToJsonFile(result, gson, OUTPUT_FILE);
            System.out.println("JSON generado correctamente: " + OUTPUT_FILE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<String> fetchDefaultPokemon(HttpClient client) throws IOException, InterruptedException {
        List<String> pokemonList = new ArrayList<>();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(POKEMON_SPECIES_ENDPOINT)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject jsonResponse = new Gson().fromJson(response.body(), JsonObject.class);
        JsonArray speciesArray = jsonResponse.getAsJsonArray("results");

        for (var species : speciesArray) {
            String speciesUrl = species.getAsJsonObject().get("url").getAsString();
            JsonObject speciesDetails = fetchJsonObject(client, speciesUrl);

            JsonArray varieties = speciesDetails.getAsJsonArray("varieties");
            String basePokemonName = varieties.get(0).getAsJsonObject()
                    .get("pokemon")
                    .getAsJsonObject()
                    .get("name")
                    .getAsString();
            pokemonList.add(basePokemonName);
        }
        return pokemonList;
    }

    private static List<String> fetchAttackMoves(HttpClient client) throws IOException, InterruptedException {
        List<String> attackMoves = new ArrayList<>();
        JsonObject movesData = fetchJsonObject(client, MOVES_ENDPOINT);
        JsonArray movesArray = movesData.getAsJsonArray("results");

        for (var move : movesArray) {
            String moveUrl = move.getAsJsonObject().get("url").getAsString();
            JsonObject moveDetails = fetchJsonObject(client, moveUrl);

            JsonObject damageClass = moveDetails.getAsJsonObject("damage_class");
            if (damageClass != null) {
                String damageClassName = damageClass.get("name").getAsString();
                if ("physical".equals(damageClassName) || "special".equals(damageClassName)) {
                    attackMoves.add(moveDetails.get("name").getAsString());
                }
            }
        }
        return attackMoves;
    }

    private static List<String> fetchTypes(HttpClient client) throws IOException, InterruptedException {
        List<String> types = new ArrayList<>();
        JsonObject typesData = fetchJsonObject(client, TYPES_ENDPOINT);
        JsonArray typesArray = typesData.getAsJsonArray("results");

        for (var type : typesArray) {
            types.add(type.getAsJsonObject().get("name").getAsString());
        }
        return types;
    }

    private static void saveToJsonFile(Map<String, Object> data, Gson gson, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo JSON: " + e.getMessage());
        }
    }

    private static JsonObject fetchJsonObject(HttpClient client, String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new Gson().fromJson(response.body(), JsonObject.class);
    }
}
