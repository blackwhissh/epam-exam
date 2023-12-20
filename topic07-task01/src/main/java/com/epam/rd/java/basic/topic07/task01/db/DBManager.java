package com.epam.rd.java.basic.topic07.task01.db;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.epam.rd.java.basic.topic07.task01.db.entity.*;

import static com.epam.rd.java.basic.topic07.task01.Constants.SETTINGS_FILE;

public class DBManager {
	private static final DBManager instance = new DBManager();
	private static final Properties properties;
	static {
		properties = new Properties();

		try (InputStream inputStream = new FileInputStream(SETTINGS_FILE)){
			properties.load(inputStream);
		} catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

	public static synchronized DBManager getInstance() {
		if(instance == null){
			return new DBManager();
		}
		return instance;
	}

	public List<User> findAllUsers() throws DBException {
		List<User> users = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(properties.getProperty("connection.url"),
				properties.getProperty("connection.user"),
				properties.getProperty("connection.password"));
			 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users")) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()){
				int id = resultSet.getInt("id");
				String login = resultSet.getString("login");
				users.add(new User(id,login));
			}

		} catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
	}

	public boolean insertUser(User user) throws DBException {
		String login =  user.getLogin();
		try (Connection connection = DriverManager.getConnection(properties.getProperty("connection.url"),
				properties.getProperty("connection.user"),
				properties.getProperty("connection.password"));
			 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (login) VALUES (?)")){

			preparedStatement.setString(1, login);

			int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	public User getUser(String login) throws DBException {
		return null;
	}

	public Team getTeam(String name) throws DBException {
		return null;
	}

	public List<Team> findAllTeams() throws DBException {
		List<Team> teams = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(properties.getProperty("connection.url"),
				properties.getProperty("connection.user"),
				properties.getProperty("connection.password"));
			 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM teams")) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()){
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				teams.add(new Team(id,name));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return teams;
	}

	public boolean insertTeam(Team team) throws DBException {
		String name =  team.getName();
		try (Connection connection = DriverManager.getConnection(properties.getProperty("connection.url"),
				properties.getProperty("connection.user"),
				properties.getProperty("connection.password"));
			 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO teams (name) VALUES (?)")){

			preparedStatement.setString(1, name);

			int affectedRows = preparedStatement.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
