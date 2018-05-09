/**
 * 
 */
import java.io.*;
/**
 * @author HECTORDEARMAS
 *
 */
public class AsciiInputStream extends FilterInputStream{
	
	public AsciiInputStream(InputStream is) {
		super(is);
		
		// TODO Auto-generated constructor stub
	}

	
	public int read() throws IOException
	{
		int carac = super.read();
		char a;
		
		if((char)carac == '<')
		{
			while ((a=(char)super.read()) != '>')
			{}
			if (a == '>')
				return 10; // \n en ascii
			carac = (char)this.read();
		}
		return carac;
	}
}