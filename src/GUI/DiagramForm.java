package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Minh Hoang
 *
 */
public class DiagramForm {
	private List<Shape> shapes = new ArrayList<>(0);
	private int Spacer = 0;
	int PanelIndex[] = new int[100];
	volatile int draggedAtX, draggedAtY;
	List<JLabel> LbList = new ArrayList<>();

	public int getSpacer() {
		return Spacer;
	}

	public void setSpacer(int spacer) {
		Spacer = spacer;
	}

	/**
	 * Draw link between class is inherited
	 * 
	 * @param panel
	 *            main panel
	 */

	public void DrawLink(JPanel panel) {

		Border border = BorderFactory.createLineBorder(Color.BLACK);
		for (int i = 0; i < LbList.size(); i++) {
			panel.remove(LbList.get(i));
		}

		panel.revalidate();
		panel.repaint();
		LbList.removeAll(LbList);
		for (int i = 0; i < shapes.size(); i++) {

			for (int j = 0; j < shapes.size(); j++) {
				if ((shapes.get(j).isInheritedFrom(shapes.get(i)) && (i != j))) {

					JLabel line1 = new JLabel();
					JLabel line2 = new JLabel();
					JLabel line3 = new JLabel();
					// check distant between 2 diagram
					int ShapeHdistant = -shapes.get(i).getLowerBound() + shapes.get(j).getUpperBound()
							- shapes.get(i).getUpperBound();
					int ShapeHdistant2 = -shapes.get(j).getLowerBound() + shapes.get(i).getUpperBound()
							- shapes.get(j).getUpperBound();

					// shapes.get(i) under
					if (shapes.get(i).getUpperBound() + shapes.get(i).getLowerBound() <= shapes.get(j)
							.getUpperBound()) {
						line1.setBounds(shapes.get(i).getWidth() + shapes.get(i).getMax() / 2,
								shapes.get(i).getUpperBound() + shapes.get(i).getLowerBound(), 1, ShapeHdistant / 2);
						line1.setBorder(border);

						line2.setBounds(shapes.get(j).getWidth() + shapes.get(j).getMax() / 2,
								line1.getY() + line1.getHeight(), 1, line1.getHeight());
						line2.setBorder(border);
						if (shapes.get(i).getWidth() + shapes.get(i).getMax() / 2 < shapes.get(j).getWidth()
								+ shapes.get(j).getMax() / 2) {
							line3.setBounds(line1.getX(), line2.getY(), line2.getX() - line1.getX(), 1);
							line3.setBorder(border);
						} else {
							line3.setBounds(line2.getX(), line2.getY(), line1.getX() - line2.getX(), 1);
							line3.setBorder(border);
						}
					}

					// shapes.get(i) over
					if (shapes.get(j).getUpperBound() + shapes.get(j).getLowerBound() <= shapes.get(i)
							.getUpperBound()) {
						line1.setBounds(shapes.get(i).getWidth() + shapes.get(i).getMax() / 2,
								shapes.get(i).getUpperBound() - ShapeHdistant2 / 2, 1, ShapeHdistant2 / 2);
						line1.setBorder(border);

						line2.setBounds(shapes.get(j).getWidth() + shapes.get(j).getMax() / 2,
								shapes.get(j).getUpperBound() + shapes.get(j).getLowerBound(), 1, ShapeHdistant2 / 2);
						line2.setBorder(border);

						if (shapes.get(i).getWidth() + shapes.get(i).getMax() / 2 < shapes.get(j).getWidth()
								+ shapes.get(j).getMax() / 2) {
							line3.setBounds(line1.getX(), line1.getY(), line2.getX() - line1.getX(), 1);
							line3.setBorder(border);
						} else {
							line3.setBounds(line2.getX(), line1.getY(), line1.getX() - line2.getX(), 1);
							line3.setBorder(border);
						}
					}
					int DistantS = -shapes.get(i).getWidth() - shapes.get(i).getMax() + shapes.get(j).getWidth()
							+ shapes.get(j).getMax() / 2;
					int DistantS2 = shapes.get(i).getWidth() - shapes.get(j).getWidth() - shapes.get(j).getMax() / 2;
					if (shapes.get(i).getUpperBound() <= shapes.get(j).getUpperBound() + shapes.get(j).getLowerBound()
							&& shapes.get(i).getUpperBound() + shapes.get(i).getLowerBound() >= shapes.get(j)
									.getUpperBound()) {
						if (shapes.get(i).getWidth() + shapes.get(i).getMax() < shapes.get(j).getWidth()
								+ shapes.get(j).getMax()) {
							line1.setBounds(shapes.get(i).getWidth() + shapes.get(i).getMax(),
									shapes.get(i).getUpperBound() + shapes.get(i).getLowerBound() / 2, DistantS, 1);
							line1.setBorder(border);
							if (shapes.get(j).getUpperBound() > shapes.get(i).getUpperBound()
									+ shapes.get(i).getLowerBound() / 2
									|| shapes.get(j).getUpperBound()
											+ shapes.get(j).getLowerBound() < shapes.get(i).getUpperBound()
													+ shapes.get(i).getLowerBound() / 2) {
								if (shapes.get(i).getUpperBound() + shapes.get(i).getLowerBound() / 2 < shapes.get(j)
										.getUpperBound()) {
									line2.setBounds(line1.getX() + line1.getWidth(), line1.getY(), 1,
											shapes.get(j).getUpperBound() - line1.getY());
									line2.setBorder(border);

								} else {
									line2.setBounds(line1.getX() + line1.getWidth(),
											shapes.get(j).getUpperBound() + shapes.get(j).getLowerBound(), 1,
											-shapes.get(j).getUpperBound() - shapes.get(j).getLowerBound()
													+ line1.getY());
									line2.setBorder(border);
								}
							}
						} else {
							line1.setBounds(shapes.get(j).getWidth() + shapes.get(j).getMax() / 2,
									shapes.get(i).getUpperBound() + shapes.get(i).getLowerBound() / 2, DistantS2, 1);
							line1.setBorder(border);
							if (shapes.get(j).getUpperBound() > shapes.get(i).getUpperBound()
									+ shapes.get(i).getLowerBound() / 2
									|| shapes.get(j).getUpperBound()
											+ shapes.get(j).getLowerBound() < shapes.get(i).getUpperBound()
													+ shapes.get(i).getLowerBound() / 2) {
								if (shapes.get(i).getUpperBound() + shapes.get(i).getLowerBound() / 2 < shapes.get(j)
										.getUpperBound()) {
									line2.setBounds(line1.getX(), line1.getY(), 1,
											shapes.get(j).getUpperBound() - line1.getY());
									line2.setBorder(border);

								} else {
									line2.setBounds(line1.getX(),
											shapes.get(j).getUpperBound() + shapes.get(j).getLowerBound(), 1,
											-shapes.get(j).getUpperBound() - shapes.get(j).getLowerBound()
													+ line1.getY());
									line2.setBorder(border);
								}
							}
						}

					}

					LbList.add(line1);
					LbList.add(line2);
					LbList.add(line3);
				}
			}
		}

		for (int i = 0; i < LbList.size(); i++) {
			panel.add(LbList.get(i));
		}

	}

