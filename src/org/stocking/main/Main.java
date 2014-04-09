package org.stocking.main;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import org.stocking.beans.ReadAndWrite;


public class Main {
	
	public static void main(String[] args) throws IOException, SQLException, ParseException{
		ReadAndWrite raw = new ReadAndWrite();
		raw.menu();
		String input = raw.leerOpcion();
		while (!input.equals("5")) {
			raw.menu();
		   	raw.leerOpcion();
		}
	}
}

