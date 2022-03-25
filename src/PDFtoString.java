import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PDFtoString {

private String filename;
private String content;

PDFtoString(String filename) {
    this.filename=filename;
    this.content="";
}

void fillContent() throws IOException {
    PDDocument document = PDDocument.load(new File(this.filename));
    this.content = new PDFTextStripper().getText(document);
    document.close();
}

String getContent() {
    return content;
}

}
