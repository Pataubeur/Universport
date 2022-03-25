import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HTMLtoPDF {

private Document html;
private String classname;

    public HTMLtoPDF(String address) throws IOException {
        this.html = Jsoup.connect(address).get();
        this.classname = "_2Hij5";
    }

    String getContent() {
        return html.html();
    }

    Elements filterByClass() {
        Elements elements = this.html.getElementsByClass(this.classname);
        return elements;
    }

    List<String> getSportPdf() {
        Elements elements = this.filterByClass();
        List<String> listElements = new ArrayList<>();
        for(Element e : elements) {
            listElements.add(e.text() + " " + e.select("a").attr("href"));
        }
        return listElements;
    }

    void downloadPDF() {
        List<String> sports = new ArrayList<>();
        List<String> listElements = this.getSportPdf();
        for(String s : listElements) {
            String[] split = s.split(" ");
            String gender ="";
            if(split.length <= 2) {
                if (!sports.contains(split[0])) {
                    sports.add(split[0]);
                    gender = " JF";
                } else {
                    gender = " JG";
                }
            }
            if(split.length > 1 && split[split.length-1].contains("http")) {
                String pdfName = "";
                for(int i = 0 ; i < split.length - 1 ; i++) {
                    if(i == split.length -2) {
                        pdfName += split[i];
                    } else {
                        pdfName += split[i] + " ";
                    }
                }
                pdfName += gender;
                downloadFile(split[split.length-1], pdfName);
            }
        }
    }

    private static void downloadFile(String fileURL, String fileName){

        //get file name from image path
        /*
        String fileName =
                fileURL.substring( fileURL.lastIndexOf("/") + 1 );
        */
        fileName += ".pdf";

        System.out.println("Saving: " + fileName + ", from: " + fileURL);

        try {

            //open the stream from URL
            URL urlImage = new URL(fileURL);
            InputStream in = urlImage.openStream();

            byte[] buffer = new byte[4096];
            int n = -1;

            OutputStream os =
                    new FileOutputStream("files" + "/" + fileName );

            //write bytes to the output stream
            while ( (n = in.read(buffer)) != -1 ){
                os.write(buffer, 0, n);
            }

            //close the stream
            os.close();

            System.out.println("File saved");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
