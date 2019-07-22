//package sample;
//
///**
// * Created by Ese on 7/3/2016.
// */
//import com.jacob.activeX.ActiveXComponent;
//import com.jacob.com.Dispatch;
//import com.jacob.com.LibraryLoader;
//import com.jacob.com.Variant;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//public class JacobTestClass {
//    public static void main(String[] args) {
//
//        int id = 4;
//        try {
//            String sql = "select id from bed_test";
//            DataBase database = new DataBase();
//            Connection connection = database.getConnection();
//            PreparedStatement prepstatement = connection.prepareStatement(sql);
//            ResultSet rs = prepstatement.executeQuery();
//            while (rs.next()) {
//                id = rs.getInt("id");
//
//                System.out.println("id is " + id);
//                System.out.println();
//
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        System.out.println("real id is " + id);
//
//        String sql = "update bed_test set name='Zeus' where id=?";
//        try {
//
//            DataBase database = new DataBase();
//            Connection connection = database.getConnection();
//            PreparedStatement prepstatement = connection.prepareStatement(sql);
//            prepstatement.setInt(1, id);
//            int result = prepstatement.executeUpdate();
//            if (result > 0){
//                System.out.println("Successful");
//            }
//        } catch (Exception ex){
//            ex.printStackTrace();
//        }
//        /**
//         * `System.getProperty("os.arch")`
//         * It'll tell us on which platform Java Program is executing. Based on that we'll load respective DLL file.
//         * Placed under same folder of program file(.java/.class).
//         */
//        String libFile = System.getProperty("os.arch").equals("amd64") ? "jacob-1.18-x64.dll" : "jacob-1.18-x86.dll";
//        System.out.println("libfile is " + libFile);
//        try {
//            /* Read DLL file*/
//            InputStream inputStream = JacobTestClass.class.getResourceAsStream(libFile);
//            if(inputStream==null)
//            {
//                System.out.println("InputStream is null");
//                return;
//            }
//            /**
//             *  Step 1: Create temporary file under <%user.home%>\AppData\Local\Temp\jacob.dll
//             *  Step 2: Write contents of `inputStream` to that temporary file.
//             */
//            File temporaryDll = File.createTempFile("jacob", ".dll");
//            FileOutputStream outputStream = new FileOutputStream(temporaryDll);
//            int i=0;
//            byte[] array = new byte[8192];
//            while((i = inputStream.read(array)) != -1)
//            {
//                outputStream.write(array, 0, i);
//            }
//            outputStream.close();
//            /**
//             * `System.setProperty(LibraryLoader.JACOB_DLL_PATH, temporaryDll.getAbsolutePath());`
//             * Set System property same like setting java home path in system.
//             *
//             * `LibraryLoader.loadJacobLibrary();`
//             * Load JACOB library in current System.
//             */
//            System.setProperty(LibraryLoader.JACOB_DLL_PATH, temporaryDll.getAbsolutePath());
//            System.out.println("temporary dll " + temporaryDll.getAbsolutePath());
//
//            System.out.println("library loader preferred " + LibraryLoader.getPreferredDLLName());
//            LibraryLoader.loadJacobLibrary();
//
//
//            /**
//             * Create ActiveXComponent using CLSID. You can also use program id here.
//             * Next line(commented line/compProgramID) shows you how you can create ActiveXComponent using ProgramID.
//             */
//            ActiveXComponent compProgramID = new ActiveXComponent("JacobTest.Class1");
//
//            System.out.println("The Library been loaded, and an activeX component been created");
//
//
//            int arg1 = 300;
//            int arg2 = 500;
//            String name="Bruno";
//            /**
//             * Call .cs(.dll) function/method as follow
//             */
//        synchronized(compProgramID)
//        {
//            int result = Dispatch.call(compProgramID, "add", arg1, arg2).toInt();
//            System.out.println("Result: " + result);
//        }
//
//
//            /* Temporary file will be removed after terminating-closing-ending the application-program */
//            temporaryDll.deleteOnExit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
