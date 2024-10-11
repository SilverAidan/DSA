public class Body {
    int row, col;       // Coordinates for the body piece
    Body next;          // Reference to the next body piece

    // Constructor
    public Body(){
        this.row = 4; 
        this.col = 4; 
        Body runner = this;
        for(int i = 1; i < 3; i++){
            runner.next = new Body(4,4-i,null);
            runner = runner.next;
        }
    }

    public Body(int row, int col, Body next) {
        this.row = row;
        this.col = col;
        this.next = next;
    }
}