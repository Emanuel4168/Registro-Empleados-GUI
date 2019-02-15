package views;

import javax.swing.*;

import Structures.*;
import controllers.EmpleadosController;
import models.Empleado;
import models.EmpleadosModel;

import java.awt.*;
import java.awt.event.*;

public class MainFrame{
	private static JPanel northPanel,southPanel,centerPanel,westPanel,eastPanel;
	private static JRadioButton rbtNombre,rbtEdad,rbtEstatura,rbtAll;
	private static JButton btnRegistrar,btnConsulta,btnSalir;
	private static JTextField txtNombre, txtEdad,txtEstatura; 
	private static JFrame mainFrame;
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private static EmpleadosController controller;
	private static MainFrame instance;
	
	private static final Color MAIN_COLOR = new Color(240,229,77);
	private static final Color BORDER_COLOR = new Color(249,244,178);
	
	public static void main(String [] a) {	
		start();
	}
	
	private MainFrame() {
		
	}
	
	public static MainFrame getInstance() {
		if(instance == null)
			instance = new MainFrame();
		return instance;
	}
	
	private static  void start(){
		mainFrame = new JFrame("Captura Empleados");
		createNorthPanel();
		createSouthPanel();
		createCenterPanel();	

		mainFrame.setResizable(false);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		controller = new EmpleadosController();
		crateListeners();
		mainFrame.setVisible(true);
	}
	
	private static void createNorthPanel() {
		northPanel = new JPanel(); 
		ButtonGroup group = new ButtonGroup();
		rbtNombre = new JRadioButton("Nombre");
		rbtEdad = new JRadioButton("Edad");
		rbtEstatura = new JRadioButton("Estatura");
		rbtAll = new JRadioButton("Edad-Estatura-Nombre");
		rbtNombre.setSelected(true);
		
		rbtNombre.setBackground(BORDER_COLOR);
		rbtEdad.setBackground(BORDER_COLOR);;
		rbtEstatura.setBackground(BORDER_COLOR);;
		rbtAll.setBackground(BORDER_COLOR);;
		
		group.add(rbtNombre);
		group.add(rbtEdad);
		group.add(rbtEstatura);
		group.add(rbtAll);
		
		northPanel.add(rbtNombre);
		northPanel.add(rbtEdad);
		northPanel.add(rbtEstatura);
		northPanel.add(rbtAll);
		northPanel.setBackground(new Color(249,244,178));
		
		mainFrame.add(northPanel,BorderLayout.NORTH);
	}
	
	private static void createSouthPanel() {
		southPanel = new JPanel();
		
		btnRegistrar = new JButton("Registrar");
		btnConsulta = new JButton("Consultar");
		btnSalir = new JButton("Salir");
		
		btnRegistrar.setBackground(MAIN_COLOR);
		btnConsulta.setBackground(MAIN_COLOR);;
		btnSalir.setBackground(MAIN_COLOR);;
		
		southPanel.add(btnRegistrar);
		southPanel.add(btnConsulta);
		southPanel.add(btnSalir);
		southPanel.setBackground(new Color(249,244,178));
		
		mainFrame.add(southPanel,BorderLayout.SOUTH);
	}
	
	private static void createCenterPanel() {
		centerPanel = new JPanel(new GridLayout(0,2,10,60));
				
		txtNombre = new JTextField();
		txtEdad = new JTextField();
		txtEstatura = new JTextField();
		
		centerPanel.add(new JLabel("Nombre: ", SwingConstants.RIGHT));
		centerPanel.add(txtNombre);
		centerPanel.add(new JLabel("Edad: ", SwingConstants.RIGHT));
		centerPanel.add(txtEdad);
		centerPanel.add(new JLabel("Estatura: ", SwingConstants.RIGHT));
		centerPanel.add(txtEstatura);
		
		centerPanel.setBackground(new Color(240,229,77));
		mainFrame.add(centerPanel);
	}

	/*private void createExtraPanels() {
		eastPanel = new JPanel();
		westPanel = new JPanel();
		
		eastPanel.setBackground(new Color(153,124,250));
		westPanel.setBackground(new Color(153,124,250));
		
		add(eastPanel, BorderLayout.EAST);
		add(westPanel, BorderLayout.WEST);
	}*/
	
