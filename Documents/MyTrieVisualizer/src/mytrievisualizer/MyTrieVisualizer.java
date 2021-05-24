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
import javax.swing.JFrame;
public class MyTrieVisualizer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
                TreePanel treePanel = new TreePanel();
		treePanel.setSize(500, 500);
		treePanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		treePanel.setResizable(true);
		treePanel.setVisible(true);
		treePanel.pack();		
	}
    }
    

