package com.techstore.entities;

import javax.persistence.*;


@Entity
@Table(name = "category_parameters_item_values")
public class ProductParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "category_parameter_id")
    private CategoryParameter categoryParameter;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Product item;

    private String value;


    public ProductParameter() {}
    public ProductParameter(final CategoryParameter categoryParameter, final Product item, final String value) {
        this.categoryParameter = categoryParameter;
        this.item = item;
        this.value = value;
    }


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public CategoryParameter getCategoryParameter() { return categoryParameter; }
    public void setCategoryParameter(CategoryParameter categoryParameter) { this.categoryParameter = categoryParameter; }

    public Product getItem() { return item; }
    public void setItem(Product item) { this.item = item; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    @Override
    public String toString() {
        return String.format("Item Parameter: { " +
                "Parameter Name: %s, " +
                "Parameter Value: %s, " +
                "Parameter Symbol: %s, " +
                "Item Name: %s }", categoryParameter.getName(), value, categoryParameter.getSymbol(), item.getName());
    }
}