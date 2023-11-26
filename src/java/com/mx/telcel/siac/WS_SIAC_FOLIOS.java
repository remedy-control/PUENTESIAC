package com.mx.telcel.siac;

import Controlador.ControladorToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.infomedia.utils.PropertyLoader;
import com.mx.telcel.ws.folios.Folio;
import com.mx.telcel.ws.folios.FoliosWServiceImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.apache.log4j.Logger;
import java.util.Properties;
import javax.jws.WebService;
import javax.jws.WebParam;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import okhttp3.OkHttpClient;
import okhttp3.*;

/**
 *
 * @author iHector
 */
@WebService(serviceName = "WS_SIAC_FOLIOS",
        portName = "SecureSiacWs",
        targetNamespace = "http://folios.ws.telcel.mx.com/")
public class WS_SIAC_FOLIOS {

    private static final Logger log = Logger.getLogger(WS_SIAC_FOLIOS.class);
    Properties prop;

    public WS_SIAC_FOLIOS() {
        prop = PropertyLoader.load("siacpuente.properties");
    }

    /**
     * This is a sample web service operation
     *
     * @param troubleTicketIdEp
     * @param titleEp
     * @param descriptionEp
     * @param statusEp
     * @param reRegionEp
     * @param siSiteIdEp
     * @param siteName
     * @param createTimeEp
     * @param submitterEp
     * @param supportGroup
     * @param tipo_incidente
     */
    public void CreateFolio(@WebParam(name = "troubleTicketIdEp") String troubleTicketIdEp,
            @WebParam(name = "titleEp") String titleEp,
            @WebParam(name = "descriptionEp") String descriptionEp,
            @WebParam(name = "statusEp") int statusEp,
            @WebParam(name = "reRegionEp") String reRegionEp,
            @WebParam(name = "siSiteEp") String siSiteIdEp,
            @WebParam(name = "siteName") String siteName,
            @WebParam(name = "createTimeEp") String createTimeEp,
            @WebParam(name = "submitterEp") String submitterEp,
            @WebParam(name = "supportGroup") String supportGroup,
            @WebParam(name = "tipo_incidente") int tipo_incidente) {
        try {
            //String vsWsdlURL = prop.getProperty("URL_SIAC");
            //String vsNameSpace = "http://folios.ws.telcel.mx.com/";
            //String vsServiceName = "secureSiacWsService";//secureSiacWsService
            QName voServiceQN = new QName(prop.getProperty("NSPACE_SIAC"), prop.getProperty("SNAME_SIAC"));

            URL voURL = new URL(prop.getProperty("URL_SIAC"));
            Service service = Service.create(voURL, voServiceQN);
            log.debug(service);
            FoliosWServiceImpl siacWS = service.getPort(FoliosWServiceImpl.class);
            log.debug(siacWS);

            Folio folio = siacWS.createFolio(troubleTicketIdEp,
                    titleEp,
                    descriptionEp,
                    statusEp,
                    reRegionEp,
                    siSiteIdEp,
                    siteName,
                    createTimeEp,
                    submitterEp,
                    supportGroup,
                    tipo_incidente);
            if (null != folio && folio.getFolio() > 0) {
                String folioSiac = "";
                String workInfo1 = "";
                String workInfo2 = "";
                folioSiac += "" + folio.getFolio();
                workInfo1 += "-FOLIO_SIAC:" + folio.getFolio();
                workInfo1 += "\\n" + "-ESTATUS_SIAC:" + folio.getEstatus();
                workInfo1 += "\\n" + "-FECHA_PROPUESTA_SIAC:" + folio.getFechaPropuesta();
                workInfo1 += "\\n" + "-FECHA_ALTA_SIAC:" + folio.getFechaAlta();
                workInfo1 += "\\n" + "-FECHA_ULTIMA_MODIFICACION_SIAC:" + folio.getFechaModificacion();
                workInfo2 += "ULTIMO_COMENTARIO_SIAC: " + folio.getUltimoComentario();
                log.debug(workInfo1);
                log.debug(workInfo2);
                //log.info("INCIDENTE DE REMEDY: " + troubleTicketIdEp);
                log.info("FOLIO SIAC: " + folioSiac);
                log.info("Incidente: " + troubleTicketIdEp
                        + "\nTitulo: " + titleEp
                        + "\nDesc: " + descriptionEp
                        + "\nEstado: " + statusEp
                        + "\nRegion: " + reRegionEp
                        + "\nCI: " + siSiteIdEp
                        + "\nCIName: " + siteName
                        + "\nFechaCreacion: " + createTimeEp
                        + "\nRemitente: " + submitterEp
                        + "\nGrupoSoporte: " + supportGroup
                        + "\nTipoIncidente: " + tipo_incidente);
                actualizaIncidente(troubleTicketIdEp, workInfo1, workInfo2, folioSiac);
            } else {
                log.error("No se genero ningun folio");
                log.error("Incidente: " + troubleTicketIdEp
                        + "\nTitulo: " + titleEp
                        + "\nDesc: " + descriptionEp
                        + "\nEstado: " + statusEp
                        + "\nRegion: " + reRegionEp
                        + "\nCI: " + siSiteIdEp
                        + "\nCIName: " + siteName
                        + "\nFechaCreacion: " + createTimeEp
                        + "\nRemitente: " + submitterEp
                        + "\nGrupoSoporte: " + supportGroup
                        + "\nTipoIncidente: " + tipo_incidente);
            }

        } catch (MalformedURLException exc) {
            exc.printStackTrace();
            log.error(exc.getMessage());
        }
    }

