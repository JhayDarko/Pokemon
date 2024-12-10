package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MovesDataLoader {

    private static final String POKEAPI_URL = "https://pokeapi.co/api/v2/move/";
    private static final String INPUT_JSON_FILE = "data/base.json";
    private static final String OUTPUT_JSON_FILE = "data/moves.json";

    public static void main(String[] args) {
        try {

            final Gson gson = new GsonBuilder().setPrettyPrinting().create();

            JsonArray inputMoves = readInputJson(INPUT_JSON_FILE);

            JsonObject movesData = new JsonObject();

            for (int i = 0; i < inputMoves.size(); i++) {
                String moveName = inputMoves.get(i).getAsString();
                JsonObject moveDetails = fetchMoveDetails(moveName);
                if (moveDetails != null) {
                    movesData.add(moveName, moveDetails);
                    System.out.println("Movimiento procesado: " + moveName);
                }
            }

            saveToJsonFile(movesData, gson, OUTPUT_JSON_FILE);
            System.out.println("Datos de movimientos guardados en " + OUTPUT_JSON_FILE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JsonArray readInputJson(String fileName) throws IOException {
        try (FileReader reader = new FileReader(fileName)) {
            JsonObject json = new Gson().fromJson(reader, JsonObject.class);
            return json.getAsJsonArray("moves");
        }
    }

    private static JsonObject fetchMoveDetails(String moveName) throws IOException, InterruptedException {

        String urlString = POKEAPI_URL + moveName;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            System.out.println("Error al obtener detalles para el movimiento: " + moveName);
            return null;
        }

        JsonObject moveDetails = new Gson().fromJson(response.body(), JsonObject.class);

        JsonObject detailedMove = new JsonObject();
        detailedMove.addProperty("accuracy", getIntValue(moveDetails, "accuracy"));
        detailedMove.addProperty("power", getIntValue(moveDetails, "power"));
        detailedMove.addProperty("pp", getIntValue(moveDetails, "pp"));

        JsonObject typeObject = moveDetails.getAsJsonObject("type");
        detailedMove.addProperty("type", typeObject != null ? typeObject.get("name").getAsString() : "unknown");

        JsonArray names = moveDetails.getAsJsonArray("names");
        detailedMove.addProperty("name", getLocalizedName(names, "es"));

        JsonObject damageClassObject = moveDetails.getAsJsonObject("damage_class");
        if (damageClassObject != null) {
            detailedMove.addProperty("damage_class", damageClassObject.get("name").getAsString());
        } else {
            detailedMove.addProperty("damage_class", "unknown");
        }

        int priority = getIntValue(moveDetails, "priority");
        detailedMove.addProperty("priority", priority);

        return detailedMove;
    }

    private static int getIntValue(JsonObject json, String key) {
        return json.has(key) && !json.get(key).isJsonNull() ? json.get(key).getAsInt() : 0;
    }

    private static String getLocalizedName(JsonArray names, String language) {
        for (int i = 0; i < names.size(); i++) {
            JsonObject nameObj = names.get(i).getAsJsonObject();
            if (nameObj.getAsJsonObject("language").get("name").getAsString().equals(language)) {
                return nameObj.get("name").getAsString();
            }
        }
        return "Unknown";
    }

    private static void saveToJsonFile(JsonObject data, Gson gson, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo JSON: " + e.getMessage());
        }
    }
}
