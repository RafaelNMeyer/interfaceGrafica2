package hfdrhfdr;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TesteFrame2 extends JFrame {

	private JLabel label1;
	private JTextField texto1;
	private JTextField texto2;
	private JButton b1;
	private JCheckBox boldBox;
	private JCheckBox italicBox;
	private JTextField texto3;
	private JRadioButton boldRadioB;
	private JRadioButton italicRadioB;
	private JRadioButton plainRadioB;
	private ButtonGroup grupoRadio;
	private JComboBox comboBox;
	private JLabel label2;
	private static final String[] names= {"download.jpeg","download (1).jpeg","download (2).jpeg"};
	private Icon[] icons = {new ImageIcon(getClass().getResource(names[0])),
			new ImageIcon(getClass().getResource(names[1])),
			new ImageIcon(getClass().getResource(names[2]))};
	private JList listaCores;
	private static final String[] nomeCores= {"Branco","preto","azul","Amarelo",
			"Vermelho","Verde","Rosa"};
	private static final Color[]cores= {Color.WHITE,Color.BLACK,Color.BLUE,
			Color.YELLOW,Color.RED,Color.GREEN,Color.PINK};
	private JList listaCores2;
	private JList listaCopia;
	private JButton botaoCopia;
	
	
	
	public TesteFrame2() {
		super("Meu Frame");
		setLayout(new FlowLayout());

		label1 = new JLabel("Escreva seu nome");
		add(label1);

		texto1 = new JTextField(15);
		add(texto1);

		texto2 = new JTextField("Nao editavel", 15);
		texto2.setEditable(false);
		texto2.setFont(new Font("Serif", Font.PLAIN, 14));
		add(texto2);

		texto3 = new JTextField("Texto radio", 15);
		add(texto3);

		boldBox = new JCheckBox("Negrito");
		italicBox = new JCheckBox("Italico");
		add(boldBox);
		add(italicBox);

		boldRadioB = new JRadioButton("Negrito");
		italicRadioB = new JRadioButton("Itálico");
		plainRadioB = new JRadioButton("Normal");
		add(boldRadioB);
		add(italicRadioB);
		add(plainRadioB);

		grupoRadio = new ButtonGroup();
		grupoRadio.add(boldRadioB);
		grupoRadio.add(italicRadioB);
		grupoRadio.add(plainRadioB);

		b1 = new JButton("Clique aqui");
		add(b1);
		
		comboBox = new JComboBox(names);
		comboBox.setMaximumRowCount(3);
		add(comboBox);
		
		listaCores = new JList(nomeCores);
		listaCores.setVisibleRowCount(4);
		listaCores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(new JScrollPane(listaCores));
		
		listaCores.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				getContentPane().setBackground(cores[listaCores.getSelectedIndex()]);	
				
			}
		});
		
		listaCores2 = new JList(nomeCores);
		listaCores2.setVisibleRowCount(5);
		listaCores2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		add(new JScrollPane(listaCores2));
		botaoCopia = new JButton("Copia >>>");
		botaoCopia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listaCopia.setListData(listaCores2.getSelectedValuesList().toArray());
			}
		});
		add(botaoCopia);
		listaCopia = new JList();
		listaCopia.setVisibleRowCount(5);
		listaCopia.setFixedCellHeight(15);
		listaCopia.setFixedCellWidth(100);
		listaCopia.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		add(new JScrollPane(listaCopia));

		OuvidorTexto handlerT1 = new OuvidorTexto();
		texto1.addActionListener(handlerT1);

		OuvidorBotao handlerB1 = new OuvidorBotao();
		b1.addActionListener(handlerB1);

		OuvidorCheckBox handlerBox = new OuvidorCheckBox();
		boldBox.addItemListener(handlerBox);
		italicBox.addItemListener(handlerBox);

		OuvidorRadioButton handlerRadio = new OuvidorRadioButton();
		boldRadioB.addItemListener(handlerRadio);
		italicRadioB.addItemListener(handlerRadio);
		plainRadioB.addItemListener(handlerRadio);
		
		OuvidorComboBox handlerComboBox = new OuvidorComboBox();
		comboBox.addItemListener(handlerComboBox);
		label2 = new JLabel(icons[0]);
		add(label2);
	}

	private String a = "";

	private class OuvidorTexto implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == texto1) {
				a = String.format("Nome Inserido: %s", e.getActionCommand());
			}
			JOptionPane.showMessageDialog(null, a);

		}

	}

	private class OuvidorBotao implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			JOptionPane.showMessageDialog(TesteFrame2.this, String.format("O seu nome é: " + a, e.getActionCommand()));

		}

	}

	private class OuvidorCheckBox implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			Font font = null;
			if (boldBox.isSelected() && italicBox.isSelected()) {
				font = new Font("Arial", Font.BOLD + Font.ITALIC, 14);
			} else {
				if (boldBox.isSelected()) {
					font = new Font("Serif", Font.BOLD + Font.ITALIC, 14);
				} else {
					if (italicBox.isSelected()) {
						font = new Font("Courier New", Font.ITALIC, 14);
					} else {
						font = new Font("Serif", Font.PLAIN, 14);
					}

				}
			}
			texto2.setFont(font);
		}

	}

	private class OuvidorRadioButton implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			Font font = null;
			if (boldRadioB.isSelected()) {
				font = new Font("Times", Font.BOLD, 16);
			} else {
				if (italicRadioB.isSelected()) {
					font = new Font("Times", Font.ITALIC, 16);
				} else {
					font = new Font("Times", Font.PLAIN, 16);
				}
			}

			texto3.setFont(font);
		}

	}
	private class OuvidorComboBox implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			
			label2.setIcon(icons[comboBox.getSelectedIndex()]);
			
		}
		
	}

}
