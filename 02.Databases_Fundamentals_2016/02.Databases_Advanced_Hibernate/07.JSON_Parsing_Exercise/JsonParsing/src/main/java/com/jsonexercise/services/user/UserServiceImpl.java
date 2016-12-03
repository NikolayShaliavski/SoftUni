package com.jsonexercise.services.user;

import com.jsonexercise.domain.dto.exportDto.SellerDto;
import com.jsonexercise.domain.dto.exportDto.UserDto;
import com.jsonexercise.domain.dto.importDto.UserJsonDto;
import com.jsonexercise.domain.entities.User;
import com.jsonexercise.io.parsers.modelParsers.ModelParser;
import com.jsonexercise.repositories.UserRepository;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private ModelParser modelParser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ModelParser modelParser) {
        this.userRepository = userRepository;
        this.modelParser = modelParser;
    }

    public void persistUsers(UserJsonDto[] userDtos) {
        List<User> users =
                this.modelParser.convertToEntity(userDtos, User.class);
        this.userRepository.save(users);
    }

    @Override
    public void addFriends() {
        List<User> allUsers =
                this.userRepository.findAll();
        for (User user : allUsers) {
            Integer friendsCount = ThreadLocalRandom.current().nextInt(1, 10);

            for (int i = 0; i < friendsCount; i++) {
                Long friendId = ThreadLocalRandom.current().nextLong(1, allUsers.size() + 1);
                User friend = this.userRepository.findOne(friendId);
                user.addFriend(friend);
            }
            //here we may not save user into DB, because it already persist there
            //and when we add friends, this data inserts automatically into table users_friends
            //this.userRepository.save(user);
        }
    }

    @Override
    public List<SellerDto> findAllWithSoldProducts() {
        List<User> sellers = this.userRepository.findAllWithSoldProducts();
        List<SellerDto> sellerDtos =
                this.modelParser.convertToDto(sellers, SellerDto.class);
        return sellerDtos;
    }

    @Override
    public List<UserDto> findAllWithAtLeastOneSoldProduct() {
        List<User> users = this.userRepository.findAllWithAtLeastOneSoldProduct();
        PropertyMap<User, UserDto> propertyMap = new PropertyMap<User, UserDto>() {
                    @Override
                    protected void configure() {
                        map(source.getSoldProducts().size(),
                                destination.getSoldProducts().getCount());
                        //map(source.getSoldProducts(), destination.getSoldProducts());
                    }
                };
        List<UserDto> userDtos =
                this.modelParser.convertToDto(users, UserDto.class, propertyMap);
        return userDtos;
    }
}