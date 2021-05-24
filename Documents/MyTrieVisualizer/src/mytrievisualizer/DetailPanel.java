/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytrievisualizer;

/**
 *
 * @author IFTEKHAR
 */
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;


public class DetailPanel extends JPanel{
    
    	private static JFrame tree;
	private static JTextArea output;
	private static String text;
        private JLabel label;
        
        public DetailPanel(TreePanel tree){
			DetailPanel.tree = tree;
			this.setPreferredSize(new Dimension(500,25));
			output = new JTextArea(25,40);
			output.setEditable(false);
			JScrollPane pane = new JScrollPane(output);
			text = new String("Trie Implementation:\n");
			output.setText(text);
			add(pane);
		}
        		/**
		 * Allows anyone to print to the detail panel on the right side of the screen.
		 * @param text
		 */
		public static void addText(String text){
			output.append(text + "\n");
		}


}
