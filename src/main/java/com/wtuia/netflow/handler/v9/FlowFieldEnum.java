package com.wtuia.netflow.handler.v9;

/**
 *
 *<a href="https://www.cisco.com/en/US/technologies/tk648/tk362/technologies_white_paper09186a00800a3db9.html">V9模板定义<a/>
 */
public enum FlowFieldEnum {
	
    IN_BYTES(1),
    IN_PKTS(2),
    FLOWS(3),
    PROTOCOL(4),
    SRC_TOS(5),
    TCP_FLAGS(6),
    L4_SRC_PORT(7),
    IPV4_SRC_ADDR(8),
    SRC_MASK(9),
    INPUT_SNMP(10),
    L4_DST_PORT(11),
    IPV4_DST_ADDR(12),
    DST_MASK(13),
    OUTPUT_SNMP(14),
    IPV4_NEXT_HOP(15),
    SRC_AS(16),
    DST_AST(17),
    BGP_IPV4_NEXT_HOP(18),
    MUL_DST_PKTS(19),
    MUL_DST_BYTES(20),
    LAST_SWITCHED(21),
    FIRST_SWITCHED(22),
    OUT_BYTES(23),
    OUT_PKTS(24),
    MIN_PKT_LNGTH(25),
    MAX_PKT_LNGTH(26),
    IPV6_SRC_ADDR(27),
    IPV6_DST_ADDR(28),
    IPV6_SRC_MASK(29),
    IPV6_DST_MASK(30),
    IPV6_FLOW_LABEL(31),
    ICMP_TYPE(32),
    MUL_IGMP_TYPE(33),
    SAMPLING_INTERVAL(34),
    SAMPLING_ALGORITHM(35),
    FLOW_ACTIVE_TIMEOUT(36),
    FLOW_INACTIVE_TIMEOUT(37),
    ENGINE_TYPE(38),
    ENGINE_ID(39),
    TOTAL_BYTES_EXP(40),
    TOTAL_PKTS_EXP(41),
    TOTAL_FLOWS_EXP(42),
    IPV4_SRC_PREFIX(44),
    IPV4_DST_PREFIX(45),
    MPLS_TOP_LABEL_TYPE(46),
    MPLS_TOP_LABEL_IP_ADDR(47),
    FLOW_SAMPLER_ID(48),
    FLOW_SAMPLER_MODE(49),
    FLOW_SAMPLER_RANDOM_INTERVAL(50),
    MIN_TTL(52),
    MAX_TTL(53),
    IPV4_IDENT(54),
    DST_TOS(55),
    IN_SRC_MAC(56),
    OUT_DST_MAC(57),
    SRC_VLAN(58),
    DST_VLAN(59),
    IP_PROTOCOL_VERSION(60),
    DIRECTION(61),
    IPV6_NEXT_HOP(62),
    BPG_IPV6_NEXT_HOP(63),
    IPV6_OPTION_HEADERS(64),
    MPLS_LABEL_1(70),
    MPLS_LABEL_2(71),
    MPLS_LABEL_3(72),
    MPLS_LABEL_4(73),
    MPLS_LABEL_5(74),
    MPLS_LABEL_6(75),
    MPLS_LABEL_7(76),
    MPLS_LABEL_8(77),
    MPLS_LABEL_9(78),
    MPLS_LABEL_10(79),
    IN_DST_MAC(80),
    OUT_SRC_MAC(81),
    IF_NAME(82),
    IF_DESC(83),
    SAMPLER_NAME(84),
    IN_PERMANENT_BYTES(85),
    IN_PERMANENT_PKTS(86),
    FRAGMENT_OFFSET(88),
    FORWARDING_STATUS(89),
    MPLS_PAL_RD(90),
    MPLS_PREFIX_LEN(91),
    SRC_TRAFFIC_INDEX(92),
    DST_TRAFFIC_INDEX(93),
    APPLICATION_DESCRIPTION(94),
    APPLICATION_TAG(95),
    APPLICATION_NAME(96),
    POST_IP_DIFF_SERV_CODE_POINT(98),
    REPLICATION_FACTOR(99),
    DEPRECATED(100),
    LAYER2_PACKET_SECTION_OFFSET(102),
    LAYER2_PACKET_SECTION_SIZE(103),
    LAYER2_PACKET_SECTION_DATA(104);

   private final int value;
	
	FlowFieldEnum(int value) {
		this.value = value;
	}
	
	public static FlowFieldEnum fromValue(int value) {
        for(FlowFieldEnum t : values()) {
            if(t.value == value)
                return t;
        }
        return null;
    }
	
}
