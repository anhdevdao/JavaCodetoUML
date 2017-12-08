package LOGIC;

 // Abstract Class represents component of a Class or Interface
 // A component must be in 2 types : Method or Attribute
 // They must have names, types and access modifier
 // Some components in a class or interface are static
 
abstract public class Component {
    protected String name;
    protected String type;
    protected String accessModifier;
    protected boolean isStatic;
    protected Structure owner;
    // Default Constructor
     
    public Component() {
    }

    // Represents this component
    // return a string represents this component
     
    abstract public String toString();

    // Compares 2 Components
    // param o Components
    // return true if they are equal
     
    abstract public boolean equalsTo(Component o);

    // Gets name of the component
    // return name
    
    public String getName() {
        return name;
    }

    // Sets name for the component
    // param name String
     
    public void setName(String name) {
        this.name = name;
    }

    // Gets type for the component
    // return type
     
    public String getType() {
        return type;
    }

    // Sets type for the component
    // param type String
     
    public void setType(String type) {
        this.type = type;
    }

    // Gets access modifier for the component
    // return accessModifier
    
    public String getAccessModifier() {
        return accessModifier;
    }

    // Sets access modifier for the component
    // param accessModifier AccessModifier
     
    public void setAccessModifier(String accessModifier) {
        this.accessModifier = accessModifier;
    }

    // Checks if the component is static or not
    // return isStatic
     
    public boolean isStatic() {
        return isStatic;
    }

    // Sets the static attribute for the component
    // param aStatic boolean
    
    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public void setOwner(Structure str){
        this.owner = str;
    }

    public Structure getOwner() {
        return owner;
    }
}