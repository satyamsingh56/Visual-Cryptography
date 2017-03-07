import java.awt.Component;//A component is an object having a graphical representation that can be displayed on the screen and that can interact with the user.
import java.awt.Container;//Components added to a container are tracked in a list.
import java.awt.Dimension;//The Dimension class encapsulates the width and height of a component (in integer precision) in a single object. The class is associated with certain properties of components. 
import java.awt.FocusTraversalPolicy;//A FocusTraversalPolicy defines the order in which Components with a particular focus cycle root are traversed. 
import java.awt.event.ActionEvent;//A semantic event which indicates that a component-defined action occurred.
import java.awt.event.ActionListener;//The listener interface for receiving action events.
import java.awt.image.BufferedImage;//The BufferedImage subclass describes an Image with an accessible buffer of image data. 
import java.io.File;//The attached Javadoc could not be retrieved as the specified Javadoc location is either wrong or currently not accessible.
import java.io.IOException;//This class is the general class of exceptions produced by failed or interrupted I/O operations.
import java.text.NumberFormat;//NumberFormat is the abstract base class for all number formats. 

import javax.imageio.ImageIO;//A class containing static convenience methods for locating ImageReaders and ImageWriters, and performing simple encoding and decoding.
import javax.swing.BorderFactory;//Factory class for vending standard Border objects.
import javax.swing.Box;//A lightweight container that uses a BoxLayout object as its layout manager.
import javax.swing.BoxLayout;//A layout manager that allows multiple components to be laid out either vertically or horizontally. 
import javax.swing.ImageIcon;//An implementation of the Icon interface that paints Icons from Images. Images that are created from a URL, filename or byte array are preloaded using MediaTracker to monitor the loaded state of the image. 
import javax.swing.JButton;//An implementation of a "push" button. 
import javax.swing.JFileChooser;//JFileChooser provides a simple mechanism for the user to choose a file.
import javax.swing.JFormattedTextField;//JFormattedTextField extends JTextField adding support for formatting arbitrary values, as well as retrieving a particular object once the user has edited the text.
import javax.swing.JFrame;//An extended version of java.awt.Frame that adds support for the JFC/Swing component architecture. 
import javax.swing.JLabel;//A display area for a short text string or an image, or both.
import javax.swing.JOptionPane;//JOptionPane makes it easy to pop up a standard dialog box that prompts users for a value or informs them of something.
import javax.swing.JPanel;//is a generic lightweight container
import javax.swing.JScrollPane;//Provides a scrollable view of a lightweight component.
import javax.swing.filechooser.FileFilter;//FileFilter is an abstract class used by JFileChooser for filtering the set of files shown to the user.


