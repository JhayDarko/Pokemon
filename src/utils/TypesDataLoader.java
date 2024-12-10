package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TypesDataLoader {

    private static final String INPUT_JSON_FILE = "data/base.json";
    private static final String OUTPUT_JSON_FILE = "data/types.json";
    private static final String SPRITES_FOLDER = "sprites/types";
    private static final String TYPE_API_BASE_URL = "https://pokeapi.co/api/v2/type/";
    private static final String TYPE_SPRITES_BASE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-ix/scarlet-violet/";

    public static void main(String[] args) {
        try {

            final HttpClient client = HttpClient.newHttpClient();
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();

            System.out.println("Leyendo tipos desde " + INPUT_JSON_FILE + "...");
            JsonArray types = readTypesFromJsonFile(gson, INPUT_JSON_FILE);

            System.out.println("Obteniendo datos de tipos...");
            JsonObject typesData = fetchTypesData(client, types);

            saveToJsonFile(typesData, gson, OUTPUT_JSON_FILE);
            System.out.println("Datos de tipos guardados en " + OUTPUT_JSON_FILE);

            System.out.println("Descargando iconos de tipos...");
            downloadTypeIcons(client, types);
            System.out.println("Iconos descargados en la carpeta " + SPRITES_FOLDER);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JsonArray readTypesFromJsonFile(Gson gson, String fileName) throws IOException {
        try (var reader = new java.io.FileReader(fileName)) {
            JsonObject data = gson.fromJson(reader, JsonObject.class);
            return data.getAsJsonArray("types");
        }
    }

    private static JsonObject fetchTypesData(HttpClient client, JsonArray types)
            throws IOException, InterruptedException {
        JsonObject typesData = new JsonObject();

        for (int i = 0; i < types.size(); i++) {
            String typeName = types.get(i).getAsString();
            String typeUrl = TYPE_API_BASE_URL + typeName;

            JsonObject typeData = fetchJsonObject(client, typeUrl);

            JsonObject processedTypeData = new JsonObject();
            processedTypeData.addProperty("name", capitalizeFirstLetter(typeData.get("names").getAsJsonArray(), "es"));
            processedTypeData.add("damage_relations",
                    simplifyDamageRelations(typeData.get("damage_relations").getAsJsonObject()));

            String spritePath = SPRITES_FOLDER + "/" + typeName + ".png";
            processedTypeData.addProperty("sprite_path", spritePath);

            typesData.add(typeName, processedTypeData);
        }

        return typesData;
    }

    private static JsonObject simplifyDamageRelations(JsonObject damageRelations) {
        JsonObject simplified = new JsonObject();

        for (String key : damageRelations.keySet()) {
            JsonArray originalArray = damageRelations.getAsJsonArray(key);
            JsonArray simplifiedArray = new JsonArray();

            for (JsonElement element : originalArray) {
                simplifiedArray.add(element.getAsJsonObject().get("name").getAsString());
            }

            simplified.add(key, simplifiedArray);
        }

        return simplified;
    }

    private static void saveToJsonFile(JsonObject data, Gson gson, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo JSON: " + e.getMessage());
        }
    }

    private static void downloadTypeIcons(HttpClient client, JsonArray types) throws IOException, InterruptedException {
        File folder = new File(SPRITES_FOLDER);
        if (!folder.exists()) {
            folder.mkdir();
        }

        for (int i = 0; i < types.size(); i++) {
            String typeName = types.get(i).getAsString();
            String spriteUrl = TYPE_SPRITES_BASE_URL + (i + 1) + ".png";
            String spriteFileName = SPRITES_FOLDER + "/" + typeName + ".png";

            downloadFile(client, spriteUrl, spriteFileName);
            System.out.println("Icono descargado: " + spriteFileName);
        }
    }

    private static void downloadFile(HttpClient client, String url, String outputPath)
            throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<java.nio.file.Path> response = client.send(request,
                HttpResponse.BodyHandlers.ofFile(java.nio.file.Path.of(outputPath)));

        if (response.statusCode() != 200) {
            System.err.println(
                    "Error descargando el archivo desde: " + url + " - Código de estado: " + response.statusCode());
        }
    }

    private static String capitalizeFirstLetter(JsonArray names, String language) {
        for (JsonElement nameElement : names) {
            JsonObject nameObj = nameElement.getAsJsonObject();
            if (nameObj.get("language").getAsJsonObject().get("name").getAsString().equals(language)) {
                String name = nameObj.get("name").getAsString();
                return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
            }
        }
        return null;
    }

    private static JsonObject fetchJsonObject(HttpClient client, String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new Gson().fromJson(response.body(), JsonObject.class);
    }
}
