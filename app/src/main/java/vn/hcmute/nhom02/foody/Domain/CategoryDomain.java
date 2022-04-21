package vn.hcmute.nhom02.foody.Domain;

public class CategoryDomain {
    private String name;
    private String pic;
    private String price;

    public CategoryDomain(String name, String pic, String price) {
        this.name = name;
        this.pic = pic;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CategoryDomain{" +
                "name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
