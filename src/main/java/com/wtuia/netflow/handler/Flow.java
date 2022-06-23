package com.wtuia.netflow.handler;

/**
 * netflow的结果对象, 只含有部分值
 */
public class Flow {
	
	private String routIp;
	private long inBytes;
	private long inPaks;
	private long flows;
	private long tcpFlag;
	private long ipv4SrcAddr;
	private long l4SrcPort;
	private long srcMask;
	private long ipv4DstAddr;
	private long l4DstPort;
	private long dstMask;
	
	public Flow(String routIp) {
		this.routIp = routIp;
	}
	
	public long getIpv4SrcAddr() {
		return ipv4SrcAddr;
	}
	
	public Flow setIpv4SrcAddr(long ipv4SrcAddr) {
		this.ipv4SrcAddr = ipv4SrcAddr;
		return this;
	}
	
	public long getIpv4DstAddr() {
		return ipv4DstAddr;
	}
	
	public Flow setIpv4DstAddr(long ipv4DstAddr) {
		this.ipv4DstAddr = ipv4DstAddr;
		return this;
	}
	
	public String getRoutIp() {
		return routIp;
	}
	
	public Flow setRoutIp(String routIp) {
		this.routIp = routIp;
		return this;
	}
	
	public long getInBytes() {
		return inBytes;
	}
	
	public Flow setInBytes(long inBytes) {
		this.inBytes = inBytes;
		return this;
	}
	
	public long getInPaks() {
		return inPaks;
	}
	
	/**
 	 */
	public Flow setInPaks(long inPaks) {
		this.inPaks = inPaks;
		return this;
	}
	
	public Flow setL4SrcPort(long l4SrcPort) {
		this.l4SrcPort = l4SrcPort;
		return this;
	}
	
	public Flow setL4DstPort(long l4DstPort) {
		this.l4DstPort = l4DstPort;
		return this;
	}
	
	public long getFlows() {
		return flows;
	}
	
	public Flow setFlows(long flows) {
		this.flows = flows;
		return this;
	}
	
	public long getTcpFlag() {
		return tcpFlag;
	}
	
	public Flow setTcpFlag(long tcpFlag) {
		this.tcpFlag = tcpFlag;
		return this;
	}
	
	public long getL4SrcPort() {
		return l4SrcPort;
	}
	
	public long getSrcMask() {
		return srcMask;
	}
	
	public Flow setSrcMask(long srcMask) {
		this.srcMask = srcMask;
		return this;
	}
	
	public long getL4DstPort() {
		return l4DstPort;
	}
	
	public long getDstMask() {
		return dstMask;
	}
	
	public Flow setDstMask(long dstMask) {
		this.dstMask = dstMask;
		return this;
	}
	
	@Override
	public String toString() {
		return "Flow{" +
				"routIp='" + routIp + '\'' +
				", inBytes=" + inBytes +
				", inPaks=" + inPaks +
				", flows=" + flows +
				", tcpFlag=" + tcpFlag +
				", ipv4SrcAddr=" + ipv4SrcAddr +
				", l4SrcPort=" + l4SrcPort +
				", srcMask=" + srcMask +
				", ipv4DstAddr=" + ipv4DstAddr +
				", l4DstPort=" + l4DstPort +
				", dstMask=" + dstMask +
				'}';
	}
}
