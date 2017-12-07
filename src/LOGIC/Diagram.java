package LOGIC;

/**
 * @author Nguyen Duong
 * 
 * Presents the Diagram class
 */

import java.util.*;

public class Diagram {
    public final List<String> BASIC_DATA_TYPES = Arrays.asList("int", "byte", "short", "long", "float", "double", "boolean", "char");

    private ArrayList<Structure> structures;

    public Diagram(){
        structures = new ArrayList<>();
    }

    public void add(Structure st){
        structures.add(st);
    }

    public ArrayList<Structure> getStructures() {
        return structures;
    }

    public void reArrangeStructures(){
        for (int i = 0; i < structures.size(); i++){
            Structure structure = structures.get(i);
            for (int j = 0; j < structures.size(); j++){
                Structure structure1 = structures.get(j);
                for (String name : structure.getSuperStructureNames()) {
                    if (name.equals(structure1.getName())) {
                        structure.inherit(structure1);
                        break;
                    }
                }
             }
        }
    }

    public void getLevelForStructures(){
        for (Structure str : structures)
            if (str.getSuperStructures().size() == 0 ){
                dfs(str);
            }
        sortStructuresByLevel();
    }

    private void dfs(Structure st){
        if (st.isInterface() == false)
            for (Structure subst : st.getDerivedStructure() ) {
                subst.setLevel(st.getLevel()+1);
                dfs(subst);
            }
        else
            for (Structure subst : st.getDerivedStructure() ){
                if (subst.isInterface())
                    subst.setLevel(st.getLevel()+1);
                dfs(subst);
            }
    }
    private void sortStructuresByLevel(){
        for(int i = 0; i < structures.size() - 1; i++)
            for (int j = i + 1; j < structures.size(); j++)
                if (structures.get(i).getLevel() > structures.get(j).getLevel()){
                    Structure s = structures.get(i);
                    structures.set(i, structures.get(j));
                    structures.set(j, s);
                }
    }
}