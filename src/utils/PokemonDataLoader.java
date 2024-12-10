package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

public class PokemonDataLoader {

    private static final String POKEAPI_URL = "https://pokeapi.co/api/v2/pokemon/";
    private static final String SPRITE_FOLDER = "sprites/pokemon/";
    private static final String INPUT_JSON_FILE = "data/base.json";
    private static final String OUTPUT_JSON_FILE = "data/pokemon.json";

    public static void main(String[] args) {
        try {

            final Gson gson = new GsonBuilder().setPrettyPrinting().create();

            JsonArray inputPokemon = readInputJson(INPUT_JSON_FILE);

            JsonObject pokemonData = new JsonObject();

            createSpriteFolder();

            for (int i = 0; i < inputPokemon.size(); i++) {
                String pokemonName = inputPokemon.get(i).getAsString();
                JsonObject pokemonDetails = fetchPokemonDetails(pokemonName);
                if (pokemonDetails != null) {
                    pokemonData.add(pokemonName, pokemonDetails);
                    System.out.println("Pokémon procesado: " + pokemonName);
                }
            }

            saveToJsonFile(pokemonData, gson, OUTPUT_JSON_FILE);
            System.out.println("Datos de Pokémon guardados en " + OUTPUT_JSON_FILE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JsonArray readInputJson(String fileName) throws IOException {
        try (FileReader reader = new FileReader(fileName)) {
            JsonObject json = new Gson().fromJson(reader, JsonObject.class);
            return json.getAsJsonArray("pokemon");
        }
    }

    private static JsonObject fetchPokemonDetails(String pokemonName) throws IOException, InterruptedException {
        String urlString = POKEAPI_URL + pokemonName;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            System.out.println("Error al obtener detalles para el Pokémon: " + pokemonName);
            return null;
        }

        JsonObject pokemonDetails = new Gson().fromJson(response.body(), JsonObject.class);

        JsonObject detailedPokemon = new JsonObject();
        detailedPokemon.addProperty("id", pokemonDetails.get("id").getAsInt());

        String pokemonNameCapitalized = capitalizeFirstLetter(pokemonDetails.get("name").getAsString());
        detailedPokemon.addProperty("name", pokemonNameCapitalized);

        JsonArray types = pokemonDetails.getAsJsonArray("types");
        JsonArray typeNames = new JsonArray();
        for (int i = 0; i < types.size(); i++) {
            JsonObject typeObject = types.get(i).getAsJsonObject();
            typeNames.add(typeObject.getAsJsonObject("type").get("name").getAsString());
        }
        detailedPokemon.add("types", typeNames);

        JsonArray stats = pokemonDetails.getAsJsonArray("stats");
        JsonObject baseStats = new JsonObject();
        for (int i = 0; i < stats.size(); i++) {
            JsonObject statObject = stats.get(i).getAsJsonObject();
            baseStats.addProperty(statObject.getAsJsonObject("stat").get("name").getAsString(),
                    statObject.get("base_stat").getAsInt());
        }
        detailedPokemon.add("base_stats", baseStats);

        JsonArray abilities = pokemonDetails.getAsJsonArray("abilities");
        JsonArray abilityNames = new JsonArray();
        for (int i = 0; i < abilities.size(); i++) {
            JsonObject abilityObject = abilities.get(i).getAsJsonObject();
            abilityNames.add(abilityObject.getAsJsonObject("ability").get("name").getAsString());
        }
        detailedPokemon.add("abilities", abilityNames);

        JsonArray moves = pokemonDetails.getAsJsonArray("moves");
        JsonArray attackMoves = new JsonArray();
        int moveCount = 0;
        for (int i = 0; i < moves.size() && moveCount < 4; i++) {
            JsonObject moveObject = moves.get(i).getAsJsonObject();
            String moveUrl = moveObject.getAsJsonObject("move").get("url").getAsString();
            if (isAttackMove(moveUrl)) {
                attackMoves.add(moveObject.getAsJsonObject("move").get("name").getAsString());
                moveCount++;
            }
        }
        detailedPokemon.add("moves", attackMoves);

        String spriteUrl = pokemonDetails.getAsJsonObject("sprites").get("front_default").getAsString();
        if (spriteUrl != null) {
            String spritePath = downloadSprite(pokemonName, spriteUrl);
            detailedPokemon.addProperty("sprite", spritePath);
        }

        return detailedPokemon;
    }

    private static boolean isAttackMove(String moveUrl) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(moveUrl))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject moveDetails = new Gson().fromJson(response.body(), JsonObject.class);
        String damageClass = moveDetails.getAsJsonObject("damage_class").get("name").getAsString();
        return "physical".equals(damageClass) || "special".equals(damageClass);
    }

    private static String downloadSprite(String pokemonName, String spriteUrl) {
        try {
            Path spritePath = Path.of(SPRITE_FOLDER + pokemonName + ".png");
            return spritePath.toString();
        } catch (Exception e) {
            System.err.println("Error al descargar el sprite para " + pokemonName + ": " + e.getMessage());
            return null;
        }
    }

    private static void createSpriteFolder() {
        File spriteFolder = new File(SPRITE_FOLDER);
        if (!spriteFolder.exists()) {
            spriteFolder.mkdirs();
        }
    }

    private static String capitalizeFirstLetter(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    private static void saveToJsonFile(JsonObject data, Gson gson, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo JSON: " + e.getMessage());
        }
    }
}
