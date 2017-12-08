package LOGIC;

import java.util.ArrayList;

// Represents structures such as Class,...
// Each structure has name, accessModifier
// Each structure has many methods and attributes
// Each structure can come from the super class of other structure / derive from other structure
// An Interface derives from many other Interfaces
 
abstract public class Structure {
    protected String name; // Get name of the structures
    protected String accessModifier; // Get modifiers of the structures
    protected ArrayList<Method> methods; // Method of the structures
    protected ArrayList<Attribute> attributes; // Attributes of the structures
    protected ArrayList<Structure> superStructures; // Get extended structures implemented in project
    protected ArrayList<Structure> derivedStructures; // Get derived structures implemented in project
    protected ArrayList<String> superStructureNames; // Get extended structures names included other source structures
    protected int level;
    protected boolean isInterface;
    protected String sourceCode;

    // Default constructor
     
    public Structure() {
        accessModifier = "public";

        methods = new ArrayList<>();
        attributes = new ArrayList<>();
        superStructures = new ArrayList<>();
        derivedStructures = new ArrayList<>();
        superStructureNames = new ArrayList<>();
    }


    // Gets method lists of the structure
    // return methods
     
    public ArrayList<Method> getMethods() {
        return methods;
    }

    // Gets the attribute lists of structure
    // return attributes
     
    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    // Gets name of the structures
    // @return name
     
    public String getName() {
        return name;
    }

    // Sets name of the structures
    // param name String
     
    public void setName(String name) {
        this.name = name;
    }

    public String getAccessModifier() {
        return accessModifier;
    }

    public void setAccessModifier(String accessModifier) {
        this.accessModifier = accessModifier;
    }

    // Gets list of structures which is this structures inherits
    // return superStructures
     
    public ArrayList<Structure> getSuperStructures() {
        return superStructures;
    }

    // Gets list of structures which is inherit from this structures
    // return derivedStructures
     
    public ArrayList<Structure> getDerivedStructure() {
        return derivedStructures;
    }

    public int getLevel(){
        return level;
    }

    // Represents structure
    // return a string 
     
    abstract public String toString();

    // Adds method to the method list of structure
    // param m Method
     
    public void addMethod(Method m) {
        this.methods.add(m);
        m.setOwner(this);
    }

    // Adds attribute to the attribute list of structure
    // param a Attribute
     
    public void addAttribute(Attribute a) {
        this.attributes.add(a);
        a.setOwner(this);
    }

    // Adds structure to the subStructure list of that structure
    // param st Structure
     
    public void addSubStructure(Structure st) {
        this.derivedStructures.add(st);
    }

    // This structure inherits from other one
    // param st Structure
     
    public void inherit(Structure st) {
        this.superStructures.add(st);
        st.addSubStructure(this);
    }

    public void inherit(String st){
        this.superStructureNames.add(st);
    }

    public ArrayList<String> getSuperStructureNames(){
        return superStructureNames;
    }
    // Compares 2 structures. They are equal if they have the same type, same name, same package
    // param st Structure
    // return true if 2 structures are equal
     
    public boolean equalsTo(Structure st) {
        return (st.getClass() == this.getClass() && st.getName() == this.getName());
    }

    protected String showAscendant(){
        if (superStructureNames.size() == 0)
            return "";
        String t = "<-";
        for (String str : superStructureNames)
            t = t + str;
        return t;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isInterface() {
        return isInterface;
    }
}