package LOGIC;

import java.util.ArrayList;

/**
 * @author Nguyen Duong
 * Represents Method component
 * Each method has name, type, access modifier and params
 */
public class Method extends Component {
    private boolean isConstructor;
    private ArrayList<Param> params;
    private String sourceCode;

    /**
     * Default Constructor
     */
    public Method() {
        params = new ArrayList<>();
    }

    /**
     * Constructor with 2 params
     * @param name String
     */
    public Method(String name, String type, String[] modifiers) {
        this.name = name;
        this.type = type;

        /* Default value */
        this.accessModifier = "public";
        this.isStatic = false;

        /* Checks the method is a constructor or not*/
        if (type.equals(name))
            this.isConstructor = true;
        else
            this.isConstructor = false;

        for(String st : modifiers) {
            if (st.equals("public") || st.equals("private") || st.equals("protected"))
                this.accessModifier = st;
            if (st.equals("static"))
                this.isStatic = true;
        }

        params = new ArrayList<>();
    }


    /**
     * Adds a parameter to the parameterlist
     * @param param Parameter
     */
    public void addParam(Param param) {
        params.add(param);
    }

    /**
     * Represents method
     * @return a string represents method
     */
    @Override
    public String toString() {
        String paramString = "(";
        for (Param p : this.params)
            paramString = paramString + p.toString() + ",";
        if (this.params.size() != 0)
            paramString = paramString.substring(0, paramString.length() - 2);
        if (!isConstructor)
            return this.getType() + ": " + this.getName() + paramString + ")";
        else
            return this.getName() + paramString + ")";

    }

    /**
     * Gets the parameter list of the method
     * @return params
     */
    public ArrayList<Param> getParams() {
        return params;
    }

    public String getSourceCode(){
        return this.sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    /**
     * Compares 2 methods
     * Equal if they have the same: return type, name, parameter list has the same types
     * @param m Component
     * @return true if equal
     */
    @Override
    public boolean equalsTo(Component m) {
        if (m == this)
            return true;
        if (m.getClass() != this.getClass() || m == null)
            return false;
        Method method = (Method) m;
        if (this.getName() != method.getName())
            return false;
        if (this.getType() != method.getType())
            return false;
        if (this.getAccessModifier() != method.getAccessModifier())
            return false;
        if (this.isStatic() != method.isStatic())
            return false;
        if (this.params.size() != method.params.size())
            return false;
        for (int i = 0; i < this.params.size(); i++)
            if (this.params.get(i).getType() != method.params.get(i).getType())
                return false;
        return true;
    }
}