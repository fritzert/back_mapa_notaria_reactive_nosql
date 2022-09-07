package com.frodas.notaria.mapa.dto;

import java.util.ArrayList;
import java.util.List;

//import io.swagger.annotations.ApiModel;

//@ApiModel(description = "Datos de los distritos")
public class DistritoGis {

	private String type;
	private List<Caracteristica> features;

	public DistritoGis() {
		super();
	}

	public DistritoGis(String type, List<Caracteristica> features) {
		super();
		this.type = type;
		this.features = features;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Caracteristica> getFeatures() {
		if (features == null) {
			features = new ArrayList<>();
		}
		return features;
	}

	public void setFeatures(List<Caracteristica> features) {
		this.features = features;
	}

}
