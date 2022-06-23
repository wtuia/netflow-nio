import com.wtuia.netflow.client.UDPClient;

public class AppTest {
	
	
	public static void main(String[] args) {
		new Thread(new UDPClient()).start();
	}
	
	
}
