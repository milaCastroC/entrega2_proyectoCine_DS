package Servicios;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import org.json.JSONObject;
import io.github.cdimascio.dotenv.Dotenv;

public class IAService {
	private static final Dotenv dotenv = Dotenv.load();
	private static final String API_KEY = dotenv.get("API_KEY");
	private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent";
	private static final HttpClient httpClient = HttpClient.newHttpClient();
	private static final Gson gson = new Gson();
	
	public static String clasificarComentario(String comentario) {
		String prompt = "Clasifica el siguiente comentario hecho sobre una pelicula como: "
				+ "Positivo, Negativo, Spam.";
		
		prompt += comentario;
		JsonObject requestBody = new JsonObject();
		JsonObject content = new JsonObject();
		JsonObject part = new JsonObject();
		part.addProperty("text", prompt);
		content.add("parts", gson.toJsonTree(new JsonObject[] { part }));
		requestBody.add("contents", gson.toJsonTree(new JsonObject[] { content }));
		try {
			// Hace petición a la API
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL))
					.header("Content-Type", "application/json").header("x-goog-api-key", API_KEY)
					.POST(HttpRequest.BodyPublishers.ofString(requestBody.toString())).build();
			
			// Recibe la respuesta
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			JSONObject jsonObj = new JSONObject(response.body());
			String mensaje = jsonObj.getJSONArray("candidates").getJSONObject(0).getJSONObject("content")
					.getJSONArray("parts").getJSONObject(0).getString("text");
			
			return mensaje;
		} catch (IOException | InterruptedException e) {
			System.out.println("Error al clasificar comentari: " + e.getMessage());
		}
		return null;
	}
}
