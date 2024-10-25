public class Address {
    String zip, street;
    int number;
    public Address(String zip, String street, int number) {
        this.zip = zip;
        this.street = street;
        this.number = number;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((zip == null) ? 0 : zip.hashCode());
        result = prime * result + ((street == null) ? 0 : street.hashCode());
        result = prime * result + number;
        return Math.abs(result);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Address other = (Address) obj;
        if (zip == null) {
            if (other.zip != null)
                return false;
        } else if (!zip.equals(other.zip))
            return false;
        if (street == null) {
            if (other.street != null)
                return false;
        } else if (!street.equals(other.street))
            return false;
        if (number != other.number)
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Address [zip=" + zip + ", street=" + street + ", number=" + number + "]";
    }
}
