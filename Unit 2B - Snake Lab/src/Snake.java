public class Snake {
    int dir = 3;
    Body head;          // Head of the snake
    boolean alive;      // Flag to check if the snake is alive
    boolean growNextMove;

    // Default constructor
    public Snake() {
        alive = true;
        head = new Body();
        growNextMove = false;
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
     if (!alive) {
        return false; // Prevent any further updates if the snake is dead
    }
        switch (c) {
            case 'U':
                if (dir != 3) { // Prevent going North if currently facing South
                    dir = 1; // Change direction to North
                }
                break;
            case 'D':
                if (dir != 1) { // Prevent going South if currently facing North
                    dir = 3; // Change direction to South
                }
                break;
            case 'L':
                if (dir != 0) { // Prevent going West if currently facing East
                    dir = 2; // Change direction to West
                }
                break;
            case 'R':
                if (dir != 2) { // Prevent going East if currently facing West
                    dir = 0; // Change direction to East
                }
                break;
            case 'M':
                moveSnake(); // Move the snake and return its alive state
                if (!alive) {
                    System.out.println("DEAD"); // Print when dead
                }
                break;
            case 'E':
                // Find the midpoint of the snake
                int midpoint = this.getSize() / 2;
                Body current = head;
                
                // Traverse to the midpoint
                for (int i = 0; i < midpoint - 1; i++) {
                    current = current.next;
                }
                
                // Cut off the second half of the snake
                if (current != null) {
                    current.next = null;
                }
                break;
            case 'F':
                growNextMove = true;
                break;
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
                head.row++;
                break;
            case 2: // West
                head.col--;
                break;
        }
    
        // Check for wall collisions (assuming a 10x10 grid)
        if (head.row < 0 || head.row >= 10 || head.col < 0 || head.col >= 10) {
            alive = false; // The snake has hit a wall
            return; // Stop further processing if a wall collision is detected
        }
    
        // Check for self-collision
        Body current = head.next;
        while (current != null) {
            if (current.row == head.row && current.col == head.col) {
                alive = false; // The snake has run into itself
                return; // Stop further processing if a collision is detected
            }
            current = current.next;
        }
    
        // Move the last body segment to the old head position
        Body runner = head;
        while (runner.next != null) {
            runner = runner.next;
        }
    
        // Move the last segment to the old head position
        runner.row = oldHeadRow;
        runner.col = oldHeadCol;
        runner.next = head.next;
    
        // If growNextMove is true, add a new segment at the neck
        if (growNextMove) {
            // Create a new segment at the old head position
            Body newSegment = new Body(oldHeadRow, oldHeadCol, head.next);
            head.next = newSegment; // Insert new segment directly after head
            growNextMove = false; // Reset the grow flag
        } else {
            head.next = runner; // Move the runner to the front
        }
    
        // Update the next reference of the new last segment
        Body runner2 = runner;
        while (runner2.next != runner) {
            runner2 = runner2.next;
        }
        runner2.next = null; // Remove the last segment's reference to avoid cycles
    }
    
    
    
    // Get the size of the snake (including the head)
    public int getSize() {
        int count = 0;
        Body temp = head; 
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    // Check if the snake is alive
    public boolean isAlive() {
        return alive;
    }

    public void reverse() {
        // Reverse the direction
        switch (dir) {
            case 0: dir = 2; break; // West to East
            case 1: dir = 3; break; // North to South
            case 2: dir = 0; break; // East to West
            case 3: dir = 1; break; // South to North
        }
    
        // Reverse the linked list, including the head
        Body prev = null;
        Body current = head;
        Body next;
    
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
    
        // Update the head to be the last body segment, which is now at 'prev'
        head = prev;
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
        if(alive == false){
            return "DEAD";
        }
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
