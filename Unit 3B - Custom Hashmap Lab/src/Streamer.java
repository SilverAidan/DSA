public class Streamer {
    private String name;
    
    public Streamer(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) { 
        Streamer other = (Streamer) obj;
        return name.equals(other.name); 
}


    @Override
    public String toString() {
        return "" + name;
    }
    
}
