package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ese on 5/18/2017.
 */
public class WebViewTest extends VBox {

    private String id;
    @FXML
    private Button load_button;

    @FXML
    private WebView web_view;

    @FXML
    void initialize(){

        /*String html = "<html><head><title>Test</title></head>" +
                "<body><h1>Hello from WebView</h1></body></html>";
        String htm="<html><head><title>Test</title></head><body><a href='google.co.uk' onclick='window.print()' >google</a></body></html>";

        URL url = WebViewTest.class.getResource("html_print.html");



        System.out.println("url string is " + url.toExternalForm());
        WebEngine webEngine = web_view.getEngine();
        webEngine.load(url.toExternalForm());

        //webEngine.load("http://localhost:8080/henry_project/");

        */

        displayTable();



    }

    void displayTable(){

        List<String> datelist=new ArrayList<>();
        List<String> operationtypelist=new ArrayList<>();
        List<String> operationdatalist=new ArrayList<>();
        List<String> operationvaluelist=new ArrayList<>();
        String name=null;
        String imageurl=null;


        /*String sql="select service_data.date_v, service_data.service_name, service_data.service_type, " +
                "service_data.service_value, patient_registry.id_sn, patient_registry.image_url " +
                "inner join patient_registry on service_data.c_id=patient_registry.id_sn where service_data.c_id=?";
        */
        String sql = "select * from service_data where c_id=?";
        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
            stmt.setString(1, id);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){

                datelist.add(rs.getString("date_v"));
                operationtypelist.add(rs.getString("service_name"));
                operationdatalist.add(rs.getString("service_type"));
                operationvaluelist.add(rs.getString("service_value"));


            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt=con.prepareStatement("select firstname, othername, image_url  from patient_registry where id_sn=?"))
        {
            stmt.setString(1, id);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()) {
                name = rs.getString("firstname") + " " + rs.getString("othername");
                imageurl=rs.getString("image_url");
            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        boolean flag=datelist.isEmpty() && operationdatalist.isEmpty() && operationtypelist.isEmpty();
        if(flag) {
            ShowDialog.show("No Laboratory Data for this patient");
            return;
        }



        String html="<html>\n" +
                "<head>\n" +
                "<style type=\"text/css\">\n" +
                "@import url('https://fonts.googleapis.com/css?family=Amarante');\n" +
                "\n" +
                "html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn, em, img, ins, kbd, q, s, samp, small, strike, strong, sub, sup, tt, var, b, u, i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas, details, embed, figure, figcaption, footer, header, hgroup, menu, nav, output, ruby, section, summary, time, mark, audio, video {\n" +
                "  margin: 0;\n" +
                "  padding: 0;\n" +
                "  border: 0;\n" +
                "  font-size: 100%;\n" +
                "  font: inherit;\n" +
                "  vertical-align: baseline;\n" +
                "  outline: none;\n" +
                "  -webkit-font-smoothing: antialiased;\n" +
                "  -webkit-text-size-adjust: 100%;\n" +
                "  -ms-text-size-adjust: 100%;\n" +
                "  -webkit-box-sizing: border-box;\n" +
                "  -moz-box-sizing: border-box;\n" +
                "  box-sizing: border-box;\n" +
                "}\n" +
                "html { overflow-y: scroll; }\n" +
                "body { \n" +

                "  font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;\n" +
                "  font-size: 62.5%;\n" +
                "  line-height: 1;\n" +
                "  color: #585858;\n" +
                "  padding: 22px 10px;\n" +
                "  padding-bottom: 55px;\n" +
                "}\n" +
                "\n" +
                "::selection { background: #5f74a0; color: #fff; }\n" +
                "::-moz-selection { background: #5f74a0; color: #fff; }\n" +
                "::-webkit-selection { background: #5f74a0; color: #fff; }\n" +
                "\n" +
                "br { display: block; line-height: 1.6em; } \n" +
                "\n" +
                "article, aside, details, figcaption, figure, footer, header, hgroup, menu, nav, section { display: block; }\n" +
                "ol, ul { list-style: none; }\n" +
                "\n" +
                "input, textarea { \n" +
                "  -webkit-font-smoothing: antialiased;\n" +
                "  -webkit-text-size-adjust: 100%;\n" +
                "  -ms-text-size-adjust: 100%;\n" +
                "  -webkit-box-sizing: border-box;\n" +
                "  -moz-box-sizing: border-box;\n" +
                "  box-sizing: border-box;\n" +
                "  outline: none; \n" +
                "}\n" +
                "\n" +
                "blockquote, q { quotes: none; }\n" +
                "blockquote:before, blockquote:after, q:before, q:after { content: ''; content: none; }\n" +
                "strong, b { font-weight: bold; } \n" +
                "\n" +
                "table { border-collapse: collapse; border-spacing: 0; }\n" +
                "img { border: 0; max-width: 100%; }\n" +
                "\n" +
                "h1 { \n" +
                "  font-family: 'Amarante', Tahoma, sans-serif;\n" +
                "  font-weight: bold;\n" +
                "  font-size: 3.6em;\n" +
                "  line-height: 1.7em;\n" +
                "  margin-bottom: 10px;\n" +
                "  text-align: center;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "/** page structure **/\n" +
                "#wrapper {\n" +
                "  display: block;\n" +
                "  width: 850px;\n" +
                "  background: #fff;\n" +
                "  margin: 0 auto;\n" +
                "  padding: 10px 17px;\n" +
                "  -webkit-box-shadow: 2px 2px 3px -1px rgba(0,0,0,0.35);\n" +
                "}\n" +
                "\n" +
                "#keywords {\n" +
                "  margin: 0 auto;\n" +
                "  font-size: 1.2em;\n" +
                "  margin-bottom: 15px;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "#keywords thead {\n" +
                "  cursor: pointer;\n" +
                "  background: #c9dff0;\n" +
                "}\n" +
                "#keywords thead tr th { \n" +
                "  font-weight: bold;\n" +
                "  padding: 12px 30px;\n" +
                "  padding-left: 42px;\n" +
                "}\n" +
                "#keywords thead tr th span { \n" +
                "  padding-right: 20px;\n" +
                "  background-repeat: no-repeat;\n" +
                "  background-position: 100% 100%;\n" +
                "}\n" +
                "\n" +
                "#keywords thead tr th.headerSortUp, #keywords thead tr th.headerSortDown {\n" +
                "  background: #acc8dd;\n" +
                "}\n" +
                "\n" +
                "#keywords thead tr th.headerSortUp span {\n" +

                "}\n" +
                "#keywords thead tr th.headerSortDown span {\n" +

                "}\n" +
                "\n" +
                "\n" +
                "#keywords tbody tr { \n" +
                "  color: #555;\n" +
                "}\n" +
                "#keywords tbody tr td {\n" +
                "  text-align: center;\n" +
                "  padding: 15px 10px;\n" +
                "}\n" +
                "#keywords tbody tr td.lalign {\n" +
                "  text-align: left;\n" +
                "}\n" +
                "\n" +
                "#imgwrapper{\n" +
                "display: block;\n" +
                "width: 200px;\n" +
                "height: 120px;\n" +
                "float: left;\n" +
                "}\n" +
                "#imgwrapper img{\n" +
                "width: 120px;\n" +
                "height: 140px;\n" +
                "}" +
                "#imgwrapper p{\n" +
                "font-size: 15;\n" +
                "font-family:  Tahoma, sans-serif;\n" +
                "margin-top: 10px;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +

                " <div id=\"wrapper\">\n" +
                "<div id=\"imgwrapper\">\n" +
                "<img alt=\"image\"  src=\"" + imageurl + "\">\n" +
                "</img>" +
                "<p>" + name + "</p>\n" +
                "</div>\n" +
                "  <h1>Patient Laboratory Test</h1>\n" +
                "  \n" +
                "  <table id=\"keywords\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "    <thead>\n" +
                "      <tr>\n" +
                "        <th><span>Date</span></th>\n" +
                "        <th><span>Test Type</span></th>\n" +
                "        <th><span>Test Data</span></th>\n" +
                "        <th><span>Value</span></th>\n" +
                "      </tr>\n" +
                "    </thead>\n" +
                "    <tbody>\n";

        for(int i=0; i<datelist.size(); i++)
        {
            if(i==0)
            {
                html+=  "      <tr>\n" +
                        "        <td class=\"lalign\"> " + datelist.get(i) + "</td>\n" +
                        "        <td>" + operationtypelist.get(i) + "</td>\n" +
                        "        <td>" + operationdatalist.get(i) + "</td>\n" +
                        "        <td>" + operationvaluelist.get(i) +"</td>\n" +
                        "      </tr>\n";
            }
            if(i>0 && i<datelist.size()){
                int d=i-1;
                if(datelist.get(d).equalsIgnoreCase(datelist.get(i)) || operationtypelist.get(d).equalsIgnoreCase(operationtypelist.get(i))){
                    html+=  "      <tr>\n" +
                            "        <td class=\"lalign\"> " + "" + "</td>\n" +
                            "        <td>" + "" + "</td>\n" +
                            "        <td>" + operationdatalist.get(i) + "</td>\n" +
                            "        <td>" + operationvaluelist.get(i) +"</td>\n" +
                            "      </tr>\n";
                }
                else{

                    html+=  "      <tr>\n" +
                            "        <td class=\"lalign\"> " + datelist.get(i) + "</td>\n" +
                            "        <td>" + operationtypelist.get(i) + "</td>\n" +
                            "        <td>" + operationdatalist.get(i) + "</td>\n" +
                            "        <td>" + operationvaluelist.get(i) +"</td>\n" +
                            "      </tr>\n";

                }
            }

        }

        html+=  "    </tbody>\n" +
                "  </table>\n" +
                " </div> \n" +
                "</body>\n" +
                "</html>";


        WebEngine webEngine = web_view.getEngine();
        webEngine.loadContent(html);

        load_button.setOnAction(e->{
            PrinterJob job = PrinterJob.createPrinterJob();
            boolean proceed = job.showPrintDialog(null);
            if (proceed) {
                webEngine.print(job);
                job.endJob();
            }

        });


    }

    public WebViewTest(String id) throws IOException {
        this.id=id;

        FXMLLoader fxmlloader=new FXMLLoader(WebViewTest.class.getResource("web_test.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();

    }

}
