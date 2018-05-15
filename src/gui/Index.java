package gui;
import javax.swing.JFrame;

import javax.swing.JTextField;
import javax.swing.JButton;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.SystemColor;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;

/**
 * This class represents the initial screen. It's responsible to do the search and 
 * other things that can be see in the execution 
 *  
 * @author 	Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @author 	Luis Eduardo  (cruxiu@ufrn.edu.br)
 * @version 14.05.2018
 */
public class Index extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private String proteinSequence;
	private JTextArea textArea;
	
	public Index () {
		getContentPane().setBackground(SystemColor.activeCaption);
		
		JLabel lblSequnciaDeProtenas = new JLabel("Prontein Sequence");
		lblSequnciaDeProtenas.setForeground(new Color(25, 25, 112));
		lblSequnciaDeProtenas.setHorizontalAlignment(SwingConstants.CENTER);
		lblSequnciaDeProtenas.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		
		textField = new JTextField();
		textField.setBackground(new Color(245, 245, 220));
		textField.setFont(new Font("Arial", Font.PLAIN, 16));
		textField.setColumns(10);
		
		JLabel lblProtenasAlinhadas = new JLabel("Aligned Proteins");
		lblProtenasAlinhadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblProtenasAlinhadas.setForeground(new Color(25, 25, 112));
		lblProtenasAlinhadas.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		
		
		JButton btnAling = new JButton("Send");
		btnAling.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		btnAling.setForeground(new Color(25, 25, 112));
		btnAling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				proteinSequence = textField.getText();
				textField.setText("");
			}
		});
		
//		JScrollPane jsPane = new JScrollPane();
//		jsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		jsPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		jsPane.add(textArea);
		
		JLabel lblNameProject = new JLabel("MSA Tool");
		lblNameProject.setForeground(new Color(25, 25, 112));
		lblNameProject.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(242)
					.addComponent(lblNameProject, GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
					.addGap(220))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSequnciaDeProtenas)
							.addContainerGap(979, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblProtenasAlinhadas)
							.addContainerGap(996, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 1095, Short.MAX_VALUE)
								.addComponent(btnAling))
							.addGap(23))))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 552, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(566, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNameProject)
					.addGap(54)
					.addComponent(lblSequnciaDeProtenas)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAling)
					.addGap(11)
					.addComponent(lblProtenasAlinhadas)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(129, Short.MAX_VALUE))
		);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		lblProtenasAlinhadas.setLabelFor(textArea);
		textArea.setFont(new Font("Arial", Font.PLAIN, 14));
		textArea.setBackground(new Color(245, 245, 220));
		
		getContentPane().setLayout(groupLayout);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocation(700, 10);
		setSize(600, 380);
		setVisible(true);
	}
	
	public String getProteinSequence () {
		return proteinSequence;
	}
	
	public void setProteinAling (String align) {
		textArea.setText(align);
	}
	
	public static void main(String[] args) {
		Index index = new Index ();
	}
}