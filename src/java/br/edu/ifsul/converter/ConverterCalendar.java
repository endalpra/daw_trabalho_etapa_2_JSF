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
@FacesConverter(value="converterCalendar")
public class ConverterCalendar implements Converter, Serializable{
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    //metodo que converte da tela para objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
    if(string == null){
        return null;
    }   
        Calendar retorno = Calendar.getInstance();
        try{
            retorno.setTime(sdf.parse(string));
            return retorno;
        }catch(Exception e){
            return null;
        }
    }

    //metodo que converte do objeto para a tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Calendar obj = (Calendar) o;
        return sdf.format(obj.getTime());
    }
    
}
