package com.erx.service.ifc;

public enum OrderStatus {
	INITIATED,
	CONFIRMED,
	PENDING_ADDRESS,
	PENDING_PACKING,
	PACKED,
	PENDING_DELIVERY,
	OUT_FOR_DELIVERY,
	DELIVERED,
	EXPIRED,
	COMPLETE
}
