package com.gestankbraturst.killborder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class TrivialInterfaceAdapter implements JsonSerializer<Object>, JsonDeserializer<Object> {

  private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();
  private static final String CLASS_KEY = "@CLASS";
  private static final String DATA_KEY = "@DATA";

  @Override
  public Object deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
    JsonObject jsonObject = json.getAsJsonObject();
    String className = jsonObject.get(CLASS_KEY).getAsString();
    try {
      Class<?> clazz = Class.forName(className);
      return GSON.fromJson(jsonObject.get(DATA_KEY), clazz);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public JsonElement serialize(Object src, Type type, JsonSerializationContext context) {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty(CLASS_KEY, src.getClass().getName());
    jsonObject.add(DATA_KEY, GSON.toJsonTree(src));
    return jsonObject;
  }

}
