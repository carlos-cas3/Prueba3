package entities;

public class Person {
    private String name;
    private String nroDoc;
    public Person() { }
    public Person(String name, String nroDoc) {
        this.name = name;
        this.nroDoc = nroDoc;
    }
    public String getNombre() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNroDoc() {
        return nroDoc;
    }
    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", nroDoc='" + nroDoc + '\'' +
                '}';
    }
    
}
