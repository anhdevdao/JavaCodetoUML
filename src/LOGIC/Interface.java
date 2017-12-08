package LOGIC;

import java.util.ArrayList;

 //Represents Interface structure
 
public class Interface extends Structure {

    // Default Constructor
     
    public Interface() {
        super();
    }

    public Interface(String name, String[] modifiers) {

        // Default values 
        this.name = name;
        this.accessModifier = "public";
        this.level = 0;
        this.isInterface = true;

        for(String st : modifiers) {
            if (st.equals("public") || st.equals("private") || st.equals("protected"))
                this.accessModifier = st;
        }

        methods = new ArrayList<>();
        attributes = new ArrayList<>();
        superStructures = new ArrayList<>();
        derivedStructures = new ArrayList<>();
        superStructureNames = new ArrayList<>();
    }

    // Represents Interface
    // return a string that represents Interface
     
    @Override
    public String toString() {
        return this.getAccessModifier() + " interface : " + this.getName();
    }
}