package views;

import javax.swing.*;

import Structures.*;

import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements ActionListener,KeyListener {
	
	private class Empleado{
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
				return Rutinas.PonBlancos(nombre, 30);
			if(criterioOrdenamiento == 2)
				return Rutinas.PonCeros(edad,4);
			if(criterioOrdenamiento == 3)
				return Rutinas.PonCeros(estatura,4);
			
			return Rutinas.PonCeros(edad,4) + Rutinas.PonCeros(estatura, 4)+ Rutinas.PonBlancos(nombre, 30);
		}
		
	}

	private JPanel northPanel,southPanel,centerPanel,westPanel,eastPanel;
	private JRadioButton rbtNombre,rbtEdad,rbtEstatura,rbtAll;
	private JButton btnRegistrar,btnConsulta,btnSalir;
	private JTextField txtNombre, txtEdad,txtEstatura;
	private ListaDBL<Empleado> empleados = new ListaDBL<>();
	private static int criterioOrdenamiento = 1; 
	
	public MainFrame() {
		createNorthPanel();
		createSouthPanel();
		createCenterPanel();
		
		setResizable(false);
		setSize(400,300);
		setLocationRelativeTo(null);
		setVisible(true);
		
		crateListeners();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public static void main(String [] a) {
		new MainFrame();
	}
	
	private void createNorthPanel() {
		northPanel = new JPanel(); 
		ButtonGroup group = new ButtonGroup();
		rbtNombre = new JRadioButton("Nombre");
		rbtEdad = new JRadioButton("Edad");
		rbtEstatura = new JRadioButton("Estatura");
		rbtAll = new JRadioButton("Edad-Estatura-Nombre");
		
		group.add(rbtNombre);
		group.add(rbtEdad);
		group.add(rbtEstatura);
		group.add(rbtAll);
		
		northPanel.add(rbtNombre);
		northPanel.add(rbtEdad);
		northPanel.add(rbtEstatura);
		northPanel.add(rbtAll);
		
		add(northPanel,BorderLayout.NORTH);
	}
	
	private void createSouthPanel() {
		southPanel = new JPanel();
		
		btnRegistrar = new JButton("Registrar");
		btnConsulta = new JButton("Consultar");
		btnSalir = new JButton("Salir");
		
		southPanel.add(btnRegistrar);
		southPanel.add(btnConsulta);
		southPanel.add(btnSalir);
		
		add(southPanel,BorderLayout.SOUTH);
	}
	
	private void createCenterPanel() {
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
		
		centerPanel.setBackground(new Color(187,168,250));
		add(centerPanel);
	}

	private void createExtraPanels() {
		eastPanel = new JPanel();
		westPanel = new JPanel();
		
		eastPanel.setBackground(new Color(153,124,250));
		westPanel.setBackground(new Color(153,124,250));
		
		add(eastPanel, BorderLayout.EAST);
		add(westPanel, BorderLayout.WEST);
	}
	
	private void showDialog() {
		if(empleados.Length() == 0) {
			JOptionPane.showMessageDialog(null, "Aún no hay empleados");
			return;
		}

		JDialog dialog = new JDialog(this,true);
		JTextArea textArea = new JTextArea();
		dialog.setTitle("Consulta");
		dialog.setSize(300,300);
		dialog.setLocationRelativeTo(null);
		dialog.add(textArea);
		//dialog.setLayout(new BoxLayout(dialog,BoxLayout.Y_AXIS));
		dialog.setLayout(new GridLayout(0,1,10,10));
		NodoDBL<Empleado> empleadoActual = empleados.getFrente();
		while(empleadoActual != null) {
			textArea.setText(textArea.getText()+"\n"+"Nombre: "+empleadoActual.Info.nombre+"  "+"Edad: "+empleadoActual.Info.edad+"  "+"Estatura: "+empleadoActual.Info.estatura);
			empleadoActual = empleadoActual.getSig();
		}
		dialog.setVisible(true);
	}
	
	private void crateListeners() {
		rbtNombre.addActionListener(this);
		rbtEdad.addActionListener(this);
		rbtEstatura.addActionListener(this);
		rbtAll.addActionListener(this);
		
		btnRegistrar.addActionListener(this);
		btnConsulta.addActionListener(this);
		btnSalir.addActionListener(this);
		
		txtNombre.addActionListener(this);
		txtNombre.addKeyListener(this);
		txtEdad.addActionListener(this);
		txtEdad.addKeyListener(this);
		txtEstatura.addActionListener(this);
		txtEstatura.addKeyListener(this);
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
	
	private void actionButton(ActionEvent e) {
		JButton onClick = (JButton)e.getSource();
		if(onClick == btnRegistrar) {
			if(txtNombre.getText().length() == 0 || txtEdad.getText().length() == 0 || txtEstatura.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "Algunos campos no han sido llenados");
				return;
			}
			
			Empleado empleado = new Empleado("",0,0);
			empleado.nombre = txtNombre.getText();
			empleado.edad = Integer.parseInt(txtEdad.getText());
			empleado.estatura = Integer.parseInt(txtEstatura.getText());
			empleados.InsertaOrdenado(empleado);
			
			txtNombre.setText("");
			txtEdad.setText("");
			txtEstatura.setText("");
			
			txtNombre.requestFocus();
			return;
		}
		
		if(onClick == btnConsulta) {
			showDialog();
			return;
		}
		
		if(onClick == btnSalir) {
			setVisible(false);
            dispose();
		}
	}
	
	private void actionRadioButton(ActionEvent e) {
		int criterioAnterior = criterioOrdenamiento;
		JRadioButton radioOnClick = (JRadioButton) e.getSource();
		if(radioOnClick == rbtNombre)
			criterioOrdenamiento = 1;
		if(radioOnClick == rbtEdad)
			criterioOrdenamiento = 2;
		if(radioOnClick == rbtEstatura)
			criterioOrdenamiento = 3;
		if(radioOnClick == rbtAll)
			criterioOrdenamiento = 4;
		
		if(criterioAnterior != criterioOrdenamiento)
			ordenarListaPorCriterio(empleados.getFrente(),empleados.getFin(),1,empleados.Length());
	}
	
	private void actionTextField(ActionEvent e) {
		JTextField field = (JTextField) e.getSource();
		
		if(field == txtNombre)
			txtEdad.requestFocus();
		if(field == txtEdad)
			txtEstatura.requestFocus();
		if(field == txtEstatura)
			btnRegistrar.requestFocus();
	}

	@Override
	public void keyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent evt) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		char typedChar = evt.getKeyChar();
		JTextField field = (JTextField) evt.getSource(); 
		System.out.println(typedChar);
		if(field == txtEdad || field == txtEstatura) {
			if(!Character.isDigit(typedChar)) {
				tk.beep();
				evt.consume();
			}
			return;
		}
		
		if(!Character.isLetter(typedChar)) {
			tk.beep();
			evt.consume();
		}
		
	}
	
	//Auxiliares
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
	  
	  private void imprimirEmpleados(ListaDBL<Empleado> empleados) {
		  NodoDBL<Empleado> empleadoActual = empleados.getFrente();
		  while(empleadoActual != null) {
			  System.out.println("Nombre: "+empleadoActual.Info.nombre+"\t"+"Edad: "+empleadoActual.Info.edad+"\t"+"Estatura: "+empleadoActual.Info.estatura);
			  empleadoActual = empleadoActual.getSig();
		  }
		  System.out.println();
	  }
}
