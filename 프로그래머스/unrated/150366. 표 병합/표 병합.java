import java.util.*;
import java.util.stream.*;

class Solution {
    List<String> output = new ArrayList();
    
    public String[] solution(String[] commands) {
        Cell[][] cells = new Cell[51][51];
        
        for(int i=1;i<51;i++){
            for(int j=1;j<51;j++){
                cells[i][j] = new Cell();
                cells[i][j].link(i,j);
            }    
        }
        
        try{
            for(String command:commands){
                exec(cells, command);
                
//                 for(int i=1;i<10;i++){
//                     for(int j = 1; j< 10;j++){
//                         System.out.printf("%6s|", cells[i][j].value);
//                     }
//                     System.out.println();
//                 }
            }    
        }catch(Exception e){
            while(true);
        }
        
        
        return output.stream().toArray(String[]::new);
    }
    
    void exec(Cell[][] cells, String command){
        String[] arguments = command.split(" ");
        String action = arguments[0];
        
        // System.out.println(command);
        
        if(action.equals("UPDATE")){
            update(cells, arguments);
        }else if(action.equals("MERGE")){
            merge(cells, arguments);
        }else if(action.equals("UNMERGE")){
            unmerge(cells, arguments);
        }else{
            print(cells, arguments);
        }
    }
    
    void update(Cell[][] cells, String[] arguments){
        if(arguments.length == 4){
            int r = Integer.parseInt(arguments[1]);
            int c = Integer.parseInt(arguments[2]);
            
            cells[r][c].value = arguments[3];
            
            // System.out.printf("\t(%d,%d)위치의 값이 %s로 변경%n", r,c,arguments[3]);
        }else{
            for(int i=1;i<cells.length;i++){
                for(int j = 1; j< cells[0].length;j++){
                    // System.out.printf("\tUPDATING (%d,%d)%n", i,j);
                    if(arguments[1].equals(cells[i][j].value)){
                        cells[i][j].value = arguments[2]; 
                        // System.out.printf("\t(%d,%d)위치의 값이 %s로 변경%n", i,j,arguments[2]);
                    }
                }
            }
        }
    }
    
    void merge(Cell[][] cells, String[] arguments){
        int r1 = Integer.parseInt(arguments[1]);
        int c1 = Integer.parseInt(arguments[2]);
        int r2 = Integer.parseInt(arguments[3]);
        int c2 = Integer.parseInt(arguments[4]);
        
        if(cells[r1][c1] == cells[r2][c2]){
            return;   
        }else if(cells[r1][c1].value != null && cells[r2][c2].value != null){
            for(List<Integer> pos:cells[r2][c2].linked){
                int x = pos.get(0);
                int y = pos.get(1);
                
                cells[x][y] = cells[r1][c1];
                
                cells[x][y].link(x,y);
            }
        }else if(cells[r1][c1].value == null && cells[r2][c2].value != null){
            for(List<Integer> pos:cells[r1][c1].linked){
                int x = pos.get(0);
                int y = pos.get(1);
                
                cells[x][y] = cells[r2][c2];
                
                cells[x][y].link(x,y);
            }
        }else{
            for(List<Integer> pos:cells[r2][c2].linked){
                int x = pos.get(0);
                int y = pos.get(1);
                
                cells[x][y] = cells[r1][c1];
                
                cells[x][y].link(x,y);
            }
        }
    }
    
    void unmerge(Cell[][] cells ,String[] arguments){
        int r = Integer.parseInt(arguments[1]);
        int c = Integer.parseInt(arguments[2]);
        
        String value = cells[r][c].value;
        
        for(List<Integer> pos:cells[r][c].linked){
            int x = pos.get(0);
            int y = pos.get(1);
            
            cells[x][y] = new Cell();
            cells[x][y].link(x,y);
        }
        
        cells[r][c].value = value;
    }
    
    void print(Cell[][] cells, String[] arguments){
        int r = Integer.parseInt(arguments[1]);
        int c = Integer.parseInt(arguments[2]);
        
        output.add(cells[r][c].value != null ? cells[r][c].value : "EMPTY");
    }
    
    static class Cell{
        String value;
        
        Set<List<Integer>> linked = new HashSet();
        
        Cell(){}
        
        Cell(String value){
            this.value = value;
        }
        
        void link(int r, int c){
            linked.add(Arrays.asList(r,c));
        }
    }
}