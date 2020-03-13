package com.techstore.entities;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;


@Entity
@Table(name = "categories")
@Immutable
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public Integer getId() { return id; }

    private String name;
    public String getName() { return name; }

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory = null;
    public Category getParentCategory() { return parentCategory; }


    public Category() {}
    public Category(final String name, final Category parentCategory) {
        this.name = name;
        this.parentCategory = parentCategory;
    }


    @Override
    public String toString() {
        return String.format("Category { " +
                "ID : %s, " +
                "Name : %s, " +
                "ParentCategoryID : %s }", id, name, parentCategory);
    }
}
