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
        String errorTitle = "Error";
        String errorCaption = "Error";
        String errorMsg = "An error occurred while making a request to the server";
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                errorMsg = "You are not authorized to view this page";
            }
            else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                errorMsg = "Page not found";
            }
            errorCaption += " (" + statusCode + ")";
        }
        errorPage.addObject("errorTitle", errorTitle);
        errorPage.addObject("errorCaption", errorCaption);
        errorPage.addObject("errorMsg", errorMsg);
        return errorPage;
    }
}