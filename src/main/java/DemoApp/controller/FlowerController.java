package DemoApp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.activation.FileTypeMap;
import javax.imageio.ImageIO;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import DemoApp.model.InventoryProductImage;
import DemoApp.service.FlowerService;
import DemoApp.storage.StorageService;
import Helpers.MappingHelper;
import Request.FlowerRequest;
import Response.ResponseObject;

@RestController
@CrossOrigin("*")
public class FlowerController {
	@Autowired
	private FlowerService flowerService;

	@Autowired
	private StorageService storageService;

//	@Autowired
//	public FlowerController(StorageService storageService) {
//		this.storageService = storageService;
//	}

	@GetMapping(path = "/flower/get")
	public ResponseObject<Object> getAllFlower() throws SQLException, ClassNotFoundException {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		apiRespo.setObject(flowerService.getAllFlower());
		return apiRespo;
	}

	@GetMapping(path = "/flower/get-by-id")
	public ResponseObject<Object> getFlowerById(@RequestBody FlowerRequest request)
			throws SQLException, ClassNotFoundException {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		apiRespo.setObject(flowerService.getFlowerById(request));
		return apiRespo;
	}

	@GetMapping(path = "/flower/get-by-key")
	public ResponseObject<Object> getFlowerByKey(@RequestBody FlowerRequest request)
			throws SQLException, ClassNotFoundException {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		apiRespo.setObject(flowerService.getFlowerByKey(request));
		return apiRespo;
	}

	@PostMapping("flower/add-flower")
	public ResponseObject<Object> addFlower(@RequestParam("file") MultipartFile file,
			@ModelAttribute FlowerRequest request) {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		try {
			storageService.store(file);
			request.setListImage(file.getOriginalFilename());
			flowerService.addFlower(request);
			apiRespo.setObject("Thêm mới sản phẩm thành công!");
		} catch (Exception e) {
			System.out.print(e);
			apiRespo.setObject("Thêm mới sản phẩm thất bại!");
			apiRespo.setError(true);
			apiRespo.setErrorReason(e.toString());

		}
		return apiRespo;
	}

	@GetMapping(value = "/image/{filename:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public ResponseEntity<byte[]> serveFile(@PathVariable String filename) throws IOException {

		//String filePath = storageService.getPath(filename);
		File imgFile = storageService.loadFile(filename);
		return ResponseEntity.ok()
	            .header("Content-Disposition", "attachment; filename=" +imgFile.getName())
	            .contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(imgFile)))
	            .body(Files.readAllBytes(imgFile.toPath()));
	}
	
	@PostMapping("test")
	public ResponseObject<Object> hehe(@ModelAttribute FlowerRequest request) {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		try {
			String jsonString = "[{\"ProductID\":252614,\"Image\":\"https://cdn.tgdd.vn/Products/Images/9079/252614/sua-bot-frisolac-gold-so-4-lon-850g-1-600x600.jpg\",\"ProductName\":\"Sá»®A Bá»T FRISOLAC GOLD 4 LON 850G\",\"productCode\":\"1052846000363\",\"productShortName\":\"Sá»®A Bá»T FRISOLAC GOLD 4 LON 850G\",\"CategoryID\":9079,\"siteID\":17,\"listProductGroup\":[{\"GroupID\":12888,\"GroupName\":\"AVAKids\",\"Description\":\"Sá»­ dá»¥ng cho site 17 + 10\",\"DisplayOrder\":1,\"IsActived\":1,\"ActivedDate\":\"2022-10-26T17:41:46.000+07:00\",\"ActivedUser\":\"162625\",\"UpdatedDate\":\"2022-10-26T17:41:46.000+07:00\",\"UpdatedUser\":\"162625\",\"objProductPropGrpLangBO\":{\"IsExist\":true},\"LanguageID\":\"vi-VN\"},{\"GroupID\":13286,\"GroupName\":\"Filter AVAKids\",\"DisplayOrder\":2,\"IsSpecial\":1,\"IsActived\":1,\"ActivedDate\":\"2022-06-13T17:12:19.000+07:00\",\"ActivedUser\":\"175196\",\"UpdatedDate\":\"2022-06-13T17:12:19.000+07:00\",\"UpdatedUser\":\"175196\",\"objProductPropGrpLangBO\":{\"IsExist\":true},\"LanguageID\":\"vi-VN\"},{\"GroupID\":15406,\"GroupName\":\"Thuá»c tÃ­nh áº©n láº¥y lÃªn bÃ i viáº¿t\",\"DisplayOrder\":3,\"IsActived\":1,\"ActivedDate\":\"2022-07-06T15:21:38.000+07:00\",\"ActivedUser\":\"175196\",\"UpdatedDate\":\"2022-07-06T15:21:38.000+07:00\",\"UpdatedUser\":\"175196\",\"objProductPropGrpLangBO\":{\"IsExist\":true},\"LanguageID\":\"vi-VN\"},{\"GroupID\":15866,\"GroupName\":\"Map cÃ¹ng dÃ²ng kids\",\"DisplayOrder\":4,\"IsSpecial\":1,\"IsActived\":1,\"ActivedDate\":\"2022-07-27T17:38:36.000+07:00\",\"ActivedUser\":\"175196\",\"UpdatedDate\":\"2022-07-27T17:38:36.000+07:00\",\"UpdatedUser\":\"175196\",\"objProductPropGrpLangBO\":{\"IsExist\":true},\"LanguageID\":\"vi-VN\"}]}]";
			List<InventoryProductImage> result = Arrays
					.asList(MappingHelper.parseJsontoObject(jsonString, InventoryProductImage[].class));
			apiRespo.setObject(result);
		} catch (Exception e) {
			System.out.print(e);
			apiRespo.setObject("Thêm mới sản phẩm thất bại!");
			apiRespo.setError(true);
			apiRespo.setErrorReason(e.toString());

		}
		return apiRespo;
	}

}
