import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;


public class ADB {
	private WMain wm = null;
	private String adbFile = "";
	
	public ADB(WMain wmain) {
		// delete log-file
		
		try {
			FileOutputStream erasor = new FileOutputStream("adb.log");
			erasor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String os = System.getProperty("os.name").split(" ")[0].toLowerCase();
		if(os.equals("windows")) {
			adbFile="adb.exe";
		}
		else if(os.equals("linux")) {
			adbFile="adblinux";
		}
		else if(os.equals("mac")) {
			adbFile="adbmac";
		}
		else {
			JOptionPane.showMessageDialog(null, "Betriebssystem konnte nicht ermittelt werden!");
			System.exit(0);
		}
		
		this.wm = wmain;
		this.startADBD();
		this.isConnected();
	}
	
	// start ADB-Daemon
	private void startADBD() {
		try {
			Runtime.getRuntime().exec("lib/adb/adb.exe fork-server server");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "ADB konnte nicht gefunden werden!");
			e.printStackTrace();
		}
	}
	
	// show error-message
	private void showError(String error) {
		JOptionPane.showMessageDialog(null, "Folgender Fehler ist aufgetreten:\r\n"+error);
		System.exit(0);
	}
	
	// check if connected
	public boolean isConnected() {
		String result = this.exec("shell busybox uname")[1].trim();
		if(result.equals("error: device not found")) {
			return false;
		}
		return true;
	}
	
	// run ADB-command
	public String[] exec(String command) {
		if(command.trim().length()==0) {
			String[] result = new String[2];
			result[0] = "0"; result[1] = "";
			return result;
		}
		
		// run command
        Process p = null;
		try {
			p = Runtime.getRuntime().exec("lib/adb/"+adbFile+" "+command);
		} catch (IOException e) {
			this.showError(e.toString());
			e.printStackTrace();
		}
		
		// get in/out-streams
        BufferedReader stdInput = new BufferedReader(new 
             InputStreamReader(p.getInputStream()));
        BufferedReader stdError = new BufferedReader(new 
             InputStreamReader(p.getErrorStream()));

        // read output
        String output = "";
        String s = null;
        try {
			while ((s = stdInput.readLine()) != null) {
			    output+=s;
			}
		} catch (IOException e) {
			this.showError(e.toString());
			e.printStackTrace();
		}

		// read error
		String error = "";
        try {
			while ((s = stdError.readLine()) != null) {
			    error+=s;
			}
		} catch (IOException e) {
			this.showError(e.toString());
			e.printStackTrace();
		}
	
		String[] result=new String[2];
		if(error.length()>0){
			result[0]="0";
			result[1]=error.trim();
		}
		else {
			result[0]="1";
			result[1]=output.trim();
		}
		this.wm.log(result[1]);
		try { 
			BufferedWriter out = new BufferedWriter(new FileWriter("adb.log", true)); 
			out.write("> "+command+"\r\n");
			out.write(result[1]+"\r\n"); 
			out.close(); 
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		return result;
	}
}
