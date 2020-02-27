package com.techstore.entities;

import javax.persistence.*;


@Entity
@Table(name = "category_parameters")
public class CategoryParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "parameter_name")
    private String name;

    @Column(name = "parameter_symbol")
    private String symbol = "";

    private Boolean searchable = false;

    private Boolean changeable = false;

    public CategoryParameter() {}
    public CategoryParameter(final Category category, final String name,
                             final String symbol, final Boolean searchable,
                             final Boolean changeable) {
        this.category = category;
        this.name = name;
        this.changeable = changeable;
        this.symbol = symbol;
        this.searchable = searchable;
    }


    public Integer getId() { return id; }
    public void setId(final Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(final String name) { this.name = name; }

    public String getSymbol() { return symbol; }
    public void setSymbol(final String symbol) { this.symbol = symbol; }

    public Boolean getSearchable() { return searchable; }
    public void setSearchable(final Boolean searchable) { this.searchable = searchable; }

    public Boolean getChangeable() { return changeable; }
    public void setChangeable(final Boolean changeable) { this.changeable = changeable; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}
