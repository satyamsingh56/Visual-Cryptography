import java.awt.Component;
//A component is an object having a graphical representation that can be displayed on the screen and that can interact with the user.
import java.awt.Container;
//A generic Abstract Window Toolkit(AWT) container object is a component that can contain other AWT components.
import java.awt.Dimension;
//The attached Javadoc could not be retrieved as the specified Javadoc location is either wrong or currently not accessible.
import java.awt.FocusTraversalPolicy;
//A FocusTraversalPolicy defines the order in which Components with a particular focus cycle root are traversed. Instances can apply the policy to arbitrary focus cycle roots, allowing themselves to be shared across 
import java.awt.event.ActionEvent;
//The attached Javadoc could not be retrieved as the specified Javadoc location is either wrong or currently not accessible.
import java.awt.event.ActionListener;
//The listener interface for receiving action events. The class that is interested in processing an action event implements this interface, and the object created with that class is registered with a component, using the component's addActionListener method.
import java.awt.image.BufferedImage;
import java.io.File;//User interfaces and operating systems use system-dependent pathname strings to name files and directories. This class presents an abstract, system-independent view of hierarchical pathnames.
import java.io.IOException;//Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
import javax.imageio.ImageIO;//A class containing static convenience methods for locating ImageReaders and ImageWriters, and performing simple encoding and decoding.
import javax.swing.BorderFactory;//Factory class for vending standard Border objects. Wherever possible, this factory will hand out references to shared Border instances.
import javax.swing.Box;//The Box class can create several kinds of invisible components that affect layout: glue, struts, and rigid areas.
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;//An implementation of the Icon interface that paints Icons from Images.
import javax.swing.JButton;//JFileChooser provides a simple mechanism for the user to choose a file. For information about using JFileChooser
import javax.swing.JFileChooser;//JOptionPane makes it easy to pop up a standard dialog box that prompts users for a value or informs them of something.
import javax.swing.JFrame;//Provides a set of "lightweight" (all-Java language) components that, to the maximum degree possible, work the same on all platforms.
import javax.swing.JLabel;//A display area for a short text string or an image, or both. A label does not react to input events. As a result, it cannot get the keyboard focus.
import javax.swing.JOptionPane;//JOptionPane makes it easy to pop up a standard dialog box that prompts users for a value or informs them of something.
import javax.swing.JPanel;//JPanel is a generic lightweight container. For examples and task-oriented documentation for JPanel, see How to Use Panels, a section in The Java Tutorial. 
import javax.swing.JScrollPane;//Provides a scrollable view of a lightweight component. A JScrollPane manages a viewport, optional vertical and horizontal scroll bars, and optional row and column heading viewports.
import javax.swing.JTextField;//Contains classes and interfaces used by the JFileChooser component. 
import javax.swing.filechooser.FileFilter;//JPanel is a generic lightweight container. For examples and task-oriented documentation for JPanel



public class DecryptFrame extends JFrame implements ActionListener {//extends Jframe interface and implementing actionlisnter class
	private static final long serialVersionUID = 1L;//implenting Jpanel class
	private JPanel pnlAll = new JPanel();// declaring new panel named as pnlAll
	private JPanel pnlKeyFile = new JPanel();// declaring new panel named as pnlKeyFile
	private JPanel pnlEncFile = new JPanel();// declaring new panel named as pnlEncFile
	private JPanel pnlScrolls = new JPanel();// declaring new panel named as pnlScrolls
	private JPanel pnlScrollOverlay = new JPanel();// declaring new panel named as pnlScrollOverlay
	private JPanel pnlScrollClean = new JPanel();// declaring new panel named as pnlScrollClean
	//declaring new label named as lblDescr 
	private JLabel lblDescr = new JLabel("<html>Enter a key file and an encrypted image below to decrypt it. You could also decrypt it by printing the key and the encrypted image on transparent paper and overlaying them manually.</html>");
	//declaring new label named as lblOverlay
	private JLabel lblOverlay = new JLabel(new ImageIcon(), JLabel.CENTER);
	//declaring new label named as lblClean
	private JLabel lblClean = new JLabel(new ImageIcon(), JLabel.CENTER);
	//declaring new textfield named as tfkey 
	private JTextField tfKey = new JTextField();
	//declaring new textfield named as tfEncr
	private JTextField tfEncr = new JTextField();
	//declaring new button named as btnSelectKey
	private JButton btnSelectKey = new JButton("Select keyfile");
	//declaring new button named as btnSelectEncr
	private JButton btnSelectEncr = new JButton("Select encrypted image");
	//declaring new button named as  btnDecrypt
	private JButton btnDecrypt = new JButton("Decrypt");
	//declaring new button named as  btnSaveOverlay
	private JButton btnSaveOverlay = new JButton("Save overlayed image to file");
	//declaring new button named as  btnSaveClean
	private JButton btnSaveClean = new JButton("Save decrypted image to file");
	//declaring new button named as  scrOverlay
	private JScrollPane scrOverlay = new JScrollPane(lblOverlay);
	//declaring new button named as  scrClean
	private JScrollPane scrClean = new JScrollPane(lblClean);
	
