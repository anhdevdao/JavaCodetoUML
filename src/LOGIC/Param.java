package LOGIC;

// Represents parameter
// A parameter has name and type
 
public class Param {
    private String name;
    private String type;

    // Default constructor
    
    public Param() {
    }

    // Constructor with 2 parameters
    // param name String
    // param type String
     
    public Param(String name, String type) {

        this.name = name;
        this.type = type;
    }

    // Gets the name of the param
    // return name
     
    public String getName() {
        return name;
    }

    // Sets the name of the param
    // param name String
    
    public void setName(String name) {
        this.name = name;
    }

    // Gets the type of the param
    // return type
     
    public String getType() {
        return type;
    }

    // Sets the type of the param
    // param type String
     
    public void setType(String type) {
        this.type = type;
    }

    // Represents the param
    // return a string represents the param
     
    public String toString(){
        return this.type + " ";
    }
}