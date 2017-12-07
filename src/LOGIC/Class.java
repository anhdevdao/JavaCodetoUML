package LOGIC;

import java.util.ArrayList;

/**
 * @author Nguyen Duong
 * Represents the structure of class
 */
public class Class extends Structure {
    private boolean isAbstract;

    /**
     * Constructor with no params
     */
    public Class(){
        super();
    }

    public Class(String name, String[] modifiers) {

        /* Default values */
        this.name = name;
        this.isAbstract = false;
        this.accessModifier = "public";
        this.level = 0;
        this.isInterface = false;

        for(String st : modifiers) {
            if (st.equals("abstract"))
                isAbstract = true;
            if (st.equals("public") || st.equals("private") || st.equals("protected"))
                this.accessModifier = st;
        }

        methods = new ArrayList<>();
        attributes = new ArrayList<>();
        superStructures = new ArrayList<>();
        derivedStructures = new ArrayList<>();
        superStructureNames = new ArrayList<>();
    }


    /**
     * Checks the class's abstract or not
     * @return true if the processing class is abstract
     */
    public boolean isAbstract() {
        return isAbstract;
    }

    /**
     * Sets the class's abstract status
     * @param anAbstract boolean
     */
    public void setAbstract(boolean anAbstract) {
        isAbstract = anAbstract;
    }

    /**
     * Represents class
     * @return a string which represents the class
     */
    @Override
    public String toString() {
        return this.getAccessModifier().toString() + " class : " + this.getName() + " ";
    }
}