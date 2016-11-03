package com.espen.utils;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import com.espen.ws.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class UserJsonParser {

	private JSONParser parser;
	private JSONObject jsonObject;
	private JSONArray jsonArray;

	public UserJsonParser() {

	}

	public ArrayList<User> deserializeArray(String json) {
		parser = new JSONParser();
		try {
			Object obj = parser.parse(json);
			jsonArray = (JSONArray) obj;

		} catch (ParseException e) {
			e.printStackTrace();
		}
		ArrayList<User> users = new ArrayList<User>();
		for (int i = 0; i < jsonArray.size(); i++) {
			try {
				users.add(deserialize(jsonArray.get(i)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return users;

	}

	public User deserializeUser(String json) throws IOException, JsonProcessingException {
		parser = new JSONParser();
		try {
			Object obj = parser.parse(json);
			User parsedUser = deserialize(obj);
			return parsedUser;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	public User deserialize(Object obj) throws IOException, JsonProcessingException {

		jsonObject = (JSONObject) obj;

		User parsedUser = new User((String) jsonObject.get("username"), (String) jsonObject.get("lastname"),
				(String) jsonObject.get("firstname"), (String) jsonObject.get("password"),
				(String) jsonObject.get("email"), (String) jsonObject.get("phone"));
		parsedUser.setAuthority((String) jsonObject.get("authority"));
		return parsedUser;
	}

	@SuppressWarnings("unchecked")
	public String toJson(User user) {
		JSONObject obj = new JSONObject();
		obj.put("username", user.getUsername());
		obj.put("lastname", user.getLastname());
		obj.put("firstname", user.getFirstname());
		obj.put("email", user.getEmail());
		obj.put("password", user.getPassword());
		obj.put("phone", user.getPhone());

		return obj.toJSONString();
	}
}
