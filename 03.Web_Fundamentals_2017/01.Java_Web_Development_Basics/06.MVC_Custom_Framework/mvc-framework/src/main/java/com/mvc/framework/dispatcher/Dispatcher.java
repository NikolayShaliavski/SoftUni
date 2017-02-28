package com.mvc.framework.dispatcher;

import com.mvc.framework.controllerAction.ControllerActionPair;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;

public interface Dispatcher {

    ControllerActionPair dispatchRequest(HttpServletRequest request);

    String dispatchAction(HttpServletRequest request,
                          HttpServletResponse response,
                          ControllerActionPair controllerActionPair) throws
            NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;
}
