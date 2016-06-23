package br.edu.ifsul.converter;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Agencia;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Érico
 */
@FacesConverter(value="converterAgencia")
public class ConverterAgencia implements Serializable, Converter{
    public ConverterAgencia(){        
    }

    //Converte da tela para o objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string == null || string.equals("Selecione um registro!")){
            return null;
        }
        return EntityManagerUtil.getEntityManager().find(Agencia.class, Integer.parseInt(string));
    }

    //Converte do objeto para a tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
      if(o == null){
          return null;
      }
      Agencia obj = (Agencia) o;
      return obj.getId().toString();
    }
    
}
