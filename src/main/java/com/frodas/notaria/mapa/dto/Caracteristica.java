package com.frodas.notaria.mapa.dto;

public class Caracteristica {

	private String type;
	private Propiedades properties;
	private Geometria geometry;

	public Caracteristica() {
		super();
	}

	public Caracteristica(String type, Propiedades properties, Geometria geometry) {
		super();
		this.type = type;
		this.properties = properties;
		this.geometry = geometry;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Propiedades getProperties() {
		return properties;
	}

	public void setProperties(Propiedades properties) {
		this.properties = properties;
	}

	public Geometria getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometria geometry) {
		this.geometry = geometry;
	}

}
