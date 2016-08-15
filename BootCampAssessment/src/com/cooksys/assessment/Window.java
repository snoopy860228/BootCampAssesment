package com.cooksys.assessment;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.io.File;
import java.awt.GridLayout;
import java.util.List;

public class Window {

	private JFrame frame;
	JList<String> list;
	JList<String> list_1;
	DefaultListModel<String> pcPartsNames1;

	/**
	 * Launch the application. The main method is the entry point to a Java application. 
	 * For this assessment, you shouldn't have to add anything to this.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application. This is the constructor for this Window class.
	 * All of the code here will be executed as soon as a Window object is made.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame. This is where Window Builder
	 * will generate its code.
	 */
	public void initialize() {
		frame = new JFrame("PC Part Builder");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Load");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Save");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Exit");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
        final DefaultListModel<String> pcPartsNames = new DefaultListModel<String>();
		
		pcPartsNames.addElement("Case");
		pcPartsNames.addElement("Motherboard");
		pcPartsNames.addElement("CPU");
		pcPartsNames.addElement("GPU");
		pcPartsNames.addElement("PSU");
		pcPartsNames.addElement("RAM");
		pcPartsNames.addElement("HDD");
		
		list = new JList<String>(pcPartsNames);
		list.setVisibleRowCount(3);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		
		pcPartsNames1 = new DefaultListModel<String>();
		
		JPanel panel = new JPanel();
		
		JButton addButton = new JButton(">>");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pcPartsNames1.addElement(list.getSelectedValue());
				pcPartsNames.removeElement(list.getSelectedValue());
			}
		});
		
		list_1 = new JList<String>(pcPartsNames1);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.setSelectedIndex(0);
		frame.getContentPane().setLayout(new GridLayout(0, 3, 0, 0));
		frame.getContentPane().add(list);
		frame.getContentPane().add(panel);
		
		JButton removeButton = new JButton("<<");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pcPartsNames1.removeElement(list_1.getSelectedValue());
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(47)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(removeButton)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(addButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(48))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(80)
					.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(removeButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(88))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().add(list_1);
	}
	
	public void save(){
		
		File file = new File("C:\\Users\\Jorge\\Downloads\\file.xml");

		try {
			
			Elements temp = new Elements();
			List<String> parts = temp.getParts();
			
			Object[] temp1 = pcPartsNames1.toArray();
			
			for(Object word : temp1){
				parts.add((String)word);
			}
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Elements.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(temp, file);
			jaxbMarshaller.marshal(temp, System.out);

		      } catch (JAXBException e) {
			   e.printStackTrace();
		      }
	}
	
    public void load(){
    	//TODO
	}

}
