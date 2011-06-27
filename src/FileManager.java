import java.io.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

/** 
 Read and write a file using an explicit encoding.
 Removing the encoding from this code will simply cause the 
 system's default encoding to be used instead.  
*/
public final class FileManager {

//  /** Requires two arguments - the file name, and the encoding to use.  */
//  public static void main(String... aArgs) throws IOException {
//    String fileName = aArgs[0];
//    String encoding = aArgs[1];
//    FileManager test = new FileManager(
//      fileName, encoding
//    );
//    test.write();
//    test.read();
//  }
  
  /** Constructor. */
  FileManager(String aFileName, String aEncoding){
    fEncoding = aEncoding;
    fFileName = aFileName;
  }
  
  /** Write fixed content to the given file. */
  void write(String content)   {
    Writer out=null;
	try {
		out = new OutputStreamWriter(new FileOutputStream(fFileName), fEncoding);
	} catch (UnsupportedEncodingException e) {
		JOptionPane.showMessageDialog(null, "Folgender Fehler ist aufgetreten:\r\n"+e.toString());
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		JOptionPane.showMessageDialog(null, "Folgender Fehler ist aufgetreten:\r\n"+e.toString());
		e.printStackTrace();
	}
    try {
      try {
		out.write(content);
	} catch (IOException e) {
		JOptionPane.showMessageDialog(null, "Folgender Fehler ist aufgetreten:\r\n"+e.toString());
		e.printStackTrace();
	}
    }
    finally {
      try {
		out.close();
	} catch (IOException e) {
		JOptionPane.showMessageDialog(null, "Folgender Fehler ist aufgetreten:\r\n"+e.toString());
		e.printStackTrace();
	}
    }
  }
  
  /** Read the contents of the given file. */
  String read() {
//    log("Reading from file.");
    StringBuilder text = new StringBuilder();
    String NL = System.getProperty("line.separator");
    Scanner scanner=null;
	try {
		scanner = new Scanner(new FileInputStream(fFileName), fEncoding);
	} catch (FileNotFoundException e) {
		JOptionPane.showMessageDialog(null, "Folgender Fehler ist aufgetreten:\r\n"+e.toString());
		e.printStackTrace();
	}
    try {
      while (scanner.hasNextLine()){
        text.append(scanner.nextLine() + NL);
      }
    }
    finally{
      scanner.close();
    }
    return text.toString();
  }
  
  // PRIVATE 
  private final String fFileName;
  private final String fEncoding;
}