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
import java.util.Collection;
import java.util.TreeMap;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
public class TrieNode extends DefaultMutableTreeNode {
    
    private static final long serialVersionUID = 1L;
	private char character;
	private String string;
	private TrieNode parent;
        
        private TreeMap<Character, TrieNode> children;
	private int value;
	
        public TrieNode(char c, String name, TrieNode parent){
		super(name);
		children = new TreeMap<Character,TrieNode>();
		value = -1;
		this.character = c;
		string = null;
		this.parent = parent;
	}
        
        public int getValue(){
		return value;
	}
	
	public void setValue(int value){
		this.value = value;
	}
	
	public TrieNode getChild(char c){
		return children.get(c);
	}
	
	public void giveChild(char c, DefaultTreeModel model){
		TrieNode newNode = new TrieNode(c, c + "", this);
		children.put(c, newNode);
		super.add(newNode);
		model.insertNodeInto(newNode, this, children.size() - 1);
	}
	
	public boolean hasChild(char c){
		return children.containsKey(c);
	}
	
	public int numChildren(){
		return children.size();
	}
	
	public void removeChild(char c, DefaultTreeModel model){
		TrieNode node = children.get(c);
		model.removeNodeFromParent(node);
		children.remove(c);
	}
	
	public Collection<TrieNode> getAllChildren(){
		return children.values();
	}
	public char getCharacter(){
		return character;
	}
	
	public void setString(String s){
		this.string = s;
	}
	
	public String getString(){
		return string;
	}

    
}
