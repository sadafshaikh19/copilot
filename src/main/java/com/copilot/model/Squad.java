package com.copilot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "squads")
public class Squad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "productOwner")
    private String productOwner;
  public Squad() {
  }


  public Squad(String name, String description, String productOwner) {
    this.name = name;
    this.description = description;
    this.productOwner = productOwner;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getProductOwner() {
    return productOwner;
  }

  public void setProductOwner(String productOwner) {
    this.productOwner = productOwner;
  }

    @Override
    public String toString() {
        return "Squad [id=" + id + ", name=" + name + ", desc=" + description + ", po=" + productOwner + "]";
    }

}
