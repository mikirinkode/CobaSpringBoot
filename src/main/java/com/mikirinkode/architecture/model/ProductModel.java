package com.mikirinkode.architecture.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;

// model untuk simpan ke databasenya
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "product")
@NoArgsConstructor
@Data
@Where(clause = "is_deleted = false") // to only show data which not beet soft deleted
public class ProductModel extends BaseDao{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "price", nullable = false)
    private Double price;

}
