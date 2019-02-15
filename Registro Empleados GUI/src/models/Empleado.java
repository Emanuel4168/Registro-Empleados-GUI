package models;

import Structures.Rutinas;

public class Empleado{
	public static int criterioOrdenamiento = 1; 
	
	public String nombre;
	public int edad;
	public int estatura;

	public Empleado(String nombre, int edad, int estatura) {
		this.nombre = nombre;
		this.edad = edad; 
		this.estatura = estatura;
	}

	public String toString() {
		if(criterioOrdenamiento == 1)
			return Rutinas.PonBlancos(nombre, 25);
		if(criterioOrdenamiento == 2)
			return Rutinas.PonCeros(edad,4);
		if(criterioOrdenamiento == 3)
			return Rutinas.PonCeros(estatura,4);

		return Rutinas.PonCeros(edad,4) + Rutinas.PonCeros(estatura, 4)+ Rutinas.PonBlancos(nombre, 25);
	}
	
	public String display() {
		return Rutinas.PonBlancos(nombre, 25)+"\t"+Rutinas.PonCeros(edad,4)+"\t"+Rutinas.PonCeros(estatura, 4);
	}

}