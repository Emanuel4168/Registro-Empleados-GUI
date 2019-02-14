package controllers;

import java.awt.event.*;
import javax.swing.*;

import models.*;
import views.MainFrame;




public class EmpleadosController implements ActionListener,KeyListener {
	
	private EmpleadosModel model;
	private MainFrame frame;
	private static 	int code;
	
	public EmpleadosController() {
		model = new EmpleadosModel();
		frame = new MainFrame();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		code = e.getKeyCode();
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		char typedChar = e.getKeyChar();
		if(frame.typedInNumericField(e)) {
			if(!Character.isDigit(typedChar)  && code != 8 && code != KeyEvent.VK_DELETE) {
				System.out.println("VK_DELETE "  +KeyEvent.VK_DELETE+  "   "+ code);
				frame.sendAlert();
				e.consume();
			}
			return;
		}
		
		if(!Character.isLetter(typedChar) && code != 8  && code != KeyEvent.VK_DELETE) {
			frame.sendAlert();
			e.consume();
		}
		
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
		
		if(criterioAnterior != Empleado.criterioOrdenamiento)
			model.ordenarListaPorCriterio();
	}
	
	private void actionTextField(ActionEvent e) {
		frame.ActionTextField(e);
	}
	
}
