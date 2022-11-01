package stream.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long id;
    private LocalDateTime createdAt;
    private Long createdByUserId;
    private OrderStatus status;
    private BigDecimal amount;  // BigDecimal 큰 수도 무리없이 담을 수 있도록 자바에서 제공하는 클래스
    private List<OrderLine> orderLines;

    public enum OrderStatus {
        CREATED,
        IN_PROGRESS,
        ERROR,
        PROCESSED
    }

    public Long getId() {
        return id;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Order setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Long getCreatedByUserId() {
        return createdByUserId;
    }

    public Order setCreatedByUserId(Long createdByUserId) {
        this.createdByUserId = createdByUserId;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Order setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Order setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public Order setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
        return this;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", " + (createdAt != null ? "createdAt=" + createdAt + ", " : "")
                + "createdByUserId=" + createdByUserId + ", " + (status != null ? "status=" + status + ", " : "")
                + (amount != null ? "amount=" + amount + ", " : "")
                + (orderLines != null ? "orderLines=" + orderLines : "") + "]";
    }
}