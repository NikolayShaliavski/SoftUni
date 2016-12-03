package com.jsonexercise.io;

import com.jsonexercise.domain.dto.exportDto.CategoryWithProductsDto;
import com.jsonexercise.domain.dto.exportDto.ProductInRangeDto;
import com.jsonexercise.domain.dto.exportDto.SellerDto;
import com.jsonexercise.domain.dto.exportDto.UserDto;
import com.jsonexercise.domain.dto.importDto.CategoryJsonDto;
import com.jsonexercise.domain.dto.importDto.ProductJsonDto;
import com.jsonexercise.domain.dto.importDto.UserJsonDto;
import com.jsonexercise.io.parsers.jsonParsers.JSONParser;
import com.jsonexercise.services.category.CategoryService;
import com.jsonexercise.services.product.ProductService;
import com.jsonexercise.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Engine implements CommandLineRunner {

    private JSONParser jsonParser;

    private UserService userService;

    private ProductService productService;

    private CategoryService categoryService;

    @Autowired
    public Engine(JSONParser jsonParser,
                  UserService userService,
                  ProductService productService,
                  CategoryService categoryService) {
        this.jsonParser = jsonParser;
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... strings) throws Exception {
        //this.importData();
        this.exportData();
    }

    private void exportData() {
        //this.exportProductsInRange();
        //this.exportSellers();
        //this.exportCategoriesStats();
        this.exportUsersWithAtLeastOneSoldProduct();
    }

    private void exportUsersWithAtLeastOneSoldProduct() {
        List<UserDto> userDtos =
                this.userService.findAllWithAtLeastOneSoldProduct();
        try {
            this.jsonParser.writeToJSON(
                    userDtos, "src/main/resources/files/output/users-and-products.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exportCategoriesStats() {
        List<CategoryWithProductsDto> categoriesStatsDto =
                this.categoryService.findCategoriesWithProducts();
        try {
            this.jsonParser.writeToJSON(
                    categoriesStatsDto, "src/main/resources/files/output/categories-by-products.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exportSellers() {
        List<SellerDto> sellers =
                this.userService.findAllWithSoldProducts();
        try {
            this.jsonParser.writeToJSON(
                    sellers, "src/main/resources/files/output/users-sold-products.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exportProductsInRange() {
        List<ProductInRangeDto> productsInRange =
                this.productService.findAllWithPriceBetween();
        try {
            this.jsonParser.writeToJSON(
                    productsInRange, "src/main/resources/files/output/products-in-range.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void importData() throws IOException {
        this.importUsers();
        this.importCategories();
        this.addFriends();
        this.importProducts();
    }

    private void importProducts() {
        ProductJsonDto[] productDtos = null;
        try {
            productDtos = this.jsonParser.readFromJSON(
                    ProductJsonDto[].class, "/files/input/json/products.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.productService.persistProduct(productDtos);
    }

    private void importCategories() {
        CategoryJsonDto[] categoryDtos = null;
        try {
            categoryDtos = this.jsonParser.readFromJSON(
                    CategoryJsonDto[].class, "/files/input/json/categories.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.categoryService.persistCategories(categoryDtos);
    }

    private void importUsers() {
        UserJsonDto[] userDtos = null;
        try {
            userDtos = this.jsonParser.readFromJSON(
                    UserJsonDto[].class, "/files/input/json/users.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.userService.persistUsers(userDtos);
    }

    private void addFriends() {
        this.userService.addFriends();
    }
}
