package GUI;
import LOGIC.Structure;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
/**
 * 
 * @author Minh Hoang
 *
 */
public class Shape {

	final static int CharSize = 6;
	final static int height = 20;
	final static int TextHeight = 20;
	int AttributesLines = 0;// number of attribute line
	int MethodsLines = 0;// number of method line
	volatile int draggedAtX, draggedAtY;
	public Structure structure;

	private JTextArea ClassName = new JTextArea();
	private JTextArea Attribute = new JTextArea();
	private JTextArea Method = new JTextArea();

	private int level = 0;// class have no parents has level 0

	private int LowerBound = 0;
	private int UpperBound = 0;
	private int width = 0;
	private int max = 0;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Information of each diagram that need to print
	 * 
	 * @param st
	 *            structure of a java class
	 */
	public Shape(Structure st) {
		this.structure = st;
		ClassName.setText(structure.getName());
		// get location of shape when click
		ClassName.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				draggedAtX = e.getX();
				draggedAtY = e.getY();
			}
		});

		Attribute.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				draggedAtX = e.getX();
				draggedAtY = e.getY();
			}
		});

		Method.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				draggedAtX = e.getX();
				draggedAtY = e.getY();
			}
		});
		// set shape's location when mouse's moving event happening after clicked on
		// className field
		ClassName.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				ClassName.setLocation(e.getX() - draggedAtX + ClassName.getLocation().x,
						e.getY() - draggedAtY + ClassName.getLocation().y);
				Attribute.setLocation(e.getX() - draggedAtX + Attribute.getLocation().x,
						e.getY() - draggedAtY + Attribute.getLocation().y);
				Method.setLocation(e.getX() - draggedAtX + Method.getLocation().x,
						e.getY() - draggedAtY + Method.getLocation().y);
				System.out.println(ClassName.getX());
				Gui.diagram.DrawLink(Gui.panel);
				Gui.frame.revalidate();
				Gui.frame.repaint();

			}
		});
		// set shape's location when mouse's moving event happening after clicked on
		// attribute field
		Attribute.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				ClassName.setLocation(e.getX() - draggedAtX + ClassName.getLocation().x,
						e.getY() - draggedAtY + ClassName.getLocation().y);
				Attribute.setLocation(e.getX() - draggedAtX + Attribute.getLocation().x,
						e.getY() - draggedAtY + Attribute.getLocation().y);
				Method.setLocation(e.getX() - draggedAtX + Method.getLocation().x,
						e.getY() - draggedAtY + Method.getLocation().y);
				Gui.diagram.DrawLink(Gui.panel);
				Gui.frame.revalidate();
				Gui.frame.repaint();
			}
		});
		// set shape's location when mouse's moving event happening after clicked on
		// methods field
		Method.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				ClassName.setLocation(e.getX() - draggedAtX + ClassName.getLocation().x,
						e.getY() - draggedAtY + ClassName.getLocation().y);
				Attribute.setLocation(e.getX() - draggedAtX + Attribute.getLocation().x,
						e.getY() - draggedAtY + Attribute.getLocation().y);
				Method.setLocation(e.getX() - draggedAtX + Method.getLocation().x,
						e.getY() - draggedAtY + Method.getLocation().y);
				Gui.diagram.DrawLink(Gui.panel);
				Gui.frame.revalidate();
				Gui.frame.repaint();
			}
		});
	}

	/**
	 * check if a extends from another class
	 * 
	 * @param a
	 *            shape need to check
	 * @return true if it has parent else return failse
	 */
	public boolean isInheritedFrom(Shape a) {
		for (Structure st : structure.getSuperStructures()) {
			if (st.equalsTo(a.structure))
				return true;
		}
		return false;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public JTextArea getClassName() {
		return ClassName;
	}

	public JTextArea getAttribute() {
		return Attribute;
	}

	public JTextArea getMethod() {
		return Method;
	}

	public int getLowerBound() {
		return LowerBound;
	}

	public void setLowerBound(int lowerBound) {
		LowerBound = lowerBound;
	}

	public int getUpperBound() {
		return ClassName.getY();
	}

	public int getWidth() {
		return ClassName.getX();
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setUpperBound(int upperBound) {
		UpperBound = upperBound;
	}

	/**
	 * add all infomation of class to shape
	 */
	public void getInfo() {
		int classNameLength = ClassName.getText().length();
		int attributeLength = 0;
		// get the length of attributes
		for (int i = 0; i < structure.getAttributes().size() - 1; i++) {
			if (structure.getAttributes().get(i + 1).toString().length() >= structure.getAttributes().get(i).toString()
					.length()) {
				attributeLength = structure.getAttributes().get(i + 1).toString().length();
			} else {
				attributeLength = structure.getAttributes().get(i).toString().length();
			}
		}
		// get the length of methods
		int methodLength = structure.getMethods().get(0).toString().length();
		for (int i = 0; i < structure.getMethods().size(); i++) {
			if (methodLength < structure.getMethods().get(i).toString().length()) {
				methodLength = structure.getMethods().get(i).toString().length();
			}
		}
		classNameLength = classNameLength * CharSize;
		attributeLength = attributeLength * CharSize;
		methodLength = methodLength * CharSize;
		int max = 0;// the max length of a line in a diagram
		String newAttribute = new String();
		String newMethod = new String();
		if (classNameLength >= attributeLength && classNameLength >= methodLength)
			max = classNameLength;
		if (attributeLength >= classNameLength && attributeLength >= methodLength)
			max = attributeLength;
		if (methodLength >= classNameLength && methodLength >= attributeLength)
			max = methodLength;
		setMax(max);
		for (int i = 0; i < structure.getAttributes().size(); i++) {
			if (i > 0) {
				newAttribute = newAttribute + "\n" + structure.getAttributes().get(i).toString();
			} else {
				newAttribute = structure.getAttributes().get(i).toString();
			}
			AttributesLines++;
		}
		Attribute.setText(newAttribute);
		for (int i = 0; i < structure.getMethods().size(); i++) {
			if (i > 0) {
				newMethod = newMethod + "\n" + structure.getMethods().get(i).toString();
			} else {
				newMethod = structure.getMethods().get(i).toString();
			}
			MethodsLines++;
		}
		Method.setText(newMethod);
	}

	/**
	 * 
	 * method draw out the diagram to the panel
	 * 
	 * @param panel
	 *            main panel to draw
	 */
	public void DrawContent(JPanel panel) {
		Font font = new Font("Times New Roman", Font.BOLD, 11);// font style for all text content in diagram
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		ClassName.setBounds(width, UpperBound, max, height + 20);
		ClassName.setBorder(border);
		ClassName.setFont(font);

		panel.add(ClassName);// add className to panel

		Attribute.setDragEnabled(true);
		Attribute.setBounds(width, ClassName.getY() + ClassName.getHeight(), max, TextHeight * AttributesLines);
		Attribute.setBorder(border);

		Attribute.setFont(font);
		panel.add(Attribute);// add attribute to panel

		Method.setDragEnabled(true);
		Method.setBounds(width, Attribute.getY() + Attribute.getHeight(), max, TextHeight * MethodsLines);
		Method.setBorder(border);
		Method.setFont(font);
		panel.add(Method);// add method to panel

		setLowerBound(ClassName.getHeight() + Attribute.getHeight() + Method.getHeight() + 10);
	}
}
