package br.edu.ifsul.converter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Érico
 */
@FacesConverter(value="converterAtiva")
public class ConverterAtiva implements Converter, Serializable{
    
    //metodo que converte da tela para objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
    if(string == null){
        return null;
    }   
         try{
             if(string.equalsIgnoreCase("sim")){
                 return true;
             }else{
                 return false;
             }            
        }catch(Exception e){
            return null;
        }
    }

    //metodo que converte do objeto para a tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o.equals(true)){
            return "Sim";
        }else{
            return "Não";
        }
    }
    
}
