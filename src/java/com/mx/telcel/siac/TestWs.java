/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.telcel.siac;
      //com.telcel.ws.siac
import com.infomedia.utils.PropertyLoader;
import com.mx.telcel.ws.folios.Folio;
import com.mx.telcel.ws.folios.FoliosWServiceImpl;

import java.net.URL;
import java.util.Properties;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import org.apache.log4j.Logger;

/**
 *
 * @author iHector
 */
public class TestWs {
    
    private static final Logger log = Logger.getLogger(TestWs.class);

    public static void siac() throws Exception {
        /*String vsWsdlURL = "http://10.119.155.84:8080/WS_SIACPUENTE/WS_SIAC_FOLIOS?wsdl";
        String vsNameSpace = "http://folios.ws.telcel.mx.com/";
        String vsServiceName = "WS_SIAC_FOLIOS";//secureSiacWsService
        QName voServiceQN = new QName(vsNameSpace, vsServiceName);
        URL voURL = new URL(vsWsdlURL);
        Service service = Service.create(voURL, voServiceQN);*/
        Properties prop = PropertyLoader.load("siacpuente.properties");
        QName voServiceQN = new QName(prop.getProperty("NSPACE_SIAC"), prop.getProperty("SNAME_SIAC"));
        URL voURL = new URL(prop.getProperty("URL_SIAC"));
        Service service = Service.create(voURL, voServiceQN);
        FoliosWServiceImpl wsSiac = service.getPort(FoliosWServiceImpl.class);
        //WSSIACFOLIOS wsSiac = service.getPort(WSSIACFOLIOS.class);
        Folio folio = wsSiac.createFolio("INC000000971600",
                "TICKET DE PRUEBA RCONTROL QA",
                "DESCRIPTION. TT DE PRUEBA HUAWEI-REMEDY",
                1,
                "REGION 5",
                "NA1042L2100",
                "R5-OCCI-NAYARIT",
                "05/05/2015",
                "temip",
                "R9-OPER-OPTIMIZACION RAN",
                 1);   
        System.out.println("Folio SIAC: " +folio.getFolio());
    }
    public static void main(String[] psParametros) {
       try {
            TestWs.siac();
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
