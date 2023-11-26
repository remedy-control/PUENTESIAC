
import com.mx.telcel.siac.WS_SIAC_FOLIOS;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class test {
        public static void main(String[] args) throws Exception {
        String id="INC000023738083";
        String ultimocomentario="Ultimo comentario desde Java";
        String foliosiac="11210338";
        long folio=11210338;
        String estatus="vigente";
        String fecha="19/11/2023";
        String workinfo="-FOLIO_SIAC: " + folio + "\\n"+" -ESTATUS_SIAC: " + estatus+ "\\n"+" -FECHA_PROPUESTA_SIAC: " + fecha;
        System.out.println("INFORMACION EN PRUEBA JAVA: "+workinfo);
        WS_SIAC_FOLIOS fs= new WS_SIAC_FOLIOS();
        
        fs.actualizaIncidente(id, workinfo, ultimocomentario, foliosiac);
        
    }
}
