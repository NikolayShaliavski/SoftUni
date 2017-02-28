package com.mvc.framework.controllerAction;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ControllerActionPair {

    private Class controllerClass;
    private Method actionMethod;
    private Map<String, String> pathVariables;

    public ControllerActionPair(Class controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
        this.pathVariables = new HashMap<>();
    }

    public Class getControllerClass() {
        return this.controllerClass;
    }

    public void setControllerClass(Class controllerClass) {
        this.controllerClass = controllerClass;
    }

    public Method getActionMethod() {
        return this.actionMethod;
    }

    public void setActionMethod(Method actionMethod) {
        this.actionMethod = actionMethod;
    }

    public void addPathVariable(String key, String value) {
        this.pathVariables.put(key, value);
    }

    public String getPathVariable(String key) {
        return this.pathVariables.get(key);
    }
}
