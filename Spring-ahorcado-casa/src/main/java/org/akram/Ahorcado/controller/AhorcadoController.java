package org.akram.Ahorcado.controller;

import org.akram.Ahorcado.model.Juego;
import org.akram.Ahorcado.model.UsuarioLoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class AhorcadoController {

	@SuppressWarnings("unused")
	@GetMapping("/partida")
    public ModelAndView juegoAhorcado(HttpServletRequest httpRequst) throws IOException {

        HttpSession sesion = httpRequst.getSession();
        ModelAndView mDV = new ModelAndView();
        Juego juego;
        UsuarioLoginDTO usuarioLogin = null;

            mDV.setViewName("ahorcado");

            if (httpRequst.getSession().getAttribute("palabra") == null) {
                juego = new Juego();
                httpRequst.getSession().setAttribute("palabra", juego);

            } else {
                juego = (Juego) httpRequst.getSession().getAttribute("palabra");
            }

            String resultado = "";

            if (juego.compruebaGanador() || juego.getNumIntentos() == 0) {

                if (juego.compruebaGanador()) {
                    resultado = "HAS GANADO!!";

                } else {

                    if (juego.getNumIntentos() == 0) {
                        resultado = "HAS PERDIDO";
                    }
                }
                httpRequst.getSession().setAttribute("palabra", new Juego());
            } else {

                resultado = "PARTIDA ACTIVA";
            }


            mDV.addObject("requestIp", httpRequst.getLocalAddr());
            mDV.addObject("agenteUsuario", httpRequst.getHeader("User-Agent"));
            mDV.addObject("numIntentos", juego.getNumIntentos());
            mDV.addObject("intentos", juego.getIntentos());
            mDV.addObject("palabra", juego.getPalabraTroceada());
            mDV.addObject("resultado", resultado);


        return mDV;

    }

    @PostMapping("/partida")
    public ModelAndView compruebaJuego(HttpServletRequest httpRequest, String letra) {

        ModelAndView mAV = new ModelAndView();
        Juego juego;
        juego = (Juego) httpRequest.getSession().getAttribute("palabra");

        juego.compruebaLetra(letra);
        httpRequest.getSession().setAttribute("palabra",juego);

        mAV.setViewName("redirect:partida");

        return mAV;
    }
}