    /**
     * This is a sample web service operation
     *
     * @param troubleTicketIdEp
     * @param titleEp
     * @param statusEp
     * @param reRegionEp
     * @param postalCodeEp
     * @param neighborhoodName
     * @param createTimeEp
     * @param submitterEp
     * @param supportGroup
     * @param commentsEp
     */
    public void CreateFolioCPD(
            @WebParam(name = "trouble_ticket_id_ep") String troubleTicketIdEp,
            @WebParam(name = "title_ep") String titleEp,
            @WebParam(name = "status_ep") int statusEp,
            @WebParam(name = "re_region_ep") String reRegionEp,
            @WebParam(name = "postal_code_ep") String postalCodeEp,
            @WebParam(name = "neighborhood_name") String neighborhoodName,
            @WebParam(name = "create_time_ep") String createTimeEp,
            @WebParam(name = "submitter_ep") String submitterEp,
            @WebParam(name = "support_group") String supportGroup,
            @WebParam(name = "comments_ep") String commentsEp
    ) {
        try {

            QName voServiceQN = new QName(prop.getProperty("NSPACE_SIAC"), prop.getProperty("SNAME_SIAC"));

            URL voURL = new URL(prop.getProperty("URL_SIAC"));
            Service service = Service.create(voURL, voServiceQN);
            log.debug(service);
            FoliosWServiceImpl siacWS = service.getPort(FoliosWServiceImpl.class);
            log.debug(siacWS);

            Folio folio = siacWS.createFolioCP(troubleTicketIdEp,
                    titleEp,
                    statusEp,
                    reRegionEp,
                    postalCodeEp,
                    neighborhoodName,
                    createTimeEp,
                    submitterEp,
                    supportGroup,
                    commentsEp);
            if (null != folio && folio.getFolio() > 0) {
                String folioSiac = "";
                String workInfo1 = "";
                String workInfo2 = "";

                folioSiac = "" + folio.getFolio();
                workInfo1 += "-FOLIO_SIAC: " + folio.getFolio();
                workInfo1 += "\\n" + "-ESTATUS_SIAC: " + folio.getEstatus();
                workInfo1 += "\\n" + "-FECHA_PROPUESTA_SIAC: " + folio.getFechaPropuesta();
                workInfo1 += "\\n" + "-FECHA_ALTA_SIAC: " + folio.getFechaAlta();
                workInfo1 += "\\n" + " -FECHA_ULTIMA_MODIFICACION_SIAC: " + folio.getFechaModificacion();
//               String workInfo1 = "-FOLIO_SIAC: " + folio.getFolio() +" -ESTATUS_SIAC: " + folio.getEstatus()+ " -FECHA_PROPUESTA_SIAC: " + folio.getFechaPropuesta().toString() + " -FECHA_ALTA_SIAC: " + folio.getFechaAlta().toString() + " -FECHA_ULTIMA_MODIFICACION_SIAC: " + folio.getFechaModificacion().toString();
                log.info(workInfo1);
                log.debug(workInfo2);
                //log.info("INCIDENTE DE REMEDY: " + troubleTicketIdEp);
                log.info("**************");
                log.info("FOLIO SIAC: " + folioSiac);
                log.info("Incidente: " + troubleTicketIdEp
                        + "\nTitulo: " + titleEp
                        + "\nEstado: " + statusEp
                        + "\nRegion: " + reRegionEp
                        + "\nZIP: " + postalCodeEp
                        + "\nNeighborhoodName: " + neighborhoodName
                        + "\nFechaCreacion: " + createTimeEp
                        + "\nRemitente: " + submitterEp
                        + "\nGrupoSoporte: " + supportGroup
                        + "\nComentarios: " + commentsEp);
                actualizaIncidente(troubleTicketIdEp, workInfo1, workInfo2, folioSiac);
            } else {
                log.info("**************");
                log.error("NO SE GENERO NINGUN FOLIO SIAC");
                log.error("Incidente: " + troubleTicketIdEp
                        + "\nTitulo: " + titleEp
                        + "\nEstado: " + statusEp
                        + "\nRegion: " + reRegionEp
                        + "\nZIP: " + postalCodeEp
                        + "\nNeighborhoodName: " + neighborhoodName
                        + "\nFechaCreacion: " + createTimeEp
                        + "\nRemitente: " + submitterEp
                        + "\nGrupoSoporte: " + supportGroup
                        + "\nComentarios: " + commentsEp);
            }

        } catch (Exception exc) {
            exc.printStackTrace();
            log.error(exc.getMessage());
        }
    }

