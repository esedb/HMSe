package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ese on 12/31/2015.
 */
public class CostofServiceController extends VBox {

    @FXML
    private TextField labcost;

    @FXML
    private Button antibutton;

    @FXML
    private ChoiceBox<?> rdchoice;

    @FXML
    private Button paregbutton;

    @FXML
    private ChoiceBox<?> consultchoice;

    @FXML
    private Button labbutton;

    @FXML
    private Button morbutton;

    @FXML
    private ChoiceBox<?> treatchoice;

    @FXML
    private ChoiceBox<?> pharchoice;

    @FXML
    private TextField pharcost;

    @FXML
    private Button treatbutton;

    @FXML
    private Button consultbutton;

    @FXML
    private TextField treatcost;

    @FXML
    private Button pharbutton;

    @FXML
    private TextField consultcost;

    @FXML
    private TextField pregcostfld;

    @FXML
    private ChoiceBox<?> pacategorychoice;

    @FXML
    private ChoiceBox<?> morchoice;

    @FXML
    private TextField morcost;

    @FXML
    private Button radbutton;

    @FXML
    private ChoiceBox<?> labchoice;

    @FXML
    private TextField radcost;

    @FXML
    private TextField anticostfld;


    Connection con=null;
    DataBase dbase=null;


    public CostofServiceController() throws IOException
    {
        FXMLLoader fxmlloader=new FXMLLoader(CostofServiceController.class.getResource("costofservice.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);
        fxmlloader.load();
    }

    @FXML
    private void initialize()
    {
        try{
            dbase=new DataBase();
            con=dbase.getConnection();

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        ObservableList lablist= FXCollections.<String>observableArrayList();
        String sql="SELECT labtype from laboratory";
        Connection con;
        DataBase dbase=new DataBase();
        try {
            con=dbase.getConnection();
            PreparedStatement prepstatement=con.prepareStatement(sql);
            ResultSet rs=prepstatement.executeQuery();
            while(rs.next())
            {
                String values=rs.getString("labtype");
                lablist.add(values);


            }

            }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        labchoice.setItems(lablist);

//choice box values

        ObservableList radlist= FXCollections.<String>observableArrayList();
        String sql2="SELECT radtype from radiological";
        Connection radcon;

        try {
            radcon=dbase.getConnection();
            PreparedStatement prepstatement=radcon.prepareStatement(sql2);
            ResultSet rs=prepstatement.executeQuery();
            while(rs.next())
            {
                String values=rs.getString("radtype");
                radlist.add(values);
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        rdchoice.setItems(radlist);

        //populating patient category choice box;
        ObservableList palist=FXCollections.observableArrayList("Family", "Individual", "Company", "HMO");
        pacategorychoice.setItems(palist);


        // organizing logic for patientcategory;
        pacategorychoice.getSelectionModel().selectFirst();





            radbutton.setOnAction(e -> {
                String pacategory=pacategorychoice.getValue().toString();
                if(pacategory=="Private") {
                    int cost = Integer.parseInt(radcost.getText());
                    boolean succesful;
                    String sql3 = "insert into radiological (pricost) values(?)";
                    try {
                        Connection con3 = dbase.getConnection();
                        PreparedStatement prepstatement = con3.prepareStatement(sql3);
                        prepstatement.setInt(1, cost);
                        int result = prepstatement.executeUpdate();
                        System.out.println("pricsot successful");


                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                if(pacategory=="Company") {

                    int cost = Integer.parseInt(radcost.getText());
                    boolean succesful;
                    String sql4 = "insert into radiological (companycost) values(?)";
                    try {
                        Connection con3 = dbase.getConnection();
                        PreparedStatement prepstatement = con3.prepareStatement(sql4);

                        prepstatement.setInt(1, cost);
                        int result = prepstatement.executeUpdate();
                        System.out.println("pricsot successful");


                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                if(pacategory=="HMO") {
                    int cost = Integer.parseInt(radcost.getText());
                    boolean succesful;
                    String sql4 = "insert into radiological (hmocost) values(?)";
                    try {
                        Connection con3 = dbase.getConnection();
                        PreparedStatement prepstatement = con3.prepareStatement(sql4);
                        prepstatement.setInt(1, cost);
                        int result = prepstatement.executeUpdate();
                        System.out.println("pricsot successful");


                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }



            });
        labbutton.setOnAction(e->
        {
            String pacategory=pacategorychoice.getValue().toString();
            if(pacategory=="Private")
            {
                 int cost=Integer.parseInt(labcost.getText());
                boolean successful;
                String sql5="INSERT INTO laboratory (pricost) values(?)";
                try {
                    Connection con4=dbase.getConnection();
                    PreparedStatement prepstatement=con4.prepareStatement(sql5);
                    prepstatement.setInt(1, cost);

                    }
                catch(Exception en)
                {
                    en.printStackTrace();
                }
            }
            if(pacategory=="Company")
            {
                int cost=Integer.parseInt(labcost.getText());
                boolean successful;
                String sql5="INSERT INTO laboratory (companycost) values(?)";
                try {
                    Connection con4=dbase.getConnection();
                    PreparedStatement prepstatement=con4.prepareStatement(sql5);
                    prepstatement.setInt(1, cost);

                }
                catch(Exception en)
                {
                    en.printStackTrace();
                }
            }
            if(pacategory=="HMO")
            {
                int cost=Integer.parseInt(labcost.getText());
                boolean successful;
                String sql5="INSERT INTO laboratory (hmocost) values(?)";
                try {
                    Connection con4=dbase.getConnection();
                    PreparedStatement prepstatement=con4.prepareStatement(sql5);
                    prepstatement.setInt(1, cost);

                }
                catch(Exception en)
                {
                    en.printStackTrace();
                }
            }



        });

        paregbutton.setOnAction(e->
        {
            Long pregcost=null;
            String pacategory = (String) pacategorychoice.getSelectionModel().getSelectedItem();

            try {
                pregcost = Long.valueOf(pregcostfld.getText());
            }
            catch(NumberFormatException ex) {
                ex.printStackTrace();
            }

           if (pacategory != null) {

                if (pacategory == "HMO" && pregcostfld.getText().length() > 0 && pregcost!=null) {
                    try {
                        String sql7 = "update cost_of_service set hmo=? where nameof_service='Patient Registration'";
                        PreparedStatement statement = this.con.prepareStatement(sql7);
                        statement.setLong(1, pregcost);
                        int result = statement.executeUpdate();
                        if (result == 1) {
                            System.out.println("Successful : _ ");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                } else if (pacategory == "Company" && pregcostfld.getText().length() > 0 && pregcost!=null) {
                    try {
                        String sql7 = "update cost_of_service set  company_cost=? where nameof_service='Patient Registration'";
                        PreparedStatement statement = this.con.prepareStatement(sql7);
                        statement.setLong(1, pregcost);
                        int result = statement.executeUpdate();
                        if (result == 1) {
                            System.out.println("Successful : _ ");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                } else if (pacategory == "Family" && pregcostfld.getText().length() > 0 && pregcost!=null) {
                    try {
                        String sql7 = "update cost_of_service set family_cost=? where nameof_service='Patient Registration'";
                        PreparedStatement statement = this.con.prepareStatement(sql7);
                        statement.setLong(1, pregcost);
                        int result = statement.executeUpdate();
                        if (result == 1) {
                            System.out.println("Successful : _ ");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (pacategory == "Individual" && pregcostfld.getText().length() > 0 && pregcost!=null) {
                    try {
                        String sql7 = "update cost_of_service set individual_cost=? where nameof_service='Patient Registration'";
                        PreparedStatement statement = this.con.prepareStatement(sql7);
                        statement.setLong(1, pregcost);
                        int result = statement.executeUpdate();
                        if (result == 1) {
                            System.out.println("Successful : _ ");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }


        });

        antibutton.setOnAction(e->
        {
            Long anticost=null;
            String pacategory = (String) pacategorychoice.getSelectionModel().getSelectedItem();
            try {
                anticost = Long.valueOf(anticostfld.getText());
            }
            catch(NumberFormatException ex) {
                ex.printStackTrace();
            }


                if (pacategory != null) {

                    if (pacategory == "HMO" && anticostfld.getText().length() > 0 && anticost!=null) {
                        System.out.println("successful Passed");
                        try {
                            String sql7 = "update cost_of_service set hmo=? where nameof_service='Antenatal'";
                            PreparedStatement statement = this.con.prepareStatement(sql7);
                            statement.setLong(1, anticost);
                            int result = statement.executeUpdate();
                            if (result == 1) {
                                System.out.println("Successful : _ ");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }


                    }
                    else if(pacategory == "Company" && anticostfld.getText().length() > 0 && anticost!=null)
                    {
                        try {
                            String sql7 = "update cost_of_service set company_cost=? where nameof_service='Antenatal'";
                            PreparedStatement statement = this.con.prepareStatement(sql7);
                            statement.setLong(1, anticost);
                            int result = statement.executeUpdate();
                            if (result == 1) {
                                System.out.println("Successful : _ ");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    else if(pacategory == "Family" && anticostfld.getText().length() > 0 && anticost!=null)
                    {
                        try {
                            String sql7 = "update cost_of_service set family_cost=? where nameof_service='Antenatal'";
                            PreparedStatement statement = this.con.prepareStatement(sql7);
                            statement.setLong(1, anticost);
                            int result = statement.executeUpdate();
                            if (result == 1) {
                                System.out.println("Successful : _ ");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                    else if(pacategory == "Individual" && anticostfld.getText().length() > 0 && anticost!=null)
                    {
                        try {
                            String sql7 = "update cost_of_service set individual_cost=? where nameof_service='Antenatal'";
                            PreparedStatement statement = this.con.prepareStatement(sql7);
                            statement.setLong(1, anticost);
                            int result = statement.executeUpdate();
                            if (result == 1) {
                                System.out.println("Successful : _ ");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }

        });

    }

}