	private JFileChooser fileChooser = new JFileChooser();//used to choose the file
	private BufferedImage imgOverlay = null;//The BufferedImage subclass describes an Image with an accessible buffer of image data.
	private BufferedImage imgClean = null;
	File fKeyFile = null;
	File fEncrFile = null;
	
	public DecryptFrame(JFrame parent) {
		// size
		//Sets the preferred size of this component. If preferredSize is null, the UI will be asked for the preferred size.
		tfKey.setMaximumSize(new Dimension(tfKey.getMaximumSize().width, tfKey.getPreferredSize().height));
		//If the maximum size has been set to a non-null value just returns it. If the UI delegate's getMaximumSize method returns a non-null value then return that; otherwise defer to the component's layout manager.
		tfEncr.setMaximumSize(new Dimension(tfEncr.getMaximumSize().width, tfEncr.getPreferredSize().height));
		//If the preferredSize has been set to a non-null value just returns it. If the UI delegate's getPreferredSize method returns a non null value
		//then return that; otherwise defer to the component's layout manager.
		int iButMaxWidth = (btnSelectKey.getPreferredSize().width > btnSelectEncr.getPreferredSize().width) ?
							btnSelectKey.getPreferredSize().width : btnSelectEncr.getPreferredSize().width;
		btnSelectKey.setPreferredSize(new Dimension(iButMaxWidth, btnSelectKey.getPreferredSize().height));
		btnSelectEncr.setPreferredSize(new Dimension(iButMaxWidth, btnSelectEncr.getPreferredSize().height));
		
		
		// orientation
		lblDescr.setAlignmentX(LEFT_ALIGNMENT);//align lblDescr button to left
		pnlKeyFile.setAlignmentX(LEFT_ALIGNMENT);//align pnlKeyFile button to left
		pnlEncFile.setAlignmentX(LEFT_ALIGNMENT);//align pnlEncFile button to left
		pnlScrolls.setAlignmentX(LEFT_ALIGNMENT);//align pnlSC button to left
		
		// action listener
		btnSelectKey.addActionListener(this);//implement action listener to btnSelectkey
		btnSelectEncr.addActionListener(this);//implement action listener to btnSelectEncr
		btnDecrypt.addActionListener(this);//implement action listener to btnDecrypt
		btnSaveOverlay.addActionListener(this);//implement action listener to btnSaveOverlay
		btnSaveClean.addActionListener(this);//implement action listener to btnSaveClean
		
		tfKey.setEditable(false);
		tfEncr.setEditable(false);
		btnSaveOverlay.setEnabled(false);
		btnSaveClean.setEnabled(false);
		
		fileChooser.setFileFilter(new FileFilter() {
			public boolean accept(File arg0) {
				if (arg0.isDirectory()) return true;
				if (arg0.getName().endsWith(".png")) return true;//used to get png file
				return false;
			}

			public String getDescription() {//used to get description
				return "Image (*.png)";//used to get png file
			}
		});	
		pnlKeyFile.setLayout(new BoxLayout(pnlKeyFile, BoxLayout.X_AXIS));//Creates an invisible component that's always the specified 
		pnlKeyFile.add(tfKey);//add tfkey to the panel pnlkeyfile
		pnlKeyFile.add(Box.createRigidArea(new Dimension(10, 0)));//public static Component createRigidArea(Dimension d)
        pnlKeyFile.add(btnSelectKey);//add btnselectkey to the panel btnselectkey

		//Creates an empty border that takes up space but which does no drawing, 
				//specifying the width of the top, left, bottom, and right sides
		pnlEncFile.setLayout(new BoxLayout(pnlEncFile, BoxLayout.X_AXIS));//Creates an invisible component that's always the specified 
		pnlEncFile.add(tfEncr);
		pnlEncFile.add(Box.createRigidArea(new Dimension(10, 0)));//public static Component createRigidArea(Dimension d)
		pnlEncFile.add(btnSelectEncr);//add btnselectkey to the panel btnselectkey
		
		pnlScrollOverlay.setLayout(new BoxLayout(pnlScrollOverlay, BoxLayout.Y_AXIS));//Sets the layout manager for this container. 
		pnlScrollOverlay.add(scrOverlay);//Appends the specified component to the end of this container. 
		pnlScrollOverlay.add(Box.createRigidArea(new Dimension(0, 10)));
		pnlScrollOverlay.add(btnSaveOverlay);//Appends the specified component to the end of this container. 
		
		pnlScrollClean.setLayout(new BoxLayout(pnlScrollClean, BoxLayout.Y_AXIS));//Creates a layout manager that will lay out components along the given axis.
        pnlScrollClean.add(scrClean);
		pnlScrollClean.add(Box.createRigidArea(new Dimension(0, 10)));//Creates an invisible component that's always the specified size. 

		pnlScrollClean.add(btnSaveClean);
		
		pnlScrolls.setLayout(new BoxLayout(pnlScrolls, BoxLayout.X_AXIS));//Sets the layout manager for this container
		pnlScrolls.add(pnlScrollOverlay);
		pnlScrolls.add(Box.createRigidArea(new Dimension(10, 0)));//Creates an invisible component that's always the specified size. 
		pnlScrolls.add(pnlScrollClean);////Creates an invisible component that's always the specified size. 
		
		pnlAll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));//Sets the border of this component. 
		pnlAll.setLayout(new BoxLayout(pnlAll, BoxLayout.Y_AXIS));//Sets the layout manager for this container. 
		pnlAll.add(lblDescr);//Appends the specified component to the end of this container. This is a convenience method for 
		pnlAll.add(pnlKeyFile);
		pnlAll.add(pnlEncFile);
		pnlAll.add(btnDecrypt);
		pnlAll.add(Box.createVerticalStrut(10));
		pnlAll.add(pnlScrolls);
		
		setFocusTraversalPolicy(new MyFocusTraversalPolicy());//Sets the focus traversal policy that will manage keyboard traversal of this Container's children, if this Container is a focus cycle root.
		
		add(pnlAll);
		setSize(500, 500);//Resizes this component so that it has width width and height height. 
		setMinimumSize(new Dimension(384, 253));//Sets the minimum size of this window to a constant value. Subsequent calls to getMinimumSize will always return this value. 
		//Sets the location of the window relative to the specified component according to the following scenarios. 
		//The target screen mentioned below is a screen to which the window should be placed after the setLocationRelativeTo method is called. 
        setLocationRelativeTo(parent);//Sets the location of the window relative to the specified component according to the following scenarios.
		setTitle("Visual Cryptography - Decrypt Image");
		setVisible(true);//Shows or hides this Window depending on the value of parameter b. 
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);//Sets the operation that will happen by default when the user initiates a "close" on this frame. You must specify one of the following choices: 


	}
	
	@Override
	public void actionPerformed(ActionEvent e) {// declaring new method
		if (e.getActionCommand().equals(btnDecrypt.getText())) {//applying if condition to btnEncrypt that is used for decyption purpose
			//applying validation on fKeyfile 
			if (fKeyFile == null || !fKeyFile.exists() || fEncrFile == null || !fEncrFile.exists()) {
				//displying error message if validation is not correct that file not found
				JOptionPane.showMessageDialog(this, "File not found", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;//return the method
			}
			
			BufferedImage imgKey = Crypting.loadAndCheckEncrFile(fKeyFile);
			//JOptionPane makes it easy to pop up a standard dialog box that prompts users for a value or informs them of something.
			if (imgKey == null) {
				JOptionPane.showMessageDialog(this, fKeyFile.getName() + " is not a valid key file", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;//return the value
			}
			//Loads a key or encrypted file in Image and checks it (roughly). It is assumed that the file is a png
			BufferedImage imgEnc = Crypting.loadAndCheckEncrFile(fEncrFile);
			if (imgEnc == null) {
				//JOptionPane makes it easy to pop up a standard dialog box that prompts users for a value or informs them of something.
				JOptionPane.showMessageDialog(this, fEncrFile.getName() + " is not an encrypted image", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			//Generates an overlay of the key and the encrypted file, therefore producing an unclean, but Human readable decryption
			imgOverlay = Crypting.overlayImages(imgKey, imgEnc);
			if (imgOverlay == null) {
				JOptionPane.showMessageDialog(this, "Decryption failed - key and encrypted image not the same size?", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// An overlay generated by overlayImages
			imgClean = Crypting.decryptImage(imgOverlay);
			if (imgClean == null) {
				JOptionPane.showMessageDialog(this, "Decryption failed - key and encrypted image not the same size?", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
             //Defines the icon this component will display. If the value of icon is null, nothing is displayed. 
            //The default value of this property is null. 
			lblOverlay.setIcon(new ImageIcon(imgOverlay));
			lblClean.setIcon(new ImageIcon(imgClean));
			
			//Enables (or disables) the button.
			btnSaveOverlay.setEnabled(true);
			btnSaveClean.setEnabled(true);
			//Returns the command string associated with this action.
		} else if (e.getActionCommand().equals(btnSaveOverlay.getText())) {
			if (imgOverlay == null) return;
			//Sets the selected file. If the file's parent directory is not the current directory, changes the current directory to be the file's parent directory.
			fileChooser.setSelectedFile(new File(""));
			//Sets the string that goes in the JFileChooser window's title bar.
		    fileChooser.setDialogTitle("Save overlay as..");
		    if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
		    	File f = fileChooser.getSelectedFile();
		    	//Tests if this string ends with the specified suffix.
		    	if (!f.toString().endsWith(".png")) {
		    		f = new File(f.toString() + ".png");
		    	}
		    	try {
					ImageIO.write(imgOverlay, "png", f);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(this, "Could not Save file because: " + e1.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
		    }
		}//Returns the command string associated with this action. 
		else if (e.getActionCommand().equals(btnSaveClean.getText())) {
			if (imgClean == null) return;
			fileChooser.setSelectedFile(new File(""));//Sets the string that goes in the JFileChooser window's title bar.
		    fileChooser.setDialogTitle("Save decrypted image as..");//Sets the string that goes in the JFileChooser window's title bar.
		    //Returns the selected file. This can be set either by the programmer via setSelectedFile or by a user action, 
		    if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
		    	File f = fileChooser.getSelectedFile();
		    	if (!f.toString().endsWith(".png")) {
		    		//Returns the pathname string of this abstract pathname. 
		    		f = new File(f.toString() + ".png");
		    	}
		    	try {
					ImageIO.write(imgClean, "png", f);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(this, "Could not Save file because: " + e1.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
		    }
		}//Returns the command string associated with this action. 
		else if (e.getActionCommand().equals(btnSelectKey.getText())) {
			fileChooser.setDialogTitle("Open keyfile..");
		    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
		    	//Returns the selected file. This can be set either by the programmer via setSelectedFile or by a user action, such as either typing the filename into the UI or selecting the file from a list in the UI.
		    	if (!fileChooser.getSelectedFile().exists()) return;
		    	if (!fileChooser.getSelectedFile().getName().endsWith(".png")) return;
		    	fKeyFile = fileChooser.getSelectedFile();
		    	tfKey.setText(fKeyFile.toString());
		    }
		} else if (e.getActionCommand().equals(btnSelectEncr.getText())) {
			fileChooser.setDialogTitle("Open source image..");
		    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
		    	if (!fileChooser.getSelectedFile().exists()) return;
		    	if (!fileChooser.getSelectedFile().getName().endsWith(".png")) return;
		    	fEncrFile = fileChooser.getSelectedFile();
		    	tfEncr.setText(fEncrFile.toString());
		    }
		}
	}

	class MyFocusTraversalPolicy extends FocusTraversalPolicy {
	    public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
	        if(aComponent.equals(btnSelectKey)) return btnSelectEncr;
	        else if(aComponent.equals(btnSelectEncr)) return btnDecrypt;
	        else if(aComponent.equals(btnDecrypt)) {
	        	if (btnSaveOverlay.isEnabled()) return btnSaveOverlay;
	        	if (btnSaveClean.isEnabled()) return btnSaveClean;
	        	return btnSelectKey;
	        }
	        else if(aComponent.equals(btnSaveOverlay) && btnSaveClean.isEnabled()) return btnSaveClean;
	        return btnSelectKey;
	    }
	   
	    public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
	        if(aComponent.equals(btnSelectKey)) {
	        	if (btnSaveClean.isEnabled()) return btnSaveClean;
	        	if (btnSaveOverlay.isEnabled()) return btnSaveOverlay;
	        	return btnDecrypt;
	        }
	        else if(aComponent.equals(btnSelectEncr)) return btnSelectKey;
	        else if(aComponent.equals(btnDecrypt)) return btnSelectEncr;
	        else if(aComponent.equals(btnSaveOverlay)) return btnDecrypt;
	        else if(aComponent.equals(btnSaveClean) && btnSaveOverlay.isEnabled()) return btnSaveOverlay;
	        return btnDecrypt;
	    }
	    
	    public Component getDefaultComponent(Container focusCycleRoot) {
	        return btnSelectKey;
	    }
	   
	    public Component getFirstComponent(Container focusCycleRoot) {
	        return btnSelectKey;
	    }
	   
	    public Component getLastComponent(Container focusCycleRoot) {
	        return btnSaveClean;
	    }
	}
	
}