    public void actualizaIncidente(String idIncidente, String workInfo, String ultimoComentario, String folioSiac) {

        log.debug(folioSiac);
        log.debug(idIncidente);
        log.debug(workInfo);
        log.debug(ultimoComentario);

        ControladorToken CT = new ControladorToken();
//        URL urlRemedy;
//        URLConnection conn;
//        BufferedReader reader;
//        String LINEA;
//        String urlParametros = "";
//        String actualizacion = "";
//**************************ingresa el folio y las fechas del SIAC*************************************
//            urlParametros += prop.getProperty("CLIENTE_ARS");
//            urlParametros += prop.getProperty("FORM_HPDWORKLOG");
//            urlParametros += prop.getProperty("DAT_HPDWORKLOG1");
//            urlParametros += " '1000000151'='" + workInfo + "' '1000000161'='" + idIncidente + "'";
//
//            urlRemedy = new URL(prop.getProperty("URL_INSERT"));
//            conn = urlRemedy.openConnection();
//            conn.setDoOutput(true);
//
//            log.debug(urlRemedy + urlParametros);
//
//            try ( OutputStreamWriter write = new OutputStreamWriter(conn.getOutputStream())) {
//                write.write(urlParametros);
//                write.flush();
//                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                while ((LINEA = reader.readLine()) != null) {
//                    if (LINEA.startsWith("<NUEVO>")) {
//                        int finalConsulta = LINEA.indexOf("</NUEVO>");
//                        actualizacion = LINEA.substring(7, finalConsulta);
//                        log.info(LINEA);
//                    } else if (LINEA.startsWith("<ERROR>")) {
//                        int finalError = LINEA.indexOf("</ERROR>");
//                        actualizacion = LINEA.substring(7, finalError);
//                        log.error(LINEA);
//                    }
//                }
//            }catch(IOException ex){
//                System.out.println("ERROR: "+ex);
//            }
//            log.info(actualizacion);
//            urlParametros = "";
//
//            //***********************Ingresa el utlimo comentario****************************************
//            urlParametros += prop.getProperty("CLIENTE_ARS");
//            urlParametros += prop.getProperty("FORM_HPDWORKLOG");
//            urlParametros += prop.getProperty("DAT_HPDWORKLOG1");
//            urlParametros += " '1000000151'='" + ultimoComentario + "' '1000000161'='" + idIncidente + "'";
//            urlRemedy = new URL(prop.getProperty("URL_INSERT"));
//            conn = urlRemedy.openConnection();
//            conn.setDoOutput(true);
//            log.debug(urlRemedy + urlParametros);
//            try ( OutputStreamWriter write = new OutputStreamWriter(conn.getOutputStream())) {
//                write.write(urlParametros);
//                write.flush();
//                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                while ((LINEA = reader.readLine()) != null) {
//                    if (LINEA.startsWith("<NUEVO>")) {
//                        int finalConsulta = LINEA.indexOf("</NUEVO>");
//                        actualizacion = LINEA.substring(7, finalConsulta);
//                        log.info(actualizacion);
//                    } else if (LINEA.startsWith("<ERROR>")) {
//                        int finalError = LINEA.indexOf("</ERROR>");
//                        actualizacion = LINEA.substring(7, finalError);
//                        log.error(actualizacion);
//                    }
//                }
//            }
//            log.info(actualizacion);
//            urlParametros = "";
//**********Inserta el numero del folio SIAC, dentro del campo oculto del formulario HPD:Help Desk***************
//            urlParametros += prop.getProperty("CLIENTE_ARS");
//            urlParametros += prop.getProperty("FORM_HELPDESK");
//            urlParametros += "&cID=" + idIncidente + "&cColumnas='536871091'='" + folioSiac + "'";
//            log.info("Este es el log del folio siac: " + urlParametros);
//            urlRemedy = new URL(prop.getProperty("URL_UPDATE"));
//            conn = urlRemedy.openConnection();
//            conn.setDoOutput(true);
//            try ( OutputStreamWriter write = new OutputStreamWriter(conn.getOutputStream())) {
//                write.write(urlParametros);
//                write.flush();
//                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                while ((LINEA = reader.readLine()) != null) {
//                    if (LINEA.startsWith(">OK<")) {
//                        actualizacion = "SE INSERTA EL FOLIO SIAC CON NUMERO: " + folioSiac;//LINEA.substring(7, finalConsulta);
//                        log.info(actualizacion);
//                    } else if (LINEA.startsWith("<ERROR>")) {
//                        int finalError = LINEA.indexOf("</ERROR>");
//                        actualizacion = LINEA.substring(7, finalError);
//                        log.error(actualizacion);
//                    }
//                }
//            }

//**************************ingresa el folio y las fechas del SIAC*************************************
        try {
            log.info("Esta es la información del folio: " + workInfo);
            System.out.println("Esta es la información del folio: " + workInfo);

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(mediaType, "{\r\n    \"values\":{\r\n        \"Work Log Type\":\"2000\",\r\n        \"Secure Work Log\":\"0\",\r\n        \"View Access\":\"1\",\r\n        \"Detailed Description\":\"" + workInfo + "\",\r\n        \"Incident Number\":\"" + idIncidente + "\"\r\n    }\r\n}");
            Request request = new Request.Builder()
                    .url("http://100.127.4.40:8008/api/arsys/v1/entry/HPD:WorkLog?fields=values(1)")
                    .method("POST", body)
                    .addHeader("Authorization", "AR-JWT " + CT.token() + "")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept-Charset", "UTF-8")
                    .build();
            Response response = client.newCall(request).execute();
            Response httpResponse = client.newCall(request).execute();
            int responseCode = httpResponse.code();
            log.info("este es el codigo de la respuesta html: " + responseCode);
            
//            //***********************Ingresa el ultimo comentario****************************************
            //Se valida primer comentario y se sigue con el ultimo
            if (responseCode == 201) {
                log.info("1. Se han insertado los datos en el formulario HPD:Worklog: " + response.toString());
                RequestBody body2 = RequestBody.create(mediaType, "{\r\n    \"values\":{\r\n        \"Work Log Type\":\"2000\",\r\n        \"Secure Work Log\":\"0\",\r\n        \"View Access\":\"1\",\r\n        \"Detailed Description\":\"" + ultimoComentario + "\",\r\n        \"Incident Number\":\"" + idIncidente + "\"\r\n    }\r\n}");
                Request request2 = new Request.Builder()
                        .url("http://100.127.4.40:8008/api/arsys/v1/entry/HPD:WorkLog?fields=values(1)")
                        .method("POST", body2)
                        .addHeader("Authorization", "AR-JWT " + CT.token() + "")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept-Charset", "UTF-8")
                        .build();
                Response response2 = client.newCall(request2).execute();
                Response httpResponse2 = client.newCall(request2).execute();
                int responseCode2 = httpResponse2.code();
                if (responseCode2 == 201) {
                    log.info("2. Se han insertado los datos en el formulario HPD:Worklog: " + response2.toString());
                }
            } else {
                log.info("No se actualizo el primer comentario");
            }
        } catch (IOException ex) {
            log.error("2. HPD:Worklog - ERROR: " + ex);
        }
//**************************************Consultar el ID request del ID incidente***********************************
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("http://100.127.4.40:8008/api/arsys/v1/entry/HPD:Help Desk/?q='1000000161'=\"" + idIncidente + "\"&fields=values(1)")
                    .addHeader("Authorization", "AR-JWT " + CT.token() + "")
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            Response httpResponse = client.newCall(request).execute();
            int responseCode = httpResponse.code();
            String Json = response.body().string();
            System.out.println(Json);
            Gson gson = new Gson();
            JsonObject gsonobject = gson.fromJson(Json, JsonObject.class);
            JsonElement data = gsonobject.get("entries");
            String objectjson2 = data.toString().replace("[", "").replace("]", "");
            JsonObject gsonobject2 = gson.fromJson(objectjson2, JsonObject.class);
            JsonElement data2 = gsonobject2.get("values");
            JsonObject gsonobject3 = gson.fromJson(data2, JsonObject.class);
            JsonElement IDrequest = gsonobject3.get("Entry ID");
            String Idrequest = IDrequest.toString().replace("\"", "");
            System.out.println("Este es el codigo html para la consulta: " + responseCode);

//**********Inserta el numero del folio SIAC, dentro del campo oculto del formulario HPD:Help Desk***************
            if (responseCode == 200) {
                log.info("3. Se ha consultado id request " + response.toString());
                log.info("Este es el id request: " + Idrequest);
                //Se agrega la información en el campo intFolioSiac
                RequestBody body2 = RequestBody.create(mediaType, "{\r\n    \"values\":{\r\n        \"intFolioSIAC1\":\"" + folioSiac + "\"\r\n    }\r\n}");
                Request request2 = new Request.Builder()
                        .url("http://100.127.4.40:8008/api/arsys/v1/entry/HPD:Help Desk/" + Idrequest + "")
                        .method("PUT", body2)
                        .addHeader("Authorization", "AR-JWT " + CT.token() + "")
                        .addHeader("Content-Type", "application/json")
                        .build();
                Response response2 = client.newCall(request2).execute();
                Response httpResponse2 = client.newCall(request2).execute();
                int responseCode2 = httpResponse2.code();
                System.out.println("Este es el codigo de la actualizacion: " + responseCode2);
                if (responseCode2 == 204) {
                    log.info("4. Se han insertado los datos en el campo intFolioSiac: " + response2.toString());
                } else {
                    System.out.println("La actualización no se ejecuto");
                }
            }
        } catch (IOException ex) {
            log.error("3. intFolioSiac - ERROR: " + ex);
        }
    }
}
