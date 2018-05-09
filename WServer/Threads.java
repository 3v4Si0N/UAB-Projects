import java.io.*;
import java.net.*;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Threads extends Thread{
	
	private String linia;
	private String[] args;
	int i;
	int contador;
	public Threads(String linia,String[] args, int contador){
		this.linia = linia;
		this.args = args;
		this.contador = contador;
	}
	public void run(){
		boolean a,z,gz;
		a = false;
		z = false;
		gz = false;
		URL pag;
		String nom;
		OutputStream os;
		URLConnection contingut;
		InputStream is;
		int caracter;
		try {
		
		//mirem quins arguments ens han passat
		for (i=0;i<args.length;i++){
			if(args[i].equals("-a")){
				a = true;
			}
			else if(args[i].equals("-z")){
				z = true;
			}
			else if(args[i].equals("-gz")){
				gz = true;
			}
			
		}
		
		System.out.println("Comprovem filtres, a:" + a + " z:"+z+" gz:"+gz);
		
			pag = new URL(linia);
			is = pag.openStream();
			contingut = pag.openConnection();
			String tipus = contingut.getContentType();
			System.out.println("tipus del fitxer "+ tipus);
			nom = " ";
			//codi pel nom
			String part = pag.getFile();
			System.out.println("get file "+part);
			if (part == ""){
				
				nom = "index" + contador + ".html";
			}
			else
			{
				int index = part.lastIndexOf("/");
				part = part.substring(index+1);
				index = part.lastIndexOf(".");
				String part2 = part.substring(0,index-1);
				part = part.substring(index);
				nom = part2 + contador + part;
				
				
				
			}
			
			String nomzip = nom;
		if (a = true && tipus.contains("text/html")){
				nom += ".asc";
				
		}
		if (z == true){
			System.out.println("dintre filtre zip");
			nom += ".zip";
		}
		if (gz == true){
			System.out.println("dintre filtre gzip");
			nom += ".gz";
		}
			
			
		System.out.println("nom "+ nom);
			
						
			
			
			
			InputStream is2;
			is2 = is;
			
			os = new FileOutputStream(nom);
			
			//apliquem filtre ascii
			if(a == true && tipus.contains("text") || tipus.contains("html") ){
				
				is = new AsciiInputStream(is2);
				
			}
			
			//apliquem filtres gz i zip
			
			if (z == true && gz == true){
				System.out.println("filtre gz i z");
			
				ZipEntry ZipE;
				os = new ZipOutputStream(new GZIPOutputStream(os));
				ZipE= new ZipEntry(nomzip);
				((ZipOutputStream) os).putNextEntry(ZipE);

				
			} else if (z == true && gz == false){
				
				
				ZipEntry ZipE;
				os = new ZipOutputStream(os);
				ZipE= new ZipEntry(nomzip);
				((ZipOutputStream) os).putNextEntry(ZipE);
										
			}else if (z == false && gz == true){
				
				System.out.println("filtre gz");
				
				os = new GZIPOutputStream(os);

			}
			
			while((caracter = is.read()) != -1){
				
				os.write(caracter);
				
			}
			
			//tanquem fluxes
			is.close();
			os.close();
			
			
			
			
			
					
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		
		System.out.println(a);
		
	}

}