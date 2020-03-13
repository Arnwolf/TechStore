package com.techstore.entities;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;


@Entity
@Table(name = "category_parameters")
@Immutable
public class CategoryParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public Integer getId() { return id; }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    public Category getCategory() { return category; }

    @Column(name = "parameter_name")
    private String name;
    public String getName() { return name; }

    @Column(name = "parameter_symbol")
    private String symbol = "";
    public String getSymbol() { return symbol; }

    private Boolean searchable = false;
    public Boolean getSearchable() { return searchable; }

    private Boolean changeable = false;
    public Boolean getChangeable() { return changeable; }

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
}
