package controller.GestioneModuloRiconoscimento;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VisualizzaModuloRiconoscimento
 */
@WebServlet("/VisualizzaModuloRiconoscimento")
public class VisualizzaModuloRiconoscimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaModuloRiconoscimento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // reads input file from an absolute path
		
		
    	String[] fileList = new String[2];
        String filePath ="/Users/antoniogiano/Desktop/Schermata 2019-12-30 alle 20.41.21.png";
        String filePath1="/Users/antoniogiano/Desktop/Schermata 2019-12-30 alle 20.41.15.png";
        fileList[0]=filePath;
        fileList[1]=filePath1;
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=mytest.zip");
        
        ServletOutputStream out=response.getOutputStream();
        ZipOutputStream zos=new ZipOutputStream(new BufferedOutputStream(out));
        for(int i=0;i<fileList.length;i++) {
        System.out.println("\n\nsetto\n\n");
        File downloadFile = new File(fileList[i]);
        
        
        System.out.println("aggiungi amo il file-->"+downloadFile.getName());
        zos.putNextEntry(new ZipEntry(downloadFile.getName()));
        
        FileInputStream inStream = new FileInputStream(downloadFile);
        
        BufferedInputStream inBuff=new BufferedInputStream(inStream);
        
        int data=0;
        while((data=inBuff.read())!=-1) {
        	zos.write(data);
        }
        inBuff.close();
        
        zos.closeEntry();
        System.out.println("Finito il file-->"+downloadFile.getName());
        }  
        zos.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
