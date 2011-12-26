import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Scanner;
import javax.swing.JOptionPane;

/** 
 Read and write a file using an explicit encoding.
 Removing the encoding from this code will simply cause the 
 system's default encoding to be used instead.  
*/
public final class FileManager {
	private final String fFileName;
	private final String fEncoding;

	/** Constructor. */
	FileManager(String aFileName, String aEncoding) {
		this.fEncoding = aEncoding;
		this.fFileName = aFileName;
	}

	/** Write fixed content to the given file. */
	void write(String content) {
		Writer out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(this.fFileName),
					this.fEncoding);
		} catch (UnsupportedEncodingException e) {
			JOptionPane.showMessageDialog(null,
					"Folgender Fehler ist aufgetreten:\r\n" + e.toString());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Folgender Fehler ist aufgetreten:\r\n" + e.toString());
			e.printStackTrace();
		}
		try {
			out.write(content);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Folgender Fehler ist aufgetreten:\r\n" + e.toString());
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,
						"Folgender Fehler ist aufgetreten:\r\n" + e.toString());
				e.printStackTrace();
			}
		}
	}

	/** Read the contents of the given file. */
	String read() {
		StringBuilder text = new StringBuilder();
		String NL = System.getProperty("line.separator");
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileInputStream(this.fFileName),
					this.fEncoding);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Folgender Fehler ist aufgetreten:\r\n" + e.toString());
			e.printStackTrace();
		}
		try {
			while (scanner.hasNextLine())
				text.append(scanner.nextLine() + NL);
		} finally {
			scanner.close();
		}
		return text.toString();
	}
}