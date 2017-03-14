//package com.softuni.store.filters;
//
//import com.softuni.store.models.viewModels.LoggedUserView;
//import com.softuni.store.services.GameService;
//
//import javax.ejb.Stateless;
//import javax.inject.Inject;
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@Stateless
//@WebFilter("/*")
//public class GameFilter implements Filter {
//
//    @Inject
//    private GameService gameService;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        HttpSession session = ((HttpServletRequest) req).getSession();
//        LoggedUserView loggedUser = (LoggedUserView) session.getAttribute("loggedUser");
////        if (loggedUser == null) {
////            ((HttpServletResponse) res).sendRedirect("/");
////            return;
////        }
////        if (loggedUser.getRole() != Role.ADMIN) {
////            String uri = ((HttpServletRequest) req).getRequestURI();
////            int uriLastElementIndex = uri.lastIndexOf('=');
////            String filterType = uri.substring(uriLastElementIndex);
////            List<GameHomeView> games;
////            if (filterType.equalsIgnoreCase("All")) {
////                games = this.gameService.getAllGames();
////            } else if (filterType.equalsIgnoreCase("Owned")) {
////                games = this.gameService.getUserOwnedGames(loggedUser.getEmail());
////            }
////        }
//        chain.doFilter(req, res);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
