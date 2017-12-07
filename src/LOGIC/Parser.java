package LOGIC;

import java.io.FileInputStream;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import java.util.ArrayList;
import java.util.EnumSet;

public class Parser {
    public static void parse(Diagram diagram, FileInputStream in){
        CompilationUnit cu = JavaParser.parse(in);
        NodeList<TypeDeclaration<?>> types = cu.getTypes();
        for (TypeDeclaration<?> type : types){
            if (type instanceof ClassOrInterfaceDeclaration) {
                ClassOrInterfaceDeclaration struc = (ClassOrInterfaceDeclaration) type;
                Structure str = null;
                String name = struc.getNameAsString();
                String[] modifiers = getModifiersAsStrings(struc.getModifiers());

                //Gets type of the structures and declares
                if (struc.isInterface())
                    str = new Interface(name, modifiers);
                else if (struc.isEnumDeclaration()) ;
                else{
                    str = new Class(name, modifiers);
                    getIherit(struc, str);
                }
                if (str != null){
                    // Parsing for methods and properties before adding to the diagram
                    parseMethodsAndProperties(struc, str);
                    str.setSourceCode(struc.toString());
                    diagram.add(str);
                }
            }
        }
    }

    private static void getIherit(ClassOrInterfaceDeclaration struc, Structure str){
        for (ClassOrInterfaceType tp : struc.getExtendedTypes())
            str.inherit(tp.getNameAsString());
        for (ClassOrInterfaceType tp : struc.getImplementedTypes())
            str.inherit(tp.getNameAsString());

    }

    private static String[] getModifiersAsStrings(EnumSet<Modifier> mdfier){
        ArrayList<String> modifiers = new ArrayList<>();
        for (Modifier m : mdfier){
            modifiers.add(m.asString());
        }
        return modifiers.toArray(new String[0]);
    }

    private static void parseMethodsAndProperties(ClassOrInterfaceDeclaration struc, Structure str){
        parseMethods(struc, str);
        parseProperties(struc, str);
        parseConstructors(struc, str);
    }

    private static void parseConstructors(ClassOrInterfaceDeclaration struc, Structure str){
        for (ConstructorDeclaration method : struc.getConstructors()){
            String name = method.getNameAsString();
            String type = struc.getNameAsString();
            String[] modifiers = getModifiersAsStrings(method.getModifiers());

            Method m = new Method(name, type, modifiers);

            for (Parameter p : method.getParameters()){
                m.addParam(new Param(p.getNameAsString(), p.getType().asString()));
            }
            m.setSourceCode(method.toString());
            str.addMethod(m);

        }
    }

    private static void parseProperties(ClassOrInterfaceDeclaration struc, Structure str){
        for(FieldDeclaration field : struc.getFields()){
            String type = field.getCommonType().asString();
            String[] modifiers = getModifiersAsStrings(field.getModifiers());

            for (VariableDeclarator var : field.getVariables()){
                String name = var.getNameAsString();
                str.addAttribute(new Attribute(name, type, modifiers));
            }
        }
    }
    
    private static void parseMethods(ClassOrInterfaceDeclaration struc , Structure str){
        for (MethodDeclaration method : struc.getMethods()){
            String name = method.getNameAsString();
            String type = method.getType().asString();

            String[] modifiers = getModifiersAsStrings(method.getModifiers());

            Method m = new Method(name, type, modifiers);
            for (Parameter p : method.getParameters()){
                m.addParam(new Param(p.getNameAsString(), p.getType().asString()));
            }
            m.setSourceCode(method.toString());
            str.addMethod(m);
        }
    }
}