/*
 * Copyright (C) Qatar Insurance Group, PO Box 666, Doha, Qatar
 * All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */
package springDemo.test.messenger.action;

/*import java.io.File;
import java.util.ArrayList;
import java.util.List;*/
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.logging.LoggingFeature;
/*import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.json.JSONArray;*/
import org.json.JSONObject;

/**
 *
 * @author ravindar.singh
 */
public class HospitalIntegrationTest {

    public static String BASE_URL = "https://www.demo.qatarinsurance.com/api/medical/";

    public static void main(String... args) {
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("alkharashy1", "alkharashy1123");
        final Client client = ClientBuilder.newClient(new ClientConfig(feature).register(LoggingFeature.class));
        //final Client f_client = ClientBuilder.newClient(new ClientConfig(feature)).register(MultiPartFeature.class).register(LoggingFilter.class);
        WebTarget webTarget = client.target(BASE_URL).path("hospital-service");
        //WebTarget f_webTarget = f_client.target(BASE_URL).path("hospital-service");
        Response res = null;
        Invocation.Builder ib = null;
        JSONObject jo = new JSONObject();
        //JSONArray ja = new JSONArray();
        int i = 0;
        try {
            switch (i) {
                case 0:
                    //jo.put("memberId", "MEM1239845");
                    jo.put("civilId", "27763402913");

                    ib = webTarget.path("eligibilityCheck").request(MediaType.APPLICATION_JSON);
                    System.out.println("URL: " + webTarget.getUri().toString());
                    System.out.println("I/P: " + jo.toString());
                    res = ib.post(Entity.entity(jo.toString(), MediaType.APPLICATION_JSON));
                    System.out.println("O/P: " + res.readEntity(String.class));
                    break;
               /* case 1:
                    FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
                    formDataMultiPart.bodyPart(new FileDataBodyPart("document", new File("C:/temp/bodyType.png"), MediaType.MULTIPART_FORM_DATA_TYPE));
                    formDataMultiPart.bodyPart(new FileDataBodyPart("document", new File("C:/temp/manuYear.png"), MediaType.MULTIPART_FORM_DATA_TYPE));
                    formDataMultiPart.field("policyNo", "P-90-17-106380-16");
                    formDataMultiPart.field("memberId", "MEM1239845");
                    formDataMultiPart.field("category", "001");
                    formDataMultiPart.field("illness", "001");
                    formDataMultiPart.field("primaryDiagnosis", "Y90.1");
                    formDataMultiPart.field("secondaryDiagnosis", ""); //Optinal
                    formDataMultiPart.field("medicalRecordNo", "1234");
                    formDataMultiPart.field("serviceType", "O");
                    formDataMultiPart.field("visitDate", "21/01/2017");
                    formDataMultiPart.field("doctorLicenseNo", "LIC45678");
                    formDataMultiPart.field("currency", "QAR");
                    formDataMultiPart.field("mobileNo", "30073231");
                    formDataMultiPart.field("illnessDetails", "Stomach pain");
                    
                    jo.put("treatCode", "LAB10010"); // Agreed Treatments
                    jo.put("treatDesc", "Hemicolectomy");
                    jo.put("quantity", "1");
                    jo.put("estimatedAmount", "2200");
                    jo.put("remarks", "Blood test");
                    ja.put(jo);
                    jo = new JSONObject();
                    jo.put("treatCode", "002"); // Un Agreed Treatments
                    jo.put("treatDesc", "Test 002");
                    jo.put("quantity", "2");
                    jo.put("estimatedAmount", "100");
                    jo.put("remarks", "Stomach pain relief treatment");
                    ja.put(jo);
                    System.out.println(ja.toString());
                    formDataMultiPart.field("treatmentsData", ja.toString(), MediaType.APPLICATION_JSON_TYPE);
                    ib = f_webTarget.path("preApprovalSubmit").request(MediaType.APPLICATION_JSON);
                    res = ib.post(Entity.entity(formDataMultiPart, formDataMultiPart.getMediaType()));
                    System.out.println(res.readEntity(String.class));
                    break;*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