	private static void crateListeners() {
		rbtNombre.addActionListener(controller);
		rbtEdad.addActionListener(controller);
		rbtEstatura.addActionListener(controller);
		rbtAll.addActionListener(controller);
		
		btnRegistrar.addActionListener(controller);
		btnConsulta.addActionListener(controller);
		btnSalir.addActionListener(controller);
		
		txtNombre.addActionListener(controller);
		txtNombre.addKeyListener(controller);
		txtEdad.addActionListener(controller);
		txtEdad.addKeyListener(controller);
		txtEstatura.addActionListener(controller);
		txtEstatura.addKeyListener(controller);
	}
	
	
	//Auxiliares
	  public void showDialog() {
			if(controller.length() == 0) {
				JOptionPane.showMessageDialog(null, "Aún no hay empleados");
				return;
			}

			JDialog dialog = new JDialog(mainFrame,true);
			JTextArea textArea = new JTextArea();
			textArea.setBackground(new Color(240,229,77));
			textArea.setEditable(false);
			dialog.setTitle("Consulta");
			dialog.setSize(300,300);
			dialog.setLocationRelativeTo(null);
			dialog.add(textArea);
			//dialog.setLayout(new BoxLayout(dialog,BoxLayout.Y_AXIS));
			dialog.setLayout(new GridLayout(0,1,10,10));
			NodoDBL<Empleado> empleadoActual = controller.getFrente();
			while(empleadoActual != null) {
				textArea.setText(textArea.getText()+"\n"+"Nombre: "+empleadoActual.Info.nombre+"  "+"Edad: "+empleadoActual.Info.edad+"  "+"Estatura: "+empleadoActual.Info.estatura);
				empleadoActual = empleadoActual.getSig();
			}
			dialog.setVisible(true);
		}
	  
	  public Empleado guardarEmpleado() {
			Empleado empleado = new Empleado("",0,0);
			empleado.nombre = txtNombre.getText();
			empleado.edad = Integer.parseInt(txtEdad.getText());
			empleado.estatura = Integer.parseInt(txtEstatura.getText());
			
			txtNombre.setText("");
			txtEdad.setText("");
			txtEstatura.setText("");
			
			txtNombre.requestFocus();
			return empleado;
	  }
	  
	  public boolean registrarIsOnClick(ActionEvent e) {
		  return e.getSource() == btnRegistrar;
	  }
	  
	  public boolean consultaIsOnClick(ActionEvent e) {
		  return e.getSource() == btnConsulta;
	  }
	  
	  public boolean salirIsOnClick(ActionEvent e) {
		  return e.getSource() == btnSalir;
	  }
	  
	  public boolean formIsNotComplete() {
		  if(txtNombre.getText().length() == 0 ||txtEdad.getText().length() == 0 || txtEstatura.getText().length() == 0) {
			  JOptionPane.showMessageDialog(null, "Algunos campos no han sido llenados");
			  return true;
		  }
		  return false;
	  }
	  
	  public boolean nombreIsSelected(ActionEvent e) {
		  return e.getSource() == rbtNombre;
	  }
	  
	  public boolean edadIsSelected(ActionEvent e) {
		  return e.getSource() == rbtEdad;
	  }
	  
	  public boolean estaturaIsSelected(ActionEvent e) {
		  return e.getSource() == rbtEstatura;
	  }
	  
	  public boolean allIsSelected(ActionEvent e) {
		  return e.getSource() == rbtAll;
	  }
	  
	  public void ActionTextField(ActionEvent e) {
			JTextField field = (JTextField) e.getSource();
			
			if(field == txtNombre)
				txtEdad.requestFocusInWindow();
			if(field == txtEdad)
				txtEstatura.requestFocusInWindow();
//			if(field == txtEstatura)
//				btnRegistrar.requestFocusInWindow();
	  }
	  
	  public boolean typedInNumericField(KeyEvent e) {
		  return e.getSource() == txtEdad || e.getSource() == txtEstatura;
	  }

	  public boolean fieldIsFull(KeyEvent e) {
		  if(e.getSource() == txtNombre)
			  return txtNombre.getText().length() > 30;
			  
		  if(e.getSource() == txtEdad)
			  return txtNombre.getText().length() > 3;
				  
		  return txtNombre.getText().length() > 4;
	  }
	  
	public void clickOnEnter() {
		if(mainFrame.getFocusOwner() == txtEstatura) {
			btnRegistrar.doClick();
		}
	}

	public void sendAlert() {
		tk.beep();
	}  
	
	public void close() {
		mainFrame.setVisible(false);
		mainFrame.dispose();
	}

}