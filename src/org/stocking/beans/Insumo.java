package org.stocking.beans;

public class Insumo {

	private Long id;
	private String nombre;
	private Integer cantidad;
	
	public void setNombre(String b){
		this.nombre = b;
	}
	public void setCantidad(Integer cant){
		this.cantidad = cant;
	}
	public String getNombre(){
		return nombre;
	}
	public Integer getCantidad(){
		return cantidad;
	}
}
