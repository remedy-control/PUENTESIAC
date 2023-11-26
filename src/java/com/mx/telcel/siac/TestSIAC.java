/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.telcel.siac;


import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import org.apache.log4j.Logger;

/**
 *
 * @author iHector
 */
public class TestSIAC {

    private static final Logger log = Logger.getLogger(TestSIAC.class);

    public static void siac() throws Exception {
        String vsWsdlURL = "http://10.119.155.73:8080/WS_SIACPUENTE/WS_SIAC_FOLIOS?wsdl";
        String vsNameSpace = "http://folios.ws.telcel.mx.com/";
        String vsServiceName = "WS_SIAC_FOLIOS";//secureSiacWsService
        QName voServiceQN = new QName(vsNameSpace, vsServiceName);
        URL voURL = new URL(vsWsdlURL);
        Service service = Service.create(voURL, voServiceQN);
//        WSSIACFOLIOS wsSiac = service.getPort(WSSIACFOLIOS.class);

        //wsSiac.createFolioCPD("INC000000974101", "PRUEBA DOS  DE WS", 1, "REGION 6", "57000", "COL. BENITO JUAREZ", "25/06/2015", "EX255385.telcel.com.mx", "R1-BCAL-INGENIERIA", "COMENTARIOS DE HOLA MUNDO");
  //        wsSiac.createFolioCPD("INC000000974123", "PRUEBA DOS  DE WS", 1, "REGION 4", "57000", "COL. BENITO JUAREZ", "25/06/2015", "EX255385.telcel.com.mx", "R1-BCAL-INGENIERIA", "COMENTARIOS DE HOLA MUNDO");
        
        //wsSiac.createFolio("INC000000971600","TICKET DE PRUEBA RCONTROL QA","DESCRIPTION. TT DE PRUEBA HUAWEI-REMEDY",1,"REGION 5","NA1042L2100","R5-OCCI-NAYARIT","05/05/2015","temip","R9-OPER-OPTIMIZACION RAN");

    }

    public static void main(String[] psParametros) {
        try {
            TestSIAC.siac();
        } catch (Exception exc) {
            log.debug("Error");
            log.error(exc);
            if (exc.getCause() != null) {
                log.error(exc.getCause());
                exc.getCause().printStackTrace();
            }
        }
    }//main

}
