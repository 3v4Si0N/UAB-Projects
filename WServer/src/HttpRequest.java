import java.io.*;
import java.net.*;
import java.util.*;
import java.util.zip.*;


public class HttpRequest implements Runnable
{
	Socket sockt; // objeto sockt de la clase socket

	// Creamos constructor de la clase HttpRequest
	public HttpRequest(Socket socket) throws Exception
	{
		this.sockt = socket;
	}

	
	//La clase Runnable tiene una funcion denominada run, la cual crearemos aqui, en la clase que implementa este interface.
	public void run()
	{
		try
		{
			processRequest();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	// Funcion que hace todo el proceso para poder enviar y recibir informacion
	private void processRequest() throws Exception
	{
		InputStream is = this.sockt.getInputStream();
		OutputStream out = this.sockt.getOutputStream();

		BufferedReader in = new BufferedReader(new InputStreamReader(is)); // Entrada de datos hacia el server
		
		String requestLine = in.readLine(); //Leeremos lo que nos entre por el inputStream
		System.out.println("\r\nPeticion recibida del cliente: " + requestLine); // Saldra por pantalla la peticion GET /_____ HTTP/1.1 recibida por el cliente

		/* La clase StringTokenizer nos ayuda a separar el string en varios substrings (tokens),
		 * utiliza un caracter delimitador para separarlos */
		StringTokenizer tokens = new StringTokenizer(requestLine); // Creamos una variable de tipo StringTokenizer para partir la peticion GET /_____ HTTP/1.1 en substrings
		
		if (requestLine.contains("?"))
			parseWithParam(requestLine, tokens, out);
		else
			parseWithoutParam(tokens, out);
  		
  		//Leemos las cabeceras y las printamos en el server
  		String headerLine = null;
  		for(int i = 0; i <= (headerLine = in.readLine()).length(); i++)
  			System.out.println(headerLine);

  		// Cerramos los flujos de datos en este orden
  		is.close();
  		out.close();
  		this.sockt.close();
	}

	
	// Metodo que parsea la peticion recibida del cliente si contiene un ? en la peticion
	private static void parseWithParam(String requestLine, StringTokenizer tokens, OutputStream out) throws Exception
	{

		StringTokenizer tokens2 = new StringTokenizer(requestLine); // Creamos un objeto de tipo StringTokenizer para poder parsear la peticion recibida por el cliente
		String parameter = null;
		String parameter2 = null;
		
		if(requestLine.contains("&"))
		{
			/*****   PARSE FIRST PARAM, FIRST FORM   *****/
			tokens2.nextToken("?"); // Cuando encontremos un ? y luego un space paramos el parseo y devolvemos el string
			parameter = tokens2.nextToken("? &"); // Cuando encontrado un ? luego un espacio y luego un & paramos el parseo
			
			/*****   PARSE 2º PARAM   *****/
			StringTokenizer tokens3 = new StringTokenizer(requestLine);
			tokens3.nextToken("&");
			parameter2 = tokens3.nextToken("& ");
			System.out.println("Parametro 1: " + parameter); // Escribimos en la consola el parametro obtenido
			System.out.println("Parametro 2: " + parameter2);
		}
		else
		{
			/*****   PARSE FIRST PARAM, FIRST FORM   *****/
			tokens2.nextToken("?"); // Obtenemos el string anterior al ?
			parameter = tokens2.nextToken("? "); // Cuando encontremos un ? y luego un space paramos el parseo y devolvemos el string
			System.out.println("Parametro 1: " + parameter); // Escribimos en la consola el parametro obtenido
		}
		
		/*****   PARSE FIRST PARAM, SECOND FORM   *****/
		/*List<String> l = Arrays.asList(requestLine.split("?"));
		List<String> l2 = Arrays.asList(l.get(1).split(" "));
		String parameter = l2.get(0);*/ 

		if (parameter.equals("asc=true")) // Comprobamos si el cliente ha pedido el parametro ascii y el zip
			sendHeaders(getFile(tokens), out, true, false); // Comprobamos que el archivo solicitado existe, enviamos las cabeceras correspondientes y por ultimo mostramos la pagina solicitada
		else if(parameter.equals("zip=true"))
			sendHeaders(getFile(tokens), out, false, true); // Comprobamos que el archivo solicitado existe, enviamos las cabeceras correspondientes y por ultimo mostramos la pagina solicitada
		else
			sendHeaders(getFile(tokens), out, false, false); // Comprobamos que el archivo solicitado existe, enviamos las cabeceras correspondientes y por ultimo mostramos la pagina solicitada
	}
	
	
	// Metodo que parsea la peticion recibida del cliente sin parametros
	private static void parseWithoutParam(StringTokenizer tokens, OutputStream out) throws Exception
	{	
		sendHeaders(getFile(tokens), out, false, false); // Comprobamos que el archivo solicitado existe, enviamos las cabeceras correspondientes y por ultimo mostramos la pagina solicitada
	}
	
	
	//Metodo que obtiene el archivo solicitado por el cliente
	private static String getFile(StringTokenizer tokens)
	{
		tokens.nextToken(); // Obtenemos el string anterior al primer espacio en blanco
		String fileName = tokens.nextToken(" ?"); // Cuando obtenemos un espacio seguido de un ? paramos el parseo y devolvemos el string
		
		fileName = "." + fileName; //Escribimos en la consola el path completo del archivo
		System.out.println("pathname: " + fileName + "\r\n"); //Escribimos en la consola el path completo del archivo
		return fileName;
	}
	

	/*Funcion que comprueba que extension tiene el archivo solicitado por el cliente, 
	 * para seguidamente devolver el tipo de content-type correspondiente.
	 */
	private static String contentType(String path)
	{
		String content = null;
		if(path.endsWith(".txt")) content = "plain/text";
		else
		if(path.endsWith(".html")) content = "text/html";
		else
		if(path.endsWith(".gif")) content = "image/gif";
		else
		if(path.endsWith(".jpg") || path.endsWith(".jpeg")) content = "image/jpeg";
		else
		if(path.endsWith(".png")) content = "image/png";
		else
		if(path.endsWith(".zip")) content = "application/zip";
		else
		if(path.endsWith(".gzip")) content = "application/z-gzip";
		else
		if(path.endsWith(".xml")) content = "application/xml";
		else
		if(path.endsWith(".pdf")) content = "application/pdf";
		else
			content = "application/octet-stream";

		return content;
	}


	// Funcion que envia al cliente los datos del archivo solicitado por el mismo
	private static void sendData(InputStream is, OutputStream out, boolean ascii, boolean zip, String fileName) throws Exception
	{
		byte[] buffer = new byte[2048];
		int bytes = 0;
		int i = 0;
		
		/*Si el cliente no solicito el parametro ascii y el archivo no es html o 
		 * el cliente solicito el parametro ascii y el archivo  no es un html, leemos el archivo con un buffer
		 */
		if ((!ascii && (contentType(fileName) != "text/html")) || (ascii && contentType(fileName) != "text/html") || (!zip && contentType(fileName) != "text/html"))
		{
			while (i <= (bytes = is.read(buffer)))
			{
				out.write(buffer, 0, bytes);
				i++;
			}
		}
		else // Si el archivo es un html leemos caracter a caracter
		{
			int carac = 0;
			while((carac = is.read()) != -1)
				out.write(carac);
		}
		is.close();
		out.close();
	}
	
	/* Funcion que comprueba que el archivo solicitado por el cliente existe, 
	 * envia las cabeceras correspondientes y seguidamente muestra la pagina que solicito, si esta existe. 
	 * Si no existe envia las cabeceras de error y muestra la pagina de error
	 */
	private static void sendHeaders(String fileName, OutputStream out, boolean ascii, boolean zip) throws Exception
	{
		FileInputStream file = null;
		boolean fileExist = true;

		// Comprobamos que el fichero existe
		try
		{
			file = new FileInputStream(fileName); //Intentamos abrir el archivo
		}
		catch (FileNotFoundException e)
		{
			fileExist = false; // Si no podemos abrir el archivo, significa que no existe, ponemos fileExist = false
		}

		String statusHeader = null;
		String contentTypeHeader = null;
		String entityBody = null;
		String contentDisposition = null;
		String breakLine = "\n";

		if (fileExist) //Si el archivo existe enviamos las cabeceras necesarias: el estado de la peticion y el content-type
		{
			/*** INICIALIZACION CABECERAS ***/
			statusHeader = "HTTP/1.1 200 OK" + "\n";
			contentTypeHeader = "Content-Type: " + contentType(fileName) + "\n";

			// Parseamos el path del archivo solicitado por el cliente para enviarle el archivo sin ./
			List<String> l = Arrays.asList(fileName.split("/"));
			String fileNameParse = l.get(1);
			contentDisposition = "Content-Disposition: form-data; filename=\"" + fileNameParse + ".asc" + "\"" + "\n\n";
			
			out.write(statusHeader.getBytes()); //Enviamos la cabecera de estado, que es comun para todos los casos
			
			// Si el cliente ha solicitado el parametro ascii y el archivo es un html, aplicamos el filtro
			if (ascii && (contentType(fileName) == "text/html"))
			{
				/*** FILTRO ASCII ***/
				out.write(contentTypeHeader.getBytes()); //Enviamos la cabecera content-type
				out.write(contentDisposition.getBytes());
				InputStream is = new AsciiInputStream(file);
				sendData(is, out, ascii, zip, fileName);
			}
			else if(zip)
			{
				/*** FILTRO ZIP ***/
				contentTypeHeader = "Content-Type: application/zip" + "\n";
				contentDisposition = "Content-Disposition: form-data; filename=\"" + fileNameParse + ".zip" + "\"" + "\n\n";
				
				out.write(contentTypeHeader.getBytes()); //Enviamos la cabecera content-type
				out.write(contentDisposition.getBytes());
				
				out = new ZipOutputStream(out); // out es un OutputStream
				ZipEntry entry = new ZipEntry(fileNameParse);
				((ZipOutputStream) out).putNextEntry(entry);
				sendData(file, out, ascii, zip, fileName);
			}
			else // Si no enviamos el archivo solicitado
			{
				/*** SIN FILTROS ***/
				out.write(contentTypeHeader.getBytes()); //Enviamos la cabecera content-type
				out.write(breakLine.getBytes()); // Enviamos un segundo salto de linea para indicar el final de las cabeceras
				sendData(file, out, ascii, zip, fileName); //Enviamos el archivo por el OutputStream
			}
		}
		else //Si no existe enviamos la cabecera HTTP/1.1 404 Not Found para indicar que la pagina a la que se intenta acceder no existe
		{
			/*** PAGINA NO EXISTENTE ***/
			statusHeader = "HTTP/1.1 404 Not Found" + "\r\n";
			contentTypeHeader = "Content-Type: " + "text/html" + "\r\n";
			entityBody = "<html>" + "<head><title>[404]Not Found</title></head>" + 
			"<body><br/><br/><br/><br/><br/><center><h1>ERROR 404 Not Found</h1><h2>Page not exist</h2></center></body></html>";
			
			out.write(statusHeader.getBytes()); // Enviamos la cabecera de estado
			out.write(contentTypeHeader.getBytes()); // Enviamos la cabecera content-type
			out.write(breakLine.getBytes()); // Enviamos un segundo salto de linea para indicar el final de las cabeceras
			out.write(entityBody.getBytes()); // Enviamos la pagina mostrando el error
		}
	}
}