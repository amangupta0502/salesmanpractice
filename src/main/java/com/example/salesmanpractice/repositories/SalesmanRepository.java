package com.example.salesmanpractice.repositories;

import com.example.salesmanpractice.entities.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesmanRepository extends JpaRepository<Salesman, Long> {
    List<Salesman> findSalesmanById(long kw);
}

