package controllers;

import java.awt.event.*;
import javax.swing.*;

import Structures.NodoDBL;
import models.*;
import views.MainFrame;




public class EmpleadosController implements ActionListener,KeyListener {
	
	private EmpleadosModel model;
	private MainFrame frame;
	
	public EmpleadosController() {
		model = new EmpleadosModel();
		frame = new MainFrame();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		char typedChar = e.getKeyChar();

		if(typedChar == KeyEvent.VK_ENTER)
			frame.clickOnEnter();
			
		if(frame.fieldIsFull(e)) {
			frame.sendAlert();
			e.consume();

		}
		
		if(frame.typedInNumericField(e)) {
			if(!Character.isDigit(typedChar))   // && code != 8 && code != KeyEvent.VK_DELETE) {
				e.consume();
			return;
		}
		
		if(!Character.isLetter(typedChar) && typedChar != ' ')    // && code != 8  && code != KeyEvent.VK_DELETE) {
			e.consume();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton)
			actionButton(e);
		if(e.getSource() instanceof JRadioButton)
			actionRadioButton(e);
		if(e.getSource() instanceof JTextField)
			actionTextField(e);	
		
	}
	
	//Métodos auxiliares	
	private void actionButton(ActionEvent e) {
		if(frame.registrarIsOnClick(e)) {
			if(frame.formIsNotComplete()) 
				return;
			
			model.insert(frame.guardarEmpleado());
			return;
		}
		
		if(frame.consultaIsOnClick(e)) {
			frame.showDialog();
			return;
		}
		
		if(frame.salirIsOnClick(e)) {
			frame.close();
		}
	}
	
	private void actionRadioButton(ActionEvent e) {
		int criterioAnterior = Empleado.criterioOrdenamiento;
		if(frame.nombreIsSelected(e))
			Empleado.criterioOrdenamiento = 1;
		if(frame.edadIsSelected(e))
			Empleado.criterioOrdenamiento = 2;
		if(frame.estaturaIsSelected(e))
			Empleado.criterioOrdenamiento = 3;
		if(frame.allIsSelected(e))
			Empleado.criterioOrdenamiento = 4;
		
		if(criterioAnterior != Empleado.criterioOrdenamiento && model.length() > 1)
			model.ordenarListaPorCriterio();
	}
	
	private void actionTextField(ActionEvent e) {
		frame.ActionTextField(e);
	}
	
	//Métodos auxiliares
	public int length() {
		return model.length();
	}
	
	public NodoDBL<Empleado> getFrente(){
		return model.getFrente();
	}
}
