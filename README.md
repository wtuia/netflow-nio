# netflow-nio

Processor for reading NetFlow packets based on Netty, including both v5 and v9.

Usage
``` java
import com.wtuia.netflow.client.NetFlowClient;
NetFlowClient.onMessage((list) -> {
	list.forEach(System.out::println);
});
```