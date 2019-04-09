/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

 
import com.google.gson.Gson;
import data.tree.Node;
import execute.ID3Algorithm;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
 
import org.apache.jasper.tagplugins.jstl.core.Redirect;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 *
 * @author nmohamed
 */
@Controller
public class staticController {

    private Object getBean(String nombrebean, ServletContext servlet) {
        ApplicationContext contexto = WebApplicationContextUtils.getRequiredWebApplicationContext(servlet);
        Object beanobject = contexto.getBean(nombrebean);
        return beanobject;
    }

    @RequestMapping("/index.htm")
    public ModelAndView facts(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {

        ModelAndView mv = new ModelAndView("index");
        Node tree= null;
		
        try {
                ID3Algorithm id=new ID3Algorithm("C:\\Users\\David\\Downloads\\practica2\\AtributosJuego.txt",
                        "C:\\Users\\David\\Downloads\\practica2\\Juego.txt");
                tree=id.buildTree(); 

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        finally{
            mv.addObject("res",new Gson().toJson(tree));
        }
        return mv;
    } 
 
}
