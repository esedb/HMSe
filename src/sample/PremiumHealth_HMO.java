//package sample;
//
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
//import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
//import org.apache.pdfbox.pdmodel.interactive.form.PDCheckbox;
//import org.apache.pdfbox.pdmodel.interactive.form.PDField;
//
//import java.io.File;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.time.LocalDate;
//import java.util.Date;
//
///**
// * Created by Ese on 3/1/2016.
// */
//public class PremiumHealth_HMO {
//    ViewAllPatientBean vb;
//    public PremiumHealth_HMO(ViewAllPatientBean vb)
//    {
//        this.vb=vb;
//
//    }
//    void pupulate()
//    {
//        String doctor="";
//        String complain="";
//        Date date=null;
//
//        try (DataBase dbase =new DataBase(); Connection con=dbase.getConnection()){
//
//            String sql="SELECT * FROM vital_signs where patient_id=?";
//            PreparedStatement statement=con.prepareStatement(sql);
//            statement.setString(1, vb.getUserId());
//            ResultSet rs=statement.executeQuery();
//            while(rs.next())
//            {
//                doctor=rs.getString("doctor");
//                complain=rs.getString("complain");
//                date=rs.getDate("date");
//            }
//
//
//        } catch (Exception ex){
//
//        }
//        try{
//        Path path = Paths.get("C:\\Users\\Ese\\IdeaProjects\\will_Parry\\src\\sample\\pdf\\claims.pdf");
//        File file=path.toFile();
//        PDDocument pdfTemplate = PDDocument.load(file);
//        PDDocumentCatalog docCatalog = pdfTemplate.getDocumentCatalog();
//        PDAcroForm acroForm = docCatalog.getAcroForm();
//        String sex=vb.getSex();
//
//        for(Object namefield: acroForm.getFields())
//        {
//            PDField name=(PDField)namefield;
//            if(name.getFullyQualifiedName().equalsIgnoreCase("name"))
//            {
//                name.setValue(vb.getFirstname()+ " " + vb.getLastname());
//            }
//            else if(name.getFullyQualifiedName().equalsIgnoreCase("address"))
//            {
//                name.setValue(vb.getAddress());
//            }
//            else if(name.getFullyQualifiedName().equalsIgnoreCase("male_check"))
//            {
//                if(sex=="Male"){
//                    ((PDCheckbox) name).check();
//                }
//
//            }
//            else if(name.getFullyQualifiedName().equalsIgnoreCase("Female")){
//                if(sex=="Female")
//                {
//                    ((PDCheckbox) name).check();
//                }
//
//            }
//            else if(name.getFullyQualifiedName().equalsIgnoreCase("docname"))
//            {
//                name.setValue(doctor);
//            }
//            else if(name.getFullyQualifiedName().equalsIgnoreCase("date"))
//            {
//                LocalDate date3=LocalDate.now();
//                String datevalue=date3.toString();
//
//                name.setValue(datevalue);
//
//            }
//            else if(name.getFullyQualifiedName().equalsIgnoreCase("recname"))
//            {
//                name.setValue(vb.getFirstname() + " " + vb.getLastname());
//            }
//
//        }
//
//        pdfTemplate.save("C:\\Users\\Ese\\IdeaProjects\\will_Parry\\src\\sample\\pdf\\claims28.pdf");
//        pdfTemplate.close();
//    }
//    catch(Exception ex)
//    {
//        ex.printStackTrace();
//    }
//    }
//}
