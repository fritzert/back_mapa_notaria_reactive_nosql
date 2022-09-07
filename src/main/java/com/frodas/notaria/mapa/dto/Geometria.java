package com.frodas.notaria.mapa.dto;

import java.util.ArrayList;
import java.util.List;

public class Geometria {

	private String type;
	private List<Float> coordinates;

	public Geometria() {
		super();
	}

	public Geometria(String type, List<Float> coordinates) {
		super();
		this.type = type;
		this.coordinates = coordinates;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Float> getCoordinates() {
		if (coordinates == null) {
			coordinates = new ArrayList<>();
		}
		return coordinates;
	}

	public void setCoordinates(List<Float> coordinates) {
		this.coordinates = coordinates;
	}

}
