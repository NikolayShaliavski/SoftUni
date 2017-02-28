package com.mvc.framework.handlers;

import com.mvc.framework.annotations.controller.Controller;
import com.mvc.framework.annotations.request.GetMapping;
import com.mvc.framework.annotations.request.PostMapping;
import com.mvc.framework.controllerAction.ControllerActionPair;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class HandlerMappingImpl implements HandlerMapping {

    @Override
    public ControllerActionPair findController(HttpServletRequest request)
            throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ControllerActionPair controllerActionPair = null;
        String uriPath = request.getRequestURI();
        String projectPath = URLDecoder.decode(
                request.getServletContext().getResource("/WEB-INF/classes").getPath(), "UTF-8");
        List<Class> controllers = this.findAllControllers(projectPath);

        for (Class controller : controllers) {
            Method[] methods = controller.getDeclaredMethods();
            for (Method method : methods) {
                String methodPath = this.findMethodPath(method);

                if (methodPath == null) {
                    continue;
                }

                if (this.isPathMatching(uriPath, methodPath)) {
                    controllerActionPair =  new ControllerActionPair(controller, method);
                    this.addPathVariables(controllerActionPair, uriPath, methodPath);
                    return controllerActionPair;
                }
            }
        }
        return controllerActionPair;
    }

    private void addPathVariables(ControllerActionPair controllerActionPair,
                                  String uriPath,
                                  String methodPath) {
        String[] uriTokens = uriPath.split("/");
        String[] methodTokens = methodPath.split("/");
        for (int i = 0; i < methodTokens.length; i++) {
            if (methodTokens[i].startsWith("{") && methodTokens[i].endsWith("}")) {
                String key = methodTokens[i].replaceAll("[{}]", "");
                String value = uriTokens[i];
                controllerActionPair.addPathVariable(key, value);
            }
        }
    }

    private boolean isPathMatching(String uriPath, String methodPath) {
        boolean isMatching = true;
        if (uriPath.endsWith("/") && !methodPath.endsWith("/")) {
            isMatching = false;
        } else {
            String[] uriTokens = uriPath.split("/");
            String[] methodTokens = methodPath.split("/");
            if (uriTokens.length != methodTokens.length) {
                isMatching = false;
            }
            for (int i = 0; i < methodTokens.length; i++) {
                if (methodTokens[i].startsWith("{") && methodTokens[i].endsWith("}")) {
                    continue;
                }
                if (!uriTokens[i].equals(methodTokens[i])) {
                    isMatching = false;
                    break;
                }
            }
        }

        return isMatching;
    }

    private String findMethodPath(Method method) {
        String methodPath = null;
        if (method.isAnnotationPresent(GetMapping.class)) {
            GetMapping getMapping = method.getAnnotation(GetMapping.class);
            methodPath = getMapping.value();
        } else if (method.isAnnotationPresent(PostMapping.class)) {
            PostMapping postMapping = method.getAnnotation(PostMapping.class);
            methodPath = postMapping.value();
        }
        return methodPath;
    }

    private List<Class> findAllControllers (String projectDir) throws ClassNotFoundException {
        List<Class> controllers = new ArrayList<>();
        File subDirectory = new File(projectDir);
        File[] files = subDirectory.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                Class controllerClass = this.getClass(file);
                if (controllerClass == null) {
                    continue;
                }
                if (!controllerClass.isAnnotationPresent(Controller.class)) {
                    continue;
                }
                controllers.add(controllerClass);
            } else if (file.isDirectory()) {
                String filePath = file.getAbsolutePath();
                controllers.addAll(this.findAllControllers(filePath));
            }
        }


        return controllers;
    }

    private Class getClass(File file) throws ClassNotFoundException {
        String absolutePath = file.getAbsolutePath();
        String className =
                absolutePath.split("classes\\\\")[1].replaceAll("\\\\", ".").replace(".class", "");

        Class currentClass = null;
        if (!className.endsWith("DispatcherServlet") && !className.contains("persistence")) {
            currentClass = Class.forName(className);
        }
        return currentClass;
    }
}
