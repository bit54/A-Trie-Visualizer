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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;


public class TreeDisplay extends JPanel implements TreeSelectionListener {
    	private static JTree tree;
	private static TrieNode root;
	private static DefaultTreeModel model;
//	JScrollPane panel;
	
	JFrame trees;
	static Map<String, Integer> entries = new HashMap<String,Integer>();
	
	public TreeDisplay(TreePanel trees) {
		this.trees = trees;
		root = new TrieNode('a',"Trie", null);
		model = new DefaultTreeModel(root);
		tree = new JTree(model);
		JScrollPane panel = new JScrollPane(tree);
		panel.setPreferredSize(new Dimension(500,400));
		add(panel);
	}

	/**
	 * Adds the name and value to the trie.
	 * @param name
	 * @param value
	 */
	public void addToTrie(String name, int value) {
		TrieNode temp = root;
		int i = 0;
		entries.put(name,value);
		while (temp.hasChild(name.charAt(i))){
			temp = temp.getChild(name.charAt(i));
			i++;
			if (i>=name.length()){
				break;
			}
		}
		if (i == name.length()){
			temp.setString(name);
			temp.setValue(value);
		}
		else {
			if (i==0) {
				while(i<name.length()){
					temp.giveChild(name.charAt(i),model);
					temp = temp.getChild(name.charAt(i));
					i++;
				}
				temp.setString(name);
				temp.setValue(value);
			}
			else {
				while(i<name.length()){
					temp.giveChild(name.charAt(i), model);
					temp = temp.getChild(name.charAt(i));
					i++;
				}
				temp.setString(name);
				temp.setValue(value);
			}
		}
	}
        
        	/**
	 * Makes a recursive call to find the key in the trie.
	 * @param key
	 * @return
	 */

        public int findInTrie(String key){
		if(root.getAllChildren().isEmpty()){
			return -1;
		}
		return findInTrie(root, key, 0);
	}

	private static int findInTrie(TrieNode node, String key, int index){
		if(node == null){
			return -1;
		}
		TrieNode child = node.getChild(key.charAt(index));
		if(child != null){
			index++;
			if(index == key.length()){
				TreePath pathToNode = new TreePath(child.getPath());
				tree.scrollPathToVisible(pathToNode);
				tree.setSelectionPath(pathToNode);
				return child.getValue();
			}
			return findInTrie(child, key, index);
		}
		return -1;
	}
	
        	/**
	 * Recursively removes the nodes in the tree until there is one or more child stemming from the node.
	 * @param key
	 * @return
	 */
	public int removeInTrie(String key){
		if(root.getAllChildren().isEmpty()){
			return -1;
		}
		return removeInTrie(root, key, 0);
	}

	private static int removeInTrie(TrieNode node, String key, int index){
		if(node == null){
			return -1;
		}
		TrieNode child = node.getChild(key.charAt(index));
		char c = key.charAt(index);
		if(child != null){
			index++;
			if(index == key.length()){
				int val = child.getValue();
				if(child.numChildren() == 0){
					node.removeChild(c, model);
				}else{
					child.setValue(-1);
				}
				entries.remove(key);
				return val;
			}
			int value = removeInTrie(child, key, index);
			if(child.numChildren() == 0){
				node.removeChild(c,model);
			}
			return value;
		}
		return -1;
	}

        	/**
	 * Prints a preorder traversal of the list.
	 */
	public void preorderTraversal(){
		DetailPanel.addText("Here is a sorted list of Strings in the Trie with their respective values.");
		preorderTraversal(root);
	}
	
	private void preorderTraversal(TrieNode node){
		TrieNode temp = node;
		
		Collection<TrieNode> children = temp.getAllChildren();
		if(children == null){
			return;
		}
		for(TrieNode n: children){
			preorderTraversal(n);
		}
		if(node.getValue() != -1){
			DetailPanel.addText(node.getString() + " : " + node.getValue());
		}
	}
	
	public void alphaSort(){
		ArrayList<Entry<String,Integer>> sorted = new ArrayList<Entry<String,Integer>>();
		Set<Entry<String, Integer>> names = entries.entrySet();
		for (Entry<String,Integer> e: names) {
			if (sorted.size() == 0) 
				sorted.add(e);
			else {
				sorted.add(e);
				int i = sorted.size()-1;
				while (((String) sorted.get(i).getKey()).compareTo(sorted.get(i-1).getKey()) < 0){
					Entry<String, Integer> temp = sorted.get(i);
					sorted.set(i, sorted.get(i-1));
					sorted.set(i-1, temp);
					i--;
					if ((i-1) == -1){
						break;
					}
				}
			}
		}
		clearAll();
		for (Entry<String,Integer> a: sorted){
			addToTrie((String)a.getKey(),(Integer) a.getValue()); 
		}
	}
	
	public void clearAll(){
		removeAll(root, null);
		entries = new HashMap<String,Integer>();
	}
	
	private static void removeAll(TrieNode node, TrieNode parent){
		Collection<TrieNode> children = node.getAllChildren();
		if(children == null){
			return;
		}
		TrieNode[] nodes = children.toArray(new TrieNode[children.size()]);
		for(int i = 0; i < nodes.length; i++){
			removeAll(nodes[i], node);
		}
		if(children.size() == 0 && parent != null){
			parent.removeChild(node.getCharacter(), model);
		}
	}
	
	public void valSort() {
		ArrayList<Entry<String,Integer>> sorted = new ArrayList<Entry<String,Integer>>();
		Set<Entry<String, Integer>> names = entries.entrySet();
		for (Entry<String,Integer> e: names) {
			if (sorted.size() == 0) 
				sorted.add(e);
			else {
				sorted.add(e);
				int i = sorted.size()-1;
				while (((Integer) sorted.get(i).getValue()<
						((Integer) sorted.get(i-1).getValue()))){
					Entry<String, Integer> temp = sorted.get(i);
					sorted.set(i, sorted.get(i-1));
					sorted.set(i-1, temp);
					i--;
					if ((i-1) == -1){
						break;
					}
				}
			}
		}
		clearAll();
		for (Entry<String,Integer> a: sorted){
			addToTrie((String)a.getKey(),(Integer) a.getValue()); 
		}
	}
	
	public Collection<String> autocomplete(String input){
		if(input == null){
			return new ArrayList<String>();
		}
		if(input.equals("")){
			return new ArrayList<String>();
		}
		Collection<String> strings = new ArrayList<String>();
		TrieNode start = autocomplete(root,input,0);
		if(start == null){
			return new ArrayList<String>();
		}
		if(start.getValue() != -1){
			strings.add(input);
		}
		if(!start.getAllChildren().isEmpty()){
			allOutcomes(start,input,strings);
		}
		
		return strings;
	}
	
	private static void allOutcomes(TrieNode node, String key, Collection<String> strings){
		for(TrieNode n: node.getAllChildren()){
			String newKey = key + n.getCharacter();
			if(n.getValue() != -1){
				strings.add(newKey);
			}
			allOutcomes(n,newKey,strings);
		}
	}
	
	private static TrieNode autocomplete(TrieNode node, String key, int index){
		if(node == null){
			return null;
		}
		TrieNode child = node.getChild(key.charAt(index));
		if(child != null){
			index++;
			if(index == key.length()){
				//TreePath pathToNode = new TreePath(child.getPath());
				//tree.scrollPathToVisible(pathToNode);
				//tree.setSelectionPath(pathToNode);
				return child;
			}
			return autocomplete(child, key, index);
		}
		return null;
	}

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}
