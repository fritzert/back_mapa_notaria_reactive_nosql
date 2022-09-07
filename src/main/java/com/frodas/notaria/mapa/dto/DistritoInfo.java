package com.frodas.notaria.mapa.dto;

import com.frodas.notaria.mapa.model.Notaria;

import java.util.ArrayList;
import java.util.List;

//import io.swagger.annotations.ApiModel;

//@ApiModel(description = "Datos del distrito y lista de notarias")
public class DistritoInfo {

	private Propiedades propiedades;
	private List<Notaria> listaNotaria;

	public DistritoInfo() {
		super();
	}

	public DistritoInfo(Propiedades propiedades, List<Notaria> listaNotaria) {
		super();
		this.propiedades = propiedades;
		this.listaNotaria = listaNotaria;
	}

	public Propiedades getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(Propiedades propiedades) {
		this.propiedades = propiedades;
	}

	public List<Notaria> getListaNotaria() {
		if (listaNotaria == null) {
			listaNotaria = new ArrayList<>();
		}
		return listaNotaria;
	}

	public void setListaNotaria(List<Notaria> listaNotaria) {
		this.listaNotaria = listaNotaria;
	}

}
