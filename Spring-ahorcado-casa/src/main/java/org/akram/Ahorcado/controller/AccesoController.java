package org.akram.Ahorcado.controller;

import org.akram.Ahorcado.model.Juego;
import org.akram.Ahorcado.model.UsuarioLoginDTO;
import org.akram.Ahorcado.security.SecurityConfig;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/acceso")
public class AccesoController {

    @GetMapping("/login")
    public ModelAndView devuelveFormularioLogin(HttpServletRequest httpRequest){

        ModelAndView mDV = new ModelAndView();
        UsuarioLoginDTO usuarioLogin = new UsuarioLoginDTO();
        String agenteUsuario = httpRequest.getHeader("User-Agent");//Devuelve el valor de la cabecera de la solicitud especificada como una cadena string.
        String requestIp = httpRequest.getRemoteAddr();//Devuelve la dirección del Protocolo de Internet (IP) del cliente o del último proxy que envió la solicitud.
        httpRequest.getSession().setAttribute("usuarioSesion", null);

        mDV.addObject("usuarioLogin", usuarioLogin);
        mDV.addObject("agenteUsuario", agenteUsuario);
        mDV.addObject("requestIp", requestIp);

        mDV.setViewName("login");
        return mDV;
    }

    @GetMapping("logout")
    public ModelAndView logout(HttpServletRequest httpRequest){

        ModelAndView mDV = new ModelAndView();
        httpRequest.getSession().invalidate();
        mDV.setViewName("redirect:login");

        return mDV;
    }

	@PostMapping("/login")
    public ModelAndView recibeCredencialesLogin(
            @Validated UsuarioLoginDTO usuarioComp,
            HttpServletRequest request,
            HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();
        if (usuarioComp.getUsuario().equals("akram") && usuarioComp.getClave().equals("akram")) {
			
        	Cookie[] cookies = request.getCookies();

            Cookie usuario = new Cookie("usuario", usuarioComp.getUsuario());
            response.addCookie(usuario);
            
            Cookie partidas= new Cookie("contador_partidas","0");
            Cookie perdidas= new Cookie("contador_partidas_perdidas","0");
            Cookie ganadas = new Cookie("contador_partidas_ganadas","0");
            
            response.addCookie(partidas);
            response.addCookie(ganadas);
            response.addCookie(perdidas);

            request.getSession().setAttribute("_id", "Bienvenido por primera vez " + usuario.getValue());
            request.getSession().setAttribute("_estadisticas" , "Llevas "+partidas.getValue()+ " partida(s) jugadas a este sitio; de las cuales "+ganadas.getValue()+
                    							" ganadas y "+perdidas.getValue()+" perdidas.");
        	
		}  

        return mav;
    }

}
