package com.point.of.sale.pos.model.repository;

import com.point.of.sale.pos.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {}
