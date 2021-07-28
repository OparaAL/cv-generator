package com.application.cvgenerator.service.implementation.cv;

import com.application.cvgenerator.dto.user.UserDataDto;
import com.application.cvgenerator.service.interfaces.cv.PdfService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public Map<String, byte[]> createPdf(UserDataDto userDataDto) throws IOException {
        ByteArrayOutputStream byteArr = null;
        String filename = "";
        try{
            Map<String,Object> data = new HashMap<>();

            data.put("firstName", userDataDto.getFirstName());
            data.put("lastName", userDataDto.getLastName());
            data.put("age", userDataDto.getAge());
            data.put("goal", userDataDto.getGoal());
            data.put("experiences", userDataDto.getExperiences());

            byteArr = createCvPdf(data, "cvTemplate.ftl");
            filename = URLEncoder.encode(String.format("%s%s_CV.pdf", userDataDto.getFirstName(), userDataDto.getLastName()), StandardCharsets.UTF_8);
            byteArr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if(byteArr != null) byteArr.close();
        }
        assert byteArr != null;
        return Map.of(filename, byteArr.toByteArray());
    }



    private ByteArrayOutputStream createCvPdf(Map<String, Object> data, String templateName) throws Exception{

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        configuration.setClassForTemplateLoading(PdfServiceImpl.class, "/templates");

        ITextRenderer renderer = new ITextRenderer();

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            configuration.setEncoding(Locale.ENGLISH, "UTF-8");
            Template template = configuration.getTemplate(templateName, "UTF-8");
            StringWriter stringWriter = new StringWriter();

            template.process(data, stringWriter);
            stringWriter.flush();
            String html = stringWriter.toString();

            renderer.setDocumentFromString(htmlToXhtml(html));
            renderer.layout();
            renderer.createPDF(out);
            renderer.finishPDF();
            out.flush();
            return out;
        }
    }

    private String htmlToXhtml(String html) {
        Document document = Jsoup.parse(html);
        document.outputSettings()
                .syntax(Document.OutputSettings.Syntax.xml)
                .escapeMode(Entities.EscapeMode.xhtml);
        return document.html();
    }
}
