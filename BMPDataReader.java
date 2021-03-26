
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class BMPDataReader {
	
	public int readMSBByte( RandomAccessFile rf ) throws IOException {
		int ch1 = rf.read();
        int ch2 = rf.read();
        int ch3 = rf.read();
        int ch4 = rf.read();
        return ((ch4 << 24) + (ch3 << 16) + (ch2 << 8) + (ch1 << 0));
	}
	
	public void readBitmapInformation( File file ) {
		
		RandomAccessFile rf = null;
		
		byte [] bmp_magic = new byte[2];
		
		try {
			rf = new RandomAccessFile(file, "r");
			
			rf.read(bmp_magic);
			
			if( bmp_magic[0] == 'B' && bmp_magic[1] == 'M') {
				System.out.println("This is a Bitmap file");
				
				
				rf.seek(10); // An Offset 10 steht die Position der Bitmap Pixel Werte
				
				long dataOffset = readMSBByte( rf );
				
				
				rf.seek(18); // An Offset 18 steht die Breite des Bitmaps
				


		        int biWidth =  readMSBByte( rf );
		    				
				
				//int biWidth = rf.readInt();
				int biHeight = readMSBByte( rf );
				
				System.out.println("Breite: " + biWidth);
				System.out.println("Height: " + biHeight);
			
				
				rf.seek(dataOffset);
				System.out.println( "R: " + rf.read() );
				System.out.println( "G: " + rf.read() );
				System.out.println( "B: " + rf.read() );
				
			}
			
			rf.close();
			
			
			
		}
		
		catch( FileNotFoundException e ) {
			System.out.println("FileNotFoundException");
		}
		
		catch( IOException e ) {
			System.out.println("IOException");
		}
		
	}

	
	public static void main(String[] args) {
		File  file  =new File("c:\\users\\svenp\\desktop\\yaay_art.bmp");
		
		BMPDataReader bmpDataReader = new BMPDataReader( ) ;
		bmpDataReader.readBitmapInformation( file );

	}

}
