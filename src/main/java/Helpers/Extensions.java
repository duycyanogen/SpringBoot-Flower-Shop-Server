package Helpers;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.text.ParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import Response.ResponseObject;

public class Extensions {



	public static ResponseEntity<Object> catchApplicationError(Exception e, ResponseObject<Object> apiRespo) {
		//e.printStackTrace();
		apiRespo.setError(true);
		apiRespo.setErrorReason("Lỗi gọi API");
		apiRespo.setToastMessage("Lỗi gọi API");
		if (e.getMessage().contains("+")) {
			apiRespo.setError(true);
			Pattern pattern = Pattern.compile("\\+(.*?)\\+");
			Matcher matcher = pattern.matcher(e.getMessage());
			if (matcher.find()) {
				apiRespo.setErrorReason(matcher.group(1));
				apiRespo.setToastMessage(matcher.group(1));
			}
		} else {
			apiRespo.setToastMessage(e.toString());
		}

		e.printStackTrace();

		return new ResponseEntity<>(apiRespo, HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<Object> catchApplicationError(Exception e, ResponseObject<Object> apiRespo, String messageDefault) {
		//e.printStackTrace();
		apiRespo.setError(true);
		if(messageDefault!=null && !messageDefault.isEmpty()) {
			apiRespo.setErrorReason(messageDefault);
			apiRespo.setToastMessage(messageDefault);
		}else {
			apiRespo.setErrorReason("Lỗi gọi API");
			apiRespo.setToastMessage("Lỗi gọi API");
		}
		if (e.getMessage().contains("+")) {
			apiRespo.setError(true);
			Pattern pattern = Pattern.compile("\\[\\+(.*?)\\+\\]");
			Matcher matcher = pattern.matcher(e.getMessage());
			if (matcher.find()) {
				apiRespo.setErrorReason(matcher.group(1));
				apiRespo.setToastMessage(matcher.group(1));
			}
		}
		e.printStackTrace();

		return new ResponseEntity<>(apiRespo, HttpStatus.NOT_FOUND);
	}

	public static ResponseObject<Object> catchError(Error err, ResponseObject<Object> apiRespo) {

		apiRespo.setToastMessage(err.getMessage());
		apiRespo.setErrorReason(err.getMessage());
		return apiRespo;
	}

	public static <T> Object parseXMLToObject(String xmlStr, Class<T> clazz) throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return jaxbUnmarshaller.unmarshal(new StringReader(xmlStr));
	}

	
}
