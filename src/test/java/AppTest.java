import com.wtuia.netflow.client.NetFlowClient;

public class AppTest {
	
	/**
	 * @see com.wtuia.netflow.handler.Flow
	 */
	public static void main(String[] args) {
		NetFlowClient.onMessage((list) -> {
			list.forEach(System.out::println);
		});
	}
	
	
}