	public boolean isChildren(Shape shape) {
		return true;
	}

	public void addShape(Shape shape) {
		shapes.add(shape);
	}

	/**
	 * arrange shapes by level
	 * 
	 * @param panel
	 *            panel containt diagrams need to set up
	 */
	public void DrawDiagram(JPanel panel) {
		int LevelMax = 0;
		int sizeW = 0;
		int sizeH = 0;
		for (int i = 0; i < shapes.size(); i++) {
			if (shapes.get(i).getLevel() > LevelMax) {
				LevelMax = shapes.get(i).getLevel();
			}
		}

		List<Shape> shapesByLevel = new ArrayList<>();
		for (int i = LevelMax; i >= 0; i--) {
			for (int j = 0; j < shapes.size(); j++) {
				if (shapes.get(j).getLevel() == i) {
					shapesByLevel.add(shapes.get(j));

				}
			}
		}

		int maxH = -1;
		for (int j = LevelMax; j >= 0; j--) {
			for (int i = 0; i < shapesByLevel.size(); i++) {
				if (shapesByLevel.get(i).getLevel() == j) {
					;

					if (i > 0) {
						sizeW = shapesByLevel.get(i - 1).getWidth() + shapesByLevel.get(i - 1).getMax() + 30;
						if ((sizeW + shapes.get(i).getMax() > Gui.WindowWidth)
								|| (shapesByLevel.get(i - 1).getLevel() != shapesByLevel.get(i).getLevel())) {
							sizeW = 0;
							sizeH = sizeH + maxH + 30;

						}

					}
					shapesByLevel.get(i).setUpperBound(sizeH);
					shapesByLevel.get(i).setWidth(sizeW);

					shapesByLevel.get(i).DrawContent(panel);
					System.out.println(shapesByLevel.get(i).getClassName().getText()
							+ shapesByLevel.get(i).getLowerBound() + " level " + j + " W " + sizeW + " H " + sizeH);

					if (shapesByLevel.get(i).getLowerBound() > maxH) {
						maxH = shapesByLevel.get(i).getLowerBound();
					}
				}
			}
		}
	}

	/**
	 * remove all shapes
	 */
	public void removeall() {
		shapes.removeAll(shapes);
	}
}
