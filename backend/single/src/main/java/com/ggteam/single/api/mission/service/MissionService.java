package com.ggteam.single.api.mission.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class MissionService {

    public static String[] detectObject(MultipartFile image){
        String fileName = image.getOriginalFilename();

        String openApiURL = "http://aiopen.etri.re.kr:8000/ObjectDetect";
        String accessKey = "4579e627-c254-4f60-bb2d-748a1bcc35e5";    // 발급받은 API Key
        String type = getFileExtension(fileName);     // 이미지 파일 확장자
        String imageContents = "";
        Gson gson = new Gson();

        Map<String, Object> request = new HashMap<>();
        Map<String, String> argument = new HashMap<>();


        try {
            byte[] imageBytes = image.getBytes();
            imageContents = Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        argument.put("type", type);
        argument.put("file", imageContents);

        request.put("argument", argument);

        URL url;
        Integer responseCode = null;
        String responBody = null;

        try {
            url = new URL(openApiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Authorization", accessKey);
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(gson.toJson(request).getBytes("UTF-8"));
            wr.flush();
            wr.close();
            responseCode = con.getResponseCode();
            InputStream is = con.getInputStream();
            byte[] buffer = new byte[is.available()];
            int byteRead = is.read(buffer);
            responBody = new String(buffer);

            System.out.println("[responseCode] " + responseCode);
            System.out.println("[responBody]");
            System.out.println(responBody);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(responseCode.equals(413)){
                String[] extractedCharactersArray = {"413"};
            }
        }


        JsonParser parser = new JsonParser();
        JsonObject rootObject = parser.parse(responBody).getAsJsonObject();

        try {
            JsonArray dataArray = rootObject.getAsJsonObject("return_object").getAsJsonArray("data");
            String[] extractedCharactersArray = new String[dataArray.size()];

            for (int i = 0; i < dataArray.size(); i++) {
                JsonObject dataObject = dataArray.get(i).getAsJsonObject();
                String classValue = dataObject.get("class").getAsString();
                extractedCharactersArray[i] = classValue;
            }
            return extractedCharactersArray;
        }
        catch (Exception e){
            String[] extractedCharactersArray = {"실패"};
            return extractedCharactersArray;
        }



    }

    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 0) {
            return fileName.substring(dotIndex + 1);
        }
        return null;
    }


}


