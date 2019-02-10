package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements ActionListener {

	private JPanel northPanel,southPanel,centerPanel,westPanel,eastPanel;
	private JRadioButton rbtNombre,rbtEdad,rbtEstatura,rbtAll;
	private JButton btnRegistrar,btnConsulta,btnSalir;
	private JTextField txtNombre, txtEdad,txtEstatura;
	
	public MainFrame() {
		createNorthPanel();
		createSouthPanel();
		createCenterPanel();
		createExtraPanels();
		
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
		centerPanel = new JPanel(new GridLayout(0,2,10,10));
				
		txtNombre = new JTextField(20);
		txtEdad = new JTextField(20);
		txtEstatura = new JTextField(20);
		
		centerPanel.add(new JLabel("Nombre: ", SwingConstants.RIGHT));
		centerPanel.add(txtNombre);
		centerPanel.add(new JLabel("Edad: ", SwingConstants.RIGHT));
		centerPanel.add(txtEdad);
		centerPanel.add(new JLabel("Estatura: ", SwingConstants.RIGHT));
		centerPanel.add(txtEstatura);
		
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
	
	private void crateListeners() {
		rbtNombre.addActionListener(this);
		rbtEdad.addActionListener(this);
		rbtEstatura.addActionListener(this);
		rbtAll.addActionListener(this);
		
		btnRegistrar.addActionListener(this);
		btnConsulta.addActionListener(this);
		btnSalir.addActionListener(this);
		
		txtNombre.addActionListener(this);
		txtEdad.addActionListener(this);
		txtEstatura.addActionListener(this);
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
		
	}
	
	private void actionRadioButton(ActionEvent e) {
		
	}
	
	private void actionTextField(ActionEvent e) {
		
	}
	
}
