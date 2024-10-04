public class Snake {
    int dir;            // Direction of the snake (0 East, 1 North, 2 South, 3 West)
    Body head;          // Head of the snake
    boolean alive;      // Flag to check if the snake is alive
    String[][] Board = new String[10][10];

    // Default constructor
    public Snake() {
        for(int i = 0; i < Board.length; i++){
            for(int j = 0; j < Board.length; j++){
                Board[i][j] = "=";
            }
        }
        head = new Body();
        Board[head.next.row][head.next.col] = "v";
        head = head.next;
        while(head.next != null){
            Board[head.next.row][head.next.col] = "o";
            head = head.next;
        }
        
    }

    // Constructor with parameters
    public Snake(int dir, int row, int col, Body p) {
        this.dir = dir;
        this.head = p;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                Board[i][j] = "#";
            }
        }
        
    }

    // Update the snake based on a series of commands
    public boolean update(String commands) {
        // Process commands
        return false;
    }

    // Update the snake based on a single command
    public boolean update(char c) {
        // Process a single command
        return false;
    }

    // Get the size of the snake (including the head)
    public int getSize() {
        return 0;
    }

    // Check if the snake is alive
    public boolean isAlive() {
        return false;
    }

    // Reverse the snake (tail becomes head and direction is opposite)
    public void reverse() {
        // Reverse the snake
    }

    // Draw the snake in a 2D board, or draw the final state if the snake is dead
    public String toString() {
        String output = "";
        for(int i = 0; i < Board.length; i++){
            for(int j = 0; j < Board[0].length; j++){
                output += Board[i][j] + " ";
            }
            output += "\n"; 
        }
        return output;
    }
}
