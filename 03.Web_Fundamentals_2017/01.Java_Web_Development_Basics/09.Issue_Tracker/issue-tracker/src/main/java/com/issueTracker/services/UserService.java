package com.issueTracker.services;

import com.issueTracker.models.bindingModels.user.LoginUserModel;
import com.issueTracker.models.bindingModels.user.RegisterUserModel;
import com.issueTracker.models.viewModels.user.LoggedUserModel;

public interface UserService {

    void register(RegisterUserModel registerUserModel);

    LoggedUserModel findLoggedUser(LoginUserModel loginUserModel);
}
