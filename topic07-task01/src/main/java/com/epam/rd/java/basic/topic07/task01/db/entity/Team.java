package com.epam.rd.java.basic.topic07.task01.db.entity;

import java.util.Objects;

public class Team {
	private final int id;
	private final String name;

	public Team(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public String getName(){
		return name;
	}
	@Override
	public String toString() {
		return "Team{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Team team = (Team) o;
		return Objects.equals(name, team.name);
	}
	public static Team createTeam(String name){
		return new Team(0, name);
	}
}