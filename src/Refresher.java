
public class Refresher extends Thread {
	WMain wm = null;
	
	public Refresher(WMain wmain) {
		this.wm = wmain;
	}
	
	public void run() {
		while(true) {
			this.wm.checkConnection();
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
