public class Body {
    int row, col;       // Coordinates for the body piece
    Body next;          // Reference to the next body piece

    // Constructor
    public Body(){
        this.row = 3; 
        this.col = 3; 
        Body runner = this;
        for(int i = 0; i < 3; i++){
            runner.next = new Body(3-i,3,null);
            runner = runner.next;
        }
    }

    public Body(int row, int col, Body next) {
        this.row = row;
        this.col = col;
        this.next = next;
    }
}