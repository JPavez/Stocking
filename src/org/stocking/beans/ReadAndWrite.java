package org.stocking.beans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.stocking.db.InsumoDao;

/**
 * Esta clase me permite imprimir en pantalla los mensajes del menu, e ingresar
 * por consola las acciones que el usuario quiera realizar.
 * 
 * @author joincube
 * 
 */
public class ReadAndWrite {

private InsumoDao insumoDao;

	BufferedReader bf;

	/**
	 * este constructor asigna un objeto BufferedReader al atributo bf que me
	 * permite leer de consola.
	 */
	public ReadAndWrite() {
		bf = new BufferedReader(new InputStreamReader(System.in));
		insumoDao = new InsumoDao();
	}

	/**
	 * muestra menu con opciones, agregar insumo, consumir insumo, buscar
	 * insumo.
	 * 
	 * @throws IOException
	 */				
			
	/**
	 * este  metodo me imprime en pantalla el menu con sus opciones.
	 */
	public void menu(){
		System.out.println("Bienvenido. Elija opcion a realizar:");
		System.out.println("1_ AGREGAR INSUMO");
		System.out.println("2_ CONSUMIR INSUMO");
		System.out.println("3_ BUSCAR INSUMO");
		System.out.println("4_ AGREGAR CANTIDAD DE UN INSUMO");
		System.out.println("5_ SALIR");
	}
			
	/**
	 * este metodo lee valores ingresados por consola y con ellos crea un objeto insumo
	 * el cual lo inserta en una base de datos.
	 * @throws IOException
	 * @throws SQLException 
	 */		
	public void agregarInsumo() throws IOException, SQLException{
		String insuNom = null;
		do{
			System.out.println("ingrese el nombre del insumo:");
			insuNom = bf.readLine();
			
			
		}while(insuNom == null || insuNom.trim().equals(""));
		Insumo nuevoNombre = new Insumo();
		nuevoNombre.setNombre(insuNom);
		System.out.println("ingrese cantidad de " + insuNom + " a ingresar:");
		String insuNum = bf.readLine();
		nuevoNombre.setCantidad(Integer.parseInt(insuNum));
		insumoDao.insert(nuevoNombre);
	}	
	/**
	 * no esta funcionando, falta que borre cantidad del insumo ingresado por consola
	 * @throws IOException
	 * @throws SQLException 
	 */
	public void consumirInsumo() throws IOException, SQLException{
		boolean bandera = true;
		do{
			System.out.println("ingrese id del insumo a consumir:");
			String insuId = bf.readLine();
			Long insumoId = null;	
			try {
				insumoId = new Long(insuId);
			} catch (NumberFormatException exception) {
				exception.printStackTrace();
			}
			Insumo nuevoObjeto = new Insumo();
			nuevoObjeto.setId(insumoId);
			System.out.println("cuantos desea consumir");
			String insuNum = bf.readLine();
			Integer insumoNum = null;
			try {
				insumoNum = new Integer(insuNum);
			} catch (NumberFormatException exception) {
				exception.printStackTrace();
			}
			nuevoObjeto.setCantidad(insumoNum);
			List<Insumo> lista = insumoDao.select(nuevoObjeto);
			if (lista.isEmpty()){
				System.out.println("no se encontraron resultados");
				bandera = false;
			} else{
				Insumo dbInsumo = lista.get(0);
				Integer cantidad = dbInsumo.getCantidad();
				if(insumoNum <= cantidad){
					cantidad = cantidad.intValue() - nuevoObjeto.getCantidad().intValue();
					dbInsumo.setCantidad(cantidad);
					insumoDao.update(dbInsumo);
					bandera = false;
				}else{
					System.out.println("es imposible realizar esta operacion, el valor ingresado es superior a la cantidad de insumos.");	
				}	
			}
		}while(bandera);
	}
	/**
	 * recibe por consola nombre e id y busca los id, nombre, cantidad en la base de datos.
	 * @throws IOException
	 * @throws SQLException
	 */
	public void buscarInsumo() throws IOException, SQLException{
		System.out.println("Ingrese Nombre del insumo a buscar (si no lo sabe presione ENTER):");
		String nomInsu = bf.readLine();
		Insumo objInsumo = new Insumo();
		objInsumo.setNombre(nomInsu);
		System.out.println("ingrese ID del insumo a buscar:");
		String idInsumo = bf.readLine();
		Long insuId = null;
		try {
			insuId = new Long(idInsumo);
		} catch (NumberFormatException exception) {
			exception.printStackTrace();
		}
		objInsumo.setId(insuId);
		System.out.println(insumoDao.select(objInsumo));
	}
	
	public void agregarCantidadInsumo() throws IOException, SQLException{
		System.out.println("ingrese ID del que desea agregar cantidad:");
		String insuId = bf.readLine();
		Insumo insumo = new Insumo();
		insumo.setId(Long.parseLong(insuId));
		System.out.println("¿cuantos desea agregar?");
		String insuCant = bf.readLine();
		insumo.setCantidad(Integer.parseInt(insuCant));
		List<Insumo> lista = insumoDao.select(insumo);
		if (lista.isEmpty()){
			System.out.println("no se encontraron resultados");
		} else{
			Insumo dbInsumo = lista.get(0);
			Integer cantidad = dbInsumo.getCantidad();
			cantidad = cantidad.intValue() + insumo.getCantidad().intValue();
			dbInsumo.setCantidad(cantidad);
			insumoDao.update(dbInsumo);
		}		
	}

	/**
	 * lee lo ingresado por usuario.
	 * @return devuelve lo ingresado por pantalla como un objeto String.
	 * @throws IOException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public String leerOpcion() throws IOException, SQLException, ParseException {
		String input = bf.readLine();
		if(input.equals("1")){
			agregarInsumo();
		}else if(input.equals("2")){
			consumirInsumo();
		}else if(input.equals("3")){
			buscarInsumo();
		}else if(input.equals("4")){
			agregarCantidadInsumo();
		}else if(input.equals("5")){
			System.exit(0);
		}
		return input;
	}
}