package org.example.bookshop.repository.order;

import java.util.List;
import java.util.Optional;
import org.example.bookshop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(Long orderId);

    Optional<OrderItem> findByIdAndOrder_Id(Long orderItemId, Long orderId);
}
