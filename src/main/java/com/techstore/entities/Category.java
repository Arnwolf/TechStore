package com.techstore.entities;

import javax.persistence.*;


@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory = null;


    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setId(Integer id) { this.id = id; }
    public Integer getId() { return id; }

    public void setParentCategory(Category parentCategory) { this.parentCategory = parentCategory; }
    public Category getParentCategory() { return parentCategory; }


    @Override
    public String toString() {
        return String.format("Category { " +
                "ID : %s, " +
                "Name : %s, " +
                "ParentCategoryID : %s }", id, name, parentCategory);
    }
}
