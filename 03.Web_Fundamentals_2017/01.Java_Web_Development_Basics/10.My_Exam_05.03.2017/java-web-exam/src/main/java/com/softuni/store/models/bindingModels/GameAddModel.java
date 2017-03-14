package com.softuni.store.models.bindingModels;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class GameAddModel {

    @Pattern(regexp = "[A-Z].{3,100}", message = "Invalid title.")
    private String title;

    @Size(min = 20, message = "Description too short.")
    private String description;

    private String thumbnail;

    @Pattern(regexp = "^([0-9]+.[0-9]{0,2})$", message = "Invalid price.")
    private String price;

    @Pattern(regexp = "^([0-9]+.[0-9]{0,1})$", message = "Invalid size.")
    private String size;

    @Size(min = 11, max = 11, message = "Invalid trailer URL.")
    private String trailer;

    private String releaseDate;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTrailer() {
        return this.trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
