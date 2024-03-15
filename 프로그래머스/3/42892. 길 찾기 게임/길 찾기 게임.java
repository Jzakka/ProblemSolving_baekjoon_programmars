import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

public class Solution {
    int n;
    TreeMap<Integer, List<Node>> nodes = new TreeMap(Comparator.reverseOrder());
    int[][] ans;
    int preIdx=0,postIdx=0;
    public int[][] solution(int[][] nodeinfo) {
        n = nodeinfo.length;
        ans = new int[][]{new int[n], new int[n]};
        
        for(int i=0;i<n;i++){
            int num = i + 1;
            Node node= new Node(num, nodeinfo[i][0], nodeinfo[i][1]);
            nodes.computeIfAbsent(node.y, k -> new ArrayList()).add(node);
        }
        nodes.forEach((k,v) -> v.sort(Node::compareTo));
        
        Node root = nodes.firstEntry().getValue().get(0);
        
        build();
        
        preOrder(root);
        out.println();
        postOrder(root);
        
        return ans;
    }
    
    void build(){
        for(Map.Entry<Integer, List<Node>> e : nodes.entrySet()){
            if(e.equals(nodes.firstEntry())){continue;}
            link(e);
        }
    }
    
    void link(Map.Entry<Integer, List<Node>> e){
        List<Node> upperLayer = nodes.lowerEntry(e.getKey()).getValue();
        int idx = 0;
            
        for(Node node : e.getValue()){
            while(idx<upperLayer.size()){
                Node parent = upperLayer.get(idx);
                if(parent.leftLinkable(node)){
                    parent.linkLeft(node);
                    break;
                }else if(parent.rightLinkable(node)){
                    parent.linkRight(node);
                    idx++;
                    break;
                }else{
                    idx++;
                }
            }
        }
    }
    
    void preOrder(Node current){
        ans[0][preIdx++] = current.num;
        if(current.left != null){
            preOrder(current.left);
        }
        if(current.right != null){
            preOrder(current.right);
        }
    }
    
    void postOrder(Node current){
        if(current.left != null){
            postOrder(current.left);
        }
        if(current.right != null){
            postOrder(current.right);
        }
        ans[1][postIdx++] = current.num;
    }
}

class Node implements Comparable<Node>{
    int num;
    int x,y;
    int lb=Integer.MIN_VALUE,rb=Integer.MAX_VALUE;
    Node parent;
    Node left;
    Node right;
    
    public Node(int num, int x, int y) {
        this.num = num;
        this.x = x;
        this.y = y;
    }
    
    public void linkLeft(Node node){
        this.left = node;
        node.parent = this;
        node.lb = this.lb;
        node.rb = this.x;
    }
    
    public void linkRight(Node node){
        this.right = node;
        node.parent = this;
        node.rb = this.rb;
        node.lb = this.x;
    }
    
    public int compareTo(Node n){
        if(this.y == n.y){
            return this.x - n.x;
        }
        return n.y - this.y;
    }
    
    public boolean leftLinkable(Node child){
        return lb < child.x && child.x < this.x;
    }
    
    public boolean rightLinkable(Node child){
        return this.x < child.x  && child.x < rb;
    }
}