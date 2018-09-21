package org.corgiking.serialize;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class User implements Serializable{
	
	private static final long serialVersionUID = 4432839107540417535L;
	
	private int id;
	private String name;
	private Map<String, String> map;
	private List<Integer> list;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	public List<Integer> getList() {
		return list;
	}
	public void setList(List<Integer> list) {
		this.list = list;
	}
	
	
}
