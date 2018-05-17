package com.zachfrew.hellospring.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping(value = "")
    @ResponseBody
    public String index(HttpServletRequest request) {
        String name = request.getParameter("name");
        if (name == null) {
            return "Hello, world!";
        }
        return "Hello, " + name;
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    @ResponseBody
    public String helloForm() {
        String html =
                "<form style='text-align: center; padding-top: 1.5rem;' method='post'>" +
                    "<input type='text' name='name' />" +
                    "<select name='language'>" +
                        "<option value='english'>English</option>" +
                        "<option value='spanish'>Spanish</option>" +
                        "<option value='german'>German</option>" +
                        "<option value='italian'>Italian</option>" +
                        "<option value='french'>French</option>" +
                    "</select>" +
                    "<input type='submit' value='Greet me!' />" +
                "</form>";
        return html;
    }

    public static String createMessage(String name, String language) {
        String greetingInLanguage = "";

        switch (language) {
            case "english":
                greetingInLanguage = "Hello";
                break;
            case "spanish":
                greetingInLanguage = "Hola";
                break;
            case "german":
                greetingInLanguage = "Hallo";
                break;
            case "italian":
                greetingInLanguage = "Ciao";
                break;
            case "french":
                greetingInLanguage = "Bonjour";
                break;
            default:
                greetingInLanguage = "Hello";
                break;
        }

        String greeting = greetingInLanguage + ", " + name + "!";
        return greeting;
    }

    @RequestMapping(value = "hello", method = RequestMethod.POST)
    @ResponseBody
    public String helloPost(HttpServletRequest request){
        String name = request.getParameter("name");
        String language = request.getParameter("language");
        String userGreeting = createMessage(name, language);
        return "<h1 style='text-align: center; padding-top: 1.5rem;'>" + userGreeting + "</h1>";
    }

    @RequestMapping(value = "hello/{name}")
    @ResponseBody
    public String helloURLSegment(@PathVariable String name) {
        return "Hello, " + name;
    }

    @RequestMapping(value = "goodbye")
    public String goodbye() {
        return "redirect:/";
    }
}
