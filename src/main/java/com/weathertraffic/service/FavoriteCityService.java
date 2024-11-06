package com.weathertraffic.service;

import com.weathertraffic.model.LocationModel;
import com.weathertraffic.util.JsonReadAndWrite;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteCityService {
    private static final String CONFIG_FILE_PATH = "config.json";
    private final JsonReadAndWrite jsonReadAndWrite = new JsonReadAndWrite();
    private static final Logger logger = LoggerFactory.getLogger(FavoriteCityService.class);

    public List<LocationModel> loadFavoriteCities() throws IOException {
        try {
            String jsonContent = jsonReadAndWrite.readFromFile(CONFIG_FILE_PATH);
            return new Gson().fromJson(jsonContent, new TypeToken<List<LocationModel>>() {}.getType());
        } catch (Exception e) {
            logger.error("Failed to load favorite cities: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    public void addFavoriteCity(LocationModel city) throws IOException {
        List<LocationModel> favoriteCities = loadFavoriteCities();
        favoriteCities.add(city);
        saveFavoriteCities(favoriteCities);
    }

    public void updateFavoriteCity(LocationModel city) throws IOException {
        List<LocationModel> favoriteCities = loadFavoriteCities();
        for (int i = 0; i < favoriteCities.size(); i++) {
            if (favoriteCities.get(i).getCityName().equals(city.getCityName())) {
                favoriteCities.set(i, city);
                break;
            }
        }
        saveFavoriteCities(favoriteCities);
    }

    public void deleteFavoriteCity(String cityName) throws IOException {
        List<LocationModel> favoriteCities = loadFavoriteCities();
        favoriteCities.removeIf(city -> city.getCityName().equals(cityName));
        saveFavoriteCities(favoriteCities);
    }

    public void saveFavoriteCities(List<LocationModel> favoriteCities) throws IOException {
        logger.info("Saving favorite cities to {}", CONFIG_FILE_PATH);
        try {
            String jsonContent = new GsonBuilder().setPrettyPrinting().create().toJson(favoriteCities);
            jsonReadAndWrite.writeToFile(CONFIG_FILE_PATH, jsonContent);
        } catch (Exception e) {
            logger.error("Failed to save favorite cities: {}", e.getMessage());
            throw new IOException("Failed to save favorite cities", e);
        }
    }

    public List<LocationModel> getFavoriteCities() throws IOException {
        return loadFavoriteCities();
    }

    public void logFavoriteCities() throws IOException {
        List<LocationModel> favoriteCities = loadFavoriteCities();
        logger.info("Favorite Cities: {}", favoriteCities);
    }
}