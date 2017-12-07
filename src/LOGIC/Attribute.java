package LOGIC;

/**
 * Represent attribute components
 */
public class Attribute extends Component {

    private boolean isConstant;

    public Attribute(){
    }

    public Attribute(String name, String type, String[] modifiers){
        this.name = name;
        this.type = type;

        this.accessModifier = "public";
        this.isStatic = false;
        this.isConstant = false;

        for(String st : modifiers) {
            if (st.equals("public") || st.equals("private") || st.equals("protected"))
                this.accessModifier = st;
            if (st.equals("static"))
                this.isStatic = true;
            if (st.equals("final"))
                this.isConstant = true;
        }

    }


    /**
     * Check if attribute is constant or not
     * @return isConstant
     */
    public boolean isConstant() {
        return isConstant;
    }

    /**
     * Set attribute value to constant
     * @param constant boolean
     */
    public void setConstant(boolean constant) {
        isConstant = constant;
    }

    /**
     * Represent attribute
     * @return string which describes attribute
     */
    @Override
    public String toString() {
        return this.getAccessModifier() + " " + this.getType() + ": "  + this.getName();
    }

    /**
     * Comparing 2 attributes
     * If 2 attributes have the same name, then they are equal
     * @param attribute components
     * @return true if 2 attributes are equal
     */
    @Override
    public boolean equalsTo(Component attr) {
        if (attr == this)
            return true;
        if (attr.getClass() != this.getClass() || attr == null)
            return false;
        return (this.getName() == attr.getName());
    }
}