package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Controller extends VBox{
    private boolean successful;

    @FXML
    private MenuItem viewalloutpatient;

    @FXML
    private MenuItem costofservice;

    @FXML
    private MenuItem hmodetails;

    @FXML
    private MenuItem companyhr;

    @FXML
    private MenuItem doclabresult1;

    @FXML
    private MenuItem logout;

    @FXML
    private MenuItem drugsales;

    @FXML
    private MenuItem addcompany;

    @FXML
    private MenuItem addlabtest;

    @FXML
    private Label bottomtext;

    @FXML
    private MenuItem ward_bed;

    @FXML
    private MenuItem opd;

    @FXML
    private MenuItem preparebudget;

    @FXML
    private MenuItem addradiology;

    @FXML
    private MenuItem doclabresult;

    @FXML
    private MenuItem docviewpatient;

    @FXML
    private MenuItem patientdiag;

    @FXML
    private MenuItem addspecialty;

    @FXML
    private MenuItem doclabresult1111;

    @FXML
    private MenuItem hospitalstructure;

    @FXML
    private MenuItem vitalsign;

    @FXML
    private MenuItem doclabresult111111;

    @FXML
    private MenuItem exit;

    @FXML
    private MenuItem dsanalysis;

    @FXML
    private BorderPane geneborderpane;

    @FXML
    private MenuItem druglist;

    @FXML
    private MenuItem supplierdetails;

    @FXML
    private MenuItem hmo11;

    @FXML
    private MenuItem drugcategory;

    @FXML
    private ImageView homebutton;

    @FXML
    private MenuItem creataccount;

    @FXML
    private MenuItem drugdetials;

    @FXML
    private MenuItem costofservice2;

    @FXML
    private MenuItem hmo1;

    @FXML
    private MenuItem adddepartment;



    @FXML
    private MenuItem viewaccsum;

    @FXML
    private MenuItem opdpatientlist;

    @FXML
    private MenuItem viewallpatients;

    @FXML
    private MenuItem expensecategory;

    @FXML
    private Menu inflowcategory;

    @FXML
    private MenuItem doctors_staff;

    @FXML
    private MenuItem changeadmin;

    @FXML
    private MenuItem purchase;

    @FXML
    private MenuItem patientReg;

    @FXML
    private MenuItem companydetails;

    @FXML
    private MenuItem doclabresult1111111;

    @FXML
    private MenuBar menubar;

    @FXML
    private MenuItem doclabresult11;

    @FXML
    private MenuItem viewallinpatient;

    @FXML
    private MenuItem hmohr;

    @FXML
    private MenuItem privatepatienthr;

    @FXML
    private MenuItem doclabresult11111;

    @FXML
    private Menu doctor_menu;

    @FXML
    private Menu nurse_menu;

    @FXML
    private Menu admin_menu;

    @FXML
    private Menu account_menu;

    @FXML
    private Menu system_menu;

    @FXML
    private Menu human_menu;

    @FXML
    private Menu medic_menu;

    @FXML
    private Menu hospital_menu;

    @FXML
    private Menu pharmacy_menu;

    @FXML
    private Menu store_menu;

    @FXML
    private Menu help_menu;

    static String sign;
    Button adcbutton;

    @FXML
    private Label logonname;

    static String sign_by;
    public  String department;

    static Stage stage;

    static BorderPane boderpane;



    public Controller(String department){

        this.department=department;
        try {
            FXMLLoader fxmlloader = new FXMLLoader(Controller.class.getResource("genesisfxml.fxml"));
            fxmlloader.setRoot(this);
            fxmlloader.setController(this);
            fxmlloader.load();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }




    @FXML
    void initialize()
    {
        nurse_menu.setDisable(true);

        doctor_menu.setDisable(true);
        admin_menu.setDisable(true);
        account_menu.setDisable(true);


        System.out.println("Department is " + department);

        if(department.equalsIgnoreCase("Doctor")){
            doctor_menu.setDisable(false);
        }

        if(department.equalsIgnoreCase("Nurse")){
            nurse_menu.setDisable(false);
        }
        if(department.equalsIgnoreCase("Account")){
            account_menu.setDisable(false);

        }
        if(department.equalsIgnoreCase("Administrator")){
            admin_menu.setDisable(false);
            nurse_menu.setDisable(false);
            doctor_menu.setDisable(false);
            account_menu.setDisable(false);
        }


        String holder="#5CB002";
        menubar.setStyle("-fx-base: white");
        homebutton.setOnMouseClicked(event->{
            try{
                FrontTilesController fc=new FrontTilesController(geneborderpane);
                geneborderpane.setCenter(fc);

            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

        });


        try {
            FrontTilesController fc = new FrontTilesController(geneborderpane);
            geneborderpane.setCenter(fc);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        List<String> templatelist=new ArrayList<>();

    }


    public void setLogonname(String value)
    {
        logonname.setText(value);

    }

    @FXML
    void companyEvent(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            Companypatient vbox=new Companypatient();
            geneborderpane.setCenter(vbox);


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void hmoEvent(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            HMPatientsController vbox=new HMPatientsController();
            geneborderpane.setCenter(vbox);


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void privatepatientEvent(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            VBox vbox=new Privatepatient();
            geneborderpane.setCenter(vbox);


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }


    @FXML
    private MenuItem company;

    @FXML
    void createAccount(ActionEvent event) {
        geneborderpane.getChildren().removeAll();

        try {
            AccountFormController accountvbox = new AccountFormController();
            geneborderpane.setCenter(accountvbox);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void changeAdminDetails(ActionEvent event) {
        geneborderpane.getChildren().removeAll();

        try {
            AdminDetailsController admindet = new AdminDetailsController();
            geneborderpane.setCenter(admindet);
            System.out.println("event occured_");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @FXML
    void addDepartment(ActionEvent event)
    {
        geneborderpane.getChildren().removeAll();

        try {
            Stage stage=new Stage();
            AddDepartmentController controller=new AddDepartmentController(stage);
            Scene scene=new Scene(controller);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    @FXML
    void patientRegAction(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try    {

            PatientRegistry pr = new PatientRegistry();
            Stage stage=new Stage();
            Scene scene=new Scene(pr);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    void addRadiologyAction(ActionEvent event) {

        try {
            Stage primaryStage = new Stage();
            AddRadiologyController ac = new AddRadiologyController();
            Scene scene = new Scene(ac);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void addLaboratoryAction(ActionEvent event) {
        try {
            Stage primaryStage = new Stage();
            AddLaboratoryController ac = new AddLaboratoryController();
            Scene scene = new Scene(ac);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }
    @FXML
    void viewAllPatietsAction(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{

            ViewAllPatientController vp=new ViewAllPatientController();
            vp.prefWidthProperty().bind(geneborderpane.widthProperty());
            geneborderpane.setCenter(vp);

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }


    }
    @FXML
    void costOfServiceAction(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            CostofServiceController cs=new CostofServiceController();
            geneborderpane.setCenter(cs);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    void drugDetialsAction(ActionEvent event)
    {
        geneborderpane.getChildren().removeAll();

        try{
            DrugDetailsController ds=new DrugDetailsController();
            geneborderpane.setCenter(ds);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
    @FXML
    void drugSupplierAction(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            DrugSupplyController ds=new DrugSupplyController();
            geneborderpane.setCenter(ds);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    @FXML
    void drugListAction(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            DrugListController ds=new DrugListController();
            geneborderpane.setCenter(ds);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void addCompanyDetails(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            CompanyDetails cd=new CompanyDetails();
            geneborderpane.setCenter(cd);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
    @FXML
    void addHMODetails(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            HMODetails cd=new HMODetails();
            geneborderpane.setCenter(cd);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    @FXML
    void addDrugCategoryAction(ActionEvent event) {

        try{
            Stage stage=new Stage();
            DrugCategory dc=new DrugCategory(stage);
            Scene scene=new Scene(dc);
            stage.setScene(scene);
            stage.setTitle("Drug Category");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    @FXML
    void purchaseAction(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            AddStoreItems as=new AddStoreItems();
            geneborderpane.setCenter(as);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
    @FXML
    void addLabTestAction(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            AddLabTest at=new AddLabTest();
            geneborderpane.setCenter(at);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    @FXML
    void drugSalesAction(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            DrugSales ds=new DrugSales(null, null);
            geneborderpane.setCenter(ds);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void dsAnalysisAction(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            TodaysPrescription tp=new TodaysPrescription();
            geneborderpane.setCenter(tp);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    @FXML
    void docViewPatientAction(ActionEvent event) {

        geneborderpane.getChildren().removeAll();
        try{
            DoctorsPatientController dc=new DoctorsPatientController();
            geneborderpane.setCenter(dc);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
    @FXML
    void vitalSignAction(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            VitalSignsController vs=new VitalSignsController(null);
            geneborderpane.setCenter(vs);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    @FXML
    void labTestResultAction(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            MLabResultController mc=new MLabResultController();
            geneborderpane.setCenter(mc);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
    @FXML
    void paymentAction(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            Pplist pl=new Pplist();
            geneborderpane.setCenter(pl);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void antenatalAction(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            ViewAntenatalPatientController va=new ViewAntenatalPatientController();
            geneborderpane.setCenter(va);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void nonAntenatalAction(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            ViewNonAntenatalController vn=new ViewNonAntenatalController();
            geneborderpane.setCenter(vn);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void dischargeAction(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            DischargePatientController dc=new DischargePatientController();
            geneborderpane.setCenter(dc);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void hmo_patientListAction(ActionEvent event) {
        geneborderpane.getChildren().removeAll();

        try{
            HMO_PatientListController hc=new HMO_PatientListController();
            geneborderpane.setCenter(hc);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    @FXML
    void createTemplates(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            CreateTemplateController ct=new CreateTemplateController();
            geneborderpane.setCenter(ct);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }


    }
    @FXML
    void logOutAction(ActionEvent event) {

        this.stage.close();



    }

    @FXML
    void exitAction(ActionEvent event) {

        Platform.exit();

    }
    @FXML
    void searchPatientAction(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try {
            SearchPatientController sc = new SearchPatientController();
            geneborderpane.setCenter(sc);
        }
        catch(Exception ex)
        {
            ErrorController ec=new ErrorController(ex.getMessage());
            Stage stage=new Stage();

            Scene scene=new Scene(ec);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();

        }


    }


    @FXML
    void createTemplateAction(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            CreateTemplateController ct=new CreateTemplateController();
            geneborderpane.setCenter(ct);

        }
        catch(Exception ex)
        {

        }

    }

    @FXML
    void birthInfoAction(ActionEvent eve)
    {
        geneborderpane.getChildren().removeAll();
        try{

            BirthInfoController bf=new BirthInfoController();
            geneborderpane.setCenter(bf);

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    void birthSearchAction(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            BirthDataController bd=new BirthDataController();
            geneborderpane.setCenter(bd);

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }

    @FXML
    void addWardAndBedAction(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            AddWardAndBedController ac=new AddWardAndBedController();
            geneborderpane.setCenter(ac);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }

    @FXML
    void inPatientAction(ActionEvent event)
    {
        geneborderpane.getChildren().removeAll();
        try{
            ViewAllInpatientController vc=new ViewAllInpatientController();
            geneborderpane.setCenter(vc);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    void outPatientsAction(ActionEvent event)
    {
        geneborderpane.getChildren().removeAll();
        try{

            ViewAllOutPatientController vo=new ViewAllOutPatientController();
            geneborderpane.setCenter(vo);

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @FXML
    void changePassword(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{
            Change_Password cp=new Change_Password();
            geneborderpane.setCenter(cp);

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }


    }

    @FXML
    void staffListAction(ActionEvent event) {
        geneborderpane.getChildren().removeAll();
        try{

            StaffList sl=new StaffList();
            geneborderpane.setCenter(sl);

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }


    }

    @FXML
    void viewAdmittedAction(ActionEvent event) {
        geneborderpane.getChildren().removeAll();

        try{
            ViewAdmittedPatientController vm=new ViewAdmittedPatientController();
            geneborderpane.setCenter(vm);

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }


    @FXML
    void createTemplate_Action(ActionEvent event) {
        try {
            CreateTemplate2Controller ct = new CreateTemplate2Controller();
            geneborderpane.setCenter(ct);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }


    }

    @FXML
    void openTemplate_Action(ActionEvent event){
        try{
            OpenTemplatesController ot=new OpenTemplatesController();
            geneborderpane.setCenter(ot);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void labReportAction(ActionEvent event){
        try{

            LaboratoryPatientList ol=new LaboratoryPatientList();
            geneborderpane.setCenter(ol);

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void docPatientAction(ActionEvent event){
        try{
            ViewPersonalPatientController cp=new ViewPersonalPatientController();
            geneborderpane.setCenter(cp);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void addStoreItemAction(ActionEvent event){
        try{
            AddStoreItemController ac=new AddStoreItemController();
            geneborderpane.setCenter(ac);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void itemUsageAction(ActionEvent event){

        try{
            RecordUsedItemsController ic=new RecordUsedItemsController();
            geneborderpane.setCenter(ic);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }

    @FXML
    void viewStoreItemAction(ActionEvent event){

        try{
            ViewStoreItemsController vc=new ViewStoreItemsController();
            geneborderpane.setCenter(vc);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }



    }

    @FXML
    void accountSummaryAction(ActionEvent event){
        try{
            AccountSummaryController ac=new AccountSummaryController();
            geneborderpane.setCenter(ac);

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void contactUsAction(ActionEvent event){
        try{
            ContactUsController cl=new ContactUsController();
            geneborderpane.setCenter(cl);

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void copyRightAction(ActionEvent event){
        try{
            LegalNoticeController ln=new LegalNoticeController();
            geneborderpane.setCenter(ln);

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void aboutAction(ActionEvent event){
        try{
            AboutController ac=new AboutController();
            geneborderpane.setCenter(ac);

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }



}





