package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ese on 6/24/2017.
 */
public class AccountSummaryController extends VBox {

    @FXML
    private WebView webview;

    @FXML
    private Button printbutton;


    @FXML
    void initialize(){
        List<String> idlist=new ArrayList<>();
        List<Long> amountlist=new ArrayList<>();
        List<String> paymentypelist=new ArrayList<>();
        List<Long> receiptlist =new ArrayList<>();
        List<String> signbylist=new ArrayList<>();
        List<String> date_v=new ArrayList<>();

        String html="";

        try(DataBase dbase=new DataBase(); Connection con=dbase.getConnection(); PreparedStatement stmt =con.prepareStatement("select * from payment_table")){
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                idlist.add(rs.getString("id_sn"));
                amountlist.add(rs.getLong("amount_deposited"));
                paymentypelist.add(rs.getString("payment_type"));
                receiptlist.add(rs.getLong("receipt_no"));
                signbylist.add(rs.getString("sign_by"));
                date_v.add(rs.getString("date_v"));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        long totalcost=0;
        for(long cost: amountlist){
            totalcost+=cost;
        }


        html="<html>\n" +
                "<head>\n" +
                "<style type=\"text/css\">\n" +
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
                    "  width: 950px;\n" +
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
                    "  padding-left: 25px;\n" +
                    "}\n" +
                    "#keywords thead tr th span { \n" +
                    "  padding-right: 8px;\n" +
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
                    "width: 100px;\n" +
                    "height: 120px;\n" +
                    "}" +
                    "#imgwrapper p{\n" +
                    "font-size: 15;\n" +
                    "font-family:  Tahoma, sans-serif;\n" +
                    "margin-top: 10px;\n" +
                    "}\n" +
                    "#totalwrapper p{\n" +
                    "font-size: 16;\n" +
                    "font-weight: bold;\n" +
                    "}" +
                    "#keywords thead tr th span{\n" +
                    "text-align: right;\n" +
                    "}" +
                    "</style>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +

                    "<div id=\"wrapper\">\n" +

                    "  <h1>Account Summary</h1>\n" +
                    "  \n" +
                    "  <table id=\"keywords\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                    "    <thead>\n" +
                    "      <tr>\n" +
                    "        <th><span>Patient Id</span></th>\n" +
                    "        <th><span>Amount Deposited</span></th>\n" +
                    "        <th><span>Payment Type</span></th>\n" +
                    "        <th><span>Receipt No</span></th>\n" +
                    "        <th><span>Sign By</span></th>\n" +
                    "        <th><span>Date</span></th>\n" +
                    "      </tr>\n" +
                    "    </thead>\n" +
                    "    <tbody>\n";

            for(int i=0; i<amountlist.size(); i++)
            {
                html+=  "      <tr>\n" +
                        "        <td class=\"lalign\"> " + idlist.get(i) + "</td>\n" +
                        "        <td>" + amountlist.get(i) + "</td>\n" +
                        "        <td>" + paymentypelist.get(i) + "</td>\n" +
                        "        <td>" + receiptlist.get(i) + "</td>\n" +
                        "        <td>" + signbylist.get(i) + "</td>\n" +
                        "        <td>" + date_v.get(i) + "</td>\n" +
                        "      </tr>\n";
            }

            html+=  "    </tbody>\n" +
                    "  </table>\n" +
                    "<div id=\"totalwrapper\">\n" +
                    "<p>Total cost is : " + totalcost + "</p>\n" +
                    "<br/>" +
                    "</div>" +
                    " </div> \n" +
                    "</body>\n" +
                    "</html>";


        WebEngine webEngine = webview.getEngine();
        webEngine.loadContent(html);

        printbutton.setOnAction(e->{

            PrinterJob job = PrinterJob.createPrinterJob();
            boolean proceed = job.showPrintDialog(null);
            if (proceed) {
                webEngine.print(job);
                job.endJob();
            }

        });


    }

    public AccountSummaryController() throws IOException {

        FXMLLoader fxmlloader=new FXMLLoader(AccountSummaryController.class.getResource("accountsummary.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }
}