public class KeyGenFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnlAll = new JPanel();//Create new panel
	private JPanel pnlRes = new JPanel();//Create new panel
	
	private JLabel lblDescr = new JLabel("<html>The entered resolution below is the largest resolution an image to be encrypted with this key can have " +
			"(The generated key will be twice as large).</html>");
	private JLabel lblWidth = new JLabel("Width:");//create new button named width
	private JLabel lblHeight = new JLabel("Height:");//create new button named height
	private JLabel lblImg = new JLabel(new ImageIcon(), JLabel.CENTER);
	private JFormattedTextField tfWidth = new JFormattedTextField(NumberFormat.getIntegerInstance());//create new text field
	private JFormattedTextField tfHeight = new JFormattedTextField(NumberFormat.getIntegerInstance());//create new text field
	private JButton btnGenerate = new JButton("Generate Key");//create new button name generate key
	private JButton btnSave = new JButton("Save key to file");//create new button name generate key
	private JScrollPane scrImage = new JScrollPane(lblImg);

	private BufferedImage imgKey = null;
	JFileChooser fileChooser = new JFileChooser();
	
	public KeyGenFrame(JFrame parent) {
		// size
		tfWidth.setMaximumSize(new Dimension(tfWidth.getMaximumSize().width, tfWidth.getPreferredSize().height));//set max size for buttton
		tfHeight.setMaximumSize(new Dimension(tfWidth.getMaximumSize().width, tfWidth.getPreferredSize().height));//set max size for buttton
		
		// orientation
		lblDescr.setAlignmentX(LEFT_ALIGNMENT);//set left alignment
		pnlRes.setAlignmentX(LEFT_ALIGNMENT);//set left alignment
		scrImage.setAlignmentX(LEFT_ALIGNMENT);//set left alingment of screen imag
		btnSave.setAlignmentX(LEFT_ALIGNMENT);//set left alingment of save button
		
		// action listener
		btnGenerate.addActionListener(this);//add action listener to generate button
		btnSave.addActionListener(this);//add action listener to save button
		tfWidth.addActionListener(this);//add action listener to width button
		tfHeight.addActionListener(this);//add action listener to height button
		
		// default value
		tfWidth.setText("200");
		tfHeight.setText("200");
		btnSave.setEnabled(false);
		
		fileChooser.setDialogTitle("Save as..");
		fileChooser.setFileFilter(new FileFilter() {
			public boolean accept(File arg0) {
				if (arg0.isDirectory()) return true;
				if (arg0.getName().endsWith(".png")) return true;
				return false;
			}

			public String getDescription() {
				return "Image (*.png)";
			}
		});
		
		pnlRes.setLayout(new BoxLayout(pnlRes, BoxLayout.X_AXIS));
		pnlRes.add(lblWidth);
		pnlRes.add(Box.createRigidArea(new Dimension(10, 0)));
		pnlRes.add(tfWidth);
		pnlRes.add(Box.createRigidArea(new Dimension(10, 0)));
		pnlRes.add(lblHeight);
		pnlRes.add(Box.createRigidArea(new Dimension(10, 0)));
		pnlRes.add(tfHeight);
		pnlRes.add(Box.createRigidArea(new Dimension(10, 0)));
		pnlRes.add(btnGenerate);
		
		pnlAll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pnlAll.setLayout(new BoxLayout(pnlAll, BoxLayout.Y_AXIS));
		pnlAll.add(lblDescr);
		pnlAll.add(pnlRes);
		pnlAll.add(Box.createVerticalStrut(10));
		pnlAll.add(scrImage);
		pnlAll.add(btnSave);
		
		setFocusTraversalPolicy(new MyFocusTraversalPolicy());
		
		add(pnlAll);
		setSize(500, 500);
		setMinimumSize(new Dimension(384, 253));
		setLocationRelativeTo(parent);
		setTitle("Visual Cryptography - Generate Key");
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(btnGenerate.getText())) {
			imgKey = Crypting.generateKey(Integer.parseInt(tfWidth.getText()), Integer.parseInt(tfHeight.getText()));
			lblImg.setIcon(new ImageIcon(imgKey));
			btnSave.setEnabled(true);
		} else if (e.getActionCommand().equals(btnSave.getText())) {
			if (imgKey == null) return;
			fileChooser.setSelectedFile(new File(""));
		    if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
		    	File f = fileChooser.getSelectedFile();
		    	if (!f.toString().endsWith(".png")) {
		    		f = new File(f.toString() + ".png");
		    	}
		    	try {
					ImageIO.write(imgKey, "png", f);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(this, "Could not Save file because: " + e1.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
		    }
		} else {
			// tfWidth or tfHeight
			btnGenerate.doClick();
		}
	}

	class MyFocusTraversalPolicy extends FocusTraversalPolicy {
	    public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
	        if(aComponent.equals(tfWidth)) return tfHeight;
	        else if(aComponent.equals(tfHeight)) return btnGenerate;
	        else if(aComponent.equals(btnGenerate) && btnSave.isEnabled()) return btnSave;
	        return tfWidth;
	    }
	   
	    public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
	        if(aComponent.equals(tfWidth) && btnSave.isEnabled()) return btnSave;
	        else if(aComponent.equals(tfHeight)) return tfWidth;
	        else if(aComponent.equals(btnGenerate)) return tfHeight;
	        return btnGenerate;
	    }
	    
	    public Component getDefaultComponent(Container focusCycleRoot) {
	        return tfWidth;
	    }
	   
	    public Component getFirstComponent(Container focusCycleRoot) {
	        return tfWidth;
	    }
	   
	    public Component getLastComponent(Container focusCycleRoot) {
	        return btnSave;
	    }
	}
	
}