package problem_02;

public class Book {

    private String author;
    private String title;
    private Double price;

    public Book(String author, String title, Double price) {
        this.setAuthor(author);
        this.setTitle(title);
        this.setPrice(price);
    }

    protected String getAuthor() {
        return this.author;
    }

    protected void setAuthor(String author) {

        String[] authorsName = author.split(" ");
        if (authorsName.length > 1) {
            String name = authorsName[1];
            if (Character.isDigit(name.charAt(0))) {
                throw new IllegalArgumentException("Author not valid!");
            }
        }
        this.author = author;
    }

    protected String getTitle() {
        return this.title;
    }

    protected void setTitle(String title) {
        if (title.isEmpty() || title.length() < 3) {
            throw new IllegalArgumentException("Title not valid!");
        }
        this.title = title;
    }

    protected Double getPrice() {
        return this.price;
    }

    protected void setPrice(Double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price not valid!");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Type: ").append(this.getClass().getSimpleName())
                .append(System.lineSeparator())
                .append("Title: ").append(this.getTitle())
                .append(System.lineSeparator())
                .append("Author: ").append(this.getAuthor())
                .append(System.lineSeparator())
                .append("Price: ").append(this.getPrice())
                .append(System.lineSeparator());
        return sb.toString();
    }
}
