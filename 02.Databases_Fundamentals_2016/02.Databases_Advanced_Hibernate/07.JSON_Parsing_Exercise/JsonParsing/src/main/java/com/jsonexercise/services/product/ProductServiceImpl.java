package com.jsonexercise.services.product;

import com.jsonexercise.domain.dto.exportDto.ProductInRangeDto;
import com.jsonexercise.domain.dto.importDto.ProductJsonDto;
import com.jsonexercise.domain.entities.Category;
import com.jsonexercise.domain.entities.Product;
import com.jsonexercise.domain.entities.User;
import com.jsonexercise.io.parsers.modelParsers.ModelParser;
import com.jsonexercise.repositories.CategoryRepository;
import com.jsonexercise.repositories.ProductRepository;
import com.jsonexercise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

	private final ModelParser modelParser;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository,
                              UserRepository userRepository,
                              CategoryRepository categoryRepository,
							  ModelParser modelParser) {
	 	this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
		this.modelParser = modelParser;
	}

	@Override
	public void persistProduct(ProductJsonDto[] productDtos) {
        List<Product> products =
                this.modelParser.convertToEntity(
                        productDtos, Product.class);
        this.addBuyerAndSeller(products);
        this.addCategories(products);
        this.productRepository.save(products);
	}

    @Override
    public List<ProductInRangeDto> findAllWithPriceBetween() {
        List<Product> products = this.productRepository.findAllWithPriceBetween();
        List<ProductInRangeDto> productDtos =
                this.modelParser.convertToDto(products, ProductInRangeDto.class);
        return productDtos;
    }

    private void addBuyerAndSeller(List<Product> products) {
        Long usersCount = this.userRepository.count();
        for (Product product : products) {
            Long buyerRandomId =
                    ThreadLocalRandom.current().nextLong(1, usersCount + 1);
            Long sellerRandomId =
                    ThreadLocalRandom.current().nextLong(1, usersCount + 1);
            User buyer =
                    this.userRepository.findOne(buyerRandomId);
            User seller =
                    this.userRepository.findOne(sellerRandomId);
            if (product.getName().length() > 8)
            {
                product.setBuyer(buyer);
            }
            product.setSeller(seller);
        }
    }

    private void addCategories(List<Product> products) {
        Long categoriesCount = this.categoryRepository.count();
        for (Product product : products) {
            Integer productCategories =
                    ThreadLocalRandom.current().nextInt(1, 10);
            for (int i = 0; i < productCategories; i++) {
                Long categoryRandomId =
                        ThreadLocalRandom.current().nextLong(1, categoriesCount + 1);
                Category category =
                        this.categoryRepository.findOne(categoryRandomId);
                //product.addCategory(category);
                category.addProduct(product);
            }
        }
    }
}