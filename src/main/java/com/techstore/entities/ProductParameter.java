package com.techstore.entities;

import javax.persistence.*;


@Entity
@Table(name = "category_parameters_item_values")
public class ProductParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public Integer getId() { return id; }

    @ManyToOne
    @JoinColumn(name = "category_parameter_id")
    private CategoryParameter categoryParameter;
    public CategoryParameter getCategoryParameter() { return categoryParameter; }

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Product item;
    public Product getItem() { return item; }

    private String value;
    public String getValue() { return value; }


    public ProductParameter() {}
    public ProductParameter(final CategoryParameter categoryParameter, final Product item, final String value) {
        this.categoryParameter = categoryParameter;
        this.item = item;
        this.value = value;
    }


    @Override
    public String toString() {
        return String.format("Item Parameter: { " +
                "Parameter Name: %s, " +
                "Parameter Value: %s, " +
                "Parameter Symbol: %s, " +
                "Item Name: %s }", categoryParameter.getName(), value, categoryParameter.getSymbol(), item.getName());
    }
}