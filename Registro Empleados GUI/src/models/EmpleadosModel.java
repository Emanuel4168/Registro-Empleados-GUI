package models;

import Structures.ListaDBL;
import Structures.NodoDBL;

public class EmpleadosModel {
	private static ListaDBL<Empleado> empleados = new ListaDBL<>();

	private void ordenarListaPorCriterio(NodoDBL<Empleado> lower, NodoDBL<Empleado> higher, int lowerIndex, int higherIndex) {
		int i = lowerIndex;
		int j = higherIndex;

		NodoDBL<Empleado> nodoIzq = lower;
		NodoDBL<Empleado> nodoDer = higher;

		int pivot = lowerIndex+(higherIndex-lowerIndex)/2;
		NodoDBL<Empleado> nodoPivote = empleados.getFrente();

		for(int it = 1 ; it < pivot; it++)
			nodoPivote = nodoPivote.getSig();

		do {

			while (nodoIzq.Info.toString().compareToIgnoreCase(nodoPivote.Info.toString()) < 0 && i < higherIndex) {
				nodoIzq = nodoIzq.getSig();
				i++;
			}

			while (nodoDer.Info.toString().compareToIgnoreCase(nodoPivote.Info.toString()) > 0 && j > lowerIndex) {
				nodoDer = nodoDer.getAnt();
				j--;
			}

			if (i <= j) {
				intercambiarNodos(nodoIzq, nodoDer);
				//move index to next position on both sides
				i++;
				nodoIzq = nodoIzq.getSig();
				j--;
				nodoDer = nodoDer.getAnt();
			}
		}while (i <= j);

		if (lowerIndex < j) 
			ordenarListaPorCriterio(lower, nodoDer, lowerIndex, j);
		if (i < higherIndex) 
			ordenarListaPorCriterio(nodoIzq, higher, i, higherIndex);

	}

	private void intercambiarNodos(NodoDBL<Empleado> izq, NodoDBL<Empleado> der) {
		Empleado aux = izq.Info; //new Empleado(izq.Info.nombre, izq.Info.edad, izq.Info.estatura);
		izq.Info = der.Info;//new Empleado(der.Info.nombre, der.Info.edad, der.Info.estatura);;
		der.Info = aux;
	}

	public void insert(Empleado emp) {
		empleados.InsertaOrdenado(emp);
	}
	
	public void ordenarListaPorCriterio() {
		ordenarListaPorCriterio(empleados.getFrente(),empleados.getFin(),1,empleados.Length());
	}
	
	public static int length() {
		return empleados.Length();
	}
	
	public static NodoDBL<Empleado> getFrente(){
		return empleados.getFrente();
	}
}
