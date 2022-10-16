package DemoApp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;

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
import DemoApp.service.FlowerService;
import DemoApp.storage.StorageService;
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
			apiRespo.setObject(storageService.loadAll());
		} catch (Exception e) {
			System.out.print(e);
			apiRespo.setObject(false);

		}
		return apiRespo;
	}

	@GetMapping(value = "/files/{filename:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public ResponseEntity<byte[]> serveFile(@PathVariable String filename) throws IOException {

		//String filePath = storageService.getPath(filename);
		File imgFile = storageService.loadFile(filename);
		return ResponseEntity.ok()
	            .header("Content-Disposition", "attachment; filename=" +imgFile.getName())
	            .contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(imgFile)))
	            .body(Files.readAllBytes(imgFile.toPath()));
	}

}
