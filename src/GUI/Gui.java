package GUI;

import LOGIC.Structure;
import LOGIC.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 
 * @author Minh Hoang
 *
 */
public class Gui {

	public static JFrame frame;

	public static final int WindowWidth = 1500;
	public static final int WindowHeight = 1000;
	Project pj;
	ArrayList<Shape> shapes = new ArrayList<>();
	public static int shapeCount = 0;
	public static JPanel panel = new JPanel();
	public static JScrollPane scroll = new JScrollPane();
	public static DiagramForm diagram = new DiagramForm();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					// ZoomPane zoom= new ZoomPane();

					// try {
					// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					// } catch (ClassNotFoundException | InstantiationException |
					// IllegalAccessException | UnsupportedLookAndFeelException ex) {
					// }
					//
					// JFrame frame = new JFrame("Testing");
					// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					// frame.setLayout(new BorderLayout());
					// frame.add(new JScrollPane(window));
					// frame.pack();
					// frame.setLocationRelativeTo(null);
					// frame.setVisible(true);

					// JScrollPane scrollPane = new JScrollPane(panel);
					// scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					// scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
					// scrollPane.setBounds(0, 0, WindowWidth, WindowHeight);
					// JPanel contentPane = new JPanel(null);
					// contentPane.setPreferredSize(new Dimension(500, 400));
					// contentPane.add(scrollPane);
					// frame.setContentPane(contentPane);
					// frame.pack();
					// frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					// frame.setVisible(true);
					// Dimension dimension = new Dimension(WindowWidth,WindowHeight);
					// frame.setMinimumSize(dimension);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// set main window
		frame = new JFrame();
		frame.setBounds(100, 100, WindowWidth, WindowHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// scroll = new
		// JScrollPane(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// scroll.setBounds(100, 100, WindowWidth, WindowHeight);

		// JScrollPane Scroll= new
		// JScrollPane(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// JScrollPane Scroll= new JScrollPane(frame);
		// Scroll.setBounds(10, 10, WindowWidth, WindowHeight);
		// frame.add(Scroll);

		// JScrollPane scroll = new JScrollPane(frame);
		//
		// scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		// scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//
		// frame.getContentPane().add(scroll);

		// set menu option
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JButton btnOpen = new JButton("Open Project");

		mnFile.add(btnOpen);

		JButton btnClose = new JButton("Close Project");

		mnFile.add(btnClose);

		// JMenu mnExport = new JMenu("Export");
		// menuBar.add(mnExport);
		//
		// JButton btnExportText = new JButton("Export Text");
		// mnExport.add(btnExportText);
		//
		// JButton btnEportImage = new JButton("Export Image");
		// mnExport.add(btnEportImage);
		//
		// JMenu mnSearch = new JMenu("Search");
		// menuBar.add(mnSearch);
		//
		// JButton searchClass = new JButton("Search Class");
		// mnSearch.add(searchClass);
		//
		// JButton searchAttribute = new JButton("Search Attribute");
		// mnSearch.add(searchAttribute);
		//
		// JButton searchMethod = new JButton("Search Method");
		// mnSearch.add(searchMethod);

		frame.getContentPane().add(panel, BorderLayout.CENTER);

		JPanel panel2 = new JPanel();
		frame.getContentPane().add(panel2, BorderLayout.WEST);

		String path = new String();
		panel.setLayout(null);
		btnOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DrawOutWhenOpenFile();
				frame.pack();
				frame.setMinimumSize(new Dimension(900, 900));
			}
		});

		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapes.removeAll(shapes);
				diagram.removeall();
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
			}
		});
		//
		//// btnEportImage.addActionListener(new ActionListener() {
		//// @Override
		//// public void actionPerformed(ActionEvent e) {
		//// export ex = new export();
		//// ex.ToImage();
		//// }
		// });

		// searchClass.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// Search searchClass = new Search();
		// searchClass.SearchClass(shapes);
		// }
		// });
		//
		// searchAttribute.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// Search search = new Search();
		// search.SearchClass(shapes);
		// }
		// });
		//
		// searchMethod.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// Search search = new Search();
		// search.SearchMethod(shapes);
		// }
		// });

	}

	public void createShapes() {
		int count = 0;
		for (Structure structure : pj.getAllStructures()) {
			shapes.add(new Shape(structure));
			shapes.get(count).getInfo();
			count++;
		}

	}

	/**
	 * add shape into diagram and add diagram into panel
	 * 
	 * @param panel
	 *            main panel
	 * @param diagram
	 *            diagram need to add infomation
	 */
	public void Draw(JPanel panel, DiagramForm diagram) {
		for (int i = 0; i < shapes.size(); i++) {
			diagram.addShape(shapes.get(i));
		}
		// check all if there is any class is inherited
		for (int i = 0; i < shapes.size(); i++) {
			for (int j = 0; j < shapes.size(); j++) {
				if ((shapes.get(j).isInheritedFrom(shapes.get(i)) && (i != j))) {

					int level = shapes.get(i).getLevel() + 1;
					shapes.get(i).setLevel(level);
				}
			}
		}
		diagram.DrawDiagram(panel);
		diagram.DrawLink(panel);

	}

	/**
	 * Draw Out Diagrams with when open file action happen
	 */
	public void DrawOutWhenOpenFile() {
		try {
			OpenFile Open = new OpenFile();
			Open.PickDirectory();
			pj = new Project(Open.getDirectoryPath());

			createShapes();
			Draw(panel, diagram);
		} catch (Exception e3) {
			e3.printStackTrace();
		}

	}
	
//	public DiagramForm() {
//		Gui.panel.addMouseWheelListener(new MouseWheelListener)() {
//			
//		}
//	}
}
