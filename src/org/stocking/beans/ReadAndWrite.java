package org.stocking.beans;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadAndWrite {

	BufferedReader bf;
	
	public ReadAndWrite(){
		bf = new BufferedReader(new InputStreamReader(System.in));
	}
	

	public void menu() throws IOException{
		boolean bandera = true;
		do{
			System.out.println("¿Desea agregar insumo? (Si/No)");
			String sino = bf.readLine();
			if (sino.equals("si")){
				System.out.println("ingrese el nombre del insumo:");
				String insuNom = bf.readLine();
				Insumo nuevoNombre = new Insumo();
				nuevoNombre.setNombre(insuNom);
				System.out.println("ingrese cantidad de "+ insuNom +" a ingresar:");
				String insuNum = bf.readLine();
				Integer nuevoNumero = new Integer(insuNum);
				nuevoNombre.setCantidad(nuevoNumero);
				
				System.out.println("¿desea continuar ingresando/consumiendo productos? (si/no)");
				String continuar = bf.readLine();
				if (continuar.equals("no")){
					bandera = false;
				}
			}else if(sino.equals("no")){
				System.out.println("ingrese nombre del insumo a consumir:");
				String insuNom = bf.readLine();
				Insumo nuevoObjeto = new Insumo();
				nuevoObjeto.setNombre(insuNom);
				System.out.println("cuantos "+ insuNom +" consumir");
				String insuNum = bf.readLine();
				Integer.parseInt(insuNum);
				Integer nuevoNumero = new Integer(insuNum);
				nuevoObjeto.setCantidad(nuevoNumero);
				//
				//
				System.out.println("¿desea continuar ingresando/consumiendo productos? (si/no)");
				String continuar = bf.readLine();
				if (continuar.equals("no")){
					bandera = false;
				}
			}		
		}while(bandera == true);
	}
}