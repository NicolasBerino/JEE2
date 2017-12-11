package springapp.model;
public class Product {
private Integer number;
private String name;
private Double price;
private String description;
private String type;
public Integer getNumber() {
return number;
}
public void setNumber(Integer number) {
this.number = number;
}
public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}
public Double getPrice() {
return price;
}
public void setPrice(Double price) {
this.price = price;
}
public String getDescription() {
return description;
}
public void setDescription(String description) {
this.description = description;
}
public String getType() {
return type;
}
public void setType(String type) {
this.type = type;
}
}