package ru.bootstrap_demo.exercise_2314.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorControllerImp implements ErrorController {
    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView errorPage = new ModelAndView("errorpage");
        String errorTitle = "Ошибка";
        String errorCaption = "Ошибка";
        String errorMsg = "При выполнении запроса к серверу произошла ошибка";
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                errorMsg = "У Вас нет прав на просмотр этой страницы";
            }
            else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                errorMsg = "Страница не найдена";
            }
            errorCaption += " (" + statusCode + ")";
        }
        errorPage.addObject("errorTitle", errorTitle);
        errorPage.addObject("errorCaption", errorCaption);
        errorPage.addObject("errorMsg", errorMsg);
        return errorPage;
    }
}