package org.stocking.beans;

public class Insumo {

	private Long id;
	private String nombre;
	private Integer cantidad;
	
		/**
		 * me permite recibir un objeto Long y guardarlo en el atributo id.
		 * @param asd es un objeto Long que asigno al atributo id.
		 */
		public void setId(Long asd){
			this.id = asd;
		}
		/**
		 * me permite recibir un objeto String y guardarlo en el atributo nombre. 
		 * @param b es un objeto String que asigno al atributo nombre.
		 */
		public void setNombre(String b){
			this.nombre = b;
		}
		/**
		 * me permite recibir un objeto Integer y guardarlo en el atributo cantidad.
		 * @param cant es un objeto Integer que asigno al atributo cantidad.
		 */
		public void setCantidad(Integer cant){
			this.cantidad = cant;
		}
		/**
		 * me permite dar acceso del valor del atributo id.
		 * @return devuelve el valor del atributo id.
		 */
		public Long getId(){
			return id;
		}
		/**
		 * da acceso al valor del atributo nombre.
		 * @return devuelve el valor del atributo nombre.
		 */
		public String getNombre(){
			return nombre;
		}
		/**
		 * da acceso al valor del atributo cantidad.
		 * @return devuelve el valor del atributo cantidad.
		 */
		public Integer getCantidad(){
			return cantidad;
		}
		/**
		 * mecanismo de sobreescribir.
		 */
		@Override
		public String toString() {
			return "Insumo [id=" + id + ", nombre=" + nombre + ", cantidad="
					+ cantidad + "]";
		}
}
