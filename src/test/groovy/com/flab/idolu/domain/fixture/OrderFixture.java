package com.flab.idolu.domain.fixture;

import java.math.BigDecimal;
import java.util.List;

import com.flab.idolu.domain.order.dto.request.OrderLineItemDto;
import com.flab.idolu.domain.order.dto.request.OrderRequest;

public class OrderFixture {

	private static final OrderLineItemDto DEFAULT_ORDER_LINE_ITEM = OrderLineItemDto.builder()
		.productId(1L)
		.price(BigDecimal.valueOf(73000))
		.quantity(1)
		.build();

	private static final OrderLineItemDto INSUFFICIENT_QUANTITY_ORDER_ITEM = OrderLineItemDto.builder()
		.productId(1L)
		.price(BigDecimal.valueOf(73000))
		.quantity(4)
		.build();

	public static final OrderRequest DEFAULT_ORDER_REQUEST = OrderRequest.builder()
		.recipient("oneny")
		.phone("01011113234")
		.zipCode("43251")
		.address1("기본 주소")
		.address2("상세 주소")
		.orderLineItems(List.of(DEFAULT_ORDER_LINE_ITEM))
		.paymentType("CREDIT")
		.build();

	public static final OrderRequest INSUFFICIENT_ORDER_REQUEST = OrderRequest.builder()
		.recipient("oneny")
		.phone("01011113234")
		.zipCode("43251")
		.address1("기본 주소")
		.address2("상세 주소")
		.orderLineItems(List.of(INSUFFICIENT_QUANTITY_ORDER_ITEM))
		.paymentType("CREDIT")
		.build();

	public static final OrderRequest BLANK_RECIPIENT_ORDER_REQUEST = OrderRequest.builder()
		.recipient("")
		.phone("01011113234")
		.zipCode("43251")
		.address1("기본 주소")
		.address2("상세 주소")
		.orderLineItems(List.of(DEFAULT_ORDER_LINE_ITEM))
		.paymentType("CREDIT")
		.build();

	public static final OrderRequest BLANK_PHONE_ORDER_REQUEST = OrderRequest.builder()
		.recipient("oneny")
		.phone("")
		.zipCode("43251")
		.address1("기본 주소")
		.address2("상세 주소")
		.orderLineItems(List.of(DEFAULT_ORDER_LINE_ITEM))
		.paymentType("CREDIT")
		.build();

	public static final OrderRequest INVALID_PHONE_ORDER_REQUEST = OrderRequest.builder()
		.recipient("oneny")
		.phone("0103234")
		.zipCode("43251")
		.address1("기본 주소")
		.address2("상세 주소")
		.orderLineItems(List.of(DEFAULT_ORDER_LINE_ITEM))
		.paymentType("CREDIT")
		.build();

	public static final OrderRequest BLANK_ZIPCODE_ORDER_REQUEST = OrderRequest.builder()
		.recipient("oneny")
		.phone("01011113234")
		.zipCode("")
		.address1("기본 주소")
		.address2("상세 주소")
		.orderLineItems(List.of(DEFAULT_ORDER_LINE_ITEM))
		.paymentType("CREDIT")
		.build();

	public static final OrderRequest BLANK_ADDRESS1_ORDER_REQUEST = OrderRequest.builder()
		.recipient("oneny")
		.phone("01011113234")
		.zipCode("43251")
		.address1("")
		.address2("상세 주소")
		.orderLineItems(List.of(DEFAULT_ORDER_LINE_ITEM))
		.paymentType("CREDIT")
		.build();

	public static final OrderRequest EMPTY_ITEMS_ORDER_REQUEST = OrderRequest.builder()
		.recipient("oneny")
		.phone("01011113234")
		.zipCode("43251")
		.address1("기본 주소")
		.address2("상세 주소")
		.orderLineItems(List.of())
		.paymentType("CREDIT")
		.build();

	public static final OrderRequest INVALID_PAYMENT_TYPE_ORDER_REQUEST = OrderRequest.builder()
		.recipient("oneny")
		.phone("01011113234")
		.zipCode("43251")
		.address1("기본 주소")
		.address2("상세 주소")
		.orderLineItems(List.of(DEFAULT_ORDER_LINE_ITEM))
		.paymentType("PAY")
		.build();
}
