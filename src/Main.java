import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        /**
       String filename = "td1.pdf";
       PDFtoString pdf = new PDFtoString("td1.pdf");
       pdf.fillContent();
       System.out.println(pdf.getContent());
        **/

       String address = "https://districtrennais.wixsite.com/rennes/suivre-le-championnat";
       HTMLtoPDF html = new HTMLtoPDF(address);
       html.downloadPDF();

    }
}
