package stream.model;

import java.math.BigDecimal;

public class OrderLine {
    private Long id;
    private OrderLineType type;
    private Long productId;
    private Integer quantity;
    private BigDecimal amount;

    public enum OrderLineType {
        PURCHASE,
        DISCOUNT
    }

    public Long getId() {
        return id;
    }

    public OrderLine setId(Long id) {
        this.id = id;
        return this;
    }

    public OrderLineType getType() {
        return type;
    }

    public OrderLine setType(OrderLineType type) {
        this.type = type;
        return this;
    }

    public Long getProductId() {
        return productId;
    }

    public OrderLine setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderLine setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public OrderLine setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public String toString() {
        return "OrderLine [id=" + id + ", " + (type != null ? "type=" + type + ", " : "") + "productId=" + productId
                + ", quantity=" + quantity + ", " + (amount != null ? "amount=" + amount : "") + "]";
    }
}
