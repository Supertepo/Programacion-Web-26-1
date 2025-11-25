package mx.ipn.upiicsa.web.controlacceso.external.mvc.controller;

import jakarta.validation.Valid;
import mx.ipn.upiicsa.web.controlacceso.internal.bs.implemet.LoginBs;
import mx.ipn.upiicsa.web.controlacceso.external.mvc.dto.LoginDto;
import mx.ipn.upiicsa.web.controlacceso.internal.input.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("loginDto", new LoginDto());
        return "index";
    }

    //@PostMapping("/")
    //public String login(@RequestParam String username, @RequestParam String password, Model model){
    //    System.out.println("USERNAME: " + username);
    //    System.out.println("PASSWORD: " + password);
    //    return "index";
    //}

    @PostMapping("/")
    public String login(@Valid @ModelAttribute LoginDto loginDto, BindingResult bindingResult, Model model){
        System.out.println("USERNAME: " + loginDto.getUsername());
        System.out.println("PASSWORD: " + loginDto.getPassword());

        for(ObjectError errorList : bindingResult.getAllErrors()) {
            System.out.println("ERROR: "+errorList.getObjectName()+" - "+errorList.getCode()+" - "+errorList.getDefaultMessage());
        }
        loginService.login(loginDto);
        return "index";
    }
}
