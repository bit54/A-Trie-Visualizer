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
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
@SuppressWarnings("serial")
public class TreePanel extends JFrame implements TreeSelectionListener {

        private JPanel treepane;
	private JPanel detailpane;
	private JPanel inputpane;
	TrieNode root;
        private JLabel label;
        
        public TreePanel() {
		this.setLayout(new BorderLayout());
		this.setTitle("Trie");
		detailpane = new DetailPanel(this);
		treepane = new TreeDisplay(this);
		inputpane = new InputPanel((TreeDisplay) treepane);
		label= new JLabel("A Trie Visualizer created by IFTEKHAR AHMAD");
                label.setFont(new Font("Serif",Font.BOLD,18));
                label.setBounds(50,0,10,10);
                
		add(treepane, BorderLayout.WEST);
		add(detailpane, BorderLayout.EAST);
		add(inputpane, BorderLayout.SOUTH);
                add(label,BorderLayout.NORTH);
	}

        	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
        
}
