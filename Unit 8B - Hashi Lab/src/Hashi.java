import java.io.IOException;
import java.util.*;

public class Hashi {
    private static HashMap<Island, ArrayList<Island>> connections = new HashMap<>();

    public Hashi(char[][] puzzle) throws IOException {
        isleArrayToHashi(puzzle);
    }

    public static void isleArrayToHashi(char[][] puzzle) {
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                if(puzzle[i][j] != ' ' && puzzle[i][j] != '-' && puzzle[i][j] != '|' && puzzle[i][j] != '#' && puzzle[i][j] != '=' && puzzle[i][j] != (char)0) {
                    if (Character.isDigit(puzzle[i][j])) {
                        int value = Character.getNumericValue(puzzle[i][j]);
                        if (!connections.containsKey(new Island(i, j, value))) {
                            Island bruh = new Island(i, j, value);
                            connections.put(bruh, addNeighbors(bruh, puzzle));
                        }
                    }
                    
                }
            }
        }
    }

    public boolean isSolved(){
        return !hasMultipleClusters() && numbersWork();
    }

    private boolean numbersWork() {
        for (Island i : connections.keySet()){
            if(connections.get(i).size() != i.getValue()){
                break;
            }
            return true;
        }
        return false;
    }

    private boolean hasMultipleClusters() {
        Map<Island, Boolean> visited = new HashMap<>();
        for (Island island : connections.keySet()) {
            visited.put(island, false);
        }
        Iterator<Island> iterator = connections.keySet().iterator();
        if (iterator.hasNext()) {
            Island startIsland = iterator.next();
            dfs(startIsland, visited, connections);
        }
        for (Map.Entry<Island, Boolean> entry : visited.entrySet()) {
            if (!entry.getValue()) { 
                return true;
            }
        }
        return false;
    }
    
    private void dfs(Island currentIsland, Map<Island, Boolean> visited, HashMap<Island, ArrayList<Island>> connections) {
        visited.put(currentIsland, true);
        for (Island neighbor : connections.get(currentIsland)) {
            if (!visited.get(neighbor)) { 
                dfs(neighbor, visited, connections);  
            }
        }
    }

    private static ArrayList<Island> addNeighbors(Island isle, char[][] puzzle) {
        ArrayList<Island> neighbors = new ArrayList<>();
        int i = isle.getX();
        int j = isle.getY();
        
        // Check right direction
        if (j + 1 < puzzle[i].length && (puzzle[i][j + 1] == '-' || puzzle[i][j + 1] == '=')) {
            int tempJ = j;
            while (tempJ + 1 < puzzle[i].length && (puzzle[i][tempJ + 1] == '-' || puzzle[i][tempJ + 1] == '=')) {
                tempJ = tempJ + 1;
            }
            if (tempJ + 1 < puzzle[i].length && Character.isDigit(puzzle[i][tempJ + 1])) {
                int value = Character.getNumericValue(puzzle[i][tempJ + 1]);
                Island righty = new Island(i, tempJ + 1, value);
                neighbors.add(righty);  
                if (puzzle[i][j + 1] == '=') {
                    neighbors.add(righty);  
                }
            }
        }
        // Check left direction
        if (j - 1 >= 0 && (puzzle[i][j - 1] == '-' || puzzle[i][j - 1] == '=')) {
            int tempJ = j;
            while (tempJ - 1 >= 0 && (puzzle[i][tempJ - 1] == '-' || puzzle[i][tempJ - 1] == '=')) {
                tempJ = tempJ - 1;
            }
            if (tempJ - 1 >= 0 && Character.isDigit(puzzle[i][tempJ - 1])) {
                int value = Character.getNumericValue(puzzle[i][tempJ - 1]);
                Island lefty = new Island(i, tempJ - 1, value);
                neighbors.add(lefty);  
                if (puzzle[i][j - 1] == '=') {
                    neighbors.add(lefty);  
                }
            }
        }
        // Check up direction
        if (i - 1 >= 0 && (puzzle[i - 1][j] == '#' || puzzle[i - 1][j] == '|')) {
            int tempI = i;
            while (tempI - 1 >= 0 && (puzzle[tempI - 1][j] == '#' || puzzle[tempI - 1][j] == '|')) {
                tempI = tempI - 1;
            }
            if (tempI - 1 >= 0 && Character.isDigit(puzzle[tempI - 1][j])) {
                int value = Character.getNumericValue(puzzle[tempI - 1][j]);
                Island upy = new Island(tempI - 1, j, value);
                neighbors.add(upy);
                if (puzzle[i - 1][j] == '#') {
                    neighbors.add(upy);  
                }
            }
        }
        // Check down direction
        if (i + 1 < puzzle.length && (puzzle[i + 1][j] == '#' || puzzle[i + 1][j] == '|')) {
            int tempI = i;
            while (tempI + 1 < puzzle.length && (puzzle[tempI + 1][j] == '#' || puzzle[tempI + 1][j] == '|')) {
                tempI = tempI + 1;
            }
            if (tempI + 1 < puzzle.length && Character.isDigit(puzzle[tempI + 1][j])) {
                int value = Character.getNumericValue(puzzle[tempI + 1][j]);
                Island downy = new Island(tempI + 1, j, value);
                neighbors.add(downy);
                if (puzzle[i + 1][j] == '#') {
                    neighbors.add(downy);  
                }
            }
        }
        return neighbors;
    }
    
    public HashMap<Island, ArrayList<Island>> getConnections() {
        return connections;
    }
}
