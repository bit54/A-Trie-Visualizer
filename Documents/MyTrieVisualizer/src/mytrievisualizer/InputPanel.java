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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputPanel extends JPanel{
    
    JButton adding;
	JButton remove;
	JButton find;
	JButton print;
	JButton alphSort;
	JButton valSort;
	JButton clear;
	JButton autocomplete;

	JTextField addfield;
	JTextField valueField;
	JTextField removefield;
	JTextField findfield;
	JTextField autocompleteField;
	
	TreeDisplay tree;
        
        public InputPanel(TreeDisplay tree) {
		
		this.tree = tree;
		
	//	setSize(100, 50);

		adding = new JButton("Add");
		adding.addActionListener(new ButtonListener(0));
		remove = new JButton("Remove");
		remove.addActionListener(new ButtonListener(1));
		find = new JButton("Find");
		find.addActionListener(new ButtonListener(2));
		print = new JButton("Print all Students!");
		print.addActionListener(new ButtonListener(3));
		alphSort = new JButton("Sort Tree By Letter");
		alphSort.addActionListener(new ButtonListener(4));
		valSort = new JButton("Sort Tree By Value ");
		valSort.addActionListener(new ButtonListener(5));
		clear = new JButton("Clear Tree");
		clear.addActionListener(new ButtonListener(6));
		autocomplete = new JButton("Autocomplete Suggestions:");
		autocomplete.addActionListener(new ButtonListener(7));

		addfield = new JTextField(5);
		valueField = new JTextField(5);
		
		removefield = new JTextField(10);
		findfield = new JTextField(10);
		autocompleteField = new JTextField(10);

		add(alphSort);
		add(valSort);
		add(adding);
		add(addfield);
		add(valueField);
		add(remove);
		add(removefield);
		add(find);
		add(findfield);
		add(print);
		add(clear);
//		add(autocomplete);
//		add(autocompleteField);
	}
        

        	class ButtonListener implements ActionListener {
		int action;

		public ButtonListener(int action) {
			this.action = action;
		}

		public void actionPerformed(ActionEvent e) {
			switch (action) {
			//case 0 is when the add button is pressed. Adds the String and value into the trie.
				case 0:	
						if(addfield.getText().equals("") || addfield.getText() == null){
							DetailPanel.addText("Please enter a valid string to add to the trie");
							return;
						}
						int value;
						try{
							value = Integer.parseInt(valueField.getText());
						}catch(NumberFormatException n){
							DetailPanel.addText("Please enter a number for the value");
							return;
						}
						((TreeDisplay) tree).addToTrie(addfield.getText(), value);
						DetailPanel.addText("The String you added was: "+ addfield.getText() + " and the value inputted was: " + valueField.getText());
						addfield.setText("");
						valueField.setText("");
					break;
			//case 1 is when the remove button is pressed. Removes the string if it exists and displays the value it had.
				case 1: 
						value = tree.removeInTrie(removefield.getText());
						if(value == -1){
							DetailPanel.addText("The String you inputted DOES NOT EXIST. Please try again.");
						}else{
							DetailPanel.addText("The String you removed is: " + removefield.getText() 
									+ " and its value was: " + value);
						}
						removefield.setText("");
					break;
			//case 2 is when the find button is pressed. Highlights the path to the node if it exists and displays the value.
				case 2: 
						String text = findfield.getText();
						if (text.isEmpty()) {
							DetailPanel.addText("Please enter the string you would like to find.");
						}
						else{
							int val = tree.findInTrie(text);
							if(val == -1){
								Collection<String> strings = tree.autocomplete(text);
								if(strings.isEmpty()){
									DetailPanel.addText("The String was not found in the Trie");
								}else{
									DetailPanel.addText("Did you mean one of these below?");
									for(String s: strings){
										DetailPanel.addText(s);
									}
								}
							}else{
								DetailPanel.addText("The String you inputted has been expanded and highlighted. Its value is: " + val);
							}
						}
					break;
			//runs a preorder traversal on the tree which displays the strings in lexographically sorted order.
				case 3: tree.preorderTraversal();
					break;
				case 4: 
					tree.alphaSort();
					break;
				case 5:
					tree.valSort();
					break;
				case 6:
					tree.clearAll();
					DetailPanel.addText("You have cleared the entire Tree");
					break;
				/*case 7:
					Collection<String> strings = tree.autocomplete(autocompleteField.getText());
					if(strings.isEmpty()){
						DetailPanel.addText("No suggestions have been found for the inputted text.");
					}else{
						DetailPanel.addText("The following suggestions were found:");
						for(String s: strings){
							DetailPanel.addText(s);
						}
					}
				break;*/
			}
		}

	}


}
