package com.windux.computadorita.Controllers;

import com.windux.Tools.calculadora;
import org.springframework. web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class CalculadoraController {
    @PostMapping("/expresion")
    public String calcularInfix(@RequestBody String infix) {
        String postfix = calculadora.convertirApostfix(infix);
        String resultado = calculadora.resolverExpresionPostfix(postfix);
        return "Postfix:" + postfix + "Resultado:" + resultado;

    }
}

