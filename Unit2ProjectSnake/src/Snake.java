public class Snake {
    int dir = 3;        // Direction of the snake (0 East, 1 North, 2 South, 3 West)
    Body head;          // Head of the snake
    boolean alive;      // Flag to check if the snake is alive
    

    // Default constructor
    public Snake() {
        alive = true;
        
        head = new Body();
      
        
    }

    // Constructor with parameters
    public Snake(int dir, int row, int col, Body p) {
        this.dir = dir;
        head = new Body(row,col,p);
        alive = true;
        
        
    }

    // Update the snake based on a series of commands
    public boolean update(String commands) {
        char[] characters = commands.toCharArray();
        for(char c : characters){
            this.update(c);
        }
        return alive;
    }

    // Update the snake based on a single command
    public boolean update(char c) {
        switch (c) {
            case 'U':
                dir = 1; // Change direction to North
                break;
            case 'D':
                dir = 3; // Change direction to South
                break;
            case 'L':
                dir = 2; // Change direction to West
                break;
            case 'R':
                dir = 0; // Change direction to East
                break;
            case 'M':
                moveSnake(); // Move the snake and return its alive state
                break;
            case 'E':
                //TODO
            case 'F':
                //TODO
            default:
                return false; 
        }
        return alive; // Indicate that the snake is alive
    }

    private void moveSnake() {
        // Save old position of head
        int oldHeadRow = head.row;
        int oldHeadCol = head.col;
    
        // Move the head based on direction
        switch (dir) {
            case 0: // East
                head.col++;
                break;
            case 1: // North
                head.row--;
                break;
            case 3: // South
              System.out.println("MOVED HEAD SOUTH");
                head.row++;
                break;
            case 2: // West
                
                head.col--;
                break;
        }
        Body runner = head;
        while(runner.next!=null)
            runner = runner.next;
        runner.row = oldHeadRow; runner.col = oldHeadCol;
        runner.next = head.next;
        head.next = runner;
        Body runner2 = runner;
        while(runner2.next!=runner)
            runner2 = runner2.next;
        runner2.next = null; 
        System.out.println(dir);
        System.out.println(head.row + "" + head.col);
    
    }
    

    // Get the size of the snake (including the head)
    public int getSize() {
        int count = 0;
        Body temp = head; 
        while (temp != null && temp.next != null) {
            temp = temp.next;
            count++;
        }
        return count;
    }

    // Check if the snake is alive
    public boolean isAlive() {
        return alive;
    }

    // Reverse the snake (tail becomes head and direction is opposite)
    public void reverse() {
        switch (dir) {
            case 0: dir = 3; break; // East to West
            case 1: dir = 2; break; // North to South
            case 2: dir = 1; break; // South to North
            case 3: dir = 0; break; // West to East
        }
        Body prev = null;
        Body current = head.next; // Start with the first body segment
        Body next;
        while(current != null){
            next = current.next; 
            current.next = prev; 
            prev = current; 
            current = next;
        }
        head.next = prev;
        
    }

    private void updateDirection(String[][] Board) {
        switch(dir){
            case 0:
                Board[head.row][head.col] = ">";
                break;
            case 1:
                Board[head.row][head.col] = "^";
                break;
            case 3:
                Board[head.row][head.col] = "v";
                break;
            case 2:
                Board[head.row][head.col] = "<";
                break;
            default:
                Board[head.row][head.col] = "v";
                break;
        }
    }

    // Draw the snake in a 2D board, or draw the final state if the snake is dead
    public String toString() {
        
        String output = "";
        String[][] Board = new String[10][10];
       
        for(int i = 0; i < Board.length; i++){
            for(int j = 0; j < Board[0].length; j++){
                Board[i][j] = "=";
            } 
            output += "\n"; 
        }
        updateDirection(Board);
        for (Body temp = head.next; temp != null; temp = temp.next) {
            Board[temp.row][temp.col] = "o";
        }
        for(int i = 0; i < Board.length; i++){
            for(int j = 0; j < Board[0].length; j++){
                output+= Board[i][j]+" ";
            } 
            output += "\n"; 
        }

        return output;
    }
}
